package controllers;

import com.avaje.ebean.Expr;
import models.*;
import play.api.templates.Html;
import play.cache.Cache;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;
import views.html.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by coffee on 5/10/2019.
 */
public class PlanTeaching extends Controller {
    public static List<PlanTeachingData> planTeachingDataList = new ArrayList<PlanTeachingData>();
    public static PlanTeachingData planTeachingData;
    public static EvaluationPlanData evaluationPlanData;
    public static List<EvaluationPlanData> evaluationPlanDataList = new ArrayList<EvaluationPlanData>();
    public static DetailPlanEvaluation detailPlanEvaluation;
    public static List<DetailPlanEvaluation> detailPlanEvaluationList = new ArrayList<DetailPlanEvaluation>();
    public static EvaluationStrategy evaluationStrategy;
    public static Teacher teacher;
    public static GeneralData generalData;

    public static Result main(Html Content) {
        return ok(main.render(Content));
    }

    public static Result savePlanTeaching() {
        DynamicForm dataPlanTeaching = Form.form().bindFromRequest();
        String week = dataPlanTeaching.get("week");
        String detail = dataPlanTeaching.get("detail");
        String hour = dataPlanTeaching.get("hour");
        String media = dataPlanTeaching.get("media");
        String teacherName = dataPlanTeaching.get("nameTeacher");
        generalData = GeneralData.finder.byId(dataPlanTeaching.get("genId"));
        planTeachingDataList = PlanTeachingData.planTeachingDataList();
        int numlist;
        int id, idNum;
        String lastNum;
        String sId;
        numlist = planTeachingDataList.size();
        if (planTeachingDataList.size() == 0) {
            sId = "PTD-000001";
        } else {
            lastNum = planTeachingDataList.get(numlist - 1).getId();
            lastNum = lastNum.substring(4);
            idNum = Integer.parseInt(lastNum);
            id = idNum + 1;
            if (id < 10) {
                sId = "PTD-00000" + id;
            } else if (id < 100) {
                sId = "PTD-0000" + id;
            } else if (id < 1000) {
                sId = "PTD-000" + id;
            } else if (id < 10000) {
                sId = "PTD-00" + id;
            } else if (id < 100000) {
                sId = "PTD-0" + id;
            } else {
                sId = "PTD-" + id;
            }

        }
        planTeachingData = new PlanTeachingData();
        planTeachingData.setId(sId);
        planTeachingData.setWeek(week);
        planTeachingData.setHours(hour);
        planTeachingData.setDetail(detail);
        planTeachingData.setMedia(media);
        planTeachingData.setInstructor(teacherName);
        planTeachingData.setGeneralData(generalData);
        List<PlanTeachingData> chkWeek;
        chkWeek = PlanTeachingData.finder.where().and(Expr.eq("week", week), Expr.eq("generalData.id", generalData.getId())).findList();
        if (chkWeek.size() == 0) {
            if (Integer.parseInt(week) > 17) {
                flash("checkWeekAll", "กำหนดสูงสุดไดี 17 สัปดาห์ครับ");
            } else {
                PlanTeachingData.insert(planTeachingData);
            }
        } else {
            flash("checkWeek", "สัปดาห์ที่ " + week + " มีการกำหนกรายระเอียดการสอนไว้เเล้ว กรุณาตรวจาอบเพื่อแก้ไข");
        }

        session("dataPlan", "plan1");
        session("nameCategory", "cat5");
        session("generalDataId", generalData.getId());
        return CreateDoc3.selectCat();
    }

    public static Result editPlanTeaching(String id) {
        planTeachingData = PlanTeachingData.finder.byId(id);
        generalData = planTeachingData.getGeneralData();
        return main(editCategory5_1.render(generalData, planTeachingData));
    }

    public static Result editPlanEvaluation(String id) {
        evaluationPlanData = EvaluationPlanData.finder.byId(id);
        generalData = evaluationPlanData.getGeneralData();
        return main(editCategory5_2.render(generalData, evaluationPlanData));
    }

    public static Result updatePlanTeaching() {
        DynamicForm dataUpdate = Form.form().bindFromRequest();
        planTeachingData = PlanTeachingData.finder.byId(dataUpdate.get("idplan"));
        generalData = planTeachingData.getGeneralData();
        planTeachingData.setWeek(dataUpdate.get("week"));
        planTeachingData.setDetail(dataUpdate.get("detail"));
        planTeachingData.setHours(dataUpdate.get("hour"));
        planTeachingData.setMedia(dataUpdate.get("media"));
        planTeachingData.setInstructor(dataUpdate.get("teacher"));
        PlanTeachingData.update(planTeachingData);
        session("dataPlan", "plan1");
        session("nameCategory", "cat5");
        session("generalDataId", generalData.getId());
        return CreateDoc3.selectCat();
    }

