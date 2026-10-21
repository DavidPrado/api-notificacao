package br.com.notificationhub.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record NotificacaoRequest(
        @NotBlank(message = "O destinatário é obrigatório")
        String destinatario,

        @NotBlank(message = "A mensagem é obrigatória")
        @Size(max = 500, message = "A mensagem não pode passar de 500 caracteres")
        String mensagem
) {
}
