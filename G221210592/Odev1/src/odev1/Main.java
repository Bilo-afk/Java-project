package odev1;

/**
 *
 * @author G221210592
 * @since 2026-04-16
 *        <p>
 *        Uygulamanın giriş noktası. Oyun nesnesi oluşturulur ve başlatılır.
 *        Tüm oyun akışı Oyun sınıfı tarafından yönetilir.
 *        </p>
 */
public class Main {

    public static void main(String[] args) {
        Oyun oyun = new Oyun();
        oyun.baslat();
    }
}