    public static Result selectPlanEvaluation() {
        String   myBasket = "basketList" + session("userId");
        DynamicForm data = Form.form().bindFromRequest();
        generalData = GeneralData.finder.byId(data.get("genId"));
        String numActivity = data.get("numActivity");
        List<EvaluationStrategy> evaluationStrategyList = EvaluationStrategy.evaluationStrategyList();
        List<BasketDetailPlanEvaluation> basketDetailPlanEvaluationList = new ArrayList<BasketDetailPlanEvaluation>();
            basketDetailPlanEvaluationList = (List<BasketDetailPlanEvaluation>) Cache.get(myBasket);
        return main(selectPlanEvaluation.render(generalData, evaluationStrategyList, numActivity, basketDetailPlanEvaluationList));
    }

    public static Result addToBasket() {
        String   myBasket = "basketList" + session("userId");
        DynamicForm data = Form.form().bindFromRequest();
        generalData = GeneralData.finder.byId(data.get("genId"));
        List<BasketDetailPlanEvaluation> basketDetailPlanEvaluationList = new ArrayList<BasketDetailPlanEvaluation>();
        evaluationStrategy = EvaluationStrategy.finder.byId(data.get("evaluationWeek"));
        if (Cache.get(myBasket) != null) {
            basketDetailPlanEvaluationList = (List<BasketDetailPlanEvaluation>) Cache.get(myBasket);
            basketDetailPlanEvaluationList.add(new BasketDetailPlanEvaluation(data.get("week"), data.get("ratingScale"), evaluationStrategy.getDevelopmentalLearning().getNumDomain(), evaluationStrategy));
        } else {
            basketDetailPlanEvaluationList.add(new BasketDetailPlanEvaluation(data.get("week"), data.get("ratingScale"), evaluationStrategy.getDevelopmentalLearning().getNumDomain(), evaluationStrategy));
        }
        List<EvaluationStrategy> evaluationStrategyList = EvaluationStrategy.evaluationStrategyList();
        String numActivity = data.get("numActivity");
        session("numActivity",numActivity);
        Cache.set(myBasket, basketDetailPlanEvaluationList);
        return main(selectPlanEvaluation.render(generalData, evaluationStrategyList, numActivity, basketDetailPlanEvaluationList));
    }

