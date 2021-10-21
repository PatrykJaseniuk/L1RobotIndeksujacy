/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.servletpodstawa;

/**
 *
 * @author jasyn
 */
class PoleHtml {
    
    String typPola, nazwaPola, wartoscDomyslna;
    
    PoleHtml(String typPola, String nazwaPola, String wartoscDomyslan)
    {
        this.nazwaPola = nazwaPola;
        this.typPola = typPola;
        this.wartoscDomyslna = wartoscDomyslan;
    }
    
    
    public void putValue(String wartosc)
    {
        
    }
    String generujPole()
    {
        String html="";
        html="<label for=\"fname\">First name:</label><br>\n" +
"  <input type=\"text\" id=\"fname\" name=\"fname\" value=\"John\"><br>";
        
        return html;
    }

    boolean validate() {
    
        return true;
    }
    
}
