import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ControllerCadastro {

    @FXML
    private TextField cSenhaInput;

    @FXML
    private TextField senhaInput;

    @FXML
    private TextField usuarioInput;

    private Connection connectToDatabase() throws SQLException {
        String url = MySQL.getUrl();
        String user = MySQL.getUser();
        String password = MySQL.getPassword();

        return DriverManager.getConnection(url, user, password);
    }

    private boolean criarUsuario(String nomeUsuario, String senha) {
        String sqlInsertUsuario = "INSERT INTO usuarios (nome, senha) VALUES (?, ?)";
        String sqlInsertHistorico = "INSERT INTO historico (id_usuario, valor) VALUES (?, 0)";

        try (Connection conn = connectToDatabase();
             PreparedStatement stmtUsuario = conn.prepareStatement(sqlInsertUsuario, PreparedStatement.RETURN_GENERATED_KEYS);
             PreparedStatement stmtHistorico = conn.prepareStatement(sqlInsertHistorico)) {

            // Inserir o usuário na tabela "usuarios"
            stmtUsuario.setString(1, nomeUsuario);
            stmtUsuario.setString(2, senha);
            int rowsAffected = stmtUsuario.executeUpdate();

            if (rowsAffected > 0) {
                // Obter o ID gerado para o novo usuário
                try (ResultSet generatedKeys = stmtUsuario.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int idUsuario = generatedKeys.getInt(1);

                        // Inserir registro na tabela "historico"
                        stmtHistorico.setInt(1, idUsuario);
                        stmtHistorico.executeUpdate();
                    }
                }
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @FXML
    void redirecionarLogin(ActionEvent event) {
        try {
            Parent novaTela = FXMLLoader.load(getClass().getResource("login.fxml"));
            Scene novaCena = new Scene(novaTela);
            Stage palco = (Stage) ((Node) event.getSource()).getScene().getWindow();
            palco.setScene(novaCena);
            palco.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void cadastrar(ActionEvent event) {
        String nomeUsuario = usuarioInput.getText();
        String senha = senhaInput.getText();

        boolean sucesso = criarUsuario(nomeUsuario, senha);

        if (sucesso) {
            redirecionarLogin(event);
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erro de Cadastro");
            alert.setHeaderText(null);
            alert.setContentText("Erro ao cadastrar o usuário.");
            alert.showAndWait();
        }
    }
}