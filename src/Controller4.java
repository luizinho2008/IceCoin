import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class Controller4 {

    @FXML
    private Label chamada;

    
    @FXML
    public void receberTexto(String user) {
        chamada.setText("Olá " + user + "!"); 
    }
    
}
