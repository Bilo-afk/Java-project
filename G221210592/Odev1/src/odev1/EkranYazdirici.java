package odev1;

import java.util.List;

/**
 *
 * @author G221210592
 * @since 2026-04-16
 * <p>
 * Ekrana şehir nüfuslarını ve şehir detaylarını yazdıran yardımcı sınıf.
 * Her satıra en fazla 5 şehir gelecek şekilde biçimlendirilmiş çıktı üretir.
 * Tek sorumluluk prensibiyle yalnızca görüntüleme işlemlerini üstlenir.
 * </p>
 */
public class EkranYazdirici {

    /**
     * Tüm şehirlerin nüfuslarını "5 per satır" formatında ekrana yazdırır.
     *
     * Örnek çıktı:
     * [21]-[36]-[99]-[66]-[48]
     * [32]-[36]-[15]-[24]-[66]
     *
     * @param sehirler Yazdırılacak şehirlerin listesi
     */
    public static void sehirleriYazdir(List<Sehir> sehirler) {
        for (int i = 0; i < sehirler.size(); i++) {
            System.out.print("[" + sehirler.get(i).getNufus() + "]");

           
            boolean satirSonu = ((i + 1) % 5 == 0);
            boolean sonEleman = (i == sehirler.size() - 1);

            if (satirSonu || sonEleman) {
                System.out.println();
            } else {
                System.out.print("-"); // şehirler arası ayraç
            }
        }
    }

   
    public static void sehirDetayYazdir(Sehir sehir) {
        System.out.println("---------------------------------------------------------");
        System.out.println("Sehir: " + sehir.getAd() + " - Nufus: " + sehir.getNufus());

        for (Ilce ilce : sehir.getIlceler()) {
            System.out.println("Ilce: " + ilce.getAd() + " - Nufus: " + ilce.getNufus());

            for (Mahalle mahalle : ilce.getMahalleler()) {
                System.out.println("Mahalle: " + mahalle.getAd() + " - Nufus: " + mahalle.getNufus());
                System.out.println("Kisiler:");

              
                for (Kisi kisi : mahalle.getKisiler()) {
                    System.out.println("\t" + kisi);
                }
            }
        }
    }
}