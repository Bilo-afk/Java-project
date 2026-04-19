package odev1;

/**
 *
 * @author G221210592
 * @since 2026-04-16
 * <p>
 * Oyun boyunca benzersiz kişi ID'leri üreten yardımcı sınıf.
 * Her çağrıda bir sonraki ID otomatik olarak 1 artırılır;
 * böylece iki kişinin aynı ID'ye sahip olması engellenir.
 * </p>
 */
public class IdUretici {

    private static int sonrakiId = 1;

   
    public static int yeniIdAl() {
        return sonrakiId++;
    }
    
    public static void idAtla(long miktar) {
        sonrakiId += miktar;
    }
}