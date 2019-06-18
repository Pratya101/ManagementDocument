package models;

import play.db.ebean.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by PRATYA on 11/22/2018.
 */
@Entity
@Table(name = "tbEvaluationPlanData")
public class EvaluationPlanData extends Model {

    @Id
    private String id;
        private String learningOutcome, activity,detail;

    @ManyToOne
    private GeneralData generalData;

    @OneToMany(mappedBy = "evaluationPlanData",cascade = CascadeType.ALL)
    private List<DetailPlanEvaluation>  detailPlanEvaluations = new ArrayList<DetailPlanEvaluation>();


    public EvaluationPlanData() {
    }

    public EvaluationPlanData(String id, String learningOutcome, String activity, String detail, GeneralData generalData) {
        this.id = id;
        this.learningOutcome = learningOutcome;
        this.activity = activity;
        this.detail = detail;
        this.generalData = generalData;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLearningOutcome() {
        return learningOutcome;
    }

    public void setLearningOutcome(String learningOutcome) {
        this.learningOutcome = learningOutcome;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public GeneralData getGeneralData() {
        return generalData;
    }

    public void setGeneralData(GeneralData generalData) {
        this.generalData = generalData;
    }

    public List<DetailPlanEvaluation> getDetailPlanEvaluations() {
        return detailPlanEvaluations;
    }

    public void setDetailPlanEvaluations(List<DetailPlanEvaluation> detailPlanEvaluations) {
        this.detailPlanEvaluations = detailPlanEvaluations;
    }

    public static Finder<String, EvaluationPlanData> finder = new Finder<String, EvaluationPlanData>(String.class, EvaluationPlanData.class);

    public static List<EvaluationPlanData> evaluationPlanDataList() {
        return finder.all();
    }

    public static void insert(EvaluationPlanData evaluationPlanData) {
        evaluationPlanData.save();
    }

    public static void update(EvaluationPlanData evaluationPlanData) {
        evaluationPlanData.update();
    }

    public static void delete(EvaluationPlanData evaluationPlanData) {
        evaluationPlanData.delete();
    }

}
