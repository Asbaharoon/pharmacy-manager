package util;

import entity.AvocatAdmin;
import entity.Dossier;
import entity.Jugement;
import entity.Tribunal;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.DossierDAO;
import net.bytebuddy.asm.Advice;

import java.time.LocalDate;

public class DossiersByAgentTableViewUtil {

    public static void fillTableColumns(TableView<Dossier> tableView) {
        tableView.getColumns().addAll(getIdColumn(),
                getIdJugementColumn(),
                getSituationColumn(),
                getDateColumn(),
                getJugementColumn(),
                getTribunalColumn(),
                getAvocatAdminColumn()
        );
    }

    public static void fillTableData(TableView<Dossier> tableView, long id, String objet) {
        tableView.getItems().setAll(getDossiersByAgentAndObjetList(id, objet));
    }

    public static ObservableList<Dossier> getDossiersByAgentAndObjetList(long id, String objet) {
        return FXCollections.observableList(new DossierDAO().getAllByAgentAndObjet(id, objet));
    }

    public static TableColumn<Dossier, Integer> getIdColumn() {
        TableColumn<Dossier, Integer> idCol = new TableColumn<>("N° DOSSIER");
        idCol.setCellValueFactory(new PropertyValueFactory<>("ID"));
        return idCol;
    }

    public static TableColumn<Dossier, AvocatAdmin> getAvocatAdminColumn() {
        TableColumn<Dossier, AvocatAdmin> avocatAdminCol = new TableColumn<>("AVOCAT D'ADMINISTRATION");
        avocatAdminCol.setCellValueFactory(new PropertyValueFactory<>("avocatAdmin"));
        avocatAdminCol.setCellFactory(col -> new TableCell<Dossier, AvocatAdmin>() {
            @Override
            protected void updateItem(AvocatAdmin avocatAdmin, boolean empty) {
                super.updateItem(avocatAdmin, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(avocatAdmin.getNom() + " " + avocatAdmin.getPrenom());
                }
        }});
        return avocatAdminCol;
    }

    public static TableColumn<Dossier, Tribunal> getTribunalColumn() {
        TableColumn<Dossier, Tribunal> tribunalCol = new TableColumn<>("TRIBUNAL");
        tribunalCol.setCellValueFactory(new PropertyValueFactory<>("tribunal"));
        tribunalCol.setCellFactory(col -> new TableCell<Dossier, Tribunal>() {
            @Override
            protected void updateItem(Tribunal tribunal, boolean empty) {
                super.updateItem(tribunal, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(tribunal.getNom());
                }
            }});
        return  tribunalCol;
    }

    public static TableColumn<Dossier, Jugement> getDateColumn() {
        TableColumn<Dossier, Jugement> dateCol = new TableColumn<>("DATE");
        dateCol.setCellValueFactory(new PropertyValueFactory<>("jugement"));
        dateCol.setCellFactory(col -> new TableCell<Dossier, Jugement>() {
            @Override
            protected void updateItem(Jugement jugement, boolean empty) {
                if (jugement != null) {
                    super.updateItem(jugement, empty);
                    if (empty) {
                        setText(null);
                    } else {
                        setText(DateUtil.localDateToArabicFormat(jugement.getDate()));
                    }
                } else {
                    setText(" - ");
                }
            }});
        return dateCol;
    }

    public static TableColumn<Dossier, Jugement> getIdJugementColumn() {
        TableColumn<Dossier, Jugement> idJugementCol = new TableColumn<>("ID JUGEMENT");
        idJugementCol.setCellValueFactory(new PropertyValueFactory<>("jugement"));
        idJugementCol.setCellFactory(col -> new TableCell<Dossier, Jugement>() {
            @Override
            protected void updateItem(Jugement jugement, boolean empty) {
                super.updateItem(jugement, empty);
                if (jugement != null) {
                    if (empty) {
                        setText(null);
                    } else {
                        setText(String.valueOf(jugement.getId()));
                    }
                } else {
                    setText(" - ");
                }
            }});
        return idJugementCol;
    }

    public static TableColumn<Dossier, Jugement> getJugementColumn() {
        TableColumn<Dossier, Jugement> jugementCol = new TableColumn<>("JUGEMENT");
        jugementCol.setCellValueFactory(new PropertyValueFactory<>("jugement"));
        jugementCol.setCellFactory(col -> new TableCell<Dossier, Jugement>() {
            @Override
            protected void updateItem(Jugement jugement, boolean empty) {
                super.updateItem(jugement, empty);
                if (jugement != null) {
                    if (empty) {
                        setText(null);
                    } else {
                        setText(jugement.getJugement());
                    }
                } else {
                    setText(" - ");
                }
            }});
        return jugementCol;
    }

    public static TableColumn<Dossier, Jugement> getSituationColumn() {
        TableColumn<Dossier, Jugement> situationCol = new TableColumn<>("SITUATION");
        situationCol.setCellValueFactory(new PropertyValueFactory<>("jugement"));
        situationCol.setCellFactory(col -> new TableCell<Dossier, Jugement>() {
            @Override
            protected void updateItem(Jugement jugement, boolean empty) {
                super.updateItem(jugement, empty);
                if (jugement != null) {
                    if (empty) {
                        setText(null);
                    } else {
                        setText(jugement.getSituation());
                    }
                } else {
                    setText(" - ");
                }
            }});
        return situationCol;
    }
}
