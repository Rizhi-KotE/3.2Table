package application;

import java.util.Locale;
import java.util.ResourceBundle;

import controler.TableControler;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Library;
import util.TableButtons;
import view.AddForm;
import view.MainMenu;
import view.PagedTable;

public class Main extends Application {
	private static ResourceBundle bundle;

	/**
	 * @return the bundle
	 */
	public static ResourceBundle getBundle() {
		if (bundle == null) {
			try {
				bundle = ResourceBundle.getBundle("application.text", Locale.getDefault());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return bundle;
	}

	/**
	 * @param bundle
	 *            the bundle to set
	 */
	public static void setBundle(Locale loc) {
		Main.bundle = ResourceBundle.getBundle("application.text", loc);
	}

	private static Stage primaryStage;

	public static Stage getStage() {
		return primaryStage;
	}

	@Override
	public void start(Stage primaryStage) {
		Main.primaryStage = primaryStage;
		Library lib = new Library();

		VBox grid = new VBox();
		
		TableControler mainControler = new TableControler(lib);
		PagedTable mainTable = new PagedTable();
		AddForm add = new AddForm(mainControler);
		MainMenu menu = new MainMenu(mainControler);
		
		mainControler.registerObserverTable(mainTable);
		grid.getChildren().addAll(menu.getMenuBar(), TableButtons.attachedPanel(mainTable), add.getPane());
		
		Scene scene = new LocalizedScene(grid, 400, 400);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setOnCloseRequest((e) -> System.exit(0));
	}

	public static void main(String[] args) {
		launch(args);
	}
}
