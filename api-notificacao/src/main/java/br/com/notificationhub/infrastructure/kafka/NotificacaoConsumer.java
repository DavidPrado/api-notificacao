package br.com.notificationhub.infrastructure.kafka;

import br.com.notificationhub.application.port.out.EmailSenderProvider;
import br.com.notificationhub.domain.Notificacao;
import br.com.notificationhub.domain.NotificacaoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class NotificacaoConsumer {

    private final NotificacaoRepository repository;
    private final EmailSenderProvider emailSenderProvider;

    @KafkaListener(topics = "notificacao-solicitada", groupId = "notification-group")
    public void consumir(Notificacao notificacao) {
        log.info("Recebido evento do Kafka para a notificação: {}", notificacao.getId());

        try{
            emailSenderProvider.enviar(notificacao.getDestinatario(), notificacao.getMensagem());

            notificacao.setStatus("ENVIADO");
            repository.save(notificacao);
            log.info("Notificação {} atualizada para ENVIADO", notificacao.getId());
        } catch (Exception e){
            log.error("Erro ao processar notificação {}: {}", notificacao.getId(), e.getMessage());
            notificacao.setTentativas(notificacao.getTentativas() + 1);
            notificacao.setStatus("FALHA");
            repository.save(notificacao);
        }
    }
}
