package controllers;

import models.*;
import play.api.templates.Html;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.loginPage;
import views.html.main;
import views.html.Notify.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Notifications extends Controller {
    public static Notifications notifications;
    public static List<models.Notifications> notificationsList = new ArrayList<models.Notifications>();
    public static List<TokenLine> tokenLineList = new ArrayList<TokenLine>();
    public static TokenLine tokenLine;

    public static Result main(Html Content) {
        return ok(main.render(Content));
    }

    public static Result sendData() {
        session("numMenu","notify");
        if (session("userId")==null){
            flash("UserError","คุณไม่มีสิทธิ์เข้าใข้งานในส่วนนี้ กรุณาLogin");
            return ok(loginPage.render());
        }
        tokenLineList = TokenLine.tokenLineList();
        notificationsList = models.Notifications.notificationsList();
        return main(sendData.render(notificationsList,tokenLineList));
    }

    public static Result reciveData() {
        try {
            DynamicForm dataRe = Form.form().bindFromRequest();
            String date = dataRe.get("date");
            String term = dataRe.get("term");
            String year = dataRe.get("year");
            String detail = dataRe.get("detail");
            String token = dataRe.get("token");
            notificationsList = models.Notifications.notificationsList();
            int numlist = notificationsList.size();
            int id, idNum;
            String lastNum;
            String sId;
            if (notificationsList.size() == 0) {
                sId = "NTF-000001";
            } else {
                lastNum = notificationsList.get(numlist - 1).getId();
                lastNum = lastNum.substring(4);
                idNum = Integer.parseInt(lastNum);
                id = idNum + 1;
                if (id < 10) {
                    sId = "NTF-00000" + id;
                } else if (id < 100) {
                    sId = "NTF-0000" + id;
                } else if (id < 1000) {
                    sId = "NTF-000" + id;
                } else if (id < 10000) {
                    sId = "NTF-00" + id;
                } else if (id < 100000) {
                    sId = "NTF-0" + id;
                } else {
                    sId = "NTF-" + id;
                }
            }
            models.Notifications newdata = new models.Notifications(sId, date, term, year, detail);
            models.Notifications.insert(newdata);
            BufferedReader br = new BufferedReader(new InputStreamReader(new URL("http://127.0.0.1/Notifications/index.php?date=" + date + "&term=" + term + "&year=" + year + "&detail=" + detail +"&token=" + token).openStream()));
        } catch (IOException e) {
            flash("error", "dataError");
        }
        flash("sendOK", "ทำการส่งการเเจ้งเตือนเรียบร้อยเเล้ว");
        return redirect("/sendData");
    }

    public static Result editToken(){
        DynamicForm data = Form.form().bindFromRequest();
        String token = data.get("token");
        String name  = data.get("name");
        tokenLine = tokenLineList.get(0);
        tokenLine.setTokenLine(token);
        tokenLine.setName(name);
        TokenLine.update(tokenLine);
        return sendData();
    }

}
