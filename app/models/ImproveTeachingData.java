package models;

import play.db.ebean.Model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by coffee on 5/14/2019.
 */
@Entity
@Table(name = "tbImproveTeachingData")
public class ImproveTeachingData extends Model {
    @Id
    private String id;
    @Lob
    private String detail;
    @ManyToOne
    private GeneralData generalData;
    @ManyToOne
    private Course course;

    public ImproveTeachingData() {
    }

    public ImproveTeachingData(String id, String detail, GeneralData generalData, Course course) {
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
    public static Finder<String, ImproveTeachingData> finder = new Finder<String, ImproveTeachingData>(String.class, ImproveTeachingData.class);

    public static List<ImproveTeachingData> improveTeachingDataList (){
        return  finder.all();
    }
    public static void insert (ImproveTeachingData improveTeachingData){
        improveTeachingData.save();
    }
    public static void update (ImproveTeachingData improveTeachingData){
        improveTeachingData.update();
    }

    public static void delete (ImproveTeachingData improveTeachingData){
    improveTeachingData.delete();
    }
}
