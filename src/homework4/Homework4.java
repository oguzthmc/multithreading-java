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
** DOSYA ADI..............: Homework4.java
**
** AÇIKLAMA
** --------
** Bu program, Java'da multithreading yapısını gerçekleştirir. 4 basamaklı
** sayıların basamak toplamlarını bularak sonucunu hesaplar. Ayrıca seri ve   
** paralel olarak programın hesaplanma süresini bulur. Yalnızca paralel
** hesaplama için thread havuzu oluşturularak 4 farklı thread kullanılmıştır.
**********************************************************************************/

package homework4;

//Kullanılan Kütüphaneler
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Homework4 {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        
        /* Herhangi bir istisna oluşması halinde programın düzgün çalışması için, 
         * diğer iki sınıftan fırlatılabilecek istisnaların yakalanması adına, 
         * try-catch bloklarını kullandım.
         */
        try {
            //Başlangıç sistem zamanının alınması
            long baslangic = System.nanoTime();

            //Basamaklar için tamsayi listeleri
            List<Integer> basamak1000 = new ArrayList<>();
            List<Integer> basamak100 = new ArrayList<>();
            List<Integer> basamak10 = new ArrayList<>();
            List<Integer> basamak1 = new ArrayList<>();

            //Seri hesaplama örneği oluşturulması ve okumanın yapılması
            SeriHesaplama sh = new SeriHesaplama();
            sh.OkumaIslemi();

            //Basamak değerlerinin elde edilmesi
            basamak1000 = sh.getBasamak1000();
            basamak100 = sh.getBasamak100();
            basamak10 = sh.getBasamak10();
            basamak1 = sh.getBasamak1();

            //Basamak değerlerinin toplamının bulunması
            sh.BasamakToplamiHesapla(basamak1000);
            sh.BasamakToplamiHesapla(basamak100);
            sh.BasamakToplamiHesapla(basamak10);
            sh.BasamakToplamiHesapla(basamak1);

            //Seri işlemi yapılması
            sh.SeriHesaplamaIslemiYap();

            //İslem sonrasındaki sistem zamanı hesaplanması
            long bitis = System.nanoTime();
            double islemSuresi = (bitis - baslangic) / 1000000.0;
            //Seri işlem süresinin yazdırılması
            System.out.println("Seri Hesaplama Suresi :  " + String.format("%.3f", islemSuresi) + " milisaniye");

            //Paralel hesaplama için thread havuzu oluşturulması
            ExecutorService threadHavuzu = Executors.newFixedThreadPool(4);
            //Başlangıç sistem zamanının alınması
            baslangic = System.nanoTime();
            
            //4 farklı threadin eş zamanlı çalıştırılması
            threadHavuzu.execute(new ParalelHesaplama(basamak1000));
            threadHavuzu.execute(new ParalelHesaplama(basamak100));
            threadHavuzu.execute(new ParalelHesaplama(basamak10));
            threadHavuzu.execute(new ParalelHesaplama(basamak1));
            //Havuzun kapatılması
            threadHavuzu.shutdown();

            //Bütün işlemler bitene kadar bekle
            while (!threadHavuzu.isTerminated()) {

            }
            
            //İslem sonrasındaki sistem zamanı hesaplanması
            bitis = System.nanoTime();
            islemSuresi = (bitis - baslangic) / 1000000.0;
            //Paralel işlem süresinin yazdırılması
            System.out.println("Paralel Hesaplama Suresi :  " + String.format("%.4f", islemSuresi) + " milisaniye");
        }
        catch (FileNotFoundException exception) {
            exception.printStackTrace(System.out);
        }
        catch (IOException exception) {
            exception.printStackTrace(System.out);
        }
        catch (Exception exception) {
            System.out.println("Exception found");
        }
    }
}
