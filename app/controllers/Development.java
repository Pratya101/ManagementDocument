package controllers;


import com.avaje.ebean.Expr;
import models.*;
import play.api.templates.Html;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by PRATYA on 11/22/2018.
 */
public class Development extends Controller {
    public static List<SyllabusCourse> syllabusCourseList = new ArrayList<SyllabusCourse>();
    public static SyllabusCourse syllabusCourse;

    public static Result main(Html Content) {
        return ok(main.render(Content));
    }

    public static Result seleceCategoryCourseForDev() {
        session("numMenu", "dataDev");
        syllabusCourseList = SyllabusCourse.syllabusCourseList();
        return main(selectSyllabusCourseDev.render(syllabusCourseList));
    }

    //DevelopmentalLearning
    public static List<DevelopmentalLearning> developmentalLearningList = new ArrayList<DevelopmentalLearning>();
    public static Form<DevelopmentalLearning> developmentalLearningForm = Form.form(DevelopmentalLearning.class);
    public static DevelopmentalLearning developmentalLearning;

    public static Result listDevelopmentalLearning(String id) {
        session("numMenu", "dataDev");
        session("sylly", id);
        developmentalLearningList = DevelopmentalLearning.finder.where().eq("syllabusCourse.id", session("sylly")).findList();
        syllabusCourse = SyllabusCourse.finder.byId(session("sylly"));
        return main(listDevelopmentalLearning.render(developmentalLearningList, syllabusCourse));
    }

    public static Result formAddDevelopmentalLearning() {
        developmentalLearningForm = Form.form(DevelopmentalLearning.class);
        syllabusCourse = SyllabusCourse.finder.byId(session("sylly"));
        return main(formAddDevelopmentalLearning.render(developmentalLearningForm, syllabusCourse));
    }

    public static Result insertDevelopmentalLearning() {
        Form<DevelopmentalLearning> newDevelopmentalLearning = developmentalLearningForm.bindFromRequest();
        if (newDevelopmentalLearning.hasErrors()) {
            flash("newDevError", "ข้อมูลไม่ถูกต้อง กรุณาตรวจสอบใหม่ด้วยครับ!");
            syllabusCourse = SyllabusCourse.finder.byId(session("sylly"));
            return main(formAddDevelopmentalLearning.render(newDevelopmentalLearning, syllabusCourse));
        } else {
            developmentalLearning = newDevelopmentalLearning.get();
            DevelopmentalLearning chk;
            chk = DevelopmentalLearning.finder.where().and(Expr.eq("numDomain", developmentalLearning.getNumDomain()), Expr.eq("syllabusCourse.id", session("sylly"))).findUnique();
            if (chk != null) {
                flash("newDevListError", "หมายเลขการพัฒนาการเรียนรู้ของหลักสูตรนี้มีอยู่เเล้วในฐานข้อมูล ไม่สามารถใช้หมายเลขซ้ำได้");
                syllabusCourse = SyllabusCourse.finder.byId(session("sylly"));
                return main(formAddDevelopmentalLearning.render(newDevelopmentalLearning, syllabusCourse));
            } else {
                developmentalLearningList = DevelopmentalLearning.developmentalLearningList();
                              int numlist = developmentalLearningList.size();
                int id, idNum;
                String lastNum;
                String sId;
                if (developmentalLearningList.size() == 0) {
                    sId = "DVL-000001";
                } else {
                    lastNum = developmentalLearningList.get(numlist - 1).getId();
                    lastNum = lastNum.substring(4);
                    idNum = Integer.parseInt(lastNum);
                    id = idNum + 1;
                    if (id < 10) {
                        sId = "DVL-00000" + id;
                    } else if (id < 100) {
                        sId = "DVL-0000" + id;
                    } else if (id < 1000) {
                        sId = "DVL-000" + id;
                    } else if (id < 10000) {
                        sId = "DVL-00" + id;
                    } else if (id < 100000) {
                        sId = "DVL-0" + id;
                    } else {
                        sId = "DVL-" + id;
                    }
                }
                developmentalLearning.setId(sId);
                syllabusCourse = SyllabusCourse.finder.byId(session("sylly"));
                developmentalLearning.setSyllabusCourse(syllabusCourse);
                DevelopmentalLearning.insert(developmentalLearning);
            }
            flash("insertOK", "ทำการเพิ่ม " + developmentalLearning.getName() + " เรียบร้อยเเล่ว");
            return redirect("/listDevelopmentalLearning/" + session("sylly"));
        }
    }

