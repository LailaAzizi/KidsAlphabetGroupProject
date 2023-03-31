package sample;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * Names: Tahmineh Darvish,Laila Azizi 
 * Course:CS170-01
 * Submission Date: 10: 00 am, Wednesday (12/9/2020)
 * Name of the Project: Kids Alphabet  
 * Quiz class which shows a picture to user and ask for the first letter
 * after 5 questions calculates the total score in which every correct answer has 20 scores
 * at the end asks user for name and call Score.saveScore in order to save score in the score.txt file
 */

public class Quiz {

    private static int questionNumber = 0 ;
    private static int score = 0 ;
    private static ImageView congrats;
    private static Label scoreText;
    private static ImageView letter1;
    private static ImageView letter2;
    private static ImageView letter3;
    private static ImageView letterImage;
    private static javafx.scene.control.TextField nameTextField;
    private static javafx.scene.control.Button saveButton;

    public static void showQuizScreen(Stage primaryStage){
        questionNumber = 0;
        ImageView secondLabel = new ImageView(new Image("sample/ui_assets/bg2.png"));
        ImageView mask = new ImageView(new Image("sample/ui_assets/mask.png"));
        letterImage = new ImageView();
        congrats = new ImageView(new Image("sample/ui_assets/congrats.png"));
        nameTextField = new javafx.scene.control.TextField("Enter your Name");
        scoreText = new Label();

        letter1 = new ImageView();
        letter2 = new ImageView();
        letter3 = new ImageView();
        saveButton = new javafx.scene.control.Button("save");
        saveButton.setFont(new Font("Arial" , 32));

        showNextQuestionInUI();

        StackPane secondaryLayout = new StackPane();
        secondaryLayout.getChildren().add(secondLabel);
        secondaryLayout.getChildren().add(mask);
        secondaryLayout.getChildren().add(letterImage);
        secondaryLayout.getChildren().add(congrats);
        secondaryLayout.getChildren().add(scoreText);

        secondaryLayout.getChildren().add(letter1);
        secondaryLayout.getChildren().add(letter2);
        secondaryLayout.getChildren().add(letter3);
        
        secondaryLayout.getChildren().add(saveButton);
        secondaryLayout.getChildren().add(nameTextField);

        //secondaryLayout.getChildren().add(soundButton);
        //secondaryLayout.getChildren().add(shuffleButton);
        mask.toFront();

        letter1.toFront();
        letter2.toFront();
        letter3.toFront();

        saveButton.setVisible(false);
        congrats.setVisible(false);
        scoreText.setVisible(false);
        nameTextField.setVisible(false);

        
        nameTextField.setPrefWidth(280);
        nameTextField.setMaxWidth(280);

        
        secondaryLayout.setAlignment(letter1 , Pos.CENTER_LEFT);
        secondaryLayout.setAlignment(letter2 , Pos.CENTER_LEFT);
        secondaryLayout.setAlignment(letter3 , Pos.CENTER_LEFT);
        secondaryLayout.setAlignment(scoreText , Pos.BOTTOM_CENTER);

        
        secondaryLayout.setMargin(saveButton , new Insets(0,0,-10,0));
        secondaryLayout.setMargin(nameTextField , new Insets(0,0,110,0));
        secondaryLayout.setMargin(letter2 , new Insets(0,0,320,90));
        secondaryLayout.setMargin(letter3 , new Insets(0,0,-320,90));
        secondaryLayout.setMargin(letter1 , new Insets(0,0,0,90));

        secondaryLayout.setMargin(scoreText , new Insets(0,0,45,168));


        secondaryLayout.setPadding(new Insets(30));

        Scene secondScene = new Scene(secondaryLayout, 1078, 628);

        // New window (Stage)
        Stage newWindow = new Stage();
        newWindow.setTitle("Quiz Time !!");
        newWindow.setScene(secondScene);
        newWindow.setResizable(false);

        // Specifies the modality for new window.
        newWindow.initModality(Modality.WINDOW_MODAL);

        // Specifies the owner Window (parent) for new window
        newWindow.initOwner(primaryStage);

        // Set position of second window, related to primary window.
        newWindow.setX(primaryStage.getX() + 200);
        newWindow.setY(primaryStage.getY() + 100);

        newWindow.show();
        
        saveButton.setOnAction(e -> {     
        	if(!nameTextField.getText().equals("Enter your Name") && nameTextField.getText().length() > 0) {        		        
        		Score.saveScore(nameTextField.getText(), score);
            	newWindow.close();
        	}
        });
        
    }

