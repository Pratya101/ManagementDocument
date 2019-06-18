package models;

import play.db.ebean.Model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tbImportantDocument")
public class ImportantDocument extends Model{
    @Id
    private String id;
    @Lob
    private String detail;

    @ManyToOne
    private GeneralData generalData;
    @ManyToOne
    private Course course;

    public ImportantDocument() {
    }

    public ImportantDocument(String id, String detail, GeneralData generalData, Course course) {
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

    public static Finder<String , ImportantDocument> finder = new Finder<String, ImportantDocument>(String.class, ImportantDocument.class);

    public static List<ImportantDocument> importantDocumentList (){
        return  finder.all();
    }
    public static void insert (ImportantDocument importantDocument){
        importantDocument.save();
    }
    public static  void update (ImportantDocument importantDocument){
        importantDocument.update();
    }
    public static void delete (ImportantDocument importantDocument){
        importantDocument.delete();
    }
}