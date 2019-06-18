package controllers;

import models.Course;
import models.CourseDestination;
import models.CourseObjectives;
import models.GeneralData;
import play.api.templates.Html;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.editCat2.*;
import views.html.main;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by coffee on 5/9/2019.
 */
public class CourseObj extends Controller {
    public static GeneralData generalData;
    public static List<CourseObjectives> courseObjectivesList = new ArrayList<CourseObjectives>();
    public static CourseObjectives courseObjectivess;
    public static CourseDestination courseDestination;
    public static Course course;
    public static Result main(Html Content) {
        return ok(main.render(Content));
    }

    public static Result saveCourseObj (){
        DynamicForm courseObjectives = Form.form().bindFromRequest();
        String detailOJT = courseObjectives.get("detailOJT");
        generalData = GeneralData.finder.byId(courseObjectives.get("genId"));
        course =  generalData.getCourse();
        courseObjectivesList = CourseObjectives.courseObjectivesList();
        int numlist = courseObjectivesList.size();
        int id, idNum;
        String lastNum;
        String sId;
        if (courseObjectivesList.size() == 0) {
            sId = "OJT-000001";
        } else {
            lastNum = courseObjectivesList.get(numlist - 1).getId();
            lastNum = lastNum.substring(4);
            idNum = Integer.parseInt(lastNum);
            id = idNum + 1;
            if (id < 10) {
                sId = "OJT-00000" + id;
            } else if (id < 100) {
                sId = "OJT-0000" + id;
            } else if (id < 1000) {
                sId = "OJT-000" + id;
            } else if (id < 10000) {
                sId = "OJT-00" + id;
            } else if (id < 100000) {
                sId = "OJT-0" + id;
            } else {
                sId = "OJT-" + id;
            }
        }
        courseObjectivess = new CourseObjectives();
        courseObjectivess.setId(sId);
        courseObjectivess.setDetail(detailOJT);
        courseObjectivess.setGeneralData(generalData);
        courseObjectivess.setCourse(course);
        CourseObjectives.insert(courseObjectivess);
        /*
        List<BasketObjectives> basketObjectives= new ArrayList<BasketObjectives>();
        if (Cache.get("basketObjectives") != null) {
            basketObjectives.addAll((List<BasketObjectives>) Cache.get("basketObjectives"));
            basketObjectives.add(new BasketObjectives("",detailOJT,null,course));
        }else{
            basketObjectives.add(new BasketObjectives("", detailOJT, null, course));
        }
        Cache.set("basketObjectives", basketObjectives);
        */
        session("nameCategory", "cat2");
        session("generalDataId", generalData.getId());
        return CreateDoc3.selectCat();
    }
    public static Result edit (String id){
        courseObjectivess = CourseObjectives.finder.byId(id);
        generalData = courseObjectivess.getGeneralData();
        return main(editCourseDes.render(generalData,courseDestination,courseObjectivess));
    }
    public static Result update (){
        DynamicForm data = Form.form().bindFromRequest();
        courseObjectivess = CourseObjectives.finder.byId(data.get("idObj"));
        generalData = courseObjectivess.getGeneralData();
        courseObjectivess.setDetail(data.get("detailObj"));
        CourseObjectives.update(courseObjectivess);
        session("nameCategory","cat2");
        session("generalDataId", generalData.getId());
        return CreateDoc3.selectCat();
    }
    public static Result deleteObj (String id){
        courseObjectivess = CourseObjectives.finder.byId(id);
        generalData  = courseObjectivess.getGeneralData();
        if (courseObjectivess != null){
            CourseObjectives.delete(courseObjectivess);
        }
        session("nameCategory","cat2");
        session("generalDataId", generalData.getId());
        return CreateDoc3.selectCat();
    }

}
