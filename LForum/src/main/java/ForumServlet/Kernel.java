/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ForumServlet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author jasyn
 */
public class Kernel
{

    static List<Pytanie> pytania = new LinkedList<>();
//    static Set<Uzytkownik> uzytkownicy = new HashSet<>();
//    static BazaDanych bazaDanych = new BazaDanych("jdbc:mysql://localhost:3306/test?serverTimezone=UTC","root", "admin");
    static String connectionUrl = "jdbc:mysql://localhost:3306/dbforum?useTimeZone=true&serverTimezone=UTC&autoReconnect=true&useSSL=false";
    static String dbAcount = "root";
    static String dbPassword = "admin";

    public static final String nazwaAtrybutuSesji = "uzytkownik";

    public static Boolean rejestruj(String login, String haslo1, String haslo2)
    {
        if (haslo1 != null && login != null)
        {
            boolean czyHaslaTakieSame = haslo1.equals(haslo2);
            boolean czyHaslo1NiePuste = !"".equals(haslo1);
            boolean czyHaslo2NiePuste = !"".equals(haslo2);
            boolean czyLoginNiePusty = !"".equals(login);

            if (czyHaslaTakieSame && czyHaslo1NiePuste && czyHaslo2NiePuste && czyLoginNiePusty)
            {
                Set<Uzytkownik> uzytkownicy = dbGetUzytkownicy();
                AtomicReference<Boolean> czyJestTakiLogin = new AtomicReference<>(false);
                uzytkownicy.forEach(uzytkownik ->
                {
                    if (uzytkownik.login == login)
                    {
                        czyJestTakiLogin.set(true);
                    }
                });
                if (!czyJestTakiLogin.get())
                {
                    dbAddUser(new Uzytkownik(login, haslo1));
                    return true;
                }
            }
        }
        return false;
    }

    public static Object getUzytkownik(String login)
    {
        Set<Uzytkownik> uzytkownicy = dbGetUzytkownicy();
        Uzytkownik szukanyUzytkownik = null;
        AtomicReference<Uzytkownik> wraper = new AtomicReference(szukanyUzytkownik);
        uzytkownicy.forEach(new Consumer<Uzytkownik>()
        {
            @Override
            public void accept(Uzytkownik uzytkownik)
            {
                if (uzytkownik.login.equals(login))
                {
                    wraper.set(uzytkownik);
                }
            }
        });
        return wraper.get();
    }

    public static boolean dodajPytanie(HttpSession session, String tytulPytania, String trescPytania)
    {
        Uzytkownik uzytkownik = (Uzytkownik) session.getAttribute(nazwaAtrybutuSesji);
        if (uzytkownik != null)
        {
            dbDodajPytanie(uzytkownik.id, tytulPytania, trescPytania);
            return true;
        }
        return false;
    }

    public static Uzytkownik getUser(HttpSession session)
    {
        return (Uzytkownik) session.getAttribute(nazwaAtrybutuSesji);
    }

    public static boolean dodajOdpowiedz(HttpSession session, int idPytania, String trescOdpowiedzi)
    {
        Uzytkownik uzytkownik = getUser(session);

        if (uzytkownik != null)
        {
            dbDodajOdpowiedz(uzytkownik.id, idPytania, trescOdpowiedzi);
            return true;
        }
        return false;
    }

//    public static boolean logIn(String login, String haslo)
//    {
//        if ("Borys".equals(login) && "Borys".equals(haslo))
//        {
//            return true;
//        } else
//        {
//            return false;
//        }
//    }
    public static List<Pytanie> getNajnowszePytania()
    {

        List<Pytanie> pytania = dbGetPytania();

        pytania.sort(new Comparator<Pytanie>()
        {
            @Override
            public int compare(Pytanie pytanie1, Pytanie pytanie2)
            {
                return pytanie1.data.compareTo(pytanie2.data);
            }
        });
//        pytania.add(new Pytanie(1, 2022, "Ile jest gwiazd na niebie", "Kto to potrafi zliczyc"));
//        pytania.add(new Pytanie(2, 2022, "Ile wazy ogien", "Tresc pytania"));
        return pytania.subList(0, pytania.size() > 5 ? 5 : pytania.size());
    }

    public static List<Pytanie> wyszukajPytan(String wyszukiwaneHaslo)
    {
        List<Pytanie> pytania = dbGetPytania();
        List<Pytanie> pasujacePytania = new LinkedList<>();
        if (wyszukiwaneHaslo != null)
        {
            for (Pytanie pytanie : pytania)
            {
                if (pytanie.tytul.contains(wyszukiwaneHaslo) || pytanie.tresc.contains(wyszukiwaneHaslo))
                {
                    pasujacePytania.add(pytanie);
                }
            }
        }
        return pasujacePytania;
    }

    public static Integer getIloscPytan()
    {
        return 100;
    }

