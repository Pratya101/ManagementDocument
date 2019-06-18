package models;

import play.db.ebean.Model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by PRATYA on 11/22/2018.
 */
@Entity
@Table(name = "tbTeachingStrategies")
public class TeachingStrategies extends Model {
    @Id
    private String id;
    @Lob
    private String teachingMethods;

    @ManyToOne
    private DevelopmentalLearning developmentalLearning;

    public TeachingStrategies() {
    }

    public TeachingStrategies(String id, String teachingMethods, DevelopmentalLearning developmentalLearning) {
        this.id = id;
        this.teachingMethods = teachingMethods;
        this.developmentalLearning = developmentalLearning;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTeachingMethods() {
        return teachingMethods;
    }

    public void setTeachingMethods(String teachingMethods) {
        this.teachingMethods = teachingMethods;
    }

    public DevelopmentalLearning getDevelopmentalLearning() {
        return developmentalLearning;
    }

    public void setDevelopmentalLearning(DevelopmentalLearning developmentalLearning) {
        this.developmentalLearning = developmentalLearning;
    }

    public static Finder<String, TeachingStrategies> finder = new Finder<String, TeachingStrategies>(String.class, TeachingStrategies.class);

    public static List<TeachingStrategies> teachingStrategiesList() {
        return finder.all();
    }

    public static void insert(TeachingStrategies teachingStrategies) {
        teachingStrategies.save();
    }

    public static void update(TeachingStrategies teachingStrategies) {
        teachingStrategies.update();
    }

    public static void delete(TeachingStrategies teachingStrategies) {
        teachingStrategies.delete();
    }

    public static List<TeachingStrategies> teachingStrategiesOnly (String id){
        return finder.where().eq("developmentalLearning.id",id).findList();
    }

}
