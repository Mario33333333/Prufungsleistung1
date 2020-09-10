package aufgabe301;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Window;

public class Controller implements Initializable{

	File file;
	FileChooser fileChooser;
	
	
	public void initialize(URL location, ResourceBundle resources) {
		fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().add(new ExtensionFilter("Textdateien", "*.txt"));
		
	}
	
	public void newFile(ActionEvent event) {
		file = null;
		textArea.setText("");
		label.setText("");
	}
	
	public void saveFile(ActionEvent event) {
		if (file != null) {
			try(FileWriter fW = new FileWriter(file); BufferedWriter bW = new BufferedWriter(fW)) {
				bW.write(textArea.getText());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}		
	}
	
	public void saveFileAs(ActionEvent event) {
		Window window = menuBar.getScene().getWindow();
		file = fileChooser.showSaveDialog(window);
		if (file != null) {
			
		}
	}

}
