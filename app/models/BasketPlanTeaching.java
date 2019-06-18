package models;

/**
 * Created by coffee on 5/10/2019.
 */
public class BasketPlanTeaching {

    private String id,week,detail,hour,media;
    private Teacher teacher;

    public BasketPlanTeaching() {
    }

    public BasketPlanTeaching(String id, String week, String detail, String hour, String media, Teacher teacher) {
        this.id = id;
        this.week = week;
        this.detail = detail;
        this.hour = hour;
        this.media = media;
        this.teacher = teacher;
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

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getMedia() {
        return media;
    }

    public void setMedia(String media) {
        this.media = media;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}
