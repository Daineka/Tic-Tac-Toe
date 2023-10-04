package com.example.tictactoefx;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.event.ActionEvent;

import java.net.URL;
import java.util.*;

public class HelloController implements Initializable {
    @FXML
    private Button button1;
    @FXML
    private Button button2;
    @FXML
    private Button button3;
    @FXML
    private Button button4;
    @FXML
    private Button button5;
    @FXML
    private Button button6;
    @FXML
    private Button button7;
    @FXML
    private Button button8;
    @FXML
    private Button button9;
    @FXML
    private Button buttonRestart;
    @FXML
    private Label winnerText;
    @FXML
    private RadioButton humanVsHuman;
    @FXML
    private RadioButton humanVsComputer;

    List<Button> buttons;
    private int count = 1;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        buttons = new ArrayList<>(Arrays.asList(button1, button2, button3, button4, button5, button6, button7, button8, button9));
        buttons.forEach(button -> {
            button.setText(" ");
            button.setFocusTraversable(false);
        });
        ToggleGroup toggleGroup = new ToggleGroup();
        humanVsComputer.setToggleGroup(toggleGroup);
        humanVsHuman.setToggleGroup(toggleGroup);
        humanVsComputer.setSelected(true);
        humanVsComputer.setFocusTraversable(false);
        humanVsHuman.setFocusTraversable(false);
    }

    @FXML
    public void onClickMethod(ActionEvent event) {
        if (event.getSource() == button1) {
            turn(button1);
        } else if (event.getSource() == button2) {
            turn(button2);
        } else if (event.getSource() == button3) {
            turn(button3);
        } else if (event.getSource() == button4) {
            turn(button4);
        } else if (event.getSource() == button5) {
            turn(button5);
        } else if (event.getSource() == button6) {
            turn(button6);
        } else if (event.getSource() == button7) {
            turn(button7);
        } else if (event.getSource() == button8) {
            turn(button8);
        } else if (event.getSource() == button9) {
            turn(button9);
        }
    }

    private void handleHumanVsHuman(Button button) {
        if (count % 2 == 1) {
            button.setText("X");
            button.setDisable(true);
            if (checkWinX()) {
                winnerText.setText("X win!!!");
                gameOver();
                return;
            }
        } else {
            button.setText("O");
            button.setDisable(true);
            if (checkWinO()) {
                winnerText.setText("O win!!!");
                gameOver();
                return;
            }
        }
        if (isTableFull()) {
            winnerText.setText("Draw!");
            return;
        }
        count++;
    }

    private void handleHumanVsComputer(Button button) {
        button.setText("X");
        button.setDisable(true);
        if (checkWinX()) {
            winnerText.setText("X win!!!");
            gameOver();
            return;
        }
        if (isTableFull()) {
            winnerText.setText("Draw!");
            return;
        }
        turnAi();
        if (checkWinO()) {
            winnerText.setText("O win!!!");
            gameOver();
            return;
        }
        if (isTableFull()) {
            winnerText.setText("Draw!");
            return;
        }
    }

    private void turnAi() {
        Random random = new Random();
        int turnRandom;
        do {
            turnRandom = random.nextInt(9);
        } while (buttons.get(turnRandom).isDisable());
        buttons.get(turnRandom).setText("O");
        buttons.get(turnRandom).setDisable(true);
    }

    private void turn(Button button) {
        if (humanVsHuman.isSelected()) {
            handleHumanVsHuman(button);
        } else if (humanVsComputer.isSelected()) {
            handleHumanVsComputer(button);
        }

    }

    boolean checkWinX() {
        return (button1.getText().equals("X") && button2.getText().equals("X") && button3.getText().equals("X")) ||
                (button1.getText().equals("X") && button4.getText().equals("X") && button7.getText().equals("X")) ||
                (button4.getText().equals("X") && button5.getText().equals("X") && button6.getText().equals("X")) ||
                (button7.getText().equals("X") && button8.getText().equals("X") && button9.getText().equals("X")) ||
                (button2.getText().equals("X") && button5.getText().equals("X") && button8.getText().equals("X")) ||
                (button3.getText().equals("X") && button6.getText().equals("X") && button9.getText().equals("X")) ||
                (button1.getText().equals("X") && button5.getText().equals("X") && button9.getText().equals("X")) ||
                (button3.getText().equals("X") && button5.getText().equals("X") && button7.getText().equals("X"));
    }

    boolean checkWinO() {
        return (button1.getText().equals("O") && button2.getText().equals("O") && button3.getText().equals("O")) ||
                (button1.getText().equals("O") && button4.getText().equals("O") && button7.getText().equals("O")) ||
                (button4.getText().equals("O") && button5.getText().equals("O") && button6.getText().equals("O")) ||
                (button7.getText().equals("O") && button8.getText().equals("O") && button9.getText().equals("O")) ||
                (button2.getText().equals("O") && button5.getText().equals("O") && button8.getText().equals("O")) ||
                (button3.getText().equals("O") && button6.getText().equals("O") && button9.getText().equals("O")) ||
                (button1.getText().equals("O") && button5.getText().equals("O") && button9.getText().equals("O")) ||
                (button3.getText().equals("O") && button5.getText().equals("O") && button7.getText().equals("O"));
    }

    boolean isTableFull() {
        return (!button1.getText().equals(" ")) && (!button2.getText().equals(" ")) && (!button3.getText().equals(" "))
                && (!button4.getText().equals(" ")) && (!button5.getText().equals(" ")) && (!button6.getText().equals(" "))
                && (!button7.getText().equals(" ")) && (!button8.getText().equals(" ")) && (!button9.getText().equals(" "));
    }

    @FXML
    public void onClickMethodRestart() {
        count = 1;
        buttons.forEach(this::resetButton);
    }

    private void resetButton(Button button) {
        button.setDisable(false);
        button.setText(" ");
        winnerText.setText("Tic Tac Toe");
    }

    private void gameOver() {
        buttons.forEach(this::buttonDisable);
    }

    private void buttonDisable(Button button) {
        button.setDisable(true);
    }
}