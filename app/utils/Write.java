package utils;

import models.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;
import play.Play;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by stefan on 3/30/17.
 */
public class Write {

    /*private static final String JAXP_SCHEMA_LANGUAGE =
            "http://java.sun.com/xml/jaxp/properties/schemaLanguage";
    private static final String W3C_XML_SCHEMA =
            "http://www.w3.org/2001/XMLSchema";*/

    private static Document document = null;
    private static String uplatePath = Play.configuration
            .getProperty("uplatnice.neobradjene.path");
    private static String mt102xsd = Play.configuration
            .getProperty("my102.xsd.path");
    private static String mt103xsd = Play.configuration
            .getProperty("my103.xsd.path");

    public static void createMT102toXML(MT10X mt102) {

        init();

        //<mt102>
        Element root = document.createElement("mt102");
        root.setAttributeNode(document.createAttribute("idPoruke"));
        root.setAttribute("idPoruke", String.valueOf(mt102.id));
        root.setAttributeNS("http://www.w3.org/2001/XMLSchema-instance",
                "xsi:schemaLocation",
                "https://github.com/JerSefJeTu/PoslovnaBanka/mt102 " + mt102xsd);

        Element zaglavlje = document.createElement("zaglavlje");
        Element bankaDuznik = createBank("banka_duznik",
                mt102.swiftKod1, mt102.obracunskiRacun1);
        Element bankaPoverilac = createBank("banka_poverilac",
                mt102.swiftKod2, mt102.obracunskiRacun2);
        Element ukupanIznos = document.createElement("ukupan_iznos");
        Text ukupanIznosText = document.createTextNode(String.valueOf(mt102.ukupanIznos));
        ukupanIznos.appendChild(ukupanIznosText);
        Element valuta = createCurrency(mt102.datum);
        Element datum = document.createElement("datum");
        Text datumText = document.createTextNode(String.valueOf(mt102.datum));
        datum.appendChild(datumText);

        zaglavlje.appendChild(bankaDuznik);
        zaglavlje.appendChild(bankaPoverilac);
        zaglavlje.appendChild(ukupanIznos);
        zaglavlje.appendChild(valuta);
        zaglavlje.appendChild(datum);

        Element listaNaloga = document
                .createElement("lista_naloga");
        for(Nalog n : mt102.nalog) {
            Element nalog = createAccountMT102(n);
            listaNaloga.appendChild(nalog);
        }

        root.appendChild(zaglavlje);
        root.appendChild(listaNaloga);

        writeToFile(root, mt102.id, "mt102");
    }

    private static Klijent findClient(Nalog account) {
        Racun racunDuznika = (Racun)Racun.find("byBrojRacuna", account.racunduznika)
                .fetch().get(0);

        //DUZNIK
        Klijent duznik = Klijent.findById(racunDuznika.klijent.id);

        return duznik;
    }

    private static Element createAccountMT102(Nalog account) {

        Klijent klijentDuznik = findClient(account);

        String klijentPoverilac = account.primalac;

        Element nalog = document.createElement("nalog");
        nalog.setAttributeNode(document.createAttribute("idNaloga"));
        nalog.setAttribute("idNaloga", String.valueOf(account.id));

        Element duznik = createClient(klijentDuznik, "duznik");
        Element poverilac = createClientCreditor(klijentPoverilac);
        Element podaciNalog = createAccountData(account);

        Element uplata = document.createElement("uplata");
        Element zaduzenje = createPayment("zaduzenje",
                account.racunduznika,
                String.valueOf(account.modelZaduzenja),
                account.pozivNaBrojZaduzenja);
        Element odobrenje = createPayment("odobrenje",
                account.racunPoverioca,
                String.valueOf(account.modelOdobrenja),
                account.pozivNaBrojOdobrenja);
        Element iznos = document.createElement("iznos");
        Text iznosText = document.createTextNode(String.valueOf(account.iznos));
        iznos.appendChild(iznosText);

        uplata.appendChild(zaduzenje);
        uplata.appendChild(odobrenje);
        uplata.appendChild(iznos);

        nalog.appendChild(duznik);
        nalog.appendChild(poverilac);
        nalog.appendChild(podaciNalog);
        nalog.appendChild(uplata);

        return nalog;
    }

