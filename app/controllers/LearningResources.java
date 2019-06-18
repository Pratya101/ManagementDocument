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


public class LearningResources extends Controller {

    public static MainDocument mainDocument;
    public static List<MainDocument> mainDocumentList = new ArrayList<MainDocument>();
    public static ImportantDocument importantDocument;
    public static List<ImportantDocument> importantDocumentList = new ArrayList<ImportantDocument>();
    public static SuggustionDocument suggustionDocument;
    public static  List<SuggustionDocument> suggustionDocumentList = new ArrayList<SuggustionDocument>();
    public static GeneralData generalData;
    public static Course course;
    public static Result main(Html Content) {
        return ok(main.render(Content));
    }

    public static Result addMainDocument (){
        DynamicForm dataDocument = Form.form().bindFromRequest();
        generalData = GeneralData.finder.byId(dataDocument.get("genId"));
        course = generalData.getCourse();
        String datamainDocument = dataDocument.get("mainDocument");
        mainDocumentList = MainDocument.mainDocumentList();
        int numlist = mainDocumentList.size();
        int id, idNum;
        String lastNum;
        String sId;
        if (mainDocumentList.size() == 0) {
            sId = "MDoc-000001";
        } else {
            lastNum = mainDocumentList.get(numlist - 1).getId();
            lastNum = lastNum.substring(5);
            idNum = Integer.parseInt(lastNum);
            id = idNum + 1;
            if (id < 10) {
                sId = "MDoc-00000" + id;
            } else if (id < 100) {
                sId = "MDoc-0000" + id;
            } else if (id < 1000) {
                sId = "MDoc-000" + id;
            } else if (id < 10000) {
                sId = "MDoc-00" + id;
            } else if (id < 100000) {
                sId = "MDoc-0" + id;
            } else {
                sId = "MDoc-" + id;
            }
        }
        mainDocument = new MainDocument();
        mainDocument.setId(sId);
        mainDocument.setDetail(datamainDocument);
        mainDocument.setGeneralData(generalData);
        mainDocument.setCourse(course);
        MainDocument.insert(mainDocument);
//
// List<BasketMainDoc>basketMainDocs= new ArrayList<BasketMainDoc>();
//        if (Cache.get("basketMainDocs") != null) {
//            basketMainDocs.addAll((List<BasketMainDoc>) Cache.get("basketMainDocs"));
//            basketMainDocs.add(new BasketMainDoc("",datamainDocument,generalData,course));
//        }else{
//            basketMainDocs.add(new BasketMainDoc("",datamainDocument,generalData,course));
//        }
//          Cache.set("basketMainDocs", basketMainDocs);
        session("nameCategory", "cat6");
        session("generalDataId", generalData.getId());
        return CreateDoc3.selectCat();
    }

    public static Result addImportant (){
        DynamicForm dataImportanrDoc = Form.form().bindFromRequest();
        String detailImportant = dataImportanrDoc.get("detailImportant");
        generalData = GeneralData.finder.byId(dataImportanrDoc.get("genId"));
        course = generalData.getCourse();
        importantDocumentList = ImportantDocument.importantDocumentList();
        int numlist = importantDocumentList.size();
        int id, idNum;
        String lastNum;
        String sId;
        if (importantDocumentList.size() == 0) {
            sId = "IDoc-000001";
        } else {
            lastNum = importantDocumentList.get(numlist - 1).getId();
            lastNum = lastNum.substring(5);
            idNum = Integer.parseInt(lastNum);
            id = idNum + 1;
            if (id < 10) {
                sId = "IDoc-00000" + id;
            } else if (id < 100) {
                sId = "IDoc-0000" + id;
            } else if (id < 1000) {
                sId = "IDoc-000" + id;
            } else if (id < 10000) {
                sId = "IDoc-00" + id;
            } else if (id < 100000) {
                sId = "IDoc-0" + id;
            } else {
                sId = "IDoc-" + id;
            }
        }
        importantDocument = new ImportantDocument();
        importantDocument.setId(sId);
        importantDocument.setDetail(detailImportant);
        importantDocument.setGeneralData(generalData);
        importantDocument.setCourse(course);
        ImportantDocument.insert(importantDocument);
//        List<BasketImportant>basketImportantList= new ArrayList<BasketImportant>();
//        if (Cache.get("basketImportantList") != null) {
//            basketImportantList.addAll((List<BasketImportant>) Cache.get("basketImportantList"));
//            basketImportantList.add(new BasketImportant("",detailImportant,null,course));
//        }else{
//            basketImportantList.add(new BasketImportant("",detailImportant,null,course));
//        }
//        Cache.set("basketImportantList", basketImportantList);
        session("nameCategory", "cat6");
        session("generalDataId", generalData.getId());
        return CreateDoc3.selectCat();
    }

