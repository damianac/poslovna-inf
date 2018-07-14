package controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import models.DnevnoStanjeRacuna;
import models.Nalog;
import models.Racun;
import play.mvc.Controller;

public class RacunDetalji extends Controller {
	
	public static void show(long idRacuna){
		List<Nalog> naloziNaTeret = new ArrayList<Nalog>();
		List<Nalog> naloziUKorist = new ArrayList<Nalog>();
		Racun racun = Racun.find("id", idRacuna).first();
		session.put("idRacuna", racun.id);
		session.put("brojRacuna", racun.brojRacuna);
		int brojDnevnihStanja = 0;
		brojDnevnihStanja= racun.dnevnoStanjeRacuna.size();
		DnevnoStanjeRacuna poslednjednevnoStanjeRacuna= racun.dnevnoStanjeRacuna.get(brojDnevnihStanja-1);
		Collection<Nalog> listNalogaPoslednjegDS= poslednjednevnoStanjeRacuna.nalog;
		for (Nalog nalog : listNalogaPoslednjegDS) {
			if(nalog.racunduznika.equals(racun.brojRacuna)){
				System.out.println("dodao Nalog na teret");
				naloziNaTeret.add(nalog);
			}else{
				System.out.println("dodao Nalog u korist");
				naloziUKorist.add(nalog);
			}
			
		}
		for (Nalog nalog : naloziNaTeret) {
			System.out.println("naloziNaTeret");
			System.out.println(nalog.duznik);
		}
		for (Nalog nalog : naloziUKorist) {
			System.out.println("naloziUKorist");
			System.out.println(nalog.duznik);
		}

		render(racun,naloziNaTeret,naloziUKorist);
	}

}
