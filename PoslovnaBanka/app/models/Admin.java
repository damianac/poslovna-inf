package models;

import javax.persistence.*;

/**
 * Created by Djordje on 8/24/2017.
 */
@Entity
@DiscriminatorValue("A")
@PrimaryKeyJoinColumn(name = "KLIJENT_ID")
@Table(name = "ADMIN")
public class Admin extends Klijent  {

    @OneToOne
    public Banka banka;

    public Banka getBanka() {
        return banka;
    }

    public void setBanka(Banka banka) {
        this.banka = banka;
    }

    public Admin(Banka banka) {
        this.banka = banka;
    }

    public Admin(String adresa, String email, String telefon, String fax, String username, String password, Mesto mesto, Banka banka) {
        super(adresa, email, telefon, fax, username, password, mesto);
        this.banka = banka;
    }

    public Admin() {
    }
}
