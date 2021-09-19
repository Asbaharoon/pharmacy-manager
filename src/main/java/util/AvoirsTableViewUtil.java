package util;

import entity.Avoir;
import entity.Avoir;
import entity.Vente;
import entity.VenteProduit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.AvoirDAO;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AvoirsTableViewUtil {
    public static void fillTableColumns(TableView<Avoir> tableView) {
        tableView.getColumns().addAll(getIdColumn(),
                getDateHeureColumn(),
                getClientColumn(),
                getProduitColumn(),
                getMotifColumn(),
                getQteRendueColumn());
    }

    public static void fillTableData(TableView<Avoir> tableView, AvoirDAO dao) {
        tableView.getItems().setAll(getAvoirsList(dao));

    }

    public static ObservableList<Avoir> getAvoirsList(AvoirDAO dao) {
        return FXCollections.observableArrayList(dao.getAll());
    }

    public static TableColumn<Avoir, Integer> getIdColumn() {
        TableColumn<Avoir, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        idCol.setPrefWidth(50);
        idCol.setStyle( "-fx-alignment: CENTER;");
        return idCol;
    }

    public static TableColumn<Avoir, LocalDateTime> getDateHeureColumn() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        TableColumn<Avoir, LocalDateTime> dateHeureCol = new TableColumn<>("Date et heure");
        dateHeureCol.setCellValueFactory(new PropertyValueFactory<>("dateHeuere"));
        dateHeureCol.setCellFactory(col -> new TableCell<Avoir, LocalDateTime>() {
            @Override
            protected void updateItem(LocalDateTime dateTime, boolean empty) {
                super.updateItem(dateTime, empty);
                if (empty) {
                    setText(null);
                } else {
                    LocalDateTime localDate = LocalDateTime.parse(dateTime.toString(), formatter);
                    setText(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(localDate));
                }
            }
        });
        return dateHeureCol;
    }

    public static TableColumn<Avoir, VenteProduit> getClientColumn() {
        TableColumn<Avoir, VenteProduit> clientCol = new TableColumn<>("Client");
        clientCol.setCellValueFactory(new PropertyValueFactory<>("venteProduit"));
        clientCol.setCellFactory(col -> new TableCell<Avoir, VenteProduit>() {
            @Override
            protected void updateItem(VenteProduit item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(item.getVente().getClient().toString());
                }
            }
        });
        return clientCol;
    }

    public static TableColumn<Avoir, VenteProduit> getProduitColumn() {
        TableColumn<Avoir, VenteProduit> produitCol = new TableColumn<>("Produit");
        produitCol.setCellValueFactory(new PropertyValueFactory<>("venteProduit"));
        produitCol.setCellFactory(col -> new TableCell<Avoir, VenteProduit>() {
            @Override
            protected void updateItem(VenteProduit item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(item.getProduit().getDesignation());
                }
            }
        });
        return produitCol;

    }
    public static TableColumn<Avoir, String> getMotifColumn() {
        TableColumn<Avoir, String> designationCol = new TableColumn<>("Motif");
        designationCol.setCellValueFactory(new PropertyValueFactory<>("motif"));
        designationCol.setPrefWidth(270);
        return designationCol;
    }

    public static TableColumn<Avoir, Integer> getQteRendueColumn() {
        TableColumn<Avoir, Integer> qteStockCol = new TableColumn<>("Quantité rendue");
        qteStockCol.setCellValueFactory(new PropertyValueFactory<>("qteRendue"));
        return qteStockCol;
    }
    

}