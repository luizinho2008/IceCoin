import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ControllerTransferir {

    @FXML
    private TextField destinatario;

    @FXML
    private TextField quantidade;

    @FXML
    private ComboBox<String> remetente;

    @FXML
    private Text carteira_menuBar;

    @FXML
    private Text contato_menuBar;

    private Stage stage;
    private Scene scene;

    private Connection connectToDatabase() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/icecoin_db";
        String user = "root";
        String password = "ifsp";
    
        return DriverManager.getConnection(url, user, password);
    }

    @FXML
    private void initialize() {
        try (Connection connection = connectToDatabase()) {
            String query = "SELECT * FROM contas WHERE id_usuario = ?";
            var preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, Sessao.getIdUsuario());
            
            var resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String nomeRemetente = resultSet.getString("endereco");
                String saldo = resultSet.getString("saldo");
                remetente.getItems().add("Hash: " + nomeRemetente + " - Saldo: IC$ " + saldo);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void irParaCarteira(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("carteira.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void irParaContato(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("contato.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void realizarTransferencia(ActionEvent event) {
        String remetenteSelecionado = remetente.getSelectionModel().getSelectedItem();

        String[] partes = remetenteSelecionado.split(" - ");
        String Remetente = partes[0].replace("Hash: ", "").trim();
        String Destinatario = destinatario.getText();

        System.out.println("Remetente: " + Remetente);
        System.out.println("Destinatário: " + Destinatario);


        if(Remetente.equals(Destinatario)) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Falha na transferência");
            alert.setHeaderText(null);
            alert.setContentText("Destinatário inválido!");
            alert.showAndWait();
        }
    }

}