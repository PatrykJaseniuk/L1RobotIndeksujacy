package classes;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import interfaces.InterfaceWebCrowlerDataBase;

/**
 * Klasa indeksujaca streone i podstrony wielowatkowo. Przed uzyciem nalezy
 * wywolac statyczna metode 'setData'. Aby rozpoczac indeksowanie wywolaj
 * statyczna metode startIndexing *
 */
public class ThreadIndexPage extends Thread
{

	static String startUrl;
	static InterfaceWebCrowlerDataBase interfaceDataBase;
	static Set<String> visitedUrls = new UrlHashSet();
	static int maxQuantityOfThreads;
	static int actualQuantityOfThreads = 0;

	private String url;

	/**
	 * Statyczna metoda inicjalizujaca niezbedne dane
	 * 
	 * @param startUrl url rozpoczecia indeksowania
	 * @param interfaceDataBase interface do bazydanyc gdzie beda zapisywane
	 * indeksy
	 * @param maxQuantityOfThreads maksymalna liczba watkow
	 * @param visitedUrls referencja na kolekcje odwiedzonych stron
	 */
	static Collection<String> setData(String startUrl, InterfaceWebCrowlerDataBase interfaceDataBase,
			int maxQuantityOfThreads)
	{
		ThreadIndexPage.startUrl = startUrl;
		ThreadIndexPage.interfaceDataBase = interfaceDataBase;
		ThreadIndexPage.maxQuantityOfThreads = maxQuantityOfThreads;
		return ThreadIndexPage.visitedUrls;
	}

	public static void startIndexing()
	{
		if (startUrl == null || interfaceDataBase == null)
		{
			System.out.println("nie wywolano funkcji set Data");
		}
		else
		{
			new ThreadIndexPage(startUrl);

			while (true)
			{
				if (actualQuantityOfThreads == 0)
				{
					break;
				}
				try
				{
					sleep(5000);
				} catch (Exception e)
				{
					// TODO: handle exception
				}
			}
			System.out.println("strony zindeksowane");
		}
	}

	private ThreadIndexPage(String url)
	{
		actualQuantityOfThreads++;
		System.out.println("liczba aktywnych watkow: " + actualQuantityOfThreads);
		this.url = url;
		start();
	}

	void indexPage(String url)
	{
		System.out.println("\nindexing url:" + url);

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
			Pattern paternUrl = Pattern.compile(startUrl);
			Matcher matcher = paternUrl.matcher(urlToCheck);

			if (matcher.find() & !visitedUrls.contains(urlToCheck))
			{
				// dodanie url do odwiedzonych
				visitedUrls.add(url);
				if (maxQuantityOfThreads > actualQuantityOfThreads)
				{
					new ThreadIndexPage(urlToCheck);
				}
				else
				{
					indexPage(urlToCheck);
				}

			}
		}
	}

	public void run()
	{
		indexPage(url);
		actualQuantityOfThreads--;
	}
}
