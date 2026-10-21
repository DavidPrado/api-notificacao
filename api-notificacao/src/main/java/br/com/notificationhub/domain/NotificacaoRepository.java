package br.com.notificationhub.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface NotificacaoRepository  extends JpaRepository<Notificacao, UUID> {
}
