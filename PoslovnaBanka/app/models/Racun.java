package models;

import play.db.jpa.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;

/***********************************************************************
 * Module:  Racun.java
 * Author:  Aleksa
 * Purpose: Defines the Class Racun
 ***********************************************************************/
@Entity
public class Racun extends Model {
   @Column(nullable = false, unique = true)
   public String brojRacuna;
   @OneToMany(mappedBy = "racun")
   public List<DnevnoStanjeRacuna> dnevnoStanjeRacuna;
   @ManyToOne
   public Banka banka;

   @ManyToOne
   @JoinColumn(name="klijent_id")
   public Klijent klijent;
   
   @Column(nullable=false)
   public Date datumOtvaranja;

   public Racun() {
   }

   public Racun(String brojRacuna, List<DnevnoStanjeRacuna> dnevnoStanjeRacuna, Banka banka, Date datumOtvaranja) {
      super();
      this.brojRacuna = brojRacuna;
      this.dnevnoStanjeRacuna = dnevnoStanjeRacuna;
      this.banka = banka;
      this.datumOtvaranja=datumOtvaranja;
   }

   @Override
   public String toString() {
      return "Racun{" +
              "brojRacuna='" + brojRacuna + '\'' +
              ", dnevnoStanjeRacuna=" + dnevnoStanjeRacuna +
              ", banka=" + banka +
              ", klijent=" + klijent +
              ", datumOtvaranja=" + datumOtvaranja +
              '}';
   }
}