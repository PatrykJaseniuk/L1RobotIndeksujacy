package classes.Gui;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.util.Collection;

import classes.BiedaGoogle;
import interfaces.InterfaceBiedaGoogle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Controler
{
	InterfaceBiedaGoogle biedaGogle = new BiedaGoogle();

	@FXML
	private Button buttonIndeksuj;

	@FXML
	private TextArea textAreaWynikiWyszukiwania;

	@FXML
	private TextArea textAreaZindeksowaneStrony;

	@FXML
	private TextField textFieldIndekuj;

	@FXML
	private TextField textFieldSzukaj;

	@FXML
	void buttonIndeksujClicked(ActionEvent event)
	{
		System.out.println("dzial indekuj");
		Collection<String> zindeksowaneStorny = biedaGogle.indexPage(textFieldIndekuj.getText());
		textAreaZindeksowaneStrony.setText(zindeksowaneStorny.toString());
	}

	@FXML
	void buttonSzukajClicked(ActionEvent event)
	{
		System.out.println("dzial szukaj");
	}

}
