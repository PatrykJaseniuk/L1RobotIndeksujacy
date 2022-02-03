/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ForumServlet;

import java.util.Objects;
import sun.security.pkcs11.wrapper.Functions;

/**
 *
 * @author jasyn
 */
public class Uzytkownik
{

    public String login;
    public final int hashHaslo;
    int id;

    Uzytkownik(String login, String haslo)
    {
       this.login = login; 
       this.hashHaslo = haslo.hashCode();    
    }

    Uzytkownik(int id, String login, int hashHaslo)
    {
        this.id =id;
        this.login = login;
        this.hashHaslo = hashHaslo;        
    }

    @Override
    public boolean equals(Object ob)
    {
        
        Uzytkownik innyUzytkownik = (Uzytkownik)ob;
        if(this.login.equals(innyUzytkownik.login) && this.hashHaslo == innyUzytkownik.hashHaslo)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
}
