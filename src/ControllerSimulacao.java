import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ControllerSimulacao {

    @FXML
    private Label converterValor;

    @FXML
    private TextField valorReal;

    @FXML
    void converter(ActionEvent event) {

        String valor = valorReal.getText();

        try {
            Double convertido = Double.parseDouble(valor);
            
            Double valorMoeda = IceCoin.getValor();

            convertido = convertido / valorMoeda;

            valor = String.valueOf(convertido);

            converterValor.setText(valor);

        } catch (NumberFormatException e) {
            System.out.println("Erro: O texto inserido não é um número válido.");
        }

    }

}
