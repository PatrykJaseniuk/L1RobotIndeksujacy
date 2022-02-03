/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ForumServlet.GuiWspolne;

import ForumServlet.DodawaniePytaniaActivity.DodawaniePytaniaActivity;
import ForumServlet.WynikWyszukiwaniaActivity.GuiWyszukanePytania;
import ForumServlet.Kernel;
import ForumServlet.LogowanieActivity.Logowanie;
import ForumServlet.Pytanie;
import ForumServlet.Uzytkownik;
import ForumServlet.WynikWyszukiwaniaActivity.WynikWyszukiwaniaActivity;
import java.io.PrintWriter;
import java.util.List;
import java.util.function.Supplier;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author jasyn
 */
public class GuiRdzen
{

    private HttpServletRequest request;
    private HttpServletResponse response;
    public GuiNajnowszePytania guiNajnowszePytania;
    public GuiElement guiSrodekStrony;
    private Supplier<Integer> dostarczycielIloscPytan;
    private Supplier<Integer> dostarczycielIloscOdpowiedzi;
    private Runnable onClickMojeKoknto;
    private GuiStanZalogowania guiStanZalogowania;
    private String action=WynikWyszukiwaniaActivity.url;
    private String method="post";
    public static String nazwaParametruWyszukiwanaFraza="wyszukiwanaFraza";

    public GuiRdzen()
    {
        guiNajnowszePytania = new GuiNajnowszePytania();
        guiSrodekStrony = new GuiWyszukanePytania();
        guiStanZalogowania = new GuiStanZalogowania();
        dostarczycielIloscOdpowiedzi = new Supplier<Integer>(){
            @Override
            public Integer get()
            {
                return Kernel.getIloscOdpowiedzi();
            }
        };
        dostarczycielIloscPytan = new Supplier<Integer>(){
            @Override
            public Integer get()
            {
                return Kernel.getIloscPytan();
            }
        };
        guiNajnowszePytania.setDataSource(new Supplier<List<Pytanie>>(){
            @Override
            public List<Pytanie> get()
            {
                return Kernel.getNajnowszePytania();
            }
        });
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
        String htmlWyszukanePytania = guiSrodekStrony.getHtml(request);
        String htmlStanZalogowania = guiStanZalogowania.getHtml(request);

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
                + "\n"
                + "\n"
                + "    <div class=\"container-fluid\" style=\"background-color: slateblue;\">\n"
                + "        <div class=\"row\">\n"
                + "            <div class=\"col\">\n"
                + "                Logo\n"
                + "            </div>\n"
                + "            <div class=\"col\">\n"
                + "                <h2> Ilosc zadanych pytan: " + iloscPytan + "</h2>\n"
                + "            </div>\n"
                + "            <div class=\"col\">\n"
                + "               <h2> Ilosc udzielonychOdpowiedzi:" + iloscOdpowiedzi + "</h2> \n"
                + "            </div>\n"
                + "            <div class=\"col\">\n"
                + htmlStanZalogowania
                + "            </div>\n"
                + "        </div>\n"
                + "    </div>\n"
                + "    <div class=\"container-xxl \">\n"
                + "        <div class=\"row m-3\">\n"
                + "            <div class=\"col-3 bg-secondary\">\n"
                + htmlNajnowszPytania
                + "            </div>\n"
                + "\n"
                + "            <div class=\"col\">\n"
                + " <!-- Pasek Wyszukiwania -->\n"
                + "                <form class=\"d-flex m-3\" method=\"" + method + "\"  action=\"" + action + "\">\n"
                + "                    <input name=\"" + nazwaParametruWyszukiwanaFraza + "\" class=\"form-control me-2\" type=\"search\" placeholder=\"Search\" aria-label=\"Search\">\n"
                + "                    <button class=\"btn btn-outline-success\" type=\"submit\">Search</button>\n"
                + "                </form>\n"
                + "                <!-- Pasek Wyszukiwania -->\n"
                + htmlWyszukanePytania
                 + "                <!-- Stopka -->\n"
                + "                <div class=\"container m-4\">\n"
                + "                    <div class=\"row\">\n"
                + "                        <div class=\"col\">\n"
                + "                        <a href=\"" + getUrlDodawaniePytania() + "\" class=\"btn btn-primary\">Dodaj pytanie</a>\n"
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

    private String getUrlDodawaniePytania()
    {
        HttpSession session = request.getSession();
        Uzytkownik uzytkownik = (Uzytkownik)session.getAttribute(Kernel.nazwaAtrybutuSesji);
        
        if(uzytkownik==null)
        {
            return Logowanie.url;
        }else
        {
            return DodawaniePytaniaActivity.url;
        }
    }
}
