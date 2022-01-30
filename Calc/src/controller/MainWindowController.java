package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.NetClient;

public class MainWindowController {
	private Main main;
	private Stage primaryStage;
	private static String expression = "";
	private static String serverAddress = "localhost";
	private String lastExpression = "";
	private String screenExpression = "";
	private String lastScreenExpression = "";
	private String number = "";
	private String lastNumber = "";
	private static String result = "";
	private static int portNumber = 9001;
	private int c = 0;
	private int oper = 0;
	private int min = 0;
	private int sub = 0;

	@FXML
	private Button acBtn;
	@FXML
	private Button plus_minBtn;
	@FXML
	private Button percentBtn;
	@FXML
	private Button divideBtn;
	@FXML
	private Button sevBtn;
	@FXML
	private Button eigBtn;
	@FXML
	private Button ninBtn;
	@FXML
	private Button multiplyBtn;
	@FXML
	private Button fouBtn;
	@FXML
	private Button fivBtn;
	@FXML
	private Button sixBtn;
	@FXML
	private Button subtractBtn;
	@FXML
	private Button oneBtn;
	@FXML
	private Button twoBtn;
	@FXML
	private Button thrBtn;
	@FXML
	private Button addBtn;
	@FXML
	private Button zerZerBtn;
	@FXML
	private Button zerBtn;
	@FXML
	private Button dotBtn;
	@FXML
	private Button equalsBtn;
	@FXML
	private TextField textField;

	public void setMain(Main main) {
		this.main = main;
		textField.setText("0");
	}

	public static String getExpression() {
		return expression;
	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}
	
	public static void getResult() throws Exception {
		NetClient client = new NetClient(serverAddress, portNumber);
		client.send();
		result = NetClient.getResponse();
		client.close();
	}

	@FXML
	public void acBtnAction() {
		expression = "";
		screenExpression = "";
		number = "";
		lastNumber = "";
		textField.setText("0");
		c = 1;
		oper = 0;
		min = 0;
		sub = 0;
	}

	@FXML
	private void plus_minBtnAction(ActionEvent event) {
		if (oper == 0 && c == 0) {
			if (min == 0) {
				lastNumber = number;
				number = "-" + number;
				textField.setText(lastScreenExpression + number);
				min = 1;
			} else {
				number = lastNumber;
				textField.setText(lastScreenExpression + number);
				min = 0;
			}
		}
	}

	@FXML
	private void percentBtnAction(ActionEvent event) {
		lastExpression = expression;
		double number1 = (Double.parseDouble(result)) * (Double.parseDouble(number) / 100.);
		number = ("" + number1);
		screenExpression = expression + number;
		textField.setText(screenExpression);
		oper = 0;
	}

	@FXML
	private void divideBtnAction(ActionEvent event) throws Exception {
		if (oper == 0) {
			if (min == 1 && sub == 1) {
				expression = expression + "(" + number + ")";
				sub = 0;
				min = 0;
			} else {
				expression = expression + number;
			}
			getResult();
			lastScreenExpression = result;
			lastExpression = result;
			screenExpression = result + "/";
			expression = result + "*1./";
			textField.setText(screenExpression);
			number = "";
			oper = 1;
			min = 0;
		} else {
			screenExpression = lastExpression + "/";
			expression = lastExpression + "*1./";
			textField.setText(screenExpression);
			oper = 1;
		}
	}

	@FXML
	private void sevBtnAction(ActionEvent event) {
		number = number + "7";
		lastScreenExpression = screenExpression;
		textField.setText(lastScreenExpression + number);
		c = 0;
		oper = 0;
	}

	@FXML
	private void eigBtnAction(ActionEvent event) {
		number = number + "8";
		lastScreenExpression = screenExpression;
		textField.setText(lastScreenExpression + number);
		c = 0;
		oper = 0;
	}

	@FXML
	private void ninBtnAction(ActionEvent event) {
		number = number + "9";
		lastScreenExpression = screenExpression;
		textField.setText(lastScreenExpression + number);
		c = 0;
		oper = 0;
	}

