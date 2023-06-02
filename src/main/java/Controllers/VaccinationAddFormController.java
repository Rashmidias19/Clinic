package Controllers;

import com.jfoenix.controls.JFXComboBox;
import dao.CrudDAO;
import dao.VaccinationDAO;
import dao.impl.PetDAOImpl;
import dao.impl.UserDAOImpl;
import dao.impl.VaccinationDAOImpl;
import dto.Pet;
import dto.User;
import dto.VaccinationSchedule;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;


public class VaccinationAddFormController implements Initializable {
    public AnchorPane dashboardPane;

    @FXML
    private Label lblID;

    @FXML
    private JFXComboBox cmbPet_ID;

    @FXML
    private Label lblCustomer_ID;

    @FXML
    private DatePicker date;

    @FXML
    private TextField time;

    @FXML
    private TextField textDescription;

    @FXML
    private Label lblContact;
    CrudDAO<VaccinationSchedule,String, FileInputStream, File> vaccinationDAO =new VaccinationDAOImpl();
    CrudDAO<Pet,String, FileInputStream, File> petDAO =new PetDAOImpl();



    public void petbtnOnAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) dashboardPane.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/PetManagementForm.fxml"))));
        stage.setTitle("VETCLOUD");
        stage.centerOnScreen();
        stage.show();
    }

    public void customerbtnOnAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) dashboardPane.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/CustomerManagementForm.fxml"))));
        stage.setTitle("VETCLOUD");
        stage.centerOnScreen();
        stage.show();
    }

    public void usersbtnOnAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) dashboardPane.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/UserManagementForm.fxml"))));
        stage.setTitle("VETCLOUD");
        stage.centerOnScreen();
        stage.show();
    }

    public void employeebtnOnAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) dashboardPane.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/EmployeeManagementForm.fxml"))));
        stage.setTitle("VETCLOUD");
        stage.centerOnScreen();
        stage.show();
    }

    public void suppliesbtnOnAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) dashboardPane.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/SupplieManagementForm.fxml"))));
        stage.setTitle("VETCLOUD");
        stage.centerOnScreen();
        stage.show();
    }

    public void billingbtnOnAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) dashboardPane.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/BillingManagementForm.fxml"))));
        stage.setTitle("VETCLOUD");
        stage.centerOnScreen();
        stage.show();
    }

    public void inhousebtnOnAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) dashboardPane.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/InhouseManagementForm.fxml"))));
        stage.setTitle("VETCLOUD");
        stage.centerOnScreen();
        stage.show();
    }

    public void logoutbtnOnAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) dashboardPane.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/LoginForm.fxml"))));
        stage.setTitle("VETCLOUD");
        stage.centerOnScreen();
        stage.show();
    }

    @Override
    public void initialize(java.net.URL url, ResourceBundle resourceBundle) {
        generateNextVaccId();
        loadPet_ID();
    }

    private void loadPet_ID() {
        try {
            ObservableList<String> obList = FXCollections.observableArrayList();
            List<String> codes = petDAO.loadID();

            for (String code : codes) {
                obList.add(code);
            }
            cmbPet_ID.setItems(obList);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }
    }

    public void cmbIDOnAction(ActionEvent actionEvent) throws IOException {
        String ID = (String) cmbPet_ID.getValue();
        try {
            Pet pet = petDAO.searchById(ID);
            FillPetFields(pet);

            // txtQty.requestFocus();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }
    }

    private void FillPetFields(Pet pet) {
        lblCustomer_ID.setText(String.valueOf(pet.getCustomerID()));
        lblContact.setText(String.valueOf(pet.getContact()));
    }

    private void generateNextVaccId() {
        try {
            String id = vaccinationDAO.getNextId();
            lblID.setText(id);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }
    }


    public void savebtnOnAction(ActionEvent actionEvent) throws SQLException, IOException {
        String Vaccination_ID = lblID.getText();
        String Pet_ID = (String) cmbPet_ID.getValue();
        String Customer_ID = lblCustomer_ID.getText();
        String Date = String.valueOf(date.getValue());
        String Time = time.getText();
        String Description = textDescription.getText();
        String Contact = lblContact.getText();

        try {
            boolean isSaved = vaccinationDAO.save(new VaccinationSchedule(Vaccination_ID,Pet_ID,Customer_ID,Date,Time,Description,Contact));
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Operation saved!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, "something went wrong!").show();
        }
        Stage stage = (Stage) dashboardPane.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/VaccinationAddForm.fxml"))));
        stage.setTitle("VETCLOUD");
        stage.centerOnScreen();
        stage.show();
    }

    public void backbtnOnAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) dashboardPane.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/VaccineScheduleManagementForm.fxml"))));
        stage.setTitle("VETCLOUD");
        stage.centerOnScreen();
        stage.show();
    }
}
