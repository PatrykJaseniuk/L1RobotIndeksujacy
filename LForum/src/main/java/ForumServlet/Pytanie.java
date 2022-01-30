/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ForumServlet;

/**
 *
 * @author jasyn
 */
public class Pytanie
{

    public String data;
    public String tytul;
    public String tresc;
    public final int id;

    Pytanie(int id, int data, String tytul, String tresc)
    {
        this.id = id;
        this.data =Integer.toString(data);
        this.tytul = tytul;
        this.tresc = tresc;
    }
    
}
