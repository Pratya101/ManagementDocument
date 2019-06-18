package models;

import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.List;

/**
 * Created by Coffee on 16/11/2561.
 */
@Entity
@Table(name = "CourseID")
public class CourseID extends Model {
    @Id
    private String id;

    @OneToOne
    private Course course;

    public CourseID() {
    }

    public CourseID(String id, Course course) {
        this.id = id;
        this.course = course;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public static Finder<String, CourseID> finder = new Finder<String, CourseID>(String.class, CourseID.class);

    public static List<CourseID> courseIDList (){
        return finder.all();
    }
    public static void insert (CourseID courseID){
        courseID.save();
    }
    public static void update (CourseID courseID){
        courseID.update();
    }
    public static void delete (CourseID courseID){
        courseID.delete();
    }

    public static CourseID listChkCourse (String id){
        return finder.where().eq("course.id",id).findUnique();
    }

}