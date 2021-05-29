package Sewing;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class adminloginController {
    private final String admin_password = "password";
    private final String admin_name = "admin";

    @FXML private TextField adminname;
    @FXML private TextField adminpassword;
    @FXML private TextField admin_login_update;

    @FXML
    void admin_login_pressed(ActionEvent event) {
        if (adminname.getText().equals(admin_name) && adminpassword.getText().equals(admin_password))
        {
            try{
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("admin_main_page.fxml"));
                Parent root2 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root2));
                admin_main_pageController admin = fxmlLoader.getController();
                admin.setText();
                stage.show();
            }
            catch (Exception e){
                System.err.println(e.getMessage());
            }
        }
        else {
            admin_login_update.setText("Wrong password or login!");

        }
    }

}
