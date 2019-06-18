package models;

import play.db.ebean.Model;
import play.mvc.Result;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

/**
 * Created by coffee on 5/23/2019.
 */
@Entity
@Table(name = "tbNotifications")
public class Notifications extends Model {
    @Id
    private String id;
    private String date,term,year,detail;

    public Notifications() {
    }

    public Notifications(String id, String date, String term, String year, String detail) {
        this.id = id;
        this.date = date;
        this.term = term;
        this.year = year;
        this.detail = detail;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
    public static Finder<String,Notifications> finder = new Finder<String, Notifications>(String.class,Notifications.class);

    public static List<Notifications> notificationsList (){
        return finder.all();
    }
    public static void insert (Notifications notifications){
        notifications.save();
    }
    public static void update (Notifications notifications){
        notifications.update();
    }
    public static void delete (Notifications notifications){
        notifications.delete();
    }
}
