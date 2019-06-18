package models;

import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.List;

/**
 * Created by PRATYA on 12/19/2018.
 */
@Entity
@Table(name = "tbCourseAssessmentStrategies")
public class CourseAssessmentStrategies extends Model {
    @Id
    private String id;
    private String detail;
    @ManyToOne
    private GeneralData generalData;

    @ManyToOne
    private Course course;

    public CourseAssessmentStrategies() {
    }

    public CourseAssessmentStrategies(String id, String detail, GeneralData generalData, Course course) {
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

    public static Finder<String, CourseAssessmentStrategies> finder = new Finder<String, CourseAssessmentStrategies>(String.class, CourseAssessmentStrategies.class);

    public static List<CourseAssessmentStrategies> courseAssessmentStrategiesList() {
        return finder.all();
    }

    public static void insert(CourseAssessmentStrategies courseAssessmentStrategies) {
        courseAssessmentStrategies.save();
    }

    public static void update(CourseAssessmentStrategies courseAssessmentStrategies) {
        courseAssessmentStrategies.update();
    }

    public static void delete(CourseAssessmentStrategies courseAssessmentStrategies) {
        courseAssessmentStrategies.delete();
    }
}
