package ch.supsi.connectfour.frontend.view;

import ch.supsi.connectfour.backend.model.handler.LocalizationModelHandler;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.*;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PreferenceView implements WritePreferenceView{
    private LocalizationModelHandler localizationModelHandler;
    private Stage stage;

    //constructor
    public PreferenceView(LocalizationModelHandler localizationModelHandler, Stage stage) {
        this.localizationModelHandler = localizationModelHandler;
        this.stage = stage;
    }

    //static

    //private

    //public
    public File showDirChooser(String initialPath){
        File file;
        try {
            DirectoryChooser directoryChooser=new DirectoryChooser();
            directoryChooser.setTitle(".");
            directoryChooser.setInitialDirectory(new File(initialPath));
            file=directoryChooser.showDialog(this.stage);
        }finally {
        }
        return file;
    }

    public String showPopUpEditSymbolPlayerSecond(String symbolPlayerSecond, String symbolFuturePlayerFirst) {
        // Carica la lista di simboli da un file delle risorse
        List<String> symbols = new ArrayList<>();
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("symbols.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                symbols.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        ChoiceDialog<String> dialog = new ChoiceDialog<>(symbolPlayerSecond, symbols);
        dialog.setTitle(this.localizationModelHandler.localize("message.editsymbol.title"));
        dialog.setHeaderText(null);
        dialog.setContentText(this.localizationModelHandler.localize("message.editsymbol.player.two.text"));

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            String selectedSymbol = result.get();
            if (!selectedSymbol.equals(symbolFuturePlayerFirst)) {
                return selectedSymbol;
            }
        }
        return symbolPlayerSecond;
    }

    public String showPopUpEditSymbolPlayerFirst(String symbolPlayerFirst, String symbolFuturePlayerSecond) {
        // Carica la lista di simboli da un file delle risorse
        List<String> symbols = new ArrayList<>();
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("symbols.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                symbols.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        ChoiceDialog<String> dialog = new ChoiceDialog<>(symbolPlayerFirst, symbols);
        dialog.setTitle(this.localizationModelHandler.localize("message.editsymbol.title"));
        dialog.setHeaderText(null);
        dialog.setContentText(this.localizationModelHandler.localize("message.editsymbol.player.one.text"));

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            String selectedSymbol = result.get();
            if (!selectedSymbol.equals(symbolFuturePlayerSecond)) {
                return selectedSymbol;
            }
        }

        return symbolPlayerFirst;
    }

    public String showPopUpEditColor(String color) {
        // Creazione del ColorPicker
        ColorPicker colorPicker = new ColorPicker();
        colorPicker.setValue(Color.valueOf(color));

        // Creazione del layout
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(colorPicker);

        // Creazione del contenuto personalizzato per l'Alert
        VBox content = new VBox(borderPane);

        // Creazione dell'Alert
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.initOwner(this.stage);
        alert.setTitle(this.localizationModelHandler.localize("ui.menu.edit.preferences.colors"));
        alert.setHeaderText(null);
        alert.getDialogPane().setContent(content);

        // Creazione dei bottoni di conferma e annulla
        ButtonType confirmButton = new ButtonType(this.localizationModelHandler.localize("message.quit.confirm"), ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButton = new ButtonType(this.localizationModelHandler.localize("message.quit.cancel"), ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(confirmButton, cancelButton);

        // Mostra l'Alert e gestisci la risposta
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == confirmButton) {
            // Ottieni il colore selezionato
            Color selectedColor = colorPicker.getValue();
            return selectedColor.toString();
        } else {
            return color; // Ritorna il suo colore normale se viene premuto il pulsante "Annulla" o se la finestra viene chiusa
        }
    }
}