    public static Result addSuggestion (){
        DynamicForm dataSugges = Form.form().bindFromRequest();
        String detailSugges = dataSugges.get("detailSuggestion");
        generalData = GeneralData.finder.byId(dataSugges.get("genId"));
        course = generalData.getCourse();
        suggustionDocumentList = SuggustionDocument.suggustionDocumentList();
        int numlist = suggustionDocumentList.size();
        int id, idNum;
        String lastNum;
        String sId;
        if (suggustionDocumentList.size() == 0) {
            sId = "SDoc-000001";
        } else {
            lastNum = suggustionDocumentList.get(numlist - 1).getId();
            lastNum = lastNum.substring(5);
            idNum = Integer.parseInt(lastNum);
            id = idNum + 1;
            if (id < 10) {
                sId = "SDoc-00000" + id;
            } else if (id < 100) {
                sId = "SDoc-0000" + id;
            } else if (id < 1000) {
                sId = "SDoc-000" + id;
            } else if (id < 10000) {
                sId = "SDoc-00" + id;
            } else if (id < 100000) {
                sId = "SDoc-0" + id;
            } else {
                sId = "SDoc-" + id;
            }
        }

        suggustionDocument  = new SuggustionDocument();
        suggustionDocument.setId(sId);
        suggustionDocument.setDetail(detailSugges);
        suggustionDocument.setGeneralData(generalData);
        suggustionDocument.setCourse(course);
        SuggustionDocument.insert(suggustionDocument);
//        List<BasketSuggestion>basketSuggestionList= new ArrayList<BasketSuggestion>();
//        if (Cache.get("basketSuggestionList") != null) {
//            basketSuggestionList.addAll((List<BasketSuggestion>) Cache.get("basketSuggestionList"));
//            basketSuggestionList.add(new BasketSuggestion("",detailSugges,null,course));
//        }else{
//            basketSuggestionList.add(new BasketSuggestion("",detailSugges,null,course));
//        }
//        Cache.set("basketSuggestionList", basketSuggestionList);
        session("nameCategory", "cat6");
        session("generalDataId", generalData.getId());
        return CreateDoc3.selectCat();
    }

    public static Result editMainDoc (String id){
        mainDocument = MainDocument.finder.byId(id);
        generalData = mainDocument.getGeneralData();
        return main(editCategory6_1.render(generalData,mainDocument));
    }
    public static Result editImportantDocument (String id){
        importantDocument = ImportantDocument.finder.byId(id);
        generalData = importantDocument.getGeneralData();
        return main(editCategory6_2.render(generalData,importantDocument));
    }
    public static Result editSuggestion (String id){
        suggustionDocument = SuggustionDocument.finder.byId(id);
        generalData = suggustionDocument.getGeneralData();
        return main(editCategory6_3.render(generalData,suggustionDocument));
    }

    public static Result updateMainDoc(){
        DynamicForm dataUpdate = Form.form().bindFromRequest();
        mainDocument = MainDocument.finder.byId(dataUpdate.get("mainDocId"));
        generalData = mainDocument.getGeneralData();
        mainDocument.setDetail(dataUpdate.get("detail"));
        MainDocument.update(mainDocument);
        session("nameCategory", "cat6");
        session("generalDataId", generalData.getId());
        return CreateDoc3.selectCat();
    }
    public static Result updateImportantDocument(){
        DynamicForm dataUpdate = Form.form().bindFromRequest();
        importantDocument = ImportantDocument.finder.byId(dataUpdate.get("importantId"));
        generalData = importantDocument.getGeneralData();
        importantDocument.setDetail(dataUpdate.get("detail"));
        ImportantDocument.update(importantDocument);
        session("nameCategory", "cat6");
        session("generalDataId", generalData.getId());
        return CreateDoc3.selectCat();
    }

    public static Result updateSuggestion(){
        DynamicForm dataUpdate = Form.form().bindFromRequest();
        suggustionDocument = SuggustionDocument.finder.byId(dataUpdate.get("suggesId"));
        generalData = suggustionDocument.getGeneralData();
        suggustionDocument.setDetail(dataUpdate.get("detail"));
        SuggustionDocument.update(suggustionDocument);
        session("nameCategory", "cat6");
        session("generalDataId", generalData.getId());
        return CreateDoc3.selectCat();
    }
    public static Result deleteMainDoc (String id){
        mainDocument = MainDocument.finder.byId(id);
        generalData = mainDocument.getGeneralData();
        if (mainDocument != null){
            MainDocument.delte(mainDocument);
        }
        session("nameCategory", "cat6");
        session("generalDataId", generalData.getId());
        return CreateDoc3.selectCat();
    }

    public static Result deleteImportant(String id){
        importantDocument = ImportantDocument.finder.byId(id);
        generalData = importantDocument.getGeneralData();
        if (importantDocument != null){
            ImportantDocument.delete(importantDocument);
        }
        session("nameCategory", "cat6");
        session("generalDataId", generalData.getId());
        return CreateDoc3.selectCat();
    }

    public static Result deleteSuggestion (String id){
        suggustionDocument = SuggustionDocument.finder.byId(id);
        generalData = suggustionDocument.getGeneralData();
        if (suggustionDocument != null){
            SuggustionDocument.delete(suggustionDocument);
        }else{
            return ok();
        }
        session("nameCategory", "cat6");
        session("generalDataId", generalData.getId());
        return CreateDoc3.selectCat();
    }
}