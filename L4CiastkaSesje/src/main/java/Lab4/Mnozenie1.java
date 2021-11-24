/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Lab4;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jasyn
 */
@WebServlet(name = "Mnozenie1", urlPatterns = {"/Mnozenie1"})
public class Mnozenie1 extends HttpServlet {

    private final String nazwaCiasteczka = "value";
    private final String nazwaPola = "czynnik";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

//         Cookie ciastko = new Cookie("value","0");
//        response.addCookie(ciastko);
        int wynik;
        if (false) {
            wynik = getCzynnik1(request);
        } else {
            wynik = 1;
        }
        widok(response, 1);
        wyslijCiastko(response, wynik);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int czynnik1 = getCzynnik1(request);
        int czynnik2 = getCzynnik2(request);

        int wynik = czynnik1 * czynnik2;
        
        wyslijCiastko(response, wynik);
        widok(response, wynik);
       
        //processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void widok(HttpServletResponse response, int wynik) {

        String html = "<!DOCTYPE html>\n"
                + "<html>\n"
                + "<head>\n"
                + "<title>Page Title</title>\n"
                + "</head>\n"
                + "<body>\n"
                + "\n"
                + "<h1>Wynik: " + wynik + "</h1><form action=\"Mnozenie1\">\n"
                + "  <label for=\"fname\">Podaj czynnik: </label><br>\n"
                + "  <input type=\"text\" id=\""+nazwaPola+"\" name=\""+nazwaPola+"\"><br><br>\n"
                + "  <input type=\"submit\" value=\"Pomnoz\">\n"
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

    private void wyslijCiastko(HttpServletResponse response, int value) {
        Cookie ciastko = new Cookie(nazwaCiasteczka, Integer.toString(value));
        response.addCookie(ciastko);
    }

    private int getCzynnik1(HttpServletRequest request) {
        Cookie tablicaCiasteczek[] = request.getCookies();

        if (tablicaCiasteczek == null) {
            return 1;
        } else {
            for (Cookie cookie : tablicaCiasteczek) {
                String name = cookie.getName();
                if (name.equals(nazwaCiasteczka)) {
                    String cookieValue;
                    cookieValue = cookie.getValue();
                    try {
                        return Integer.parseInt(cookieValue);
                    } catch (Exception e) {
                        return 1;
                    }
                }
            }
        }
        return 1;
    }

    private int getCzynnik2(HttpServletRequest request) {
        
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

    private boolean czyMaciaCiastko(HttpServletRequest request) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return false;
    }

}
