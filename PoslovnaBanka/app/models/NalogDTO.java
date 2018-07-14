package models;

import java.util.Date;

public class NalogDTO {

	   public String racunduznika;
	   
	   public String sifraPlacanja;
		
	   public String racunPoverioca;
		
	   public String pozivNaBrojZaduzenja;
		
	   public String pozivNaBrojOdobrenja;
		
	   public String modelZaduzenja;
	   
	   public String modelOdobrenja;
	   
	   public double iznos;
	   
	   public String duznik;
	   
	   public String primalac;
	   
	   public String svrhaPlacanja;
	   
	   public Date datumNaloga;
	   
	   public NalogDTO(Nalog n){
		   super();
		   this.datumNaloga=n.datumNaloga;
		   this.duznik=n.duznik;
		   this.iznos=n.iznos;
		   this.modelOdobrenja=n.modelOdobrenja;
		   this.modelZaduzenja=n.modelZaduzenja;
		   this.pozivNaBrojOdobrenja=n.pozivNaBrojOdobrenja;
		   this.pozivNaBrojZaduzenja=n.pozivNaBrojZaduzenja;
		   this.primalac=n.primalac;
		   this.racunduznika=n.racunduznika;
		   this.racunPoverioca=n.racunPoverioca;
		   this.sifraPlacanja=n.sifraPlacanja;
		   this.svrhaPlacanja=n.svrhaPlacanja;
	   }
	   
	   public NalogDTO(){
		   
	   }

	@Override
	public String toString() {
		return "NalogDTO [racunduznika=" + racunduznika + ", sifraPlacanja=" + sifraPlacanja + ", racunPoverioca="
				+ racunPoverioca + ", pozivNaBrojZaduzenja=" + pozivNaBrojZaduzenja + ", pozivNaBrojOdobrenja="
				+ pozivNaBrojOdobrenja + ", modelZaduzenja=" + modelZaduzenja + ", modelOdobrenja=" + modelOdobrenja
				+ ", iznos=" + iznos + ", duznik=" + duznik + ", primalac=" + primalac + ", svrhaPlacanja="
				+ svrhaPlacanja + ", datumNaloga=" + datumNaloga + "]";
	}
	   
	   
}
