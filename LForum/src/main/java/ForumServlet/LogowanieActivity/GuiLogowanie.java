/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ForumServlet.LogowanieActivity;

import ForumServlet.GuiWspolne.GuiElement;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author jasyn
 */
class GuiLogowanie implements GuiElement
{
    private static int iloscGuiLogowanie=0;
    public String urlRejestracja;
    GuiLogowanie()
    {
        nazwaParametruHaslo = "guiLogowanieHaslo"+iloscGuiLogowanie;
        nazwaParametruEmail = "guiLogowanieEmail"+iloscGuiLogowanie;
        iloscGuiLogowanie++;
    }

    private String komunikat="";
    private String method="post";
    private String action="";
    private String nazwaParametruEmail="";
    private String nazwaParametruHaslo="";
    private HttpServletRequest request;
    HttpServletResponse response;
    private Runnable onLoginClick;

//    void process(HttpServletRequest request, HttpServletResponse response)
//    {
//        this.request = request;
//        this.response = response;
//        obsłuzZadanie();
//        odpowiedz();
//    }

    void setOnLogInClick(Runnable onLoginClick)
    {
        this.onLoginClick = onLoginClick;
    }

    String getLogin()
    {
        return request.getParameter(nazwaParametruEmail);
    }

    String getHaslo()
    {
        return request.getParameter(nazwaParametruHaslo);
    }

    HttpSession getSession()
    {
        return request.getSession();
    }

    void putKomunikatNieprawidloweHasloLubLogin()
    {
        komunikat = "Nieprawidlowy login lub haslo";
    }

//    private void odpowiedz()
//    {
//        response.setContentType("text/html;charset=UTF-8");
//        try (PrintWriter out = response.getWriter())
//        {
//            /* TODO output your page here. You may use following sample code. */
//            String html = "<!doctype html>\n"
//                    + "<html lang=\"en\">\n"
//                    + "\n"
//                    + "<head>\n"
//                    + "    <!-- Required meta tags -->\n"
//                    + "    <meta charset=\"utf-8\">\n"
//                    + "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n"
//                    + "\n"
//                    + "    <!-- Bootstrap CSS -->\n"
//                    + "    <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css\" rel=\"stylesheet\"\n"
//                    + "        integrity=\"sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3\" crossorigin=\"anonymous\">\n"
//                    + "\n"
//                    + "    <title>Hello, world!</title>\n"
//                    + "</head>\n"
//                    + "\n"
//                    + "<body>\n"
//                    + "\n"
//                    + "\n"
//                    + "    <div class=\"container-sm\">\n"
//                    + "        <!-- Content here -->\n"
//                    + "\n"
//                    + "        <div class=\"row justify-content-md-center\">\n"
//                    + "            <div class=\"col-md-auto\">\n"
//                    + "                Variable width content\n"
//                    + "                <h1>"+komunikat+"</h1>\n"
//                    + "                <form  method=\""+ method+"\"  action=\"" + action + "\">\n"
//                    + "                    <div class=\"mb-3\">\n"
//                    + "                        <label for=\"exampleInputEmail1\" class=\"form-label\">Email address</label>\n"
//                    + "                        <input type=\"text\" class=\"form-control\" id=\"exampleInputEmail1\" name=\""+ nazwaParametruEmail +"\" aria-describedby=\"emailHelp\">\n"
//                    + "                        <div id=\"emailHelp\" class=\"form-text\">We'll never share your email with anyone else.</div>\n"
//                    + "                    </div>\n"
//                    + "                    <div class=\"mb-3\">\n"
//                    + "                        <label for=\"exampleInputPassword1\" class=\"form-label\">Password</label>\n"
//                    + "                        <input type=\"password\" class=\"form-control\" id=\"exampleInputPassword1\"name=\""+ nazwaParametruHaslo +"\">\n"
//                    + "                    </div>\n"
//                    + "                    <div class=\"mb-3 form-check\">\n"
//                    + "                        <input type=\"checkbox\" class=\"form-check-input\" id=\"exampleCheck1\">\n"
//                    + "                        <label class=\"form-check-label\" for=\"exampleCheck1\">Check me out</label>\n"
//                    + "                    </div>\n"
//                    + "                    <button type=\"submit\" class=\"btn btn-primary\">Log In</button>\n"
//                    + "                </form>\n"
//                    + "                <a class=\"btn btn-primary\" href=\""+urlRejestracja+"\" role=\"button\">Rejestracja</a>"
//                    + "            </div>\n"
//                    + "        </div>\n"
//                    + "    </div>\n"
//                    + "\n"
//                    + "    <!-- Optional JavaScript; choose one of the two! -->\n"
//                    + "\n"
//                    + "    <!-- Option 1: Bootstrap Bundle with Popper -->\n"
//                    + "    <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js\"\n"
//                    + "        integrity=\"sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p\"\n"
//                    + "        crossorigin=\"anonymous\"></script>\n"
//                    + "\n"
//                    + "    <!-- Option 2: Separate Popper and Bootstrap JS -->\n"
//                    + "    <!--\n"
//                    + "    <script src=\"https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js\" integrity=\"sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB\" crossorigin=\"anonymous\"></script>\n"
//                    + "    <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js\" integrity=\"sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13\" crossorigin=\"anonymous\"></script>\n"
//                    + "    -->\n"
//                    + "</body>\n"
//                    + "</html>";           
//            out.println(html);
//        } catch (Exception e)
//        {
//        }
//    }
    
