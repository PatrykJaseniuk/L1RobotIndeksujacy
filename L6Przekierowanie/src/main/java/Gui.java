
import Widok.Form;
import Widok.Header;
import Widok.Input;
import Widok.View;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author jasyn
 */
public class Gui
{    
    public static Input input;
    public static View view;
    static void KonstruowanieGui()
    {
        view = new View();
        
        Header naglowek = new Header();
        naglowek.setText("Podaj Adres");
        view.add(naglowek);
        
        Form formularz = new Form("Przekierowanie", "get");
        
        input = new Input("URL","text");
        formularz.add(input);
        
        view.add(formularz);       
    }
}
