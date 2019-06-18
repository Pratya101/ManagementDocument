package controllers;

import com.avaje.ebean.Expr;
import models.*;
import play.api.templates.Html;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Coffee on 23/1/2562.
 */
public class FixTeaching extends Controller {

    public static Result main(Html Content) {
        return ok(main.render(Content));
    }

    public static Instructors instructors;
    public static List<Instructors> instructorsList = new ArrayList<Instructors>();
    public static Form<Instructors> instructorsForm = Form.form(Instructors.class);
    public static SyllabusCourse syllabusCourse;
    public static List<SyllabusCourse> syllabusCourseList = new ArrayList<SyllabusCourse>();
    public static TandY tandY;
    public static List<TandY> tandYList = new ArrayList<TandY>();
    public static Form<TandY> tandYForm = Form.form(TandY.class);

    public static Result listYearAndTerm() {
        session("numMenu","listYearAndTerm");
        tandYList = TandY.tandYList();
        return main(listYearAndTerm.render(tandYList));
    }

    public static Result formAddyearAndTrem() {
        tandYForm = Form.form(TandY.class);
        syllabusCourseList = SyllabusCourse.syllabusCourseList();
        return main(formAddyearAndTrem.render(tandYForm,syllabusCourseList));
    }

    public static Result insertTandM() {
        Form<TandY> newTM = tandYForm.bindFromRequest();
        tandYList = TandY.tandYList();
        int numlist = tandYList.size();
        int id;
        String sId;

        id = numlist + 1;
        if (id < 10) {
            sId = "ty-00000" + id;
        } else if (id < 100) {
            sId = "ty-0000" + id;
        } else if (id < 1000) {
            sId = "ty-000" + id;
        } else if (id < 10000) {
            sId = "ty-00" + id;
        } else if (id < 100000) {
            sId = "ty-0" + id;
        } else {
            sId = "ty-" + id;
        }

        tandY = TandY.finder.where().and(Expr.eq("t", newTM.get().getT()), Expr.eq("y", newTM.get().getY())).eq("syllabusCourse.id",newTM.get().getSyllabusCourse().getId()).findUnique();
        if (tandY != null) {
            flash("ErrorInsert", "เทอมเเละปีของหลักสูตรนี้มีเเล้วในฐานข้อมูล ไม่สามารถทำรายการได้");
            tandYForm = Form.form(TandY.class);
            syllabusCourseList = SyllabusCourse.syllabusCourseList();
            return main(formAddyearAndTrem.render(tandYForm,syllabusCourseList));
        } else {
            tandY = new TandY(sId, newTM.get().getT(), newTM.get().getY(),newTM.get().getSyllabusCourse());
            TandY.insert(tandY);
            flash("insertOK", "ทำการเพิ่มเทอม " + newTM.get().getT() + "/" + newTM.get().getY() + " เรียบร้อยเเล้ว");
            return redirect("/listYearAndTerm");
        }

    }

    public static Result deleteTremAndYear(String id) {
        tandY = TandY.finder.byId(id);
        String trem = tandY.getT();
        String year = tandY.getY();
        instructorsList = Instructors.finder.where().and(Expr.eq("term", trem), Expr.eq("year", year)).eq("syllabusCourse.id",tandY.getSyllabusCourse().getId()).findList();
        if (instructorsList.size() == 0) {
            TandY.delete(tandY);
            flash("delOK", "ลบเทอม " + trem + "/" + year + " เรียบร้อยแล้ว");
        } else {
            flash("DeleteError", "ไม่สามารถลบได้เนื่องจากมีข้อมูลอยู่ในปีเเละเทอมนี้");
        }
        return redirect("/listYearAndTerm");

    }

    public static List<Teacher> teacherList = new ArrayList<Teacher>();
    public static List<CategoryCourse> categoryCourseList = new ArrayList<CategoryCourse>();
    public static CategoryCourse categoryCourse;
    public static List<GroupCourse> groupCourseList = new ArrayList<GroupCourse>();
    public static GroupCourse groupCourse;
    public static Teacher teacher;
    public static Course course;
    public static String idTandY;
    public static String namee;

