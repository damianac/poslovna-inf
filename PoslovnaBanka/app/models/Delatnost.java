package models;

import play.db.jpa.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by stefan on 4/3/17.
 */
@Entity
@Table(name = "DELATNOST")
public class Delatnost extends Model {

    @Column(name = "SIFRA", unique = true, nullable = false)
    public String sifra;

    @Column(name = "NAZIV", nullable = false, unique = true)
    public String naziv;

    public Delatnost() {}

    public Delatnost(String sifra, String naziv) {
        super();
        this.sifra = sifra;
        this.naziv = naziv;
    }
}
