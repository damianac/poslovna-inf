package controllers;

import play.mvc.Controller;
import play.mvc.With;

/**
 * Created by Djordje on 04-Sep-17.
 */
@With(Secure.class)
public class Admin extends Controller {

    public static void show(String mode){
        render();
    }
}
