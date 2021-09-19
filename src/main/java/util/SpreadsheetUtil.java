package util;

import com.gembox.spreadsheet.*;
import com.gembox.spreadsheet.tables.Table;
import entity.Client;
import entity.Fournisseur;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.FileChooser;
import model.FournisseurDAO;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class SpreadsheetUtil {
    static {
        SpreadsheetInfo.setLicense("FREE-LIMITED-KEY");
    }

    public static void load(ActionEvent event, TableView table) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open file");
        File file = fileChooser.showOpenDialog(table.getScene().getWindow());

        ExcelFile workbook = ExcelFile.load(file.getAbsolutePath());
        ExcelWorksheet worksheet = workbook.getWorksheet(0);
        String[][] sourceData = new String[100][26];
        for (int row = 0; row < sourceData.length; row++) {
            for (int column = 0; column < sourceData[row].length; column++) {
                ExcelCell cell = worksheet.getCell(row, column);
                if (cell.getValueType() != CellValueType.NULL)
                    sourceData[row][column] = cell.getValue().toString();
            }
        }
        fillTable(sourceData, table);
    }

    private static void fillTable(String[][] dataSource, TableView table) {
        table.getColumns().clear();
        ObservableList<ObservableList<String>> data = FXCollections.observableArrayList();
        for (String[] row : dataSource)
            data.add(FXCollections.observableArrayList(row));
        table.setItems(data);
        ArrayList<TableColumn<ObservableList<String>, String>> cols = new ArrayList<>();
        cols.add(new TableColumn<>("ID"));
        cols.add(new TableColumn<>("Nom"));
        cols.add(new TableColumn<>("Prenom"));
        cols.add(new TableColumn<>("Telephone"));
        for (int i = 0; i < 4; i++) {
            final int currentColumn = i;
            cols.get(i).setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().get(currentColumn)));
            cols.get(i).setEditable(true);
            cols.get(i).setCellFactory(TextFieldTableCell.forTableColumn());
            cols.get(i).setOnEditCommit(
                    (TableColumn.CellEditEvent<ObservableList<String>, String> t) -> {
                        t.getTableView().getItems().get(t.getTablePosition().getRow()).set(t.getTablePosition().getColumn(), t.getNewValue());
                    });
            table.getColumns().add(cols.get(i));
        }
    }

    public static void saveF(ActionEvent event, TableView<Fournisseur> table) throws IOException {
        ExcelFile file = new ExcelFile();
        ExcelWorksheet worksheet = file.addWorksheet("sheet");
        worksheet.getCell(0, 0).setValue("ID");
        worksheet.getCell(0, 1).setValue("Nom");
        worksheet.getCell(0, 2).setValue("Prenom");
        worksheet.getCell(0, 3).setValue("Telephone");
        for (int row = 1; row <= table.getItems().size(); row++) {
            ObservableList<Fournisseur> cell = FXCollections.observableArrayList(table.getItems().get(row - 1));
            worksheet.getCell(row, 0).setValue(cell.get(0).getId());
            worksheet.getCell(row, 1).setValue(cell.get(0).getNom());
            worksheet.getCell(row, 2).setValue(cell.get(0).getPrenom());
            worksheet.getCell(row, 3).setValue(cell.get(0).getTelePrt());
        }

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("XLSX files (*.xlsx)", "*.xlsx"),
                new FileChooser.ExtensionFilter("XLS files (*.xls)", "*.xls"),
                new FileChooser.ExtensionFilter("ODS files (*.ods)", "*.ods"),
                new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv"),
                new FileChooser.ExtensionFilter("HTML files (*.html)", "*.html")
        );
        File saveFile = fileChooser.showSaveDialog(table.getScene().getWindow());

        file.save(saveFile.getAbsolutePath());
    }

    public static void saveC(ActionEvent event, TableView<Client> table) throws IOException {
        ExcelFile file = new ExcelFile();
        ExcelWorksheet worksheet = file.addWorksheet("sheet");
        worksheet.getCell(0, 0).setValue("ID");
        worksheet.getCell(0, 1).setValue("Nom");
        worksheet.getCell(0, 2).setValue("Prenom");
        worksheet.getCell(0, 3).setValue("Telephone");
        for (int row = 1; row <= table.getItems().size(); row++) {
            ObservableList<Client> cell = FXCollections.observableArrayList(table.getItems().get(row - 1));
            worksheet.getCell(row, 0).setValue(cell.get(0).getId());
            worksheet.getCell(row, 1).setValue(cell.get(0).getNom());
            worksheet.getCell(row, 2).setValue(cell.get(0).getPrenom());
            worksheet.getCell(row, 3).setValue(cell.get(0).getTelePrt());
        }

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("XLSX files (*.xlsx)", "*.xlsx"),
                new FileChooser.ExtensionFilter("XLS files (*.xls)", "*.xls"),
                new FileChooser.ExtensionFilter("ODS files (*.ods)", "*.ods"),
                new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv"),
                new FileChooser.ExtensionFilter("HTML files (*.html)", "*.html")
        );
        File saveFile = fileChooser.showSaveDialog(table.getScene().getWindow());

        file.save(saveFile.getAbsolutePath());
    }
}