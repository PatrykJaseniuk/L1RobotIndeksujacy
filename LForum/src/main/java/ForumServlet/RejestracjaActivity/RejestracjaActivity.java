/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package ForumServlet.RejestracjaActivity;

import ForumServlet.GuiWspolne.GuiRdzen;
import ForumServlet.Kernel;
import ForumServlet.LogowanieActivity.Logowanie;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.function.BiFunction;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jasyn
 */
@WebServlet(name = "RejestracjaActivity", urlPatterns =
{
    "/RejestracjaActivity"
})
public class RejestracjaActivity extends HttpServlet
{

    public static final String url = "RejestracjaActivity";
    GuiRdzen gui;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    RejestracjaActivity()
    {
        gui = new GuiRdzen();
        GuiRejestracja guiRejestracja = new GuiRejestracja(url);
        guiRejestracja.onClickRejestruj = new TriFunction<String, String, String, Boolean>()
        {
            @Override
            public Boolean apply(String login, String haslo1, String haslo2)
            {
                if (Kernel.rejestruj(login, haslo1, haslo2))
                {
                    gui.redirect(Logowanie.url);
                    return true;
                } else
                {
                    return false;
                }
            }
        };
        gui.guiSrodekStrony = guiRejestracja;
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        gui.process(request, response);
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
            throws ServletException, IOException
    {
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
            throws ServletException, IOException
    {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo()
    {
        return "Short description";
    }// </editor-fold>

}
