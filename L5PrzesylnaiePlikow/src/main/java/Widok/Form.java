package Widok;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author jasyn
 */
public class Form extends Element {

    private String action;

    public Form(String action) {
        this.action = action;
    }

    @Override
    public String getHtml() {
         
        String html = "<form method=\"post\" enctype=\"multipart/form-data\"  action=\"" + action + "\">\n";

        for (Element element : elementy) {
            html += element.getHtml();
        }

        html
                += "  <input type=\"submit\" value=\"Submit\">\n"
                + "</form> ";
        return html;
    }
}
