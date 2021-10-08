package classes;

import interfaces.InterfaceBiedaGoogle;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        //System.out.print(new String[]);
        InterfaceBiedaGoogle biedaGoogle = new BiedaGoogle();
        System.out.println(biedaGoogle.log());
        biedaGoogle.indexPage("http://www.parys.nysa.pl");
        System.out.print((biedaGoogle.search(new String[]{"Andrzej", "Magda"})));        
    }
}
