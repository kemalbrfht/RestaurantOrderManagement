
import java.util.ArrayList;
import java.util.List;

public class RestoranYonetimi {
    private List<Masa> masalar;

    public RestoranYonetimi() {
        masalar = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            masalar.add(new Masa(i));
        }
    }

    public void masalariListele() {
        for (Masa masa : masalar) {
            System.out.println("Masa " + masa.getMasaNo() + ": " + (masa.isDolu() ? "Dolu" : "Boş"));
        }
    }

    public Masa masaGetir(int masaNo) {
        if (masaNo < 1 || masaNo > 20) {
            System.out.println("Geçersiz masa numarası!");
            return null;
        }
        return masalar.get(masaNo - 1);
    }

    public void siparisVer(Masa masa, String kategori, String urun, double fiyat) {
        masa.getSiparisler().add(new Siparis(kategori, urun, fiyat));
        System.out.println(urun + " sipariş edildi.");
    }
}
