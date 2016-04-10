package util;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import view.PagedTable;

public class TableButtons {
	public static Pane attachedPanel(PagedTable table){
		Pane pane = new VBox();
		Pane navigation = new HBox();
		Button prev = new Button("previos");
		prev.setOnAction(e -> {
			table.previos();
		});
		Button first = new Button("first");
		first.setOnAction(e -> {
			table.first();
		});
		Button next = new Button("next");
		next.setOnAction(e -> {
			table.next();
		});
		Button end = new Button("end");
		end.setOnAction(e -> {
			table.end();
		});
		Label label = new Label(Integer.toString(table.getLibSize().get()));
		label.setMinSize(10, 0);
		table.getLibSize().addListener((e, oldValue, newValue) -> {
			label.setText(Integer.toString(table.getLibSize().get()));
		});
		navigation.getChildren().addAll(first, prev, label, next, end);
		pane.getChildren().addAll(table.getTable(), navigation);
		return pane;
	}
}
