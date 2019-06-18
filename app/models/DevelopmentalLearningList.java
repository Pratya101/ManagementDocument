package models;

import play.db.ebean.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by PRATYA on 11/22/2018.
 */
@Entity
@Table(name = "tbDevelopmentalLearningList")
public class DevelopmentalLearningList extends Model {
    @Id
    private String id;
    @Lob
    private String listDevelopmentalLearning;


    @OneToMany(mappedBy = "developmentalLearningList",cascade = CascadeType.ALL)
    private List<Curriculum> curriculumList = new ArrayList<Curriculum>();
    @ManyToOne
    private DevelopmentalLearning developmentalLearning;

    public DevelopmentalLearningList() {
    }


    public DevelopmentalLearningList(String id, String listDevelopmentalLearning, DevelopmentalLearning developmentalLearning) {
        this.id = id;
        this.listDevelopmentalLearning = listDevelopmentalLearning;
        this.developmentalLearning = developmentalLearning;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getListDevelopmentalLearning() {
        return listDevelopmentalLearning;
    }

    public void setListDevelopmentalLearning(String listDevelopmentalLearning) {
        this.listDevelopmentalLearning = listDevelopmentalLearning;
    }

    public DevelopmentalLearning getDevelopmentalLearning() {
        return developmentalLearning;
    }

    public void setDevelopmentalLearning(DevelopmentalLearning developmentalLearning) {
        this.developmentalLearning = developmentalLearning;
    }

    public List<Curriculum> getCurriculumList() {
        return curriculumList;
    }

    public void setCurriculumList(List<Curriculum> curriculumList) {
        this.curriculumList = curriculumList;
    }

    public static Finder<String, DevelopmentalLearningList> finder = new Finder<String, DevelopmentalLearningList>(String.class, DevelopmentalLearningList.class);

    public static List<DevelopmentalLearningList> developmentalLearningListList() {
        return finder.all();
    }

    public static void insert(DevelopmentalLearningList developmentalLearningList) {
        developmentalLearningList.save();
    }

    public static void update(DevelopmentalLearningList developmentalLearningList) {
        developmentalLearningList.update();

    }

    public static void delete(DevelopmentalLearningList developmentalLearningList) {
        developmentalLearningList.delete();
    }

    public static List<DevelopmentalLearningList> developmentalLearningListOnly(String id){
        return finder.where().eq("developmentalLearning.id",id).findList();
    }

    public static List<DevelopmentalLearningList> getAllList (){
        return finder.all();
    }
}
