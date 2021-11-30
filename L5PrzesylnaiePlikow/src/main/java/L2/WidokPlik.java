/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package L2;

import Widok.DataList;
import Widok.FileInpute;
import Widok.Form;
import Widok.Header;
import Widok.Inpute;
import Widok.View;
import java.util.Arrays;

/**
 *
 * @author jasyn
 */
public class WidokPlik {

    public static View view;
    public static DataList listaFolderow;
    public static Header info;
    public static FileInpute file;

    public static void konstruowanieWidoku() {
        view = new View();

        Form form = new Form("Plik");
        view.add(form);

        listaFolderow = new DataList(Arrays.asList("Folder1", "Folder2", "Folder3", "Folder4", "Folder5 "), "Wybierz Folder");
        form.add(listaFolderow);

        file = new FileInpute("Plik");
        form.add(file);

        info = new Header();
        view.add(info);

    }
}
