package br.com.notificationhub.application.port.out;

import br.com.notificationhub.domain.Notificacao;

public interface NotificacaoEventPublisher {
    void publicar(Notificacao notificacao);
}
