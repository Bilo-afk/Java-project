package odev1;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author G221210592
 * @since 2026-04-16
 *        <p>
 *        Bir şehri temsil eden sınıf. Şehrin adını, bağlı ilçelerini ve
 *        toplam nüfusunu yönetir. Nüfus büyümesi ve bölünme kontrolü
 *        bu sınıf üzerinden koordine edilir.
 *        </p>
 */
public class Sehir {

    private String ad;
    private List<Ilce> ilceler;

    public Sehir(String ad) {
        this.ad = ad;
        this.ilceler = new ArrayList<>();
    }

    public void ilceEkle(Ilce ilce) {
        ilceler.add(ilce);
    }

    public long getNufus() {

        long toplam = 0;
        for (Ilce ilce : ilceler) {
            toplam += ilce.getNufus();
        }
        return toplam;
    }

    public void yaslariArttir() {
        for (Ilce ilce : ilceler) {
            ilce.yaslariArttir();
        }
    }

    public void buyumeUygula(int katsayi) {
        for (Ilce ilce : ilceler) {
            ilce.buyumeUygula(katsayi);
        }
    }

    public boolean bolunmeliMi() {
        return getNufus() >= 1000L;
    }

    public String getAd() {
        return ad;
    }

    public List<Ilce> getIlceler() {
        return ilceler;
    }
}