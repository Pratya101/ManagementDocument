package models;

import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.List;

/**
 * Created by Coffee on 16/11/2561.
 */
@Entity
@Table(name = "tbDataHours")
public class DataHours extends Model {

    @Id
    private String id;
    private String hDescribe, hSupplementary, hWork,hStudy;


    @ManyToOne
    private GeneralData generalData;
    public DataHours() {
    }

    public DataHours(String id, String hDescribe, String hSupplementary, String hWork, String hStudy, GeneralData generalData) {
        this.id = id;
        this.hDescribe = hDescribe;
        this.hSupplementary = hSupplementary;
        this.hWork = hWork;
        this.hStudy = hStudy;
        this.generalData = generalData;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String gethDescribe() {
        return hDescribe;
    }

    public void sethDescribe(String hDescribe) {
        this.hDescribe = hDescribe;
    }

    public String gethSupplementary() {
        return hSupplementary;
    }

    public void sethSupplementary(String hSupplementary) {
        this.hSupplementary = hSupplementary;
    }

    public String gethWork() {
        return hWork;
    }

    public void sethWork(String hWork) {
        this.hWork = hWork;
    }

    public String gethStudy() {
        return hStudy;
    }

    public void sethStudy(String hStudy) {
        this.hStudy = hStudy;
    }

    public GeneralData getGeneralData() {
        return generalData;
    }

    public void setGeneralData(GeneralData generalData) {
        this.generalData = generalData;
    }

    public static Finder<String, DataHours> finder = new Finder<String, DataHours>(String.class, DataHours.class);

    public static List<DataHours> dataHoursList() {
        return finder.all();
    }

    public static void insert(DataHours dataHourses) {
        dataHourses.save();
    }

    public static void update(DataHours dataHourses) {
        dataHourses.update();
    }

    public static void delete(DataHours dataHourses) {
        dataHourses.delete();
    }
}
