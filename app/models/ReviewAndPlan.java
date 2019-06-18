package models;

import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "tbReviewAndPlan")
public class ReviewAndPlan extends Model {

    @Id
    private String id;

    private String detail;
    @ManyToOne
    private GeneralData generalData;
    @ManyToOne
    private Course course;

    public ReviewAndPlan() {
    }

    public ReviewAndPlan(String id, String detail, GeneralData generalData, Course course) {
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
    public static Finder<String, ReviewAndPlan> finder = new Finder<String, ReviewAndPlan>(String.class, ReviewAndPlan.class);

    public static List<ReviewAndPlan> reviewAndPlanList (){
        return finder.all();
    }

    public static  void insert (ReviewAndPlan reviewAndPlan){
        reviewAndPlan.save();
    }
    public static  void  update  (ReviewAndPlan reviewAndPlan){
        reviewAndPlan.update();
    }
    public static  void  delete (ReviewAndPlan reviewAndPlan){
        reviewAndPlan.delete();
    }
}