    public static Integer getIloscOdpowiedzi()
    {
        return 1234;
    }

    public static Pytanie getPytanie(Integer id)
    {
        
        Pytanie pytanie = dbGetPytanie(id);
        return pytanie;
    }

    public static boolean isAdmin(HttpSession session)
    {
        Uzytkownik uzytkownik = (Uzytkownik) session.getAttribute(nazwaAtrybutuSesji);
        if (uzytkownik != null)
        {
            if ("admin".equalsIgnoreCase(uzytkownik.login))
            {
                return true;
            }
        }
        return false;
    }

    public static boolean deletePytanie(HttpSession session, Integer idPytania)
    {
        if (isAdmin(session))
        {
            dbDeletePytanie(idPytania);
            return true;
        }
        return false;
    }

    public static boolean logIn(HttpSession session, String login, String haslo)
    {
        Set<Uzytkownik> uzytkownicy = dbGetUzytkownicy();

        Uzytkownik u = new Uzytkownik(login, haslo);
        AtomicReference<Boolean> czyJestTakiUzytkownik = new AtomicReference<>(false);
        uzytkownicy.forEach(uzytkownik ->
        {
            if (uzytkownik.equals(u))
            {
                czyJestTakiUzytkownik.set(true);
            }
        });
        if (czyJestTakiUzytkownik.get())
        {
            session.setAttribute(Kernel.nazwaAtrybutuSesji, Kernel.getUzytkownik(login));
            return true;
        } else
        {
            return false;
        }
    }

    private static Set<Uzytkownik> dbGetUzytkownicy()
    {
        Set<Uzytkownik> uzytkownicy = new HashSet<>();
        String sqlSelectAllPersons = "SELECT * FROM dbforum.user;";
        try (Connection conn = DriverManager.getConnection(connectionUrl, dbAcount, dbPassword);
                PreparedStatement ps = conn.prepareStatement(sqlSelectAllPersons);
                ResultSet rs = ps.executeQuery())
        {
            Date data = new Date();
            while (rs.next())
            {
                int id = rs.getInt("id");
                String login = rs.getString("login");
                int hashHaslo = rs.getInt("passwordhash");

                uzytkownicy.add(new Uzytkownik(id, login, hashHaslo));
            }
        } catch (SQLException e)
        {
            System.out.print(e);
        }
        return uzytkownicy;
    }

    private static Boolean dbAddUser(Uzytkownik uzytkownik)
    {
        String sqlAddUser = "INSERT INTO `dbforum`.`user` (`login`, `passwordhash`) VALUES ('" + uzytkownik.login + "', '" + uzytkownik.hashHaslo + "');";
        try
        {
            Connection conn = DriverManager.getConnection(connectionUrl, dbAcount, dbPassword);
            PreparedStatement ps = conn.prepareStatement(sqlAddUser);
            return ps.execute();
//            Date data = new Date();
//            while (rs.next())
//            {
//                String login = rs.getString("login");
//                int hashHaslo = rs.getInt("passwordhash");
//
//                uzytkownicy.add(new Uzytkownik(login, hashHaslo));
//            }
        } catch (SQLException e)
        {
            System.out.print(e);
        }
        return false;
    }

    private static void dbDeletePytanie(Integer idPytania)
    {
        String sqlDeletePytanie = "DELETE FROM `dbforum`.`pytanie` WHERE (`idpytanie` = '" + idPytania + "');";
        try
        {
            Connection conn = DriverManager.getConnection(connectionUrl, dbAcount, dbPassword);
            PreparedStatement ps = conn.prepareStatement(sqlDeletePytanie);
            ps.execute();
//            Date data = new Date();
//            while (rs.next())
//            {
//                String login = rs.getString("login");
//                int hashHaslo = rs.getInt("passwordhash");
//
//                uzytkownicy.add(new Uzytkownik(login, hashHaslo));
//            }
        } catch (SQLException e)
        {
            System.out.print(e);
        }
    }

    private static void dbDodajPytanie(int idUzytkownika, String tytulPytania, String trescPytania)
    {
        String sqlAddPytanie = "INSERT INTO `dbforum`.`pytanie` (`tresc_pytania`, `tytul_pytania`, `uzytkownik`) VALUES ('" + trescPytania + "', '" + tytulPytania + "', '" + idUzytkownika + "');";
        try
        {
            Connection conn = DriverManager.getConnection(connectionUrl, dbAcount, dbPassword);
            PreparedStatement ps = conn.prepareStatement(sqlAddPytanie);
            ps.execute();
//            Date data = new Date();
//            while (rs.next())
//            {
//                String login = rs.getString("login");
//                int hashHaslo = rs.getInt("passwordhash");
//
//                uzytkownicy.add(new Uzytkownik(login, hashHaslo));
//            }
        } catch (SQLException e)
        {
            System.out.print(e);
        }
    }

