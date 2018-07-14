package models;

import javax.persistence.*;

/**
 * Created by stefan on 4/3/17.
 */
@Entity
@DiscriminatorValue("F")
@PrimaryKeyJoinColumn(name = "KLIJENT_ID")
@Table(name = "FIZICKO_LICE")
public class FizickoLice extends Klijent {

    @Column(name = "IME", nullable = false)
    public String ime;

    @Column(name = "PREZIME", nullable = false)
    public String prezime;

    @Column(name = "JMBG", nullable = false, unique = true)
    public String jmbg;

    public FizickoLice() {}

    public FizickoLice(String adresa, String email, String telefon,
                       String fax, String username, String password,
                       String ime, String prezime, String jmbg, Mesto mesto) {
        super(adresa, email, telefon, fax, username, password, mesto);
        this.ime = ime;
        this.prezime = prezime;
        this.jmbg = jmbg;
    }

}
