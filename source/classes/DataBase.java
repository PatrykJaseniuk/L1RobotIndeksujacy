package classes;

import interfaces.InterfaceWebCrowlerDataBase;
import interfaces.InterfaceSearchDataBase;

public class DataBase implements InterfaceWebCrowlerDataBase, InterfaceSearchDataBase
{

    @Override
    public void push(String keyWord, String url)
    {
        System.out.println("addnig url: "+ url+ " under index: " + keyWord);        
    }

    @Override
    public String[] search(String[] KeyWords)
    {
        System.out.println("wyszukuje adresow url zawierajacych: "+ KeyWords);
        return new String[]{"adres url 1", "adres url 2"};
    }

    @Override
    public boolean isUrlExist(String url)
    {
        // TODO Auto-generated method stub
        return false;
    }
    
}
