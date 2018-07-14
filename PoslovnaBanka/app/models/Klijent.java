package models;

import play.db.jpa.Model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by stefan on 4/3/17.
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "TIP_LICA")
@Table(name = "KLIJENT")
public abstract class Klijent extends Model {

    @Column(name = "ADRESA")
    public String adresa;

    @Column(name = "EMAIL", unique = true)
    public String email;

    @Column(name = "TELEFON")
    public String telefon;

    @Column(name = "FAX")
    public String fax;

    @Column(name = "USERNAME", unique = true, nullable = false)
    public String username;

    @Column(name = "PASSWORD", nullable = false)
    public String password;

    @OneToMany(mappedBy="klijent")
    public List<Racun> racuni;

    @ManyToOne
    public Mesto mesto;

    public Klijent() {}

    public Klijent(String adresa, String email, String telefon,
                   String fax, String username, String password, Mesto mesto) {
        super();
        this.adresa = adresa;
        this.email = email;
        this.telefon = telefon;
        this.fax = fax;
        this.username = username;
        this.password = password;
        this.mesto = mesto;
    }
}
