package controllers;

import models.PravnoLice;
import play.data.validation.Required;
import play.mvc.Controller;

import java.util.List;

/**
 * Created by Djordje on 8/25/2017.
 */
public class PravnaLica extends Controller {

    public static void show(String mode){
        List<PravnoLice> pravnaLica=PravnoLice.findAll();
        if (mode == null || mode.equals(""))
            mode = "edit";
        render(pravnaLica, mode);
    }

    public static void add(@Required String naziv, String pib, String maticniBroj, String username, String telefon, String adresa, String email, String fax) {
        if(validation.hasErrors()) {

            validation.keep();
            show("add");
        }else {
            PravnoLice pravnoLice =new PravnoLice();
            pravnoLice.naziv = naziv;
            pravnoLice.pib = pib;
            pravnoLice.maticniBroj = maticniBroj;
            pravnoLice.username = username;
            pravnoLice.telefon = telefon;
            pravnoLice.adresa = adresa;
            pravnoLice.email = email;
            pravnoLice.fax = fax;
            pravnoLice.password = "12345";
            pravnoLice.save();
            validation.keep();
            show("");
        }
    }

    public static void filter(@Required String username){
        List<PravnoLice> pravnalica = PravnoLice.find("byUSERNAMELike", "%"+ username +"%").fetch();
        String mode = "edit";
        renderTemplate("PravnaLica/show.html", pravnalica, mode);
    }

    public static void edit(@Required String naziv, String pib, String maticniBroj, String username, String telefon, String adresa, String email, String fax, Long id){
        PravnoLice pravnoLice = PravnoLice.findById(id);
        pravnoLice.naziv = naziv;
        pravnoLice.pib = pib;
        pravnoLice.maticniBroj = maticniBroj;
        pravnoLice.username = username;
        pravnoLice.telefon = telefon;
        pravnoLice.adresa = adresa;
        pravnoLice.email = email;
        pravnoLice.fax = fax;
        pravnoLice.password = "12345";
        pravnoLice.save();
        show("");
    }
    public static void delete(long id){
        PravnoLice pravnoLice  = PravnoLice.findById(id);
        System.out.println(id);
        pravnoLice.delete();
        show("");
    }
}
