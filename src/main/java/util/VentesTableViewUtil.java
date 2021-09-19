package util;

import entity.Commande;
import entity.Vente;
import entity.Client;
import entity.Utilisateur;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.VenteDAO;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class VentesTableViewUtil {
    public static void fillTableColumns(TableView<Vente> tableView) {
        tableView.getColumns().addAll(getIdColumn(),
                getClientColumn(),
                getDateHeureColumn(),
                getEtatPaimentColumn(),
                getPrixTotalColumn(),
                getRestePaimentColumn()
        );
    }

    public static void filterTableData(TableView<Vente> tableView, List<Vente> ventesList) {
        tableView.getItems().setAll(FXCollections.observableArrayList(ventesList));
    }

    public static void fillTableData(TableView<Vente> tableView, VenteDAO dao) {
        tableView.getItems().setAll(getVentesList(dao));
    }

    public static ObservableList<Vente> getVentesList(VenteDAO dao) {
        return FXCollections.observableArrayList(dao.getAll());
    }

    public static TableColumn<Vente, Integer> getIdColumn() {
        TableColumn<Vente, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        idCol.setPrefWidth(50);
        idCol.setMinWidth(50);
        idCol.setStyle("-fx-alignment: CENTER;");
        return idCol;
    }

    public static TableColumn<Vente, Client> getClientColumn() {
        TableColumn<Vente, Client> clientCol = new TableColumn<>("Client");
        clientCol.setCellValueFactory(new PropertyValueFactory<>("client"));
        clientCol.setCellFactory(col -> new TableCell<Vente, Client>() {
            @Override
            protected void updateItem(Client client, boolean empty) {
                if (client != null) {
                    super.updateItem(client, empty);
                    if (empty) {
                        setText(null);
                    } else {
                        setText(client.getNom() + " " + client.getPrenom());
                    }
                } else {
                    setText(null);
                }
            }
        });
        return clientCol;
    }

    public static TableColumn<Vente, BigDecimal> getPrixTotalColumn() {
        TableColumn<Vente, BigDecimal> prixTotalCol = new TableColumn<>("Prix total");
        prixTotalCol.setCellValueFactory(new PropertyValueFactory<>("prixTotal"));
        prixTotalCol.setCellFactory(col -> new TableCell<Vente, BigDecimal>() {
            @Override
            protected void updateItem(BigDecimal prixTotal, boolean empty) {
                super.updateItem(prixTotal, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(prixTotal.toString() + " DH");
                }
            }
        });
        return prixTotalCol;
    }


    public static TableColumn<Vente, Byte> getEtatPaimentColumn() {
        TableColumn<Vente, Byte> etatCol = new TableColumn<>("Etat de paiement");
        etatCol.setCellValueFactory(new PropertyValueFactory<>("etatPaiement"));
        etatCol.setCellFactory(col -> new TableCell<Vente, Byte>() {
            @Override
            protected void updateItem(Byte etat, boolean empty) {
                super.updateItem(etat, empty);
                if (empty) {
                    setText(null);
                } else {
                    if (etat == 0) {
                        setText("Payé");
                    } else {
                        setText("Non payé");
                    }
                }
            }
        });
        return etatCol;
    }

    public static TableColumn<Vente, BigDecimal> getRestePaimentColumn() {
        TableColumn<Vente, BigDecimal> restePaimentColumn = new TableColumn<>("Reste");
        restePaimentColumn.setCellValueFactory(new PropertyValueFactory<>("restePaiment"));
        restePaimentColumn.setCellFactory(col -> new TableCell<Vente, BigDecimal>() {
            @Override
            protected void updateItem(BigDecimal reste, boolean empty) {
                if (reste != null) {
                    super.updateItem(reste, empty);
                    if (empty) {
                        setText(null);
                    } else {
                        setText(reste.toString() + " DH");
                    }
                }
            }
        });
        return restePaimentColumn;
    }

    public static TableColumn<Vente, LocalDateTime> getDateHeureColumn() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        TableColumn<Vente, LocalDateTime> dateHeureCol = new TableColumn<>("Date et heure");
        dateHeureCol.setCellValueFactory(new PropertyValueFactory<>("dateHeuere"));
        dateHeureCol.setCellFactory(col -> new TableCell<Vente, LocalDateTime>() {
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

    public static TableColumn<Vente, Utilisateur> getUserColumn() {
        TableColumn<Vente, Utilisateur> userCol = new TableColumn<>("Utilisateur");
        userCol.setCellValueFactory(new PropertyValueFactory<>("utilisateur"));
        userCol.setCellFactory(col -> new TableCell<Vente, Utilisateur>() {
            @Override
            protected void updateItem(Utilisateur user, boolean empty) {
                super.updateItem(user, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(user.getNom() + " " + user.getPrenom());
                }
            }
        });
        return userCol;
    }
}



