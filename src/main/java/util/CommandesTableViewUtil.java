package util;

import entity.Commande;
import entity.Fournisseur;
import entity.Utilisateur;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.CommandeDAO;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

public class CommandesTableViewUtil {
    public static void fillTableColumns(TableView<Commande> tableView) {
        tableView.getColumns().addAll(getIdColumn(),
                getFournisseurColumn(),
                getDateHeureColumn(),
                getEtatPaimentColumn(),
                getPrixTotalColumn(),
                getRestePaimentColumn()
        );
    }

    public static void filterTableData(TableView<Commande> tableView, List<Commande> cmdsList) {
        tableView.getItems().setAll(FXCollections.observableArrayList(cmdsList));
    }

    public static void fillTableData(TableView<Commande> tableView, CommandeDAO dao) {
        tableView.getItems().setAll(getCmdsList(dao));
    }

    public static ObservableList<Commande> getCmdsList(CommandeDAO dao) {
        return FXCollections.observableArrayList(dao.getAll());
    }

    public static TableColumn<Commande, Integer> getIdColumn() {
        TableColumn<Commande, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        idCol.setPrefWidth(50);
        idCol.setMinWidth(50);
        idCol.setStyle("-fx-alignment: CENTER;");
        return idCol;
    }

    public static TableColumn<Commande, Fournisseur> getFournisseurColumn() {
        TableColumn<Commande, Fournisseur> fournisseurCol = new TableColumn<>("Fournisseur");
        fournisseurCol.setCellValueFactory(new PropertyValueFactory<>("fournisseur"));
        fournisseurCol.setCellFactory(col -> new TableCell<Commande, Fournisseur>() {
            @Override
            protected void updateItem(Fournisseur fournisseur, boolean empty) {
                if (fournisseur != null) {
                    super.updateItem(fournisseur, empty);
                    if (empty) {
                        setText(null);
                    } else {
                        setText(fournisseur.getNom() + " " + fournisseur.getPrenom());
                    }
                } else {
                    setText(null);
                }
            }
        });
        return fournisseurCol;
    }

    public static TableColumn<Commande, BigDecimal> getPrixTotalColumn() {
        TableColumn<Commande, BigDecimal> prixTotalCol = new TableColumn<>("Prix total");
        prixTotalCol.setCellValueFactory(new PropertyValueFactory<>("prixTotal"));
        prixTotalCol.setCellFactory(col -> new TableCell<Commande, BigDecimal>() {
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


    public static TableColumn<Commande, Byte> getEtatPaimentColumn() {
        TableColumn<Commande, Byte> etatCol = new TableColumn<>("Etat de paiment");
        etatCol.setCellValueFactory(new PropertyValueFactory<>("etatPaiment"));
        etatCol.setCellFactory(col -> new TableCell<Commande, Byte>() {
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

    public static TableColumn<Commande, BigDecimal> getRestePaimentColumn() {
        TableColumn<Commande, BigDecimal> restePaimentColumn = new TableColumn<>("Reste");
        restePaimentColumn.setCellValueFactory(new PropertyValueFactory<>("restePaiement"));
        restePaimentColumn.setCellFactory(col -> new TableCell<Commande, BigDecimal>() {
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

    public static TableColumn<Commande, LocalDateTime> getDateHeureColumn() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        TableColumn<Commande, LocalDateTime> dateHeureCol = new TableColumn<>("Date et heure");
        dateHeureCol.setCellValueFactory(new PropertyValueFactory<>("dateHeuere"));
        dateHeureCol.setCellFactory(col -> new TableCell<Commande, LocalDateTime>() {
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

    public static TableColumn<Commande, Utilisateur> getUserColumn() {
        TableColumn<Commande, Utilisateur> userCol = new TableColumn<>("Utilisateur");
        userCol.setCellValueFactory(new PropertyValueFactory<>("utilisateur"));
        userCol.setCellFactory(col -> new TableCell<Commande, Utilisateur>() {
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


