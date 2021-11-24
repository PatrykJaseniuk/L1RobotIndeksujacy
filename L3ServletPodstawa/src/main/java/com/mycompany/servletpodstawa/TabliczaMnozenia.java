/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.servletpodstawa;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import javax.persistence.Enumerated;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jasyn
 */
public class TabliczaMnozenia extends HttpServlet {

      
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
        response.setContentType("text/html;charset=UTF-8");
        Enumeration<String> initParamitersNames = getServletConfig().getInitParameterNames();
        String initParamiterValue = getServletConfig().getInitParameter(initParamitersNames.nextElement());
        try (PrintWriter out = response.getWriter()) {//konstrukcja try-with-resource
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet TabliczaMnozenia</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet TabliczaMnozenia at " + request.getContextPath() + "</h1>");
            out.println("<h1>Parametr inicjalizacyjny z deskryptora wdrozenia " + initParamiterValue + "</h1>");
            out.println(rysujTabliczkeMnozenia(Integer.parseInt(initParamiterValue)));
            out.println("</body>");
            out.println("</html>");
            
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
            throws ServletException, IOException {
       processRequest(request, response); 
            String parametr = request.getParameter("parametr");
            
             response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {//konstrukcja try-with-resource
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet TabliczaMnozenia</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println(rysujTabliczkeMnozenia(Integer.parseInt(parametr)));
            out.println("</body>");
            out.println("</html>");
        }
        
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

  String rysujTabliczkeMnozenia(int wymiar)
    {
        int tabliczkaMnozenia[][] =generujTabliczkeMnozenia(wymiar);
        String html=" ";
        html+="<table>";
        //pierwszy wiersz
        html+="<tr>";
        html+="<td>X</td>";
        for(int i=0;i<wymiar;i++)
        {
            int indeks = i+1;
            html+= "<td>" + indeks +"</td>";
        }
        html +="</tr>";
        
        //kolejne wiersze        
        for(int i=0;i<wymiar;i++)
        {
             html+="<tr>";
             
                //pierwsza komorka
                html+="<td>"+ (i+1) +"</td>";
                
                //pozostale komorki
                for(int j=0;j<wymiar;j++)
                {
                    html+="<td>"+tabliczkaMnozenia[i][j]+"</td>";
                }
             
             html +="</tr>";
        }
        
        
        html+="</table>";
        return html;
    }
  
  int [][] generujTabliczkeMnozenia(int wymiar)
  {
      int t[][]= new int[wymiar][wymiar];
      
      for(int i=0; i<wymiar;i++)
      {
          for(int j=0; j<wymiar; j++ )
          {
              t[i][j]=(i+1)*(j+1);
          }
      }
      return t;
  }
}
