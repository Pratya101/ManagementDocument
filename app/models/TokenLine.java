package models;

import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

/**
 * Created by coffee on 5/30/2019.
 */
@Entity
@Table(name = "tbTokenLine")
public class TokenLine extends Model {
    @Id
    private String id;
    private String tokenLine,name,status;

    public TokenLine() {
    }

    public TokenLine(String id, String tokenLine, String name, String status) {
        this.id = id;
        this.tokenLine = tokenLine;
        this.name = name;
        this.status = status;
    }

    public String getTokenLine() {
        return tokenLine;
    }

    public void setTokenLine(String tokenLine) {
        this.tokenLine = tokenLine;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static Finder<String,TokenLine> finder = new Finder<String, TokenLine>(String.class,TokenLine.class);

    public static List<TokenLine> tokenLineList (){
        return finder.all();
    }
    public static void insert (TokenLine tokenLine){
        tokenLine.save();
    }
    public static void update (TokenLine tokenLine){
        tokenLine.update();
    }
    public static void delete ( TokenLine tokenLine){
        tokenLine.delete();
    }

}