    public static Result formFixTeaching(String id) {
        if (!session("status").equals("หัวหน้าสาขา")) {
            return main(error404.render());
        }
        session("tandYid", id);
        tandY = TandY.finder.byId(id);
        session("t", tandY.getT());
        session("y", tandY.getY());
        idTandY = id;
        categoryCourseList = CategoryCourse.finder.where().eq("syllabusCourse.id",tandY.getSyllabusCourse().getId()).findList();
        session("nameSyll",tandY.getSyllabusCourse().getNameTH());
        teacherList = Teacher.teacherList();
        List<Course> courseList = new ArrayList<Course>();
        return main(formFixTeaching.render(courseList, teacherList, categoryCourseList, groupCourseList, "", ""));

    }

    public static List<CategoryCourse> nameC;
    public static List<GroupCourse> datag;
    public static String nameGroup;

    public static Result loadData() {
        if (!session("status").equals("หัวหน้าสาขา")) {
            return main(error404.render());
        }
        List<Course> courseList = new ArrayList<Course>();
        DynamicForm data = Form.form().bindFromRequest();
        if (data.get("cat") != null) {
            datag = GroupCourse.listcat(data.get("cat"));
            categoryCourse = CategoryCourse.finder.byId(data.get("cat"));
            if (categoryCourse == null) {
                flash("noData", "ไม่มีข้อมูล");
            } else {

                namee = categoryCourse.getName();
            }
            nameGroup = "";
            return main(formFixTeaching.render(courseList, teacherList, categoryCourseList, datag, namee, nameGroup));
        } else {
            if (data.get("group") != null) {
                courseList = Course.finder.where().eq("groupCourse.id", data.get("group")).findList();
                groupCourse = GroupCourse.finder.byId(data.get("group"));
                if (groupCourse == null) {
                    flash("noData", "ไม่มีข้อมูล");
                    return main(formFixTeaching.render(courseList, teacherList, categoryCourseList, datag, namee, nameGroup));
                } else {
                    nameGroup = groupCourse.getName();
                }
                if (courseList.size()==0){
                    flash("noData","ไม่มีข้อมูลของรายวิชาในกลุ่มนี้");
                }else{
                    namee = courseList.get(0).getGroupCourse().getCategoryCourse().getName();
                }
                teacherList = Teacher.teacherList();
                return main(formFixTeaching.render(courseList, teacherList, categoryCourseList, datag, namee, nameGroup));
            } else {
                categoryCourseList = CategoryCourse.categoryCourseList();
                return main(formFixTeaching.render(courseList, teacherList, categoryCourseList, datag, namee, nameGroup));
            }
        }
    }

