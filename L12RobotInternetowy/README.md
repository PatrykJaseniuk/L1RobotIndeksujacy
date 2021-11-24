# L1, L2 Robot Indeksujacy

## Zadanie:
- opracowac GUI
- Użytkownik wprowadza adres URL strony od ktorej ma się zacząć zbieranie linków.
W komponencie poniżej prezentować listę niepowtarzalnych linków.

## Wymagania:
- Komentarze tworzące dokumentację 
- Kod ma być obiektowy
- Wielowątkowość
- Użycie wybranej kolekcji

## wykonanie:
- IDE: VSCodium
- parsing strony internetowej z wykorzystaniem biblioteki jsoup https://jsoup.org/
- gui JavaFX

## Kompilacja i uruchamianie

W celu kompilacji i uruchomienia nalezy wskazac sciezke do modulow javafx i dolaczyc moduly: 
'javafx.controls' i 'javafx.fxml'(w przypadku kozystania z pliku FXML)

Oto komendy ktore tego dokonuja:

kompilacja:

`javac --module-path <sciezka do folderu z plikami .jar> --add-modules javafx.controls,javafx.fxml <nazwa pliku.java>`

uruchamianie:

`java --module-path <sciezka do folderu z plikami .jar> --add-modules javafx.controls,javafx.fxml <nazwa pliku>`

W celu skonfigurowanie VS Codium do pracy z javaFX nalezy do pliku launch.json dodac konfiguracje:

`"vmArgs": "--module-path <sciezka do folderu z modulami javaFX> --add-modules javafx.controls,javafx.fxml"`
