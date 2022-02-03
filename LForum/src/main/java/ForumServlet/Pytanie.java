/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ForumServlet;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author jasyn
 */
public class Pytanie
{

    public Date data;
    public String tytul;
    public String tresc;
    public final int id;
    public Uzytkownik uzytkownik;
    public List<Odpowiedz> odpowiedzi=new LinkedList<>();

    Pytanie(int id, Uzytkownik uzytkownik, Date data, String tytul, String tresc, List<Odpowiedz> odpowiedzi)
    {
        this.id = id;
        this.data =data;
        this.tytul = tytul;
        this.tresc = tresc;
        this.uzytkownik= uzytkownik;
        this.odpowiedzi = odpowiedzi;
    }
    
}
