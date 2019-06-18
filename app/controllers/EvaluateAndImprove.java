package controllers;

import models.*;
import play.api.templates.Html;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;
import views.html.main;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by coffee on 5/14/2019.
 */
public class    EvaluateAndImprove extends Controller {
    public static GeneralData generalData;
    public static Course course;
    public static List<CourseAssessmentStrategies> courseAssessmentStrategiesList  = new ArrayList<CourseAssessmentStrategies>();
    public static CourseAssessmentStrategies courseAssessmentStrategies;
    public static List<TeachingStrategiesData> teachingStrategiesDataList = new ArrayList<TeachingStrategiesData>();
    public static TeachingStrategiesData teachingStrategiesData;
    public static List<ImproveTeachingData> improveTeachingDataList = new ArrayList<ImproveTeachingData>();
    public static ImproveTeachingData improveTeachingData;
    public static List<StandardReview> standardReviewList = new ArrayList<StandardReview>();
    public static StandardReview standardReview;
    public static List<ReviewAndPlan> reviewAndPlanList = new ArrayList<ReviewAndPlan>();
    public static ReviewAndPlan reviewAndPlan;


    public static Result main(Html Content) {
        return ok(main.render(Content));
    }

    public static Result saveData7_1(){
        DynamicForm data7_1 = Form.form().bindFromRequest();
        String datadDetail = data7_1.get("detail");
        generalData = GeneralData.finder.byId(data7_1.get("genId"));
        course = generalData.getCourse();
        courseAssessmentStrategiesList = CourseAssessmentStrategies.courseAssessmentStrategiesList();
        int numlist = courseAssessmentStrategiesList.size();
        int id, idNum;
        String lastNum;
        String sId;
        if (courseAssessmentStrategiesList.size() == 0) {
            sId = "CAS-000001";
        } else {
            lastNum = courseAssessmentStrategiesList.get(numlist - 1).getId();
            lastNum = lastNum.substring(4);
            idNum = Integer.parseInt(lastNum);
            id = idNum + 1;
            if (id < 10) {
                sId = "CAS-00000" + id;
            } else if (id < 100) {
                sId = "CAS-0000" + id;
            } else if (id < 1000) {
                sId = "CAS-000" + id;
            } else if (id < 10000) {
                sId = "CAS-00" + id;
            } else if (id < 100000) {
                sId = "CAS-0" + id;
            } else {
                sId = "CAS-" + id;
            }
        }
        courseAssessmentStrategies  = new CourseAssessmentStrategies();
        courseAssessmentStrategies.setId(sId);
        courseAssessmentStrategies.setDetail(datadDetail);
        courseAssessmentStrategies.setGeneralData(generalData);
        courseAssessmentStrategies.setCourse(course);
        CourseAssessmentStrategies.insert(courseAssessmentStrategies);
        session("nameCategory", "cat7");
        session("generalDataId", generalData.getId());
        return CreateDoc3.selectCat();
    }

    public static Result saveData7_2 (){
        DynamicForm data7_2  = Form.form().bindFromRequest();
        String detail = data7_2.get("detail");
        generalData = GeneralData.finder.byId(data7_2.get("genId"));
        course = generalData.getCourse();
        teachingStrategiesDataList = TeachingStrategiesData.teachingStrategiesDataList();
        int numlist = teachingStrategiesDataList.size();
        int id, idNum;
        String lastNum;
        String sId;
        if (teachingStrategiesDataList.size() == 0) {
            sId = "TSD-000001";
        } else {
            lastNum = teachingStrategiesDataList.get(numlist - 1).getId();
            lastNum = lastNum.substring(4);
            idNum = Integer.parseInt(lastNum);
            id = idNum + 1;
            if (id < 10) {
                sId = "TSD-00000" + id;
            } else if (id < 100) {
                sId = "TSD-0000" + id;
            } else if (id < 1000) {
                sId = "TSD-000" + id;
            } else if (id < 10000) {
                sId = "TSD-00" + id;
            } else if (id < 100000) {
                sId = "TSD-0" + id;
            } else {
                sId = "TSD-" + id;
            }
        }
        teachingStrategiesData = new TeachingStrategiesData();
        teachingStrategiesData.setId(sId);
        teachingStrategiesData.setDetail(detail);
        teachingStrategiesData.setGeneralData(generalData);
        teachingStrategiesData.setCourse(course);
        TeachingStrategiesData.insert(teachingStrategiesData);
        session("nameCategory", "cat7");
        session("generalDataId", generalData.getId());
        return CreateDoc3.selectCat();
    }

