/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author jasyn
 */
class Form extends Element {

    private String action;

    Form(String action) {
        this.action = action;
    }

    @Override
    public String getHtml() {
        String html = "<form action=\"" + action + "\">\n";

        for (Element element : elementy) {
            html += element.getHtml();
        }

        html
                += "  <input type=\"submit\" value=\"Submit\">\n"
                + "</form> ";

        return html;
    }
}
