package Sewing;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.PrintWriter;

public class SewingMainController {



    @FXML private Button login_as_customer_button;
    @FXML private Button login_as_admin_button;




    @FXML
    void loginAdminPressed(ActionEvent event) throws Exception {

        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("adminlogin.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        }
        catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    @FXML
    void loginCustomerPressed(ActionEvent event) {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("customerlogin.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        }
        catch (Exception e){
            System.err.println(e.getMessage());
        }
    }








}
