/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.servletpodstawa;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jasyn
 */
public class Formularz extends HttpServlet {

    FormularzHtml formularzHtml = new FormularzHtml("");

    Formularz() {
        formularzHtml.addField(new PoleHtml("text", "imie", "", "^\\w+$"));
        formularzHtml.addField(new PoleHtml("text", "nazwisko", "", "^\\w+$"));
        formularzHtml.addField(new PoleHtml("text", "nr tel", "", "^\\d{9}$"));
        formularzHtml.addField(new PoleHtml("text", "email", "", "^\\w+@\\w+\\.\\w+$"));
        formularzHtml.addField(new PoleHtml("text", "pesel", "", "^\\d{11}$"));
        formularzHtml.addField(new PoleHtml("text", "data urodzenia", "", "^\\d{2}-\\d{2}-\\d{4}$"));
        formularzHtml.addField(new PoleHtml("text", "plec", "", "M|m|K|k"));
        formularzHtml.addField(new PoleHtml("text", "wzrost", "", "^\\d{1,3}$"));
        formularzHtml.addField(new PoleHtml("text", "hobby", "", ".+"));
        formularzHtml.addField(new PoleHtml("text", "co jest stolica Polski", "", "Warszawa"));
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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Formularz</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Formularz at " + request.getContextPath() + "</h1>");
            out.println(formularzHtml.generuj());
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

        //wprowadz pola
        try{
            formularzHtml.putValue("imie", request.getParameter("imie"));
            formularzHtml.putValue("nr tel", request.getParameter("nr tel"));
            formularzHtml.putValue("nazwisko", request.getParameter("nazwisko"));
            formularzHtml.putValue("email", request.getParameter("email"));
            formularzHtml.putValue("pesel", request.getParameter("pesel"));
            formularzHtml.putValue("data urodzenia", request.getParameter("data urodzenia"));
            formularzHtml.putValue("plec", request.getParameter("plec"));
            formularzHtml.putValue("hobby", request.getParameter("hobby"));
            formularzHtml.putValue("wzrost", request.getParameter("wzrost"));
             formularzHtml.putValue("co jest stolica Polski", request.getParameter("co jest stolica Polski"));
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        
        //sprawdz
        boolean validation = true;
        String html = "";
        String resoult="";
        validation = formularzHtml.validate();        
        
        if (validation) {
            resoult = "<h1> Validation ok</h1>\n"
                    + request.getParameter("imie");
        } else {
            //wyswietl formulaz z informacja
            resoult = formularzHtml.generuj();
        }
         html = "<!DOCTYPE html>\n"
                    + "<html>\n"
                    + "<head>\n"
                    + "<body>\n"
                    + "</body>\n"
                    + resoult
                    + "</html>\n";
         response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println(html);
        }
        //processRequest(request, response);
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

}
