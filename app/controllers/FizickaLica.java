package controllers;

import models.FizickoLice;
import play.data.validation.Required;
import play.mvc.Controller;

import java.util.List;

/**
 * Created by Djordje on 8/25/2017.
 */
public class FizickaLica extends Controller {

    public static void show(String mode){
        List<FizickoLice> fizickaLica = FizickoLice.findAll();
        System.out.println(fizickaLica);
        if (mode == null || mode.equals(""))
            mode = "edit";
        render(fizickaLica, mode);
    }

    public static void add(@Required String ime, String prezime, String username, String telefon, String adresa, String email, String fax, String jmbg) {
        if(validation.hasErrors()) {

            validation.keep();
            show("add");
        }else {
            FizickoLice fizickoLice =new FizickoLice();
            fizickoLice.ime = ime;
            fizickoLice.prezime = prezime;
            fizickoLice.username = username;
            fizickoLice.telefon = telefon;
            fizickoLice.adresa = adresa;
            fizickoLice.email = email;
            fizickoLice.fax = fax;
            fizickoLice.jmbg = jmbg;
            fizickoLice.password = "12345";
            fizickoLice.save();
            validation.keep();
            show("add");
        }
    }

    public static void filter(@Required String username){
        List<FizickoLice> fizickoLice = FizickoLice.find("byUSERNAMELike", "%"+ username +"%").fetch();
        String mode = "edit";
        renderTemplate("FizickaLica/show.html", fizickoLice, mode);
    }

    public static void edit(@Required String ime, String prezime, String username, String telefon, String adresa, String email, String fax, String jmbg, Long id){
        FizickoLice fizickoLice = FizickoLice.findById(id);
        fizickoLice.ime = ime;
        fizickoLice.prezime = prezime;
        fizickoLice.username = username;
        fizickoLice.telefon = telefon;
        fizickoLice.adresa = adresa;
        fizickoLice.email = email;
        fizickoLice.fax = fax;
        fizickoLice.jmbg = jmbg;
        fizickoLice.save();
        show("");
    }

    public static void delete(long id){
        FizickoLice fizickoLice = FizickoLice.findById(id);
        System.out.println(id);
        fizickoLice.delete();
        show("");
    }
}
