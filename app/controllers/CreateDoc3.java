package controllers;

import com.avaje.ebean.Expr;
import models.*;
import play.api.templates.Html;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Controller;
import com.avaje.ebean.Ebean;
import com.avaje.ebean.Query;
import play.mvc.Result;
import views.html.CreateCategory.*;
import views.html.*;

import javax.xml.crypto.Data;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by coffee on 5/8/2019.
 */
public class CreateDoc3 extends Controller {
    public static List<Instructors> instructorsList = new ArrayList<Instructors>();
    public static Instructors instructors;
    public static Course course;
    public static GeneralData generalData;
    public static List<GeneralData> generalDataList = new ArrayList<GeneralData>();
    public static Teacher teacher;
    public static List<CourseDestination> courseDestinationList = new ArrayList<CourseDestination>();
    public static List<CourseObjectives> courseObjectivesList = new ArrayList<CourseObjectives>();
    public static CourseDestination courseDestination;
    public static CourseObjectives courseObjectivess;
    public static DataHours dataHours;
    public static List<DataHours> dataHoursList = new ArrayList<DataHours>();
    public static PlanTeachingData planTeachingData;
    public static List<PlanTeachingData> planTeachingDataList = new ArrayList<PlanTeachingData>();
    public static EvaluationPlanData evaluationPlanData;
    public static List<EvaluationPlanData> evaluationPlanDataList = new ArrayList<EvaluationPlanData>();
    public static List<MainDocument> mainDocumentList = new ArrayList<MainDocument>();
    public static MainDocument mainDocument;
    public static List<ImportantDocument> importantDocumentList = new ArrayList<ImportantDocument>();
    public static ImportantDocument importantDocument;
    public static List<SuggustionDocument> suggustionDocumentList = new ArrayList<SuggustionDocument>();
    public static SuggustionDocument suggustionDocument;
    public static List<CourseAssessmentStrategies> courseAssessmentStrategiesList = new ArrayList<CourseAssessmentStrategies>();
    public static CourseAssessmentStrategies courseAssessmentStrategies;
    public static List<TeachingStrategiesData> teachingStrategiesDataList = new ArrayList<TeachingStrategiesData>();
    public static TeachingStrategiesData teachingStrategiesData;
    public static List<ImproveTeachingData> improveTeachingDataList = new ArrayList<ImproveTeachingData>();
    public static ImproveTeachingData improveTeachingData;
    public static List<StandardReview> standardReviewList = new ArrayList<StandardReview>();
    public static StandardReview standardReview;
    public static List<ReviewAndPlan> reviewAndPlanList = new ArrayList<ReviewAndPlan>();
    public static ReviewAndPlan reviewAndPlan;
    public static List<DetailPlanEvaluation> detailPlanEvaluationList = new ArrayList<DetailPlanEvaluation>();
    public static DetailPlanEvaluation detailPlanEvaluation;
    public static List<Data_ListDevelopment> data_listDevelopmentList = new ArrayList<Data_ListDevelopment>();
    public static List<Data_MethodTeaching> data_methodTeachingList = new ArrayList<Data_MethodTeaching>();
    public static List<Data_MethodEvaluation> data_methodEvaluationList = new ArrayList<Data_MethodEvaluation>();
    public static Data_ListDevelopment data_listDevelopment;
    public static Data_MethodEvaluation data_methodEvaluation;
    public static Data_MethodTeaching data_methodTeaching;

    public static Result main(Html Content) {
        return ok(main.render(Content));
    }

