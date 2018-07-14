package controllers;

import models.Delatnost;
import play.data.validation.Error;
import play.data.validation.Required;
import play.mvc.Controller;
import play.mvc.With;

import java.util.List;

/**
 * Created by Djordje on 3/27/2017.
 */
@With(Secure.class)
public class Delatnosti extends Controller {

    public static void show(String mode){
        List<Delatnost> delatnosti = Delatnost.findAll();
        if (mode == null || mode.equals(""))
            mode = "edit";
        render(delatnosti, mode);
    }
    public static void add(@Required String nazivDelatnosti, String sifraDelatnosti) {
        if(validation.hasErrors()) {
            for(Error error : validation.errors()) {
                System.out.println(error.message());
            }
            validation.keep();
            show("add");
        }else {
            Delatnost delatnost =new Delatnost();
            delatnost.naziv = nazivDelatnosti;
            delatnost.sifra = sifraDelatnosti;
            delatnost.save();
            validation.keep();
            show("add");
        }
    }
    public static void filter(@Required String nazivDelatnosti){
        List<Delatnost> delatnosti = Delatnost.find("byNAZIV_DELATNOSTILike", "%"+ nazivDelatnosti +"%").fetch();
        String mode = "edit";
        renderTemplate("Delatnosti/show.html", delatnosti, mode);
    }
    public static void edit(@Required String nazivDelatnosti, String sifraDelatnosti, long id){
        Delatnost delatnost = Delatnost.findById(id);
        delatnost.naziv=nazivDelatnosti;
        delatnost.sifra=sifraDelatnosti;
        delatnost.save();
        show("");
    }
    public static void delete(long id){
        Delatnost delatnost = Delatnost.findById(id);
        System.out.println(id);
        delatnost.delete();
        show("");
    }

    public static void create(){
        show("add");

    }
}