	@FXML
	private void multiplyBtnAction(ActionEvent event) throws Exception {

		if (oper == 0) {
			if (min == 1 && sub == 1) {
				expression = expression + "(" + number + ")";
				sub = 0;
				min = 0;
			} else {
				expression = expression + number;
			}
			getResult();
			lastScreenExpression = result;
			lastExpression = result;
			screenExpression = result + "x";
			expression = result + "*";
			textField.setText(screenExpression);
			number = "";
			oper = 1;
			min = 0;
		} else {
			screenExpression = lastExpression + "x";
			expression = lastExpression + "*";
			textField.setText(screenExpression);
			oper = 1;
		}
	}

	@FXML
	private void fouBtnAction(ActionEvent event) {
		number = number + "4";
		lastScreenExpression = screenExpression;
		textField.setText(lastScreenExpression + number);
		c = 0;
		oper = 0;
	}

	@FXML
	private void fivBtnAction(ActionEvent event) {
		number = number + "5";
		lastScreenExpression = screenExpression;
		textField.setText(lastScreenExpression + number);
		c = 0;
		oper = 0;
	}

	@FXML
	private void sixBtnAction(ActionEvent event) {
		number = number + "6";
		lastScreenExpression = screenExpression;
		textField.setText(lastScreenExpression + number);
		c = 0;
		oper = 0;
	}

	@FXML
	private void subtractBtnAction(ActionEvent event) throws Exception {
		if (oper == 0) {
			if (min == 1 && sub == 1) {
				expression = expression + "(" + number + ")";
				sub = 0;
				min = 0;
			} else {
				expression = expression + number;
			}
			getResult();
			lastScreenExpression = result;
			lastExpression = result;
			screenExpression = result + "-";
			expression = result + "-";
			textField.setText(screenExpression);
			number = "";
			oper = 1;
			sub = 1;
			min = 0;
		} else {
			screenExpression = lastExpression + "-";
			expression = lastExpression + "-";
			textField.setText(screenExpression);
			oper = 1;
			sub = 1;
		}
	}

	@FXML
	private void oneBtnAction(ActionEvent event) {
		number = number + "1";
		lastScreenExpression = screenExpression;
		textField.setText(lastScreenExpression + number);
		c = 0;
		oper = 0;
	}

	@FXML
	private void twoBtnAction(ActionEvent event) {
		number = number + "2";
		lastScreenExpression = screenExpression;
		textField.setText(lastScreenExpression + number);
		c = 0;
		oper = 0;
	}

	@FXML
	private void thrBtnAction(ActionEvent event) {
		number = number + "3";
		lastScreenExpression = screenExpression;
		textField.setText(lastScreenExpression + number);
		c = 0;
		oper = 0;
	}

	@FXML
	private void addBtnAction(ActionEvent event) throws Exception {
		if (oper == 0) {
			if (min == 1 && sub == 1) {
				expression = expression + "(" + number + ")";
				sub = 0;
				min = 0;
			} else {
				expression = expression + number;
			}
			getResult();
			lastScreenExpression = result;
			lastExpression = result;
			screenExpression = result + "+";
			expression = result + "+";
			textField.setText(screenExpression);
			number = "";
			oper = 1;
			min = 0;
		} else {
			screenExpression = lastExpression + "+";
			expression = lastExpression + "+";
			textField.setText(screenExpression);
			oper = 1;
		}
	}

	@FXML
	private void zerZerBtnAction(ActionEvent event) {
		number = number + "00";
		lastScreenExpression = screenExpression;
		textField.setText(lastScreenExpression + number);
		c = 0;
		oper = 0;
	}

	@FXML
	private void zerBtnAction(ActionEvent event) {
		number = number + "0";
		lastScreenExpression = screenExpression;
		textField.setText(lastScreenExpression + number);
		c = 0;
		oper = 0;
	}

	@FXML
	private void dotBtnAction(ActionEvent event) {
		number = number + ".";
		lastScreenExpression = screenExpression;;
		textField.setText(lastScreenExpression + number);
		c = 0;
		oper = 0;
	}

	@FXML
	private void equalsBtnAction(ActionEvent event) throws Exception {
		if (min == 1 && sub == 1) {
			expression = expression + "(" + number + ")";
			sub = 0;
			min = 0;
		} else {
			expression = expression + number;
		}
		getResult();
		expression = "";
		number = result;
		screenExpression = result;
		textField.setText(screenExpression);
		c = 1;
		oper = 0;
	}
}
