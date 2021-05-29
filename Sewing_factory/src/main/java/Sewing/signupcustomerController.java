package Sewing;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class signupcustomerController {
    @FXML private TextField firstnamesignup;
    @FXML private TextField lastnamesignup;
    @FXML private TextField usernamesignup;
    @FXML private TextField passwordsignup;
    @FXML private TextField initialbalancenamesignup;
    @FXML private TextField signup_update;
    @FXML private Button addnewcustomerid;

    @FXML
    void addnewcustomer(ActionEvent event) {
        System.out.println("hello!!!");
        String f_name = firstnamesignup.getText();
        String s_name = lastnamesignup.getText();
        String u_name = usernamesignup.getText();
        String u_pass = passwordsignup.getText();
        String in_balance = initialbalancenamesignup.getText();

        System.out.println(f_name + " " + s_name + " " + u_name + " " + u_pass + " " + in_balance);

        try {
            File myObj = new File("unapproved_clients.txt");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        if (f_name.length() > 0 && s_name.length() > 0 && u_name.length() > 0 && u_pass.length() > 0 && in_balance.length() > 0 && newClient(f_name,u_name)) {
            try {
                FileWriter myWriter = new FileWriter("clients.txt",true);
                myWriter.write(f_name + " " + s_name + " " + u_name + " " + u_pass + " " + in_balance + "\n");
                myWriter.close();
                //System.out.println("Successfully wrote to the file.");
                signup_update.setText("Client is added!");
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
        else {
            signup_update.setText("Wrong credentials!");
        }
    }

    private boolean newClient(String f_name, String u_name) {
        boolean neww = true;
        try {
            File myObj = new File("clients.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] dataArray = data.split(" ");
                if (dataArray[0].equals(f_name) || dataArray[2].equals(u_name))
                {
                    neww = false;
                    break;
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return neww;
    }

}