    void obsłuzZadanie()
    {
        komunikat ="";
        
        if(request.getParameter(nazwaParametruEmail)!=null)
        {
            onLoginClick.run();
        }        
    }

//    void redirect(String docelowyUrl)
//    {
//         try
//        {
//            response.sendRedirect(docelowyUrl);
//        }
//        catch(Exception e)
//        {
//            System.out.println("problem z wyslaniem przekierowania");
//        }
//    }

    @Override
    public String getHtml(HttpServletRequest request)
    {
        this.request = request;
        obsłuzZadanie();
        String html = 
                    "<div class=\"container-sm\">\n"
                    + "        <!-- Content here -->\n"
                    + "\n"
                    + "        <div class=\"row justify-content-md-center\">\n"
                    + "            <div class=\"col-md-auto\">\n"
                    + "                Variable width content\n"
                    + "                <h1>"+komunikat+"</h1>\n"
                    + "                <form  method=\""+ method+"\"  action=\"" + action + "\">\n"
                    + "                    <div class=\"mb-3\">\n"
                    + "                        <label for=\"exampleInputEmail1\" class=\"form-label\">Email address</label>\n"
                    + "                        <input type=\"text\" class=\"form-control\" id=\"exampleInputEmail1\" name=\""+ nazwaParametruEmail +"\" aria-describedby=\"emailHelp\">\n"
                    + "                        <div id=\"emailHelp\" class=\"form-text\">We'll never share your email with anyone else.</div>\n"
                    + "                    </div>\n"
                    + "                    <div class=\"mb-3\">\n"
                    + "                        <label for=\"exampleInputPassword1\" class=\"form-label\">Password</label>\n"
                    + "                        <input type=\"password\" class=\"form-control\" id=\"exampleInputPassword1\"name=\""+ nazwaParametruHaslo +"\">\n"
                    + "                    </div>\n"
                    + "                    <div class=\"mb-3 form-check\">\n"
                    + "                        <input type=\"checkbox\" class=\"form-check-input\" id=\"exampleCheck1\">\n"
                    + "                        <label class=\"form-check-label\" for=\"exampleCheck1\">Check me out</label>\n"
                    + "                    </div>\n"
                    + "                    <button type=\"submit\" class=\"btn btn-primary\">Log In</button>\n"
                    + "                </form>\n"
                    + "                <a class=\"btn btn-primary\" href=\""+urlRejestracja+"\" role=\"button\">Rejestracja</a>"
                    + "            </div>\n"
                    + "        </div>\n"
                    + "    </div>\n";                       
        return html;
    }
}
