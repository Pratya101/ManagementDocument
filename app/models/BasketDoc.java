package models;

/**
 * Created by coffee on 5/26/2019.
 */
public class BasketDoc {
    private String id,course,status,teacher,term,year;

    public BasketDoc() {
    }

    public BasketDoc(String id, String course, String status, String teacher, String term, String year) {
        this.id = id;
        this.course = course;
        this.status = status;
        this.teacher = teacher;
        this.term = term;
        this.year = year;
    }


    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }
}
