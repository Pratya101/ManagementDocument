package controllers;

import models.*;
import play.api.templates.Html;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.editCat2.*;
import views.html.main;

import java.util.ArrayList;
import java.util.List;

public class CourseDes extends Controller {
    public static Result main(Html Content) {
        return ok(main.render(Content));
    }
    public static GeneralData generalData;
    public static List<CourseDestination> courseDestinationList = new ArrayList<CourseDestination>();
    public static CourseDestination courseDestination;
    public static CourseObjectives courseObjectives;
    public static List<CourseObjectives> courseObjectivesList = new ArrayList<CourseObjectives>();
    public static Course course;
    public static Instructors instructors;
    public static Result saveDes() {
        DynamicForm dataDestination = Form.form().bindFromRequest();
        String detailDes = dataDestination.get("detailDes");
        generalData = GeneralData.finder.byId(dataDestination.get("genId"));
        course = generalData.getCourse();
        courseDestinationList = CourseDestination.courseDestinationList();
        int numlist = courseDestinationList.size();
        int id, idNum;
        String lastNum;
        String sId;
        if (courseDestinationList.size() == 0) {
            sId = "Des-000001";
        } else {
            lastNum = courseDestinationList.get(numlist - 1).getId();
            lastNum = lastNum.substring(4);
            idNum = Integer.parseInt(lastNum);
            id = idNum + 1;
            if (id < 10) {
                sId = "Des-00000" + id;
            } else if (id < 100) {
                sId = "Des-0000" + id;
            } else if (id < 1000) {
                sId = "Des-000" + id;
            } else if (id < 10000) {
                sId = "Des-00" + id;
            } else if (id < 100000) {
                sId = "Des-0" + id;
            } else {
                sId = "Des-" + id;
            }
        }
        courseDestination = new CourseDestination();
        courseDestination.setId(sId);
        courseDestination.setDetail(detailDes);
        courseDestination.setCourse(course);
        courseDestination.setGeneralData(generalData);
        CourseDestination.insert(courseDestination);
        /*
        List<BasketDestination> basketDestinations = new ArrayList<BasketDestination>();
        if (Cache.get("basketDestinations") != null) {
            basketDestinations.addAll((List<BasketDestination>) Cache.get("basketDestinations"));
            basketDestinations.add(new BasketDestination("",detailDes,null,course));
        }else{
            basketDestinations.add(new BasketDestination("", detailDes, null, course));
        }

        Cache.set("basketDestinations", basketDestinations);
        */
        session("nameCategory", "cat2");
        session("generalDataId", generalData.getId());
        return CreateDoc3.selectCat();
    }
    public static Result edit(String id){
        courseDestination=CourseDestination.finder.byId(id);
        generalData = courseDestination.getGeneralData();
        return main(editCourseDes.render(generalData,courseDestination,courseObjectives));
    }
    public static Result update (){
        DynamicForm data = Form.form().bindFromRequest();
        courseDestination = CourseDestination.finder.byId(data.get("idDes"));
        generalData = courseDestination.getGeneralData();
        courseDestination.setDetail(data.get("detailDes"));
        CourseDestination.update(courseDestination);
        session("nameCategory","cat2");
        session("generalDataId", generalData.getId());
        return CreateDoc3.selectCat();
    }
    public static Result deleteDes (String id){
        courseDestination = CourseDestination.finder.byId(id);
        generalData  = courseDestination.getGeneralData();
        if (courseDestination != null){
            CourseDestination.delete(courseDestination);
        }
        session("nameCategory","cat2");
        session("generalDataId", generalData.getId());
        return CreateDoc3.selectCat();
    }
}