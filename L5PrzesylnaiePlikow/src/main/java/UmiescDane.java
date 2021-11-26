
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author jasyn
 */
public class UmiescDane {

    static String fileName = "C:\\borys\\dane.txt";

    static public void umiescDane(HttpServletRequest request) {

        String dane = Strona.inputDane.getText(request);
        try {
            File myObj = new File(fileName);
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
            FileWriter fileWriter = new FileWriter(fileName);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.printf(dane);
            printWriter.close();
        } catch (Exception e) {
            System.out.println("nie udalo sie zapisac do pliku");
        }
    }
}
