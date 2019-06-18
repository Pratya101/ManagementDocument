package models;

import play.db.ebean.Model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by PRATYA on 11/22/2018.
 */
@Entity
@Table(name = "tbTeachingStrategiesData")
public class TeachingStrategiesData extends Model {
    @Id
    private String id;
    @Lob
    private String detail;
    @ManyToOne
    private GeneralData generalData;
    @ManyToOne
    private Course course;

    public TeachingStrategiesData() {
    }

    public TeachingStrategiesData(String id, String detail, GeneralData generalData, Course course) {
        this.id = id;
        this.detail = detail;
        this.generalData = generalData;
        this.course = course;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public static Finder<String, TeachingStrategiesData> finder = new Finder<String, TeachingStrategiesData>(String.class, TeachingStrategiesData.class);

    public static List<TeachingStrategiesData> teachingStrategiesDataList() {
        return finder.all();
    }

    public static void insert(TeachingStrategiesData teachingStrategiesData) {
        teachingStrategiesData.save();
    }

    public static void update(TeachingStrategiesData teachingStrategiesData) {
        teachingStrategiesData.update();
    }

    public static void delete(TeachingStrategiesData teachingStrategiesData) {
        teachingStrategiesData.delete();
    }
}
