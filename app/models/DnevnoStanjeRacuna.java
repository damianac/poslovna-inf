package models;
/***********************************************************************
 * Module:  DnevnoStanjeracuna.java
 * Author:  Aleksa
 * Purpose: Defines the Class DnevnoStanjeracuna
 ***********************************************************************/
import play.db.jpa.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;

@Entity
public class DnevnoStanjeRacuna extends Model {

   @Column(nullable = false)
   public Date datum;
   @Column(nullable = false)
   public double prethodnoStanje;
   @Column(nullable = false)
   public double prometUKorist;
   @Column(nullable = false)
   public double novoStanje;
   @OneToMany(mappedBy = "dnevnoStanjeRacuna")
   public List<Nalog> nalog;
   @ManyToOne
   public Racun racun;

   public DnevnoStanjeRacuna() {
   }

   public DnevnoStanjeRacuna(Date datum, double prethodnoStanje, double prometUKorist, double novoStanje, List<Nalog> nalog, Racun racun) {
	   super();
      this.datum = datum;
      this.prethodnoStanje = prethodnoStanje;
      this.prometUKorist = prometUKorist;
      this.novoStanje = novoStanje;
      this.nalog = nalog;
      this.racun = racun;
   }

}