package odev1;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author G221210592
 * @since 2026-04-16
 * <p>
 * Bir şehre bağlı ilçeyi temsil eden sınıf.
 * İlçenin adını, bağlı mahallelerini ve bunlardan hesaplanan
 * toplam nüfusunu yönetir.
 * </p>
 */
public class Ilce {

    private String ad;
    private List<Mahalle> mahalleler;

    
    public Ilce(String ad) {
        this.ad = ad;
        this.mahalleler = new ArrayList<>();
    }

   
    public void mahalleEkle(Mahalle m) {
        mahalleler.add(m);
    }

   
    public long getNufus() {
        long toplam = 0;
        for (Mahalle m : mahalleler) {
            toplam += m.getNufus();
        }
        return toplam;
    }

   
    public void yaslariArttir() {
        for (Mahalle m : mahalleler) {
            m.yaslariArttir();
        }
    }

    
    public void buyumeUygula(int katsayi) {
        for (Mahalle m : mahalleler) {
            m.buyumeUygula(katsayi);
        }
    }

   
    public List<Mahalle> getMahalleler() {
        return mahalleler;
    }

   
    public String getAd() {
        return ad;
    }
}