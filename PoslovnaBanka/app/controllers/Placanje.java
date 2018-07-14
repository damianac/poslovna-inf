package controllers;

import java.util.Date;
import java.util.List;

import models.Banka;
import models.DnevnoStanjeRacuna;
import models.MT10X;
import models.Nalog;
import models.Racun;
import play.data.validation.Valid;
import play.mvc.Controller;
import utils.Banks;
import utils.Write;

@SuppressWarnings("unchecked")
public class Placanje extends Controller {
	
	public static void show(){
		List<Racun> racuni = Racun.find("klijent_id", session.get("idKlijenta")).fetch();
        render(racuni);
	}
	
	public static void add(@Valid Nalog nalog, boolean hitno){
		String messageType = hitno == true ? "MT103" : "MT102";
		MT10X mt10x = createMT10X(nalog, messageType);
		if(messageType.equals("MT103")) {
			Write.createMT103toXML(mt10x);
		}
		show();
	}
	
	public static MT10X createMT10X(Nalog nalog, String messageType) {
		
		Banka bankaPoverioca = null;
		Banka bankaDuznika = null;
		if(nalog.racunPoverioca != null)
			bankaPoverioca = Banks.getBankByAccount(nalog.racunPoverioca);
		
		if(nalog.racunduznika != null)
			bankaDuznika = Banks.getBankByAccount(nalog.racunduznika);
		
		
		MT10X mt10x = null;
		if(messageType.equals("MT102")) {
			List<MT10X> list = MT10X.find("bySwiftKod2", bankaPoverioca.swiftKod)
			 		 .fetch();
			
			if(list.size() < 1 || list == null) {
				mt10x = null;
			} else {
				mt10x = list.get(0);
			}
			
			if(mt10x == null || mt10x.obradjeno == true) {
				// MT102
				mt10x = new MT10X();
				mt10x.datum = new Date();
				mt10x.swiftKod1 = bankaPoverioca.swiftKod;
				mt10x.swiftKod2 = bankaDuznika.swiftKod;
				mt10x.obracunskiRacun1 = bankaPoverioca.obracunskiRacun;
				mt10x.obracunskiRacun2 = bankaDuznika.obracunskiRacun;
				mt10x.nalog.add(nalog);
				mt10x.ukupanIznos = nalog.iznos;
				mt10x.vrstaPoruke = "MT102";
				mt10x.clearing = null;
			} else {
				mt10x.nalog.add(nalog);
				for(Nalog n : mt10x.nalog) {
					mt10x.ukupanIznos += n.iznos;
				}
				mt10x.clearing = null;
			}
		} else {
			// MT103 - RTGS
			mt10x = new MT10X();
			mt10x.datum = new Date();
			mt10x.swiftKod1 = bankaPoverioca.swiftKod;
			mt10x.swiftKod2 = bankaDuznika.swiftKod;
			mt10x.obracunskiRacun1 = bankaPoverioca.obracunskiRacun;
			mt10x.obracunskiRacun2 = bankaDuznika.obracunskiRacun;
			mt10x.nalog.add(nalog);
			mt10x.ukupanIznos = nalog.iznos;
			mt10x.obradjeno = true;
			mt10x.vrstaPoruke = "MT103";
			mt10x.clearing = null;
		}
		
		nalog.datumNaloga = new Date();
		nalog.mt10x = mt10x;
		nalog.obradjen = true;
		
		Racun racun = (Racun) Racun.find("byBrojRacuna", nalog.racunduznika)
				.fetch().get(0);
		DnevnoStanjeRacuna dnevnoStanjeRacuna = (DnevnoStanjeRacuna) DnevnoStanjeRacuna
				.find("byRacun_id", racun.id)
				.fetch().get(0);
		dnevnoStanjeRacuna.novoStanje = 
				dnevnoStanjeRacuna.prethodnoStanje - nalog.iznos;
		
		dnevnoStanjeRacuna.prometUKorist = 
				dnevnoStanjeRacuna.novoStanje - dnevnoStanjeRacuna.prethodnoStanje;
		
		dnevnoStanjeRacuna.datum = new Date();
		
		nalog.dnevnoStanjeRacuna = dnevnoStanjeRacuna;
		dnevnoStanjeRacuna.save();
		
		mt10x.save();

		return mt10x;
	}

}