    public static Result editDevelopmentalLearning(String id) {
        developmentalLearning = DevelopmentalLearning.finder.byId(id);
        if (developmentalLearning != null) {
            developmentalLearningForm = Form.form(DevelopmentalLearning.class).fill(developmentalLearning);
            return main(editDevelopmentalLearning.render(developmentalLearningForm));
        } else {
            flash("dataDevError", "มีการแก้ไข URL");
            return redirect("/listDevelopmentalLearning/" + session("sylly"));
        }
    }

    public static Result updateDevelopmentalLearning() {
        Form<DevelopmentalLearning> dataDevelopmentalLearning = developmentalLearningForm.bindFromRequest();
        if (dataDevelopmentalLearning.hasErrors()) {
            flash("dataDevError", "ข้อมูลไม่ถูกต้อง กรุณาตรวจสอบใหม่ด้วยครับ!");
            return main(editDevelopmentalLearning.render(dataDevelopmentalLearning));
        } else {
            developmentalLearning = dataDevelopmentalLearning.get();
            DevelopmentalLearning.update(developmentalLearning);
            flash("updateOK", "ทำการเเก้ไขเรียบร้อยเเล้ว");
            return redirect("/listDevelopmentalLearning/" + session("sylly"));
        }
    }

    public static Result deleteDevelopmentalLearning(String id) {
        developmentalLearning = DevelopmentalLearning.finder.byId(id);
        session("nameDev", developmentalLearning.getName());
        if (developmentalLearning != null) {
            developmentalLearningListList = DevelopmentalLearningList.finder.where().eq("developmentalLearning.id", id).findList();
            evaluationStrategyList = EvaluationStrategy.finder.where().eq("developmentalLearning.id", id).findList();
            teachingStrategiesList = TeachingStrategies.finder.where().eq("developmentalLearning.id", id).findList();
            if (developmentalLearningListList.size() != 0 || evaluationStrategyList.size() != 0 || teachingStrategiesList.size() != 0) {
                flash("noDelOK", "ไม่สามาถลบได้เนื่องจากข้อมูลนี้มีการใช้งานจากหน้าอื่นอยู่");
            } else {
                DevelopmentalLearning.delete(developmentalLearning);
                flash("delOK", "ลบ " + session("nameDev") + " เรียบร้อยแล้ว");
                session().remove("nameDev");
            }
        }
        return redirect("/listDevelopmentalLearning/" + session("sylly"));
    }

    //DevelopmentalLearningList
    public static List<DevelopmentalLearningList> developmentalLearningListList = new ArrayList<DevelopmentalLearningList>();
    public static Form<DevelopmentalLearningList> developmentalLearningListForm = Form.form(DevelopmentalLearningList.class);
    public static DevelopmentalLearningList developmentalLearningListOb;
    public static String devID;

    public static Result listDevelopmentalLearningList(String id) {
        session("divId", id);
        devID = id;
        developmentalLearning = DevelopmentalLearning.nameDev(id);
        developmentalLearningListList = DevelopmentalLearningList.developmentalLearningListOnly(id);
        return main(listDevelopmentalLearningList.render(developmentalLearningListList, developmentalLearning));
    }

    public static Result formAddDevelopmentalLearningList() {
        developmentalLearningListForm = Form.form(DevelopmentalLearningList.class);
        return main(formAddDevelopmentalLearningList.render(developmentalLearningListForm));
    }

