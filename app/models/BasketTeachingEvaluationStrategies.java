package models;

/**
 * Created by coffee on 5/14/2019.
 */
public class BasketTeachingEvaluationStrategies {
    private String id,detail;
    private GeneralData generalData;
    private Course course;

    public BasketTeachingEvaluationStrategies() {
    }

    public BasketTeachingEvaluationStrategies(String id, String detail, GeneralData generalData, Course course) {
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
}
