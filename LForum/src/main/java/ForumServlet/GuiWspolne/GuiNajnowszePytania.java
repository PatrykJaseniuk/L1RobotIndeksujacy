/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ForumServlet.GuiWspolne;

import ForumServlet.Pytanie;
import ForumServlet.PytanieActivity.PytanieActivity;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 *
 * @author jasyn
 */
public class GuiNajnowszePytania
{
    private Supplier<List<Pytanie>> supplier;
    private Consumer<Pytanie> actionOnButtonClick;

    String getHtml()
    {
        String htmlNajnowszePytania = "";

        for (Pytanie pytanie : supplier.get())
        {
            htmlNajnowszePytania
                    += "                <div class=\"card mt-4\">\n"
                    + "                    <div class=\"card-header\">\n"
                    + "                        " + pytanie.tytul + "\n"
                    + "                    </div>\n"
                    + "                    <div class=\"card-body\">\n"
                   
                + "                <div class=\"row \">\n"
                + "                    <div class=\"col\">\n"
                + "                        <p class=\"card-text \">"+pytanie.uzytkownik.login+"</p>\n"
                + "                    </div>\n"
                + "                    <div class=\"col\">\n"
                + "                        <p class=\"card-text\">"+pytanie.data+"</p>\n"
                + "                    </div>\n"
                + "                </div>\n"
                    + "                        <a href=\"" + PytanieActivity.getUrl(pytanie) + "\" class=\"btn btn-primary\">Przejdz</a>\n"
                    + "                    </div>\n"
                    + "            </div>\n";
        }

        String html = " <!-- Boczny pasek -->\n"

                + "                <h5>Najnowsze pytania</h5>\n"
                + htmlNajnowszePytania

                + "            <!-- Boczny pasek -->";
        return html;
    }

    public void setDataSource(Supplier<List<Pytanie>> supplier)
    {
        this.supplier = supplier;
    }

    public void setActionOnButtonClick(Consumer<Pytanie> consumer)
    {
        this.actionOnButtonClick = consumer;
    }
}
