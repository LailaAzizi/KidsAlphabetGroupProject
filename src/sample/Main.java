package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;


/**
 * Names: Tahmineh Darvish,Laila Azizi 
 * Course:CS170-01
 * Submission Date: 10: 00 am, Wednesday (12/9/2020)
 * Name of the Project: Kids Alphabet  
 * Main class which shows main screen to user and he/she can choose one of the 3 main parts of the application
 * it creates primary stage and other screens are the children of this
 */

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {

		primaryStage.setTitle("Kids Alphabet");

		// define main menu background image //
		BackgroundImage myBI = new BackgroundImage(new Image("sample/ui_assets/bg1.png"),
				BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
				BackgroundSize.DEFAULT);

		// we dont want user to resize the screen
		primaryStage.setResizable(false);

		// create main buttons
		ImageButton ibLearn = new ImageButton(new Image("sample/ui_assets/button1.png"), new Image("sample/ui_assets/button1_pressed.png"));
		ImageButton ibQuiz = new ImageButton(new Image("sample/ui_assets/button2.png"), new Image("sample/ui_assets/button2_pressed.png"));
		ImageButton ibScores = new ImageButton(new Image("sample/ui_assets/button3.png"), new Image("sample/ui_assets/button3_pressed.png"));

		// adding button call backs
		ibLearn.callback = param -> {
			Teaching.showTeachScreen(primaryStage);
			return false;
		};

		ibQuiz.callback = param -> {
			Quiz.showQuizScreen(primaryStage);
			return false;
		};

		ibScores.callback = param -> {
			Score.showScoreScreen(primaryStage);
			return false;
		};

		// main menu buttons are vertically aligned so we user VBox
		VBox vbox = new VBox(ibLearn, ibQuiz, ibScores);

		// adjusting positions and alignments
		vbox.setPadding(new Insets(170, 0, 10, 30));
		VBox.setMargin(ibLearn, new Insets(10, 10, 10, 10));
		VBox.setMargin(ibQuiz, new Insets(10, 10, 10, 10));
		VBox.setMargin(ibScores, new Insets(10, 10, 10, 10));
		vbox.setBackground(new Background(myBI));
		Scene sc = new Scene(vbox, 1078, 628);
		primaryStage.setScene(sc);

		primaryStage.show();

	}


	public static void main(String[] args) {
		
		launch(args);
	}
}
