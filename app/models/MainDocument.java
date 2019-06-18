package models;

import play.db.ebean.Model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tbMainDocument")
public class MainDocument extends Model {
    @Id
    private String id;
    @Lob
    private String detail;

    @ManyToOne
    private GeneralData generalData;

    @ManyToOne
    private Course course;

    public MainDocument() {
    }

    public MainDocument(String id, String detail, GeneralData generalData, Course course) {
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

    public static Finder<String , MainDocument> finder = new Finder<String, MainDocument>(String.class, MainDocument.class);

    public static List<MainDocument> mainDocumentList (){
        return finder.all();
    }

    public static void insert (MainDocument mainDocument){
        mainDocument.save();
    }
    public static void update (MainDocument mainDocument){
        mainDocument.update();
    }
    public static void delte (MainDocument mainDocument){
        mainDocument.delete();
    }
}