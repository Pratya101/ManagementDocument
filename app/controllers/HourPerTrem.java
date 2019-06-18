package controllers;

/**
 * Created by coffee on 5/9/2019.
 */

import models.DataHours;
import models.GeneralData;
import play.api.templates.Html;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.main;

import java.util.ArrayList;
import java.util.List;

public class HourPerTrem extends Controller {

    public static GeneralData generalData;
    public static DataHours dataHours;
    public static List<DataHours> dataHoursList = new ArrayList<DataHours>();
    public static Result main(Html Content) {
        return ok(main.render(Content));
    }

    public static Result addHours() {
        DynamicForm dataHourAmount = Form.form().bindFromRequest();
        generalData = GeneralData.finder.byId(dataHourAmount.get("genId"));
        String  hDescribe =  dataHourAmount.get("describe");
        String hSupplementary = dataHourAmount.get("supplementary");
        String hWork =  dataHourAmount.get("work");
        String hStudy = dataHourAmount.get("study");
        dataHoursList = DataHours.dataHoursList();
        int numlist = dataHoursList.size();
        int id, idNum;
        String lastNum;
        String sId;
        if (dataHoursList.size() == 0) {
            sId = "Hr-000001";
        } else {
            lastNum = dataHoursList.get(numlist - 1).getId();
            lastNum = lastNum.substring(3);
            idNum = Integer.parseInt(lastNum);
            id = idNum + 1;
            if (id < 10) {
                sId = "Hr-00000" + id;
            } else if (id < 100) {
                sId = "Hr-0000" + id;
            } else if (id < 1000) {
                sId = "Hr-000" + id;
            } else if (id < 10000) {
                sId = "Hr-00" + id;
            } else if (id < 100000) {
                sId = "Hr-0" + id;
            } else {
                sId = "Hr-" + id;
            }
        }
        dataHours = new DataHours();
        dataHours.setId(sId);
        dataHours.sethDescribe(hDescribe);
        dataHours.sethSupplementary(hSupplementary);
        dataHours.sethWork(hWork);
        dataHours.sethStudy(hStudy);
        dataHours.setGeneralData(generalData);
        DataHours.insert(dataHours);
        session("nameCategory", "cat3");
        session("generalDataId", generalData.getId());
        return  CreateDoc3.selectCat();
    }

    public static Result addsuggestion() {
        DynamicForm dataSuggestion = Form.form().bindFromRequest();
        generalData = GeneralData.finder.byId(dataSuggestion.get("genId"));
        String detailSugges = dataSuggestion.get("suggestion");
        generalData.setSuggestion(detailSugges);
        GeneralData.update(generalData);
        session("nameCategory", "cat3");
        session("generalDataId", generalData.getId());
        return CreateDoc3.selectCat();
    }

    public static Result deleteHour (String id){
        dataHours = DataHours.finder.byId(id);
        generalData = dataHours.getGeneralData();
        if (dataHours!= null){
            DataHours.delete(dataHours);
        }
        session("nameCategory", "cat3");
        session("generalDataId", generalData.getId());
        return CreateDoc3.selectCat();

    }

    public static Result deleteHoursSession(){
        session().remove("hDescribe");
        session().remove("hSupplementary");
        session().remove("hWork");
        session().remove("hStudy");
        session().remove("suggestion");
        session("nameCategory", "cat3");
        return CreateDoc3.selectCat();
    }

    public static Result editHour(){
        DynamicForm dataHour = Form.form().bindFromRequest();
        dataHours = DataHours.finder.byId(dataHour.get("hourId"));
        generalData =dataHours.getGeneralData();
        dataHours.sethDescribe(dataHour.get("describe"));
        dataHours.sethSupplementary(dataHour.get("supplementary"));
        dataHours.sethWork(dataHour.get("work"));
        dataHours.sethStudy(dataHour.get("study"));
        DataHours.update(dataHours);
        session("nameCategory", "cat3");
        session("generalDataId", generalData.getId());
        return CreateDoc3.selectCat();
    }

    public static Result editSuggestion (){
        DynamicForm dataupdate = Form.form().bindFromRequest();
        generalData = GeneralData.finder.byId(dataupdate.get("genId"));
        generalData.setSuggestion(dataupdate.get("suggestion"));
        GeneralData.update(generalData);
        session("nameCategory", "cat3");
        session("generalDataId", generalData.getId());
        return CreateDoc3.selectCat();
    }
    public static Result deleteSuggestion(String id){
        generalData = GeneralData.finder.byId(id);
        if (generalData != null){
            generalData.setSuggestion(null);
            GeneralData.update(generalData);
        }
        session("nameCategory", "cat3");
        session("generalDataId", generalData.getId());
        return CreateDoc3.selectCat();
    }



}
