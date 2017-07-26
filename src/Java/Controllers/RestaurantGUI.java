package Java.Controllers;

import Java.Classes.Restaurant;
import Java.Structures.WeightedGraph;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import static java.lang.Math.*;

/**
 * Created by Biggus on 6/23/2017.
 */

public class RestaurantGUI implements Initializable
{
    @FXML
    private Pane                  pane;
    private TextField             locationField;
    private TextField             radiusField;
    private TextField             attributeSearchField;
    private Stage                 stage;
    private Scene                 scene;
    private TextArea              resultsArea;
    private ImageView             imView;
    private Button                backBtn;
    private Button                attributeBtn;
    private Button                locationBtn;
    private TableView<Restaurant> tmpSheetTableOne;
    
    
    private static WeightedGraph<Restaurant> restaurantGraph = new WeightedGraph<>(130);
    ObservableList<Restaurant> sheetCollection = FXCollections.observableArrayList();
    ObservableList<Restaurant> justCollection = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        //1366×768 is most common resolution
        pane.setMinSize(1300,750);
    
        backBtn = new Button();
        backBtn.setOnMouseClicked(e -> switchScene());
        backBtn.setLayoutY(500);
        backBtn.setLayoutX(210);
        backBtn.setPrefWidth(50);
        backBtn.setPrefHeight(20);
        backBtn.setText("Back");
        
        imView = new ImageView();
        imView.setLayoutX(150);
        imView.setLayoutY(330);
        imView.setFitWidth(400);
        imView.setFitHeight(160);
        imView.setPickOnBounds(true);
        imView.setPreserveRatio(true);
        
        resultsArea = new TextArea();
        resultsArea = new TextArea();
        resultsArea.setEditable(false);
        resultsArea.setLayoutX(15);
        resultsArea.setLayoutY(15);
        resultsArea.setPrefHeight(300);
        resultsArea.setPrefWidth(450);
        
        locationBtn = new Button("...");
        locationBtn.setOnMouseClicked(i -> locationResults());
        locationBtn.setAlignment(Pos.TOP_CENTER);
        locationBtn.setDefaultButton(true);
        locationBtn.setLayoutX(850);
        locationBtn.setLayoutY(6.5);
        locationBtn.setPrefHeight(26);
        locationBtn.setPrefWidth(26);
        
        radiusField = new TextField("Within This Radius");
        radiusField.setVisible(true);
        radiusField.setAlignment(Pos.TOP_CENTER);
        radiusField.setLayoutX(660.0);
        radiusField.setLayoutY(7.5);
        radiusField.setMinWidth(210);
        
        locationField = new TextField("Find A Restaurant Near You");
        locationField.setVisible(true);
        locationField.setAlignment(Pos.TOP_CENTER);
        locationField.setLayoutX(450.0);
        locationField.setLayoutY(7.5);
        locationField.setMinWidth(210);
        
        attributeSearchField = new TextField("Search");
        attributeSearchField.setOnKeyPressed(e -> {if(e.getCode() == KeyCode.ENTER){ Search(); }});
        attributeSearchField.setVisible(true);
        attributeSearchField.setAlignment(Pos.TOP_CENTER);
        attributeSearchField.setLayoutX(15.0);
        attributeSearchField.setLayoutY(7.5);
        attributeSearchField.setMinWidth(420);
    
        tmpSheetTableOne = new TableView<>(readExcel());
        tmpSheetTableOne.setOnMouseClicked(e -> tableSearch(tmpSheetTableOne));
        tmpSheetTableOne.setMinSize(1300, 750);
        tmpSheetTableOne.setPadding(new Insets(0, 15, 15, 0));
        tmpSheetTableOne.setLayoutX(15);
        tmpSheetTableOne.setLayoutY(45);
    
        attributeBtn = new Button("...");
        attributeBtn.setAlignment(Pos.TOP_CENTER);
        attributeBtn.setDefaultButton(true);
        attributeBtn.setLayoutX(409);
        attributeBtn.setLayoutY(6.5);
        attributeBtn.setOnMouseClicked(e -> Search());
        attributeBtn.setPrefHeight(26);
        attributeBtn.setPrefWidth(26);
        
        TableColumn<Restaurant, String> NameCol = new TableColumn<>("Name");
        NameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        NameCol.setMinWidth(180);
        NameCol.setMaxWidth(NameCol.getMinWidth());
        
        TableColumn<Restaurant, String> AddressCol = new TableColumn<>("Address");
        AddressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        AddressCol.setMinWidth(350);
        AddressCol.setMaxWidth(AddressCol.getMinWidth());
        
        TableColumn<Restaurant, String> LatitudeCol = new TableColumn<>("Latitude");
        LatitudeCol.setCellValueFactory(new PropertyValueFactory<>("latitude"));
        
        TableColumn<Restaurant, String> LongitudeCol = new TableColumn<>("Longitude");
        LongitudeCol.setCellValueFactory(new PropertyValueFactory<>("longitude"));
    
