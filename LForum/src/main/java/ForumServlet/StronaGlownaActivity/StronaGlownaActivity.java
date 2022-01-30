/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package ForumServlet.StronaGlownaActivity;


import ForumServlet.Gui.GuiStronaGlowna;
import ForumServlet.Kernel;
import ForumServlet.MojeKontoActivity.MojeKontoActivity;
import ForumServlet.Pytanie;
import ForumServlet.PytanieActivity.PytanieActivity;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jasyn
 */
@WebServlet(name = "StronaGlowna", urlPatterns =
{
    "/StronaGlowna"
})
public class StronaGlownaActivity extends HttpServlet
{
    StronaGlownaActivity()
    {
        budowanieWidoku();
        guiStronaGlowna = new GuiStronaGlowna();
        guiStronaGlowna.guiNajnowszePytania.setDataSource(new Supplier<List<Pytanie>>() {
            @Override
            public List<Pytanie> get()
            {
                return Kernel.getNajnowszePytania();
            }
        });
        guiStronaGlowna.guiWyszukanePytania.setSearchEngine(new Function<String,List<Pytanie>>(){
            @Override
            public List<Pytanie> apply(String wyszukiwaneHaslo)
            {
                return Kernel.wyszukajPytan(wyszukiwaneHaslo);
            }           
        });        
        
        guiStronaGlowna.setDetaSourceIloscPytan(new Supplier<Integer>() {
            @Override
            public Integer get()
            {
                return Kernel.getIloscPytan();
            }           
        });
        guiStronaGlowna.setDataSourceIloscOdpowiedzi(new Supplier<Integer>() {
            @Override
            public Integer get()
            {
                return Kernel.getOdpowiedzi();
            }           
        });
        
        guiStronaGlowna.guiNajnowszePytania.setActionOnButtonClick(new Consumer<Pytanie>(){
            @Override
            public void accept(Pytanie pytanie)
            {                
                guiStronaGlowna.redirect(PytanieActivity.getUrl(pytanie));
            }
        });
        
        guiStronaGlowna.guiWyszukanePytania.setActionOnButtonClick(new Consumer<Pytanie>(){
            @Override
            public void accept(Pytanie pytanie)
            {                
                guiStronaGlowna.redirect(PytanieActivity.getUrl(pytanie));
            }
        });  
        
        guiStronaGlowna.setActionOnButtonMojeKontoClick(new Runnable(){
            @Override
            public void run()
            {
                guiStronaGlowna.redirect(MojeKontoActivity.url);
            }
        });        
    }

    public static final String url = "StronaGlowna";
    private GuiStronaGlowna guiStronaGlowna;

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

    private void budowanieWidoku()
    {

    }
}
