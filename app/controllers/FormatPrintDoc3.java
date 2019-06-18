package controllers;


import com.avaje.ebean.Expr;
import models.*;
import play.api.templates.Html;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.formatPrint.*;
import views.html.*;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;


public class FormatPrintDoc3 extends Controller {

    public static GeneralData generalData;
    public static List<Data_ListDevelopment> data_listDevelopmentList =  new ArrayList<Data_ListDevelopment>();
    public static List<Data_MethodTeaching> data_methodTeachingList = new ArrayList<Data_MethodTeaching>();
    public static List<Data_MethodEvaluation> data_methodEvaluationList =new ArrayList<Data_MethodEvaluation>();
    public static Result main(Html Content) {
        return ok(main.render(Content));
    }

    public static Result mainDoc3 (String id){
        generalData = GeneralData.finder.byId(id);
        return main(mainDocPage.render(generalData));

    }

    public static Result formatCat1 (String id){
        generalData = GeneralData.finder.byId(id);
        return main(formatPrintCat1.render(generalData));

    }

    public static Result formatCat2 (String id){
        generalData = GeneralData.finder.byId(id);
        return main(formatPrintCat2.render(generalData));
    }

    public static Result formatCat3 (String id){
        generalData = GeneralData.finder.byId(id);
        return main(formatPrintCat3.render(generalData));
    }

    public static Result formatCat4 (){
        DynamicForm data4 = Form.form().bindFromRequest();
        String domainId = data4.get("idDomain");
        generalData = GeneralData.finder.byId(data4.get("genId"));
        data_listDevelopmentList = Data_ListDevelopment.finder.where().and(Expr.eq("generalData.id", generalData.getId()),Expr.eq("developmentalLearning.id",domainId)).findList();
        data_methodTeachingList = Data_MethodTeaching.finder.where().and(Expr.eq("generalData.id", generalData.getId()),Expr.eq("developmentalLearning.id",domainId)).findList();
        data_methodEvaluationList = Data_MethodEvaluation.finder.where().and(Expr.eq("generalData.id", generalData.getId()),Expr.eq("developmentalLearning.id",domainId)).findList();
        String idCatDomain="0";
        String status;
        if (data_listDevelopmentList.size()!=0){
            idCatDomain=data_listDevelopmentList.get(0).getDevelopmentalLearning().getId();
        }
        if (idCatDomain == domainId){
            status="1";
        }else {
            status ="0";
        }
        String numDoamin="";
        List<DevelopmentalLearning> allDomain =  new ArrayList<DevelopmentalLearning>();
        allDomain = DevelopmentalLearning.developmentalLearningList();
        DevelopmentalLearning findIndex;
        for (int i= 0 ; i<allDomain.size();i++){
            findIndex= DevelopmentalLearning.finder.byId(domainId);
            if (findIndex.getNumDomain().equals(allDomain.get(i).getNumDomain())){
                numDoamin = allDomain.get(i).getNumDomain();
                break;
            }
        }
        return main(formatPrintCat4.render(generalData,data_listDevelopmentList,data_methodTeachingList,data_methodEvaluationList,status,numDoamin));
    }
    
    public static Result formatCat5_1 (String id){
        generalData = GeneralData.finder.byId(id);
        return main(formatPrintCat5_1.render(generalData));
    }

    public static Result formatCat5_2 (String id){
        generalData = GeneralData.finder.byId(id);
        return main(formatPrintCat5_2.render(generalData));
    }

    public static Result formatCat6 (String id){
        generalData = GeneralData.finder.byId(id);
        return main(formatPrintCat6.render(generalData));
    }
    public static Result selectPrint(String id){
        generalData =GeneralData.finder.byId(id);
        return main(selectPrint.render(generalData));
    }
    public static Result formatCat7 (String id){
        generalData = GeneralData.finder.byId(id);
        return main(formatPrintCat7.render(generalData));
    }