    public static Result insertInsttrutors() {
        if (!session("status").equals("หัวหน้าสาขา")) {
            return main(error404.render());
        }
        instructorsList = Instructors.instructorsList();
        int numlist = instructorsList.size();
        int id;
        String lastNum;
        String sId;
        int idNum;
        if (instructorsList.size() == 0) {
            sId = "Ins-000001";
        } else {
            lastNum = instructorsList.get(numlist - 1).getId();
            lastNum = lastNum.substring(4);
            idNum = Integer.parseInt(lastNum);
            id = idNum + 1;
            if (id < 10) {
                sId = "Ins-00000" + id;
            } else if (id < 100) {
                sId = "Ins-0000" + id;
            } else if (id < 1000) {
                sId = "Ins-000" + id;
            } else if (id < 10000) {
                sId = "Ins-00" + id;
            } else if (id < 100000) {
                sId = "Ins-0" + id;
            } else {
                sId = "Ins-" + id;
            }
        }

        DynamicForm newData = Form.form().bindFromRequest();
        course = Course.finder.byId(newData.get("course"));
        String chkidCourse = course.getId();
        teacher = Teacher.finder.byId(newData.get("nameT"));
        String status = newData.get("status");
        instructors = new Instructors();
        instructors.setId(sId);
        instructors.setYear(session("y"));
        instructors.setTerm(session("t"));
        instructors.setCourse(course);
        instructors.setTeacher(teacher);
        instructors.setStatus(status);
        tandY = TandY.finder.byId(session("tandYid"));
        instructors.setSyllabusCourse(tandY.getSyllabusCourse());
        instructors.setRoom(newData.get("room"));
        instructorsList = Instructors.finder.where().and(Expr.eq("term", session("t")), Expr.eq("year", session("y"))).eq("course.id", course.getId()).findList();
       String chk1 = "",chk2="",chk3="";
        if (instructorsList.size() != 0) {
            for (int i = 0; i < instructorsList.size(); i++) {
                chk1 = "";
                chk2 = "";
                chk3 = "";
                if (instructorsList.get(i).getStatus().equals("1") && status.equals("1")) {
                    if (instructorsList.get(i).getRoom().equals(newData.get("room"))){
                        chk1="1_1";
                        break;
                    }else{
                        chk1="1_2";
                    }
                }else{
                    chk1 = "2";
                    }
                if (teacher.getId().equals(instructorsList.get(i).getTeacher().getId())){
                    chk2="1";
                    break;
                }else{
                    chk2="2";
                }
                if (instructorsList.get(i).getStatus().equals("1") && status.equals("1")) {
                    chk1="1";
                    break;
                }else{
                    chk1 = "2";
                }



            }
            if (chk1=="1_1"){
                flash("FixError", "ไม่สามารถทำรายการได้เนื่องจาก วิชานี้เเละห้องได้ถูกกำหนดผู้รับผิดชอบหลักไว้เเล้ว" + instructorsList.size());
                return redirect("/formFixTeaching/" + session("tandYid"));
            }else if(chk1=="1_2"){
                Instructors.insert(instructors);
                flash("insertOK", "ทำการกำหนด " + teacher.getName() + " " + teacher.getSername() + " สอนในรายวิชา" + course.getNameTH() + " เรียบร้อยเเล้ว");
            }else{
                if  (chk2=="1"){
                    flash("FixError", "ไม่สามารถทำรายการได้เนื่องจาก รหัสอาจารย์นี้ได้ถูกกำหนดผู้รับผิดชอบรองไว้เเล้ว");
                       return redirect("/formFixTeaching/" + session("tandYid"));
                }else{
                    Instructors.insert(instructors);
                      flash("insertOK", "ทำการกำหนด " + teacher.getName() + " " + teacher.getSername() + " สอนในรายวิชา" + course.getNameTH());
                }
            }
        } else {
            Instructors.insert(instructors);
            flash("insertOK", "ทำการกำหนด " + teacher.getName() + " " + teacher.getSername() + " สอนในรายวิชา" + course.getNameTH() + " เรียบร้อยเเล้ว");
        }
        return redirect("/listInstructors/" + session("tandYid"));


//        for (int i = 0; i < instructorsList.size(); i++) {
//            if (instructorsList.get(i).getStatus().equals("1") && status.equals("1")) {
//                flash("FixError", "ไม่สามารถทำรายการได้เนื่องจาก วิชานี้ได้ถูกกำหนดผู้รับผิดชอบหลักไว้เเล้ว");
//                return redirect("/formFixTeaching/" + session("tandYid" +instructorsList.get(i).getStatus()+"=="+status ));
//            } else if (instructorsList.get(i).getStatus().equals("1") && status.equals("2")) {
//                if (instructorsList.get(i).getTeacher().getId().equals(teacher.getId())) {
//                    flash("FixError", "ไม่สามารถทำรายการได้เนื่องจาก รหัสอาจารย์นี้ได้ถูกกำหนดผู้รับผิดชอบรองไว้เเล้ว");
//                    return redirect("/formFixTeaching/" + session("tandYid")+
//                            instructorsList.get(i).getTeacher().getId() + "=="+teacher.getId() +"&&" + instructorsList.get(i).getStatus() + "==" +status);
//                } else {
//                    Instructors.insert(instructors);
//                    flash("insertOK", "ทำการกำหนด " + teacher.getName() + " " + teacher.getSername() + " สอนในรายวิชา " + course.getNameTH() + " เรียบร้อยเเล้ว");
//                    return redirect("/listInstructors/" + session("tandYid"));
//                }
//            } else if (instructorsList.get(i).getStatus().equals("1") && status.equals("2")) {
//                if (instructorsList.get(i).getTeacher().getId().equals(teacher.getId())) {
//                    flash("FixError", "ไม่สามารถทำรายการได้เนื่องจาก รหัสอาจารย์นี้ได้ถูกกำหนดผู้รับผิดชอบรองไว้เเล้ว");
//                    return redirect("/formFixTeaching/" + session("tandYid")+
//                            instructorsList.get(i).getTeacher().getId() + "=="+teacher.getId() +"&&" + instructorsList.get(i).getStatus() + "==" +status);
//                } else{
//                    Instructors.insert(instructors);
//                    flash("insertOK", "ทำการกำหนด " + teacher.getName() + " " + teacher.getSername() + " สอนในรายวิชา " + course.getNameTH() + " เรียบร้อยเเล้ว");
//                    return redirect("/listInstructors/" + session("tandYid"));
//                }
//            }
//        }
//        //ถามีการกำหนดผู้รับผิดชอบหลักเเล้ว
//        List<Instructors> chkMainTeacher = Instructors.finder.where().eq("course.id", course.getId()).findList();
//       // List<Instructors> chkCoures = Instructors.finder.where().and(Expr.eq("course.id", course.getId()), Expr.eq("teacher.id", instructors.getTeacher().getId())).and(Expr.eq("status", status), Expr.eq("room", newData.get("room"))).findList();
//        List<Instructors> checkIns = Instructors.finder.where().eq("course.id", course.getId()).findList();
////        for (int i=0;i<instructorsList.size();i++){
//            if (instructorsList.get(i).getTerm()==session("t")&&instructorsList.get(i).getYear()==session("y")
//                    &&course.getId()==instructorsList.get(i).getCourse().getId()&&newData.get("room")==instructorsList.get(i).getRoom()){
//                flash("FixError","ไม่สามารถทำรายการได้เนื่องจาก วิชานี้ได้ถูกกำหนดผู้รับผิดชอบหลักไว้เเล้ว");
////                return redirect("/formFixTeaching/"+session("tandYid"));
//            }
//        }

    }


