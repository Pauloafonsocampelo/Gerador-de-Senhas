module com.example.gerador_de_senhas {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.gerador_de_senhas to javafx.fxml;
    exports com.example.gerador_de_senhas;
}