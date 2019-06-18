package models;

import play.db.ebean.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Coffee on 16/11/2561.
 */
@Entity
@Table(name = "tbCategoryCourse")
public class CategoryCourse extends Model {
@Id
    private String id;
    private  String name;
    private int amount;

    @ManyToOne
    private SyllabusCourse syllabusCourse;

    @OneToMany(mappedBy = "categoryCourse",cascade = CascadeType.ALL)
    private List<GroupCourse> groupCourseList = new ArrayList<GroupCourse>();

    public CategoryCourse() {
    }

    public CategoryCourse(String id,String name, int amount, SyllabusCourse syllabusCourse) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.syllabusCourse = syllabusCourse;
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

    public SyllabusCourse getSyllabusCourse() {
        return syllabusCourse;
    }

    public void setSyllabusCourse(SyllabusCourse syllabusCourse) {
        this.syllabusCourse = syllabusCourse;
    }

    public List<GroupCourse> getGroupCourseList() {
        return groupCourseList;
    }

    public void setGroupCourseList(List<GroupCourse> groupCourseList) {
        this.groupCourseList = groupCourseList;
    }

    public static Finder<String, CategoryCourse> finder = new Finder<String, CategoryCourse>(String.class, CategoryCourse.class);
    public static List<CategoryCourse> categoryCourseList (){
        return finder.all();
    }
    public static void insert (CategoryCourse categoryCourse){
        categoryCourse.save();
    }
    public static void update (CategoryCourse categoryCourse){
        categoryCourse.update();
    }
    public static void delete (CategoryCourse categoryCourse){
        categoryCourse.delete();
    }

    public static CategoryCourse nameCat (String id){
        return finder.where().eq("id", id).findUnique();
    }
}
