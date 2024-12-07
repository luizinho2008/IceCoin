import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ControllerLogin {

    @FXML
    private PasswordField senhaInput;

    @FXML
    private TextField usuarioInput;

    private Connection connectToDatabase() throws SQLException {
        String url = MySQL.getUrl();
        String user = MySQL.getUser();
        String password = MySQL.getPassword();
    
        return DriverManager.getConnection(url, user, password);
    }

    private boolean verificarLogin(String nomeUsuario, String senha) {
        String sql = "SELECT * FROM usuarios WHERE nome = ? AND senha = ?";
        
        try (Connection conn = connectToDatabase();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
              stmt.setString(1, nomeUsuario);
              stmt.setString(2, senha);
    
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Sessao.setNomeUsuario(rs.getString("nome"));
                Sessao.setIdUsuario(rs.getInt("id_usuario"));
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }    

    @FXML
    void redirecionarBanco(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("carteira.fxml"));
            Parent novaTela = loader.load();
            Scene novaCena = new Scene(novaTela);
            Stage palco = (Stage) ((Node) event.getSource()).getScene().getWindow();
            palco.setScene(novaCena);
            palco.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void redirecionarCadastro(ActionEvent event) {
        try {
            Parent novaTela = FXMLLoader.load(getClass().getResource("cadastro.fxml"));
            Scene novaCena = new Scene(novaTela);
            Stage palco = (Stage) ((Node) event.getSource()).getScene().getWindow();
            palco.setScene(novaCena);
            palco.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void logar(ActionEvent event) {
        String nomeUsuario = usuarioInput.getText();  
        String senha = senhaInput.getText();  

        boolean loginAutenticado = verificarLogin(nomeUsuario, senha);

        if (loginAutenticado) {

            redirecionarBanco(event);
            
            System.out.println("ID: " + Sessao.getIdUsuario());
            System.out.println("Nome: " + Sessao.getNomeUsuario());
        } else {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("IceCoin informa");
            alert.setHeaderText(null);
            alert.setContentText("Usuário ou senha incorretos!");
            alert.showAndWait();
        }
    }
}