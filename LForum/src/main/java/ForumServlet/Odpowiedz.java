/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ForumServlet;

import java.util.Date;

/**
 *
 * @author jasyn
 */
public class Odpowiedz
{

    public String tresc;
    public Uzytkownik uzytkownik;
    public Date data;    
    private final int idOdpowiedz;

    Odpowiedz(int idOdpowiedz, Uzytkownik uzytkownik, String trescOdpowiedzi, Date data)
    {
        this.idOdpowiedz = idOdpowiedz;
        this.uzytkownik = uzytkownik;
        this.tresc= trescOdpowiedzi;
        this.data = data;
    }
}