    public static Result copyDoc3(String Insid) {

        instructors = Instructors.finder.byId(Insid);
        generalDataList = GeneralData.finder.where().eq("course.id", instructors.getCourse().getId()).findList();
        int i = generalDataList.size() - 1;
        if (generalDataList.size() != 0) {
            for (int n = 0;n<generalDataList.size();n++){
                if (generalDataList.get(n).getCourseDestinationList().size()!=0||generalDataList.get(n).getCourseObjectivesList().size()!=0||
                        generalDataList.get(n).getDataHoursList().size()!=0||generalDataList.get(n).getSuggestion()!=null||generalDataList.get(n).getData_listDevelopmentList().size()!=0
                        ||generalDataList.get(n).getData_methodTeachingList().size()!=0||generalDataList.get(n).getData_methodEvaluationList().size()!=0||generalDataList.get(n).getPlanTeachingDataList().size()!=0||
                        generalDataList.get(n).getEvaluationPlanDataList().size()!=0||generalDataList.get(n).getMainDocumentList().size()!=0||
                        generalDataList.get(n).getImportantDocumentList().size()!=0||generalDataList.get(n).getSuggustionDocumentList().size()!=0||
                        generalDataList.get(n).getCourseAssessmentStrategiesList().size()!=0||generalDataList.get(n).getTeachingStrategiesDataList().size()!=0||
                        generalDataList.get(n).getImproveTeachingDataList().size()!=0||generalDataList.get(n).getStandardReviews().size()!=0||
                        generalDataList.get(n).getReviewAndPlanList().size()!=0){
                    course = generalDataList.get(n).getCourse();
                    generalData = generalDataList.get(n);
                    break;
                }
            }
            boolean found = false;
            for (int x = 0; x < generalDataList.size(); x++) {
                if (instructors.getTerm().equals(generalDataList.get(x).getInstructors().getTerm()) && instructors.getYear().equals(generalDataList.get(x).getInstructors().getYear())) {
                    flash("NotMakt", "ไม่สามารถทำสำเนาได้เนื่องจาก รายวิชานี้ของปีเเละเทอมนี้ มีการสร้างเอกสารไว้เเล้ว กรุณาตรวจสอบที่หน้ารายการเอกสาร");
                    found = true;
                    return redirect("/selectCourse");
                }
            }
            if (found == false) {
//                course = generalDataList.get(i).getCourse();
//                generalData = generalDataList.get(i);
                String sugges = generalData.getSuggestion();
                session("dataGenaralData", generalData.getId());
//สร้างเอกสารใหม่
                List<GeneralData> numRow = GeneralData.generalDataList();
                int numlist = numRow.size();
                int id, idNum;
                String lastNum;
                String sId;
                if (numRow.size() == 0) {
                    sId = "Doc-000001";
                } else {
                    lastNum = numRow.get(numlist - 1).getId();
                    lastNum = lastNum.substring(4);
                    idNum = Integer.parseInt(lastNum);
                    id = idNum + 1;
                    if (id < 10) {
                        sId = "Doc-00000" + id;
                    } else if (id < 100) {
                        sId = "Doc-0000" + id;
                    } else if (id < 1000) {
                        sId = "Doc-000" + id;
                    } else if (id < 10000) {
                        sId = "Doc-00" + id;
                    } else if (id < 100000) {
                        sId = "Doc-0" + id;
                    } else {
                        sId = "Doc-" + id;
                    }
                }
                session("newGenId", sId);
                teacher = Teacher.finder.byId(session("userId"));
                generalData = new GeneralData();
                generalData.setId(sId);
                generalData.setCourse(course);
                generalData.setInstructors(instructors);
                generalData.setTeacher(teacher);
                generalData.setTerm(instructors.getTerm());
                generalData.setYear(instructors.getYear());
                instructors.setStatusDoc("1");
                Format formatter;
                formatter = new SimpleDateFormat("EEEE ที่ dd เดือน MMMM พ.ศ. yyyy", new Locale("th", "TH"));
                generalData.setDetail(formatter.format(new Date()));
                Instructors.update(instructors);
                GeneralData.insert(generalData);
                //Coppy จุดมุงหมายรายวิชา
                generalData = GeneralData.finder.byId(session("dataGenaralData"));
                courseDestinationList = CourseDestination.finder.where().eq("generalData.id", generalData.getId()).findList();
                for (int y = 0; y < courseDestinationList.size(); y++) {
                    String detail = courseDestinationList.get(y).getDetail();
                    List<CourseDestination> numrow = CourseDestination.courseDestinationList();
                    numlist = numrow.size();
                    if (numrow.size() == 0) {
                        sId = "Des-000001";
                    } else {
                        lastNum = numrow.get(numlist - 1).getId();
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
                    generalData = GeneralData.finder.byId(session("newGenId"));
                    courseDestination.setId(sId);
                    courseDestination.setDetail(detail);
                    courseDestination.setCourse(course);
                    courseDestination.setGeneralData(generalData);
                    CourseDestination.insert(courseDestination);
                }
//Copy วัตุประสงค์ในการพัฒนา/ปรับปรุงรายวิชา
                generalData = GeneralData.finder.byId(session("dataGenaralData"));
                courseObjectivesList = CourseObjectives.finder.where().eq("generalData.id", generalData.getId()).findList();
                for (int y = 0; y < courseObjectivesList.size(); y++) {
                    String detail = courseObjectivesList.get(y).getDetail();
                    List<CourseObjectives> numrowobj = CourseObjectives.courseObjectivesList();
                    numlist = numrowobj.size();
                    if (numrowobj.size() == 0) {
                        sId = "OJT-000001";
                    } else {
                        lastNum = numrowobj.get(numlist - 1).getId();
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
                    generalData = GeneralData.finder.byId(session("newGenId"));
                    courseObjectivess.setId(sId);
                    courseObjectivess.setDetail(detail);
                    courseObjectivess.setCourse(course);
                    courseObjectivess.setGeneralData(generalData);
                    CourseObjectives.insert(courseObjectivess);
                }

                //Copy จำนวนชั่วโมงที่ใชต่อภาคการศึกษา
                generalData = GeneralData.finder.byId(session("dataGenaralData"));
                dataHours = DataHours.finder.where().eq("generalData.id", generalData.getId()).findUnique();
                dataHoursList = DataHours.dataHoursList();
                numlist = dataHoursList.size();
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
                DataHours newDataHours = new DataHours();
                generalData = GeneralData.finder.byId(session("newGenId"));
                newDataHours.setId(sId);
                newDataHours.sethDescribe(dataHours.gethDescribe());
                newDataHours.sethStudy(dataHours.gethStudy());
                newDataHours.sethSupplementary(dataHours.gethSupplementary());
                newDataHours.sethWork(dataHours.gethWork());
                newDataHours.setGeneralData(generalData);
                generalData.setSuggestion(sugges);
                GeneralData.update(generalData);
                DataHours.insert(newDataHours);

                //CopyData_ListDevelopment
                generalData = GeneralData.finder.byId(session("dataGenaralData"));
                data_listDevelopmentList = Data_ListDevelopment.finder.where().eq("generalData.id", generalData.getId()).findList();
                for (int y = 0; y < data_listDevelopmentList.size(); y++) {
                    List<Data_ListDevelopment> numrow = Data_ListDevelopment.data_listDevelopmentList();
                    numlist = numrow.size();
                    if (numrow.size() == 0) {
                        sId = "Dev-000001";
                    } else {
                        lastNum = numrow.get(numlist - 1).getId();
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
                    generalData = GeneralData.finder.byId(session("newGenId"));
                    data_listDevelopment = new Data_ListDevelopment();
                    data_listDevelopment.setId(sId);
                    data_listDevelopment.setCurriculum(data_listDevelopmentList.get(y).getCurriculum());
                    data_listDevelopment.setGeneralData(generalData);
                    data_listDevelopment.setDevelopmentalLearning(data_listDevelopmentList.get(y).getDevelopmentalLearning());
                    Data_ListDevelopment.insert(data_listDevelopment);
                }

                //Copy Data_MethodTeaching
                generalData = GeneralData.finder.byId(session("dataGenaralData"));
                data_methodTeachingList = Data_MethodTeaching.finder.where().eq("generalData.id", generalData.getId()).findList();
                for (int y = 0; y < data_methodTeachingList.size(); y++) {
                    List<Data_MethodTeaching> numrow = Data_MethodTeaching.data_methodTeachingList();
                    numlist = numrow.size();
                    if (numrow.size() == 0) {
                        sId = "DMT-000001";
                    } else {
                        lastNum = numrow.get(numlist - 1).getId();
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
                    generalData = GeneralData.finder.byId(session("newGenId"));
                    data_methodTeaching = new Data_MethodTeaching();
                    data_methodTeaching.setId(sId);
                    data_methodTeaching.setTeachingStrategies(data_methodTeachingList.get(y).getTeachingStrategies());
                    data_methodTeaching.setGeneralData(generalData);
                    data_methodTeaching.setDevelopmentalLearning(data_methodTeachingList.get(y).getDevelopmentalLearning());
                    Data_MethodTeaching.insert(data_methodTeaching);
                }
                //Copy Data_MethodEvaluation
                generalData = GeneralData.finder.byId(session("dataGenaralData"));
                data_methodEvaluationList = Data_MethodEvaluation.finder.where().eq("generalData.id", generalData.getId()).findList();
                for (int y = 0; y < data_methodEvaluationList.size(); y++) {
                    List<Data_MethodEvaluation> numrow = Data_MethodEvaluation.data_methodEvaluationList();
                    numlist = numrow.size();
                    if (numrow.size() == 0) {
                        sId = "DMT-000001";
                    } else {
                        lastNum = numrow.get(numlist - 1).getId();
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
                    generalData = GeneralData.finder.byId(session("newGenId"));
                    data_methodEvaluation = new Data_MethodEvaluation();
                    data_methodEvaluation.setId(sId);
                    data_methodEvaluation.setEvaluationStrategy(data_methodEvaluationList.get(y).getEvaluationStrategy());
                    data_methodEvaluation.setGeneralData(generalData);
                    data_methodEvaluation.setDevelopmentalLearning(data_methodEvaluationList.get(y).getDevelopmentalLearning());
                    Data_MethodEvaluation.insert(data_methodEvaluation);
                }

                //Copyแผนการสอน
                generalData = GeneralData.finder.byId(session("dataGenaralData"));
                planTeachingDataList = PlanTeachingData.finder.where().eq("generalData.id", generalData.getId()).findList();
                for (int y = 0; y < planTeachingDataList.size(); y++) {
                    List<PlanTeachingData> numrow = PlanTeachingData.planTeachingDataList();
                    numlist = numrow.size();
                    if (numrow.size() == 0) {
                        sId = "PTD-000001";
                    } else {
                        lastNum = numrow.get(numlist - 1).getId();
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
                    generalData = GeneralData.finder.byId(session("newGenId"));
                    planTeachingData = new PlanTeachingData();
                    planTeachingData.setId(sId);
                    planTeachingData.setWeek(planTeachingDataList.get(y).getWeek());
                    planTeachingData.setHours(planTeachingDataList.get(y).getHours());
                    planTeachingData.setDetail(planTeachingDataList.get(y).getDetail());
                    planTeachingData.setMedia(planTeachingDataList.get(y).getMedia());
                    planTeachingData.setInstructor(planTeachingDataList.get(y).getInstructor());
                    planTeachingData.setGeneralData(generalData);
                    PlanTeachingData.insert(planTeachingData);
                }

                //Copyแผนการประเมินการสอน
                generalData = GeneralData.finder.byId(session("dataGenaralData"));
                evaluationPlanDataList = EvaluationPlanData.finder.where().eq("generalData.id", generalData.getId()).findList();
                for (int y = 0; y < evaluationPlanDataList.size(); y++) {
                    List<EvaluationPlanData> numrow = EvaluationPlanData.evaluationPlanDataList();
                    numlist = numrow.size();
                    if (numrow.size() == 0) {
                        sId = "PEV-000001";
                    } else {
                        lastNum = numrow.get(numlist - 1).getId();
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
                    generalData = GeneralData.finder.byId(session("newGenId"));
                    evaluationPlanData = new EvaluationPlanData();
                    evaluationPlanData.setId(sId);
                    String idPlanEvaluation = sId;
                    evaluationPlanData.setActivity(evaluationPlanDataList.get(y).getActivity());
                    evaluationPlanData.setLearningOutcome(evaluationPlanDataList.get(y).getLearningOutcome());
//                    evaluationPlanData.setEvaluationWeek(evaluationPlanDataList.get(y).getEvaluationWeek());
//                    evaluationPlanData.setEvaluationMethod(evaluationPlanDataList.get(y).getEvaluationMethod());
//                    evaluationPlanData.setRatingScale(evaluationPlanDataList.get(y).getRatingScale());
                    evaluationPlanData.setDetail(evaluationPlanDataList.get(y).getDetail());
                    evaluationPlanData.setGeneralData(generalData);
                    EvaluationPlanData.insert(evaluationPlanData);
                    detailPlanEvaluationList = DetailPlanEvaluation.finder.where().eq("evaluationPlanData.id",evaluationPlanDataList.get(y).getId()).findList();
                    for (int x=0;x<detailPlanEvaluationList.size();x++){
                        List<DetailPlanEvaluation> numrows = DetailPlanEvaluation.detailPlanEvaluationList();
                        numlist = numrows.size();
                        if (numrows.size() == 0) {
                            sId = "DES-000001";
                        } else {
                            lastNum = numrows.get(numlist - 1).getId();
                            lastNum = lastNum.substring(4);
                            idNum = Integer.parseInt(lastNum);
                            id = idNum + 1;
                            if (id < 10) {
                                sId = "DES-00000" + id;
                            } else if (id < 100) {
                                sId = "DES-0000" + id;
                            } else if (id < 1000) {
                                sId = "DES-000" + id;
                            } else if (id < 10000) {
                                sId = "DES-00" + id;
                            } else if (id < 100000) {
                                sId = "DES-0" + id;
                            } else {
                                sId = "DES-" + id;
                            }
                        }
                        detailPlanEvaluation = new DetailPlanEvaluation();
                        detailPlanEvaluation.setId(sId);
                        detailPlanEvaluation.setEvaluationStrategy(detailPlanEvaluationList.get(x).getEvaluationStrategy());
                        evaluationPlanData = EvaluationPlanData.finder.byId(idPlanEvaluation);
                        detailPlanEvaluation.setEvaluationPlanData(evaluationPlanData);
                        detailPlanEvaluation.setEvaluationWeek(detailPlanEvaluationList.get(x).getEvaluationWeek());
                        detailPlanEvaluation.setRatingScale(detailPlanEvaluationList.get(x).getRatingScale());
//                    evaluationPlanData.setEvaluationWeek(evaluationPlanDataList.get(y).getEvaluationWeek());
//                    evaluationPlanData.setEvaluationMethod(evaluationPlanDataList.get(y).getEvaluationMethod());
//                    evaluationPlanData.setRatingScale(evaluationPlanDataList.get(y).getRatingScale());
                        DetailPlanEvaluation.insert(detailPlanEvaluation );
                    }
                }
                //Copyเอกสารและตำราหลัก
                generalData = GeneralData.finder.byId(session("dataGenaralData"));
                mainDocumentList = MainDocument.finder.where().eq("generalData.id", generalData.getId()).findList();
                for (int y = 0; y < mainDocumentList.size(); y++) {
                    List<MainDocument> numrow = MainDocument.mainDocumentList();
                    numlist = numrow.size();
                    if (numrow.size() == 0) {
                        sId = "MDoc-000001";
                    } else {
                        lastNum = numrow.get(numlist - 1).getId();
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
                    generalData = GeneralData.finder.byId(session("newGenId"));
                    mainDocument = new MainDocument();
                    mainDocument.setId(sId);
                    mainDocument.setDetail(mainDocumentList.get(y).getDetail());
                    mainDocument.setGeneralData(generalData);
                    mainDocument.setCourse(course);
                    MainDocument.insert(mainDocument);
                }

                //Copyเอกสารและข้อมูลสำคัญ

                generalData = GeneralData.finder.byId(session("dataGenaralData"));
                importantDocumentList = ImportantDocument.finder.where().eq("generalData.id", generalData.getId()).findList();
                for (int y = 0; y < importantDocumentList.size(); y++) {
                    List<ImportantDocument> numrow = ImportantDocument.importantDocumentList();
                    numlist = numrow.size();
                    if (numrow.size() == 0) {
                        sId = "IDoc-000001";
                    } else {
                        lastNum = numrow.get(numlist - 1).getId();
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
                    generalData = GeneralData.finder.byId(session("newGenId"));
                    importantDocument = new ImportantDocument();
                    importantDocument.setId(sId);
                    importantDocument.setDetail(importantDocumentList.get(y).getDetail());
                    importantDocument.setGeneralData(generalData);
                    importantDocument.setCourse(course);
                    ImportantDocument.insert(importantDocument);
                }
                //Copy เอกสารและเอกสารเเละข้อมูลเเนะนำ
                generalData = GeneralData.finder.byId(session("dataGenaralData"));
                suggustionDocumentList = SuggustionDocument.finder.where().eq("generalData.id", generalData.getId()).findList();
                for (int y = 0; y < suggustionDocumentList.size(); y++) {
                    List<SuggustionDocument> numrow = SuggustionDocument.suggustionDocumentList();
                    numlist = numrow.size();
                    if (numrow.size() == 0) {
                        sId = "SDoc-000001";
                    } else {
                        lastNum = numrow.get(numlist - 1).getId();
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
                    generalData = GeneralData.finder.byId(session("newGenId"));
                    suggustionDocument = new SuggustionDocument();
                    suggustionDocument.setId(sId);
                    suggustionDocument.setDetail(suggustionDocumentList.get(y).getDetail());
                    suggustionDocument.setGeneralData(generalData);
                    suggustionDocument.setCourse(course);
                    SuggustionDocument.insert(suggustionDocument);
                }
                //Copy กลุยุทธ์การประเมินประสิทธิผลของรายวิชาโดยนักศึกษา
                generalData = GeneralData.finder.byId(session("dataGenaralData"));
                courseAssessmentStrategiesList = CourseAssessmentStrategies.finder.where().eq("generalData.id", generalData.getId()).findList();
                for (int y = 0; y < courseAssessmentStrategiesList.size(); y++) {
                    List<CourseAssessmentStrategies> numrow = CourseAssessmentStrategies.courseAssessmentStrategiesList();
                    numlist = numrow.size();
                    if (numrow.size() == 0) {
                        sId = "CAS-000001";
                    } else {
                        lastNum = numrow.get(numlist - 1).getId();
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
                    generalData = GeneralData.finder.byId(session("newGenId"));
                    courseAssessmentStrategies = new CourseAssessmentStrategies();
                    courseAssessmentStrategies.setId(sId);
                    courseAssessmentStrategies.setDetail(courseAssessmentStrategiesList.get(y).getDetail());
                    courseAssessmentStrategies.setGeneralData(generalData);
                    courseAssessmentStrategies.setCourse(course);
                    CourseAssessmentStrategies.insert(courseAssessmentStrategies);
                }
                //Copy กลยุทธ์การประเมินการสอน
                generalData = GeneralData.finder.byId(session("dataGenaralData"));
                teachingStrategiesDataList = TeachingStrategiesData.finder.where().eq("generalData.id", generalData.getId()).findList();
                for (int y = 0; y < teachingStrategiesDataList.size(); y++) {
                    List<TeachingStrategiesData> numrow = TeachingStrategiesData.teachingStrategiesDataList();
                    numlist = numrow.size();
                    if (numrow.size() == 0) {
                        sId = "TSD-000001";
                    } else {
                        lastNum = numrow.get(numlist - 1).getId();
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
                    generalData = GeneralData.finder.byId(session("newGenId"));
                    teachingStrategiesData = new TeachingStrategiesData();
                    teachingStrategiesData.setId(sId);
                    teachingStrategiesData.setDetail(teachingStrategiesDataList.get(y).getDetail());
                    teachingStrategiesData.setGeneralData(generalData);
                    teachingStrategiesData.setCourse(course);
                    TeachingStrategiesData.insert(teachingStrategiesData);
                }

                //Copy การสอบทวนมาตรฐานผลสัมฤทธิ์ของนักศึกษาในรายวิชา
                generalData = GeneralData.finder.byId(session("dataGenaralData"));
                standardReviewList = StandardReview.finder.where().eq("generalData.id", generalData.getId()).findList();
                for (int y = 0; y < standardReviewList.size(); y++) {
                    List<StandardReview> numrow = StandardReview.standardReviewList();
                    numlist = numrow.size();
                    if (numrow.size() == 0) {
                        sId = "STD-000001";
                    } else {
                        lastNum = numrow.get(numlist - 1).getId();
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
                    generalData = GeneralData.finder.byId(session("newGenId"));
                    standardReview = new StandardReview();
                    standardReview.setId(sId);
                    standardReview.setDetail(standardReviewList.get(y).getDetail());
                    standardReview.setGeneralData(generalData);
                    standardReview.setCourse(course);
                    StandardReview.insert(standardReview);
                }
                //Copy  การดำเนินการทบทวนเเละการวางแผนปรับปรุงประสิทธิผลของรายวิชา
                generalData = GeneralData.finder.byId(session("dataGenaralData"));
                reviewAndPlanList = ReviewAndPlan.finder.where().eq("generalData.id", generalData.getId()).findList();
                for (int y = 0; y < reviewAndPlanList.size(); y++) {
                    List<ReviewAndPlan> numrow = ReviewAndPlan.reviewAndPlanList();
                    numlist = numrow.size();
                    if (numrow.size() == 0) {
                        sId = "RAP-000001";
                    } else {
                        lastNum = numrow.get(numlist - 1).getId();
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
                    generalData = GeneralData.finder.byId(session("newGenId"));
                    reviewAndPlan = new ReviewAndPlan();
                    reviewAndPlan.setId(sId);
                    reviewAndPlan.setDetail(reviewAndPlanList.get(y).getDetail());
                    reviewAndPlan.setGeneralData(generalData);
                    reviewAndPlan.setCourse(course);
                    ReviewAndPlan.insert(reviewAndPlan);
                }
                return redirect("/showDoc3");
            }

        } else {
            flash("NoDoc", "ไม่สามารถทำการสำเนาเอกสารได้เนื่องจากไม่ได้มีการสร้างเอกสารไว้");
            return redirect("/selectCourse");
        }
        return redirect("/showDoc3");

    }
    public static List<GeneralData> generalDataList1 = new ArrayList<GeneralData>();

    public static Result showDoc3(){
        session("numMenu","Doc");
        if (session("userId")==null){
            flash("UserError","คุณไม่มีสิทธิ์เข้าใข้งานในส่วนนี้ กรุณาLogin");
            return ok(loginPage.render());
        }
            generalDataList = GeneralData.generalDataList();
            return selectCourseForDoc3();


    }

    public static Result selectCourseForDoc3() {
       List<Instructors> data1= Instructors.finder.where().and(Expr.eq("teacher.id", session("userId")), Expr.eq("status", "1")).eq("statusDoc","1").findList();
        List<Instructors> data2= Instructors.finder.where().and(Expr.eq("teacher.id", session("userId")), Expr.eq("status", "1")).eq("statusDoc",null).findList();
        generalDataList = GeneralData.generalDataList();
        return main(selectCourse.render(data2,generalDataList));
    }

    public static Result newDoc3(String Insid) {
        instructors = Instructors.finder.byId(Insid);
        generalDataList = GeneralData.finder.where().eq("course.id", instructors.getCourse().getId()).findList();
        if (generalDataList.size() != 0) {
            boolean found = false;
            for (int x = 0; x < generalDataList.size(); x++) {
                if (instructors.getTerm().equals(generalDataList.get(x).getInstructors().getTerm()) && instructors.getYear().equals(generalDataList.get(x).getInstructors().getYear())) {
                    flash("NotMakt", "ไม่สามารถสร้างเอกสารได้เนื่องจาก รายวิชานี้ของปีเเละเทอมนี้ มีการสร้างเอกสารไว้เเล้ว กรุณาตรวจสอบที่หน้ารายการเอกสาร");
                    found = true;
                    return redirect("/selectCourse");
                }
            }
                if (found == false) {
                    generalDataList = GeneralData.generalDataList();
                    int numlist = generalDataList.size();
                    int id, idNum;
                    String lastNum;
                    String sId;
                    if (generalDataList.size() == 0) {
                        sId = "Doc-000001";
                    } else {
                        lastNum = generalDataList.get(numlist - 1).getId();
                        lastNum = lastNum.substring(4);
                        idNum = Integer.parseInt(lastNum);
                        id = idNum + 1;
                        if (id < 10) {
                            sId = "Doc-00000" + id;
                        } else if (id < 100) {
                            sId = "Doc-0000" + id;
                        } else if (id < 1000) {
                            sId = "Doc-000" + id;
                        } else if (id < 10000) {
                            sId = "Doc-00" + id;
                        } else if (id < 100000) {
                            sId = "Doc-0" + id;
                        } else {
                            sId = "Doc-" + id;
                        }
                    }
                    course = Course.finder.byId(instructors.getCourse().getId());
                    teacher = Teacher.finder.byId(session("userId"));
                    generalData = new GeneralData();
                    generalData.setId(sId);
                    generalData.setCourse(course);
                    generalData.setInstructors(instructors);
                    generalData.setTeacher(teacher);
                    generalData.setTerm(instructors.getTerm());
                    generalData.setYear(instructors.getYear());
                    instructors.setStatusDoc("1");
                    Format formatter;
                    formatter = new SimpleDateFormat("EEEE ที่ dd เดือน MMMM พ.ศ. yyyy", new Locale("th", "TH"));
                    generalData.setDetail(formatter.format(new Date()));
                    Instructors.update(instructors);
                    GeneralData.insert(generalData);
                }
                return redirect("/showDoc3");

            }else{
            generalDataList = GeneralData.generalDataList();
            int numlist = generalDataList.size();
            int id, idNum;
            String lastNum;
            String sId;
            if (generalDataList.size() == 0) {
                sId = "Doc-000001";
            } else {
                lastNum = generalDataList.get(numlist - 1).getId();
                lastNum = lastNum.substring(4);
                idNum = Integer.parseInt(lastNum);
                id = idNum + 1;
                if (id < 10) {
                    sId = "Doc-00000" + id;
                } else if (id < 100) {
                    sId = "Doc-0000" + id;
                } else if (id < 1000) {
                    sId = "Doc-000" + id;
                } else if (id < 10000) {
                    sId = "Doc-00" + id;
                } else if (id < 100000) {
                    sId = "Doc-0" + id;
                } else {
                    sId = "Doc-" + id;
                }
            }
            course = Course.finder.byId(instructors.getCourse().getId());
            teacher = Teacher.finder.byId(session("userId"));
            generalData = new GeneralData();
            generalData.setId(sId);
            generalData.setCourse(course);
            generalData.setInstructors(instructors);
            generalData.setTeacher(teacher);
            generalData.setTerm(instructors.getTerm());
            generalData.setYear(instructors.getYear());
            instructors.setStatusDoc("1");
            Format formatter;
            formatter = new SimpleDateFormat("EEEE ที่ dd เดือน MMMM พ.ศ. yyyy", new Locale("th", "TH"));
            generalData.setDetail(formatter.format(new Date()));
            Instructors.update(instructors);
            GeneralData.insert(generalData);
            return redirect("/showDoc3");
        }
        }

    public static Result editDoc3(String id) {
        generalData = GeneralData.finder.byId(id);
        if (generalData != null) {
            return main(cat1.render(generalData));
        } else {
            flash("editURL", "มีการแก้ไข URL");
            return redirect("/showDoc3");
        }
    }

    public static List<DevelopmentalLearning> developmentalLearningList = new ArrayList<DevelopmentalLearning>();
    public static Result selectCat() {
        DynamicForm dataCat = Form.form().bindFromRequest();
        String plan,domainId,n;
        if (dataCat.get("namePlan") == null) {
            if (session().get("dataPlan") != null) {
                plan = session().get("dataPlan");
            } else {
                plan = "plan1";
            }
        } else {
            plan = dataCat.get("namePlan");
        }
        String nameCategory;
        if (dataCat.get("idDoc3") == null) {
            generalData = GeneralData.finder.byId(session("generalDataId"));
            nameCategory = session("nameCategory");
        } else {
            generalData = GeneralData.finder.byId(dataCat.get("idDoc3"));
            nameCategory = dataCat.get("nameCat");
        }
        if (dataCat.get("domainId")==null){
            if ( session().get("domainId")==null){
                    developmentalLearningList = DevelopmentalLearning.finder.where().eq("numDomain","1").findList();
                    domainId=developmentalLearningList.get(0).getId();
                n="1";
            }else{
                domainId = session().get("domainId");
                n=session().get("n");
            }
        }else{
            domainId = dataCat.get("domainId");
            n=dataCat.get("n");
        }
        if (nameCategory.equals("cat1")) {
            generalData = GeneralData.finder.byId(generalData.getId());
            return main(cat1.render(generalData));
        } else if (nameCategory.equals("cat2")) {
            generalData = GeneralData.finder.byId(generalData.getId());
            List<CourseObjectives> dataCourseOb = new ArrayList<CourseObjectives>();
            List<CourseDestination> dataCourseDes = new ArrayList<CourseDestination>();
            courseObjectivesList = CourseObjectives.finder.where().eq("generalData.id", generalData.getId()).findList();
            courseDestinationList = CourseDestination.finder.where().eq("generalData.id", generalData.getId()).findList();
            dataCourseOb = CourseObjectives.finder.where().eq("generalData.id", generalData.getId()).findList();
            dataCourseDes = CourseDestination.finder.where().eq("generalData.id", generalData.getId()).findList();
            return main(cat2.render(generalData, courseDestinationList, courseObjectivesList, dataCourseDes, dataCourseOb));
        } else if (nameCategory.equals("cat3")) {
            generalData = GeneralData.finder.byId(generalData.getId());
            dataHours = DataHours.finder.where().eq("generalData.id", generalData.getId()).findUnique();
            return main(category3.render(generalData, dataHours));
        } else if (nameCategory.equals("cat4")) {
            generalData = GeneralData.finder.byId(generalData.getId());
            List<DevelopmentalLearning> allDomain =  new ArrayList<DevelopmentalLearning>();
            List<Curriculum> domainMapping = new ArrayList<Curriculum>();
            List<TeachingStrategies> listTeach = new ArrayList<TeachingStrategies>();
            List<EvaluationStrategy> listEvaluation = new ArrayList<EvaluationStrategy>();
            allDomain = DevelopmentalLearning.finder.where().eq("syllabusCourse.id",generalData.getCourse().getGroupCourse().getCategoryCourse().getSyllabusCourse().getId()).findList();
            DevelopmentalLearning findIndex;
            String numDoamin="";
                for (int i= 0 ; i<allDomain.size();i++){
                    findIndex= DevelopmentalLearning.finder.byId(domainId);
                    if (findIndex.getNumDomain().equals(allDomain.get(i).getNumDomain())){
                        numDoamin = allDomain.get(i).getNumDomain();
                    break;
                    }
                }
            domainMapping = Curriculum.finder.where().or(Expr.eq("importance", "1"), Expr.eq("importance", "2")).and(Expr.eq("course.id", generalData.getCourse().getId()),Expr.eq("developmentalLearning.numDomain",numDoamin)).eq("developmentalLearning.syllabusCourse.id",generalData.getCourse().getGroupCourse().getCategoryCourse().getSyllabusCourse().getId()).findList();
            listTeach = TeachingStrategies.finder.where().eq("developmentalLearning.id",domainId).findList();
            listEvaluation = EvaluationStrategy.finder.where().eq("developmentalLearning.id",domainId).findList();
            data_listDevelopmentList = Data_ListDevelopment.finder.where().and(Expr.eq("generalData.id", generalData.getId()),Expr.eq("developmentalLearning.id",domainId)).findList();
            data_methodTeachingList = Data_MethodTeaching.finder.where().and(Expr.eq("generalData.id", generalData.getId()),Expr.eq("developmentalLearning.id",domainId)).findList();
            data_methodEvaluationList = Data_MethodEvaluation.finder.where().and(Expr.eq("generalData.id", generalData.getId()),Expr.eq("developmentalLearning.id",domainId)).findList();
            return main(category4.render(generalData,allDomain,domainId,numDoamin,n,
                    domainMapping,listTeach,listEvaluation,data_listDevelopmentList,data_methodTeachingList,data_methodEvaluationList));
        } else if (nameCategory.equals("cat5")) {
            generalData = GeneralData.finder.byId(generalData.getId());
            planTeachingDataList = PlanTeachingData.finder.where().eq("generalData.id", generalData.getId()).findList();
            evaluationPlanDataList = EvaluationPlanData.finder.where().eq("generalData.id", generalData.getId()).findList();
            List<EvaluationStrategy> evaluationStrategyList = EvaluationStrategy.evaluationStrategyList();
            return main(category5.render(generalData, planTeachingDataList, evaluationPlanDataList, plan,evaluationStrategyList));
        } else if (nameCategory.equals("cat6")) {
            generalData = GeneralData.finder.byId(generalData.getId());
            mainDocumentList = MainDocument.finder.where().eq("generalData.id", generalData.getId()).findList();
            importantDocumentList = ImportantDocument.finder.where().eq("generalData.id", generalData.getId()).findList();
            suggustionDocumentList = SuggustionDocument.finder.where().eq("generalData.id", generalData.getId()).findList();
            return main(category6.render(generalData, mainDocumentList, importantDocumentList, suggustionDocumentList));
        } else if (nameCategory.equals("cat7")) {
            generalData = GeneralData.finder.byId(generalData.getId());
            courseAssessmentStrategiesList = CourseAssessmentStrategies.finder.where().eq("generalData.id", generalData.getId()).findList();
            teachingStrategiesDataList = TeachingStrategiesData.finder.where().eq("generalData.id", generalData.getId()).findList();
            improveTeachingDataList = ImproveTeachingData.finder.where().eq("generalData.id", generalData.getId()).findList();
            standardReviewList = StandardReview.finder.where().eq("generalData.id", generalData.getId()).findList();
            reviewAndPlanList = ReviewAndPlan.finder.where().eq("generalData.id", generalData.getId()).findList();
            return main(category7.render(generalData, courseAssessmentStrategiesList, teachingStrategiesDataList, improveTeachingDataList, standardReviewList, reviewAndPlanList));
        } else {
            return ok();
        }
    }

    public static Result deleteDoc3(String id) {
        generalData = GeneralData.finder.byId(id);
        if (generalData != null) {
            GeneralData.delete(generalData);
            instructors = generalData.getInstructors();
            instructors.setStatusDoc(null);
            Instructors.update(instructors);
        }
        return redirect("/showDoc3");
    }
}

