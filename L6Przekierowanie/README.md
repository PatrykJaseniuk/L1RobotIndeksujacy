# Lista 6

W zastosowaniach WEB często wykorzystuje się serwisy skracające długość adresu URL – przykład serwisów:
- https://bitly.com/
- https://tiny.pl/
Stwórz aplikację JEE, z wykorzystaniem poznanych na wykładzie sposobów przekierowań, która będzie posiadała następującą funkcjonalność: 

## Zadanie 1.
Aplikacja będzie posiadała zdefiniowany plik tekstowy (dla chętnych może być plik XML lub baza danych), w którym znajdą się odpowiednie dane pozwalające na przekierowanie użytkownika ze skróconego linku (dostępnego w obrębie aplikacji WEB) do docelowej strony Internetowej (w zadaniu załóż, że aby dodać nowy element należy ręcznie edytować plik).
Przykład działania (koncepcyjny):
W pasku adresu przeglądarki np. wpisujemy:
http://localhost:port/ścieżka_do_serwletu?strona=xyz123
link nas przenosi np. do
https://www.google.pl/maps/place/Mercure+Warszawa+Centrum/@52.2434043,20.9997351,15z/data=!4m18!1m9!2m8!1sHotele!3m6!1sHotele!2sOpas%C5%82y+Tom,+Wierzbowa+9,+00-094+Warszawa!3s0x471eccf665add9cb:0xa383dee2c3099606!4m2!1d21.0084899!2d52.2433882!3m7!1s0x471ecc8dce994863:0xd498a1094d594437!5m2!4m1!1i2!8m2!3d52.2307646!4d21.0010569
(adres zaczerpnięty z https://tiny.pl/)

dst

## Jak Korzystac

W Servlecie w funkcji `private void wypelnianieMapy(Map<String, String> mapaUrl)` jest scieżka do pliku którą trzeba zmodyfikować.
W pliku dwie kolejne linie tworzą pare linków, w każdej lini znajduje się tylko jeden link.
