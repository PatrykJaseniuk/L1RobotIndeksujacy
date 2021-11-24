#Lista 4

Zadanie 1.
Uwaga – zadanie do wykonania na ciasteczkach bez użycia sesji!
Stwórz aplikację WEB (logika ma się kryć w serwlecie), gdzie obliczona zostanie wartość iloczynu w następujący sposób:
a) na stronie Internetowej ma się znajdować jedno pole input typu tekstowego, w które użytkownik ma możliwość wprowadzania liczby (uwaga – walidacja ma zostać przeprowadzona po stronie serwera), oraz element tekstowy prezentujący bieżącą wartość.
b) kolejne żądnia użytkownika, które zawierają poprawną wartość liczbową w polu tekstowym mają być brane pod uwagę przy wyznaczaniu iloczynu w następujący sposób – przykład:
żądanie 1 – klient wpisał 3 – na stronie pojawia się wartość 3
żądanie 2 – klient wpisał 2 – na stronie pojawia się wartość 6
żądanie 3 – klient wpisał 4 – na stronie pojawia się wartość 24

dst

***

Zadanie 2.
Uwaga – zadanie do wykonania na ciasteczkach lub sesji (do wyboru) 
Stwórz prostą grę rzut kostką. Założenia:
- użytkownik podaje wartość (1-6),
- serwlet losuje wartość z przedziału <1,6>,
- gdy wartość wczytana od użytkownika zgadza się z wylosowaną wartością, dolicz użytkownikowi określoną liczbę punktów (wartość przyjęta arbitralnie),
- gdy wartość wczytana od użytkownika nie zgadza się z wylosowaną wartością, odejmij użytkownikowi określoną liczbę punktów (wartość przyjęta arbitralnie),
Na stronie internetowej mają być widoczne takie elementy jak:
- liczba punktów,
- wylosowana wartość,
- wartość, którą obstawiał użytkownik.
Załóż, że użytkownik zaczyna grę z arbitralną liczbą punktów na koncie. W momencie, gdy liczba jego punktów dojdzie do 0 należy wyświetlić komunikat o przegranej.

db

***

Zadanie 3.
Uwaga – zadanie do wykonania na sesji!
Stwórz aplikację WEB (logika w serwlecie), na której w kolejnych żądaniach użytkownik podaje kolejne wartości całkowite z przedziału 0 – 10 (walidacja po stronie serwletu). Wartości z kolejnych żądań mają zostać zapamiętane w sesji. Po przyciśnięciu przycisku generuj ma zostać wygenerowany wykres słupkowy w formacie SVG, który prezentuje wartości wczytane od użytkownika (wysokość słupków ma być proporcjonalna do wartości podanych przez użytkownika z kolejnych żądań). Np. Użytkownik podając wartości 2,3,1 i przyciskając przycisk generuj ma uzyskać następujący efekt:

bdb

 
