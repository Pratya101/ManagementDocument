package controllers;

/**
 * Created by PRATYA on 11/23/2018.
 */

import models.*;
import play.api.templates.Html;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Mapping extends Controller {

    public static Result main(Html Content) {
        return ok(main.render(Content));
    }

    //Curriculum
    public static List<Curriculum> curriculumList = new ArrayList<Curriculum>();
    public static Form<Curriculum> curriculumForm = Form.form(Curriculum.class);
    public static SyllabusCourse syllabusCourse;
    public static List<SyllabusCourse> syllabusCourseList = new ArrayList<SyllabusCourse>();
    public static Curriculum curriculum;
    public static List<DevelopmentalLearningList> developmentalLearningListList = new ArrayList<DevelopmentalLearningList>();

    public static Result listCurriculum() {
        curriculumList = Curriculum.curriculumList();
        return main(listCurriculum.render(curriculumList));
    }

    /*
        public static Result formAddCurriculum() {
            courseList = Course.courseList();
            developmentalLearningListList = DevelopmentalLearningList.developmentalLearningListList();
            curriculumForm = Form.form(Curriculum.class);
            return main(formAddCurriculum.render(curriculumForm,developmentalLearningListList,courseList));
        }

        public static Result insertCurriculum() {
            courseList = Course.courseList();
            developmentalLearningListList = DevelopmentalLearningList.developmentalLearningListList();
            Form<Curriculum> newCurriculum = curriculumForm.bindFromRequest();
            if (newCurriculum.hasErrors()) {
                flash("newCurriculumError", "ข้อมูลไม่ถูกต้อง กรุณาตรวจสอบใหม่ด้วยครับ!");
                return main(formAddCurriculum.render(newCurriculum,developmentalLearningListList,courseList));
            } else {
                curriculum = newCurriculum.get();
                Curriculum chk;
                chk = Curriculum.finder.byId(curriculum.getId());
                if (chk != null) {
                    flash("newCurriculumError", "รหัสการกระจายนี้มีอยู่เเล้วในฐานข้อมูล ไม่สามารถใช้รหัสซ้ำได้");
                    return main(formAddCurriculum.render(newCurriculum,developmentalLearningListList,courseList));
                } else {
                    Curriculum.insert(curriculum);
                    return redirect("/listCurriculum");
                }
            }
        }
    */
    public static Result editCurriculum(String id) {
        courseList = Course.courseList();
        developmentalLearningListList = DevelopmentalLearningList.developmentalLearningListList();
        curriculum = Curriculum.finder.byId(id);
        if (curriculum != null) {
            curriculumForm = Form.form(Curriculum.class).fill(curriculum);
            return main(editCurriculum.render(curriculumForm, developmentalLearningListList, courseList));
        } else {
            flash("dataCurriculumError", "มีการแก้ไข URL");
            return redirect("/listCurriculum");
        }
    }

    public static Result updateCurriculum() {
        courseList = Course.courseList();
        developmentalLearningListList = DevelopmentalLearningList.developmentalLearningListList();
        Form<Curriculum> dataCurriculum = curriculumForm.bindFromRequest();
        if (dataCurriculum.hasErrors()) {
            flash("dataCurriculumError", "ข้อมูลไม่ถูกต้อง กรุณาตรวจสอบใหม่ด้วยครับ!");
            return main(editCurriculum.render(dataCurriculum, developmentalLearningListList, courseList));
        } else {
            curriculum = dataCurriculum.get();
            Curriculum.update(curriculum);
            return redirect("/listCurriculum");
        }
    }

    public static Result deleteCurriculum(String id) {
        curriculum = Curriculum.finder.byId(id);
        if (curriculum != null) {
            Curriculum.delete(curriculum);
        }
        return redirect("/listCurriculum");
    }

    public static List<Course> courseList = new ArrayList<Course>();

    public static Result CouresList() {
        session("numMenu", "CouresList");
        courseList = new ArrayList<Course>();
        curriculumList = new ArrayList<Curriculum>();
        List<SyllabusCourse> syllabusCourseLists = SyllabusCourse.syllabusCourseList();
        SyllabusCourse syllabusCourse1 = new SyllabusCourse();
        return main(listCourseMap.render(courseList, curriculumList, syllabusCourseLists, syllabusCourse1));
    }

    public static Result loadCourse() {
        curriculumList = Curriculum.curriculumList();
        DynamicForm data = Form.form().bindFromRequest();
        syllabusCourse = SyllabusCourse.finder.byId(data.get("syll"));
        session("syllMapping",syllabusCourse.getId());
        courseList = Course.finder.where().eq("groupCourse.categoryCourse.syllabusCourse.id", syllabusCourse.getId()).findList();
        curriculumList = Curriculum.finder.where().eq("developmentalLearning.syllabusCourse.id", syllabusCourse.getId()).findList();
        syllabusCourseList = SyllabusCourse.syllabusCourseList();
        return main(listCourseMap.render(courseList, curriculumList, syllabusCourseList, syllabusCourse));
    }


    public static Course courses;
    public static String CID;

    public static Result editMapping(String id) {
        curriculumList = Curriculum.finder.where().eq("course.id", id).findList();
        session("ciddd", curriculumList.get(0).getCourse().getId());
        course = Course.finder.byId(session().get("ciddd"));
        return main(editMapping.render(curriculumList, course));
    }

    public static HashMap newmap = new HashMap<String, String>();
    public static HashMap newmap1 = new HashMap<String, String>();
    public static DevelopmentalLearningList developmentalLearningList;
    public static Course course;

    public static Result updateMapping() {
        curriculumList = Curriculum.finder.where().eq("course.id", session("ciddd")).findList();
        DynamicForm newMapping = Form.form().bindFromRequest();
        int i = 0;
        while (newMapping.field("id[" + i + "]") != null) {
            String id = newMapping.field("id[" + i + "]").value();
            String value = newMapping.field("learnId[" + i + "]").value();
            newmap.put(id, value);
            newmap1.put(i, id);
            i++;
            if (i == curriculumList.size()) {
                break;
            }
        }

        int y;
        for (y = 0; y < curriculumList.size(); y++) {
            String id = (String) newmap1.get(y);
            String val = (String) newmap.get(id);
            developmentalLearningList = curriculumList.get(y).getDevelopmentalLearningList();
            course = curriculumList.get(y).getCourse();
            curriculum = Curriculum.finder.byId(id);
            curriculum.setId(id);
            curriculum.setImportance(val);
            Curriculum.update(curriculum);
            flash("updateOK", "ทำการเเก้ไขเรียบร้อยเเล้ว");
        }

/*
        curriculumList = Curriculum.finder.where().eq("course.id",session("ciddd")).findList();
        int text =  curriculumList.size();
        String testSec = session("ciddd");
        String id = newMapping.field("id[16]").value();
        String value = newMapping.field("learnId[16]").value();
        return ok(testVal.render(id,value,testSec));
        */
        return redirect("/listCourseForMapping");

    }

    public static List<CourseID> courseIDList = new ArrayList<CourseID>();

    public static Result listCourseForMapping() {
        courseIDList = CourseID.courseIDList();
        List<SyllabusCourse> syllabusCourseLists = SyllabusCourse.syllabusCourseList();
        SyllabusCourse syllabusCourse1 = new SyllabusCourse();
        return main(listCourseForMapping.render(courseIDList, syllabusCourseLists, syllabusCourse1));
    }


}
