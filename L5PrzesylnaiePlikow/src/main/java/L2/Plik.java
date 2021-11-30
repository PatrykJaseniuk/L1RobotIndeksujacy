/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package L2;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author jasyn
 */
@WebServlet(name = "Plik", urlPatterns =
{
    "/Plik"
})
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 10, // 10 MB
        maxRequestSize = 1024 * 1024 * 100 // 100 MB
)
public class Plik extends HttpServlet
{

    Plik()
    {
        WidokPlik.konstruowanieWidoku();
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
        process(request);
        WidokPlik.view.response(response);
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

    private void process(HttpServletRequest request)
    {
        Part plik = WidokPlik.file.getFile(request);
        String folder = WidokPlik.listaFolderow.getText(request);

        System.out.println("folder: " + folder);

        zapiszPlikNaDysku(folder, plik);
    }

    private void zapiszPlikNaDysku(String folder, Part filePart)
    {
        boolean czyUdaloSieZapisac = true;
        String fileName = filePart.getSubmittedFileName();
        File file = new File("C:\\borys\\" + folder + "\\" + fileName);

        try
        {
            InputStream fileContent = filePart.getInputStream();
            Files.copy(fileContent, file.toPath());
        } catch (Exception e)
        {
            System.out.println("nie udolao sie zapisac pliku");
            czyUdaloSieZapisac = false;
        }
        wyswietlRezultat(czyUdaloSieZapisac);

    }

    private void wyswietlRezultat(boolean czyUdaloSieZapisac)
    {
        String odpowiedz = "nie udalo sie zapisac pliku";
        if (czyUdaloSieZapisac)
        {
            odpowiedz = "udalo sie zapisac plik";
        }
        WidokPlik.info.setText(odpowiedz);
    }
}
