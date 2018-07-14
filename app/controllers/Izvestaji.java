package controllers;

import models.FizickoLice;
import models.Klijent;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import play.mvc.Controller;
import java.sql.DriverManager;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Djordje on 4/7/2017.
 */
public class Izvestaji extends Controller {

    public static final String dirPath = "./izvestaji/";
    public static final String dbUrl = "jdbc:mysql://localhost:3306/poslovna";
    public static final String user = "root";
    public static final String passowrd = "1234";
    public static void show()
    {
        render();
    }

    public static void report1(Date datumPocetka, Date datumZavrsetka, long idKlijenta){
        try {
            Klijent klijent = Klijent.findById(idKlijenta);
            String username = klijent.username;
            Map params = new HashMap(1);

            params.put("datumPocetka", datumPocetka);
            params.put("datumZavrsetka", datumZavrsetka);
            params.put("username", username);
            if(klijent instanceof FizickoLice){
                System.out.println("fizicko");
                JasperPrint jp = JasperFillManager.fillReport( dirPath+"IzvodFizickoLice.jasper",params,DriverManager.getConnection(dbUrl, user, passowrd) );
                JasperViewer.viewReport(jp, false);
            }else{
                System.out.println("pravno");
                JasperPrint jp = JasperFillManager.fillReport( dirPath+"IzvodPravnoLice.jasper",params,DriverManager.getConnection(dbUrl, user, passowrd));
                JasperViewer.viewReport(jp, false);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        show();
    }

    public static void report2(String swiftKod){
        try {
            Map params = new HashMap(1);
            params.put("swiftKod", swiftKod);
            JasperPrint jp = JasperFillManager.fillReport( dirPath+"IzvestajRacunaZaZadatuBanku.jasper",params,DriverManager.getConnection(dbUrl, user, passowrd));
            JasperViewer.viewReport(jp, false);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        show();
    }
}
