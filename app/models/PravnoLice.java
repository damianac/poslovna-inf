package models;

import javax.persistence.*;

/**
 * Created by stefan on 4/3/17.
 */
@Entity
@DiscriminatorValue("P")
@PrimaryKeyJoinColumn(name = "KLIJENT_ID")
@Table(name = "PRAVNO_LICE")
public class PravnoLice extends Klijent {

    @Column(name = "NAZIV", nullable = false)
    public String naziv;

    @Column(name = "MATICNI_BROJ", nullable = false, unique = true)
    public String maticniBroj;

    @Column(name = "PIB", nullable = false, unique = true)
    public String pib;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_DELATNOSTI")
    public Delatnost delatnost;

    public PravnoLice() {}

    public PravnoLice(String adresa, String email, String telefon,
                      String fax, String username, String password,
                      String naziv, String maticniBroj, String pib,
                      Delatnost delatnost, Mesto mesto) {
        super(adresa, email, telefon, fax, username, password, mesto);
        this.naziv = naziv;
        this.maticniBroj = maticniBroj;
        this.pib = pib;
        this.delatnost = delatnost;
    }

}
