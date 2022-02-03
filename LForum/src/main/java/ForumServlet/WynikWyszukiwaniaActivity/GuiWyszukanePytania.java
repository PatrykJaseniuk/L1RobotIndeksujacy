/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ForumServlet.WynikWyszukiwaniaActivity;

import ForumServlet.GuiWspolne.GuiElement;
import ForumServlet.GuiWspolne.GuiRdzen;
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
public class GuiWyszukanePytania implements GuiElement
{

    private static int iloscGuiWyszukiwaniePytania = 0;

    public GuiWyszukanePytania()
    {
        iloscGuiWyszukiwaniePytania++;
    }

    private Function<String, List<Pytanie>> searchEngine;

    @Override
    public String getHtml(HttpServletRequest request)
    {
        String htmlWynikWyszukiwania = "";

        List<Pytanie> pytania = searchEngine.apply(request.getParameter(GuiRdzen.nazwaParametruWyszukiwanaFraza));

        for (Pytanie pytanie : pytania)
        {
            htmlWynikWyszukiwania
                    += "                <div class=\"card m-2\">\n"
                    + "                    <div class=\"card-header\">\n"
                    + "                        " + pytanie.tytul + "\n"
                    + "                    </div>\n"
                    + "                    <div class=\"card-body\">\n"
                    + "                            <p class=\"card-text\">" + pytanie.tresc + "</p>\n"
                    + "                <div class=\"row border-top\">\n"
                    + "                    <div class=\"col\">\n"
                    + "                        <p class=\"card-text \">" + pytanie.uzytkownik.login + "</p>\n"
                    + "                    </div>\n"
                    + "                    <div class=\"col\">\n"
                    + "                        <p class=\"card-text\">" + pytanie.data + "</p>\n"
                    + "                    </div>\n"
                    + "                </div>\n"
                    + "                        <a href=\"" + PytanieActivity.getUrl(pytanie) + "\" class=\"btn btn-primary\">Przejdz</a>\n"
                    + "                </div>\n"
                    + "                    </div>\n";
        }

        String html = " <!-- Pasek Wyszukiwania -->\n"
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
