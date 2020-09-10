package aufgabe301;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Window;

public class Controller implements Initializable {

	@FXML
	MenuBar menuBar;
	@FXML
	Menu fileMenu;
	@FXML
	MenuItem newFileMenuItem;
	@FXML
	MenuItem openFileMenuItem;
	@FXML
	MenuItem saveFileMenuItem;
	@FXML
	MenuItem saveFileAsMenuItem;
	@FXML
	Label label;
	@FXML
	TextArea textArea;

	File file;
	FileChooser fileChooser;

	public void initialize(URL location, ResourceBundle resources) {
		fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().add(new ExtensionFilter("Textdateien", "*.txt"));
	}

	public void newFile(ActionEvent actionEvent) {
		file = null;
		textArea.setText("");
		label.setText("");
	}

	public void openFile(ActionEvent actionEvent) {
		Window window = menuBar.getScene().getWindow();
		file = fileChooser.showOpenDialog(window);
		if (file != null) {
			label.setText(file.getAbsolutePath());
			try (FileReader fileReader = new FileReader(file);
					BufferedReader bufferedReader = new BufferedReader(fileReader)) {
				String input = "";
				String line;
				while ((line = bufferedReader.readLine()) != null) {
					input += line + "\n";
				}
				textArea.setText(input);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void saveFile(ActionEvent actionEvent) {
		if (file != null) {
			try (FileWriter fileWriter = new FileWriter(file);
					BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
				bufferedWriter.write(textArea.getText());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void saveFileAs(ActionEvent actionEvent) {
		Window window = menuBar.getScene().getWindow();
		file = fileChooser.showSaveDialog(window);
		if (file != null) {
			label.setText(file.getAbsolutePath());
			try (FileWriter fileWriter = new FileWriter(file);
					BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
				bufferedWriter.write(textArea.getText());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}