package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Library;
import view.AddForm;
import view.MainTable;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Library lib = new Library();

			VBox grid = new VBox();
			grid.getChildren().add(new MainTable(lib).getTable());
			grid.getChildren().add(new AddForm(lib).getPane());

			Scene scene = new Scene(grid, 400, 400);

			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setOnCloseRequest((e) -> System.exit(0));
			;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
