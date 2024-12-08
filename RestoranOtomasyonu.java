
import java.util.Map;
import java.util.Scanner;

public class RestoranOtomasyonu {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RestoranYonetimi restoran = new RestoranYonetimi();
        Menu menu = new Menu();

        while (true) {
            System.out.println("\n1. Masaları Listele");
            System.out.println("2. Masa Yönetimi");
            System.out.println("3. Menüyü Görüntüle");
            System.out.println("4. Çıkış");
            System.out.print("Seçiminiz: ");
            int secim = scanner.nextInt();

            switch (secim) {
                case 1:
                    restoran.masalariListele();
                    break;

                case 2:
                    System.out.print("Masa numarasını girin: ");
                    int masaNo = scanner.nextInt();
                    Masa masa = restoran.masaGetir(masaNo);
                    if (masa == null) break;

                    if (!masa.isDolu()) {
                        System.out.print("Bu masayı açmak istiyor musunuz? (Evet=1 / Hayır=2): ");
                        int cevap = scanner.nextInt();
                        if (cevap == 1) masa.masaAc();
                        break;
                    }

                    System.out.println("\n1. Sipariş Ver");
                    System.out.println("2. Sipariş Durumu");
                    System.out.println("3. Hesap Öde");
                    System.out.print("Seçiminiz: ");
                    int masaSecim = scanner.nextInt();

                    switch (masaSecim) {
                        case 1:
                            System.out.println("\nKategoriler:");
                            System.out.println("1. Ana Yemekler");
                            System.out.println("2. İçecekler");
                            System.out.println("3. Tatlılar");
                            System.out.print("Kategori seçin: ");
                            int kategoriSecim = scanner.nextInt();

                            String kategori = "";
                            if (kategoriSecim == 1) kategori = "Ana Yemek";
                            else if (kategoriSecim == 2) kategori = "İçecek";
                            else if (kategoriSecim == 3) kategori = "Tatlı";
                            else {
                                System.out.println("Geçersiz kategori!");
                                break;
                            }

                            Map<String, Double> seciliKategori = menu.getKategori(kategori);
                            if (seciliKategori == null) {
                                System.out.println("Kategori bulunamadı!");
                                break;
                            }

                            System.out.println("\n" + kategori + ":");
                            int urunIndex = 1;
                            for (String urun : seciliKategori.keySet()) {
                                System.out.println(urunIndex + ". " + urun + " (" + seciliKategori.get(urun) + " TL)");
                                urunIndex++;
                            }

                            System.out.print("Ürün seçin: ");
                            int urunSecim = scanner.nextInt();
                            String[] urunler = seciliKategori.keySet().toArray(new String[0]);
                            if (urunSecim < 1 || urunSecim > urunler.length) {
                                System.out.println("Geçersiz ürün seçimi!");
                                break;
                            }

                            String seciliUrun = urunler[urunSecim - 1];
                            double fiyat = seciliKategori.get(seciliUrun);
                            restoran.siparisVer(masa, kategori, seciliUrun, fiyat);
                            break;

                        case 2:
                            System.out.println("Sipariş Durumu:");
                            for (Siparis siparis : masa.getSiparisler()) {
                                System.out.println("- " + siparis.getKategori() + ": " + siparis.getUrun() + " (" + siparis.getFiyat() + " TL)");
                            }
                            break;

                        case 3:
                            double toplamHesap = masa.hesaplaHesap();
                            System.out.println("Toplam Hesap: " + toplamHesap + " TL");
                            System.out.print("Hesap ödensin mi? (Evet=1 / Hayır=2): ");
                            int hesapOnay = scanner.nextInt();
                            if (hesapOnay == 1) {
                                masa.masaKapat();
                            }
                            break;
                    }
                    break;

                case 3:
                    while (true) {
                        System.out.println("\nMenü:");
                        System.out.println("1. Ana Yemekler");
                        System.out.println("2. İçecekler");
                        System.out.println("3. Tatlılar");
                        System.out.println("4. Ana Menüye Dön");
                        System.out.print("Kategori seçin: ");
                        int kategoriSecim = scanner.nextInt();

                        if (kategoriSecim == 4) {
                            System.out.println("Ana menüye dönülüyor...");
                            break;
                        }

                        String kategori = "";
                        if (kategoriSecim == 1) kategori = "Ana Yemek";
                        else if (kategoriSecim == 2) kategori = "İçecek";
                        else if (kategoriSecim == 3) kategori = "Tatlı";
                        else {
                            System.out.println("Geçersiz kategori!");
                            continue;
                        }

                        Map<String, Double> seciliKategori = menu.getKategori(kategori);
                        if (seciliKategori == null) {
                            System.out.println("Kategori bulunamadı!");
                            continue;
                        }

                        System.out.println("\n" + kategori + ":");
                        int urunIndex = 1;
                        String[] urunler = seciliKategori.keySet().toArray(new String[0]);
                        for (int i = 0; i < urunler.length; i++) {
                            System.out.println((i + 1) + ". " + urunler[i] + " (" + seciliKategori.get(urunler[i]) + " TL)");
                        }

                        System.out.print("Ürün seçin (Ana menüye dönmek için 0 girin): ");
                        int urunSecim = scanner.nextInt();
                        if (urunSecim == 0) break;

                        if (urunSecim < 1 || urunSecim > urunler.length) {
                            System.out.println("Geçersiz ürün seçimi!");
                            continue;
                        }

                        String seciliUrun = urunler[urunSecim - 1];
                        System.out.println(seciliUrun + " seçtiniz.");
                    }
                    break;

                case 4:
                    System.out.println("Çıkış yapılıyor...");
                    return;

                default:
                    System.out.println("Geçersiz seçim!");
            }
        }
    }
}