    public static Result saveData7_3 (){
        DynamicForm data7_3 = Form.form().bindFromRequest();
        String detail = data7_3.get("detail");
        generalData = GeneralData.finder.byId(data7_3.get("genId"));
        course = generalData.getCourse();
        improveTeachingDataList = ImproveTeachingData.improveTeachingDataList();
        int numlist = improveTeachingDataList.size();
        int id, idNum;
        String lastNum;
        String sId;
        if (improveTeachingDataList.size() == 0) {
            sId = "ITD-000001";
        } else {
            lastNum = improveTeachingDataList.get(numlist - 1).getId();
            lastNum = lastNum.substring(4);
            idNum = Integer.parseInt(lastNum);
            id = idNum + 1;
            if (id < 10) {
                sId = "ITD-00000" + id;
            } else if (id < 100) {
                sId = "ITD-0000" + id;
            } else if (id < 1000) {
                sId = "ITD-000" + id;
            } else if (id < 10000) {
                sId = "ITD-00" + id;
            } else if (id < 100000) {
                sId = "ITD-0" + id;
            } else {
                sId = "ITD-" + id;
            }
        }
        improveTeachingData = new ImproveTeachingData();
        improveTeachingData.setId(sId);
        improveTeachingData.setDetail(detail);
        improveTeachingData.setGeneralData(generalData);
        improveTeachingData.setCourse(course);
        ImproveTeachingData.insert(improveTeachingData);
        session("nameCategory", "cat7");
        session("generalDataId", generalData.getId());
        return CreateDoc3.selectCat();
    }

    public static  Result saveData7_4 (){
        DynamicForm data7_4 = Form.form().bindFromRequest();
        String detail = data7_4.get("detail");
        generalData = GeneralData.finder.byId(data7_4.get("genId"));
        course = generalData.getCourse();
        standardReviewList = StandardReview.standardReviewList();
        int numlist = standardReviewList.size();
        int id, idNum;
        String lastNum;
        String sId;
        if (standardReviewList.size() == 0) {
            sId = "STD-000001";
        } else {
            lastNum = standardReviewList.get(numlist - 1).getId();
            lastNum = lastNum.substring(4);
            idNum = Integer.parseInt(lastNum);
            id = idNum + 1;
            if (id < 10) {
                sId = "STD-00000" + id;
            } else if (id < 100) {
                sId = "STD-0000" + id;
            } else if (id < 1000) {
                sId = "STD-000" + id;
            } else if (id < 10000) {
                sId = "STD-00" + id;
            } else if (id < 100000) {
                sId = "STD-0" + id;
            } else {
                sId = "STD-" + id;
            }
        }
        standardReview = new StandardReview();
        standardReview.setId(sId);
        standardReview.setDetail(detail);
        standardReview.setGeneralData(generalData);
        standardReview.setCourse(course);
        StandardReview.insert(standardReview);
        session("nameCategory", "cat7");
        session("generalDataId", generalData.getId());
        return CreateDoc3.selectCat();
    }

    public static Result saveData7_5 (){
        DynamicForm data7_5 = Form.form().bindFromRequest();
        String detail = data7_5.get("detail");
        generalData = GeneralData.finder.byId(data7_5.get("genId"));
        course  = generalData.getCourse();
        reviewAndPlanList = ReviewAndPlan.reviewAndPlanList();
        int numlist = reviewAndPlanList.size();
        int id, idNum;
        String lastNum;
        String sId;
        if (reviewAndPlanList.size() == 0) {
            sId = "RAP-000001";
        } else {
            lastNum = reviewAndPlanList.get(numlist - 1).getId();
            lastNum = lastNum.substring(4);
            idNum = Integer.parseInt(lastNum);
            id = idNum + 1;
            if (id < 10) {
                sId = "RAP-00000" + id;
            } else if (id < 100) {
                sId = "RAP-0000" + id;
            } else if (id < 1000) {
                sId = "RAP-000" + id;
            } else if (id < 10000) {
                sId = "RAP-00" + id;
            } else if (id < 100000) {
                sId = "RAP-0" + id;
            } else {
                sId = "RAP-" + id;
            }
        }
        reviewAndPlan = new ReviewAndPlan();
        reviewAndPlan.setId(sId);
        reviewAndPlan.setDetail(detail);
        reviewAndPlan.setGeneralData(generalData);
        reviewAndPlan.setCourse(course);
        ReviewAndPlan.insert(reviewAndPlan);
        session("nameCategory", "cat7");
        session("generalDataId", generalData.getId());
        return CreateDoc3.selectCat();
    }

    public static  Result edit7_1(String id){
        courseAssessmentStrategies = CourseAssessmentStrategies.finder.byId(id);
        generalData = courseAssessmentStrategies.getGeneralData();
        return main(editCategory7_1.render(generalData,courseAssessmentStrategies));
    }

    public static  Result edit7_2(String id){
        teachingStrategiesData = TeachingStrategiesData.finder.byId(id);
        generalData = teachingStrategiesData.getGeneralData();
        return main(editCategory7_2.render(generalData,teachingStrategiesData));
    }

    public static  Result edit7_3(String id){
        improveTeachingData = ImproveTeachingData.finder.byId(id);
        generalData = improveTeachingData.getGeneralData();
        return main(editCategory7_3.render(generalData,improveTeachingData));
    }

