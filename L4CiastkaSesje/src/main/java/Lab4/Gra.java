/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Lab4;

import java.io.PrintWriter;
import java.util.concurrent.ThreadLocalRandom;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jasyn
 */
public class Gra {
    
    private static HttpServletRequest request;
    private static HttpServletResponse response;
    private static int liczbaPunktow;
    private static int wylosowanaLiczba;
    private static int podanaLiczba;
    private static String wynik="";
    private final static int maksymalnaIloscPunktow = 10;
    private final static int startowaIloscPunktow = 5;
    private final static String nazwaPola="liczbaOczek";
    private final static String nazwaCiasteczka="liczbaPunktow";
    

    static void graj(HttpServletRequest request, HttpServletResponse response) {
        
        rozgrywka(request);
        odpowiedz(response);   
        
    }

    static void rozgrywka(final HttpServletRequest request)
    {
        liczbaPunktow = getLiczbaPunktow(request);
        wylosowanaLiczba = getWylosowanaLiczba();
        podanaLiczba = getPodanaLiczba(request);        
        sprawdzLiczby();        
        wynik = getWynik();  
    }
    
    
    
    static void odpowiedz(HttpServletResponse response) {
        
        Cookie ciastko = new Cookie(nazwaCiasteczka, Integer.toString(liczbaPunktow));
        response.addCookie(ciastko);
        
      String html = "<!DOCTYPE html>\n"
                + "<html>\n"
                + "<head>\n"
                + "<title>Page Title</title>\n"
                + "</head>\n"
                + "<body>\n"
                + "\n"
                + "<h1>Wynik: " + wynik + "</h1>"
              + "<h1>Wyrzucoan liczba: " + podanaLiczba + "</h1>"
              + "<h1>Wylosowana liczba: " + wylosowanaLiczba + "</h1>"
              + "<h1>Liczba punktow: " + liczbaPunktow + "</h1>"
              + "<form action=\"Kostka\">\n"
                + "  <label for=\""+nazwaPola+"\">Podaj wyrzucona liczbe oczek: </label><br>\n"
                + "  <input type=\"text\" id=\""+nazwaPola+"\" name=\""+nazwaPola+"\"><br><br>\n"
                + "  <input type=\"submit\" value=\"Graj\">\n"
                + "</form>"
                + "\n"
                + "</body>\n"
                + "</html>";
      response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println(html);
        } catch (Exception e) {

        }
        
        
        
    }

    private static int getLiczbaPunktow(final HttpServletRequest request) {
        
       Cookie tablicaCiasteczek[] = request.getCookies();

        if (tablicaCiasteczek == null) {
            return startowaIloscPunktow;
        } else {
            for (Cookie cookie : tablicaCiasteczek) {
                String name = cookie.getName();
                if (name.equals(nazwaCiasteczka)) {
                    String cookieValue;
                    cookieValue = cookie.getValue();
                    try {
                        return Integer.parseInt(cookieValue);
                    } catch (Exception e) {
                        return startowaIloscPunktow;
                    }
                }
            }
        }
        return startowaIloscPunktow;
    }

    private static int getWylosowanaLiczba() {
        return ThreadLocalRandom.current().nextInt(1, 6 + 1);
    }

    private static int getPodanaLiczba(final HttpServletRequest request) {
        String input = request.getParameter(nazwaPola);
        try
        {
            return Integer.parseInt(input);
        }
        catch(Exception e)
        {
            return 1;
        }
    }

    private static void zyskPunktow() {
       liczbaPunktow++;
    }

    private static void utrataPunktow() {
       liczbaPunktow--;
    }

    private static String getWynik() {
       
        if(liczbaPunktow>maksymalnaIloscPunktow)
        {
            return "zwyciestwo";
        }
        else if(liczbaPunktow<0)
        {
            return "przegrana";
        }
        else
        {
            return"w grze";
        }
    }

    private static void sprawdzLiczby() {
        if(wylosowanaLiczba== podanaLiczba)
        {
            zyskPunktow();
        }
        else
        {
            utrataPunktow();
        }        
    }
    
    
    
}
