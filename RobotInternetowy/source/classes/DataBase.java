package classes;

import interfaces.InterfaceWebCrowlerDataBase;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import interfaces.InterfaceSearchDataBase;

public class DataBase implements InterfaceWebCrowlerDataBase, InterfaceSearchDataBase
{
    Map<String, Set<String>> keyWordUrlMap = new HashMap<String, Set<String>>();

    @Override
    public void push(String keyWord, String url)
    {
        //System.out.println("addnig url: " + url + " under index: " + keyWord);
        Set<String> urlSet;
        if ((urlSet = keyWordUrlMap.get(keyWord)) != null)
        {
            urlSet.add(url);
        }
        else
        {
            Set<String> newUrlSet = new HashSet<String>();
            newUrlSet.add(url);
            keyWordUrlMap.put(keyWord, newUrlSet);
        }
    }

    @Override
    public Collection<String> search(String keyWordsString)
    {
        Set<String> urlSet = new HashSet<String>();

        Pattern patern = Pattern.compile("\\W+");
        String keyWords[] = patern.split(keyWordsString);

        if (keyWords.length > 0)
        {
            urlSet = keyWordUrlMap.get(keyWords[0]);
        }

        for (String keyWord : keyWords)
        {
            Set<String> localUrlSet;
            if ((localUrlSet = keyWordUrlMap.get(keyWord)) != null)
            {
                try
                {
                    urlSet.retainAll(localUrlSet);
                } catch (Exception e)
                {
                    System.out.println(e.getMessage());
                }
            }
        }

        return urlSet;

    }

    @Override
    public boolean isUrlExist(String url)
    {
        // TODO Auto-generated method stub
        return false;
    }

}
