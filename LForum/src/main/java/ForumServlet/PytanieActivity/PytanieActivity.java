/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package ForumServlet.PytanieActivity;

import ForumServlet.GuiWspolne.GuiRdzen;
import ForumServlet.Kernel;
import ForumServlet.Pytanie;
import ForumServlet.WynikWyszukiwaniaActivity.WynikWyszukiwaniaActivity;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
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
@WebServlet(name = "PytanieActivity", urlPatterns =
{
    "/PytanieActivity"
})
public class PytanieActivity extends HttpServlet
{
    
private GuiRdzen guiStronaGlowna = new GuiRdzen();
    PytanieActivity()
    {
        GuiPytanie guiPytanie = new GuiPytanie();
        guiStronaGlowna.guiSrodekStrony = guiPytanie;

        guiPytanie.setData(new Function<Integer, Pytanie>()
        {
            @Override
            public Pytanie apply(Integer id)
            {
                return Kernel.getPytanie(id);
            }
        });
        guiPytanie.onDeleteButtonClick=new BiConsumer<HttpSession, Integer>(){
            @Override
            public void accept(HttpSession session, Integer idPytania)
            {
                if(Kernel.deletePytanie(session,idPytania))
                {
                    guiStronaGlowna.redirect(WynikWyszukiwaniaActivity.url);
                }
            }
        };
         
    }
    private static final String nazwaParametruIdPytania = "idPytania";

    public static String getUrl(Pytanie pytanie)
    {
        return "PytanieActivity?" + nazwaParametruIdPytania + "=" + pytanie.id;
    }

    public static int getIdPytania(HttpServletRequest request)
    {
        int idPytania = -1;
        try
        {
            idPytania = Integer.valueOf(request.getParameter(nazwaParametruIdPytania));

        } catch (Exception e)
        {
            return -1;
        }
        return idPytania;
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
            throws ServletException, IOException
    {
        guiStronaGlowna.process(request, response);
//         response.setContentType("text/html;charset=UTF-8");
//        try (PrintWriter out = response.getWriter())
//        {
//            out.println("szczegoly pytania id= "+request.getParameter(nazwaParametruIdPytania));
//        } catch (Exception e)
//        {
//        }
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