    public static Result insertDevelopmentalLearningList() {
        Form<DevelopmentalLearningList> newDevelopmentalLearningList = developmentalLearningListForm.bindFromRequest();
        if (newDevelopmentalLearningList.hasErrors()) {
            flash("newDevListError", "ข้อมูลไม่ถูกต้อง กรุณาตรวจสอบใหม่ด้วยครับ!");
            return main(formAddDevelopmentalLearningList.render(newDevelopmentalLearningList));
        } else {
            developmentalLearningListOb = newDevelopmentalLearningList.get();
            developmentalLearning = DevelopmentalLearning.finder.byId(session("divId"));
            developmentalLearningListOb.setDevelopmentalLearning(developmentalLearning);
            developmentalLearningListList = DevelopmentalLearningList.developmentalLearningListList();
            int numlist = developmentalLearningListList.size();
            int id, idNum;
            String lastNum;
            String sId;
            if (developmentalLearningListList.size() == 0) {
                sId = "DEV-000001";
            } else {
                lastNum = developmentalLearningListList.get(numlist - 1).getId();
                lastNum = lastNum.substring(4);
                idNum = Integer.parseInt(lastNum);
                id = idNum + 1;
                if (id < 10) {
                    sId = "DEV-00000" + id;
                } else if (id < 100) {
                    sId = "DEV-0000" + id;
                } else if (id < 1000) {
                    sId = "DEV-000" + id;
                } else if (id < 10000) {
                    sId = "DEV-00" + id;
                } else if (id < 100000) {
                    sId = "DEV-0" + id;
                } else {
                    sId = "DEV-" + id;
                }
            }
            developmentalLearningListOb.setId(sId);
            DevelopmentalLearningList.insert(developmentalLearningListOb);
            flash("insertOK", "ทำการเพิ่ม " + developmentalLearningListOb.getListDevelopmentalLearning() + " เรียบร้อยเเล่ว");
            return redirect("/listDevelopmentalLearningList/" + session("divId"));
        }
    }

    public static Result editDevelopmentalLearningList(String id) {
        developmentalLearningList = DevelopmentalLearning.developmentalLearningList();
        developmentalLearningListOb = DevelopmentalLearningList.finder.byId(id);
        if (developmentalLearningList != null) {
            developmentalLearningListForm = Form.form(DevelopmentalLearningList.class).fill(developmentalLearningListOb);
            return main(editDevelopmentalLearningList.render(developmentalLearningListForm, developmentalLearningList));
        } else {
            flash("dataDevListError", "มีการแก้ไข URL");
            return redirect("/listDevelopmentalLearningList/" + session("divId"));
        }
    }

    public static Result updateDevelopmentalLearningList() {
        developmentalLearningList = DevelopmentalLearning.developmentalLearningList();
        Form<DevelopmentalLearningList> dataDevelopmentalLearningList = developmentalLearningListForm.bindFromRequest();
        if (dataDevelopmentalLearningList.hasErrors()) {
            flash("dataDevListError", "ข้อมูลไม่ถูกต้อง กรุณาตรวจสอบใหม่ด้วยครับ!");
            return main(editDevelopmentalLearningList.render(dataDevelopmentalLearningList, developmentalLearningList));
        } else {
            developmentalLearningListOb = dataDevelopmentalLearningList.get();
            DevelopmentalLearningList.update(developmentalLearningListOb);
            flash("updateOK", "ทำการเเก้ไขเรียบร้อยเเล้ว");
            return redirect("/listDevelopmentalLearningList/" + session("divId"));
        }
    }

    public static Result deleteDevelopmentalLearningList(String id) {
        developmentalLearningListOb = DevelopmentalLearningList.finder.byId(id);
        session("nameDevList", developmentalLearningListOb.getListDevelopmentalLearning());
        if (developmentalLearningListOb != null) {
            DevelopmentalLearningList.delete(developmentalLearningListOb);
            flash("delOK", "ลบ " + session("nameDevList") + " เรียบร้อยแล้ว");
        }
        return redirect("/listDevelopmentalLearningList/" + session("divId"));
    }

    //TeachingStrategies
    public static List<TeachingStrategies> teachingStrategiesList = new ArrayList<TeachingStrategies>();
    public static Form<TeachingStrategies> teachingStrategiesForm = Form.form(TeachingStrategies.class);
    public static TeachingStrategies teachingStrategies;
    public static String tsID;

    public static Result listTeachingStrategies(String id) {
        session("tsID", id);
        developmentalLearning = DevelopmentalLearning.nameDev(id);
        teachingStrategiesList = TeachingStrategies.teachingStrategiesOnly(id);
        return main(listTeachingStrategies.render(teachingStrategiesList, developmentalLearning));
    }

    public static Result formAddTeachingStrategies() {
        developmentalLearningList = DevelopmentalLearning.developmentalLearningList();
        teachingStrategiesForm = Form.form(TeachingStrategies.class);
        return main(formAddTeachingStrategies.render(teachingStrategiesForm, developmentalLearningList));
    }

