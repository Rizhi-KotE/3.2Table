package controler;

import java.util.Locale;

import javax.swing.JOptionPane;

import application.Main;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogEvent;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCharacterCombination;
import javafx.scene.input.KeyCombination;
import javafx.stage.FileChooser;
import model.Library;

public class MainMenu {
	private MenuBar menuBar;
	private Library library;

	public MenuBar getMenuBar() {
		return menuBar;
	}

	public MainMenu(Library lib) {
		library = lib;
		menuBar = new MenuBar();

		Menu menu = new Menu("file");
		menuBar.getMenus().add(menu);

		MenuItem item = new MenuItem("open");
		item.setOnAction((e) -> {
			// TODO
		});

		MenuItem save = new MenuItem("save");
		item.setOnAction((e) -> {
			FileChooser chooser = new FileChooser();
			chooser.showOpenDialog(Main.getStage());
		});
		menu.getItems().addAll(item, save);
		
		menu = new Menu("edit");
		menuBar.getMenus().add(menu);
		
		item = new MenuItem("find");
		item.setOnAction((e)->{new FindDialog(lib).show(FindDialog.Type.FIND);});
		menu.getItems().add(item);
		item = new MenuItem("delete");
		item.setOnAction((e)->{new FindDialog(lib).show(FindDialog.Type.DELETE);});
		menu.getItems().add(item);
		menu = new Menu("help");
		menuBar.getMenus().add(menu);

		Menu changeLanguage = new Menu("changeLanguage");
		menu.getItems().add(changeLanguage);

		MenuItem ru = new MenuItem("RU");
		ru.setOnAction((e) -> {
			Main.setBundle(new Locale("RU"));
		});
		MenuItem eng = new MenuItem("ENG");
		eng.setOnAction((e) -> {
			Main.setBundle(new Locale("ENG"));
		});
		changeLanguage.getItems().addAll(ru, eng);
		
		
	}
}
