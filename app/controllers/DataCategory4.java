package controllers;

import com.avaje.ebean.Expr;
import play.data.DynamicForm;
import play.mvc.Controller;
import models.*;
import play.api.templates.Html;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
/**
 * Created by coffee on 5/21/2019.
 */
public class DataCategory4 extends Controller {
    public static GeneralData generalData;
    public static Curriculum curriculum;
    public static TeachingStrategies teachingStrategies;
    public static EvaluationStrategy evaluationStrategy;
    public static DevelopmentalLearning developmentalLearning;
    public static Data_ListDevelopment data_listDevelopment;
    public static List<Data_ListDevelopment> data_listDevelopmentList = new ArrayList<Data_ListDevelopment>();
    public static Data_MethodTeaching data_methodTeaching;
    public static List<Data_MethodTeaching> data_methodTeachingList = new ArrayList<Data_MethodTeaching>();
    public static Data_MethodEvaluation data_methodEvaluation;
    public static List<Data_MethodEvaluation> data_methodEvaluationList = new ArrayList<Data_MethodEvaluation>();

    public static Result main(Html Content) {
        return ok(main.render(Content));
    }

    public static Result insertDevListData() {
        DynamicForm dataDev = Form.form().bindFromRequest();
        generalData = GeneralData.finder.byId(dataDev.get("genId"));
        String domainId = dataDev.get("domainId");
        Map<String, String[]> map = request().body().asFormUrlEncoded();
        String[] valI = map.get("listDev");
        int i = 0;
        for (i = 0; i < valI.length; i++) {
            String value = valI[i];
            data_listDevelopmentList = Data_ListDevelopment.data_listDevelopmentList();
            int numlist = data_listDevelopmentList.size();
            int id, idNum;
            String lastNum;
            String sId;
            if (data_listDevelopmentList.size() == 0) {
                sId = "Dev-000001";
            } else {
                lastNum = data_listDevelopmentList.get(numlist - 1).getId();
                lastNum = lastNum.substring(4);
                idNum = Integer.parseInt(lastNum);
                id = idNum + 1;
                if (id < 10) {
                    sId = "Dev-00000" + id;
                } else if (id < 100) {
                    sId = "Dev-0000" + id;
                } else if (id < 1000) {
                    sId = "Dev-000" + id;
                } else if (id < 10000) {
                    sId = "Dev-00" + id;
                } else if (id < 100000) {
                    sId = "Dev-0" + id;
                } else {
                    sId = "Dev-" + id;
                }
            }
            curriculum = Curriculum.finder.byId(value);
            developmentalLearning = curriculum.getDevelopmentalLearning();
            Data_ListDevelopment newDataDev = new Data_ListDevelopment(sId,developmentalLearning.getNumDomain(), curriculum, generalData, developmentalLearning);
            Data_ListDevelopment.insert(newDataDev);
        }
        session("domainId", domainId);
        session("nameCategory", "cat4");
        session("generalDataId", generalData.getId());
        return CreateDoc3.selectCat();
    }

    public static Result insertTeachingData() {
        DynamicForm dataDev = Form.form().bindFromRequest();
        generalData = GeneralData.finder.byId(dataDev.get("genId"));
        String domainId = dataDev.get("domainId");
        Map<String, String[]> map = request().body().asFormUrlEncoded();
        String[] valI = map.get("teachId");
        int i = 0;
        for (i = 0; i < valI.length; i++) {
            String value = valI[i];
            data_methodTeachingList = Data_MethodTeaching.data_methodTeachingList();
            int numlist = data_methodTeachingList.size();
            int id, idNum;
            String lastNum;
            String sId;
            if (data_methodTeachingList.size() == 0) {
                sId = "DMT-000001";
            } else {
                lastNum = data_methodTeachingList.get(numlist - 1).getId();
                lastNum = lastNum.substring(4);
                idNum = Integer.parseInt(lastNum);
                id = idNum + 1;
                if (id < 10) {
                    sId = "DMT-00000" + id;
                } else if (id < 100) {
                    sId = "DMT-0000" + id;
                } else if (id < 1000) {
                    sId = "DMT-000" + id;
                } else if (id < 10000) {
                    sId = "DMT-00" + id;
                } else if (id < 100000) {
                    sId = "DMT-0" + id;
                } else {
                    sId = "DMT-" + id;
                }
            }
            teachingStrategies = TeachingStrategies.finder.byId(value);
            developmentalLearning = teachingStrategies.getDevelopmentalLearning();
            Data_MethodTeaching newDataTeach = new Data_MethodTeaching(sId,developmentalLearning.getNumDomain(), teachingStrategies, generalData, developmentalLearning);
            Data_MethodTeaching.insert(newDataTeach);
        }
        session("domainId", domainId);
        session("nameCategory", "cat4");
        session("generalDataId", generalData.getId());
        return CreateDoc3.selectCat();
    }

