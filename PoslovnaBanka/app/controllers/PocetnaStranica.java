package controllers;

import play.mvc.Controller;
import play.mvc.With;

/**
 * Created by Djordje on 3/26/2017.
 */
@With(Secure.class)
public class PocetnaStranica extends Controller {

    public static void show(String mode){

        render(mode);
    }

}
