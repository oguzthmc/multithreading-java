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
** DOSYA ADI..............: ParalelHesaplama.java
**
** AÇIKLAMA
** --------
** Bu program, verilen dosyadaki 4 basamaklı sayıların basamak değerleri 
** toplamlarını paralel hesaplama yaparak bulur. Ayrıca, Runnable sınıfına ait 
** run metodu override edilmiştir.
**********************************************************************************/

package homework4;

import java.util.List;

public class ParalelHesaplama implements Runnable {
    
    public List<Integer> liste;
    public ParalelHesaplama(List<Integer> digerListe) {
        liste = digerListe;
    }
    
    //Basamak değerlerinin paralel olarak hesaplanması
    public int BasamakToplamiParalelHesapla(List<Integer> listeList) {
        
        int basamakToplami = 0;
        int i = 0;
        
        while (i < listeList.size()) {
            basamakToplami += listeList.get(i);
            ++i;
        }
        return basamakToplami;
    }
    
    @Override
    public void run() {
       BasamakToplamiParalelHesapla(liste);
    }
}
