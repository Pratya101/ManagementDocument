package models;

import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pratya on 9/29/2018.
 */
@Entity
@Table(name = "tbfacultys")
public class Faculty extends Model{
    @Id
    private String id;
    private String name,shotname;

    @OneToMany(mappedBy = "faculty")
    private  List<Department> departments = new ArrayList<Department>();

    public Faculty() {
    }

    public Faculty(String id, String name, String shotname) {
        this.id = id;
        this.name = name;
        this.shotname = shotname;
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

    public String getShotname() {
        return shotname;
    }

    public void setShotname(String shotname) {
        this.shotname = shotname;
    }


    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }
    public  static Finder<String, Faculty> finder = new Finder(String.class, Faculty.class);
    public  static List<Faculty> facultyList (){
        return finder.all();
    }
    public  static  void insert (Faculty faculty){
        faculty.save();
    }
    public  static  void  update (Faculty faculty){
        faculty.update();
    }
    public  static  void  delete (Faculty faculty){
        faculty.delete();
    }
}
