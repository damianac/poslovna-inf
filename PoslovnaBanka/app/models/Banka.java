package models;

import play.db.jpa.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
/***********************************************************************
 * Module:  Banka.java
 * Author:  Aleksa
 * Purpose: Defines the Class Banka
 ***********************************************************************/
@Entity
public class Banka extends Model {

    @Column(nullable = false, unique = true)
    public int sifraBanke;
    @Column(nullable = false, unique = true)
    public String nazivBanke;
    @Column(nullable = false, unique = true)
    public String adresaBanke;
    @Column(nullable = false, unique = true)
    public String telefonBanke;
    @Column(nullable = false, unique = true)
    public int PIB;
    @Column(nullable = false, unique = true)
    public String swiftKod;
    @Column(nullable = false, unique = true)
    public String obracunskiRacun;

    @ManyToOne
    public Mesto mesto;
	
    public Banka(){
    	
    }
    
    public Banka(int sifraBanke, String nazivBanke, String adresaBanke, String telefonBanke,
			int PIB, String swiftKod, String obracunskiRacun) {
		super();
		this.sifraBanke = sifraBanke;
		this.nazivBanke = nazivBanke;
		this.adresaBanke = adresaBanke;
		this.telefonBanke = telefonBanke;
		this.PIB = PIB;
		this.swiftKod = swiftKod;
		this.obracunskiRacun = obracunskiRacun;
	}

	@Override
	public String toString() {
		return "Banka [sifraBanke=" + sifraBanke + ", nazivBanke=" + nazivBanke + ", adresaBanke=" + adresaBanke
				+ ", telefonBanke=" + telefonBanke + ", PIB=" + PIB + ", swiftKod=" + swiftKod + ", obracunskiRacun="
				+ obracunskiRacun + ", mesto=" + mesto + "]";
	}

    
}