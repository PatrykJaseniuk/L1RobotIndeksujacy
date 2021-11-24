/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.servletpodstawa;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author jasyn
 */
public class MagicznyKwadratHtml {
    //private String html;
    String action="";    
   private Map<String,PoleHtml> mapaPol = new HashMap<String,PoleHtml>();
   int rozmiar = 5;
   MagicznyKwadratHtml(String action)
   {
       this.action = action;
       
       for(int y=0; y<rozmiar; y++)
       {
           for (int x=0; x<rozmiar; x++)
           {
               addField(new PoleHtml("text", x+""+y, "", "^\\d+$"));
           }
       }
   }
    
    private void addField(PoleHtml poleHtml)
    {
        String nazwaPola = poleHtml.getName();
        mapaPol.put(nazwaPola, poleHtml);
    }
   public void putValue(HttpServletRequest request)
   {
       for(int y=0; y<rozmiar; y++)
       {
           for (int x=0; x<rozmiar; x++)
           {
               
                   mapaPol.get(x+""+y).putValue(request.getParameter(x+""+y));
             
               
           }
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
        //sprawdzanie wierszy i kolumn
        for(int y=0; y<rozmiar; y++)
        {
            int sumaWiersz=0;
            int sumaKolumna=0;
            for (int x=0;x<rozmiar;x++)
            {
                try{
                sumaWiersz+=Integer.parseInt(mapaPol.get(x+""+y).getValue());
                sumaKolumna+=Integer.parseInt(mapaPol.get(y+""+x).getValue());
                  }catch(Exception e)
               {
                   return false;                  
               }
            }
            if(sumaWiersz!=5 || sumaKolumna!=5)
            {
                isOk=false;
                break;
            }
        }
        
        
        return isOk;
    }
    public String generuj()
    {
        String html = "";
        //poczatek
        html+="<form action=\""+action+"\" method=\"post\">\n";
         html+="<table>";
        for(int y=0; y<rozmiar; y++)
       {
           html+="<tr>";
           for (int x=0; x<rozmiar; x++)
           {
               html += "<td>"+mapaPol.get(x+""+y).generujPole()+"</td>";
           }
            html +="</tr>";
       }
       
        //koniec
        html+="<input type=\"submit\" value=\"Submit\">\n";
        html+="</form>";        
        
        return html;
    }
}
