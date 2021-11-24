package classes.Gui;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.util.Collection;

import classes.BiedaGoogle;
import interfaces.InterfaceBiedaGoogle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Controler
{
	private InterfaceBiedaGoogle biedaGogle = new BiedaGoogle();

	@FXML
	private Button buttonIndeksuj;

	@FXML
	private TextField textFieldIndekuj;

	@FXML
	private TextField textFieldSzukaj;

	@FXML
    private ListView<String> listViewWynikWyszukiwania;

    @FXML
    private ListView<String> listViewZindeksowaneStrony;

	@FXML
	void buttonIndeksujClicked(ActionEvent event)
	{
		System.out.println("dzial indekuj");
		Collection<String> zindeksowaneStorny = biedaGogle.indexPage(textFieldIndekuj.getText());
		listViewZindeksowaneStrony.setItems(FXCollections.observableArrayList(zindeksowaneStorny));
		System.out.println(zindeksowaneStorny.toString());
	}

	@FXML
	void buttonSzukajClicked(ActionEvent event)
	{
		System.out.println("dzial szukaj");
		Collection<String> wynikWyszukiwania = biedaGogle.search(textFieldSzukaj.getText());
		listViewWynikWyszukiwania.setItems(FXCollections.observableArrayList(wynikWyszukiwania));
		System.out.println(wynikWyszukiwania.toString());
	}

}
