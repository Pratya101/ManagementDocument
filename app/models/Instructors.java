package models;

import com.avaje.ebean.Expr;
import play.db.ebean.Model;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by PRATYA on 11/23/2018.
 */
@Entity
@Table(name = "tbInstructors")
public class Instructors extends Model {
    @Id
    private String id;
    private String term, year, status,room,statusDoc;

    @ManyToOne
    private SyllabusCourse syllabusCourse;

    @ManyToOne
    private Teacher teacher;

    @OneToMany(mappedBy = "instructors", cascade = CascadeType.ALL)
    private List<GeneralData> generalDataList= new ArrayList<GeneralData>();

    @ManyToOne
    private Course course;

    public Instructors() {
    }

    public Instructors(String id, String term, String year, String status, String room, String statusDoc, SyllabusCourse syllabusCourse, Teacher teacher, Course course) {
        this.id = id;
        this.term = term;
        this.year = year;
        this.status = status;
        this.room = room;
        this.statusDoc = statusDoc;
        this.syllabusCourse = syllabusCourse;
        this.teacher = teacher;
        this.course = course;
    }

    public SyllabusCourse getSyllabusCourse() {
        return syllabusCourse;
    }

    public void setSyllabusCourse(SyllabusCourse syllabusCourse) {
        this.syllabusCourse = syllabusCourse;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getStatusDoc() {
        return statusDoc;
    }

    public void setStatusDoc(String statusDoc) {
        this.statusDoc = statusDoc;
    }

    public static Finder<String, Instructors> finder = new Finder<String, Instructors>(String.class, Instructors.class);

    public List<GeneralData> getGeneralDataList() {
        return generalDataList;
    }

    public void setGeneralDataList(List<GeneralData> generalDataList) {
        this.generalDataList = generalDataList;
    }

    public static List<Instructors> instructorsList() {
        return finder.all();
    }

    public static void insert(Instructors instructors) {
        instructors.save();
    }

    public static void update(Instructors instructors) {
        instructors.update();
    }

    public static void delete(Instructors instructors) {
        instructors.delete();
    }

    public static List<Instructors> instructorsListOnly (String term , String year){
        return  finder.where().and(Expr.eq("term", term),Expr.eq("year",year)).findList();
    }

    public static Instructors instructorsss (String cid, String status){
        return  finder.where().and(Expr.eq("course.id", cid), Expr.eq("status", status)).findUnique();
    }
}
