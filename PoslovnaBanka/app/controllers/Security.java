package controllers;

import models.Klijent;

public class Security extends controllers.Secure.Security{
	/**
	pronalazi usera po username i passwordu i vraca true ukoliko ga je pronasao
	 */
	 static boolean authenticate(String username, String password) {
	        Klijent user = Klijent.find("username", username).first();
	        return user != null && user.password.equals(password);
	    }
}