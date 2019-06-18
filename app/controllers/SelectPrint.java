package controllers;

import play.mvc.Controller;
import models.*;
import play.api.templates.Html;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.CreateCategory.*;
import views.html.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by coffee on 5/26/2019.
 */
public class SelectPrint extends Controller {
    public static Result main(Html Content) {
        return ok(main.render(Content));
    }


}
