/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ForumServlet.Gui;

import ForumServlet.Kernel;
import java.io.PrintWriter;
import java.util.function.Supplier;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jasyn
 */
public class GuiStronaGlowna
{

    private HttpServletRequest request;
    private HttpServletResponse response;
    public GuiNajnowszePytania guiNajnowszePytania;
    public GuiWyszukanePytania guiWyszukanePytania;
    private Supplier<Integer> dostarczycielIloscPytan;
    private Supplier<Integer> dostarczycielIloscOdpowiedzi;
    private Runnable onClickMojeKoknto;
    private GuiStanZalogowania guiStanZalogowania;

    public GuiStronaGlowna()
    {
        guiNajnowszePytania = new GuiNajnowszePytania();
        guiWyszukanePytania = new GuiWyszukanePytania();
        guiStanZalogowania = new GuiStanZalogowania();
    }

    public void process(HttpServletRequest request, HttpServletResponse response)
    {
        this.request = request;
        this.response = response;
        obsłuzZadanie();
        odpowiedz();
    }

    private void obsłuzZadanie()
    {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void odpowiedz()
    {
        Integer iloscPytan = dostarczycielIloscPytan.get();
        Integer iloscOdpowiedzi = dostarczycielIloscOdpowiedzi.get();

        String htmlNajnowszPytania = guiNajnowszePytania.getHtml();
        String htmlWyszukanePytania = guiWyszukanePytania.getHtml(request);
        String htmlStanZalogowania = guiStanZalogowania.getHtml();

        String html = "<!doctype html>\n"
                + "<html lang=\"en\">\n"
                + "\n"
                + "<head>\n"
                + "    <!-- Required meta tags -->\n"
                + "    <meta charset=\"utf-8\">\n"
                + "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n"
                + "\n"
                + "    <!-- Bootstrap CSS -->\n"
                + "    <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css\" rel=\"stylesheet\"\n"
                + "        integrity=\"sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3\" crossorigin=\"anonymous\">\n"
                + "\n"
                + "    <title>Hello, world!</title>\n"
                + "</head>\n"
                + "\n"
                + "<body>\n"
                + "    <h1>Hello, world!</h1>\n"
                + "\n"
                + "\n"
                + "    <div class=\"container\">\n"
                + "        <div class=\"row\">\n"
                + "            <div class=\"col\">\n"
                + "                Logo\n"
                + "            </div>\n"
                + "            <div class=\"col\">\n"
                + "                 Ilosc zadanych pytan: " + iloscPytan + "\n"
                + "            </div>\n"
                + "            <div class=\"col\">\n"
                + "                Ilosc udzielonychOdpowiedzi:" + iloscOdpowiedzi + " \n"
                + "            </div>\n"
                + "            <div class=\"col\">\n"
                + htmlStanZalogowania
                + "            </div>\n"
                + "        </div>\n"
                + "    </div>\n"
                + "    <div class=\"container \">\n"
                + "        <div class=\"row m-3\">\n"
                + "            <div class=\"col-3 bg-secondary\">\n"
                + htmlNajnowszPytania
                + "            </div>\n"
                + "\n"
                + "            <div class=\"col\">\n"
                + htmlWyszukanePytania
                 + "                <!-- Stopka -->\n"
                + "                <div class=\"container m-4\">\n"
                + "                    <div class=\"row\">\n"
                + "                        <div class=\"col\">\n"
                + "                            <button type=\"button\" class=\"btn btn-secondary\">Secondary</button>\n"
                + "                        </div>\n"
                + "                        <div class=\"col\">\n"
                + "                            <button type=\"button\" class=\"btn btn-secondary\">Secondary</button>\n"
                + "                        </div>\n"
                + "                        <div class=\"col\">\n"
                + "                            <button type=\"button\" class=\"btn btn-secondary\">Secondary</button>\n"
                + "                        </div>\n"
                + "                    </div>\n"
                + "\n"
                + "                </div>\n"
                + "                <!-- Stopka -->\n"
                + "            </div>\n"
                + "\n"
                + "        </div>\n"
                + "    </div>\n"
                + "\n"
                + "    <!-- Optional JavaScript; choose one of the two! -->\n"
                + "\n"
                + "    <!-- Option 1: Bootstrap Bundle with Popper -->\n"
                + "    <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js\"\n"
                + "        integrity=\"sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p\"\n"
                + "        crossorigin=\"anonymous\"></script>\n"
                + "\n"
                + "    <!-- Option 2: Separate Popper and Bootstrap JS -->\n"
                + "    <!--\n"
                + "    <script src=\"https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js\" integrity=\"sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB\" crossorigin=\"anonymous\"></script>\n"
                + "    <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js\" integrity=\"sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13\" crossorigin=\"anonymous\"></script>\n"
                + "    -->\n"
                + "</body>\n"
                + "\n"
                + "</html>";
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter())
        {
            out.println(html);
        } catch (Exception e)
        {
        }
    }

    public void setDetaSourceIloscPytan(Supplier<Integer> dostarczycielIloscPytan)
    {
        this.dostarczycielIloscPytan = dostarczycielIloscPytan;
    }

    public void setDataSourceIloscOdpowiedzi(Supplier<Integer> dostarczycielIloscPytan)
    {
        this.dostarczycielIloscOdpowiedzi = dostarczycielIloscPytan;
    }

    public void redirect(String url)
    {
        try
        {
            response.sendRedirect(url);
        } catch (Exception e)
        {
        }
    }

    public void setActionOnButtonMojeKontoClick(Runnable onClickMojeKonto)
    {
        this.onClickMojeKoknto = onClickMojeKonto;
    }
}
