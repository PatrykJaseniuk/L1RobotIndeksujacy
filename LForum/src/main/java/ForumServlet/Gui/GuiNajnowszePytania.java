/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ForumServlet.Gui;

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
                    + "                        " + pytanie.data + "\n"
                    + "                    </div>\n"
                    + "                    <div class=\"card-body\">\n"
                    + "                        <h5 class=\"card-title\">" + pytanie.tytul + "</h5>\n"
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
