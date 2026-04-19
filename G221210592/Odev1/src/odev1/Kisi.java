package odev1;

/**
 *
 * @author G221210592
 * @since 2026-04-16
 *        <p>
 *        Bir mahallede yaşayan kişiyi temsil eden sınıf.
 *        Kişinin benzersiz ID'sini, adını-soyadını ve yaşını tutar.
 *        Oyun boyunca aynı ID'ye sahip iki kişi asla olmaz.
 *        </p>
 */
public class Kisi {

    private int id;
    private String adSoyad;
    private int yas;

    public Kisi(int id, String adSoyad, int yas) {
        this.id = id;
        this.adSoyad = adSoyad;
        this.yas = yas;
    }

    public int getId() {
        return id;
    }

    public String getAdSoyad() {
        return adSoyad;
    }

    public int getYas() {
        return yas;
    }

    public void yasArttir() {
        this.yas++;
    }

    @Override
    public String toString() {
        return id + " - " + adSoyad + " - " + yas;
    }
}