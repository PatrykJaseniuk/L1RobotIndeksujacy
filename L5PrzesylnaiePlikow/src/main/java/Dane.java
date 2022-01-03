/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jasyn
 */
@WebServlet(urlPatterns = {"/Dane"}, initParams = {
    @WebInitParam(name = "login", value = "admin"),
    @WebInitParam(name = "haslo", value = "admin")})
public class Dane extends HttpServlet {

    Dane() {
        Strona.konstruowanieWidoku();
    }

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
        Process(request);
        Strona.view.response(response);
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
        processRequest(request, response);
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

    private void Process(HttpServletRequest request) {
        String podanyLogin = Strona.inputLogin.getText(request);
        String podaneHaslo = Strona.inputHaslo.getText(request);
        String wlasciweHaslo = getHaslo();
        String wlasciwyLogin = getLogin();
        
        if (wlasciwyLogin.equals(podanyLogin)) {
             Strona.info.setText("login prawidlowy");
            if (wlasciweHaslo.equals(podaneHaslo)) {
                Strona.info.setText("login i haslo prawidlowe");
                UmiescDane.umiescDane(request);
            }
        }
        else
        {
             Strona.info.setText("login nie prawidlowy");
        }
    }

    private String getHaslo() {
        return "admin";
    }

    private String getLogin() {
        return "admin";
    }
}