    public static Result insertTeachingStrategies() {
        developmentalLearningList = DevelopmentalLearning.developmentalLearningList();
        Form<TeachingStrategies> newTeachingStrategies = teachingStrategiesForm.bindFromRequest();
        if (newTeachingStrategies.hasErrors()) {
            flash("newTeacherStrategiesError", "ข้อมูลไม่ถูกต้อง กรุณาตรวจสอบใหม่ด้วยครับ!");
            return main(formAddTeachingStrategies.render(newTeachingStrategies, developmentalLearningList));
        } else {
            teachingStrategies = newTeachingStrategies.get();
            developmentalLearning = DevelopmentalLearning.nameDev(session("tsID"));
            teachingStrategies.setDevelopmentalLearning(developmentalLearning);
            teachingStrategiesList = TeachingStrategies.teachingStrategiesList();
            int numlist = teachingStrategiesList.size();
            int id, idNum;
            String lastNum;
            String sId;
            if (teachingStrategiesList.size() == 0) {
                sId = "TES-000001";
            } else {
                lastNum = teachingStrategiesList.get(numlist - 1).getId();
                lastNum = lastNum.substring(4);
                idNum = Integer.parseInt(lastNum);
                id = idNum + 1;
                if (id < 10) {
                    sId = "TES-00000" + id;
                } else if (id < 100) {
                    sId = "TES-0000" + id;
                } else if (id < 1000) {
                    sId = "TES-000" + id;
                } else if (id < 10000) {
                    sId = "TES-00" + id;
                } else if (id < 100000) {
                    sId = "TES-0" + id;
                } else {
                    sId = "TES-" + id;
                }
            }
            teachingStrategies.setId(sId);
            TeachingStrategies.insert(teachingStrategies);
            flash("insertOK", "ทำการเพิ่ม " + teachingStrategies.getTeachingMethods() + " เรียบร้อยเเล่ว");
            return redirect("/listTeachingStrategies/" + session("tsID"));
        }
    }

    public static Result editTeachingStrategies(String id) {
        developmentalLearningList = DevelopmentalLearning.developmentalLearningList();
        teachingStrategies = TeachingStrategies.finder.byId(id);
        if (teachingStrategies != null) {
            teachingStrategiesForm = Form.form(TeachingStrategies.class).fill(teachingStrategies);
            return main(editTeachingStrategies.render(teachingStrategiesForm, developmentalLearningList));
        } else {
            flash("dataTeacherStrategiesError", "มีการแก้ไข URL");
            return redirect("/listTeachingStrategies/" + session("tsID"));
        }
    }

    public static Result updateTeachingStrategies() {
        developmentalLearningList = DevelopmentalLearning.developmentalLearningList();
        Form<TeachingStrategies> dataTeachingStrategies = teachingStrategiesForm.bindFromRequest();
        if (dataTeachingStrategies.hasErrors()) {
            flash("dataTeacherStrategiesError", "ข้อมูลไม่ถูกต้อง กรุณาตรวจสอบใหม่ด้วยครับ!");
            return main(editTeachingStrategies.render(dataTeachingStrategies, developmentalLearningList));
        } else {
            teachingStrategies = dataTeachingStrategies.get();
            TeachingStrategies.update(teachingStrategies);
            flash("updateOK", "ทำการเเก้ไขเรียบร้อยเเล้ว");
            return redirect("/listTeachingStrategies/" + session("tsID"));
        }
    }

    public static Result deleteTeachingStrategies(String id) {
        teachingStrategies = TeachingStrategies.finder.byId(id);
        session("teachingStrategies", teachingStrategies.getTeachingMethods());
        if (teachingStrategies != null) {
            TeachingStrategies.delete(teachingStrategies);
            flash("delOK", "ลบ " + session("teachingStrategies") + " เรียบร้อยแล้ว");
        }
        return redirect("/listTeachingStrategies/" + session("tsID"));
    }

    //EvaluationStrategy
    public static List<EvaluationStrategy> evaluationStrategyList = new ArrayList<EvaluationStrategy>();
    public static Form<EvaluationStrategy> evaluationStrategyForm = Form.form(EvaluationStrategy.class);
    public static EvaluationStrategy evaluationStrategy;

    public static Result listEvaluationStrategy(String id) {
        session("esId", id);
        developmentalLearning = DevelopmentalLearning.nameDev(id);
        evaluationStrategyList = EvaluationStrategy.evaluationStrategyOnly(id);
        return main(listEvaluationStrategy.render(evaluationStrategyList, developmentalLearning));
    }

