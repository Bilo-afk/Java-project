package odev1;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author G221210592
 * @since 2026-04-16
 * <p>
 * Bir ilçeye bağlı mahalleyi ve mahallede yaşayan kişileri yöneten sınıf.
 * Nüfus doğrudan kisiler listesinin boyutundan okunur; ayrı bir sayaç
 * tutulmaz. Bu sayede detay ekranı ile tur ekranı her zaman tutarlı kalır.
 * Büyüme sırasında eklenen yeni sakinler hızlı isim üretici ile oluşturulur.
 * </p>
 */
public class Mahalle {

    private String ad;
   
    private List<Kisi> kisiler;
    
    // Performans optimizasyonu: Liste belleği tıkadığı için gerçek nüfusu 
    // listeden bağımsız matematiksel olarak sayıyoruz.
    private long gercekNufus;

    
    public Mahalle(String ad) {
        this.ad = ad;
        this.kisiler = new ArrayList<>();
        this.gercekNufus = 0;
    }

   
    public void kisiEkle(Kisi kisi) {
        if (kisiler.size() < 1000) {
            kisiler.add(kisi);
        }
        gercekNufus++;
    }

    
    public long getNufus() {
        return gercekNufus;
    }

    
    public void yaslariArttir() {
        for (Kisi k : kisiler) {
            k.yasArttir();
        }
    }

   
    public void buyumeUygula(int katsayi) {
        if (katsayi == 0) {
            if (kisiler.size() < 1000) {
                kisiler.add(new Kisi(IdUretici.yeniIdAl(), FakerServisi.hizliIsim(), FakerServisi.rastgeleYas()));
            } else {
                IdUretici.idAtla(1);
            }
            gercekNufus++;
        } else {
            long eskiNufus = gercekNufus;
            long eklenecek = eskiNufus * (katsayi - 1);
            gercekNufus += eklenecek;
            
            // Performans optimizasyonu: Belleği kurtarmak için (OOM hatası önlemi), 
            // fiziksel listeye (görsel olarak listelenecek kişi sayısına) bir limit (1000) koyuyoruz.
            // Sayıları tam tutarak kalan ID'leri matematiksel olarak atlıyoruz.
            long eklenecekGorsel = Math.min(eklenecek, 1000 - kisiler.size());
            
            if (eklenecekGorsel > 0) {
                if (kisiler instanceof ArrayList) {
                    ((ArrayList<Kisi>) kisiler).ensureCapacity(Math.min(1000, kisiler.size() + (int)eklenecekGorsel));
                }
                for (int i = 0; i < (int)eklenecekGorsel; i++) {
                    kisiler.add(new Kisi(IdUretici.yeniIdAl(), FakerServisi.hizliIsim(), FakerServisi.rastgeleYas()));
                }
            }
            
            // Görsel olarak listeye eklenmeyen ama var olan kişilerin ID değerlerini global atlatıyoruz.
            long atlanacakId = eklenecek - Math.max(0, eklenecekGorsel);
            if(atlanacakId > 0) {
                IdUretici.idAtla(atlanacakId);
            }
        }
    }

   
    public List<Kisi> getKisiler() {
        return kisiler;
    }

   
    public String getAd() {
        return ad;
    }
}