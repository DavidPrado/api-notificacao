package br.com.notificationhub.api;

import br.com.notificationhub.application.dto.NotificacaoRequest;
import br.com.notificationhub.application.port.in.CriarNotificacaoUseCase;
import br.com.notificationhub.domain.Notificacao;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/notificacoes")
@RequiredArgsConstructor
public class NotificacaoController {

    private final CriarNotificacaoUseCase criarNotificacaoUseCase;

    @PostMapping
    public ResponseEntity<String> solicitar(@RequestBody @Valid NotificacaoRequest request){
        Notificacao notificacao = criarNotificacaoUseCase.executar(
                request.destinatario(),
                request.mensagem()
        );

        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body("Notificação agendada. ID: " + notificacao.getId());
    }
}
