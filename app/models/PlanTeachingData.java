package models;

import play.db.ebean.Model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by coffee on 5/10/2019.
 */
@Entity
@Table(name = "tbPlanTeachingData")
public class PlanTeachingData extends Model {
    @Id
    private String id;
    private String week, hours, media, instructor;
    @Lob
    private String detail;
    @ManyToOne
    private GeneralData generalData;

    public PlanTeachingData() {
    }

    public PlanTeachingData(String id, String week, String hours, String media, String instructor, String detail, GeneralData generalData) {
        this.id = id;
        this.week = week;
        this.hours = hours;
        this.media = media;
        this.instructor = instructor;
        this.detail = detail;
        this.generalData = generalData;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public String getMedia() {
        return media;
    }

    public void setMedia(String media) {
        this.media = media;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public GeneralData getGeneralData() {
        return generalData;
    }

    public void setGeneralData(GeneralData generalData) {
        this.generalData = generalData;
    }

    public static Finder<String, PlanTeachingData> finder = new Finder<String, PlanTeachingData>(String.class, PlanTeachingData.class);

    public static List<PlanTeachingData> planTeachingDataList() {
        return finder.all();
    }

    public static void insert(PlanTeachingData planTeachingData) {
        planTeachingData.save();
    }

    public static void update(PlanTeachingData planTeachingData) {
        planTeachingData.update();
    }

    public static void delete(PlanTeachingData planTeachingData) {
        planTeachingData.delete();
    }
}
