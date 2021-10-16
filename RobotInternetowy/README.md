# Kompilacja i uruchamianie
***
W celu kompilacji i uruchomienia nalezy wskazac sciezke do modulow javafx i dolaczyc moduly: 
'javafx.controls' i 'javafx.fxml'(w przypadku kozystania z pliku FXML)

Oto komendy ktore tego dokonuja:

kompilacja:
`javac --module-path <sciezka do folderu z plikami .jar> --add-modules javafx.controls,javafx.fxml <nazwa pliku.java>`

uruchamianie:
`java --module-path <sciezka do folderu z plikami .jar> --add-modules javafx.controls,javafx.fxml <nazwa pliku>`

W celu skonfigurowanie VS Codium do pracy z javaFX nalezy do pliku launch.json dodac konfiguracje:
`"vmArgs": "java --module-path <sciezka do folderu z modulami javaFX> --add-modules javafx.controls,javafx.fxml"`