    public static Result savePlanvalutation() {
        String   myBasket = "basketList" + session("userId");
        DynamicForm dataPlan = Form.form().bindFromRequest();
        String activity = dataPlan.get("numActivity");
        generalData = GeneralData.finder.byId(dataPlan.get("genId"));
        List<BasketDetailPlanEvaluation> basketDetailPlanEvaluationList = new ArrayList<BasketDetailPlanEvaluation>();
        if (Cache.get(myBasket) != null) {
            basketDetailPlanEvaluationList = (List<BasketDetailPlanEvaluation>) Cache.get(myBasket);
            evaluationPlanDataList = EvaluationPlanData.evaluationPlanDataList();
            int numlist;
            int id, idNum;
            String lastNum;
            String sId;
            numlist = evaluationPlanDataList.size();
            if (evaluationPlanDataList.size() == 0) {
                sId = "PEV-000001";
            } else {
                lastNum = evaluationPlanDataList.get(numlist - 1).getId();
                lastNum = lastNum.substring(4);
                idNum = Integer.parseInt(lastNum);
                id = idNum + 1;
                if (id < 10) {
                    sId = "PEV-00000" + id;
                } else if (id < 100) {
                    sId = "PEV-0000" + id;
                } else if (id < 1000) {
                    sId = "PEV-000" + id;
                } else if (id < 10000) {
                    sId = "PEV-00" + id;
                } else if (id < 100000) {
                    sId = "PEV-0" + id;
                } else {
                    sId = "PEV-" + id;
                }
            }

            evaluationPlanData = new EvaluationPlanData();
            evaluationPlanData.setId(sId);
            evaluationPlanData.setActivity(activity);
            evaluationPlanData.setLearningOutcome("");
            evaluationPlanData.setDetail(dataPlan.get("detail"));
            evaluationPlanData.setGeneralData(generalData);
            EvaluationPlanData.insert(evaluationPlanData);
            int i;
            String outcome1 = "";
            String outcome2 = "";
            String outcome3 = "";
            String outcome4 = "";
            String outcome5 = "";
            String outcome6 = "";
            for (i = 0; i < basketDetailPlanEvaluationList.size(); i++) {
                evaluationStrategy = EvaluationStrategy.finder.byId(basketDetailPlanEvaluationList.get(i).getEvaluationStrategy().getId());
                String outCome = evaluationStrategy.getDevelopmentalLearning().getNumDomain();
                detailPlanEvaluationList = DetailPlanEvaluation.detailPlanEvaluationList();
                int listnum;
                int idd, iddnum;
                String lasstid;
                String ssid;
                listnum = detailPlanEvaluationList.size();
                if (detailPlanEvaluationList.size() == 0) {
                    ssid = "DES-000001";
                } else {
                    lasstid = detailPlanEvaluationList.get(listnum - 1).getId();
                    lasstid = lasstid.substring(4);
                    iddnum = Integer.parseInt(lasstid);
                    idd = iddnum + 1;
                    if (idd < 10) {
                        ssid = "DES-00000" + idd;
                    } else if (idd < 100) {
                        ssid = "DES-0000" + idd;
                    } else if (idd < 1000) {
                        ssid = "DES-000" + idd;
                    } else if (idd < 10000) {
                        ssid = "DES-00" + idd;
                    } else if (idd < 100000) {
                        ssid = "DES-0" + idd;
                    } else {
                        ssid = "DES-" + idd;
                    }
                }
                detailPlanEvaluation = new DetailPlanEvaluation();
                detailPlanEvaluation.setId(ssid);
                detailPlanEvaluation.setEvaluationStrategy(evaluationStrategy);
                detailPlanEvaluation.setEvaluationWeek(basketDetailPlanEvaluationList.get(i).getWeek());
                detailPlanEvaluation.setRatingScale(basketDetailPlanEvaluationList.get(i).getRate());
                evaluationPlanData = EvaluationPlanData.finder.byId(sId);
                detailPlanEvaluation.setEvaluationPlanData(evaluationPlanData);

                if (basketDetailPlanEvaluationList.get(i).getOutcome().equals("1")) {
                    outcome1 = "1";
                } else if (basketDetailPlanEvaluationList.get(i).getOutcome().equals("2")) {
                    outcome2 = "2";
                } else if (basketDetailPlanEvaluationList.get(i).getOutcome().equals("3")) {
                    outcome3 = "3";
                } else if (basketDetailPlanEvaluationList.get(i).getOutcome().equals("4")) {
                    outcome4 = "4";
                } else if (basketDetailPlanEvaluationList.get(i).getOutcome().equals("5")) {
                    outcome5 = "5";
                } else if (basketDetailPlanEvaluationList.get(i).getOutcome().equals("6")) {
                    outcome6 = "6";
                }
                String dataOutcome = outcome1 + " " + outcome2 + " " + outcome3 + " " + outcome4 + " " + outcome5 + " " + outcome6;
                evaluationPlanData.setLearningOutcome(dataOutcome);
                EvaluationPlanData.update(evaluationPlanData);
                DetailPlanEvaluation.insert(detailPlanEvaluation);
            }
        }
        Cache.remove(myBasket);
        session("dataPlan", "plan2");
        session("nameCategory", "cat5");
        session("generalDataId", generalData.getId());
        return CreateDoc3.selectCat();
    }

    public static Result deletePlanTeaching(String id) {
        planTeachingData = PlanTeachingData.finder.byId(id);
        generalData = planTeachingData.getGeneralData();
        if (planTeachingData != null) {
            PlanTeachingData.delete(planTeachingData);
        }
        session("dataPlan", "plan1");
        session("nameCategory", "cat5");
        session("generalDataId", generalData.getId());
        return CreateDoc3.selectCat();

    }

    public static Result deletePlanValutation(String id) {
        evaluationPlanData = EvaluationPlanData.finder.byId(id);
        generalData = evaluationPlanData.getGeneralData();
        if (evaluationPlanData != null) {
            EvaluationPlanData.delete(evaluationPlanData);
        }
        session("dataPlan", "plan2");
        session("nameCategory", "cat5");
        session("generalDataId", generalData.getId());
        return CreateDoc3.selectCat();
    }

    public static Result deletePlanBasket(String id) {
        String   myBasket = "basketList" + session("userId");
        List<BasketDetailPlanEvaluation> basketDetailPlanEvaluationList = new ArrayList<BasketDetailPlanEvaluation>();
        if (Cache.get(myBasket) != null) {
            basketDetailPlanEvaluationList = (List<BasketDetailPlanEvaluation>) Cache.get(myBasket);
            for (int i = 0; i < basketDetailPlanEvaluationList.size(); i++) {
                if (basketDetailPlanEvaluationList.get(i).getEvaluationStrategy().getId().equals(id)) ;
                basketDetailPlanEvaluationList.remove(i);
                break;
            }
        }
        Cache.set(myBasket, basketDetailPlanEvaluationList);
        List<EvaluationStrategy> evaluationStrategyList = EvaluationStrategy.evaluationStrategyList();
        return main(selectPlanEvaluation.render(generalData, evaluationStrategyList, session("numActivity"), basketDetailPlanEvaluationList));
    }

}
