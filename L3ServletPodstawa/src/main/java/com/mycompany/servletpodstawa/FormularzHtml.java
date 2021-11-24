/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.servletpodstawa;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author jasyn
 */
public class FormularzHtml {
    //private String html;
    String action="";    
   private Map<String,PoleHtml> mapaPol = new HashMap<String,PoleHtml>();
   
   FormularzHtml(String action)
   {
       this.action = action;
   }
    
    public void addField(PoleHtml poleHtml)
    {
        String nazwaPola = poleHtml.getName();
        mapaPol.put(nazwaPola, poleHtml);
    }
   public void putValue(String nazwaPola, String wartosc) throws Exception
   {
       PoleHtml poleHtml =  mapaPol.get(nazwaPola);
       
       if(poleHtml ==null | wartosc==null)
       {
           throw new Exception("Nie ma takiego pola.");
       }
       else
       {
           poleHtml.putValue(wartosc);
       }
   }
    boolean validate ()
    {
        boolean isOk=true;
        
        for (Map.Entry<String, PoleHtml> entry : mapaPol.entrySet()) {
            PoleHtml poleHtml = entry.getValue();
            if(!poleHtml.validate())
            { 
                isOk=false;
            }            
        }
        return isOk;
    }
    public String generuj()
    {
        String html = "";
        //poczatek
        html+="<form action=\""+action+"\" method=\"post\">\n";
        
        for (Map.Entry<String, PoleHtml> entry : mapaPol.entrySet()) {
            PoleHtml poleHtml = entry.getValue();
            html+=poleHtml.generujPole();            
        }        
        //koniec
        html+="<input type=\"submit\" value=\"Submit\">\n";
        html+="</form>";        
        
        return html;
    }
    
    Map getfieldMap()
    {
        return mapaPol;
    }
}
