package model;

public class ContatoExisteException extends Exception {

    public ContatoExisteException() {
        super("Contato já existe.");
    }

    public ContatoExisteException(String mensagem) {
        super(mensagem);
    }
}
