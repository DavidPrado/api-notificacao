package br.com.notificationhub.application.port.out;

public interface EmailSenderProvider {
    void enviar(String destinatario, String mensagem);
}