    public static Result insertDataEvaluation() {
        DynamicForm dataDev = Form.form().bindFromRequest();
        generalData = GeneralData.finder.byId(dataDev.get("genId"));
        String domainId = dataDev.get("domainId");
        String n = dataDev.get("n");
        Map<String, String[]> map = request().body().asFormUrlEncoded();
        String[] valI = map.get("dataEvaluation");
        int i = 0;
        for (i = 0; i < valI.length; i++) {
            String value = valI[i];
            data_methodEvaluationList = Data_MethodEvaluation.data_methodEvaluationList();
            int numlist = data_methodEvaluationList.size();
            int id, idNum;
            String lastNum;
            String sId;
            if (data_methodEvaluationList.size() == 0) {
                sId = "DME-000001";
            } else {
                lastNum = data_methodEvaluationList.get(numlist - 1).getId();
                lastNum = lastNum.substring(4);
                idNum = Integer.parseInt(lastNum);
                id = idNum + 1;
                if (id < 10) {
                    sId = "DME-00000" + id;
                } else if (id < 100) {
                    sId = "DME-0000" + id;
                } else if (id < 1000) {
                    sId = "DME-000" + id;
                } else if (id < 10000) {
                    sId = "DME-00" + id;
                } else if (id < 100000) {
                    sId = "DME-0" + id;
                } else {
                    sId = "DME-" + id;
                }
            }
            evaluationStrategy = EvaluationStrategy.finder.byId(value);
            developmentalLearning = evaluationStrategy.getDevelopmentalLearning();
            Data_MethodEvaluation newDataEvalution = new Data_MethodEvaluation(sId,developmentalLearning.getNumDomain(), evaluationStrategy, generalData, developmentalLearning);
            Data_MethodEvaluation.insert(newDataEvalution);
        }
        session("domainId", domainId);
        session("nameCategory", "cat4");
        session("generalDataId", generalData.getId());
        return CreateDoc3.selectCat();
    }

    public static Result deleteCat4_1() {
        DynamicForm data = Form.form().bindFromRequest();
        data_listDevelopmentList = Data_ListDevelopment.finder.where().and(Expr.eq("developmentalLearning.id", data.get("idDomain")),
                Expr.eq("generalData.id", data.get("genId"))).findList();
        if (data_listDevelopmentList != null) {
            for (int i = 0; i < data_listDevelopmentList.size(); i++) {
                data_listDevelopment = Data_ListDevelopment.finder.byId(data_listDevelopmentList.get(i).getId());
                if (data_listDevelopment != null) {
                    Data_ListDevelopment.delte(data_listDevelopment);
                }
            }
            session("domainId", data.get("idDomain"));
            session("nameCategory", "cat4");
            session("generalDataId", data.get("genId"));
            return CreateDoc3.selectCat();
        } else {
            flash("์Nodata", "ไม่มีข้อมูล ไม่สามารถลบได้");
            session("domainId", data.get("idDomain"));
            session("nameCategory", "cat4");
            session("generalDataId", data.get("genId"));
            return CreateDoc3.selectCat();

        }
    }
    public static Result deleteCat4_2() {
        DynamicForm data = Form.form().bindFromRequest();
       data_methodTeachingList = Data_MethodTeaching.finder.where().and(Expr.eq("developmentalLearning.id", data.get("idDomain")),
                Expr.eq("generalData.id", data.get("genId"))).findList();
        if (data_methodTeachingList != null) {
            for (int i = 0; i < data_methodTeachingList.size(); i++) {
                data_methodTeaching = Data_MethodTeaching.finder.byId(data_methodTeachingList.get(i).getId());
                if (data_methodTeaching != null) {
                    Data_MethodTeaching.delete(data_methodTeaching);
                }
            }
            session("domainId", data.get("idDomain"));
            session("nameCategory", "cat4");
            session("generalDataId", data.get("genId"));
            return CreateDoc3.selectCat();
        } else {
            flash("์Nodata", "ไม่มีข้อมูล ไม่สามารถลบได้");
            session("domainId", data.get("idDomain"));
            session("nameCategory", "cat4");
            session("generalDataId", data.get("genId"));
            return CreateDoc3.selectCat();

        }
    }

    public static Result deleteCat4_3() {
        DynamicForm data = Form.form().bindFromRequest();
        data_methodEvaluationList = Data_MethodEvaluation.finder.where().and(Expr.eq("developmentalLearning.id", data.get("idDomain")),
                Expr.eq("generalData.id", data.get("genId"))).findList();
        if (data_methodEvaluationList != null) {
            for (int i = 0; i < data_methodEvaluationList.size(); i++) {
                data_methodEvaluation = Data_MethodEvaluation.finder.byId(data_methodEvaluationList.get(i).getId());
                if (data_methodEvaluation != null) {
                    Data_MethodEvaluation.delete(data_methodEvaluation);
                }
            }
            session("domainId", data.get("idDomain"));
            session("nameCategory", "cat4");
            session("generalDataId", data.get("genId"));
            return CreateDoc3.selectCat();
        } else {
            flash("Nodata", "ไม่มีข้อมูล ไม่สามารถลบได้");
            session("domainId", data.get("idDomain"));
            session("nameCategory", "cat4");
            session("generalDataId", data.get("genId"));
            return CreateDoc3.selectCat();

        }
    }

}
