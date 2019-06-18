package models;

public class BasketMainDoc {
    private  String id,detail;
    private GeneralData generalDatal;
    private Course course;

    public BasketMainDoc() {
    }

    public BasketMainDoc(String id, String detail, GeneralData generalDatal, Course course) {
        this.id = id;
        this.detail = detail;
        this.generalDatal = generalDatal;
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

    public GeneralData getGeneralDatal() {
        return generalDatal;
    }

    public void setGeneralDatal(GeneralData generalDatal) {
        this.generalDatal = generalDatal;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}