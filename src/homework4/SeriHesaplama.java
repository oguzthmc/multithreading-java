/*********************************************************************************
** SAKARYA ÜNİVERSİTESİ
** BİLGİSAYAR VE BİLİŞİM BİLİMLERİ FAKÜLTESİ
** BİLGİSAYAR MÜHENDİSLİĞİ BÖLÜMÜ
** BSM208 - PROGRAMLAMA DİLLERİNİN PRENSİPLERİ DERSİ
** 2018-2019 BAHAR DÖNEMİ
**
** ÖDEV NUMARASI..........: 4
** ÖĞRENCİ ADI............: OĞUZHAN TOHUMCU
** ÖĞRENCİ NUMARASI.......: B181210397
** DERSİN ALINDIĞI GRUP...: 1-B
** DOSYA ADI..............: SeriHesaplama.java
**
** AÇIKLAMA
** --------
** Bu program, verilen dosyadaki 4 basamaklı sayıların basamak değerleri 
** toplamlarını seri hesaplama yaparak bulur. Bulunan sonucu ekrana yazdırır.
** Thread kullanılmamıştır.
**********************************************************************************/

package homework4;

//Kullanılan Kütüphaneler
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SeriHesaplama {
    //Sayıların ve basamak değerlerinin tutulduğu tamsayı listeleri
    private final List<Integer> sayiListesi = new ArrayList<>();
    private final List<Integer> basamak1 = new ArrayList<>();
    private final List<Integer> basamak10 = new ArrayList<>();
    private final List<Integer> basamak100 = new ArrayList<>();
    private final List<Integer> basamak1000 = new ArrayList<>();
    
    public final String filename = "Sayilar.txt";   //okunan dosya
    //Dosya okuma için tanımlanan değişkenler
    BufferedReader br = null;
    FileReader fr = null;

    //Okunacak dosyanın constructor aracılığıyla geçirilmesi
    public SeriHesaplama() throws FileNotFoundException {
        
        fr = new FileReader(filename);
        br = new BufferedReader(fr);
        
        try {
            fr = new FileReader(filename);
            br = new BufferedReader(fr);
        } 
        catch (FileNotFoundException exception) {
            System.out.println("FileNotFoundException error");
        }
        catch (Exception exception) {
            System.out.println("Exception error");
        }
    }
    
    //Basamak değerlerinin alınması
    //Basamak değerleri üzerinde oynama yapılmayacağı için set metodları yazılmamıştır.
    public List<Integer> getBasamak1()
    {
        return basamak1;
    } 
    public List<Integer> getBasamak10()
    {
        return basamak10;
    }
    public List<Integer> getBasamak100()
    {
        return basamak100; 
    }
    public List<Integer> getBasamak1000()
    {
        return basamak1000; 
    }
    
    //Dosyadan satır satır okuma işlemi yapılması ve gerekli istisnaların kontrolü 
    public void OkumaIslemi() throws IOException {
        
        try {
            // read line by line
            String line;
            while ((line = br.readLine()) != null) {
                sayiListesi.add(Integer.valueOf(line));
            }
        } 
        catch (IOException exception) {
            System.err.format("IOException: %s%n", exception);
        } 
        finally {
            try {
                if (br != null)
                    br.close();

                if (fr != null)
                    fr.close();
            } 
            catch (IOException exception) {
                System.err.format("IOException: %s%n", exception);
            }
        }
    }
    
    //Basamak değerlerinin toplanması
    public int BasamakToplamiHesapla(List<Integer> liste) {
        int basamakToplami = 0;
        int i = 0;

        while (i < liste.size()) {
            basamakToplami += liste.get(i);
            ++i;
        }

        return basamakToplami;
    }
    
    //Seri hesaplamanın yapıldığı ana metod
    public void SeriHesaplamaIslemiYap() {
        
        int sayi;
        int j = 0;
        int basamak1000Toplam = 0;
        int basamak100Toplam = 0;
        int basamak10Toplam = 0;
        int basamak1Toplam = 0;
        int k;
        String sonuc = "";
        
        //Her basamağın seri bir şekilde hesaplanması
        while (j < sayiListesi.size()) {

            sayi = sayiListesi.get(j);
            basamak1000.add(sayi / 1000);
            sayi = sayi - (basamak1000.get(j) * 1000);

            basamak100.add(sayi / 100);
            sayi = sayi - (basamak100.get(j) * 100);

            basamak10.add(sayi / 10);
            sayi = sayi - (basamak10.get(j) * 10);

            basamak1.add(sayi);
            ++j;
        }

        //Basamak toplamlarının metod çağrısı ile alınması
        basamak1000Toplam = BasamakToplamiHesapla(basamak1000);
        basamak100Toplam = BasamakToplamiHesapla(basamak100);
        basamak10Toplam = BasamakToplamiHesapla(basamak10);
        basamak1Toplam = BasamakToplamiHesapla(basamak1);
        
        String[] sayilar = new String[4];
        for (int i = 0; i < sayilar.length; i++) {
            if (i == 0) {
                sayilar[i] = Integer.toString(basamak1000Toplam);
            }
            else if (i == 1) {
                sayilar[i] = Integer.toString(basamak100Toplam);
            }
            else if (i == 2) {
                sayilar[i] = Integer.toString(basamak10Toplam);
            }
            else {
                sayilar[i] = Integer.toString(basamak1Toplam);
            }
        }
        
        for (k = 0; k < sayilar.length; k++) {
            sonuc += sayilar[k];
        }
        //Sonucun yazdırılması
        System.out.println("Sonuç = " + sonuc);
    }
    
}