    private static void showNextQuestionInUI() {
        int letter_index = showRandomLetter(letterImage);
        int correct_answer_index = fillChoices(letter_index,letter1,letter2,letter3);

        letter1.setOnMouseClicked(event -> {
            if(correct_answer_index == 0){
                correctAnswer();
            }else{
                inCorrectAnswer();
            }
        });
        letter2.setOnMouseClicked(event -> {
            if(correct_answer_index == 1){
                correctAnswer();
            }else{
                inCorrectAnswer();
            }
        });
        letter3.setOnMouseClicked(event -> {
            if(correct_answer_index == 2){
                correctAnswer();
            }else{
                inCorrectAnswer();
            }
        });
    }

    private static int fillChoices(int index , ImageView i1,ImageView i2,ImageView i3){
        Random r = new Random();

        int correct_choice = r.nextInt(3);
        int incorrect_choice1 = index;
        int incorrect_choice2 = index;
        while (incorrect_choice1 == index){
            incorrect_choice1 = r.nextInt(26);
        }
        while (incorrect_choice2 == index ||
                incorrect_choice2 == incorrect_choice1){
            incorrect_choice2 = r.nextInt(26);
        }

        Image correct_image = new Image("sample/alphabet_letter_images/letter (" + (index + 1) + ").png");
        Image incorrect_image1 = new Image("sample/alphabet_letter_images/letter (" + (incorrect_choice1 + 1) + ").png");
        Image incorrect_image2 = new Image("sample/alphabet_letter_images/letter (" + (incorrect_choice2 + 1) + ").png");

        switch (correct_choice){
            case 0:
                i1.setImage(correct_image);
                i2.setImage(incorrect_image1);
                i3.setImage(incorrect_image2);
                break;
            case 1:
                i1.setImage(incorrect_image1);
                i2.setImage(correct_image);
                i3.setImage(incorrect_image2);
                break;
            case 2:
                i1.setImage(incorrect_image1);
                i2.setImage(incorrect_image2);
                i3.setImage(correct_image);
                break;
        }

        return correct_choice;
    }

    private static int showRandomLetter(ImageView image){
        Random r = new Random();
        int index = r.nextInt(26);
        char randomChar = (char) (index + 'a');
        System.out.println(randomChar);
        image.setImage(new Image("sample/alphabet_images/letter (" + (index + 1) + ").png"));
        return index;
    }

    private static void correctAnswer(){
        score+=20;
        nextQuestion();
    }

    private static void inCorrectAnswer(){
        nextQuestion();
    }

    private static void nextQuestion() {
        showNextQuestionInUI();
        questionNumber++;
        if(questionNumber == 5){
            endQuiz();
        }
    }

    private static void endQuiz(){
     
        congrats.toFront();
        congrats.setVisible(true);
        scoreText.setVisible(true);
        scoreText.setFont(new Font("Arial" , 68));
        scoreText.setTextFill(new Color(217/255f,54/255f,85/255f,1));
        scoreText.setText(score + "");
        scoreText.toFront();
        showAskNameDialog();
    }

    private static void showAskNameDialog() {
    	nameTextField.setVisible(true);
    	nameTextField.toFront();
    	saveButton.setVisible(true);
    	saveButton.toFront();
        
    }
}
