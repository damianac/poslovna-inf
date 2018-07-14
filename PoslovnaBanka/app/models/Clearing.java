package models;

import play.db.jpa.Model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
@Entity
public class Clearing extends Model {

	@Column(nullable=false)
	public Date datumIVreme;
	
	@OneToMany(mappedBy = "clearing", cascade = {CascadeType.ALL})
	@Column(nullable=false)
	public List<MT10X> poruke;

	public Clearing(){
		this.poruke = new ArrayList<>();
	}
	
	public Clearing(Date datumIVreme, List<MT10X> poruke) {
		super();
		this.datumIVreme = datumIVreme;
		this.poruke = poruke;
	}
	
	
}
