package models;

import com.avaje.ebean.Expr;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.List;
import java.util.Random;

/**
 * Created by PRATYA on 11/22/2018.
 */
@Entity
@Table(name = "tbCurriculum")
public class Curriculum extends Model {
    @Id
    private String id;
    private String importance;
    @ManyToOne
    private DevelopmentalLearningList developmentalLearningList;
    @ManyToOne
    private DevelopmentalLearning developmentalLearning;

    @ManyToOne
    private Course course;

    public Curriculum() {
        setId(id);
    }

    public Curriculum(String importance, DevelopmentalLearningList developmentalLearningList,DevelopmentalLearning developmentalLearning, Course course) {
        setId(id);
        this.importance = importance;
        this.developmentalLearningList = developmentalLearningList;
        this.developmentalLearning = developmentalLearning;
        this.course = course;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        if (id == null){
            int i ;
            Random random = new Random();
            i = random.nextInt(100000)+1;
            this.id = "CR-"+Integer.toString(i);
        }else{
        this.id = id;
        }
    }

    public String getImportance() {
        return importance;
    }

    public void setImportance(String importance) {
        this.importance = importance;
    }

    public DevelopmentalLearningList getDevelopmentalLearningList() {
        return developmentalLearningList;
    }

    public void setDevelopmentalLearningList(DevelopmentalLearningList developmentalLearningList) {
        this.developmentalLearningList = developmentalLearningList;
    }

    public DevelopmentalLearning getDevelopmentalLearning() {
        return developmentalLearning;
    }

    public void setDevelopmentalLearning(DevelopmentalLearning developmentalLearning) {
        this.developmentalLearning = developmentalLearning;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public static Finder<String, Curriculum> finder = new Finder<String, Curriculum>(String.class, Curriculum.class);



    public static List<Curriculum> curriculumList() {
        return finder.all();
    }

    public static void insert(Curriculum curriculum) {
        curriculum.save();
    }

    public static void update(Curriculum curriculum) {
        curriculum.update();
    }

    public static void delete(Curriculum curriculum) {
        curriculum.delete();
    }

        public static Curriculum curriculumCouse (String Cid, String Did){
            return finder.where().and(Expr.eq("course.id",Cid),Expr.eq("developmentalLearningList.id",Did)).findUnique();
        }

        public static List<Curriculum> dataCurriculum (String id){
            return finder.where().eq("course.id",id).findList();
        }
}
