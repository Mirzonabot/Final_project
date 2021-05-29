package Sewing;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.Scanner;

public class admin_main_pageController implements Initializable {

    @FXML private TextField zip;
    @FXML private TextField fabric;
    @FXML private TextField thread;
    @FXML private TextField button;
    @FXML private TextField zipPrice;
    @FXML private TextField fabricPrice;
    @FXML private TextField threadPrice;
    @FXML private TextField buttonPrice;

    @FXML
    void Update_material_list(ActionEvent event) {
        try {
            FileWriter myWriter = new FileWriter("material.txt");
            myWriter.write("Zip " + zip.getText() + " units " + zipPrice.getText()+ "\n");
            myWriter.write("Fabric " + fabric.getText() + " meters " + fabricPrice.getText() + "\n");
            myWriter.write("Thread " + thread.getText() + " meters " + threadPrice.getText()+"\n");
            myWriter.write("Button " + button.getText() + " units " + buttonPrice.getText()+"\n");
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void setText() {
        try {
            File myObj = new File("material.txt");
            Scanner myReader = new Scanner(myObj);
            int num = 1;
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                //System.out.println(data);
                //System.out.println("string printed!");
                String[] dataArray = data.split(" ");
                System.out.println(Arrays.toString(dataArray));
                if(num == 1)
                {
                    zip.setText(dataArray[1]);
                    zipPrice.setText(dataArray[3]);

                }
                else if(num == 2)
                {
                    fabric.setText(dataArray[1]);
                    fabricPrice.setText(dataArray[3]);
                }
                else if(num == 3)
                {
                    thread.setText(dataArray[1]);
                    threadPrice.setText(dataArray[3]);
                }
                else if(num == 4)
                {
                    button.setText(dataArray[1]);
                    buttonPrice.setText(dataArray[3]);
                }
                num++;

            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
