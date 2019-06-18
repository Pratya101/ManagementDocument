package models;

import play.db.ebean.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Coffee on 16/11/2561.
 */
@Entity
@Table(name = "tbGeneralData")
public class GeneralData extends Model {

    @Id
    private String id;
    private String suggestion, detail,year,term;

    @ManyToOne
    private Course course;

    @ManyToOne
    private Instructors instructors;

    @ManyToOne
    private Teacher teacher;


    @OneToMany(mappedBy = "generalData", cascade = CascadeType.ALL)
    private List<CourseObjectives> courseObjectivesList = new ArrayList<CourseObjectives>();

    @OneToMany(mappedBy = "generalData", cascade = CascadeType.ALL)
    private List<CourseDestination> courseDestinationList = new ArrayList<CourseDestination>();


    @OneToMany(mappedBy = "generalData", cascade = CascadeType.ALL)
    private List<DataHours> dataHoursList = new ArrayList<DataHours>();

    @OneToMany(mappedBy = "generalData", cascade = CascadeType.ALL)
    private List<PlanTeachingData> planTeachingDataList = new ArrayList<PlanTeachingData>();

    @OneToMany(mappedBy = "generalData", cascade = CascadeType.ALL)
    private List<EvaluationPlanData> evaluationPlanDataList = new ArrayList<EvaluationPlanData>();

    @OneToMany(mappedBy = "generalData", cascade = CascadeType.ALL)
    private List<MainDocument> mainDocumentList = new ArrayList<MainDocument>();

    @OneToMany(mappedBy = "generalData", cascade = CascadeType.ALL)
    private List<ImportantDocument> importantDocumentList = new ArrayList<ImportantDocument>();

    @OneToMany(mappedBy = "generalData", cascade = CascadeType.ALL)
    private List<SuggustionDocument> suggustionDocumentList = new ArrayList<SuggustionDocument>();

    @OneToMany(mappedBy = "generalData", cascade = CascadeType.ALL)
    private List<CourseAssessmentStrategies> courseAssessmentStrategiesList = new ArrayList<CourseAssessmentStrategies>();

    @OneToMany(mappedBy = "generalData", cascade = CascadeType.ALL)
    private List<TeachingStrategiesData> teachingStrategiesDataList = new ArrayList<TeachingStrategiesData>();

    @OneToMany(mappedBy = "generalData", cascade = CascadeType.ALL)
    private List<ImproveTeachingData> improveTeachingDataList = new ArrayList<ImproveTeachingData>();

    @OneToMany(mappedBy = "generalData", cascade = CascadeType.ALL)
    private List<StandardReview> standardReviews = new ArrayList<StandardReview>();

    @OneToMany(mappedBy = "generalData", cascade = CascadeType.ALL)
    private List<ReviewAndPlan> reviewAndPlanList = new ArrayList<ReviewAndPlan>();

    @OneToMany(mappedBy = "generalData",cascade = CascadeType.ALL)
    private List<Data_ListDevelopment> data_listDevelopmentList = new ArrayList<>();

    @OneToMany(mappedBy = "generalData",cascade = CascadeType.ALL)
    private List<Data_MethodTeaching> data_methodTeachingList = new ArrayList<Data_MethodTeaching>();

    @OneToMany(mappedBy = "generalData",cascade = CascadeType.ALL)
    private List<Data_MethodEvaluation>  data_methodEvaluationList = new ArrayList<Data_MethodEvaluation>();

    public GeneralData() {
    }