    private static Element createAccountData(Nalog account) {
        Element podaciNalog = document.createElement("podaci_nalog");

        Element svrhaPlacanja = document.createElement("svrha_placanja");
        Text svrhaPlacanjaText = document.createTextNode(account.svrhaPlacanja);
        svrhaPlacanja.appendChild(svrhaPlacanjaText);

        Element datum = document.createElement("datum");
        Text datumText = document.createTextNode(String.valueOf(
                account.datumNaloga));
        datum.appendChild(datumText);

        Element sifraValute = document.createElement("sifra_valute");
        Text sifraValuteText = document.createTextNode("RSD");
        sifraValute.appendChild(sifraValuteText);

        podaciNalog.appendChild(svrhaPlacanja);
        podaciNalog.appendChild(datum);
        podaciNalog.appendChild(sifraValute);

        return podaciNalog;
    }

    public static void createMT103toXML(MT10X mt103) {

        Klijent client = findClient(mt103.nalog.get(0));

        Klijent klijentDuznik = client;

        String klijentPoverilac = mt103.nalog.get(0).primalac;

        Element banka = null;
        Element klijent = null;

        init();

        //<mt103>
        Element root = document.createElement("mt103");
        root.setAttributeNode(document.createAttribute("idPoruke"));
        root.setAttribute("idPoruke", String.valueOf(mt103.id));
        root.setAttributeNS("http://www.w3.org/2001/XMLSchema-instance",
                "xsi:schemaLocation",
                "https://github.com/JerSefJeTu/PoslovnaBanka/mt103 " + mt103xsd);

        Element duznik = document.createElement("duznik");
        banka = createBank("banka",
                mt103.swiftKod1, mt103.obracunskiRacun1);
        klijent = createClient(klijentDuznik, "klijent");
        duznik.appendChild(banka);
        duznik.appendChild(klijent);

        Element poverilac = document.createElement("poverilac");
        banka = createBank("banka",
                mt103.swiftKod2, mt103.obracunskiRacun2);
        klijent = createClientCreditor(klijentPoverilac);
        poverilac.appendChild(banka);
        poverilac.appendChild(klijent);

        // Ako je mt103, onda postoji samo jedan nalog
        // stoga sam taj nalog i uzeo.
        Nalog nalogRef = mt103.nalog.get(0);

        Element nalog = createAccountMT103(nalogRef);

        Element uplata = document.createElement("uplata");
        Element zaduzenje = createPayment("zaduzenje",
                nalogRef.racunduznika,
                String.valueOf(nalogRef.modelZaduzenja),
                nalogRef.pozivNaBrojZaduzenja);
        Element odobrenje = createPayment("odobrenje",
                nalogRef.racunPoverioca,
                String.valueOf(nalogRef.modelOdobrenja),
                nalogRef.pozivNaBrojOdobrenja);
        Element iznos = document.createElement("iznos");
        Text iznosText = document.createTextNode(String.valueOf(nalogRef.iznos));
        iznos.appendChild(iznosText);

        uplata.appendChild(zaduzenje);
        uplata.appendChild(odobrenje);
        uplata.appendChild(iznos);

        root.appendChild(duznik);
        root.appendChild(poverilac);
        root.appendChild(nalog);
        root.appendChild(uplata);

        writeToFile(root, mt103.id, "mt103");
    }

