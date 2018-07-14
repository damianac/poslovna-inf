package controllers;

import play.mvc.*;
import play.mvc.Scope.Session;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import models.*;

@With(Secure.class)
public class Application extends Controller {

    public static void index() {
    	String username = Security.connected();
    	Session session = Scope.Session.current();
    	Klijent user = Klijent.find("username", username).first();
    	//to store values into the session
    	if(user instanceof PravnoLice)
    	{
    		PravnoLice klijent = (PravnoLice) user;
    		System.out.println();
    		session.put("naziv", klijent.naziv);
    		session.put("ime", "");
    		session.put("prezime", "");
    		session.put("idKlijenta", klijent.id);
    		
    		for (Racun racun : klijent.racuni) {
				System.out.println(racun.brojRacuna);
			}

			Racuni.show();
    	}else if (user instanceof FizickoLice){
    		FizickoLice klijent = (FizickoLice) user;
    		session.put("ime", klijent.ime);
    		session.put("prezime", klijent.prezime);
    		session.put("naziv", "");
    		session.put("idKlijenta", klijent.id);
			Racuni.show();
    		
    		for (Racun racun : klijent.racuni) {
				System.out.println(racun.brojRacuna);
			}
    	}else {
			models.Admin admin = (models.Admin) user;
			session.put("banka", admin.getBanka().swiftKod);
			Admin.show("");
		}
    	
    	Runnable runnable = new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println("HI");
			}
		};
		
		ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
		executor.scheduleAtFixedRate(runnable, 0, 3, TimeUnit.SECONDS);
    	
    }

}