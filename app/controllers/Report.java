package controllers;

import com.avaje.ebean.Expr;
import play.cache.Cache;
import play.data.Form;
import play.mvc.Controller;
import models.*;
import play.api.templates.Html;
import play.data.DynamicForm;
import play.mvc.Result;
import views.html.*;
import java.util.ArrayList;
import java.util.List;

public class Report extends Controller {
    public static List<GeneralData> generalDataList = new ArrayList<GeneralData>();
    public static GeneralData generalData;
    public static List<Instructors> instructorsList = new ArrayList<Instructors>();
    public static Result main(Html Content) {
        return ok(main.render(Content));
    }
    public static Result conditionData (){
        session("numMenu","conditionData");
        DynamicForm data = Form.form().bindFromRequest();
        String term  = data.get("term");
        String year = data.get("year");
        List<GeneralData> generalDataList1 = GeneralData.finder.where().and(Expr.eq("term",term),Expr.eq("year",year)).findList();
        generalDataList = GeneralData.finder.where().and(Expr.eq("term",term),Expr.eq("year",year)).eq("teacher.id",session("userId")) .findList();
        List<BasketDoc> basketDocList = new ArrayList<BasketDoc>();
        return main(report.render(generalDataList,instructorsList,basketDocList,"",generalDataList1));
    }

    public static Result dataReport (){
        DynamicForm data = Form.form().bindFromRequest();
        String term  = data.get("term");
        String year = data.get("year");
        List<GeneralData> generalDataList1 = GeneralData.finder.where().and(Expr.eq("term",term),Expr.eq("year",year)).findList();
        generalDataList = GeneralData.finder.where().and(Expr.eq("term",term),Expr.eq("year",year)).eq("teacher.id",session("userId")) .findList();
        List<BasketDoc> basketDocList = new ArrayList<BasketDoc>();
        instructorsList= Instructors.finder.where().and(Expr.eq("term",term),Expr.eq("year",year)).and(Expr.eq("teacher.id",session("userId")),Expr.eq("status","1")).eq("statusDoc",null).findList();
        return main(report.render(generalDataList,instructorsList,basketDocList,"",generalDataList1));
    }

