package classes;

import interfaces.InterfaceBiedaGoogle;
import interfaces.InterfaceSearchDataBase;
import interfaces.InterfaceWebCrowlerIndex;

public class BiedaGoogle implements InterfaceBiedaGoogle
{

    @Override
    public void indexPage(String url)
    {
       interfaceWebCrowlerIndex.index(url);       
    }

    @Override
    public String log()
    {
        
        return "witam panstwa";
    }

    @Override
    public String[] search(String[] keyWords)
    {        
        return interfaceSearchDataBase.search(keyWords);
    }
    
   private DataBase dataBase = new DataBase();
   private InterfaceSearchDataBase interfaceSearchDataBase = dataBase;
   private WebCrowler webCrowler = new WebCrowler(dataBase);  
   private InterfaceWebCrowlerIndex interfaceWebCrowlerIndex = webCrowler;
    
}
