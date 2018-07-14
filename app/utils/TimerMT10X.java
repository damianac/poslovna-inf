package utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import models.Clearing;
import models.MT10X;
import play.jobs.Every;
import play.jobs.Job;

@Every("35s")
public class TimerMT10X extends Job {
	
	public void doJob() {
		createClearing();
	}
	
	private void createClearing() {
		Clearing clearing = new Clearing();
		clearing.datumIVreme = new Date();
		
		try {
			List<MT10X> mt102 = MT10X.find("byObradjenoAndVrstaPorukeLike", false, "%MT102%").fetch();
				clearing.poruke.addAll(mt102);
				if(clearing.poruke != null && clearing.poruke.size() > 0) {
					for(MT10X m : clearing.poruke) {
						m.obradjeno = true;
						m.clearing = clearing;
						Write.createMT102toXML(m);
					}
					clearing.save();
				}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
