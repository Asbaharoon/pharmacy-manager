package util;

import entity.Agent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.AgentDAO;


public class AgentsTableViewUtil {

    public static void fillTableColumns(TableView<Agent> tableView) {
        tableView.getColumns().addAll(AgentsTableViewUtil.getMatriculeColumn(),
                AgentsTableViewUtil.getCinColumn(),
                AgentsTableViewUtil.getNomColumn(),
                AgentsTableViewUtil.getPrenomColumn()
        );

    }

    public static void fillTableData(TableView<Agent> tableView) {
        tableView.getItems().setAll(AgentsTableViewUtil.getAgentsList());
    }

    public static ObservableList<Agent> getAgentsList() {
        return FXCollections.observableList(AgentDAO.getAll());
    }

    public static TableColumn<Agent, Integer> getMatriculeColumn() {
        TableColumn<Agent, Integer> matriculeCol = new TableColumn<>("MATRICULE");
        matriculeCol.setCellValueFactory(new PropertyValueFactory<>("matricule"));
        return matriculeCol;
    }

    public static TableColumn<Agent, String> getCinColumn() {
        TableColumn<Agent, String> cinCol = new TableColumn<>("CIN");
        cinCol.setCellValueFactory(new PropertyValueFactory<>("CIN"));
        return cinCol;
    }

    public static TableColumn<Agent, String> getNomColumn() {
        TableColumn<Agent, String> nomCol = new TableColumn<>("NOM");
        nomCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
        return nomCol;
    }

    public static TableColumn<Agent, String> getPrenomColumn() {
        TableColumn<Agent, String> prenomCol = new TableColumn<>("PRENOM");
        prenomCol.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        return prenomCol;
    }

}
