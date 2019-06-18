package models;

import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "Data_MethodTeaching")
/**
 * Created by coffee on 5/21/2019.
 */
public class Data_MethodTeaching extends Model {
@Id
    private String id;
    private String numDomain;
@ManyToOne
    private TeachingStrategies teachingStrategies;
    @ManyToOne
    private GeneralData generalData;
    @ManyToOne
    DevelopmentalLearning developmentalLearning;

    public Data_MethodTeaching() {
    }

    public Data_MethodTeaching(String id, String numDomain, TeachingStrategies teachingStrategies, GeneralData generalData, DevelopmentalLearning developmentalLearning) {
        this.id = id;
        this.numDomain = numDomain;
        this.teachingStrategies = teachingStrategies;
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

    public TeachingStrategies getTeachingStrategies() {
        return teachingStrategies;
    }

    public void setTeachingStrategies(TeachingStrategies teachingStrategies) {
        this.teachingStrategies = teachingStrategies;
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

    public static Finder<String,Data_MethodTeaching> finder = new Finder<String, Data_MethodTeaching>(String.class,Data_MethodTeaching.class);

    public static List<Data_MethodTeaching> data_methodTeachingList (){
        return  finder.all();
    }

    public static void insert (Data_MethodTeaching data_methodTeaching){
        data_methodTeaching.save();
    }

    public static void update   (Data_MethodTeaching data_methodTeaching){
        data_methodTeaching.update();
    }
    public static void delete (Data_MethodTeaching data_methodTeaching){
        data_methodTeaching.delete();
    }


}
