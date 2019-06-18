package models;

import play.db.ebean.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Coffee on 16/11/2561.
 */
@Entity
@Table(name = "tbGroupCourse")
public class GroupCourse extends Model {
    @Id
    private String id;
    private String name;
    private int amount;

    @ManyToOne
    private CategoryCourse categoryCourse;

    @OneToMany(mappedBy = "groupCourse",cascade = CascadeType.ALL)
    private List<Course> courseArrayList = new ArrayList<Course>();

    public GroupCourse() {


    }

    public GroupCourse(String name, int amount, CategoryCourse categoryCourse) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.categoryCourse = categoryCourse;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public CategoryCourse getCategoryCourse() {
        return categoryCourse;
    }

    public List<Course> getCourseArrayList() {
        return courseArrayList;
    }

    public void setCourseArrayList(List<Course> courseArrayList) {
        this.courseArrayList = courseArrayList;
    }

    public void setCategoryCourse(CategoryCourse categoryCourse) {
        this.categoryCourse = categoryCourse;
    }
    public static Finder<String, GroupCourse> finder = new Finder<String, GroupCourse>(String.class, GroupCourse.class);
    public static List<GroupCourse> groupCourseList (){
        return finder.all();
    }
    public static void insert (GroupCourse groupCourse){
        groupCourse.save();
    }
    public static void update (GroupCourse groupCourse){
        groupCourse.update();
    }
    public static void delete (GroupCourse groupCourse){
        groupCourse.delete();
    }
    public static List<GroupCourse> groupCourseOnly (String id){
        return finder.where().eq("categoryCourse.id", id).findList();
    }
    public static GroupCourse GPName (String id){
        return finder.where().eq("categoryCourse.id", id).findUnique();
    }
    public static List<GroupCourse> listcat (String id){
        return finder.where().eq("categoryCourse.id", id).findList();
    }

}
