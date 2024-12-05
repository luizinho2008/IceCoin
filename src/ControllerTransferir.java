import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
        String password = "";
    
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

        if (remetenteSelecionado == null || remetenteSelecionado.isEmpty() || destinatario.getText().isEmpty() || quantidade.getText().isEmpty()) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Falha na transferência");
            alert.setHeaderText(null);
            alert.setContentText("Preencha todos os campos corretamente!");
            alert.showAndWait();
            return;
        }

        String[] partes = remetenteSelecionado.split(" - ");
        String remetenteHash = partes[0].replace("Hash: ", "").trim();
        String destinatarioHash = destinatario.getText().trim();
        double quantidadeTransferencia;

        try {
            quantidadeTransferencia = Double.parseDouble(quantidade.getText());
            if (quantidadeTransferencia <= 0) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Erro");
            alert.setHeaderText(null);
            alert.setContentText("Informe uma quantidade válida!");
            alert.showAndWait();
            return;
        }

        if (remetenteHash.equals(destinatarioHash)) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Falha na transferência");
            alert.setHeaderText(null);
            alert.setContentText("O destinatário não pode ser o mesmo que o remetente!");
            alert.showAndWait();
            return;
        }

        try (Connection connection = connectToDatabase()) {
            connection.setAutoCommit(false);

            String debitarQuery = "UPDATE contas SET saldo = saldo - ? WHERE endereco = ? AND saldo >= ?";
            var debitarStmt = connection.prepareStatement(debitarQuery);
            debitarStmt.setDouble(1, quantidadeTransferencia);
            debitarStmt.setString(2, remetenteHash);
            debitarStmt.setDouble(3, quantidadeTransferencia);
            int rowsUpdated = debitarStmt.executeUpdate();

            if (rowsUpdated == 0) {
                connection.rollback();
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Falha na transferência");
                alert.setHeaderText(null);
                alert.setContentText("Saldo insuficiente para realizar a transferência!");
                alert.showAndWait();
                return;
            }

            String creditarQuery = "UPDATE contas SET saldo = saldo + ? WHERE endereco = ?";
            var creditarStmt = connection.prepareStatement(creditarQuery);
            creditarStmt.setDouble(1, quantidadeTransferencia);
            creditarStmt.setString(2, destinatarioHash);
            rowsUpdated = creditarStmt.executeUpdate();

            connection.commit();
            
            String sql = "INSERT INTO transacoes(enderecoRemetente, enderecoDestinatario, valor) VALUES (?, ?, ?)";
        
            try (Connection conn = connectToDatabase();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
                
                stmt.setString(1, remetenteHash);
                stmt.setString(2, destinatarioHash);
                stmt.setDouble(3, quantidadeTransferencia);
                
                stmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Transferência concluída");
            alert.setHeaderText(null);
            alert.setContentText("Transferência realizada com sucesso!");
            alert.showAndWait();

            try {
                Parent novaTela = FXMLLoader.load(getClass().getResource("carteira.fxml"));
                Scene novaCena = new Scene(novaTela);
                Stage palco = (Stage) ((Node) event.getSource()).getScene().getWindow();
                palco.setScene(novaCena);
                palco.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText(null);
            alert.setContentText("Ocorreu um erro ao realizar a transferência. Tente novamente.");
            alert.showAndWait();
        }
    }
}