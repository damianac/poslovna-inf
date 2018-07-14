package models;

import play.db.jpa.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import java.util.Date;
/***********************************************************************
 * Module:  GasenjeRacuna.java
 * Author:  Aleksa
 * Purpose: Defines the Class GasenjeRacuna
 ***********************************************************************/
@Entity
public class GasenjeRacuna extends Model {

   @Column(nullable = false)
   public Date datumGasenja;
   @Column(nullable = false)
   public String prenosNaRacun;
   @OneToOne
   public Racun racun;

   public GasenjeRacuna() {
   }

   public GasenjeRacuna(Date datumGasenja, String prenosNaRacun, Racun racun) {
      super();
	   this.datumGasenja = datumGasenja;
      this.prenosNaRacun = prenosNaRacun;
      this.racun = racun;
   }


}