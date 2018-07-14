package controllers;

import models.Racun;
import play.mvc.Controller;

import java.util.List;

/**
 * Created by Djordje on 05-Sep-17.
 */
public class Racuni extends Controller {

    public static void show(){
        List<Racun> racuni = Racun.find("klijent_id", session.get("idKlijenta")).fetch();
        System.out.println(racuni);
        render(racuni);
    }
}
