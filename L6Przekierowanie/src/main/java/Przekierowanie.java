/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jasyn
 */
@WebServlet(urlPatterns =
{
    "/Przekierowanie"
})
public class Przekierowanie extends HttpServlet
{

    Przekierowanie()
    {
        Gui.KonstruowanieGui();
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
        String urlKrotkie = Gui.input.getText(request);
        
        String urlDocelowe = getDoceloweUrl(urlKrotkie);
        if(urlDocelowe!=null)
        {
            przekieruj(urlDocelowe, response);
        }
        else
        {
            Gui.view.response(response); 
        }   
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

    private String getUrlKrotkie(HttpServletRequest request)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    private void przekieruj(String docelowyUrl, HttpServletResponse response)
    {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        try
        {
            response.sendRedirect(docelowyUrl);
        }
        catch(Exception e)
        {
            
        }
            
    }

    private String getDoceloweUrl(String urlKrotkie)
    {
        Map<String,String> mapaUrl = new HashMap<String, String>();
        
        wypelnianieMapy(mapaUrl);
        
        return mapaUrl.get(urlKrotkie);        
    }

    private void wypelnianieMapy(Map<String, String> mapaUrl)
    {
       try {
      File myObj = new File("C:\\Users\\jasyn\\OneDrive\\Desktop\\UrlMap.txt");
      Scanner myReader = new Scanner(myObj);
      while (myReader.hasNextLine()) {
        String urlKrotkie = myReader.nextLine();
        String urlDlugie="";
        if(myReader.hasNextLine())
        {
            urlDlugie = myReader.nextLine();
        }
        mapaUrl.put(urlKrotkie, urlDlugie);
        
        String info = urlKrotkie+" -> "+urlDlugie;
        System.out.println();
      }
      myReader.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
    }

}
