package models;

import play.db.ebean.Model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by PRATYA on 12/19/2018.
 */
@Entity
@Table (name = "tbCourseDestination")
public class CourseDestination extends Model {
    @Id
    private String id;
    @Lob
    private String detail;

    @ManyToOne
    private GeneralData generalData;

    @ManyToOne
    private Course course;

    public CourseDestination() {
    }

    public CourseDestination(String id, String detail, GeneralData generalData, Course course) {
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

    public static Finder<String, CourseDestination> finder = new Finder<String, CourseDestination>(String.class, CourseDestination.class);

    public static List<CourseDestination> courseDestinationList (){
        return finder.all();
    }
    public static void insert (CourseDestination courseDestination){
        courseDestination.save();
    }
    public static void update (CourseDestination courseDestination){
        courseDestination.update();
    }
    public static void delete (CourseDestination courseDestination){
    courseDestination.delete();
    }
}

