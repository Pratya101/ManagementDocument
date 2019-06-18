package models;

import play.db.ebean.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Coffee on 16/11/2561.
 */
@Entity
@Table (name = "tbSyllabusCourse")
public class SyllabusCourse extends Model {
    @Id
    private  String id;
    private  String nameTH,nameEng;

    @OneToMany(mappedBy = "syllabusCourse",cascade = CascadeType.ALL)
    private List<DevelopmentalLearning> developmentalLearningList = new ArrayList<DevelopmentalLearning>();

    @OneToMany(mappedBy = "syllabusCourse",cascade = CascadeType.ALL)
    private List<CategoryCourse> categoryCourses =new ArrayList<CategoryCourse>();

    @OneToMany(mappedBy = "syllabusCourse",cascade = CascadeType.ALL)
    private List<TandY> tandYList = new ArrayList<TandY>();

    @OneToMany(mappedBy = "syllabusCourse",cascade = CascadeType.ALL)
    private List<Instructors> instructorsList = new ArrayList<Instructors>();

    public SyllabusCourse() {
    }

    public SyllabusCourse(String id, String nameTH, String nameEng) {
        this.id = id;
        this.nameTH = nameTH;
        this.nameEng = nameEng;
    }

    public List<DevelopmentalLearning> getDevelopmentalLearningList() {
        return developmentalLearningList;
    }

    public void setDevelopmentalLearningList(List<DevelopmentalLearning> developmentalLearningList) {
        this.developmentalLearningList = developmentalLearningList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNameTH() {
        return nameTH;
    }

    public void setNameTH(String nameTH) {
        this.nameTH = nameTH;
    }

    public String getNameEng() {
        return nameEng;
    }

    public void setNameEng(String nameEng) {
        this.nameEng = nameEng;
    }


    public List<CategoryCourse> getCategoryCourses() {
        return categoryCourses;
    }

    public void setCategoryCourses(List<CategoryCourse> categoryCourses) {
        this.categoryCourses = categoryCourses;
    }

    public List<TandY> getTandYList() {
        return tandYList;
    }

    public void setTandYList(List<TandY> tandYList) {
        this.tandYList = tandYList;
    }

    public List<Instructors> getInstructorsList() {
        return instructorsList;
    }

    public void setInstructorsList(List<Instructors> instructorsList) {
        this.instructorsList = instructorsList;
    }

    public static Finder<String, SyllabusCourse> finder = new Finder<String, SyllabusCourse>(String.class, SyllabusCourse.class);
    public static List<SyllabusCourse> syllabusCourseList(){
        return finder.all();
    }
    public static void insert(SyllabusCourse syllabusCourse){
        syllabusCourse.save();
    }
    public static void update(SyllabusCourse syllabusCourse){
        syllabusCourse.update();
    }
    public static void  delete (SyllabusCourse syllabusCourse){
        syllabusCourse.delete();
    }

}
