package Sewing;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.*;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.Scanner;

public class customerpageController implements Initializable {

    private final String[] products = {"Jeans","Shirt","T-shirt","Jackets"};
    @FXML private TextField customer_username;
    @FXML private TextField customer_balance;
    @FXML private ChoiceBox<String> productList;
    @FXML private TextField productAmount;
    @FXML private TextArea customTextArea;

    @FXML
    void order(ActionEvent event) {
        String orderedProduct = productList.getValue();
        int orderedAmount = Integer.parseInt(productAmount.getText());




        if(available(orderedProduct,orderedAmount) && computeAndUpdatePrice(orderedAmount,orderedProduct) <=Integer.parseInt(customer_balance.getText()))
        {
            int clienBalance = Integer.parseInt(customer_balance.getText()) - computeAndUpdatePrice(orderedAmount,orderedProduct);
            System.out.println("ordered!");
            update_material(orderedProduct,orderedAmount);
            customer_balance.setText(String.valueOf(clienBalance));
            customTextArea.setText("Your order is received!");
            updateClientBalance(String.valueOf(clienBalance),customer_username.getText());
        }
        else {
            System.out.println("not available!");
            showDefiteince(orderedProduct,orderedAmount);
        }

    }

    private void updateClientBalance(String balance, String name) {
        try{
            File infile =new File("clients.txt");
            File outfile =new File("copy.txt");

            FileInputStream instream = new FileInputStream(infile);
            FileOutputStream outstream = new FileOutputStream(outfile);

            byte[] buffer = new byte[1024];

            int length;
            /*copying the contents from input stream to
             * output stream using read and write methods
             */
            while ((length = instream.read(buffer)) > 0){
                outstream.write(buffer, 0, length);
            }

            //Closing the input/output file streams
            instream.close();
            outstream.close();

            System.out.println("File copied successfully!!");

        }catch(IOException ioe){
            ioe.printStackTrace();
        }


        try {
            File myObj = new File("copy.txt");
            Scanner myReader = new Scanner(myObj);
            FileWriter myWriter = new FileWriter("clients.txt");
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                //System.out.println(data);
                //System.out.println("string printed!");
                String[] dataArray = data.split(" ");
                //System.out.println(Arrays.toString(dataArray));
                System.out.println(dataArray[0]+"   "+name.split(" ")[0]+"  "+balance);
                if (dataArray[0].equals(name.split(" ")[0]))
                {

                    dataArray[4] = balance;
                    myWriter.write(dataArray[0]+" "+dataArray[1]+" "+dataArray[2]+" "+dataArray[3]+" "+dataArray[4]);
                    System.out.println(Arrays.toString(dataArray));

                }
                else {
                    myWriter.write(data);
                }
                myWriter.write("\n");

            }
            myWriter.close();
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private int computeAndUpdatePrice(int orderedAmount, String orderedProduct) {
        int button = 0;
        int fabric = 0;
        int thread = 0;
        int zip = 0;
        int zipPrice = 0;
        int fabricPrice = 0;
        int threadPrice = 0;
        int buttonPrice = 0;
        int total = 0;
        int clienBalance = Integer.parseInt(customer_balance.getText());


        try {
            File myObj = new File("expenditure.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] dataArray = data.split(" ");


                if(dataArray[0].equals(orderedProduct))
                {

                    button = orderedAmount * Integer.parseInt(dataArray[4]);
                    fabric = orderedAmount * Integer.parseInt(dataArray[2]);
                    thread = orderedAmount * Integer.parseInt(dataArray[3]);
                    zip = orderedAmount * Integer.parseInt(dataArray[1]);
                    break;
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        System.out.println(button+"  "+fabric+"  "+thread+"  "+"  "+zip);
        try {
            File myObj = new File("material.txt");
            Scanner myReader = new Scanner(myObj);
            int num = 1;
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] dataArray = data.split(" ");
                if(num == 1)
                {
                    zipPrice = Integer.parseInt(dataArray[3]);

                }
                else if(num == 2)
                {
                    fabricPrice = Integer.parseInt(dataArray[3]);

                }
                else if(num == 3)
                {
                    threadPrice = Integer.parseInt(dataArray[3]);
                }
                else if(num == 4)
                {
                    buttonPrice = Integer.parseInt(dataArray[3]);
                }
                num++;

            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        total = button*buttonPrice + fabricPrice*fabric+threadPrice*thread+zipPrice*zip;
        return total;

    }

    private void update_material(String orderedProduct, int orderedAmount) {

        int button = 0;
        int avButton = 0;
        int fabric = 0;
        int avFabric = 0;
        int thread = 0;
        int avThread = 0;
        int zip = 0;
        int avZip = 0;
        int zipPrice = 0;
        int fabricPrice = 0;
        int threadPrice = 0;
        int buttonPrice = 0;

        try {
            File myObj = new File("expenditure.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] dataArray = data.split(" ");


                if(dataArray[0].equals(orderedProduct))
                {

                    button = orderedAmount * Integer.parseInt(dataArray[4]);
                    fabric = orderedAmount * Integer.parseInt(dataArray[2]);
                    thread = orderedAmount * Integer.parseInt(dataArray[3]);
                    zip = orderedAmount * Integer.parseInt(dataArray[1]);
                    break;
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        System.out.println(button+"  "+fabric+"  "+thread+"  "+"  "+zip);
        try {
            File myObj = new File("material.txt");
            Scanner myReader = new Scanner(myObj);
            int num = 1;
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] dataArray = data.split(" ");
                if(num == 1)
                {
                    avZip = Integer.parseInt(dataArray[1]);
                    zipPrice = Integer.parseInt(dataArray[3]);

                }
                else if(num == 2)
                {
                    avFabric = Integer.parseInt(dataArray[1]);
                    fabricPrice = Integer.parseInt(dataArray[3]);

                }
                else if(num == 3)
                {
                    avThread = Integer.parseInt(dataArray[1]);
                    threadPrice = Integer.parseInt(dataArray[3]);
                }
                else if(num == 4)
                {
                    avButton = Integer.parseInt(dataArray[1]);
                    buttonPrice = Integer.parseInt(dataArray[3]);
                }
                num++;

            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        try {
            FileWriter myWriter = new FileWriter("material.txt");
            myWriter.write("Zip " + (avZip-zip) + " units " + zipPrice+ "\n");
            myWriter.write("Fabric " + (avFabric-fabric) + " meters " + fabricPrice + "\n");
            myWriter.write("Thread " + (avThread-thread) + " meters " + threadPrice+"\n");
            myWriter.write("Button " + (avButton-buttonPrice) + " units " + buttonPrice+"\n");
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

    private void showDefiteince(String orderedProduct, int orderedAmount) {

        int button = 0;
        int avButton = 0;
        int fabric = 0;
        int avFabric = 0;
        int thread = 0;
        int avThread = 0;
        int zip = 0;
        int avZip = 0;
        String toBePrinted = "You don't have enough product!\nPlease order agian!\n";

        try {
            File myObj = new File("expenditure.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] dataArray = data.split(" ");

                if(dataArray[0].equals(orderedProduct))
                {
                    button = orderedAmount * Integer.parseInt(dataArray[4]);
                    fabric = orderedAmount * Integer.parseInt(dataArray[2]);
                    thread = orderedAmount * Integer.parseInt(dataArray[3]);
                    zip = orderedAmount * Integer.parseInt(dataArray[1]);
                    break;
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        //customTextArea.setText(orderedProduct+"  "+ orderedAmount+" "+ button+"  "+ fabric +"  "+thread+"  "+zip);
        try {
            File myObj = new File("material.txt");
            Scanner myReader = new Scanner(myObj);
            int num = 1;
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] dataArray = data.split(" ");
                if(num == 1)
                {
                    avZip = Integer.parseInt(dataArray[1]);
                    if(avZip < zip)
                    {
                        toBePrinted += ("Zip: " +avZip+ " Ordered: "+zip+"\n");
                    }

                }
                else if(num == 2)
                {
                    avFabric = Integer.parseInt(dataArray[1]);
                    if(avFabric < fabric)
                    {
                        toBePrinted += ("Fabric: " +avFabric+ " Ordered: "+fabric+"\n");
                    }
                }
                else if(num == 3)
                {
                    avThread = Integer.parseInt(dataArray[1]);
                    if(avThread < thread)
                    {
                        toBePrinted += ("Thread: " +avThread+ " Ordered: "+thread+"\n");
                    }
                }
                else if(num == 4)
                {
                    avButton = Integer.parseInt(dataArray[1]);
                    if(avButton < button)
                    {
                        toBePrinted += ("Button: " +avButton+ " Ordered: "+button+"\n");
                    }
                }
                num++;

            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        customTextArea.setText(toBePrinted);

    }



    private boolean available(String orderedProduct, int orderedAmount) {
        int button = 0;
        int avButton = 0;
        int fabric = 0;
        int avFabric = 0;
        int thread = 0;
        int avThread = 0;
        int zip = 0;
        int avZip = 0;

        try {
            File myObj = new File("expenditure.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] dataArray = data.split(" ");


                if(dataArray[0].equals(orderedProduct))
                {

                    button = orderedAmount * Integer.parseInt(dataArray[4]);
                    fabric = orderedAmount * Integer.parseInt(dataArray[2]);
                    thread = orderedAmount * Integer.parseInt(dataArray[3]);
                    zip = orderedAmount * Integer.parseInt(dataArray[1]);
                    break;
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        System.out.println(button+"  "+fabric+"  "+thread+"  "+"  "+zip);
        try {
            File myObj = new File("material.txt");
            Scanner myReader = new Scanner(myObj);
            int num = 1;
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] dataArray = data.split(" ");
                if(num == 1)
                {
                    avZip = Integer.parseInt(dataArray[1]);

                }
                else if(num == 2)
                {
                    avFabric = Integer.parseInt(dataArray[1]);
                }
                else if(num == 3)
                {
                    avThread = Integer.parseInt(dataArray[1]);
                }
                else if(num == 4)
                {
                    avButton = Integer.parseInt(dataArray[1]);
                }
                num++;

            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }


        return button <= avButton && fabric <= avFabric && zip <= avZip && thread <= avThread;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        productList.getItems().addAll(products);
    }

    public void setText(String name, String balance)
    {
        this.customer_username.setText(name);
        this.customer_balance.setText(balance);
    }
}
