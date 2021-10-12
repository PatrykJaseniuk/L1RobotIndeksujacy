package interfaces;

public interface InterfaceWebCrowlerDataBase
{
    void push(String keyWord, String url);
    boolean isUrlExist(String url);
}