    public static  Result edit7_4(String id){
        standardReview = StandardReview.finder.byId(id);
        generalData = standardReview.getGeneralData();
        return main(editCategory7_4.render(generalData,standardReview));
    }

    public static  Result edit7_5(String id){
        reviewAndPlan = ReviewAndPlan.finder.byId(id);
        generalData = reviewAndPlan.getGeneralData();
        return main(editCategory7_5.render(generalData,reviewAndPlan));
    }

    public static Result update7_1(){
        DynamicForm dataUpdate = Form.form().bindFromRequest();
        courseAssessmentStrategies = CourseAssessmentStrategies.finder.byId(dataUpdate.get("id7_1"));
        generalData = courseAssessmentStrategies.getGeneralData();
        courseAssessmentStrategies.setDetail(dataUpdate.get("detail"));
        CourseAssessmentStrategies.update(courseAssessmentStrategies);
        session("nameCategory", "cat7");
        session("generalDataId", generalData.getId());
        return CreateDoc3.selectCat();
    }

    public static Result update7_2 (){
        DynamicForm dataUpdate = Form.form().bindFromRequest();
        teachingStrategiesData = TeachingStrategiesData.finder.byId(dataUpdate.get("id7_2"));
        generalData = teachingStrategiesData.getGeneralData();
        teachingStrategiesData.setDetail(dataUpdate.get("detail"));
        TeachingStrategiesData.update(teachingStrategiesData);
        session("nameCategory", "cat7");
        session("generalDataId", generalData.getId());
        return CreateDoc3.selectCat();

    }

    public static Result update7_3 (){
        DynamicForm dataUpdate = Form.form().bindFromRequest();
        improveTeachingData = ImproveTeachingData.finder.byId(dataUpdate.get("id7_3"));
        generalData = improveTeachingData.getGeneralData();
        improveTeachingData.setDetail(dataUpdate.get("detail"));
        ImproveTeachingData.update(improveTeachingData);
        session("nameCategory", "cat7");
        session("generalDataId", generalData.getId());
        return CreateDoc3.selectCat();
    }

    public static Result update7_4 (){
        DynamicForm dataUpdate = Form.form().bindFromRequest();
        standardReview = StandardReview.finder.byId(dataUpdate.get("id7_4"));
        generalData = standardReview.getGeneralData();
        standardReview.setDetail(dataUpdate.get("detail"));
        StandardReview.update(standardReview);
        session("nameCategory", "cat7");
        session("generalDataId", generalData.getId());
        return CreateDoc3.selectCat();
    }

    public static Result update7_5(){
        DynamicForm dataUpdate = Form.form().bindFromRequest();
        reviewAndPlan = ReviewAndPlan.finder.byId(dataUpdate.get("id7_5"));
        generalData = reviewAndPlan.getGeneralData();
        reviewAndPlan.setDetail(dataUpdate.get("detail"));
        ReviewAndPlan.update(reviewAndPlan);
        session("nameCategory", "cat7");
        session("generalDataId", generalData.getId());
        return CreateDoc3.selectCat();
    }

    public static Result delete7_1 (String id){
        courseAssessmentStrategies = CourseAssessmentStrategies.finder.byId(id);
        generalData = courseAssessmentStrategies.getGeneralData();
        if (courseAssessmentStrategies != null){
            CourseAssessmentStrategies.delete(courseAssessmentStrategies);
        }
        session("nameCategory","cat7");
        session("generalDataId", generalData.getId());
        return CreateDoc3.selectCat();
    }

    public static Result delete7_2 (String id){
        teachingStrategiesData = TeachingStrategiesData.finder.byId(id);
        generalData = teachingStrategiesData.getGeneralData();
        if (teachingStrategiesData != null){
            TeachingStrategiesData.delete(teachingStrategiesData);
        }
        session("nameCategory","cat7");
        session("generalDataId", generalData.getId());
        return CreateDoc3.selectCat();
    }

    public static Result delete7_3 (String id) {
        improveTeachingData = ImproveTeachingData.finder.byId(id);
        generalData  = improveTeachingData.getGeneralData();
        if (improveTeachingData!= null){
            ImproveTeachingData.delete(improveTeachingData);
        }
        session("nameCategory","cat7");
        session("generalDataId", generalData.getId());
        return CreateDoc3.selectCat();
    }

    public static Result delete7_4 (String id){
        standardReview = StandardReview.finder.byId(id);
        generalData = standardReview.getGeneralData();
        if (standardReview != null){
            StandardReview.delete(standardReview);
        }
        session("nameCategory","cat7");
        session("generalDataId", generalData.getId());
        return CreateDoc3.selectCat();
    }

    public static Result delete7_5 (String id){
        reviewAndPlan = ReviewAndPlan.finder.byId(id);
        generalData = reviewAndPlan.getGeneralData();
        if (reviewAndPlan !=null){
            ReviewAndPlan.delete(reviewAndPlan);
        }
        session("nameCategory","cat7");
        session("generalDataId", generalData.getId());
        return CreateDoc3.selectCat();
    }

}