    public static Result formAddEvaluationStrategy() {
        developmentalLearningList = DevelopmentalLearning.developmentalLearningList();
        evaluationStrategyForm = Form.form(EvaluationStrategy.class);
        return main(formAddEvaluationStrategy.render(evaluationStrategyForm, developmentalLearningList));
    }

    public static Result insertEvaluationStrategy() {
        developmentalLearningList = DevelopmentalLearning.developmentalLearningList();
        Form<EvaluationStrategy> newEvaluationStrategy = evaluationStrategyForm.bindFromRequest();
        if (newEvaluationStrategy.hasErrors()) {
            flash("newTeacherStrategiesError", "ข้อมูลไม่ถูกต้อง กรุณาตรวจสอบใหม่ด้วยครับ!");
            return main(formAddEvaluationStrategy.render(newEvaluationStrategy, developmentalLearningList));
        } else {
            evaluationStrategy = newEvaluationStrategy.get();
            developmentalLearning = DevelopmentalLearning.nameDev(session("esId"));
            evaluationStrategy.setDevelopmentalLearning(developmentalLearning);
            evaluationStrategyList = EvaluationStrategy.evaluationStrategyList();
            int numlist = evaluationStrategyList.size();
            int id, idNum;
            String lastNum;
            String sId;
            if (evaluationStrategyList.size() == 0) {
                sId = "EVA-000001";
            } else {
                lastNum = evaluationStrategyList.get(numlist - 1).getId();
                lastNum = lastNum.substring(4);
                idNum = Integer.parseInt(lastNum);
                id = idNum + 1;
                if (id < 10) {
                    sId = "EVA-00000" + id;
                } else if (id < 100) {
                    sId = "EVA-0000" + id;
                } else if (id < 1000) {
                    sId = "EVA-000" + id;
                } else if (id < 10000) {
                    sId = "EVA-00" + id;
                } else if (id < 100000) {
                    sId = "EVA-0" + id;
                } else {
                    sId = "EVA-" + id;
                }
            }
            evaluationStrategy.setId(sId);
            EvaluationStrategy.insert(evaluationStrategy);
            flash("insertOK", "ทำการเพิ่ม " + evaluationStrategy.getEvaluationMethod() + " เรียบร้อยเเล่ว");
            return redirect("/listEvaluationStrategy/" + session("esId"));
        }
    }


    public static Result editEvaluationStrategy(String id) {
        developmentalLearningList = DevelopmentalLearning.developmentalLearningList();
        evaluationStrategy = EvaluationStrategy.finder.byId(id);
        if (evaluationStrategy != null) {
            evaluationStrategyForm = Form.form(EvaluationStrategy.class).fill(evaluationStrategy);
            return main(editEvaluationStrategy.render(evaluationStrategyForm, developmentalLearningList));
        } else {
            flash("dataTeacherStrategiesError", "มีการแก้ไข URL");
            return redirect("/listEvaluationStrategy/" + session("esId"));
        }
    }

    public static Result updateEvaluationStrategy() {
        developmentalLearningList = DevelopmentalLearning.developmentalLearningList();
        Form<EvaluationStrategy> dataEvaluationStrategy = evaluationStrategyForm.bindFromRequest();
        if (dataEvaluationStrategy.hasErrors()) {
            flash("dataTeacherStrategiesError", "ข้อมูลไม่ถูกต้อง กรุณาตรวจสอบใหม่ด้วยครับ!");
            return main(editEvaluationStrategy.render(dataEvaluationStrategy, developmentalLearningList));
        } else {
            evaluationStrategy = dataEvaluationStrategy.get();
            EvaluationStrategy.update(evaluationStrategy);
            flash("updateOK", "ทำการเเก้ไขเรียบร้อยเเล้ว");
            return redirect("/listEvaluationStrategy/" + session("esId"));
        }
    }

    public static Result deleteEvaluationStrategy(String id) {
        evaluationStrategy = EvaluationStrategy.finder.byId(id);
        session("evaluationStrategy", evaluationStrategy.getEvaluationMethod());
        if (evaluationStrategy != null) {
            EvaluationStrategy.delete(evaluationStrategy);
            flash("delOK", "ลบ " + session("evaluationStrategy") + " เรียบร้อยแล้ว");
        }
        return redirect("/listEvaluationStrategy/" + session("esId"));
    }

}
