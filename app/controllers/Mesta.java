package controllers;

import models.Mesto;
import play.data.validation.Error;
import play.data.validation.Required;
import play.mvc.Controller;
import play.mvc.With;

import java.util.List;
/**
 * Created by Djordje on 3/27/2017.
 */
@With(Secure.class)
public class Mesta extends Controller {

    public static void show(String mode){
        List<Mesto>mesta=Mesto.findAll();
        if (mode == null || mode.equals(""))
            mode = "edit";
        render(mesta, mode);
    }
    public static void add(@Required String nazivMesta, int postanskiBroj) {
        System.out.println(nazivMesta+" "+postanskiBroj);
        if(validation.hasErrors()) {
            for(Error error : validation.errors()) {
                System.out.println(error.message());
            }
            validation.keep();
            show("add");
        }else {
            Mesto mesto=new Mesto();
            mesto.naziv=nazivMesta;
            mesto.postanskiBroj=postanskiBroj;
            mesto.save();
            validation.keep();
            show("add");
        }
    }
    public static void filter(@Required String nazivMesta){
        List<Mesto> mesta = Mesto.find("byNAZIVLike", "%"+ nazivMesta +"%").fetch();
        String mode = "edit";
        renderTemplate("Mesta/show.html", mesta, mode);
    }
    public static void edit(@Required String nazivMesta, int postanskiBroj, long id){
        Mesto mesto = Mesto.findById(id);
        mesto.naziv=nazivMesta;
        mesto.postanskiBroj = postanskiBroj;
        mesto.save();
        show("");
    }
    public static void delete(long id){
        Mesto mesto = Mesto.findById(id);
        System.out.println(id);
        mesto.delete();
        show("");
    }
}
