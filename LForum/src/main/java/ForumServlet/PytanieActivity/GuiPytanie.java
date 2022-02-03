/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ForumServlet.PytanieActivity;

import ForumServlet.GuiWspolne.GuiElement;
import ForumServlet.Kernel;
import ForumServlet.LogowanieActivity.Logowanie;
import ForumServlet.Odpowiedz;
import ForumServlet.Pytanie;
import ForumServlet.PytanieActivity.PytanieActivity;
import ForumServlet.Uzytkownik;
import java.util.function.BiConsumer;
import java.util.function.Function;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author jasyn
 */
public class GuiPytanie implements GuiElement
{

    private Function<Integer, Pytanie> dostarczycielPytania = null;
    private String nazwaParametruTrescOdpowiedzi = "trescOdowiedzi";
    public BiConsumer<HttpSession, Integer> onDeleteButtonClick=null;
    private final String nazawaParametruDelete="deletePytanie";

    @Override
    public String getHtml(HttpServletRequest request)
    {
        Pytanie pytanie = dostarczycielPytania.apply(PytanieActivity.getIdPytania(request));
       String htmlPrzyciskiAdmin = getAndProsessPrzyciskiAdmina(request,pytanie);
        String htmlDodajOdpowiedz = getHtmlAndProcessDodajPytanie(request);
        

        String html
                = "    <div class=\"container\">\n"
                + "        <div class=\"card m-2 \">\n"
                + "            <h5 class=\"card-header bg-primary\">" + pytanie.tytul + "</h5>\n"
                + "            <div class=\"card-body\">\n"
                + "                <p class=\"card-text\">" + pytanie.tresc + "</p>\n"
                + "                <div class=\"row border-top\">\n"
                + "                    <div class=\"col\">\n"
                + "                        <p class=\"card-text \">" + pytanie.uzytkownik.login + "</p>\n"
                + "                    </div>\n"
                + "                    <div class=\"col\">\n"
                + "                        <p class=\"card-text\">" + pytanie.data + "</p>\n"
                + "                    </div>\n"
                + "                </div>\n"
                + "            </div>\n"
                + "        </div>\n"
                +htmlPrzyciskiAdmin
                + "     <div class=\"container \">\n"
                + getOdpowiedzi(pytanie)
                + "\n"
                + "        </div>\n"
                + "    </div>\n"
                + htmlDodajOdpowiedz;

        return html;
    }

    public void setData(Function<Integer, Pytanie> dostarczycielPytania)
    {
        this.dostarczycielPytania = dostarczycielPytania;
    }

    private String getOdpowiedzi(Pytanie pytanie)
    {
        String html = "";
        Odpowiedz odpowiezd;
        for (Odpowiedz odpowiedz : pytanie.odpowiedzi)
        {
            html += "<div class=\"card m-2\">\n"
                    + "                <div class=\"card-body\">\n"
                    + "                    <p class=\"card-text\">" + odpowiedz.tresc + "</p>\n"
                    + "                    <div class=\"row border-top\">\n"
                    + "                        <div class=\"col\">\n"
                    + "                            <p class=\"card-text \">" + odpowiedz.uzytkownik + "</p>\n"
                    + "                        </div>\n"
                    + "                        <div class=\"col\">\n"
                    + "                            <p class=\"card-text\">" + odpowiedz.data + "</p>\n"
                    + "                        </div>\n"
                    + "                    </div>\n"
                    + "                </div>\n"
                    + "            </div>";
        }
        return html;
    }

    private String getHtmlAndProcessDodajPytanie(HttpServletRequest request)
    {
        String wiadomosc = "";
        String html = "";
        Uzytkownik uzytkownik = Kernel.getUser(request.getSession());
        if (uzytkownik == null)
        {
            html = " <a href=\"" + Logowanie.url + "\" class=\"btn btn-primary\">Zaloguj sie aby udzielic odpowiedzi</a>\n";
        } else
        {
            
            html = "<div class=\"container-xxl\">\n"
                    + "        <!-- Content here -->\n"
                    + "        <form action=\"\" method=\"post\">\n"
                    + "            <div class=\"form-floating\">\n"
                    + "                <textarea  name=\"" + nazwaParametruTrescOdpowiedzi + "\" class=\"form-control\" placeholder=\"Leave a comment here\" id=\"floatingTextarea\" required></textarea>\n"
                    + "                <label for=\"floatingTextarea\">Treść odpowiedzi</label>\n"
                    + "            </div>\n"
                    + "            <button type=\"submit\" class=\"btn btn-primary mt-3\">Odpowiedz</button>\n"
                    + "        </form>\n"
                    + "    </div>";

            String trescOdpowiedzi = request.getParameter(nazwaParametruTrescOdpowiedzi);

            if (trescOdpowiedzi != null)
            {
                if(Kernel.dodajOdpowiedz(request.getSession(),PytanieActivity.getIdPytania(request),trescOdpowiedzi))
                {
                    wiadomosc = "dodano odpowiedz";
                }else
                {
                    wiadomosc = "Nie udalo sie dodac wiadomosci";
                }
            }            
            html+="<h3>"+wiadomosc+"</h3>";
        }

        return html;
    }

    private String getAndProsessPrzyciskiAdmina(HttpServletRequest request, Pytanie pytanie)
    {String html="";
        
        boolean czyJestZalogowanyAdmin = Kernel.isAdmin(request.getSession());
        if(czyJestZalogowanyAdmin)
        {
            if(request.getParameter(nazawaParametruDelete)!=null)
            {
                onDeleteButtonClick.accept(request.getSession(), PytanieActivity.getIdPytania(request));
            }
            html="<a href=\""+PytanieActivity.getUrl(pytanie)+"&"+nazawaParametruDelete+"=true"  + "\" class=\"btn btn-danger\">Usun</a>\n";
        }
        
        
        return html;
    }

    

}