    private static void init() {
        DocumentBuilderFactory factory = DocumentBuilderFactory
                .newInstance();

        /*factory.setNamespaceAware(true);
        factory.setValidating(true);
        //U slucaju da bude bila greska
        //Proveriti da li validira XSD 1.1
        factory.setAttribute(JAXP_SCHEMA_LANGUAGE,
                             W3C_XML_SCHEMA);*/

        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            document = builder.newDocument();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    private static void writeToFile(Element root, long id,
                                    String messageType) {
        TransformerFactory tFactory = TransformerFactory.newInstance();
        Transformer transformer = null;
        try {
            transformer = tFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(root);
            StreamResult result = new StreamResult(new File(uplatePath + messageType
                    + "_" + id + ".xml"));
            transformer.transform(source, result);
        } catch (TransformerConfigurationException e1) {
            e1.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    private static Element createPayment(
            String nazivElementa,
            String racun,
            String model,
            String poziv) {

        Element ZO = document.createElement(nazivElementa);

        Element brojRacuna = document
                .createElement("broj_racuna");
        Text brojRacunaText = document.
                createTextNode(racun);
        brojRacuna.appendChild(brojRacunaText);

        Element brojModela = document
                .createElement("broj_modela");
        Text brojModelaText = document
                .createTextNode(model);
        brojModela.appendChild(brojModelaText);

        Element pozivNaBroj = document
                .createElement("poziv_na_broj");
        Text pozivNaBrojText = document
                .createTextNode(poziv);
        pozivNaBroj.appendChild(pozivNaBrojText);

        ZO.appendChild(brojRacuna);
        ZO.appendChild(brojModela);
        ZO.appendChild(pozivNaBroj);

        return ZO;
    }

    private static Element createAccountMT103(Nalog account) {
        Element nalog = document.createElement("nalog");

        Element svrhaPlacanja = document.createElement("svrha_placanja");
        Text svrhaPlacanjaText = document.createTextNode(account.svrhaPlacanja);
        svrhaPlacanja.appendChild(svrhaPlacanjaText);

        Element valuta = createCurrency(account.datumNaloga);

        Element datum = document.createElement("datum");
        Text datumText = document.createTextNode(String.valueOf(account.datumNaloga));
        datum.appendChild(datumText);

        nalog.appendChild(svrhaPlacanja);
        nalog.appendChild(valuta);
        nalog.appendChild(datum);

        return nalog;
    }

    private static Element createCurrency(Date date) {
        Element valuta = document.createElement("valuta");

        Element datum = document.createElement("datum");
        Text datumText = document.createTextNode(
                String.valueOf(date));
        datum.appendChild(datumText);

        Element sifra = document.createElement("sifra");
        Text sifraText = document.createTextNode("RSD");
        sifra.appendChild(sifraText);

        valuta.appendChild(datum);
        valuta.appendChild(sifra);

        return valuta;
    }

    private static Element createBank(String bankaElement,
                                      String swiftKod,
                                      String obracunskiRacun) {

        Element banka = document.createElement(bankaElement);

        Element swift = document.createElement("swift");
        Text swiftText = document.createTextNode(swiftKod);
        swift.appendChild(swiftText);

        Element brojRacuna = document.createElement("broj_racuna");
        Text brojRacunaText = document.createTextNode(obracunskiRacun);
        brojRacuna.appendChild(brojRacunaText);

        banka.appendChild(swift);
        banka.appendChild(brojRacuna);

        return banka;
    }

    private static Element createClient(Klijent client, String tagName) {

        Element klijent = null;

        if(client instanceof FizickoLice) {
            klijent = createIndividual((FizickoLice) client, tagName);
        } else if (client instanceof PravnoLice) {
            klijent = createLegalEntity((PravnoLice) client, tagName);
        } else {
            System.out.print("Greska!");
        }

        return klijent;

    }

    private static Element createClientCreditor(String client) {

        Element klijent = document.createElement("poverilac");
        Text klijentText = document.createTextNode(client);
        klijent.appendChild(klijentText);

        return klijent;

    }

    private static Element createLegalEntity(PravnoLice legalEntity, String tagName) {
        Element klijent = document.createElement(tagName);

        klijent.setAttributeNode(document.createAttribute("tip"));
        klijent.setAttribute("tip", "pravno_lice");

        Element naziv = document.createElement("naziv");
        Text nazivText = document.createTextNode(legalEntity.naziv);
        naziv.appendChild(nazivText);

        Element adresa = createAddress(legalEntity.adresa,
                legalEntity.mesto);

        klijent.appendChild(naziv);
        klijent.appendChild(adresa);

        return klijent;
    }

    private static Element createIndividual(FizickoLice individual, String tagName) {
        Element klijent = document.createElement(tagName);

        klijent.setAttributeNode(document.createAttribute("tip"));
        klijent.setAttribute("tip", "fizicko_lice");

        Element ime = document.createElement("ime");
        Text imeText = document.createTextNode(individual.ime);
        ime.appendChild(imeText);

        Element prezime = document.createElement("prezime");
        Text prezimeText = document.createTextNode(individual.prezime);
        prezime.appendChild(prezimeText);

        Element adresa = createAddress(individual.adresa,
                individual.mesto);

        klijent.appendChild(ime);
        klijent.appendChild(prezime);
        klijent.appendChild(adresa);

        return klijent;
    }

    private static Element createAddress(String address, Mesto location) {
        Element adresa = document.createElement("adresa");

        Element ulica = document.createElement("ulica");
        Text ulicaText = document.createTextNode(address);
        ulica.appendChild(ulicaText);

        Element mesto = document.createElement("mesto");
        mesto.setAttributeNode(document.createAttribute("pb"));
        mesto.setAttribute("pb", String.valueOf(location.postanskiBroj));
        Text mestoText = document.createTextNode(location.naziv);
        mesto.appendChild(mestoText);

        adresa.appendChild(ulica);
        adresa.appendChild(mesto);

        return adresa;
    }

}