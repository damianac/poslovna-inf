package utils;

import models.Banka;
import models.Racun;

import java.util.List;
/**
 * Created by stefan on 3/29/17.
 */
public class Bills {

    public static String generateAccountNumber() {
        int bankCode = loadBankInformation();
        int billPartition = loadBillsInformation() + 1;
        String billPartitionStr = String.valueOf(billPartition);
        if(billPartitionStr.length() > 13) {
            return null;
        } else {
            while(billPartitionStr.length() < 13) {
                billPartitionStr = "0" + billPartitionStr;
            }
        }
        // Dobijanje kontrolne sume
        long checksum = 98 - (Long.parseLong(
                bankCode + billPartitionStr) * 100 % 97);

        // Vizuelno sa crticama
        String bill = bankCode + "-" + billPartitionStr + "-" + checksum;
        return bill;
    }

    private static int loadBankInformation() {
        /*
            Uzeo sam prvu banku iz baze pod pretpostavkom
            da je to nasa banka.
            Ako zelite, ID(odnosno sifru) nase banke mogu da citam i iz nekog
            fajla, ili ako imate neku ideju kako da dobijem ovaj ID(sifru)
            slobodno kazite.
            Namjerno je razdvojeno citanje bankovnih informacija
            od ostatka algoritma, da bi metod ucitavanja bio
            nezavisan od logike.
         */
        Banka banka = Banka.findById(1L);
        return (int) banka.sifraBanke;
    }

    private static int loadBillsInformation() {
        /*
            Metoda za citanje poslednjeg tekuceg racuna iz banke.
            Vodim se logikom da se svaki racun inkrementuje za +1.
            Struktura racuna izgleda ovako:
            BBB-XXXXXXXXXXXXX-CC
            BBB-trocifreni deo racuna; sifra banke dodeljena od strane
            Narodne Banke Srbije.
            XXX...X - 13-cifreni broj; Na osnovu odluke("O jedinstvenoj strukturi
            tekucih racuna") Narodne Banke Srbije, dozvoljeno je da se u
            stampanim i pisanim dokumentima izostavljaju vodece nule, ali
            je to u IS i dalje broj od 13 cifara i to treba imati u vidu.
            U stampanim i pisanim dokumentima je takodje dozvoljeno stavljanje
            crtica izmedju razlicitih delova strukture racuna, ali se u IS one
            ne stavljaju.
            CC - kontrolni broj, dobija se na sledeci nacin:
            ostatakPriDeljenju = (BBBXXXXXXXXXXXXX * 100)MOD97;
            CC = 98 - ostatakPriDeljenju;
         */
        List<Racun> bills = Racun.findAll();
        Racun bill = bills.get(bills.size() - 1);
        String[] billPartitions = bill.brojRacuna.split("-");
        return Integer.parseInt(billPartitions[1]);
    }
}
