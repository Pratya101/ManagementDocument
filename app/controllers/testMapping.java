package controllers;

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

/**
 * Created by Coffee on 23/1/2562.
 */
public class testMapping extends Controller {

    public static Result main(Html Content) {
        return ok(main.render(Content));
    }

    public static List<DevelopmentalLearningList> devList = new ArrayList<DevelopmentalLearningList>();
    public static Course course;
    public static DevelopmentalLearningList datalist;
    public static Form<Curriculum> curriculumForm = Form.form(Curriculum.class);
    public static List<CourseID> courseIDList = new ArrayList<CourseID>();
    public static CourseID courseIDd;
    public static List<Course> courseList = new ArrayList<Course>();
    public static List<Curriculum> curriculumList = new ArrayList<Curriculum>();
    public static Curriculum curriculum;
    public static SyllabusCourse syllabusCourse;
    public static List<SyllabusCourse> syllabusCourseList = new ArrayList<SyllabusCourse>();
    public static Result formAddMaipping(String id) {
        devList = DevelopmentalLearningList.finder.where().eq("developmentalLearning.syllabusCourse.id",session("syllMapping")).findList();
        curriculumList = Curriculum.finder.where().eq("developmentalLearning.syllabusCourse.id",session("syllMapping")).findList();
        session("courseId", id);
        course = Course.finder.byId(id);
        courseIDd = CourseID.listChkCourse(id);
        if (courseIDd != null) {
            if (courseIDList != null) {
                flash("chkCourse", "รายวิชานี้ได้ทำการ Mapping ไว้เเล้ว กรุณาไปตรวจสอบที่หน้ารายการ Mapping");
               return Mapping.loadCourse();
            } else {
                return main(formAddMapping.render(course, devList));
            }
        } else {
            return main(formAddMapping.render(course, devList));
        }
    }

    public static HashMap newmap = new HashMap<String, String>();

    public static Result inserttestMapping() {
        courseIDList = CourseID.courseIDList();
        devList = DevelopmentalLearningList.developmentalLearningListList();
        DynamicForm newMapping = Form.form().bindFromRequest();
        int i = 0;
        while (newMapping.field("id[" + i + "]") != null) {
            String id = newMapping.field("id[" + i + "]").value();
            String value = newMapping.field("learnId[" + i + "]").value();
            newmap.put(id, value);
            i++;
            if (i == devList.size()) {
                break;
            }
        }
        int y;
        Course dataC = Course.finder.byId(newMapping.get("idc"));
        for (y = 0; y < devList.size(); y++) {
            List<DevelopmentalLearningList> devlists = new ArrayList<DevelopmentalLearningList>();
            DevelopmentalLearning devs;
            devList = DevelopmentalLearningList.developmentalLearningListList();
            String id = devList.get(y).getId();
            String val = (String) newmap.get(id);
            datalist = DevelopmentalLearningList.finder.byId(id);
            devs = DevelopmentalLearning.finder.byId(datalist.getDevelopmentalLearning().getId());
            Curriculum newdata = new Curriculum(val, datalist,devs, dataC);
            Curriculum.insert(newdata);
        }
        courseIDList = CourseID.courseIDList();
        int numlist = courseIDList.size();
        int id, idNum;
        String lastNum;
        String sId;
        if (courseIDList.size() == 0) {
            sId = "C-000001";
        } else {
            lastNum = courseIDList.get(numlist - 1).getId();
            lastNum = lastNum.substring(2);
            idNum = Integer.parseInt(lastNum);
            id = idNum + 1;
            if (id < 10) {
                sId = "C-00000" + id;
            } else if (id < 100) {
                sId = "C-0000" + id;
            } else if (id < 1000) {
                sId = "C-000" + id;
            } else if (id < 10000) {
                sId = "C-00" + id;
            } else if (id < 100000) {
                sId = "C-0" + id;
            } else {
                sId = "Ins-" + id;
            }
        }
        CourseID newdataid = new CourseID();
        newdataid.setId(sId);
        newdataid.setCourse(Course.finder.byId(session("courseId")));
        CourseID.insert(newdataid);
        course = Course.finder.byId(session("courseId"));
        flash("insertOK", "ทำการMappingรายวิชา" + course.getNameTH() + "เรียบร้อยแล้ว");
        return redirect("/listCourseForMapping");

    }

    public static Result listCourseForMapping() {
        session("numMenu","listCourseForMapping");
        courseIDList = new ArrayList<CourseID>();
        List<SyllabusCourse> syllabusCourseLists = SyllabusCourse.syllabusCourseList();
        SyllabusCourse syllabusCourse1 = new SyllabusCourse();
        return main(listCourseForMapping.render(courseIDList,syllabusCourseLists,syllabusCourse1));
    }

    public static Result loadCourse (){
        DynamicForm data = Form.form().bindFromRequest();
        courseIDList = CourseID.finder.where().eq("course.groupCourse.categoryCourse.syllabusCourse.id",data.get("syll")).findList();
        syllabusCourse = SyllabusCourse.finder.byId(data.get("syll"));
        syllabusCourseList = SyllabusCourse.syllabusCourseList();
        return main(listCourseForMapping.render(courseIDList, syllabusCourseList, syllabusCourse));
    }

    public static Result detailMapping(String id) {
        courseIDList = CourseID.courseIDList();
        course = Course.finder.byId(id);
        List<Curriculum> dataCurriculumList = Curriculum.dataCurriculum(id);
        if (dataCurriculumList == null) {
            flash("noData", "มีการแก้ไข URL");
            return redirect("/listCourseForMapping");
        } else {

            return main(detailMapping.render(dataCurriculumList, course));
        }

    }

    public static Result deleteMapping(String id) {
        courseIDd = CourseID.finder.byId(id);

        curriculumList = Curriculum.finder.where().eq("course.id", courseIDd.getCourse().getId()).findList();
        if (curriculumList != null) {
            for (int i = 0; i < curriculumList.size(); i++) {
                curriculum = curriculumList.get(i);
                Curriculum.delete(curriculum);
            }
            CourseID.delete(courseIDd);
            flash("delOK","ลบรายการ Mapping ของรายวิชา  " + courseIDd.getCourse().getNameTH() + " เรียบร้อยแล้ว");
        }
        return redirect("/listCourseForMapping");
    }

}


