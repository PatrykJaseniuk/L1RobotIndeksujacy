/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ForumServlet.GuiWspolne;

import ForumServlet.Kernel;
import ForumServlet.LogowanieActivity.Logowanie;
import ForumServlet.RejestracjaActivity.RejestracjaActivity;
import ForumServlet.WynikWyszukiwaniaActivity.WynikWyszukiwaniaActivity;
import ForumServlet.Uzytkownik;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author jasyn
 */
class GuiStanZalogowania implements GuiElement
{

    public static int iloscGui=0;
    public String urlLogowanie = Logowanie.url ;
    public String urlRejestracja = RejestracjaActivity.url;
    String urlWyloguj="";
    String nazwaParametruWyloguj="";
    
    GuiStanZalogowania()
    {
        nazwaParametruWyloguj = "wyloguj"+iloscGui;
        urlWyloguj=WynikWyszukiwaniaActivity.url+"?"+nazwaParametruWyloguj+"= ";
    }

    @Override
    public String getHtml(HttpServletRequest request)
    {
        processButton(request);
        
        String wnetrze = "";

        HttpSession sesia = request.getSession();
        Uzytkownik uzytkownik = (Uzytkownik) sesia.getAttribute(Kernel.nazwaAtrybutuSesji);
        if (uzytkownik != null)
        {
            
            wnetrze = "<h3>Witaj " + uzytkownik.login + "</h3>"
                    + " <a class=\"btn btn-primary\" href=\""+urlWyloguj+"\" role=\"button\">Wyloguj</a>\n";
            
        } else
        {
            wnetrze = "<div class=\"row m-2\">\n"
                    + "            <a class=\"btn btn-primary\" href=\""+urlLogowanie+"\" role=\"button\">Logowanie</a>\n"
                    + "        </div>\n"
                    + "        <div class=\"row m-2\">\n"
                    + "             <a class=\"btn btn-primary\" href=\""+urlRejestracja+"\" role=\"button\">Rejestracja</a>\n"
                    + "        </div>";
        }

        String html = " <div class=\"container-sm\">\n"
                + wnetrze
                + "    </div>";

        return html;

    }

    private void processButton(HttpServletRequest request)
    {
        if(request.getParameter(nazwaParametruWyloguj)!=null)
        {
            HttpSession session = request.getSession();
            session.setAttribute(Kernel.nazwaAtrybutuSesji, null);
        }
    }

}
