package models;

import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.List;

/**
 * Created by Coffee on 16/11/2561.
 */
@Entity
@Table(name = "TandY")
public class    TandY extends Model {
@Id
    private String id;
    private  String t,y;

    @ManyToOne
    private SyllabusCourse syllabusCourse;

    public TandY() {
    }

    public TandY(String id, String t, String y, SyllabusCourse syllabusCourse) {
        this.id = id;
        this.t = t;
        this.y = y;
        this.syllabusCourse = syllabusCourse;
    }

    public SyllabusCourse getSyllabusCourse() {
        return syllabusCourse;
    }

    public void setSyllabusCourse(SyllabusCourse syllabusCourse) {
        this.syllabusCourse = syllabusCourse;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getT() {
        return t;
    }

    public void setT(String t) {
        this.t = t;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    public static Finder<String, TandY> finder = new Finder<String, TandY>(String.class, TandY.class);
    public static List<TandY> tandYList (){
        return finder.all();
    }
    public static  void insert (TandY tandY){
        tandY.save();
    }
    public static  void update (TandY tandY){
        tandY.update();
    }
    public static  void delete (TandY tandY){
        tandY.delete();
    }
}