    public static Result printAll (String id){
        generalData =GeneralData.finder.byId(id);
        data_listDevelopmentList = Data_ListDevelopment.data_listDevelopmentList();

        List<Data_ListDevelopment> datamain1_1 = Data_ListDevelopment.finder.where().and(Expr.eq("generalData.id",id),Expr.eq("numDomain","1")).findList();
        List<Data_ListDevelopment> datamain1_2 = Data_ListDevelopment.finder.where().and(Expr.eq("generalData.id",id),Expr.eq("numDomain","2")).findList();
        List<Data_ListDevelopment> datamain1_3 = Data_ListDevelopment.finder.where().and(Expr.eq("generalData.id",id),Expr.eq("numDomain","3")).findList();
        List<Data_ListDevelopment> datamain1_4 = Data_ListDevelopment.finder.where().and(Expr.eq("generalData.id",id),Expr.eq("numDomain","4")).findList();
        List<Data_ListDevelopment> datamain1_5 = Data_ListDevelopment.finder.where().and(Expr.eq("generalData.id",id),Expr.eq("numDomain","5")).findList();
        List< Data_MethodTeaching> teachmain1_1 =  Data_MethodTeaching.finder.where().and(Expr.eq("generalData.id",id),Expr.eq("numDomain","1")).findList();
        List< Data_MethodTeaching> teachmain1_2 =  Data_MethodTeaching.finder.where().and(Expr.eq("generalData.id",id),Expr.eq("numDomain","2")).findList();
        List< Data_MethodTeaching> teachmain1_3 =  Data_MethodTeaching.finder.where().and(Expr.eq("generalData.id",id),Expr.eq("numDomain","3")).findList();
        List< Data_MethodTeaching> teachmain1_4 =  Data_MethodTeaching.finder.where().and(Expr.eq("generalData.id",id),Expr.eq("numDomain","4")).findList();
        List< Data_MethodTeaching> teachmain1_5 =  Data_MethodTeaching.finder.where().and(Expr.eq("generalData.id",id),Expr.eq("numDomain","5")).findList();
        List< Data_MethodEvaluation> evaluation1_1 =  Data_MethodEvaluation.finder.where().and(Expr.eq("generalData.id",id),Expr.eq("numDomain","1")).findList();
        List< Data_MethodEvaluation> evaluation1_2 =  Data_MethodEvaluation.finder.where().and(Expr.eq("generalData.id",id),Expr.eq("numDomain","2")).findList();
        List< Data_MethodEvaluation> evaluation1_3 =  Data_MethodEvaluation.finder.where().and(Expr.eq("generalData.id",id),Expr.eq("numDomain","3")).findList();
        List< Data_MethodEvaluation> evaluation1_4 =  Data_MethodEvaluation.finder.where().and(Expr.eq("generalData.id",id),Expr.eq("numDomain","4")).findList();
        List< Data_MethodEvaluation> evaluation1_5 =  Data_MethodEvaluation.finder.where().and(Expr.eq("generalData.id",id),Expr.eq("numDomain","5")).findList();
        
        return main(formatPrintAll.render(generalData,datamain1_1,datamain1_2,datamain1_3,
                datamain1_4,datamain1_5,teachmain1_1,teachmain1_2,teachmain1_3,teachmain1_4,teachmain1_5,
                evaluation1_1,evaluation1_2,evaluation1_3,evaluation1_4,evaluation1_5));
    }

    public static Result printAllCat4(String id){
        generalData =GeneralData.finder.byId(id);
        List<Data_ListDevelopment> datamain1_1 = Data_ListDevelopment.finder.where().and(Expr.eq("generalData.id",id),Expr.eq("numDomain","1")).findList();
        List<Data_ListDevelopment> datamain1_2 = Data_ListDevelopment.finder.where().and(Expr.eq("generalData.id",id),Expr.eq("numDomain","2")).findList();
        List<Data_ListDevelopment> datamain1_3 = Data_ListDevelopment.finder.where().and(Expr.eq("generalData.id",id),Expr.eq("numDomain","3")).findList();
        List<Data_ListDevelopment> datamain1_4 = Data_ListDevelopment.finder.where().and(Expr.eq("generalData.id",id),Expr.eq("numDomain","4")).findList();
        List<Data_ListDevelopment> datamain1_5 = Data_ListDevelopment.finder.where().and(Expr.eq("generalData.id",id),Expr.eq("numDomain","5")).findList();
        List< Data_MethodTeaching> teachmain1_1 =  Data_MethodTeaching.finder.where().and(Expr.eq("generalData.id",id),Expr.eq("numDomain","1")).findList();
        List< Data_MethodTeaching> teachmain1_2 =  Data_MethodTeaching.finder.where().and(Expr.eq("generalData.id",id),Expr.eq("numDomain","2")).findList();
        List< Data_MethodTeaching> teachmain1_3 =  Data_MethodTeaching.finder.where().and(Expr.eq("generalData.id",id),Expr.eq("numDomain","3")).findList();
        List< Data_MethodTeaching> teachmain1_4 =  Data_MethodTeaching.finder.where().and(Expr.eq("generalData.id",id),Expr.eq("numDomain","4")).findList();
        List< Data_MethodTeaching> teachmain1_5 =  Data_MethodTeaching.finder.where().and(Expr.eq("generalData.id",id),Expr.eq("numDomain","5")).findList();
        List< Data_MethodEvaluation> evaluation1_1 =  Data_MethodEvaluation.finder.where().and(Expr.eq("generalData.id",id),Expr.eq("numDomain","1")).findList();
        List< Data_MethodEvaluation> evaluation1_2 =  Data_MethodEvaluation.finder.where().and(Expr.eq("generalData.id",id),Expr.eq("numDomain","2")).findList();
        List< Data_MethodEvaluation> evaluation1_3 =  Data_MethodEvaluation.finder.where().and(Expr.eq("generalData.id",id),Expr.eq("numDomain","3")).findList();
        List< Data_MethodEvaluation> evaluation1_4 =  Data_MethodEvaluation.finder.where().and(Expr.eq("generalData.id",id),Expr.eq("numDomain","4")).findList();
        List< Data_MethodEvaluation> evaluation1_5 =  Data_MethodEvaluation.finder.where().and(Expr.eq("generalData.id",id),Expr.eq("numDomain","5")).findList();

        return main(printAllcat4.render(generalData,datamain1_1,datamain1_2,datamain1_3,
                datamain1_4,datamain1_5,teachmain1_1,teachmain1_2,teachmain1_3,teachmain1_4,teachmain1_5,
                evaluation1_1,evaluation1_2,evaluation1_3,evaluation1_4,evaluation1_5));
    }

    public static Result printAllCat5(String id){
        generalData = GeneralData.finder.byId(id);
        return main(printAllCat5.render(generalData));
    }
}
