package models;


import com.avaje.ebean.Expr;
import play.db.ebean.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Coffee on 29/9/2561.
 */
@Entity
@Table(name = "tbTeacher")
public class Teacher extends Model {
    @Id
    private  String id;
    private  String name,sername,status,position,username,password;

    @ManyToOne()
    private Department department;

    @OneToMany(mappedBy = "teacher")
    private List<Instructors> instructorsList = new ArrayList<Instructors>();

    @OneToMany(mappedBy = "teacher")
    private List<GeneralData> generalDataList = new ArrayList<GeneralData>();

    public Teacher() {
    }

    public Teacher(String id, String name, String sername, String status, String position,String username,String password, Department department) {
        this.id = id;
        this.name = name;
        this.sername = sername;
        this.status = status;
        this.position = position;
        this.username = username;
        this.password = password;
        this.department = department;
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

    public String getSername() {
        return sername;
    }

    public void setSername(String sername) {
        this.sername = sername;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public List<Instructors> getInstructorsList() {
        return instructorsList;
    }

    public void setInstructorsList(List<Instructors> instructorsList) {
        this.instructorsList = instructorsList;
    }

    public static Finder<String, Teacher> finder = new Finder(String.class, Teacher.class);

    public List<GeneralData> getGeneralDataList() {
        return generalDataList;
    }

    public void setGeneralDataList(List<GeneralData> generalDataList) {
        this.generalDataList = generalDataList;
    }

    public  static List<Teacher> teacherList (){
        return  finder.all();
    }
    public  static  void insert (Teacher teacher){
        teacher.save();
    }
    public  static  void update (Teacher teacher){
        teacher.update();
    }
    public  static  void delete (Teacher teacher){
        teacher.delete();
    }

    public  static Teacher authen(String user, String password){
        return  finder.where().and(Expr.eq("username",user),Expr.eq("password",password)).findUnique();
    }
}
