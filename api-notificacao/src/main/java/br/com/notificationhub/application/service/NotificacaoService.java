package br.com.notificationhub.application.service;

import br.com.notificationhub.application.port.in.CriarNotificacaoUseCase;
import br.com.notificationhub.application.port.out.NotificacaoEventPublisher;
import br.com.notificationhub.domain.Notificacao;
import br.com.notificationhub.domain.NotificacaoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificacaoService implements CriarNotificacaoUseCase {

    private final NotificacaoRepository repository;
    private final NotificacaoEventPublisher eventPublisher;

    @Override
    @Transactional
    public Notificacao executar(String destinatario, String mensagem){
        log.info("Processando nova notificação para: {}", destinatario);

        Notificacao notificacao = Notificacao.builder()
                .id(UUID.randomUUID())
                .destinatario(destinatario)
                .mensagem(mensagem)
                .status("PENDENTE")
                .tentativas(0)
                .criadoEm(LocalDateTime.now())
                .build();

        repository.save(notificacao);
        eventPublisher.publicar(notificacao);
        log.info("Notificação {} salva e enviada para a fila.", notificacao.getId());

        return notificacao;
    }
}
