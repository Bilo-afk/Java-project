package odev1;

import com.github.javafaker.Faker;
import java.util.Locale;
import java.util.Random;

/**
 *
 * @author G221210592
 * @since 2026-04-16
 * <p>
 * Java Faker kütüphanesi aracılığıyla rastgele isim, yer adı ve yaş
 * üreten servis sınıfı. Başlangıç kişileri Faker ile oluşturulur.
 * Büyüme sırasında eklenen yeni sakinler için hizliIsim() kullanılır;
 * bu metot sabit Türkçe isim listesinden seçim yapar — Faker'ın
 * yansıma (reflection) maliyeti olmadan binlerce kişi hızlıca üretilebilir.
 * </p>
 */
public class FakerServisi {

    private static final Faker faker = new Faker(Locale.forLanguageTag("tr"));
    private static final Random rnd  = new Random();

    private static final String[] HIZLI_ISIM_HAVUZU = new String[2000];

    static {
      
        for (int i = 0; i < HIZLI_ISIM_HAVUZU.length; i++) {
            HIZLI_ISIM_HAVUZU[i] = faker.name().fullName();
        }
    }

  
    public static String kisiAdi() {
        return faker.name().fullName();
    }

    
    public static String hizliIsim() {
        return HIZLI_ISIM_HAVUZU[rnd.nextInt(HIZLI_ISIM_HAVUZU.length)];
    }

   
    public static String sehirAdi() {
        return faker.address().city();
    }

  
    public static String ilceAdi() {
        return faker.address().streetName();
    }

   
    public static String mahalleAdi() {
        return faker.address().secondaryAddress();
    }

    
    public static int rastgeleYas() {
        // Performans optimizasyonu: Faker arkaplanda çok ağır çalıştığı için basit sayı üretimi
        // doğrudan Native Random sınıfı üzerinden yapılır. Büyük sayılarda tıkanmayı engeller.
        return rnd.nextInt(51);
    }
}