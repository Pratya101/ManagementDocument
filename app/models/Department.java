package models;

import play.db.ebean.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pratya on 9/29/2018.
 */
@Entity
@Table(name = "tbDepartment")
public class Department extends Model {
    @Id
    private String id;
    private String nameTh,nameEng,shotname;

    @ManyToOne()
    private Faculty faculty;

    @OneToMany(mappedBy = "department")
    private List<Teacher> teacherList = new ArrayList<Teacher>();




    public Department() {
    }

    public Department(String id, String nameTh, String nameEng, String shotname, Faculty faculty) {
        this.id = id;
        this.nameTh = nameTh;
        this.nameEng = nameEng;
        this.shotname = shotname;
        this.faculty = faculty;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNameTh() {
        return nameTh;
    }

    public void setNameTh(String nameTh) {
        this.nameTh = nameTh;
    }

    public String getNameEng() {
        return nameEng;
    }

    public void setNameEng(String nameEng) {
        this.nameEng = nameEng;
    }

    public String getShotname() {
        return shotname;
    }

    public void setShotname(String shotname) {
        this.shotname = shotname;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public List<Teacher> getTeacherList() {
        return teacherList;
    }

    public void setTeacherList(List<Teacher> teacherList) {
        this.teacherList = teacherList;
    }

    public  static  Finder<String, Department>  finder = new Finder(String.class, Department.class);

    public  static List<Department> departmentList  (){
        return  finder.all();
    }

    public  static  void insert (Department department){
        department.save();
    }

    public  static  void  update (Department department){
        department.update();
    }
    public  static  void  delete (Department department){
        department.delete();
    }


}
