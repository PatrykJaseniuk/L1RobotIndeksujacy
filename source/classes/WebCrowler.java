package classes;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import interfaces.InterfaceWebCrowlerDataBase;
import interfaces.InterfaceWebCrowlerIndex;

public class WebCrowler implements InterfaceWebCrowlerIndex
{
    InterfaceWebCrowlerDataBase interfaceDataBase;
    String startUrl;
    Set<String> visitedUrls = new HashSet<String>();

    WebCrowler(InterfaceWebCrowlerDataBase interfacePushDataBase)
    {
        this.interfaceDataBase = interfacePushDataBase;
    }

    @Override
    public void index(String url)
    {
        indexPage(url);
    }

    void indexPage(String url)
    {
        System.out.println("\nindexing url:"+ url);
        //dodanie url do odwiedzonych
        visitedUrls.add(url);
        // uzyskanie z url drzewa skladniowego (syntactic tree)
        Document doc;
        try
        {
            doc = Jsoup.connect(url).get();
        } catch (Exception e)
        {
            System.out.println("nie można otwozyc strony: " + url);
            return;
        }

        // z dokumentu uzyskanie listy slow
        String text = doc.body().text();

        Pattern patern = Pattern.compile("\\W+");
        String tablicaSlow[] = patern.split(text);
        // wepchniecie listy slow kluczowych do bazy danych
        for (String keyWord : tablicaSlow)
        {
            interfaceDataBase.push(keyWord, url);
        }

        // z dokumentu uzyskanie listy linkow domeny
        Elements links = doc.select("a");
        

        for (Element element : links)
        {
            String urlToCheck = element.attr("abs:href");
            Pattern paternUrl = Pattern.compile(url);
            Matcher matcher = paternUrl.matcher(urlToCheck);
            
            if (matcher.find() & !visitedUrls.contains(urlToCheck))
            {
                indexPage(urlToCheck);
            }
        }
    }
}
