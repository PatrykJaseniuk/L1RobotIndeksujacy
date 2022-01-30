/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ForumServlet;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author jasyn
 */
public class Kernel
{
    public static final String nazwaAtrybutuSesji="uzytkownik";
    public static final String appUrl="L67Forum";

    public static boolean logIn(String login, String haslo)
    {
        if("Borys".equals(login)&&"Borys".equals(haslo))
            return true;
        else
        {
            return false;
        }
    }
    
    public static List<Pytanie> getNajnowszePytania()
    {
        List<Pytanie> pytania= new LinkedList<>();
         pytania.add(new Pytanie(1,2022,"Ile jest gwiazd na niebie", "Kto to potrafi zliczyc"));
        pytania.add(new Pytanie(2,2022,"Ile wazy ogien", "Tresc pytania"));        
       return pytania;
    }    

    public static List<Pytanie> wyszukajPytan(String wyszukiwaneHaslo)
    {
        List<Pytanie> pytania = new LinkedList<>();
        pytania.add(new Pytanie(3,2022,"Byc albo nie byc", "Oto jest Pytanie"));
        pytania.add(new Pytanie(4,2022,"Ile jest gwiazd na niebie", "Kto to potrafi zliczyc"));
        pytania.add(new Pytanie(5,2022,"Ile wazy ogien", "Tresc pytania"));
        
        return pytania;
    }

    public static Integer getIloscPytan()
    {
        return 100;
    }

    public static Integer getOdpowiedzi()
    {
        return 1234;
    }
}
