package models;

import play.db.ebean.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Coffee on 16/11/2561.
 */
@Entity
@Table(name = "tbCourse")
public class Course extends Model {


    @Id
    private String id;
    private String idCourse,nameTH,nameENG;
    @Lob
    private String detialTH;
    @Lob
    private String detailENG;
        private String requiredCourses;

    private int amount;

    @ManyToOne
    private GroupCourse groupCourse;

    @OneToMany(mappedBy = "course",cascade = CascadeType.ALL)
    private List<Instructors> instructorsList = new ArrayList<Instructors>();

    @OneToMany(mappedBy = "course",cascade = CascadeType.ALL)
    private List<Curriculum> curriculumList = new ArrayList<Curriculum>();

    @OneToMany(mappedBy = "course",cascade = CascadeType.ALL)
    private List<GeneralData> generalDataList = new ArrayList<GeneralData>();

    @OneToMany(mappedBy = "course",cascade = CascadeType.ALL)
    private List<CourseDestination> courseDestinationList= new ArrayList<CourseDestination>();

    @OneToMany(mappedBy = "course",cascade = CascadeType.ALL)
    private List<CourseObjectives> courseObjectivesList= new ArrayList<CourseObjectives>();

    @OneToMany(mappedBy = "course",cascade = CascadeType.ALL)
    private List<TeachingStrategiesData> teachingStrategiesDataList = new ArrayList<TeachingStrategiesData>();
    public Course() {
    }

    public Course(String id, String idCourse, String nameTH, String nameENG, String detialTH, String detailENG, String requiredCourses, int amount, GroupCourse groupCourse, Curriculum curriculum) {
        this.id = id;
        this.idCourse = idCourse;
        this.nameTH = nameTH;
        this.nameENG = nameENG;
        this.detialTH = detialTH;
        this.detailENG = detailENG;
        this.requiredCourses = requiredCourses;
        this.amount = amount;
        this.groupCourse = groupCourse;
    }

    public String getIdCourse() {
        return idCourse;
    }

    public void setIdCourse(String idCourse) {
        this.idCourse = idCourse;
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

    public String getNameENG() {
        return nameENG;
    }

    public void setNameENG(String nameENG) {
        this.nameENG = nameENG;
    }

    public String getDetialTH() {
        return detialTH;
    }

    public void setDetialTH(String detialTH) {
        this.detialTH = detialTH;
    }

    public String getDetailENG() {
        return detailENG;
    }

    public void setDetailENG(String detailENG) {
        this.detailENG = detailENG;
    }

    public String getRequiredCourses() {
        return requiredCourses;
    }

    public void setRequiredCourses(String requiredCourses) {
        this.requiredCourses = requiredCourses;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public GroupCourse getGroupCourse() {
        return groupCourse;
    }

    public void setGroupCourse(GroupCourse groupCourse) {
        this.groupCourse = groupCourse;
    }



    public List<GeneralData> getGeneralDataList() {
        return generalDataList;
    }

    public List<Instructors> getInstructorsList() {
        return instructorsList;
    }

    public void setInstructorsList(List<Instructors> instructorsList) {
        this.instructorsList = instructorsList;
    }

    public List<Curriculum> getCurriculumList() {
        return curriculumList;
    }

    public void setCurriculumList(List<Curriculum> curriculumList) {
        this.curriculumList = curriculumList;
    }

    public void setGeneralDataList(List<GeneralData> generalDataList) {
        this.generalDataList = generalDataList;
    }

    public List<CourseDestination> getCourseDestinationList() {
        return courseDestinationList;
    }

    public void setCourseDestinationList(List<CourseDestination> courseDestinationList) {
        this.courseDestinationList = courseDestinationList;
    }

    public List<CourseObjectives> getCourseObjectivesList() {
        return courseObjectivesList;
    }

    public void setCourseObjectivesList(List<CourseObjectives> courseObjectivesList) {
        this.courseObjectivesList = courseObjectivesList;
    }

    public List<TeachingStrategiesData> getTeachingStrategiesDataList() {
        return teachingStrategiesDataList;
    }

    public void setTeachingStrategiesDataList(List<TeachingStrategiesData> teachingStrategiesDataList) {
        this.teachingStrategiesDataList = teachingStrategiesDataList;
    }

    public static Finder<String, Course> finder = new Finder<String, Course>(String.class, Course.class);
    public static List<Course> courseList (){
        return finder.all();
    }
    public static void insert (Course course){
        course.save();
    }
    public static void update (Course course){
        course.update();
    }
    public static void delete (Course course){
        course.delete();
    }
    public static List<Course> courseOnly  (String id){
        return finder.where().eq("groupCourse.id", id).findList();
    }
    public static List<Course> dataTeaching (String id){
        return finder.where().eq("id",id).findList();
    }
}
