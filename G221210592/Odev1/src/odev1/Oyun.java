package odev1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author G221210592
 * @since 2026-04-16
 * <p>
 * Oyunun ana akışını yöneten sınıf. Kullanıcıdan tur sayısı ve şehir
 * verilerini alır; her turda nüfus büyümesini uygular, gerekirse şehirleri
 * böler ve sonuçları ekrana yazdırır. Oyun bitiminde kullanıcının seçtiği
 * şehrin detaylarını görüntüler.
 * </p>
 */
public class Oyun {

    private List<Sehir> sehirler;
    private Scanner scanner;

    public Oyun() {
        sehirler = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    
    public void baslat() {
        turSayisiniVeSehirleriOlustur();
    }

   
    private void turSayisiniVeSehirleriOlustur() {
        System.out.print("Oyunun çalışacağı tur sayısını giriniz: ");
        int turSayisi = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Şehir sayılarını aralarında boşluk bırakarak giriniz ");
        String satir = scanner.nextLine();

       
        String[] parcalar = satir.trim().split("\\s+");

        for (String parca : parcalar) {
            int sayi;
            try {
                sayi = Integer.parseInt(parca);
            } catch (NumberFormatException e) {
               
                System.out.println("Hata: Geçersiz sayı atlandı: " + parca);
                continue;
            }

            
            if (sayi < 10 || sayi > 99) {
                System.out.println("Hata: Lütfen iki basamaklı sayı giriniz: " + sayi);
                continue;
            }

            
            int ilceSayisi    = sayi / 10;
            int mahalleSayisi = sayi % 10;

           
            if (ilceSayisi == 0 || mahalleSayisi == 0) {
                System.out.println("Hata: İlçe ve mahalle sayısı sıfır olamaz: " + sayi);
                continue;
            }

            Sehir sehir = sehirOlustur(sayi);
            sehirler.add(sehir);
        }

        if (sehirler.isEmpty()) {
            System.out.println("Geçerli bir şehir girilmedi. Oyun başlatılamıyor.");
            return;
        }

       
        KonsolTemizleyici.temizle();
        System.out.println("İlk tura başlamadan nüfus durumu:");
        EkranYazdirici.sehirleriYazdir(sehirler);

       
        for (int tur = 1; tur <= turSayisi; tur++) {
          
            birTurOynat();

            
            sehirleriBol();

            
            KonsolTemizleyici.temizle();
            System.out.println(tur + ". tur sonu:");
            EkranYazdirici.sehirleriYazdir(sehirler);

           
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        oyunSonuSecim();
    }

   
    private Sehir sehirOlustur(int sayi) {
        int ilceSayisi    = sayi / 10;
        int mahalleSayisi = sayi % 10;

       
        mahalleSayisi = NufusHelper.mahalleSayisiniDuzelt(ilceSayisi, mahalleSayisi);

        
        int nufus = NufusHelper.nufusuDuzelt(sayi, mahalleSayisi);

        Sehir sehir = new Sehir(FakerServisi.sehirAdi());

       
        int mahallePerIlce   = mahalleSayisi / ilceSayisi;
        int kisiPerMahalle   = nufus / mahalleSayisi;

        for (int i = 0; i < ilceSayisi; i++) {
            Ilce ilce = new Ilce(FakerServisi.ilceAdi());

            for (int j = 0; j < mahallePerIlce; j++) {
                Mahalle mahalle = new Mahalle(FakerServisi.mahalleAdi());

                for (int k = 0; k < kisiPerMahalle; k++) {
                    Kisi kisi = new Kisi(
                        IdUretici.yeniIdAl(),
                        FakerServisi.kisiAdi(),
                        FakerServisi.rastgeleYas()  // başlangıçta 0-50 arası
                    );
                    mahalle.kisiEkle(kisi);
                }

                ilce.mahalleEkle(mahalle);
            }

            sehir.ilceEkle(ilce);
        }

        return sehir;
    }

   
    private void birTurOynat() {
        for (Sehir sehir : sehirler) {
            // Katsayıyı önce al; büyüme sırasında nüfus değişmesin diye
            int katsayi = NufusHelper.buyumeKatsayisi(sehir.getNufus());
            sehir.buyumeUygula(katsayi);
            sehir.yaslariArttir(); // yaş artışı büyümeden sonra yapılıyor
        }
    }

    
    private void sehirleriBol() {
      
        List<Sehir> yeniSehirler = new ArrayList<>();

        for (Sehir eskiSehir : sehirler) {
            // 4 basamaklı mı? (>= 1000)
            if (eskiSehir.bolunmeliMi()) {
                Sehir yeniSehir = sehriBol(eskiSehir);
                if (yeniSehir != null && yeniSehir.getNufus() > 0) {
                    yeniSehirler.add(yeniSehir);
                }
            }
        }

       
        sehirler.addAll(yeniSehirler);
    }

    
    private Sehir sehriBol(Sehir eskiSehir) {
        List<Ilce> ilceler = eskiSehir.getIlceler();
        int toplamIlce = ilceler.size();

        Sehir yeniSehir = new Sehir(FakerServisi.sehirAdi());

        if (toplamIlce >= 2) {
            
            int yeniSehreGidecek = toplamIlce / 2; 

          
            List<Ilce> tasinacaklar = new ArrayList<>();
            for (int i = toplamIlce - yeniSehreGidecek; i < toplamIlce; i++) {
                tasinacaklar.add(ilceler.get(i));
            }
            ilceler.removeAll(tasinacaklar);
            for (Ilce ilce : tasinacaklar) {
                yeniSehir.ilceEkle(ilce);
            }

        } else {
            
            Ilce eskiIlce = ilceler.get(0);
            List<Mahalle> mahalleler = eskiIlce.getMahalleler();
            int toplamMahalle = mahalleler.size();

           
            if (toplamMahalle < 2) {
                return null;   // bölünemez
            }

            int yeniIlceyeGidecek = toplamMahalle / 2;   // floor

            List<Mahalle> tasinacaklar = new ArrayList<>();
            for (int i = toplamMahalle - yeniIlceyeGidecek; i < toplamMahalle; i++) {
                tasinacaklar.add(mahalleler.get(i));
            }
            mahalleler.removeAll(tasinacaklar);

           
            Ilce yeniIlce = new Ilce(FakerServisi.ilceAdi());
            for (Mahalle m : tasinacaklar) {
                yeniIlce.mahalleEkle(m);
            }
            yeniSehir.ilceEkle(yeniIlce);
        }

        // Nüfus sıfır çıktıysa bölünme gerçekleşmedi demektir
        if (yeniSehir.getNufus() == 0) {
            return null;
        }
        return yeniSehir;
    }

    // -------------------------------------------------------------------------
    // Oyun sonu
    // -------------------------------------------------------------------------

   
    private void oyunSonuSecim() {
        KonsolTemizleyici.temizle();
        System.out.println("--- OYUN BİTTİ ---");
        EkranYazdirici.sehirleriYazdir(sehirler);

      
        System.out.println("Incelemek istediginiz sehrin satir ve sutununu giriniz");
        System.out.print("Satir: ");
        int satir = scanner.nextInt();

        System.out.print("Sutun: ");
        int sutun = scanner.nextInt();

        int index = satir * 5 + sutun;

        if (index >= 0 && index < sehirler.size()) {
            System.out.println();
            EkranYazdirici.sehirDetayYazdir(sehirler.get(index));
        } else {
            System.out.println("Geçersiz satır/sütun seçimi.");
        }

        System.out.println();
        System.out.println("Cikmak icin herhangi bir tusa ve Enter'a basin...");
        scanner.nextLine(); // önceki newline'ı temizle
        scanner.nextLine(); // kullanıcı girişini bekle
    }
}