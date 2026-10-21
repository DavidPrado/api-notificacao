package br.com.notificationhub.application.port.in;

import br.com.notificationhub.domain.Notificacao;

public interface CriarNotificacaoUseCase {

    Notificacao executar(String destinatario, String mensagem);
}
