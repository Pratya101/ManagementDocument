package models;

import play.db.ebean.Model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tbSuggustionDocument")
public class SuggustionDocument extends Model {

    @Id
    private String id;
    @Lob
    private String detail;

    @ManyToOne
    private GeneralData generalData;
    @ManyToOne
    private Course course;

    public SuggustionDocument() {
    }

    public SuggustionDocument(String id, String detail, GeneralData generalData, Course course) {
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

    public static Finder<String, SuggustionDocument> finder = new Finder<String, SuggustionDocument>(String.class, SuggustionDocument.class);

    public static List<SuggustionDocument> suggustionDocumentList (){
            return  finder.all();
    }
    public static void insert (SuggustionDocument suggustionDocument){
        suggustionDocument.save();
    }

    public static void update (SuggustionDocument suggustionDocument){
        suggustionDocument.update();
    }

    public static void delete (SuggustionDocument suggustionDocument){
        suggustionDocument.delete();
    }

}