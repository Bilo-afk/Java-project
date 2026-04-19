package odev1;

/**
 *
 * @author G221210592
 * @since 2026-04-16
 * <p>
 * Nüfus ile ilgili yardımcı hesaplama metodlarını içeren sınıf.
 * Mahalle sayısı düzeltme, nüfus düzeltme ve büyüme katsayısı hesaplama
 * işlemlerini tek sorumluluk prensibiyle yürütür.
 * </p>
 */
public class NufusHelper {

   
    public static int mahalleSayisiniDuzelt(int ilceSayisi, int mahalleSayisi) {
       
        if (mahalleSayisi % ilceSayisi == 0) {
            return mahalleSayisi;
        }

       
        for (int deneme = mahalleSayisi + 1; deneme <= 9; deneme++) {
            if (deneme % ilceSayisi == 0) {
                return deneme;
            }
        }

       
        for (int deneme = 1; deneme <= 9; deneme++) {
            if (deneme % ilceSayisi == 0) {
                return deneme;
            }
        }

       
        return ilceSayisi;
    }

   
    public static int nufusuDuzelt(int nufus, int mahalleSayisi) {
        
        if (nufus % mahalleSayisi == 0) {
            return nufus;
        }

       
        int yeni = nufus;
        while (yeni % mahalleSayisi != 0) {
            yeni++;
        }
        return yeni;
    }
    public static int buyumeKatsayisi(long nufus) {
       
        long sonIkiBasamak = nufus % 100;
        int onlar = (int)(sonIkiBasamak / 10);
        int birler = (int)(sonIkiBasamak % 10);
        return onlar + birler;   // 0 ise Mahalle özel durumu ele alır
    }
}