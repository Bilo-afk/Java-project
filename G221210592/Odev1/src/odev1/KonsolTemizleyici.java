package odev1;

/**
 *
 * @author G221210592
 * @since 2026-04-16
 *        <p>
 *        Konsol ekranını temizleyen yardımcı sınıf.
 *        İşletim sistemine göre uygun temizleme yöntemi otomatik seçilir:
 *        Windows'ta "cls" komutu, diğer sistemlerde ANSI escape kodu
 *        kullanılır.
 *        </p>
 */
public class KonsolTemizleyici {

    public static void temizle() {
        try {
            String os = System.getProperty("os.name").toLowerCase();
            if (System.console() != null) {
                // Gerçek konsol: işletim sistemine özgü komut
                if (os.contains("win")) {
                    new ProcessBuilder("cmd", "/c", "cls")
                            .inheritIO()
                            .start()
                            .waitFor();
                } else {
                    // Linux/Mac için ayrı komut
                    new ProcessBuilder("clear")
                            .inheritIO()
                            .start()
                            .waitFor();
                }
            } else {

                System.out.print("\033[2J\033[H");
                System.out.flush();
            }
        } catch (Exception e) {

            for (int i = 0; i < 50; i++) {
                System.out.println();
            }
        }
    }
}