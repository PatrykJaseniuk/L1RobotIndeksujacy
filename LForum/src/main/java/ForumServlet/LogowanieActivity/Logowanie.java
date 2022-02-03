/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package ForumServlet.LogowanieActivity;

import ForumServlet.GuiWspolne.GuiRdzen;
import ForumServlet.WynikWyszukiwaniaActivity.WynikWyszukiwaniaActivity;
import ForumServlet.Kernel;
import ForumServlet.LogowanieActivity.GuiLogowanie;
import ForumServlet.RejestracjaActivity.RejestracjaActivity;
import ForumServlet.Uzytkownik;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author jasyn
 */
@WebServlet(name = "Logowanie", urlPatterns =
{
    "/Logowanie"
})
public class Logowanie extends HttpServlet
{
    
    public static final String url = "Logowanie";
    private GuiRdzen gui= new GuiRdzen();
    
    Logowanie()
    {
        guiLogowanie =  new GuiLogowanie();
        guiLogowanie.urlRejestracja=RejestracjaActivity.url;
        
        guiLogowanie.setOnLogInClick(new Runnable(){
            @Override
            public void run()
            {
                String login = guiLogowanie.getLogin();
                String haslo = guiLogowanie.getHaslo();
                boolean czyUdaloSieZalogowac = Kernel.logIn(guiLogowanie.getSession(),login,haslo);
                if(czyUdaloSieZalogowac)
                {                    
                    gui.redirect(WynikWyszukiwaniaActivity.url);
                }
                else
                {
                    guiLogowanie.putKomunikatNieprawidloweHasloLubLogin();
                }                
            }            
        });
        gui.guiSrodekStrony = guiLogowanie;
    }

    private GuiLogowanie guiLogowanie;

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
            throws ServletException, IOException
    {
       gui.process(request,response);
//       guiLogowanie.process(request,response);
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
