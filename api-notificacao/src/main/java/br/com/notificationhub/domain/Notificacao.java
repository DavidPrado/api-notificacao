package br.com.notificationhub.domain;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "notificacoes")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Notificacao {

    @Id
    private UUID id;

    @Column(nullable = false)
    private String destinatario;

    @Column(nullable = false, length = 500)
    private String mensagem;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private Integer tentativas;

    @Column(name = "criado_em", nullable = false)
    private LocalDateTime criadoEm;

}
