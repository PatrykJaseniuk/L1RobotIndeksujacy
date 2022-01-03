/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package L2;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.servlet.ServletContext;
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

    private final String lokoalizacjaFolderowZPlikami = "C:\\borys\\";
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
        obslugaPobieraniaPlikuOdKlienta(request);
        obslugaWysylaniaPlikuDoKlienta(request, response);
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

    private void zapiszPlikNaDysku(String folder, Part filePart)
    {
        boolean czyUdaloSieZapisac = true;       

        try
        {
            String fileName = filePart.getSubmittedFileName();
        File file = new File(lokoalizacjaFolderowZPlikami+"" + folder + "\\" + fileName);            
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

    private void obslugaWysylaniaPlikuDoKlienta(HttpServletRequest request, HttpServletResponse response)
    {
        WidokPlik.listaPlikow.indeksowanieFolderu(lokoalizacjaFolderowZPlikami);
        PrzesylanieWybranegoPliku(request, response);
    }

    private void obslugaPobieraniaPlikuOdKlienta(HttpServletRequest request)
    {
        Part plik = WidokPlik.file.getFile(request);
        String folder = WidokPlik.listaFolderow.getText(request);
        System.out.println("folder: " + folder);
        zapiszPlikNaDysku(folder, plik);
    }



    private void PrzesylanieWybranegoPliku(HttpServletRequest request, HttpServletResponse response)
    {
        String adresPliku = WidokPlik.listaPlikow.getAdresPliku(request);
        if(adresPliku!=null)
        {
            wyslijPlik(adresPliku, response);
        }
        
    }



    private void wyslijPlik(String filePath, HttpServletResponse response)
    {
        try{       
                // reads input file from an absolute path
        File downloadFile = new File(filePath);
        FileInputStream inStream = new FileInputStream(downloadFile);
         
        // if you want to use a relative path to context root:
        String relativePath = getServletContext().getRealPath("");
        System.out.println("relativePath = " + relativePath);
         
        // obtains ServletContext
        ServletContext context = getServletContext();
         
        // gets MIME type of the file
        String mimeType = context.getMimeType(filePath);
        if (mimeType == null) {        
            // set to binary type if MIME mapping not found
            mimeType = "application/octet-stream";
        }
        System.out.println("MIME type: " + mimeType);
         
        // modifies response
        response.setContentType(mimeType);
        response.setContentLength((int) downloadFile.length());
         
        // forces download
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
        response.setHeader(headerKey, headerValue);
         
        // obtains response's output stream
        OutputStream outStream = response.getOutputStream();
         
        byte[] buffer = new byte[4096];
        int bytesRead = -1;
         
        while ((bytesRead = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, bytesRead);
        }
         
        inStream.close();
        outStream.close();   
        }
        catch(Exception e)
        {
            
        }
    }
}
