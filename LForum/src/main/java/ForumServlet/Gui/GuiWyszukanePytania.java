/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ForumServlet.Gui;

import ForumServlet.Pytanie;
import ForumServlet.PytanieActivity.PytanieActivity;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author jasyn
 */
public class GuiWyszukanePytania
{
    private static int iloscGuiWyszukiwaniePytania=0;
    
    GuiWyszukanePytania()
    {
         nazwaParametruWyszukiwanaFraza="wyszukiwanaFraza"+iloscGuiWyszukiwaniePytania;
         iloscGuiWyszukiwaniePytania++;
    }

    private final String action="";
    private String method="get";
    private Function<String, List<Pytanie>> searchEngine;
    private String nazwaParametruWyszukiwanaFraza;

    String getHtml(HttpServletRequest request)
    {
        String htmlWynikWyszukiwania = "";

        List<Pytanie> pytania = searchEngine.apply(request.getParameter(nazwaParametruWyszukiwanaFraza));

        for (Pytanie pytanie : pytania)
        {
            htmlWynikWyszukiwania
                    += "                <div class=\"card m-2\">\n"
                    + "                    <div class=\"card-header\">\n"
                    + "                        " + pytanie.data + "\n"
                    + "                    </div>\n"
                    + "                    <div class=\"card-body\">\n"
                    + "                        <h5 class=\"card-title\">" + pytanie.tytul + "</h5>\n"
                    + "                            <p class=\"card-text\">" + pytanie.tresc + "\n"
                    + "                            </p>\n"
                    + "                        <a href=\"" + PytanieActivity.getUrl(pytanie) + "\" class=\"btn btn-primary\">Przejdz</a>\n"
                    + "                </div>\n"
                    + "                    </div>\n";
        }

        String html = " <!-- Pasek Wyszukiwania -->\n"
                + "                <form class=\"d-flex m-3\" method=\"" + method + "\"  action=\"" + action + "\">\n"
                + "                    <input name=\"" + nazwaParametruWyszukiwanaFraza + "\" class=\"form-control me-2\" type=\"search\" placeholder=\"Search\" aria-label=\"Search\">\n"
                + "                    <button class=\"btn btn-outline-success\" type=\"submit\">Search</button>\n"
                + "                </form>\n"
                + "                <!-- Pasek Wyszukiwania -->\n"
                + "                <!-- Pole Glowne z pytaniami -->\n"
                + "                <div class=\"container bg-primary p-1 \">\n"
                + htmlWynikWyszukiwania
                + "                </div>\n"
                + "                <!-- Pole Glowne z pytaniami -->";
        return html;
    }

    public void setSearchEngine(Function<String, List<Pytanie>> searchEngine)
    {
        this.searchEngine = searchEngine;
    }

    public void setActionOnButtonClick(Consumer<Pytanie> consumer)
    {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
