/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Lab4;

import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import javax.jms.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author jasyn
 */
public class WykresKlasa {

    private static final String NAZWA_POLA = "poleLiczba";
    private static final String GENERUJ_WYKRES = "generujWykres";
    private static final String ACTION = "Wykres";
    private static final String NAZWA_ATRYBUTU_SESJI = "listaLiczb";

    private static HttpSession sesja;
    private static List<Integer> listaLiczb;
    private static boolean czyGenerowacWykres;

    public static void dzialaj(HttpServletRequest request, HttpServletResponse response) {
        obsolugaWejscia(request);
        generowanieWyjscia(response);
    }

    private static void obsolugaWejscia(HttpServletRequest request) {
        getSesionData(request);
        odzytajLiczbe(request);
        odczytajCzyGenerowacWykres(request);
        zapisanieSesji(request);
    }

    private static void generowanieWyjscia(HttpServletResponse response) {

        String html = "<!DOCTYPE html>\n"
                + "<html>\n"
                + "<head>\n"
                + "<title>Page Title</title>\n"
                + "</head>\n"
                + "<body>\n"
                + "\n"
                + "<h1>Liczby: " + generujHtmlLiczby() + "</h1>"
                + "<form action=\"" + ACTION + "\">\n"
                + "  <label for=\"" + NAZWA_POLA + "\">Podaj liczbe: </label><br>\n"
                + "  <input type=\"text\" id=\"" + NAZWA_POLA + "\" name=\"" + NAZWA_POLA + "\"><br><br>\n"
                + "  <input type=\"submit\" value=\"Wprowadz\">\n"
                + "</form>"
                + "<form action=\"" + ACTION + "\">\n"
                + "  <input type=\"hidden\" id=\"" + GENERUJ_WYKRES + "\" name=\"" + GENERUJ_WYKRES + "\"><br><br>\n"
                + "  <input type=\"submit\" value=\"Wygeneruj wykres\">\n"
                + "</form>"
                + generujHtmlWykres()
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

    private static String generujHtmlLiczby() {
        String liczby = "";

        for (Integer integer : listaLiczb) {
            liczby += " " + integer.toString();
        }

        return liczby;
    }

    private static String generujHtmlWykres() {
        String html = "";
        if (czyGenerowacWykres) {
            for (Integer integer : listaLiczb) {
                html += "<svg width=\"60\" height=\"300\">\n"
                        + "  <rect width=\"40\" height=\"" + Integer.toString((integer * 30)) + "\"  y=\""+Integer.toString(300-integer*30)+"\" style=\"fill:rgb(0,0,255);stroke-width:2;stroke:rgb(0,0,0)\" />\n"
                        + "<text fill=\"#FF0000\" font-size=\"30\" font-family=\"Verdana\" x=\"10\" y=\"300\">"+integer.toString()+"</text>"
                        + "</svg>";
            }
        }
        return html;
    }

    private static void odzytajLiczbe(HttpServletRequest request) {

        String liczba = request.getParameter(NAZWA_POLA);
        try {
            int liczbaInt = Integer.parseInt(liczba);
            if (liczbaInt >= 0 && liczbaInt <= 10) {
                listaLiczb.add(liczbaInt);
            }

        } catch (Exception e) {

        }
    }

    private static void odczytajCzyGenerowacWykres(HttpServletRequest request) {
        czyGenerowacWykres = false;
        Enumeration<String> nazwyParametrow = request.getParameterNames();
        if (nazwyParametrow == null) {
            czyGenerowacWykres = false;
        } else {
            while (nazwyParametrow.hasMoreElements()) {
                String nazwa = nazwyParametrow.nextElement();
                if (nazwa.equals(GENERUJ_WYKRES)) {
                    czyGenerowacWykres = true;
                    break;
                }
            }
        }

    }

    private static void zapisanieSesji(HttpServletRequest request) {
        sesja.setAttribute(NAZWA_ATRYBUTU_SESJI, listaLiczb);
    }

    private static void getSesionData(HttpServletRequest request) {
        sesja = request.getSession();

        listaLiczb = (LinkedList<Integer>) sesja.getAttribute(NAZWA_ATRYBUTU_SESJI);
        if (listaLiczb == null) {
            listaLiczb = new LinkedList<Integer>();
        }
    }
}