    public static Result DocOK(){
        DynamicForm data  = Form.form().bindFromRequest();
        String doc = data.get("doc");
        String year = data.get("year");
        String term = data.get("term");
        List<BasketDoc> basketDocList = new ArrayList<BasketDoc>();
        List<GeneralData> generalDataList1 = new ArrayList<GeneralData>();
            generalDataList = GeneralData.finder.where().and(Expr.eq("term",term),Expr.eq("year",year)) .findList();
        if (doc.equals("2")){
                Cache.remove("basketDocList");
            int y=1;
            for (int i=0;i<generalDataList.size();i++){
                if (generalDataList.get(i).getCourseDestinationList().size() == 0 || generalDataList.get(i).getCourseObjectivesList().size() == 0 ||
                        generalDataList.get(i).getDataHoursList().size() == 0 || generalDataList.get(i).getSuggestion() == null || generalDataList.get(i).getData_listDevelopmentList().size() == 0
                        || generalDataList.get(i).getData_methodTeachingList().size() == 0 || generalDataList.get(i).getData_methodEvaluationList().size() == 0 || generalDataList.get(i).getPlanTeachingDataList().size() == 0 ||
                        generalDataList.get(i).getEvaluationPlanDataList().size() == 0 || generalDataList.get(i).getMainDocumentList().size() == 0 ||
                        generalDataList.get(i).getImportantDocumentList().size() == 0 || generalDataList.get(i).getSuggustionDocumentList().size() == 0 ||
                        generalDataList.get(i).getCourseAssessmentStrategiesList().size() == 0 || generalDataList.get(i).getTeachingStrategiesDataList().size() == 0 ||
                        generalDataList.get(i).getImproveTeachingDataList().size() == 0 || generalDataList.get(i).getStandardReviews().size() == 0 ||
                        generalDataList.get(i).getReviewAndPlanList().size() == 0){
                    if(Cache.get("basketDocList")!=null){
                        basketDocList = (List<BasketDoc>) Cache.get("basketDocList");
                        basketDocList.add(new BasketDoc(Integer.toString(y),generalDataList.get(i).getCourse().getNameTH(),"กำลังดำเนินการ...",generalDataList.get(i).getTeacher().getName() + " " +generalDataList.get(i).getTeacher().getSername(),generalDataList.get(i).getTerm(),generalDataList.get(i).getYear()));
                    }else{
                        basketDocList.add(new BasketDoc(Integer.toString(y),generalDataList.get(i).getCourse().getNameTH(),"กำลังดำเนินการ...",generalDataList.get(i).getTeacher().getName() + " " +generalDataList.get(i).getTeacher().getSername(),generalDataList.get(i).getTerm(),generalDataList.get(i).getYear()));
                    }
                    Cache.set("basketDocList",basketDocList);
                }
                y=y+1;
            }
        }else if (doc.equals("1")){
            Cache.remove("basketDocList");
            int y=1;
            for (int i=0;i<generalDataList.size();i++){
                if (generalDataList.get(i).getCourseDestinationList().size() != 0 && generalDataList.get(i).getCourseObjectivesList().size() != 0 &&
                        generalDataList.get(i).getDataHoursList().size() != 0 && generalDataList.get(i).getSuggestion() != null && generalDataList.get(i).getData_listDevelopmentList().size() != 0
                        && generalDataList.get(i).getData_methodTeachingList().size() != 0 && generalDataList.get(i).getData_methodEvaluationList().size() != 0 && generalDataList.get(i).getPlanTeachingDataList().size() != 0 &&
                        generalDataList.get(i).getEvaluationPlanDataList().size() != 0 && generalDataList.get(i).getMainDocumentList().size() != 0 &&
                        generalDataList.get(i).getImportantDocumentList().size() != 0 && generalDataList.get(i).getSuggustionDocumentList().size() != 0 &&
                        generalDataList.get(i).getCourseAssessmentStrategiesList().size() != 0 && generalDataList.get(i).getTeachingStrategiesDataList().size() != 0 &&
                        generalDataList.get(i).getImproveTeachingDataList().size() != 0 && generalDataList.get(i).getStandardReviews().size() != 0 &&
                        generalDataList.get(i).getReviewAndPlanList().size() != 0){
                    if(Cache.get("basketDocList")!=null){
                        basketDocList = (List<BasketDoc>) Cache.get("basketDocList");
                        basketDocList.add(new BasketDoc(Integer.toString(y),generalDataList.get(i).getCourse().getNameTH(),"เรียบร้อย...",generalDataList.get(i).getTeacher().getName() + " " +generalDataList.get(i).getTeacher().getSername(),generalDataList.get(i).getTerm(),generalDataList.get(i).getYear()));
                    }else{
                        basketDocList.add(new BasketDoc(Integer.toString(y),generalDataList.get(i).getCourse().getNameTH(),"เรียบร้อย...",generalDataList.get(i).getTeacher().getName() + " " +generalDataList.get(i).getTeacher().getSername(),generalDataList.get(i).getTerm(),generalDataList.get(i).getYear()));
                    }
                    Cache.set("basketDocList",basketDocList);
                }
                y=y+1;
            }

        }else if (doc.equals("3")){
            instructorsList = Instructors.finder.where().and(Expr.eq("term",term),Expr.eq("year",year)).eq("statusDoc",null).findList();
        }else if (doc.equals("0")){
            generalDataList1 = GeneralData.finder.where().and(Expr.eq("term",term),Expr.eq("year",year)).findList();
            instructorsList = Instructors.finder.where().and(Expr.eq("term",term),Expr.eq("year",year)).eq("statusDoc",null).findList();
        }
        return main(report.render(generalDataList,instructorsList,basketDocList,doc,generalDataList1));
    }
}
