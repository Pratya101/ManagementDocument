package models;

import play.db.ebean.Model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Data_ListDevelopment")
/**
 * Created by coffee on 5/21/2019.
 */
public class Data_ListDevelopment extends Model {
    @Id
    private String id;
    private String numDomain;

    @ManyToOne
    private Curriculum curriculum;

    @ManyToOne
    private GeneralData generalData;

    @ManyToOne
    DevelopmentalLearning developmentalLearning;

    public Data_ListDevelopment() {
    }


    public Data_ListDevelopment(String id, String numDomain, Curriculum curriculum, GeneralData generalData, DevelopmentalLearning developmentalLearning) {
        this.id = id;
        this.numDomain = numDomain;
        this.curriculum = curriculum;
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

    public Curriculum getCurriculum() {
        return curriculum;
    }

    public void setCurriculum(Curriculum curriculum) {
        this.curriculum = curriculum;
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

    public static Finder<String,Data_ListDevelopment> finder = new Finder<String, Data_ListDevelopment>(String.class,Data_ListDevelopment.class);

    public static List<Data_ListDevelopment> data_listDevelopmentList (){
        return finder.all();
    }
    public static void insert (Data_ListDevelopment data_listDevelopment){
        data_listDevelopment.save();
    }
    public static void update (Data_ListDevelopment data_listDevelopment){
        data_listDevelopment.update();
    }
    public static void  delte (Data_ListDevelopment data_listDevelopment){
        data_listDevelopment.delete();
    }
}