    public static Result listIns(String id) {
        if (!session("status").equals("หัวหน้าสาขา")) {
            return main(error404.render());
        }
        session("tandYid", id);
        tandY = TandY.finder.byId(id);
        String termm;
        String yearr;
        session("term", tandY.getT());
        session("year", tandY.getY());
        termm = tandY.getT();
        yearr = tandY.getY();
        List<Instructors> instructorsList1 = new ArrayList<Instructors>();
        instructorsList1 = Instructors.finder.where().and(Expr.eq("term", termm), Expr.eq("year", yearr)).eq("syllabusCourse.id",tandY.getSyllabusCourse().getId()).findList();
        return main(listInstructors.render(instructorsList1,tandY));
    }

    public static Result deleteIns(String id) {
        if (!session("status").equals("หัวหน้าสาขา")) {
            return main(error404.render());
        }
        instructors = Instructors.finder.byId(id);
        String term = instructors.getTerm();
        String year = instructors.getYear();
        if (instructors == null) {
            flash("nodata", "ไม่มีข้อมูลไม่สามารถลบได่");
        } else {
            Instructors.delete(instructors);
            flash("delOK", "ทำการลบเรียบร้อยแล้ว");
        }
        return redirect("/listInstructors/" + session("tandYid"));
    }

    public static Result detailIns(String id) {
        instructors = Instructors.finder.byId(id);
        return main(detailInstructors.render(instructors));
    }


