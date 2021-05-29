package Sewing;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.Scanner;

public class customerloginController implements Initializable {
    @FXML private Button signup;
    @FXML private TextField username;
    @FXML private TextField userpassword;
    @FXML private Button login;
    @FXML private TextField customerLoginUpdate;

    @FXML
    void signuppressed(ActionEvent event) {
        System.out.println("hello");
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("signupcustomer.fxml"));
            Parent root2 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root2));
            stage.show();
        }
        catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    @FXML
    void logincustomer(ActionEvent event) {

        String userPassword = userpassword.getText();
        String userName = username.getText();

        //System.out.println(userName+"    "+userPassword);
        try {
            File myObj = new File("clients.txt");
            Scanner myReader = new Scanner(myObj);
            boolean found = false;
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                //System.out.println(data);
                //System.out.println("string printed!");
                String[] dataArray = data.split(" ");
                //System.out.println(Arrays.toString(dataArray));
                //System.out.println("Array printed!");
                //System.out.println(dataArray[2]+"   "+dataArray[3]);
                if(dataArray[2].equals(userName) && dataArray[3].equals(userPassword))
                {
                    System.out.println("Matched");
                    login(dataArray);
                    found = true;
                    break;
                }
            }
            if (!found)
            {
                customerLoginUpdate.setText("Wrong password or login!");
            }


            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

    public void login(String[] dataArray)
    {
        try{

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("customerpage.fxml"));
            Parent root2 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root2));
            customerpageController cutomer = fxmlLoader.getController();
            cutomer.setText(dataArray[0]+" "+dataArray[1],dataArray[4]);
            stage.show();
        }
        catch (Exception e){
            System.err.println(e.getMessage());
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
