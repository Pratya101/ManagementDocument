package models;

import play.db.ebean.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by coffee on 5/23/2019.
 */
@Entity
@Table(name = "tbDetailPlanEvaluation")
public class DetailPlanEvaluation extends Model {
    @Id
    private String id;
    private String evaluationWeek, ratingScale;

    @ManyToOne
    private EvaluationStrategy evaluationStrategy;

    @ManyToOne
        private EvaluationPlanData evaluationPlanData;

    public DetailPlanEvaluation() {
    }


    public DetailPlanEvaluation(String id, String evaluationWeek, String ratingScale, EvaluationStrategy evaluationStrategy, EvaluationPlanData evaluationPlanData) {
        this.id = id;
        this.evaluationWeek = evaluationWeek;
        this.ratingScale = ratingScale;
        this.evaluationStrategy = evaluationStrategy;
        this.evaluationPlanData = evaluationPlanData;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEvaluationWeek() {
        return evaluationWeek;
    }

    public void setEvaluationWeek(String evaluationWeek) {
        this.evaluationWeek = evaluationWeek;
    }

    public String getRatingScale() {
        return ratingScale;
    }

    public void setRatingScale(String ratingScale) {
        this.ratingScale = ratingScale;
    }

    public EvaluationStrategy getEvaluationStrategy() {
        return evaluationStrategy;
    }

    public void setEvaluationStrategy(EvaluationStrategy evaluationStrategy) {
        this.evaluationStrategy = evaluationStrategy;
    }

    public EvaluationPlanData getEvaluationPlanData() {
        return evaluationPlanData;
    }

    public void setEvaluationPlanData(EvaluationPlanData evaluationPlanData) {
        this.evaluationPlanData = evaluationPlanData;
    }

    public static Finder<String,DetailPlanEvaluation> finder = new Finder<String, DetailPlanEvaluation>(String.class,DetailPlanEvaluation.class);

    public static List<DetailPlanEvaluation> detailPlanEvaluationList (){
        return finder.all();
    }
    public static void insert (DetailPlanEvaluation detailPlanEvaluation){
        detailPlanEvaluation.save();
    }
    public static void update (DetailPlanEvaluation detailPlanEvaluation){
        detailPlanEvaluation.update();
    }
    public static void delete (DetailPlanEvaluation detailPlanEvaluation){
        detailPlanEvaluation.delete();
    }

}
