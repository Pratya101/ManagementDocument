package models;

import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "tbStandardReview")
public class StandardReview extends Model {

    @Id
    private String id;
    private String detail;

    @ManyToOne
    private GeneralData generalData;

    @ManyToOne
    Course course;

    public StandardReview() {
    }

    public StandardReview(String id, String detail, GeneralData generalData, Course course) {
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

    public static Finder<String, StandardReview> finder = new Finder<String, StandardReview>(String.class, StandardReview.class);

    public static List<StandardReview> standardReviewList (){
        return     finder.all();
    }

    public static void insert (StandardReview standardReview){
        standardReview.save();
    }
    public static void update (StandardReview standardReview){
        standardReview.update();
    }
    public static void delete (StandardReview standardReview){
        standardReview.delete();
    }
}