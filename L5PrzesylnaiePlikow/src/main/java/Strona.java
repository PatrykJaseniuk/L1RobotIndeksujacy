/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author jasyn
 */
public class Strona {
    
    public static View view;
    public static Inpute inputLogin;
    public static Inpute inputHaslo;
    public static Inpute inputDane;
    public static Header info;
    
   public  static void konstruowanieWidoku() {
        view = new View();
        Form form = new Form("Dane");
        inputLogin = new Inpute("Login", "text");
        inputHaslo = new Inpute("Haslo", "text");
        inputDane = new Inpute("Dane", "text");
        form.add(inputLogin);
        form.add(inputHaslo);
        form.add(inputDane);
        view.add(form);
        info = new Header();
        view.add(info);
    }
}
