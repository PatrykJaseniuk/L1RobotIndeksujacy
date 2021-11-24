package classes;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import interfaces.InterfaceWebCrowlerDataBase;
import interfaces.InterfaceWebCrowlerIndex;

public class WebCrowler implements InterfaceWebCrowlerIndex
{
    InterfaceWebCrowlerDataBase interfaceDataBase;
    

    WebCrowler(InterfaceWebCrowlerDataBase interfacePushDataBase)
    {
        this.interfaceDataBase = interfacePushDataBase;
    }

    @Override
    public Collection<String> index(String startUrl)
    {
        Collection<String> visitedUrls;
        visitedUrls = ThreadIndexPage.setData(startUrl, interfaceDataBase, 10);
        try {
            ThreadIndexPage.startIndexing();
        } catch (Exception e) {
            //TODO: handle exception
        }
        
        return visitedUrls;
    }

   
}
