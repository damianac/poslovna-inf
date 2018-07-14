package utils;

import models.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import play.Play;

import javax.print.Doc;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by stefan on 4/6/17.
 */
public class Read {

    private static DocumentBuilderFactory factory;
    private static Document document;
    private static DocumentBuilder documentBuilder;
    private static String neobradjeneUplatnice =
            Play.configuration.getProperty("uplatnice.neobradjene.path");
    private static String obradjeneUplatnice =
            Play.configuration.getProperty("uplatnice.obradjene.path");

    private static File neobradjenoDir;

    private static void init() {
        factory = DocumentBuilderFactory
                .newInstance();
        try {
            documentBuilder = factory
                    .newDocumentBuilder();
            document = null;
            neobradjenoDir = new File(neobradjeneUplatnice);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    public static void readMT102() {
        init();
        File[] neobradjenoMT102 =
                findFiles("mt102");

        readFiles(neobradjenoMT102);
    }

    public static void readMT103() {
        init();
        File[] neobradjenoMT103 =
                findFiles("mt103");

        readFiles(neobradjenoMT103);
    }

    private static boolean decomponiseMT102(Document document) {

        Element mt102El = (Element) document
                .getElementsByTagName("mt102")
                .item(0);
        Element zaglavljeEl = (Element) document
                .getElementsByTagName("zaglavlje")
                .item(0);
        Element listaNalogaEl = (Element) document
                .getElementsByTagName("lista_naloga")
                .item(0);

        Banka bankaDuznik = getBank(zaglavljeEl,
                "banka_duznik");
        Banka bankaPoverilac = getBank(zaglavljeEl,
                "banka_poverilac");

        double ukupanIznos = Double.parseDouble(
                zaglavljeEl
                        .getElementsByTagName("ukupan_iznos")
                        .item(0)
                        .getTextContent());
        String datumStr = zaglavljeEl
                .getElementsByTagName("datum")
                .item(0)
                .getTextContent();

        Date datum = getDate(datumStr);

        NodeList nodeNalozi = zaglavljeEl.getElementsByTagName("nalog");
        for(int i = 0; i < nodeNalozi.getLength(); i++) {
            Element nalogEl = (Element) nodeNalozi.item(i);
            String duznik = getClient(nalogEl, "duznik");
            String poverilac = getClient(nalogEl, "poverilac");

            Element podaciNalog = (Element) nalogEl
                    .getElementsByTagName("podaci_nalog")
                    .item(0);
            String svrhaPlacanja = podaciNalog
                    .getElementsByTagName("svrha_placanja")
                    .item(0)
                    .getTextContent();
            String dateStr = podaciNalog
                    .getElementsByTagName("datum")
                    .item(0)
                    .getTextContent();
            Date datumNaloga = getDate(dateStr);

            Element uplataEl = (Element) nalogEl
                    .getElementsByTagName("uplata")
                    .item(0);

            // UPLATA
            Element zaduzenjeEl = (Element) uplataEl
                    .getElementsByTagName("zaduzenje")
                    .item(0);
            Element odobrenjeEl = (Element) uplataEl
                    .getElementsByTagName("odobrenje")
                    .item(0);
            double iznos = Double.parseDouble(uplataEl
                    .getElementsByTagName("iznos")
                    .item(0).getTextContent());

            //<<<ZADUZENJE>>>
            String zaduzenjeRacun = zaduzenjeEl
                    .getElementsByTagName("broj_racuna")
                    .item(0)
                    .getTextContent();
            String zaduzenjeModel = zaduzenjeEl
                    .getElementsByTagName("broj_modela")
                    .item(0)
                    .getTextContent();
            String zaduzenjeBroj = zaduzenjeEl
                    .getElementsByTagName("poziv_na_broj")
                    .item(0)
                    .getTextContent();

            Racun racunDuznik = (Racun) Racun.find("byBrojRacuna", zaduzenjeRacun)
                    .fetch();


            //<<<ODOBRENJE>>>
            String odobrenjeRacun = odobrenjeEl
                    .getElementsByTagName("broj_racuna")
                    .item(0)
                    .getTextContent();
            String odobrenjeModel = odobrenjeEl
                    .getElementsByTagName("broj_modela")
                    .item(0)
                    .getTextContent();
            String odobrenjeBroj = odobrenjeEl
                    .getElementsByTagName("poziv_na_broj")
                    .item(0)
                    .getTextContent();

            Racun racunPoverilac = (Racun) Racun.find("byBrojRacuna", odobrenjeRacun)
                    .fetch();

            MT10X mt102 = new MT10X(datumNaloga, ukupanIznos, "mt102",
                    bankaDuznik.swiftKod, bankaPoverilac.swiftKod,
                    bankaDuznik.obracunskiRacun, bankaPoverilac.obracunskiRacun,
                    true, null);
            mt102.save();


            Nalog nalog = new Nalog(zaduzenjeRacun, odobrenjeRacun, zaduzenjeBroj,
                    odobrenjeBroj, zaduzenjeModel, odobrenjeModel, iznos,
                    duznik, poverilac, svrhaPlacanja, datumNaloga, true);
            nalog.mt10x = mt102;
            nalog.save();

            if(racunDuznik != null) {
                dailyState(racunDuznik, iznos, "-", datumNaloga);
            }

            if(racunPoverilac != null) {
                dailyState(racunPoverilac, iznos, "+", datumNaloga);
            }
        }

        return true;
    }

    private static boolean decomponiseMT103(Document document) {

        Element mt103El = (Element) document
                .getElementsByTagName("mt103")
                .item(0);
        Element duznikEl = (Element) mt103El
                .getElementsByTagName("duznik")
                .item(0);
        Element poverilacEl = (Element) mt103El
                .getElementsByTagName("poverilac")
                .item(0);
        Element nalogEl = (Element) mt103El
                .getElementsByTagName("nalog")
                .item(0);
        Element uplataEl = (Element) mt103El
                .getElementsByTagName("uplata")
                .item(0);

        Banka bankaDuznik = getBank(duznikEl,
                "banka");
        Banka bankaPoverilac = getBank(poverilacEl,
                "banka");

        String duznik= getClient(duznikEl, "klijent");
        String poverilac = poverilacEl.getTextContent();


        String svrhaPlacanja = nalogEl
                .getElementsByTagName("svrha_placanja")
                .item(0)
                .getTextContent();
        Element valutaEl = (Element) nalogEl.getElementsByTagName("valuta")
                .item(0);
        String datumNalogaStr = valutaEl.getElementsByTagName("datum")
                .item(0)
                .getTextContent();
        Date datumNaloga = getDate(datumNalogaStr);

        // UPLATA
        Element zaduzenjeEl = (Element) uplataEl
                .getElementsByTagName("zaduzenje")
                .item(0);
        Element odobrenjeEl = (Element) uplataEl
                .getElementsByTagName("odobrenje")
                .item(0);
        double iznos = Double.parseDouble(uplataEl
                .getElementsByTagName("iznos")
                .item(0).getTextContent());

        //<<<ZADUZENJE>>>
        String zaduzenjeRacun = zaduzenjeEl
                .getElementsByTagName("broj_racuna")
                .item(0)
                .getTextContent();
        String zaduzenjeModel = zaduzenjeEl
                .getElementsByTagName("broj_modela")
                .item(0)
                .getTextContent();
        String zaduzenjeBroj = zaduzenjeEl
                .getElementsByTagName("poziv_na_broj")
                .item(0)
                .getTextContent();

        Racun racunDuznik = (Racun) Racun.find("byBrojRacuna", zaduzenjeRacun)
                .fetch().get(0);


        //<<<ODOBRENJE>>>
        String odobrenjeRacun = odobrenjeEl
                .getElementsByTagName("broj_racuna")
                .item(0)
                .getTextContent();
        String odobrenjeModel = odobrenjeEl
                .getElementsByTagName("broj_modela")
                .item(0)
                .getTextContent();
        String odobrenjeBroj = odobrenjeEl
                .getElementsByTagName("poziv_na_broj")
                .item(0)
                .getTextContent();

        Racun racunPoverilac = (Racun) Racun.find("byBrojRacuna", odobrenjeRacun)
                .fetch();

        MT10X mt103 = new MT10X(datumNaloga, iznos, "mt103",
                bankaDuznik.swiftKod, bankaPoverilac.swiftKod,
                bankaDuznik.obracunskiRacun, bankaPoverilac.obracunskiRacun,
                true, null);
        mt103.save();


        Nalog nalog = new Nalog(zaduzenjeRacun, odobrenjeRacun, zaduzenjeBroj,
                odobrenjeBroj, zaduzenjeModel, odobrenjeModel, iznos,
                duznik, poverilac, svrhaPlacanja, datumNaloga, true);
        nalog.mt10x = mt103;
        nalog.save();

        if(racunDuznik != null) {
            dailyState(racunDuznik, iznos, "-", datumNaloga);
        }

        if(racunPoverilac != null) {
            dailyState(racunPoverilac, iznos, "+", datumNaloga);
        }

        return true;
    }

    private static void dailyState(Racun bill, double amount,
                            String operation, Date date) {

        DnevnoStanjeRacuna dnevnoStanjeRacuna =
                (DnevnoStanjeRacuna) DnevnoStanjeRacuna.find("racun_id",
                        bill.id)
                        .fetch();

        dnevnoStanjeRacuna.prethodnoStanje =
                dnevnoStanjeRacuna.novoStanje;
        if(operation.equals("+")) {
            dnevnoStanjeRacuna.novoStanje =
                    dnevnoStanjeRacuna.prethodnoStanje + amount;
            dnevnoStanjeRacuna.prometUKorist = amount;
        } else if (operation.equals("-")) {
            dnevnoStanjeRacuna.novoStanje =
                    dnevnoStanjeRacuna.prethodnoStanje - amount;
            dnevnoStanjeRacuna.prometUKorist = -amount;
        }

        dnevnoStanjeRacuna.datum = date;
        dnevnoStanjeRacuna.save();
    }
    
    private static String getClientCreditor(Element client,
    										String elementName) {
    	
    	Element klijentEl = (Element) client
                .getElementsByTagName(elementName)
                .item(0);
    	return klijentEl.getTextContent();
    }

    private static String getClient(Element client,
                                    String elementName) {

        Element klijentEl = (Element) client
                .getElementsByTagName(elementName)
                .item(0);

        Element adresaEl = (Element) klijentEl
                .getElementsByTagName("adresa")
                .item(0);

        String adresa = getAddress(adresaEl);

        String klijent = null;
        String tip = klijentEl.getAttribute("tip");
        if(tip.equals("fizicko_lice")) {
            String ime = klijentEl
                    .getElementsByTagName("ime")
                    .item(0)
                    .getTextContent();
            String prezime = klijentEl
                    .getElementsByTagName("prezime")
                    .item(0)
                    .getTextContent();
            klijent = ime + " " + prezime + adresa;
        } else if(tip.equals("pravno_lice")) {
            String naziv = klijentEl
                    .getElementsByTagName("naziv")
                    .item(0)
                    .getTextContent();
            klijent = naziv + adresa;
        }

        return klijent;
    }

    private static Banka getBank(Element client, String
                                 elementName) {
        Element bankaEl = (Element) client
                .getElementsByTagName(elementName)
                .item(0);
        String swift = bankaEl
                .getElementsByTagName("swift")
                .item(0)
                .getTextContent();
        Banka banka = (Banka) Banka.find("bySwiftKod", swift)
                .fetch().get(0);

        return banka;
    }

    private static String getAddress(Element address) {

        String ulica = address.getElementsByTagName("ulica")
                .item(0)
                .getTextContent();

        Element mestoEl = (Element) address
                .getElementsByTagName("mesto")
                .item(0);
        String mesto = mestoEl.getTextContent();
        String postanskiBroj = mestoEl.getAttribute("pb");

        String adresa = "\n" + ulica + "\n"
                + postanskiBroj + " " + mesto;

        return adresa;
    }

    private static Date getDate(String dateStr) {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy.");
        Date date = null;
        try {
            date = format.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    private static boolean finishPayment(Document document, String root) {
        if(root.equals("mt103")) {
            return decomponiseMT103(document);
        } else if (root.equals("mt102")) {
            return decomponiseMT102(document);
        } else {
            System.out.println("Greska!");
        }
        return false;
    }

    private static void readFiles(File[] files) {
        try {
            for(File file : files) {
                document = documentBuilder.parse(file);
                System.out.println(file.getName().substring(0, 5));
                if(finishPayment(document, file.getName().substring(0, 5))) {
                    file.renameTo(new File(neobradjeneUplatnice + "/"
                            + file.getName()));
                    file.delete();
                }
            }
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        dispose();
    }

    private static void dispose() {
        neobradjenoDir = null;
        factory = null;
        documentBuilder = null;
    }

    private static File[] findFiles(String filePrefix) {
        File[] files = neobradjenoDir.listFiles();
        for(int i = 0; i < files.length; i++) {
        	System.out.println(files[i].getName());
        }
        
        return files;
    }

}
