/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.servletpodstawa;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author jasyn
 */
class PoleHtml {

    String nazwaPola, wartosc;
    String typPola;
    String validationRegex;
    String validationResponse="";

    String getName() {
        return nazwaPola;
    }
    String getValue()
    {
        return wartosc;
    }

    PoleHtml(String typPola, String nazwaPola, String wartoscDomyslan, String validationRegex) {
        this.nazwaPola = nazwaPola;
        this.typPola = typPola;
        this.wartosc = wartoscDomyslan;
        this.validationRegex = validationRegex;
    }

    public void putValue(String wartosc) {
        this.wartosc = wartosc;
    }

    String generujPole() {
        String html = "";

        html += "<label for=\"" + nazwaPola + "\">" + nazwaPola + ":</label><br>\n"
                + "  <input type=\"" + typPola + "\" id=\"" + typPola + "\" name=\"" + nazwaPola + "\" value=\"" + wartosc + "\">";        
            html += validationResponse;        
        html += "<br>";
        return html;
    }

    public boolean validate() {
        if (validationRegex == null) {
            validationResponse="";
            return true;
        } else {
            Pattern pattern = Pattern.compile(validationRegex);
            Matcher matcher = pattern.matcher(wartosc);
            boolean isOk = matcher.find();
            if(!isOk)
            {
                validationResponse ="<small>nieprawidlowy format</small>";
            }
            else
            {
                validationResponse ="";
            }
            return isOk;
        }
    }
}
