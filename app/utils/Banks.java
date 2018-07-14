package utils;

import models.Banka;
import models.Nalog;

public class Banks {

	public static Banka getBankByAccount(String accountBank) {
		
		String bankCode = null;
			
		if(accountBank != null) {
			bankCode = accountBank.substring(0, 3);
		}
		System.out.println(bankCode);
		Banka bankDebtor = (Banka) Banka
				.find("bySifraBanke", Integer.parseInt(bankCode))
				.fetch()
				.get(0);

		return bankDebtor;
	}
	
	
}
