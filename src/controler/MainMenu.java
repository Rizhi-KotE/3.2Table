package controler;

import java.io.File;
import java.util.Locale;

import javax.swing.JOptionPane;

import application.LocalizedScene;
import application.Main;
import frm.SaveGraph;
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
			FileChooser chooser = new FileChooser();
			File fileName = chooser.showOpenDialog(Main.getStage());
			library.openFile(SaveGraph.loadLybrary(fileName));
		});

		MenuItem save = new MenuItem("save");
		save.setOnAction((e) -> {
			FileChooser chooser = new FileChooser();
			File fileName = chooser.showSaveDialog(Main.getStage());
			SaveGraph.save(fileName, library.getBooks());
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
			LocalizedScene.ChangeLocal("RU");
		});
		MenuItem eng = new MenuItem("ENG");
		eng.setOnAction((e) -> {
			LocalizedScene.ChangeLocal("ENG");
		});
		changeLanguage.getItems().addAll(ru, eng);
		
		
	}
}
