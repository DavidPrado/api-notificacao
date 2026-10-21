package br.com.notificationhub.infrastructure.kafka;

import br.com.notificationhub.application.port.out.NotificacaoEventPublisher;
import br.com.notificationhub.domain.Notificacao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaEventPublisher implements NotificacaoEventPublisher {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private static final String TOPICO = "notificacao-solicitada";

    @Override
    public void publicar(Notificacao notificacao) {
        try {
            kafkaTemplate.send(TOPICO,notificacao.getId().toString(), notificacao);
            log.info("Mensagem enviada para o Kafka no tópico [{}]", TOPICO);
        } catch (Exception ex) {
            log.error("Falha crítica ao enviar para o Kafka", ex);
        }
    }
}