    public GeneralData(String id, String suggestion, String detail, Course course, Instructors instructors, Teacher teacher,String term,String year) {
        this.id = id;
        this.suggestion = suggestion;
        this.detail = detail;
        this.course = course;
        this.instructors = instructors;
        this.teacher = teacher;
        this.term = term;
        this.year = year;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Instructors getInstructors() {
        return instructors;
    }

    public void setInstructors(Instructors instructors) {
        this.instructors = instructors;
    }

    public List<CourseObjectives> getCourseObjectivesList() {
        return courseObjectivesList;
    }

    public void setCourseObjectivesList(List<CourseObjectives> courseObjectivesList) {
        this.courseObjectivesList = courseObjectivesList;
    }

    public List<CourseDestination> getCourseDestinationList() {
        return courseDestinationList;
    }

    public void setCourseDestinationList(List<CourseDestination> courseDestinationList) {
        this.courseDestinationList = courseDestinationList;
    }

    public void setDataHoursList(List<DataHours> dataHoursList) {
        this.dataHoursList = dataHoursList;
    }

    public void setPlanTeachingDataList(List<PlanTeachingData> planTeachingDataList) {
        this.planTeachingDataList = planTeachingDataList;
    }

    public List<EvaluationPlanData> getEvaluationPlanDataList() {
        return evaluationPlanDataList;
    }

    public void setEvaluationPlanDataList(List<EvaluationPlanData> evaluationPlanDataList) {
        this.evaluationPlanDataList = evaluationPlanDataList;
    }

    public void setMainDocumentList(List<MainDocument> mainDocumentList) {
        this.mainDocumentList = mainDocumentList;
    }

    public void setImportantDocumentList(List<ImportantDocument> importantDocumentList) {
        this.importantDocumentList = importantDocumentList;
    }

    public void setSuggustionDocumentList(List<SuggustionDocument> suggustionDocumentList) {
        this.suggustionDocumentList = suggustionDocumentList;
    }

    public void setCourseAssessmentStrategiesList(List<CourseAssessmentStrategies> courseAssessmentStrategiesList) {
        this.courseAssessmentStrategiesList = courseAssessmentStrategiesList;
    }

    public void setTeachingStrategiesDataList(List<TeachingStrategiesData> teachingStrategiesDataList) {
        this.teachingStrategiesDataList = teachingStrategiesDataList;
    }

    public void setImproveTeachingDataList(List<ImproveTeachingData> improveTeachingDataList) {
        this.improveTeachingDataList = improveTeachingDataList;
    }

    public void setStandardReviews(List<StandardReview> standardReviews) {
        this.standardReviews = standardReviews;
    }

    public void setReviewAndPlanList(List<ReviewAndPlan> reviewAndPlanList) {
        this.reviewAndPlanList = reviewAndPlanList;
    }

    public void setData_listDevelopmentList(List<Data_ListDevelopment> data_listDevelopmentList) {
        this.data_listDevelopmentList = data_listDevelopmentList;
    }

    public void setData_methodTeachingList(List<Data_MethodTeaching> data_methodTeachingList) {
        this.data_methodTeachingList = data_methodTeachingList;
    }

    public void setData_methodEvaluationList(List<Data_MethodEvaluation> data_methodEvaluationList) {
        this.data_methodEvaluationList = data_methodEvaluationList;
    }

    public List<TeachingStrategiesData> getTeachingStrategiesDataList() {
        return teachingStrategiesDataList;
    }

    public List<ImproveTeachingData> getImproveTeachingDataList() {
        return improveTeachingDataList;
    }

    public List<StandardReview> getStandardReviews() {
        return standardReviews;
    }

    public List<ReviewAndPlan> getReviewAndPlanList() {
        return reviewAndPlanList;
    }

    public List<DataHours> getDataHoursList() {
        return dataHoursList;
    }

    public List<PlanTeachingData> getPlanTeachingDataList() {
        return planTeachingDataList;
    }

    public List<MainDocument> getMainDocumentList() {
        return mainDocumentList;
    }

    public List<ImportantDocument> getImportantDocumentList() {
        return importantDocumentList;
    }

    public List<SuggustionDocument> getSuggustionDocumentList() {
        return suggustionDocumentList;
    }

    public List<CourseAssessmentStrategies> getCourseAssessmentStrategiesList() {
        return courseAssessmentStrategiesList;
    }

    public List<Data_ListDevelopment> getData_listDevelopmentList() {
        return data_listDevelopmentList;
    }

    public List<Data_MethodTeaching> getData_methodTeachingList() {
        return data_methodTeachingList;
    }

    public List<Data_MethodEvaluation> getData_methodEvaluationList() {
        return data_methodEvaluationList;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public static Finder<String, GeneralData> finder = new Finder<String, GeneralData>(String.class, GeneralData.class);

    public static List<GeneralData> generalDataList() {
        return finder.all();
    }

    public static void insert(GeneralData generalData) {
        generalData.save();
    }

    public static void update(GeneralData generalData) {
        generalData.update();
    }

    public static void delete(GeneralData generalData) {
        generalData.delete();
    }
}
