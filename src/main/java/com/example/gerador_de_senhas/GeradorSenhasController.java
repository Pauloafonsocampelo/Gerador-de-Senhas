package com.example.gerador_de_senhas;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;

import java.security.SecureRandom;

public class GeradorSenhasController {

    @FXML private CheckBox chkLETRAS;
    @FXML private CheckBox chkletras;
    @FXML private CheckBox chkNumeros;
    @FXML private CheckBox chkSimbolos;
    @FXML private Button btnGerar;
    @FXML private Button btnCopiar;
    @FXML private TextField Camposenha;
    @FXML private TextField TamanhoSenha;

    private final SecureRandom aleatorio = new SecureRandom();

    private static final String MINUSCULAS = "abcdefghijhlmnopqrstuvwxyz";
    private static final String MAIUSCULAS = "abcdefghijklmnopqrstuvwxyz".toUpperCase();
    private static final String NUMEROS = "0123456789";
    private static final String SIMBOLOS = "!@#$%¨&*()<>^~?[]+=-";

    @FXML
    public void initialize(){
        btnGerar.setOnAction(e -> gerarSenha());
        btnCopiar.setOnAction(e -> copiarSenha());

        TextFormatter<Integer> somenteNumeros = new TextFormatter<>(
                change -> {
                    String novoTexto = change.getControlNewText();
                    if (novoTexto.matches("\\d*")) {
                        return change; // aceita só números
                    }
                    return null; // ignora o que não for número
                }
        );
        TamanhoSenha.setTextFormatter(somenteNumeros);
    }

    public void gerarSenha () {
        int tamanho = 0;
        try {
            tamanho = Integer.parseInt(TamanhoSenha.getText());
            if(tamanho < 8) {

                Camposenha.setText("A senha precisa ter no mínimo 8 caractére!");
                return;
            }
        } catch (NumberFormatException e) {
            // valor inválido, define um padrão
            tamanho = 8; // ou mostra erro
        }
        StringBuilder conjunto = new StringBuilder();


        if(chkletras.isSelected()) {
            conjunto.append(MINUSCULAS);
        }

        if(chkLETRAS.isSelected()) {
            conjunto.append(MAIUSCULAS);
        }

        if(chkNumeros.isSelected()) {
            conjunto.append(NUMEROS);
        }

        if(chkSimbolos.isSelected()) {
            conjunto.append(SIMBOLOS);
        }

        if(conjunto.length() == 0) {
            Camposenha.setText("Selecione ao menos uma opção!");
            return;
        }

        StringBuilder senha = new StringBuilder();
        for (int i = 0; i < tamanho; i++) {
            int indice = aleatorio.nextInt(conjunto.length());
            senha.append(conjunto.charAt(indice));
        }

        Camposenha.setText(senha.toString());

    }

    private void copiarSenha() {
        String senha = Camposenha.getText();
        if(senha != null && !senha.isEmpty()) {
            ClipboardContent conteudo = new ClipboardContent();
            conteudo.putString(senha);
            Clipboard.getSystemClipboard().setContent(conteudo);
        }
    }
}
