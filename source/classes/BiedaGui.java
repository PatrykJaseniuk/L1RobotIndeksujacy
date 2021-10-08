package classes;

import java.util.Collection;

import interfaces.InterfaceBiedaGoogle;

public class BiedaGui
{
    InterfaceBiedaGoogle biedaGogle = new BiedaGoogle();

    BiedaGui()
    {
        indeksujStrone();
        wyszukiwanie();
    }

    private String zapytajOaddresUrlDoZindeksowania()
    {
        System.out.println("podaj adres url do zindeksowania: ");
        return System.console().readLine();
    }

    private void indeksujStrone()
    {

        while (true)
        {
            String url = zapytajOaddresUrlDoZindeksowania();
            Collection<String> zindeksowaneStrony = biedaGogle.indexPage(url);

            if (zindeksowaneStrony.size() == 0)
            {
                System.out.println("Nie udalo sie otworzyc stron: " + url);
                continue;
            } else
            {
                System.out.println("zindeksowane striny: ");
                for (String zindeksowanaStrona : zindeksowaneStrony)
                {
                    System.out.println(zindeksowanaStrona);
                }
                break;
            }
        }
    }

    private void wyszukiwanie()
    {
        while (true)
        {
            wyszukajSlowKluczowychIWyswietlUrlStornNaKtorychSieZnajduja(zapytajOSlowaKluczowe());
            // if (!zapytajCzyDalejWyszukiwac())
            // {
            //     break;
            // }
        }
    }

    private String zapytajOSlowaKluczowe()
    {
        System.out.println("podaj slowa do wyszukania: ");
        return System.console().readLine();
    }

    private void wyszukajSlowKluczowychIWyswietlUrlStornNaKtorychSieZnajduja(String slowaKluczowe)
    {
        Collection<String> stronyZeSlowamiKluczowymi = biedaGogle.search(slowaKluczowe);

        if (stronyZeSlowamiKluczowymi==null)
        {
            System.out.println("nie znaleziono slow");
        } else
        {
            System.out.println("wyniki wyszukiwania: ");
            for (String strona : stronyZeSlowamiKluczowymi)
            {
                System.out.println(strona);
            }
        }
    }

    private boolean zapytajCzyDalejWyszukiwac()
    {

        while (true)
        {
            System.out.println("czy wyszukiwac dalej? [T/n] ");
            String odpowiedz = System.console().readLine();
            if (odpowiedz == "T" | odpowiedz == "t")
            {
                return true;
            } else if (odpowiedz == "n" | odpowiedz == "N")
            {
                return false;
            }
        }
    }
}
