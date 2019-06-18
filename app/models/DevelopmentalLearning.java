package models;

import play.db.ebean.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by PRATYA on 11/22/2018.
 */
@Entity
@Table (name = "tbDevelopmentalLearning")
public class DevelopmentalLearning extends Model {

    @Id
    private String id;
    private String numDomain;
    @Lob
    private String name;
    @ManyToOne
    private SyllabusCourse syllabusCourse;

    @OneToMany(mappedBy = "developmentalLearning",cascade = CascadeType.ALL)
    private List<Curriculum> curriculumList = new ArrayList<Curriculum>();

    @OneToMany(mappedBy = "developmentalLearning",cascade = CascadeType.ALL)
    private List<DevelopmentalLearningList> developmentalLearningListList = new ArrayList<DevelopmentalLearningList>();

    @OneToMany(mappedBy = "developmentalLearning" , cascade = CascadeType.ALL)
    private List<TeachingStrategies> teachingStrategiesList = new ArrayList<TeachingStrategies>();

    @OneToMany(mappedBy = "developmentalLearning" ,cascade = CascadeType.ALL)
    private List<EvaluationStrategy> evaluationStrategyList = new ArrayList<EvaluationStrategy>();


        public DevelopmentalLearning() {
    }

    public DevelopmentalLearning(String id, String numDomain, String name, SyllabusCourse syllabusCourse) {
        this.id = id;
        this.numDomain = numDomain;
        this.name = name;
        this.syllabusCourse = syllabusCourse;
    }

    public String getNumDomain() {
        return numDomain;
    }

    public void setNumDomain(String numDomain) {
        this.numDomain = numDomain;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<DevelopmentalLearningList> getDevelopmentalLearningListList() {
        return developmentalLearningListList;
    }



    public List<TeachingStrategies> getTeachingStrategiesList() {
        return teachingStrategiesList;
    }



    public List<Curriculum> getCurriculumList() {
        return curriculumList;
    }

    public List<EvaluationStrategy> getEvaluationStrategyList() {
        return evaluationStrategyList;
    }

    public void setEvaluationStrategyList(List<EvaluationStrategy> evaluationStrategyList) {
        this.evaluationStrategyList = evaluationStrategyList;
    }

    public static Finder<String, DevelopmentalLearning> finder = new Finder<String, DevelopmentalLearning>(String.class, DevelopmentalLearning.class);

    public static List<DevelopmentalLearning> developmentalLearningList (){
        return finder.all();
    }
    public static void insert (DevelopmentalLearning developmentalLearning){
        developmentalLearning.save();
    }
    public static void update (DevelopmentalLearning developmentalLearning){
        developmentalLearning.update();
    }
    public static void delete (DevelopmentalLearning developmentalLearning){
        developmentalLearning.delete();
    }

    public static DevelopmentalLearning nameDev (String id){
        return finder.where().eq("id",id).findUnique();
    }

}

