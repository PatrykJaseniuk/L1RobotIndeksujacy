/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ForumServlet.RejestracjaActivity;

import ForumServlet.GuiWspolne.GuiElement;
import java.util.function.BiFunction;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author jasyn
 */
class GuiRejestracja implements GuiElement
{

    TriFunction<String, String, String, Boolean> onClickRejestruj;
    private String action;
    private String method;
    private final String nazwaParametruLogin;
    private final String nazwaParametruHaslo1;
    private final String nazwaParametruHaslo2;
    private static int iloscGui=0;
    
    GuiRejestracja(String action)
    {
        this.action= action;
        this.method="post";
        nazwaParametruLogin = "login"+iloscGui;
        nazwaParametruHaslo1 = "haslo1"+iloscGui;
        nazwaParametruHaslo2 = "haslo2"+iloscGui;
        
        iloscGui++;
    }
     

    @Override
    public String getHtml(HttpServletRequest request)
    { 
        String login = request.getParameter(nazwaParametruLogin);
        String haslo1 = request.getParameter(nazwaParametruHaslo1);
        String haslo2 = request.getParameter(nazwaParametruHaslo2);
        String odpowiedz="";
        
        if(login!=null && haslo1!=null && haslo2!=null)
        {
            if(!onClickRejestruj.apply(login,haslo1, haslo2))
            {
                odpowiedz = "nieprawidloweDane";
            }
        }
        
        String html =  "    <div class=\"container-sm\">\n"
                + "        <!-- Content here -->\n"
                + "\n"
                + "        <div class=\"row justify-content-md-center\">\n"
                + "            <div class=\"col-md-auto\">\n"
                + "                <h5>Rejestracja</h5>\n"
                + "                <form action=\""+action+"\" method=\""+method+"\">\n"
                + "                    <div class=\"mb-3\">\n"
                + "                        <label for=\"exampleInputEmail1\" class=\"form-label\">Login</label>\n"
                + "                        <input type=\"text\" class=\"form-control\" id=\"exampleInputEmail1\" name=\""+nazwaParametruLogin+"\" >\n"
                + "                    </div>\n"
                + "                    <div class=\"mb-3\">\n"
                + "                        <label for=\"exampleInputPassword1\" class=\"form-label\">Haslo</label>\n"
                + "                        <input type=\"password\" class=\"form-control\" id=\"exampleInputPassword1\" name=\""+nazwaParametruHaslo1+"\">\n"
                + "                    </div>\n"
                + "                    <div class=\"mb-3\">\n"
                + "                        <label for=\"exampleInputPassword2\" class=\"form-label\">Powtórz hasło</label>\n"
                + "                        <input type=\"password\" class=\"form-control\" id=\"exampleInputPassword2\" name=\""+nazwaParametruHaslo2+"\">\n"
                + "                    </div>\n"
                + "                    <button type=\"submit\" class=\"btn btn-primary\">Rejestracja</button>\n"
                + "                    <h5>"+odpowiedz+"</h5>\n"
                + "                </form>\n"
                + "            </div>\n"
                + "        </div>\n"
                + "    </div>\n";
        
        return html;
    }    
}
