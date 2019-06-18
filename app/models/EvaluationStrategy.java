package models;

import play.db.ebean.Model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by PRATYA on 11/22/2018.
 */
@Entity
@Table(name = "tbEvaluationStrategy")
public class EvaluationStrategy extends Model {
    @Id
    private String id;
    @Lob
    private String evaluationMethod;

    @ManyToOne
    private DevelopmentalLearning developmentalLearning;

    public EvaluationStrategy() {
    }

    public EvaluationStrategy(String id, String evaluationMethod, DevelopmentalLearning developmentalLearning) {
        this.id = id;
        this.evaluationMethod = evaluationMethod;
        this.developmentalLearning = developmentalLearning;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEvaluationMethod() {
        return evaluationMethod;
    }

    public void setEvaluationMethod(String evaluationMethod) {
        this.evaluationMethod = evaluationMethod;
    }

    public DevelopmentalLearning getDevelopmentalLearning() {
        return developmentalLearning;
    }

    public void setDevelopmentalLearning(DevelopmentalLearning developmentalLearning) {
        this.developmentalLearning = developmentalLearning;
    }

    public static Finder<String, EvaluationStrategy> finder = new Finder<String, EvaluationStrategy>(String.class, EvaluationStrategy.class);

    public static List<EvaluationStrategy> evaluationStrategyList() {
        return finder.all();
    }

    public static void insert(EvaluationStrategy evaluationStrategy) {
        evaluationStrategy.save();
    }

    public static void update(EvaluationStrategy evaluationStrategy) {
        evaluationStrategy.update();
    }

    public static void delete(EvaluationStrategy evaluationStrategy) {
        evaluationStrategy.delete();
    }

    public static List<EvaluationStrategy> evaluationStrategyOnly (String id){
        return finder.where().eq("developmentalLearning.id",id).findList();
    }
}