        TableColumn<Restaurant, String> NumberCol = new TableColumn<>("Number");
        NumberCol.setCellValueFactory(new PropertyValueFactory<>("number"));
        NumberCol.setMaxWidth(200);
    
        TableColumn<Restaurant, String> ImageCol = new TableColumn<>("Image");
        ImageCol.setCellValueFactory(new PropertyValueFactory<>("image"));
        ImageCol.setMinWidth(502);
        ImageCol.setMaxWidth(ImageCol.getMinWidth());

        tmpSheetTableOne.setItems(readExcel());
        tmpSheetTableOne.getColumns().addAll(NameCol, AddressCol, LatitudeCol, LongitudeCol, NumberCol, ImageCol);
        
        pane.getChildren().addAll(attributeSearchField, attributeBtn, tmpSheetTableOne, radiusField, locationField, locationBtn);
    }
    
    @SuppressWarnings({"unchecked", "unchecked"})
    public ObservableList readExcel()
    {
        String                     fileName        = "RestaurantsList.xls";
        FileInputStream            fis;
        
        // Make fis empty just in case
        fis = null;
        try
        {
            // Create a FileInputStream to read the excel file.
            fis = new FileInputStream(fileName);
            
            // Create an excel workbook from the file system.
            HSSFWorkbook workbook = new HSSFWorkbook(fis);
            
            // Get the first sheet on the workbook.
            HSSFSheet sheet = workbook.getSheetAt(0);
            
            //Use an iterator to store the info in the rows and columns into the List
            Iterator rows = sheet.rowIterator();
            //while (rows.hasNext())
            for (int i = 0; i < 59; i++)
            {
                Restaurant Imos    = new Restaurant();
                String     address = "";
                String location = "";
                HSSFRow    row     = (HSSFRow) rows.next();
                Iterator   cells   = row.cellIterator();
                
                int j = 0;
                while (cells.hasNext())
                {
                    HSSFCell cell = (HSSFCell) cells.next();
                    if (!(cell.toString().equals("") || cell.toString() == null))
                    {
                        //System.out.println("Cell: [" + cell + "]"/*\nIncrement #: " + j*/);
                        switch (j) {
                            case 0:
                                Imos.setName(cell.toString());
                                break;
                            case 1:
                                address += (cell.toString() + " ");
                                break;
                            case 2:
                                address += (cell.toString() + ", ");
                                break;
                            case 3:
                                address += (cell.toString() + " ");
                                break;
                            case 4:
                                int x = (int) cell.getNumericCellValue();
                                address += Integer.toString(x);
                                Imos.setAddress(address);
                                break;
                            case 5:
                                location += (cell.toString() + ", ");
                                break;
                            case 6:
                                location += cell.toString();
                                Imos.setLocation(location);
                                break;
                            case 7:
                                long y = (long) cell.getNumericCellValue();
                                Imos.setNumber(Long.toString(y));
                                break;
                            case 8:
                                final String url = cell.toString();
                                Imos.setImage(url);
                                break;
                            default:
                                break;
                        }
                        j++;
                    }
                }
                
                restaurantGraph.addVertex(Imos);
                sheetCollection.add(Imos);
                //System.out.println("Data List: " + Data.toString());
                //System.out.println("Sheet Data: " + sheetData.toString());
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (fis != null)
            {
                try
                {
                    fis.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
    
        return sheetCollection;
    }
    
    public void Search()
    {
        String tmp = "";
        int type = 0;

        if(attributeSearchField.getText().contains(",")) {
            tmp = attributeSearchField.getText().substring(0, attributeSearchField.getText().indexOf(","));
        }

        if(Pattern.matches("[\\d]+[\\.][\\d]+", tmp))
        {
            //key is a coordinate
            type = 1;
        }
        else if (Pattern.matches("^[\\d]{10}",attributeSearchField.getText()))
        {
            //key is a number
            type = 2;
        }
        else if (Pattern.matches("^[^\\d]+[a-zA-Z]+", attributeSearchField.getText()))
        {
            //key is a name
            type = 3;
        }

        //System.out.println(type);

        if(type != 0)
        {
            attributeResults(type);
        }
    }
    
    public void attributeResults(int dataType)
    {
        String              tmpString = "";
        Image               im        = null;
        Restaurant          tmpRest;
    
        //tmpRest holds the dequeued Restaurant and anotherRest was just hholding a Restaurant with one attribute
        for (int i = 0; i < restaurantGraph.getNumVertices(); i++)
        {
            
            tmpRest = (Restaurant) restaurantGraph.getVertices(i);
        
            switch (dataType)
            {
                case 1:
                    //datatype == 1 is a coordinate
                    if(tmpRest.getLocation().equals(attributeSearchField.getText()))
                    {
                        tmpString = tmpRest.toString();
                        tmpString = tmpString.replace("StringProperty [value: ", "");
                        tmpString = tmpString.replace("]", "");
                        im = new Image(tmpRest.getImage());
                    }
                    else
                    {
                        tmpString = "Element not in list";
                    }
                    break;
                case 2:
                    //datatype == 2 is a number
                    if(tmpRest.getNumber().equals(attributeSearchField.getText()))
                    {
                        tmpString = tmpRest.toString();
                        tmpString = tmpString.replace("StringProperty [value: ", "");
                        tmpString = tmpString.replace("]", "");
                        im = new Image(tmpRest.getImage());
                    }
                    else
                    {
                        tmpString = "Element not in list";
                    }
                    break;
                case 3:
                    //datatype == 3 is a name
                    if(tmpRest.getName().equals(attributeSearchField.getText()))
                    {
                        tmpString = tmpRest.toString();
                        tmpString = tmpString.replace("StringProperty [value: ", "");
                        tmpString = tmpString.replace("]", "");
                        im = new Image(tmpRest.getImage());
                    }
                    else
                    {
                        tmpString = "Element not in list";
                    }
                    break;
            }
        
            imView.setImage(im);
            resultsArea.setText(tmpString);
        }
    }
    
    public void locationResults()
    {
        Restaurant focus        = null;
        Restaurant restOfCollection;
        Restaurant userLocation = new Restaurant();
        double     restOfCollectionLat, restOfCollectionLong, focusLat, focusLong;
        String     output;
        String     lati         = locationField.getText().substring(locationField.getText().indexOf(".")-2, locationField.getText().lastIndexOf(".")-3);
        String     longi        = locationField.getText().substring(locationField.getText().lastIndexOf(".") - 3);
        
        lati = lati.replace(" ", "");
        lati = lati.replace(",", "");
        
        userLocation.setLocation("0, 0");
        userLocation.setLatitude(lati);
        userLocation.setLongitude(longi);
        userLocation.setName("Your Location");
    
        restaurantGraph.addVertex(userLocation);
        sheetCollection.add(0, userLocation);
        
        for(int i = 0; i < sheetCollection.size(); i++)
        {
            focus = sheetCollection.get(i);
            output = focus.getLatitude();
            output = output.replace(",", "");
            focusLat = Double.parseDouble(output);
            output = focus.getLongitude();
            output = output.replace(",", "");
            focusLong = Double.parseDouble(output);
            output = "";
    
            for (int j = (i + 1); j < sheetCollection.size(); j++)
            {
                restOfCollection = sheetCollection.get(j);
                restOfCollectionLat = Double.parseDouble(restOfCollection.getLatitude().substring(0, 5));
                restOfCollectionLong = Double.parseDouble(restOfCollection.getLongitude().substring(0, 5).replace(",", ""));
    
                restaurantGraph.addEdge(focus, restOfCollection, haversineDistance(focusLong, restOfCollectionLong, focusLat, restOfCollectionLat));
            }
        }
        
        for(int i = 0; i < tmpSheetTableOne.getItems().size(); i++)
        {
            //System.out.println(sheetCollection.get(i).getName() + " " + restaurantGraph.weightIs(userLocation, sheetCollection.get(i)) + " " + Integer.parseInt(radiusField.getText()));
            if (restaurantGraph.weightIs(userLocation, sheetCollection.get(i)) <= Integer.parseInt(radiusField.getText()))
            {
                justCollection.add(sheetCollection.get(i));
            }
        }
        
        tmpSheetTableOne.getItems().clear();
        tmpSheetTableOne.setItems(justCollection);
    }
    
    public int haversineDistance(double Lon1, double Lon2, double Lat1, double Lat2)
    {
        final double EARTHS_RADIUS_KM = 6371;
    
        double a, c;
    
        double dLat = toRadians(Lat2 - Lat1);
        double dLon = toRadians(Lon2 - Lon1);
    
        Lat2 = toRadians(Lat2);
        Lat1 = toRadians(Lat1);
    
        a = pow(sin(dLat/2), 2) + cos(Lat1) * cos(Lat2) * pow(sin(dLon/2), 2);
        c = 2 * atan2(sqrt(a), sqrt(1 - a));
    
        return (int) ((int) EARTHS_RADIUS_KM * c);
    }
    
    private void tableSearch(TableView table)
    {
        Restaurant item = (Restaurant) table.getSelectionModel().getSelectedItem();
        //System.out.println(item.getName());
        attributeSearchField.setText(item.getName());
        Search();
    }
    
    private void switchScene()
    {
        Stage stage = (Stage) backBtn.getScene().getWindow();
        stage.close();
        Parent RPopUp;
        try
        {
            RPopUp = FXMLLoader.load(this.getClass().getResource("/Resources/View/Main.fxml"));
            Scene scene = new Scene(RPopUp);
            stage.setScene(scene);
            stage.show();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}