    public static Result editFixTeaching(String id) {
        instructors = Instructors.finder.byId(id);
        session("t", instructors.getTerm());
        session("y", instructors.getYear());
        session("InsID", instructors.getId());
        if (instructors != null) {
            categoryCourseList = CategoryCourse.categoryCourseList();
            teacherList = Teacher.teacherList();
            List<Course> courseList = Course.finder.where().eq("groupCourse.id", instructors.getCourse().getGroupCourse().getId()).findList();
            groupCourseList = GroupCourse.listcat(instructors.getCourse().getGroupCourse().getCategoryCourse().getId());
            String nameCatrgory = instructors.getCourse().getGroupCourse().getCategoryCourse().getName();
            String nameGroupCourse = instructors.getCourse().getGroupCourse().getName();
            String fnameTeacher = instructors.getTeacher().getName();
            String lnameTeacher = instructors.getTeacher().getSername();
            String room = instructors.getRoom();
            String status = instructors.getStatus();
            String nameCoures = instructors.getCourse().getNameTH();
            String idCoures = instructors.getCourse().getId();
            session("nameSyll",instructors.getSyllabusCourse().getNameTH());
            return main(editFixTeaching.render(courseList, teacherList, categoryCourseList, groupCourseList, nameCatrgory, nameCatrgory, fnameTeacher, lnameTeacher, status, room, nameCoures, idCoures));
        } else {
            flash("editInsError", "มีการแก้ไข URL");
            return redirect("/listInstructors/" + session("tandYid"));

        }
    }


    public static Result loadDataEdit() {
        if (!session("status").equals("หัวหน้าสาขา")) {
            return main(error404.render());
        }
        List<Course> courseList = new ArrayList<Course>();
        DynamicForm data = Form.form().bindFromRequest();
        String fnameTeacher = data.get("fnameTeacher");
        String lnameTeacher = data.get("lnameTeacher");
        String status = data.get("status");
        String room = data.get("room");
        categoryCourse = CategoryCourse.finder.where().eq("name", data.get("category")).findUnique();
        if (categoryCourse != null) {
            datag = GroupCourse.finder.where().eq("categoryCourse.id", categoryCourse.getId()).findList();
        } else {
            flash("noData", "ไม่มีข้อมูล");
        }
        if (data.get("cat") != null) {
            datag = GroupCourse.listcat(data.get("cat"));
            categoryCourse = CategoryCourse.finder.byId(data.get("cat"));
            if (categoryCourse == null) {
                flash("noData", "ไม่มีข้อมูล");
            } else {

                namee = categoryCourse.getName();
            }
            nameGroup = "";
            return main(editFixTeaching.render(courseList, teacherList, categoryCourseList, datag, namee, nameGroup, fnameTeacher, lnameTeacher, status, room, "", ""));
        } else {
            if (data.get("group") != null) {
                courseList = Course.finder.where().eq("groupCourse.id", data.get("group")).findList();
                groupCourse = GroupCourse.finder.byId(data.get("group"));
                if (groupCourse == null) {
                    flash("noData", "ไม่มีข้อมูล");
                } else {
                    nameGroup = groupCourse.getName();
                }
                namee = courseList.get(0).getGroupCourse().getCategoryCourse().getName();
                teacherList = Teacher.teacherList();
                return main(editFixTeaching.render(courseList, teacherList, categoryCourseList, datag, namee, nameGroup, fnameTeacher, lnameTeacher, status, room, "", ""));
            } else {
                categoryCourseList = CategoryCourse.categoryCourseList();
                return main(editFixTeaching.render(courseList, teacherList, categoryCourseList, datag, namee, nameGroup, fnameTeacher, lnameTeacher, status, room, "", ""));
            }
        }
    }

    public static Result updateInsttrutors() {
        DynamicForm dataUpdate = Form.form().bindFromRequest();
        String InsId = session().get("InsID");
        String term = session("t");
        String yeat = session("y");
        String status = dataUpdate.get("status");
        String room = dataUpdate.get("room");
        teacher = Teacher.finder.byId(dataUpdate.get("nameT"));
        tandY = TandY.finder.byId(session("tandYid"));
        instructors.setSyllabusCourse(tandY.getSyllabusCourse());
        course = Course.finder.byId(dataUpdate.get("course"));
        instructors = new Instructors(InsId, term, yeat, status, room,null,tandY.getSyllabusCourse(), teacher, course);
        Instructors.update(instructors);
        flash("updateOK", "ทำการเเก้ไขเรียบร้อยเเล้ว");
        return redirect("/listInstructors/" + session("tandYid"));
    }
}


