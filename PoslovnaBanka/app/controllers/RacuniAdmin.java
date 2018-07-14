package controllers;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import models.*;
import play.data.validation.Error;
import play.data.validation.Required;
import play.mvc.Controller;
import play.mvc.With;
import java.text.SimpleDateFormat;
/**
 * Created by Djordje on 3/26/2017.
 */
@With(Secure.class)
public class RacuniAdmin extends Controller {

    public static void show(String mode){
        List<Racun> racuni = Racun.findAll();
        render(racuni, mode);
    }

    public static void delete(long id){
        Racun racun = Racun.findById(id);
        racun.delete();
        show("");
    }

    public static void add(@Required String brojRacuna, String datumOtvaranja, long klijent){
        if(validation.hasErrors()) {
            validation.keep();
            for(Error error : validation.errors()) {
                System.out.println(error.message());
            }
            show("add");
        }else {
            System.out.println("datum:"+datumOtvaranja);
            Racun racun = new Racun();
            Banka banka = Banka.find("swiftKod", session.get("banka")).first();
            racun.banka = banka;
            racun.brojRacuna = brojRacuna;
            Date datum = new Date();
            racun.datumOtvaranja = datum;
            FizickoLice fizickoLice = FizickoLice.findById(klijent);
            PravnoLice pravnoLice = PravnoLice.findById(klijent);
            if(fizickoLice!=null){
                racun.klijent = fizickoLice;
            }else if (pravnoLice != null){
                racun.klijent = pravnoLice;
            }else {
                throw new IllegalArgumentException("");
            }
            racun.save();
            validation.keep();
            show("add");
        }
    }

    public static void edit(){
        show("edit");
    }
}
