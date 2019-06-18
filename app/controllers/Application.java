package controllers;

import models.*;
import play.api.templates.Html;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Application extends Controller {


    public static Result main(Html Content) {
        return ok(main.render(Content));
    }

    //Login*****************************************************************************************
public static Instructors instructors;
    public static Result login() {
        instructorsList = Instructors.instructorsList();
        int moreTerm=0;
        int moreYear=0;
        for (int i=0 ;i<instructorsList.size();i++){
            if (moreTerm<Integer.parseInt(instructorsList.get(i).getTerm())){
                moreTerm = Integer.parseInt(instructorsList.get(i).getTerm());
            }

        }
        for (int i=0 ;i<instructorsList.size();i++){
            if (moreYear<Integer.parseInt(instructorsList.get(i).getYear())){
                moreYear = Integer.parseInt(instructorsList.get(i).getYear());
            }

        }
        session("useTerm",Integer.toString(moreTerm));
        session("useYear",Integer.toString(moreYear));
        if (session("userId")== null){
        Form<Teacher> teacherForm = Form.form(Teacher.class);
        return ok(loginPage.render());
        }else{
            if (session("status").equals("ผู้ดูเเลระบบ")) {
                return main(indexAdmin.render());
            }else{
                    return redirect("/showDoc3");
            }
        }
    }

    public static Result subbmitLogin() {
        String usr = Form.form().bindFromRequest().get("username");
        String pwd = Form.form().bindFromRequest().get("password");
        Teacher logged = Teacher.authen(usr, pwd);
        if (logged != null) {
            session("userId", logged.getId());
            session("name", logged.getName());
            session("sername", logged.getSername());
            session("position", logged.getPosition());
            session("status", logged.getStatus());
            String satatus = logged.getStatus();
                return redirect("/");
        } else {
            flash("loginError", "Username หรือ Password ไม่ถูกต้องกรุณาตรวจสอบใหม่ด้วยครับ !");
            return login();
        }
    }

    public static Result logOut() {
        session().clear();
        return redirect("/");
    }

    //Faculty
    public static List<Faculty> facultyList  = new ArrayList<Faculty>();
    public static Form<Faculty> facultyForm = Form.form(Faculty.class);
    public static Faculty faculty;

    public static Result listFaculty() {
        if (!session("status").equals("ผู้ดูเเลระบบ")){
            flash("UserError","คุณไม่มีสิทธิ์เข้าใข้งานในส่วนนี้");
            return main(error404.render());
        }
        facultyList = Faculty.facultyList();
        return main(listFaculty.render(facultyList));
    }

    public static Result formAddFaculty() {
        if (!session("status").equals("ผู้ดูเเลระบบ")){
            flash("UserError","คุณไม่มีสิทธิ์เข้าใข้งานในส่วนนี้");
            return main(error404.render());
        }
        facultyForm = Form.form(Faculty.class);
        return main(formAddFaculty.render(facultyForm));
    }

    public static Result insertFaculty() {
        if (!session("status").equals("ผู้ดูเเลระบบ")){
            flash("UserError","คุณไม่มีสิทธิ์เข้าใข้งานในส่วนนี้");
            return main(error404.render());
        }
        Form<Faculty> newFaculty = facultyForm.bindFromRequest();
        if (newFaculty.hasErrors()) {
            flash("newFacError", "ข้อมูลไม่ถูกต้อง กรุณาตรวจสอบใหม่ด้วยครับ!");
            return main(formAddFaculty.render(newFaculty));
        } else {
            faculty = newFaculty.get();
            facultyList = Faculty.facultyList();
                int numlist = facultyList.size();
                int id,idNum;
                String sId,lastNum;
                if (facultyList.size() ==0){
                    sId ="F-000001";
                    faculty.setId(sId);
                    Faculty.insert(faculty);
                    flash("insertOK","ทำการเพิ่ม " + faculty.getName() + " เรียบร้อยเเล่ว");
                }else{
                    lastNum =  facultyList.get(numlist-1).getId();
                    lastNum = lastNum.substring(2);
                    idNum = Integer.parseInt(lastNum);
                    id = idNum + 1;
                    if (id < 10) {
                        sId = "F-00000" + id;
                    } else if (id < 100) {
                        sId = "F-0000" + id;
                    } else if (id < 1000) {
                        sId = "F-000" + id;
                    } else if (id < 10000) {
                        sId = "F-00" + id;
                    } else if (id < 100000) {
                        sId = "F-0" + id;
                    } else {
                        sId = "F-" + id;
                    }
                    faculty.setId(sId);
                    Faculty.insert(faculty);
                    flash("insertOK","ทำการเพิ่ม " + faculty.getName() + " เรียบร้อยเเล่ว");
                }
                return redirect("/listFaculty");
            }
    }

    public static Result editFaculty(String id) {
        if (!session("status").equals("ผู้ดูเเลระบบ")){
            flash("UserError","คุณไม่มีสิทธิ์เข้าใข้งานในส่วนนี้");
            return main(error404.render());
        }
        faculty = Faculty.finder.byId(id);
        if (faculty != null) {
            facultyForm = Form.form(Faculty.class).fill(faculty);
            return main(editFaculty.render(facultyForm));
        } else {
            flash("dataFacError", "มีการแก้ไข URL");
            return redirect("/listFaculty");
        }
    }

    public static Result updateFaculty() {
        if (!session("status").equals("ผู้ดูเเลระบบ")){
            flash("UserError","คุณไม่มีสิทธิ์เข้าใข้งานในส่วนนี้");
            return main(error404.render());
        }
        Form<Faculty> dataFaculty = facultyForm.bindFromRequest();
        if (dataFaculty.hasErrors()) {
            flash("dataFacError", "ข้อมูลไม่ถูกต้อง กรุณาตรวจสอบใหม่ด้วยครับ!");
            return main(editFaculty.render(dataFaculty));
        } else {
            faculty = dataFaculty.get();
            Faculty.update(faculty);
                flash("updateOK", "ทำการเเก้ไขเรียบร้อยเเล้ว");
            return redirect("/listFaculty");
        }
    }

    public static Result deleteFaculty(String id) {
        faculty = Faculty.finder.byId(id);
        if (faculty != null) {
            departmentList = Department.finder.where().eq("faculty.id",id).findList();
            if (departmentList.size() != 0){
                flash("noDelOK","ไม่สามาถลบได้เนื่องจากข้อมูลนี้มีการใช้งานจากหน้าอื่นอยู่");
            }else{
                Faculty.delete(faculty);
                flash("delOK","ลบ" + faculty.getName() + "เรียบร้อยแล้ว");
            }
        }
        return redirect("/listFaculty");
    }

    //Department

    public static List<Department> departmentList = new ArrayList<Department>();
    public static Form<Department> departmentForm = Form.form(Department.class);
    public static Department department;

    public static Result listDepartment() {
        if (!session("status").equals("ผู้ดูเเลระบบ")){
            flash("UserError","คุณไม่มีสิทธิ์เข้าใข้งานในส่วนนี้");
            return main(error404.render());
        }

        departmentList = Department.departmentList();
        return main(listDepartment.render(departmentList));
    }

    public static Result formAddDepartment() {
        if (!session("status").equals("ผู้ดูเเลระบบ")){
            flash("UserError","คุณไม่มีสิทธิ์เข้าใข้งานในส่วนนี้");
            return main(error404.render());
        }
        departmentForm = Form.form(Department.class);
        facultyList = Faculty.facultyList();
        return main(formAddDepartment.render(departmentForm,facultyList));
    }

    public static Result insertDepartment() {
        if (!session("status").equals("ผู้ดูเเลระบบ")){
            flash("UserError","คุณไม่มีสิทธิ์เข้าใข้งานในส่วนนี้");
            return main(error404.render());
        }
        facultyList = Faculty.facultyList();
        Form<Department> newDepartment = departmentForm.bindFromRequest();
        if (newDepartment.hasErrors()) {
            flash("newDepartError", "ข้อมูลไม่ถูกต้อง กรุณาตรวจสอบใหม่ด้วยครับ!");
            return main(formAddDepartment.render(newDepartment,facultyList));
        } else {
            department = newDepartment.get();
            departmentList = Department.departmentList();
            int numlist = departmentList.size();
            int id,idNum;
            String sId,lastNum;
            if (departmentList.size() ==0 ){
                sId ="DEP-000001";
                department.setId(sId);
                Department.insert(department);
                flash("insertOK","ทำการเพิ่ม " + department.getNameTh() + " เรียบร้อยเเล่ว");
            }else{
                lastNum =  departmentList.get(numlist-1).getId();
                lastNum = lastNum.substring(4);
                idNum = Integer.parseInt(lastNum);
                id = idNum + 1;
                if (id < 10) {
                    sId = "DEP-00000" + id;
                } else if (id < 100) {
                    sId = "DEP-0000" + id;
                } else if (id < 1000) {
                    sId = "DEP-000" + id;
                } else if (id < 10000) {
                    sId = "DEP-00" + id;
                } else if (id < 100000) {
                    sId = "DEP-0" + id;
                } else {
                    sId = "DEP-" + id;
                }
                department = newDepartment.get();
                department.setId(sId);
                Department.insert(department);
                flash("insertOK","ทำการเพิ่ม " + department.getNameTh() + " เรียบร้อยเเล่ว");
            }
            return redirect("/listDepartment");
        }
    }

    public static Result editDepartment(String id) {
        if (!session("status").equals("ผู้ดูเเลระบบ")){
            flash("UserError","คุณไม่มีสิทธิ์เข้าใข้งานในส่วนนี้");
            return main(error404.render());
        }
        department = Department.finder.byId(id);
        facultyList  = Faculty.facultyList();
        if (department != null) {
            departmentForm = Form.form(Department.class).fill(department);
            return main(editDepartment.render(departmentForm,facultyList));
        } else {
            flash("dataDepartError", "มีการแก้ไข URL");
            return redirect("/listDepartment");
        }
    }

    public static Result updateDepartment() {
        if (!session("status").equals("ผู้ดูเเลระบบ")){
            flash("UserError","คุณไม่มีสิทธิ์เข้าใข้งานในส่วนนี้");
            return main(error404.render());
        }
        Form<Department> dataDepartment = departmentForm.bindFromRequest();
        facultyList = Faculty.facultyList();
        if (dataDepartment.hasErrors()) {
            flash("newDepartError", "ข้อมูลไม่ถูกต้อง กรุณาตรวจสอบใหม่ด้วยครับ!");
            return main(editDepartment.render(dataDepartment,facultyList));
        } else {
            department = dataDepartment.get();
            Department.update(department);
            flash("updateOK", "ทำการเเก้ไขเรียบร้อยเเล้ว");
            return redirect("/listDepartment");
        }
    }

    public static Result deleteDepartment(String id) {
        if (!session("status").equals("ผู้ดูเเลระบบ")){
            flash("UserError","คุณไม่มีสิทธิ์เข้าใข้งานในส่วนนี้");
            return main(error404.render());
        }
        department = Department.finder.byId(id);
        if (department != null) {
            teacherList = Teacher.finder.where().eq("department.id",id).findList();
            if (teacherList.size() != 0){
                flash("noDelOK","ไม่สามาถลบได้เนื่องจากข้อมูลนี้มีการใช้งานจากหน้าอื่นอยู่");
            }else{
                flash("delOK","ลบ" + department.getNameTh() + "เรียบร้อยแล้ว");
                Department.delete(department);
            }
        }
        return redirect("/listDepartment");
    }

    //Teacher
    public static List<Teacher> teacherList = new ArrayList<Teacher>();
    public static Form<Teacher> teacherForm = Form.form(Teacher.class);
    public static Teacher teacher;

    public static Result listTeacher() {
        if (!session("status").equals("ผู้ดูเเลระบบ")){
            flash("UserError","คุณไม่มีสิทธิ์เข้าใข้งานในส่วนนี้");
            return main(error404.render());
        }
        teacherList = Teacher.teacherList();
        return main(listTeacher.render(teacherList));
    }

    public static Result formAddTeacher() {
        if (!session("status").equals("ผู้ดูเเลระบบ")){
            flash("UserError","คุณไม่มีสิทธิ์เข้าใข้งานในส่วนนี้");
            return main(error404.render());
        }
        teacherForm = Form.form(Teacher.class);
        departmentList = Department.departmentList();
        return main(formAddTeacher.render(teacherForm, departmentList));
    }

    public static Result insertTeacher() {
        if (!session("status").equals("ผู้ดูเเลระบบ")){
            flash("UserError","คุณไม่มีสิทธิ์เข้าใข้งานในส่วนนี้");
            return main(error404.render());
        }
        departmentList = Department.departmentList();
        Form<Teacher> newTeacher = teacherForm.bindFromRequest();
        if (newTeacher.hasErrors()) {
            flash("newTeachError", "ท่านป้อนข้อมูลไม่ถูกต้องกรุณาตรวจสอบใหม่ด้วยครับ");
            return main(formAddTeacher.render(newTeacher, departmentList));
        } else {
            teacher = newTeacher.get();
            teacher.setStatus("user");
            Teacher chk;
            chk = Teacher.finder.byId(teacher.getId());
            if (chk != null) {
                flash("newTeachError", "รหัสอาจารย์ท่านนี้มีอยู่เเล้วในฐานข้อมูลไม่สามารถใช้รหัสซ้ำได้ครับ");
                return main(formAddTeacher.render(newTeacher, departmentList));
            } else {
                Teacher.insert(teacher);
                flash("insertOK", "ทำการเพิ่ม " + teacher.getName() + "  " + teacher.getSername() + " เรียบร้อยเเล่ว");
                return redirect("/listTeacher");
            }
        }
    }

    public static Result editTeacher(String id) {
        if (!session("status").equals("ผู้ดูเเลระบบ")){
            flash("UserError","คุณไม่มีสิทธิ์เข้าใข้งานในส่วนนี้");
            return main(error404.render());
        }
        departmentList = Department.departmentList();
        teacher = Teacher.finder.byId(id);

        if (teacher != null) {
            teacherForm = Form.form(Teacher.class).fill(teacher);
            return main(editTeacher.render(teacherForm, departmentList));
        } else {
            flash("dataTeachError", "มีการแก้ไข URL");
            return redirect("/listTeacher");
        }
    }

    public static Result updateTeacher() {
        if (!session("status").equals("ผู้ดูเเลระบบ")){
            flash("UserError","คุณไม่มีสิทธิ์เข้าใข้งานในส่วนนี้");
            return main(error404.render());
        }
        departmentList = Department.departmentList();
        Form<Teacher> dataTeacher = teacherForm.bindFromRequest();
        if (dataTeacher.hasErrors()) {
            flash("newTeachError", "ข้อมูลไม่ถูกต้อง กรุณาตรวจสอบใหม่ด้วยครับ!");
            return main(editTeacher.render(dataTeacher, departmentList));
        } else {
            teacher = dataTeacher.get();
            Teacher.update(teacher);
            flash("updateOK", "ทำการเเก้ไขเรียบร้อยเเล้ว");
            return redirect("/listTeacher");
        }
    }
    public static List<Instructors>    instructorsList = new ArrayList<Instructors>();
    public static Result deleteTeacher(String id) {
        if (!session("status").equals("ผู้ดูเเลระบบ")){
            flash("UserError","คุณไม่มีสิทธิ์เข้าใข้งานในส่วนนี้");
            return main(error404.render());
        }
        teacher = Teacher.finder.byId(id);
        if (teacher != null) {
            instructorsList = Instructors.finder.where().eq("teacher.id",id).findList();
            if (instructorsList.size() != 0){
                flash("noDelOK","ไม่สามาถลบได้เนื่องจากข้อมูลนี้มีการใช้งานจากหน้าอื่นอยู่");
            }else{
                flash("insertOK","ทำการเพิ่ม " + teacher.getName() + "  " + teacher.getSername() + " เรียบร้อยเเล่ว");
                Teacher.delete(teacher);
            }
        }
        return redirect("/listTeacher");
    }

    public static Result detailTeacher(String id) {
        if (!session("status").equals("ผู้ดูเเลระบบ")){
            flash("UserError","คุณไม่มีสิทธิ์เข้าใข้งานในส่วนนี้");
            return main(error404.render());
        }
        teacher = Teacher.finder.byId(id);
        if (teacher != null) {
            return main(detailTeacher.render(teacher));
        } else {
            return redirect("/listTeacher");
        }
    }

    //Syllabus
    public static List<SyllabusCourse> syllabusCourseList = new ArrayList<SyllabusCourse>();
    public static Form<SyllabusCourse> syllabusCourseForm = Form.form(SyllabusCourse.class);
    public static SyllabusCourse syllabusCourse;

    public static Result listSyllabusCourse() {
        if (!session("status").equals("ผู้ดูเเลระบบ")){
            flash("UserError","คุณไม่มีสิทธิ์เข้าใข้งานในส่วนนี้");
            return main(error404.render());
        }
        syllabusCourseList = SyllabusCourse.syllabusCourseList();
        return main(listSyllabusCourse.render(syllabusCourseList));
    }

    public static Result formAddSyllabusCourse() {
        if (!session("status").equals("ผู้ดูเเลระบบ")){
            flash("UserError","คุณไม่มีสิทธิ์เข้าใข้งานในส่วนนี้");
            return main(error404.render());
        }
        departmentList = Department.departmentList();
        syllabusCourseForm = Form.form(SyllabusCourse.class);
        return main(formAddSyllabusCourse.render(syllabusCourseForm,departmentList));
    }

    public static Result insertSyllabusCourse() {
        if (!session("status").equals("ผู้ดูเเลระบบ")){
            flash("UserError","คุณไม่มีสิทธิ์เข้าใข้งานในส่วนนี้");
            return main(error404.render());
        }
        departmentList = Department.departmentList();
        Form<SyllabusCourse> newSyllabusCourse = syllabusCourseForm.bindFromRequest();
        if (newSyllabusCourse.hasErrors()) {
            return main(formAddSyllabusCourse.render(newSyllabusCourse,departmentList));
        } else {
            syllabusCourse = newSyllabusCourse.get();
            syllabusCourseList = SyllabusCourse.syllabusCourseList();
            int numlist = syllabusCourseList.size();
            int id,idNum;
            String lastNum;
            String sId;
            if (syllabusCourseList.size()  == 0 ){
                sId = "SYL-000001";
                syllabusCourse.setId(sId);
                SyllabusCourse.insert(syllabusCourse);
                flash("insertOK","ทำการเพิ่ม " + syllabusCourse.getNameTH() + " เรียบร้อยเเล่ว");
            }else{
                lastNum =  syllabusCourseList.get(numlist-1).getId();
                lastNum = lastNum.substring(4);
                idNum = Integer.parseInt(lastNum);
                id = idNum + 1;
                if (id < 10) {
                    sId = "SYL-00000" + id;
                } else if (id < 100) {
                    sId = "SYL-0000" + id;
                } else if (id < 1000) {
                    sId = "SYL-000" + id;
                } else if (id < 10000) {
                    sId = "SYL-00" + id;
                } else if (id < 100000) {
                    sId = "SYL-0" + id;
                } else {
                    sId = "SYL-" + id;
                }
                syllabusCourse.setId(sId);
                SyllabusCourse.insert(syllabusCourse);
                flash("insertOK","ทำการเพิ่ม " + syllabusCourse.getNameTH() + " เรียบร้อยเเล่ว");
            }
                return redirect("/listSyllabusCourse");

        }
    }

    public static Result editSyllabusCourse(String id) {
        syllabusCourse = SyllabusCourse.finder.byId(id);
        departmentList = Department.departmentList();
        if (syllabusCourse != null) {
            syllabusCourseForm = Form.form(SyllabusCourse.class).fill(syllabusCourse);
            return main(editSyllabusCourse.render(syllabusCourseForm,departmentList));
        } else {
            flash("datasyllabusError", "มีการแก้ไข URL");
            return redirect("/listSyllabusCourse");
        }
    }

    public static Result updateSyllabusCourse() {
        if (!session("status").equals("ผู้ดูเเลระบบ")){
            flash("UserError","คุณไม่มีสิทธิ์เข้าใข้งานในส่วนนี้");
            return main(error404.render());
        }
        departmentList = Department.departmentList();
        Form<SyllabusCourse> dataSyllabusCourse = syllabusCourseForm.bindFromRequest();
        if (dataSyllabusCourse.hasErrors()) {
            flash("dataSyllabusError", "ข้อมูลไม่ถูกต้อง กรุณาตรวจสอบใหม่ด้วยครับ!");
            return main(editSyllabusCourse.render(dataSyllabusCourse,departmentList));
        } else {
            syllabusCourse = dataSyllabusCourse.get();
            SyllabusCourse.update(syllabusCourse);
            flash("updateOK", "ทำการเเก้ไขเรียบร้อยเเล้ว");
            return redirect("/listSyllabusCourse");
        }
    }

    public static Result deleteSyllabusCourse(String id) {
        if (!session("status").equals("ผู้ดูเเลระบบ")){
            flash("UserError","คุณไม่มีสิทธิ์เข้าใข้งานในส่วนนี้");
            return main(error404.render());
        }
        syllabusCourse = SyllabusCourse.finder.byId(id);
        if (syllabusCourse != null) {
            categoryCourseList = CategoryCourse.finder.where().eq("syllabusCourse.id",id).findList();
            if (categoryCourseList.size() != 0){
                flash("noDelOK","ไม่สามาถลบได้เนื่องจากข้อมูลนี้มีการใช้งานจากหน้าอื่นอยู่");
            }else{
                flash("delOK","ลบ" + syllabusCourse.getNameTH() + "เรียบร้อยแล้ว");
                SyllabusCourse.delete(syllabusCourse);
            }
        }
        return redirect("/listSyllabusCourse");
    }

    //CategoryCourse
    public static List<CategoryCourse> categoryCourseList = new ArrayList<CategoryCourse>();
    public static Form<CategoryCourse> categoryCourseForm = Form.form(CategoryCourse.class);
    public static CategoryCourse categoryCourse;

    public static Result listCategoryCourse(String id) {
        session("idSyllabus",id);
        if (!session("status").equals("ผู้ดูเเลระบบ")){
            flash("UserError","คุณไม่มีสิทธิ์เข้าใข้งานในส่วนนี้");
            return main(error404.render());
        }
        categoryCourseList = CategoryCourse.finder.where().eq("syllabusCourse.id",session("idSyllabus")).findList();
        syllabusCourse = SyllabusCourse.finder.byId(id);
            return main(listCategoryCourse.render(categoryCourseList,syllabusCourse));
    }

    public static Result formAddCategoryCourse() {
        if (!session("status").equals("ผู้ดูเเลระบบ")){
            flash("UserError","คุณไม่มีสิทธิ์เข้าใข้งานในส่วนนี้");
            return main(error404.render());
        }
        syllabusCourseList = SyllabusCourse.syllabusCourseList();
        categoryCourseForm = Form.form(CategoryCourse.class);
        syllabusCourse = SyllabusCourse.finder.byId(session("idSyllabus"));
        return main(formAddCategoryCourse.render(categoryCourseForm,syllabusCourseList,syllabusCourse));
    }

    public static Result insertCategoryCourse() {
        if (!session("status").equals("ผู้ดูเเลระบบ")){
            flash("UserError","คุณไม่มีสิทธิ์เข้าใข้งานในส่วนนี้");
            return main(error404.render());
        }
        syllabusCourse = SyllabusCourse.finder.byId(session("idSyllabus"));
        syllabusCourseList = SyllabusCourse.syllabusCourseList();
        Form<CategoryCourse> newCategoryCourse = categoryCourseForm.bindFromRequest();
        if (newCategoryCourse.hasErrors()) {
            flash("newCategoryError", "ท่านกรอกข้อมูลไม่ถูกต้อง กรุณาตรวจสอบใหม่ด้วยครับ");
            return main(formAddCategoryCourse.render(newCategoryCourse,syllabusCourseList,syllabusCourse));
        } else {
            categoryCourse = newCategoryCourse.get();
            int i ;
            String id;
            Random random = new Random();
            i = random.nextInt(100000)+1;
            id = "CR-" + Integer.toString(i);
            syllabusCourse = SyllabusCourse.finder.byId(session("idSyllabus"));
            categoryCourse.setId(id);
            categoryCourse.setSyllabusCourse(syllabusCourse);
            CategoryCourse.insert(categoryCourse);
            flash("insertOK","ทำการเพิ่ม " + categoryCourse.getName() + " เรียบร้อยเเล้ว");
                return redirect("/listCategoryCourse/"+session("idSyllabus"));
        }
    }

    public static Result editCategoryCourse(String id) {
        if (!session("status").equals("ผู้ดูเเลระบบ")){
            flash("UserError","คุณไม่มีสิทธิ์เข้าใข้งานในส่วนนี้");
            return main(error404.render());
        }
        syllabusCourseList = SyllabusCourse.syllabusCourseList();
        categoryCourse = CategoryCourse.finder.byId(id);
        if (categoryCourse != null) {
            categoryCourseForm = Form.form(CategoryCourse.class).fill(categoryCourse);
            return main(editCategoryCourse.render(categoryCourseForm,syllabusCourseList));
        } else {
            flash("dataCategoryError", "มีการแก้ไข URL");
            return listCategoryCourse(session("idSyllabus"));
        }
    }

    public static Result updateCategoryCourse() {
        if (!session("status").equals("ผู้ดูเเลระบบ")){
            flash("UserError","คุณไม่มีสิทธิ์เข้าใข้งานในส่วนนี้");
            return main(error404.render());
        }
        syllabusCourseList = SyllabusCourse.syllabusCourseList();
        Form<CategoryCourse> dataCategoryCourse = categoryCourseForm.bindFromRequest();
        syllabusCourse = SyllabusCourse.finder.byId(session("idSyllabus"));
        if (dataCategoryCourse.hasErrors()) {
            flash("dataCategoryError", "ข้อมูลไม่ถูกต้อง กรุณาตรวจสอบใหม่ด้วยครับ!");
            return main(editCategoryCourse.render(dataCategoryCourse,syllabusCourseList));
        } else {
                CategoryCourse dataUpdate = new CategoryCourse(dataCategoryCourse.get().getId(),dataCategoryCourse.get().getName(),dataCategoryCourse.get().getAmount(),syllabusCourse);
            CategoryCourse.update(dataUpdate);
            flash("updateOK", "ทำการเเก้ไขเรียบร้อยเเล้ว");
            return redirect("/listCategoryCourse/"+session("idSyllabus"));
        }
    }

    public static Result deleteCategoryCourse(String id) {
        if (!session("status").equals("ผู้ดูเเลระบบ")){
            flash("UserError","คุณไม่มีสิทธิ์เข้าใข้งานในส่วนนี้");
            return main(error404.render());
        }
        categoryCourse = CategoryCourse.finder.byId(id);
        if (categoryCourse != null) {
            groupCourseList = GroupCourse.finder.where().eq("categoryCourse.id",id).findList();
            if (groupCourseList.size() != 0 ){
                flash("noDelOK","ไม่สามาถลบได้เนื่องจากข้อมูลนี้มีการใช้งานจากหน้าอื่นอยู่");
            }else{
                flash("delOK","ลบ" + categoryCourse.getName() + "เรียบร้อยแล้ว");
                CategoryCourse.delete(categoryCourse);
            }

        }
        return redirect("/listCategoryCourse/"+session("idSyllabus"));
    }



    //GroupCourse
    public static List<GroupCourse> groupCourseList = new ArrayList<GroupCourse>();
    public static Form<GroupCourse> groupCourseForm = Form.form(GroupCourse.class);
    public static GroupCourse groupCourse;
    public static String CatId;

    public static Result listGroupCourse(String id) {
        session("nameCat",id);
        if (!session("status").equals("ผู้ดูเเลระบบ")){
            flash("UserError","คุณไม่มีสิทธิ์เข้าใข้งานในส่วนนี้");
            return main(error404.render());
        }
            groupCourseList  = GroupCourse.groupCourseOnly(id);
            categoryCourse = CategoryCourse.nameCat(id);
            return main(listGroupCourse.render(groupCourseList,categoryCourse));



    }

    public static Result formAddGroupCourse(String id) {
        if (!session("status").equals("ผู้ดูเเลระบบ")){
            flash("UserError","คุณไม่มีสิทธิ์เข้าใข้งานในส่วนนี้");
            return main(error404.render());
        }
        categoryCourse = CategoryCourse.finder.byId(session("nameCat"));
        categoryCourseList = CategoryCourse.categoryCourseList();
        groupCourseForm = Form.form(GroupCourse.class);
        return main(formAddGroupCourse.render(groupCourseForm,categoryCourseList,CatId,categoryCourse));
    }

    public static Result insertGroupCourse() {
        if (!session("status").equals("ผู้ดูเเลระบบ")){
            flash("UserError","คุณไม่มีสิทธิ์เข้าใข้งานในส่วนนี้");
            return main(error404.render());
        }

        categoryCourseList = CategoryCourse.categoryCourseList();
        Form<GroupCourse> newGroupCourse = groupCourseForm.bindFromRequest();
        if (newGroupCourse.hasErrors()) {
            flash("newGroupError", "ท่านกรอกข้อมูลไม่ถูกต้อง กรุณาตรวจสอบใหม่ด้วยครับ");
            categoryCourse = CategoryCourse.finder.byId(session("nameCat"));
            return main(formAddGroupCourse.render(newGroupCourse,categoryCourseList,CatId,categoryCourse));
        } else {
            groupCourse = newGroupCourse.get();
            categoryCourse = CategoryCourse.finder.byId(session("nameCat"));
            int i ;
            String id;
            Random random = new Random();
            i = random.nextInt(100000)+1;
            id = "GR-" + Integer.toString(i);
            groupCourse.setId(id);
                groupCourse.setCategoryCourse(categoryCourse);
                GroupCourse.insert(groupCourse);
            flash("insertOK","ทำการเพิ่ม " + groupCourse.getName() + " เรียบร้อยเเล้ว");
                return redirect("/listGroupCourse/" + session("nameCat"));
        }
    }

    public static Result editGroupCourse(String id) {
        if (!session("status").equals("ผู้ดูเเลระบบ")){
            flash("UserError","คุณไม่มีสิทธิ์เข้าใข้งานในส่วนนี้");
            return main(error404.render());
        }
        categoryCourseList = CategoryCourse.categoryCourseList();
        groupCourse = GroupCourse.finder.byId(id);
        if (groupCourse != null) {
            groupCourseForm = Form.form(GroupCourse.class).fill(groupCourse);
            return main(editGroupCourse.render(groupCourseForm,categoryCourseList));
        } else {
            flash("dataGroupError", "มีการแก้ไข URL");
            return redirect("/listGroupCourse/" + session("nameCat"));
        }
    }

    public static Result updateGroupCourse() {
        if (!session("status").equals("ผู้ดูเเลระบบ")){
            flash("UserError","คุณไม่มีสิทธิ์เข้าใข้งานในส่วนนี้");
            return main(error404.render());
        }
        categoryCourseList = CategoryCourse.categoryCourseList();
        Form<GroupCourse> dataGroupCourse = groupCourseForm.bindFromRequest();
        if (dataGroupCourse.hasErrors()) {
            flash("dataGroupError", "ข้อมูลไม่ถูกต้อง กรุณาตรวจสอบใหม่ด้วยครับ!");
            return main(editGroupCourse.render(dataGroupCourse,categoryCourseList));
        } else {
            groupCourse = dataGroupCourse.get();
            GroupCourse.update(groupCourse);
            flash("updateOK", "ทำการเเก้ไขเรียบร้อยเเล้ว");
            return redirect("/listGroupCourse/"+session("nameCat"));
        }
    }

    public static Result deleteGroupCourse(String id) {
        if (!session("status").equals("ผู้ดูเเลระบบ")){
            flash("UserError","คุณไม่มีสิทธิ์เข้าใข้งานในส่วนนี้");
            return main(error404.render());
        }
        groupCourse = GroupCourse.finder.byId(id);
        if (groupCourse != null) {
            courseList = Course.finder.where().eq("groupCourse.id",id).findList();
            if (courseList.size() != 0 ){
                flash("noDelOK","ไม่สามาถลบได้เนื่องจากข้อมูลนี้มีการใช้งานจากหน้าอื่นอยู่");
            }else{
                flash("delOK","ลบ" + groupCourse.getName() + "เรียบร้อยแล้ว");
                GroupCourse.delete(groupCourse);
            }

        }
        return redirect("/listGroupCourse/"+session("nameCat"));
    }

    //Course
    public static List<Course> courseList = new ArrayList<Course>();
    public static Form<Course> courseForm = Form.form(Course.class);
    public static Course course;
    public static String GCID;
    public static Result listCourse(String id) {
        if (!session("status").equals("ผู้ดูเเลระบบ")){
            flash("UserError","คุณไม่มีสิทธิ์เข้าใข้งานในส่วนนี้");
            return main(error404.render());
        }
        GCID = id;
        session("groupCourseID",id);
        groupCourse = GroupCourse.finder.byId(GCID);
        courseList = Course.courseOnly(GCID);
        categoryCourse = CategoryCourse.finder.byId(groupCourse.getCategoryCourse().getId());
        return main(listCourse.render(courseList,groupCourse,categoryCourse));
    }

    public static Result formAddCourse() {
        if (!session("status").equals("ผู้ดูเเลระบบ")){
            flash("UserError","คุณไม่มีสิทธิ์เข้าใข้งานในส่วนนี้");
            return main(error404.render());
        }
        groupCourseList = GroupCourse.groupCourseList();
        courseForm = Form.form(Course.class);
        groupCourse = GroupCourse.finder.byId(session("groupCourseID"));
        return main(formAddCourse.render(courseForm,groupCourseList,groupCourse));
    }

    public static Result insertCourse() {
        if (!session("status").equals("ผู้ดูเเลระบบ")){
            flash("UserError","คุณไม่มีสิทธิ์เข้าใข้งานในส่วนนี้");
            return main(error404.render());
        }
        groupCourseList = GroupCourse.groupCourseList();
        Form<Course> newCourse = courseForm.bindFromRequest();

        if (newCourse.hasErrors()) {
            flash("newCourseError", "ท่านกรอกข้อมูลไม่ถูกต้อง กรุณาตรวจสอบใหม่ด้วยครับ");
            groupCourse = GroupCourse.finder.byId(session("groupCourseID"));
            return main(formAddCourse.render(newCourse,groupCourseList,groupCourse));
        } else {
            course = newCourse.get();
                courseList = Course.courseList();
                int numlist = courseList.size();
                int id;
                String lastNum;
                String sId;
                int idNum;
                if (courseList.size() == 0) {
                    sId = "COS-000001";
                } else {
                    lastNum = courseList.get(numlist - 1).getId();
                    lastNum = lastNum.substring(4);
                    idNum = Integer.parseInt(lastNum);
                    id = idNum + 1;
                    if (id < 10) {
                        sId = "COS-00000" + id;
                    } else if (id < 100) {
                        sId = "COS-0000" + id;
                    } else if (id < 1000) {
                        sId = "COS-000" + id;
                    } else if (id < 10000) {
                        sId = "COS-00" + id;
                    } else if (id < 100000) {
                        sId = "COS-0" + id;
                    } else {
                        sId = "COS-" + id;
                    }
                }
                groupCourse  = GroupCourse.finder.byId(session("groupCourseID"));
                course.setGroupCourse(groupCourse);
                course.setId(sId);
                Course.insert(course);
                flash("insertOK","ทำการเพิ่ม " + course.getNameTH() + " เรียบร้อยเเล้ว");
                return redirect("/listCourse/"+session("groupCourseID"));
        }
    }

    public static Result editCourse(String id) {
        if (!session("status").equals("ผู้ดูเเลระบบ")){
            flash("UserError","คุณไม่มีสิทธิ์เข้าใข้งานในส่วนนี้");
            return main(error404.render());
        }
        groupCourseList = GroupCourse.groupCourseList();
        course = Course.finder.byId(id);
        if (course != null) {
            courseForm = Form.form(Course.class).fill(course);
            return main(editCourse.render(courseForm,groupCourseList));
        } else {
            flash("dataCourseError", "มีการแก้ไข URL");
            return redirect("/listCourse/"+session("groupCourseID"));
        }
    }

    public static Result updateCourse() {
        if (!session("status").equals("ผู้ดูเเลระบบ")){
            flash("UserError","คุณไม่มีสิทธิ์เข้าใข้งานในส่วนนี้");
            return main(error404.render());
        }
        groupCourseList = GroupCourse.groupCourseList();
        Form<Course> dataCourse = courseForm.bindFromRequest();
        if (dataCourse.hasErrors()) {
            flash("dataCourseError", "ข้อมูลไม่ถูกต้อง กรุณาตรวจสอบใหม่ด้วยครับ!");
            return main(editCourse.render(dataCourse,groupCourseList));
        } else {
            course = dataCourse.get();
            Course.update(course);
            flash("updateOK", "ทำการเเก้ไขเรียบร้อยเเล้ว");
            return redirect("/listCourse/"+session("groupCourseID"));
        }
    }

    public static Result deleteCourse(String id) {
        if (!session("status").equals("ผู้ดูเเลระบบ")){
            flash("UserError","คุณไม่มีสิทธิ์เข้าใข้งานในส่วนนี้");
            return main(error404.render());
        }
        course = Course.finder.byId(id);
        if (course != null) {
            Course.delete(course);
            flash("delOK","ลบ วิชา " + course.getNameTH() + "เรียบร้อยแล้ว");
        }
        return redirect("/listCourse/"+session("groupCourseID"));
    }

    public static Result detailCourse  (String id){
        if (!session("status").equals("ผู้ดูเเลระบบ")){
            flash("UserError","คุณไม่มีสิทธิ์เข้าใข้งานในส่วนนี้");
            return main(error404.render());
        }
        course = Course.finder.byId(id);
        if (course != null){
            return main(detailCourse.render(course));
        }else{
            flash("noDataCourse","ไม่มีข้อมูลรายวิชานี้");
            return redirect("/listCourse");
        }
    }


    //GeneralData
    public static List<GeneralData> generalDataList = new ArrayList<GeneralData>();
    public static Form<GeneralData> generalDataForm = Form.form(GeneralData.class);
    public static GeneralData generalData;

    public static Result listGeneralData() {
        if (!session("status").equals("ผู้ดูเเลระบบ")){
            flash("UserError","คุณไม่มีสิทธิ์เข้าใข้งานในส่วนนี้");
            return main(error404.render());
        }
        generalDataList = GeneralData.generalDataList();
        return main(listGeneralData.render(generalDataList));
    }

    public static Result formAddGeneralData() {
        if (!session("status").equals("ผู้ดูเเลระบบ")){
            flash("UserError","คุณไม่มีสิทธิ์เข้าใข้งานในส่วนนี้");
            return main(error404.render());
        }
        generalDataForm = Form.form(GeneralData.class);
        courseList = Course.courseList();
        return main(formAddGeneralData.render(generalDataForm, courseList));
    }

    public static Result insertGeneralData() {
        if (!session("status").equals("ผู้ดูเเลระบบ")){
            flash("UserError","คุณไม่มีสิทธิ์เข้าใข้งานในส่วนนี้");
            return main(error404.render());
        }
        courseList = Course.courseList();
        Form<GeneralData> newGeneralData = generalDataForm.bindFromRequest();
        if (newGeneralData.hasErrors()) {
            flash("newGeneralError", "ท่านป้อนข้อมูลไม่ถูกต้องกรุณาตรวจสอบใหม่ด้วยครับ");
            return main(formAddGeneralData.render(newGeneralData, courseList));
        } else {
            generalData = newGeneralData.get();
            GeneralData chk;
            chk = GeneralData.finder.byId(generalData.getId());
            if (chk != null) {
                flash("newGeneralError", "รหัสอาจารย์ท่านนี้มีอยู่เเล้วในฐานข้อมูลไม่สามารถใช้รหัสซ้ำได้ครับ");
                return main(formAddGeneralData.render(newGeneralData, courseList));
            } else {
                GeneralData.insert(generalData);
                return redirect("/listGeneralData");
            }
        }
    }

    public static Result editGeneralData(String id) {
        if (!session("status").equals("ผู้ดูเเลระบบ")){
            flash("UserError","คุณไม่มีสิทธิ์เข้าใข้งานในส่วนนี้");
            return main(error404.render());
        }
        courseList = Course.courseList();
        generalData = GeneralData.finder.byId(id);

        if (generalData != null) {
            generalDataForm = Form.form(GeneralData.class).fill(generalData);
            return main(editGeneralData.render(generalDataForm, courseList));
        } else {
            flash("dataGeneralError", "มีการแก้ไข URL");
            return redirect("/listGeneralData");
        }
    }

    public static Result updateGeneralData() {
        if (!session("status").equals("ผู้ดูเเลระบบ")){
            flash("UserError","คุณไม่มีสิทธิ์เข้าใข้งานในส่วนนี้");
            return main(error404.render());
        }
        courseList = Course.courseList();
        Form<GeneralData> dataGeneralData = generalDataForm.bindFromRequest();
        if (dataGeneralData.hasErrors()) {
            flash("newGeneralError", "ข้อมูลไม่ถูกต้อง กรุณาตรวจสอบใหม่ด้วยครับ!");
            return main(editGeneralData.render(dataGeneralData, courseList));
        } else {
            generalData = dataGeneralData.get();
            GeneralData.update(generalData);
            return redirect("/listGeneralData");
        }
    }

    public static Result deleteGeneralData(String id) {
        if (!session("status").equals("ผู้ดูเเลระบบ")){
            flash("UserError","คุณไม่มีสิทธิ์เข้าใข้งานในส่วนนี้");
            return main(error404.render());
        }
        generalData = GeneralData.finder.byId(id);
        if (generalData != null) {
            GeneralData.delete(generalData);
        }
        return redirect("/listGeneralData");
    }

    public static Result detailGeneralData(String id) {
        if (!session("status").equals("ผู้ดูเเลระบบ")){
            flash("UserError","คุณไม่มีสิทธิ์เข้าใข้งานในส่วนนี้");
            return main(error404.render());
        }
        generalData = GeneralData.finder.byId(id);
        teacher = Teacher.finder.byId(session("userId"));
        if (generalData != null) {
            return main(detailGeneralData.render(generalData,teacher));
        } else {
            flash("noDataGeneral", "ไม่มีข้อมูลอาจารย์");
            return redirect("/listGeneralData");
        }
    }
}
