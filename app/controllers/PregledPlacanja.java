package controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import models.DnevnoStanjeRacuna;
import models.Nalog;
import models.NalogDTO;
import play.data.validation.Required;
import play.mvc.Controller;

public class PregledPlacanja extends Controller {

	public static void show(ArrayList<NalogDTO> nalozi,boolean empty){
		if(nalozi==null){
			ArrayList<Object> dnevnaStanja= (ArrayList<Object>) DnevnoStanjeRacuna.find("byRacun_idLike", session.get("idRacuna")).fetch();
			List<Long> idDnevnihStanja=new ArrayList<>();
			for (Object o : dnevnaStanja) {
				idDnevnihStanja.add(((DnevnoStanjeRacuna)o).id);
			}
			List<Nalog> naloziDnevnogStanja= Nalog.find("byDnevnoStanjeRacuna_idLike", idDnevnihStanja).fetch();
			ArrayList<NalogDTO> naloziSvi=new ArrayList<>();
			for (Nalog n : naloziDnevnogStanja) {
				NalogDTO nt=new NalogDTO(n);
				System.out.println(nt);
				naloziSvi.add(nt);
			}
			render(naloziSvi,empty);
		}else{
			ArrayList<NalogDTO> naloziSvi=nalozi;
			render(naloziSvi,empty);
		}
	}
	
	public static void filter(Date pocetniDatum, Date krajnjiDatum){

		ArrayList<Object> dnevnaStanja= (ArrayList<Object>) DnevnoStanjeRacuna.find("byRacun_idLikeAndDatumGreaterThanEqualsAndDatumLessThanEquals", session.get("idRacuna"),pocetniDatum,krajnjiDatum).fetch();
		List<Long> idDnevnihStanja=new ArrayList<>();
		for (Object o : dnevnaStanja) {
			idDnevnihStanja.add(((DnevnoStanjeRacuna)o).id);
		}
		if(idDnevnihStanja.size()==0){
			show(null,true);
		}
		else{
			List<Nalog> naloziDnevnogStanja= Nalog.find("byDnevnoStanjeRacuna_idLike", idDnevnihStanja).fetch();
			ArrayList<NalogDTO> nalozi=new ArrayList<>();
			for (Nalog n : naloziDnevnogStanja) {
				nalozi.add(new NalogDTO(n));
			}
			show(nalozi,false);
		}
	}

	public static void filter(@Required String racunduznika){
		List<Nalog> nalozi = Nalog.find("byracunduznikaLike", "%"+ racunduznika +"%").fetch();
		String mode = "edit";
		renderTemplate("PregledPlacanja/show.html", nalozi, mode);
	}

	public static void admin_show(String mode){
		ArrayList<Object> dnevnaStanja= (ArrayList<Object>) DnevnoStanjeRacuna.all().fetch();
		List<Long> idDnevnihStanja=new ArrayList<>();
		for (Object o : dnevnaStanja) {
			idDnevnihStanja.add(((DnevnoStanjeRacuna)o).id);
		}
		List<Nalog> naloziDnevnogStanja= Nalog.all().fetch();
		ArrayList<NalogDTO> naloziSvi=new ArrayList<>();
		for (Nalog n : naloziDnevnogStanja) {
			NalogDTO nt=new NalogDTO(n);
			System.out.println(nt);
			naloziSvi.add(nt);
		}
		render(naloziSvi);
	}
	
}
