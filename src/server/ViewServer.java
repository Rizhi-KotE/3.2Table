package server;

import javax.swing.JFrame;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ViewServer extends Application {
	JFrame frame;

	TextArea text;
	TableServer server;
	@Override
	public void start(Stage primaryStage) throws Exception {
		Pane root = new VBox();
		
		text = new TextArea();		
		
		Button start = new Button("start");
		Button stop = new Button("stop");
		
		root.getChildren().addAll(text, start, stop);
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args){
		launch(args);
	}
}
