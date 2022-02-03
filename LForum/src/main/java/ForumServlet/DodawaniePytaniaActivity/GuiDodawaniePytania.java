/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ForumServlet.DodawaniePytaniaActivity;

import ForumServlet.GuiWspolne.GuiElement;
import ForumServlet.Kernel;
import ForumServlet.Uzytkownik;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author jasyn
 */
public class GuiDodawaniePytania implements GuiElement
{

    String action="";
    private String nazwaParametruTytul = "tytulNowegoPytania";
    private String nazwaParametruTresc = "trescNowegoPytania";
    Runnable onDodajPytanieClick;
    private String wiadomosc="";

    @Override
    public String getHtml(HttpServletRequest request)
    {
        buttonAction(request);

        String html = "<div class=\"container-xxl\">\n"
                + "        <!-- Content here -->\n"
                + "        <form action=\"" + action + "\" method=\"post\">            \n"
                + "            <div class=\"mb-3 form-floating\">\n"
                + "            <input name=\"" + nazwaParametruTytul + "\" type=\"text\" class=\"form-control\" id=\"floatingInputValue\" placeholder=\"name@example.com\" required>\n"
                + "            <label for=\"floatingInputValue\">Tytuł pytania</label>\n"
                + "            </div>\n"
                + "            <div class=\"form-floating\">\n"
                + "                <textarea  name=\"" + nazwaParametruTresc + "\" class=\"form-control\" placeholder=\"Leave a comment here\" id=\"floatingTextarea\" required></textarea>\n"
                + "                <label for=\"floatingTextarea\">Treść pytania</label>\n"
                + "            </div>\n"
                + "            <button type=\"submit\" class=\"btn btn-primary mt-3\">Zadaj Pytanie</button>\n"
                + "        </form>\n"
                + "        <h2>"+wiadomosc+"</h2>\n"
                + "    </div>";
        return html;
    }

    private void buttonAction(HttpServletRequest request)
    {
        wiadomosc="";
        String tytulPytania = request.getParameter(nazwaParametruTytul);
        String trescPytania = request.getParameter(nazwaParametruTresc);
        if (tytulPytania != null)
        {
            if (Kernel.dodajPytanie(request.getSession(), tytulPytania, trescPytania))
            {
                wiadomosc = "pytania zostalo dodane";
            } else
            {
                wiadomosc = "nie udalo sie dodac pytania";
            }
        }
    }
}
