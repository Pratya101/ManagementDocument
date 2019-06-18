package models;

import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "Data_MethodEvaluation")

/**
 * Created by coffee on 5/21/2019.
 */
public class Data_MethodEvaluation extends Model {
    @Id
    private String id;
    private String numDomain;
    @ManyToOne
    private EvaluationStrategy evaluationStrategy;
    @ManyToOne
    private GeneralData generalData;
    @ManyToOne
    DevelopmentalLearning developmentalLearning;

    public Data_MethodEvaluation() {
    }

    public Data_MethodEvaluation(String id, String numDomain, EvaluationStrategy evaluationStrategy, GeneralData generalData, DevelopmentalLearning developmentalLearning) {
        this.id = id;
        this.numDomain = numDomain;
        this.evaluationStrategy = evaluationStrategy;
        this.generalData = generalData;
        this.developmentalLearning = developmentalLearning;
    }

    public String getNumDomain() {
        return numDomain;
    }

    public void setNumDomain(String numDomain) {
        this.numDomain = numDomain;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public EvaluationStrategy getEvaluationStrategy() {
        return evaluationStrategy;
    }

    public void setEvaluationStrategy(EvaluationStrategy evaluationStrategy) {
        this.evaluationStrategy = evaluationStrategy;
    }

    public GeneralData getGeneralData() {
        return generalData;
    }

    public void setGeneralData(GeneralData generalData) {
        this.generalData = generalData;
    }

    public DevelopmentalLearning getDevelopmentalLearning() {
        return developmentalLearning;
    }

    public void setDevelopmentalLearning(DevelopmentalLearning developmentalLearning) {
        this.developmentalLearning = developmentalLearning;
    }

    public static Finder<String, Data_MethodEvaluation> finder = new Finder<String, Data_MethodEvaluation>(String.class, Data_MethodEvaluation.class);
    public static List<Data_MethodEvaluation> data_methodEvaluationList (){
        return finder.all();
    }

    public static void insert(Data_MethodEvaluation data_methodEvaluation) {
        data_methodEvaluation.save();
    }

    public static void update(Data_MethodEvaluation data_methodEvaluation) {
        data_methodEvaluation.update();
    }

    public static void delete(Data_MethodEvaluation data_methodEvaluation) {
        data_methodEvaluation.delete();
    }
}
