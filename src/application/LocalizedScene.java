package application;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class LocalizedScene extends Scene {

	private static Set<Parent>  scenes = new HashSet<>();
	private static Map<Object, String[]> text = new HashMap<>(); 
	public LocalizedScene(Parent root) {
		super(root);
		localizeLabeled(root);
		scenes.add(root);
	}

	public LocalizedScene(Parent root, double width, double height) {
		super(root, width, height);
		localizeLabeled(root);
		scenes.add(root);
	}
	
	public static void ChangeLocal(String s){
		Main.setBundle(new Locale(s));
		for(Object p: text.keySet()){
			localiseNode("", p);
		}
	}

	private static void localizeLabeled(Parent root) {
		Iterator<Node> it = root.getChildrenUnmodifiable().iterator();
		while (it.hasNext()) {
			Node node = it.next();

			Method method = null;
			try {
				method = node.getClass().getMethod("setText", String.class);
			} catch (NoSuchMethodException e) {

			} catch (SecurityException e) {

			}
			if (node instanceof TextField) {
				localiseNode("PromptText", node);
			} else if (method != null) {
				localiseNode("Text", node);
			} else if (node instanceof MenuBar) {
				localiseMenuBar((MenuBar) node);
			} else if (node instanceof TableView<?>) {
				localiseTable((TableView) node);
			} else if (node instanceof Parent) {
				if (((Parent) node).getChildrenUnmodifiable() != null) {
					localizeLabeled((Parent) node);
				}
			}
		}
	}

	private static void localiseMenuBar(MenuBar node) {
		for (Menu menu : node.getMenus()) {
			localiseNode("Text", menu);
			for (MenuItem item : menu.getItems()) {
				localiseNode("Text", item);
			}
		}
	}

	private static void localiseTable(TableView node) {
		Iterator<TableColumn<?, ?>> it = node.getColumns().iterator();
		while (it.hasNext()) {
			TableColumn<?, ?> column = it.next();
			localiseNode("Text", column);
		}
	}

	private static void localiseNode(String name, Object node) {
		Method set = null;
		Method get = null;
		if(text.get(node)!=null&&text.get(node)[1]!=null){
			name = text.get(node)[1];
		}
		try {
			set = node.getClass().getMethod("set" + name, String.class);
			get = node.getClass().getMethod("get" + name, new Class[0]);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		String currentText = text.get(node)[0];
		if(currentText==null)
		try {
			currentText = (String) get.invoke(node, new Object[0]);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

		if (currentText != null) {
			String newText = null;
			try {
				newText = Main.getBundle().getString(currentText);
			} catch (Exception e) {
				newText = currentText;
			}
			try {
				set.invoke(node, newText);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
			text.put(node, (String[])Arrays.asList(currentText, name).toArray());
		}
	}
}
