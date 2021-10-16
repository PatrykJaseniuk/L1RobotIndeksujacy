package interfaces;

import java.util.Collection;

/**
 * interface do obslugi wyszukiwarki
 */
public interface InterfaceBiedaGoogle
{
    /**
     * rozpoczyna proces indeksowania wybranej striony i jej podstron
     * @param url 
     * url storny do zindeksowania
     * @return
     * zwraca kolekcje zindeksowanych stron 
     */
    Collection<String> indexPage(String url);
    /**
     * wysyla aktualny status procesu indeksowania
     * @return
     * status indeksowania
     */
    String log();
    /**
     * wuszukuje podany keyWord w zindeksowanych stornach i zwraca URL storn gdzie one wystepuja
     * @param keyWords
     * szukany klucz
     * @return
     * tablica z URL stron gdzie wystepuje podane haslo
     */
    Collection<String> search(String keyWords);
}