    private static void dbDodajOdpowiedz(int idUzytkownika, int idPytania, String trescOdpowiedzi)
    {
       String sqlAddOdpowiedz = "INSERT INTO `dbforum`.`odpowiedz` (`pytanie`, `uzytkownik`, `tresc_odpowiedzi`) VALUES ('"+idPytania+"', '"+idUzytkownika+"', '"+trescOdpowiedzi+"');";
        try
        {
            Connection conn = DriverManager.getConnection(connectionUrl, dbAcount, dbPassword);
            PreparedStatement ps = conn.prepareStatement(sqlAddOdpowiedz);
            ps.execute();
        } catch (SQLException e)
        {
            System.out.print(e);
        }
    }

    private static List<Pytanie> dbGetPytania()
    {
        List<Pytanie> pytania = new LinkedList<>();
        String sqlSelectAllPytania = "SELECT * FROM dbforum.pytanie;";
        try (Connection conn = DriverManager.getConnection(connectionUrl, dbAcount, dbPassword);
                PreparedStatement ps = conn.prepareStatement(sqlSelectAllPytania);
                ResultSet rs = ps.executeQuery())
        {
            while (rs.next())
            {
                int idPytania = rs.getInt("idpytanie");
                String trescPytania = rs.getString("tresc_pytania");
                String tytulPytania = rs.getString("tytul_pytania");
                int idUzytkownik = rs.getInt("uzytkownik");
                Uzytkownik uzytkownik = dbGetUzytkownik(idUzytkownik);

                pytania.add(new Pytanie(idPytania, uzytkownik, new Date(), tytulPytania, trescPytania, null));
            }
        } catch (SQLException e)
        {
            System.out.print(e);
        }
        return pytania;
    }

    private static Pytanie dbGetPytanie(Integer idPytanie)
    {
        Pytanie pytanie = null;
        String sqlSelectPytanie = "SELECT * FROM dbforum.pytanie WHERE idpytanie=" + idPytanie + " ;";
        try (Connection conn = DriverManager.getConnection(connectionUrl, dbAcount, dbPassword);
                PreparedStatement ps = conn.prepareStatement(sqlSelectPytanie);
                ResultSet rs = ps.executeQuery())
        {
            while (rs.next())
            {
                int idPytania = rs.getInt("idpytanie");
                String trescPytania = rs.getString("tresc_pytania");
                String tytulPytania = rs.getString("tytul_pytania");
                int idUzytkownik = rs.getInt("uzytkownik");
                Uzytkownik uzytkownik = dbGetUzytkownik(idUzytkownik);
                List<Odpowiedz> odpowiedzi = dbGetOdpowiedzi(idPytania);

                pytanie = new Pytanie(idPytania, uzytkownik, new Date(), tytulPytania, trescPytania, odpowiedzi);
            }
        } catch (SQLException e)
        {
            System.out.print(e);
        }
        return pytanie;
    }

    private static Uzytkownik dbGetUzytkownik(int idUzytkownik)
    {
        Uzytkownik uzytkownik = null;
        String sqlSelectUzytkownik = "SELECT * FROM dbforum.user WHERE id=" + idUzytkownik + " ;";
        try (Connection conn = DriverManager.getConnection(connectionUrl, dbAcount, dbPassword);
                PreparedStatement ps = conn.prepareStatement(sqlSelectUzytkownik);
                ResultSet rs = ps.executeQuery())
        {
            while (rs.next())
            {
                int idUzytkownika = rs.getInt("id");
                String login = rs.getString("login");
                int passwordHash = rs.getInt("passwordhash");

                uzytkownik = new Uzytkownik(idUzytkownika, login, passwordHash);
            }
        } catch (SQLException e)
        {
            System.out.print(e);
        }
        return uzytkownik;
    }

    private static List<Odpowiedz> dbGetOdpowiedzi(int idPytania)
    {
        List<Odpowiedz> odpowiedzi = new LinkedList<>();
        String sqlSelectUzytkownik = "SELECT * FROM dbforum.odpowiedz WHERE pytanie=" + idPytania + ";";
        try (Connection conn = DriverManager.getConnection(connectionUrl, dbAcount, dbPassword);
                PreparedStatement ps = conn.prepareStatement(sqlSelectUzytkownik);
                ResultSet rs = ps.executeQuery())
        {
            while (rs.next())
            {
                int idOdpowiedz = rs.getInt("id_odpowiedz");
                String trescOdpowiedzi = rs.getString("tresc_odpowiedzi");
                int idUzytkownika = rs.getInt("uzytkownik");
                Uzytkownik uzytkownik = dbGetUzytkownik(idUzytkownika);

                odpowiedzi.add(new Odpowiedz(idOdpowiedz, uzytkownik, trescOdpowiedzi, new Date()));
            }
        } catch (SQLException e)
        {
            System.out.print(e);
        }
        return odpowiedzi;
    }
}
