package com.example.labfinalsa;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import java.util.Date;

public class ShowDataTablesOnScreen {
    public static VBox createTables(){

        ObservableList observableList = FXCollections.observableArrayList(FileHandling.personInformationArrayList);

        //create table:

        //set table view:
        TableView<PersonInformation> tableView = new TableView<>();
        tableView.setItems(observableList);

//        set col1:
        TableColumn<PersonInformation,String> personNameCol = new TableColumn<>("Name");
        personNameCol.setCellValueFactory(new PropertyValueFactory<PersonInformation,String>("name"));

        //set col2:
        TableColumn<PersonInformation,String> genderCol = new TableColumn<>("Gender");
        genderCol.setCellValueFactory(new PropertyValueFactory<PersonInformation,String>("gender"));

        //set col3:
        TableColumn<PersonInformation,String> membershipTypeCol = new TableColumn<>("MembershipType");
        membershipTypeCol.setCellValueFactory(new PropertyValueFactory<PersonInformation,String>("membershipTypes"));

        //set col4:
        TableColumn<PersonInformation, Date> dateTableColumn = new TableColumn<>("D-O-B");
        dateTableColumn.setCellValueFactory(new PropertyValueFactory<PersonInformation,Date>("dateOfBirth"));

        tableView.getColumns().addAll(personNameCol,genderCol,membershipTypeCol,dateTableColumn);
        tableView.getSortOrder().add(personNameCol);


        //create sorting choicebox:
        ChoiceBox<String> sortingChoiceBox = new ChoiceBox<>();
        sortingChoiceBox.getItems().addAll(
                "Name",
                "MembershipType",
                "Gender",
                "DOB"
        );
        sortingChoiceBox.setValue("Name");

        sortingChoiceBox.setOnAction(
                e -> {
                    String selectedCol = sortingChoiceBox.getValue();
                    TableColumn<PersonInformation,?> column;
                    switch (selectedCol){
                        case "Name":
                            column = personNameCol;
                            break;

                        case "Gender":
                            column = genderCol;
                            break;

                        case "MembershipType":
                            column = membershipTypeCol;
                            break;

                        case "DOB":
                            column = dateTableColumn;
                            break;

                        default:
                            column = personNameCol;
                    }
                    tableView.getSortOrder().add(column);
                }
        );

        VBox vBox = new VBox(15);
        vBox.getChildren().addAll(sortingChoiceBox,tableView);
        return vBox;
    }
}