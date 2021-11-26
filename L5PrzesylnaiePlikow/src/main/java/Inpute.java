
import javax.servlet.http.HttpServletRequest;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author jasyn
 */
class Inpute extends Element {

    static int licznik = 0;
    private String label;
    private String typ;
    private String nazwa;

    Inpute(String label, String typ) {
        this.nazwa = "input" + licznik++;
        this.label = label;
        this.typ = typ;
    }

    @Override
    public String getHtml() {
        String html = "<label for=\"" + nazwa + "\">" + label + ":</label><br>\n"
                + "  <input type=\"" + typ + "\" id=\"" + nazwa + "\" name=\"" + nazwa + "\"><br>";

        return html;
    }

    String getText(HttpServletRequest request) {
        String text = request.getParameter(nazwa);
        return text;
    }
}
