package controller;

import entity.Avoir;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import model.AvoirDAO;
import util.AvoirsTableViewUtil;

import java.net.URL;
import java.util.ResourceBundle;

public class AvoirsController implements Initializable {
    public TableView<Avoir> avoirsTableView;
    private AvoirDAO avoirDAO;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        avoirDAO = new AvoirDAO();
        AvoirsTableViewUtil.fillTableColumns(avoirsTableView);
        AvoirsTableViewUtil.fillTableData(avoirsTableView, avoirDAO);
    }
}
