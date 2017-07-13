package Java.Controllers;

import Java.Classes.Restaurant;
import Java.Structures.BST;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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

/**
 * Created by Biggus on 6/23/2017.
 */
public class RestaurantGUI implements Initializable
{
    @FXML
    private Pane                  pane;
    private TextField             Search;
    private Stage                 stage;
    private Scene                 scene;
    
    private static BST<Restaurant> restaurantTree = new BST<>();

    //todo Make clicking on a row show the restaurant info

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        //1366×768 is most common resolution
        pane.setMinSize(1300,750);
        
        Search = new TextField("Search");
        Search.setOnKeyPressed(e -> {if(e.getCode() == KeyCode.ENTER){ Search(); }});
        Search.setVisible(true);
        Search.setAlignment(Pos.TOP_CENTER);
        Search.setLayoutX(15.0);
        Search.setLayoutY(7.5);
        Search.setMinWidth(420);
        
        //System.out.println(readExcel());
    
        TableView<Restaurant> tmpSheetTableOne = new TableView<>(readExcel());
        tmpSheetTableOne.setOnMouseClicked(e -> tableSearch(tmpSheetTableOne));
        tmpSheetTableOne.setMinSize(1300, 750);
        tmpSheetTableOne.setPadding(new Insets(0, 15, 15, 0));
        tmpSheetTableOne.setLayoutX(15);
        tmpSheetTableOne.setLayoutY(45);
    
        Button tmpSearchBtnOne = new Button("...");
        tmpSearchBtnOne.setAlignment(Pos.TOP_CENTER);
        tmpSearchBtnOne.setDefaultButton(true);
        tmpSearchBtnOne.setLayoutX(409);
        tmpSearchBtnOne.setLayoutY(6.5);
        tmpSearchBtnOne.setOnMouseClicked(e -> Search());
        tmpSearchBtnOne.setPrefHeight(26);
        tmpSearchBtnOne.setPrefWidth(26);
        
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
        
        pane.getChildren().addAll(Search, tmpSearchBtnOne, tmpSheetTableOne);
    }
    
    @SuppressWarnings({"unchecked", "unchecked"})
    public ObservableList readExcel()
    {
        String                     fileName        = "RestaurantsList.xls";
        FileInputStream            fis;
        ObservableList<Restaurant> sheetCollection = FXCollections.observableArrayList();
        
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
                
                restaurantTree.add(Imos);
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
        String key = Search.getText();
        int type = 0;

        try
        {
            stage = (Stage) this.Search.getScene().getWindow();
            stage.close();
            Parent RPopUp = FXMLLoader.load(this.getClass().getResource("/Resources/View/RestaurantPopUp.fxml"));
            scene = new Scene(RPopUp);
            stage.setScene(scene);
            stage.show();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        if(key.contains(",")) {
            tmp = key.substring(0, key.indexOf(","));
        }

        if(Pattern.matches("[\\d]+[\\.][\\d]+", tmp))
        {
            //key is a coordinate
            type = 1;
        }
        if (Pattern.matches("^[\\d]{10}", key))
        {
            //key is a number
            type = 2;
        }
        if (Pattern.matches("^[^\\d]+[a-zA-Z]+", key))
        {
            //key is a name
            type = 3;
        }

        //System.out.println(type);

        if(type != 0)
        {
            RestaurantPopUp.showResults(key, restaurantTree, type);
        }
    }
    
    public void tableSearch(TableView table)
    {
        Restaurant item = (Restaurant) table.getSelectionModel().getSelectedItem();
        //System.out.println(item.getName());
        Search.setText(item.getName());
        Search();
    }
}