package com.example.labfinalsa;

import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FileHandling.readDataFromFile();
        HBox mainScreen = mainPortalScreen();
        Scene scene = new Scene(mainScreen, 320, 240);
        switchScenes(scene,stage,"Portal");
    }

    public static void main(String[] args) {
        launch();
    }

    public static void switchScenes(Scene scene,Stage stage,String title){
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }

    public static HBox mainPortalScreen(){
        HBox root = new HBox(20);
        root.setAlignment(Pos.CENTER);


        VBox mainButtons = new VBox(10);
        mainButtons.setAlignment(Pos.CENTER_LEFT);
        Text memberText = new Text("Welcome!");
        memberText.setStyle("-fx-background-color: #4CAF50; " +
                "-fx-text-fill: white; " +
                "-fx-font-size: 18px; " +
                "-fx-font-weight: bold;");



        Button addNewMemberButton = createAndStyleButton("Add New Member");
        addNewMemberButton.setOnAction(
                e -> {
                    VBox vBoxInputForm = inputForm();
                    vBoxInputForm.setAlignment(Pos.CENTER_RIGHT);

                    Button backButton = new Button("Back");
                    backButton.setOnAction(
                            back -> {
                            root.getChildren().removeAll(vBoxInputForm,backButton);
                            }
                    );
                    vBoxInputForm.getChildren().addAll(backButton);
                    root.getChildren().addAll(vBoxInputForm);
                }
        );

        Button viewMembersButton = createAndStyleButton("View Members");
        viewMembersButton.setOnAction(
                viewMembers -> {
                    VBox vBox = ShowDataTablesOnScreen.createTables();
                    Scene thisScene = new Scene(vBox,450,320);
                    Stage stage = new Stage();
                    switchScenes(thisScene,stage,"Data");
                }
        );
        mainButtons.getChildren().addAll(memberText,addNewMemberButton,viewMembersButton);

        root.getChildren().add(mainButtons);
        return root;
    }
    public static Button createAndStyleButton(String title){
        Button button = new Button(title);
        button.setStyle("-fx-background-color: #4CAF50; " +
                "-fx-text-fill: white; " +
                "-fx-font-size: 16px; " +
                "-fx-padding: 10px 20px;");
        return button;
    }
    public static VBox inputForm(){

        VBox root = new VBox(20);
        root.setAlignment(Pos.CENTER);

        //name:
        VBox nameBox = new VBox(3);
        Label nameLabel = new Label("Enter Name");
        TextField nameField = new TextField();
        nameBox.getChildren().addAll(nameLabel,nameField);

        //gender:
        final ToggleGroup toggleGroup = new ToggleGroup();
        HBox genders = new HBox(5);
        RadioButton maleRadioButton = new RadioButton("MALE");
        maleRadioButton.setToggleGroup(toggleGroup);
        RadioButton femaleRadioButton = new RadioButton("FEMALE");
        femaleRadioButton.setToggleGroup(toggleGroup);
        maleRadioButton.setStyle("-fx-text-fill: blue;");
        femaleRadioButton.setStyle("-fx-text-fill: pink;");
        genders.getChildren().addAll(maleRadioButton,femaleRadioButton);

        //drop-down list:
        HBox comboHbox = new HBox(5);
        Label comboLabel = new Label("Choose Package");
        final ComboBox<String> comboBox = new ComboBox<>();
        comboBox.getItems().addAll(
                "STANDARD",
                "PREMIUM",
                "VIP"
        );
        comboBox.setValue("STANDARD");
        comboHbox.getChildren().addAll(comboLabel,comboBox);

        //date
        DatePicker datePicker = new DatePicker();
        final LocalDate[] date = new LocalDate[1];
        datePicker.setOnAction(new EventHandler() {
            public void handle(Event t) {
                date[0] = datePicker.getValue();
            }
        });

        //button
        Button saveButton = new Button("Save");
        saveButton.setOnAction(
                save -> {
                    String name = nameField.getText();
                    String genderr = getSelectedGender(toggleGroup);
                    String membershipType = comboBox.getValue();
                    Date dob = Date.from(date[0].atStartOfDay(ZoneId.systemDefault()).toInstant());
                    FileHandling.addToArraylist(new PersonInformation(name,genderr,membershipType,dob));

                    //clears all fields:
                    nameField.clear();
                    datePicker.setValue(null);
                    comboBox.setValue(null);
                    maleRadioButton.setSelected(false);
                    femaleRadioButton.setSelected(false);
                }
        );

        //setting
        root.getChildren().addAll(nameBox,genders,comboHbox,datePicker,saveButton);

        return root;
    }

    public static String getSelectedGender(ToggleGroup toggleGroup) {
        Toggle selectedToggle = toggleGroup.getSelectedToggle();

        if (selectedToggle != null) {
            RadioButton selectedRadioButton = (RadioButton) selectedToggle;
            return selectedRadioButton.getText();
        } else {
            return "NO_GENDER_SELECTED";
        }

    }

}