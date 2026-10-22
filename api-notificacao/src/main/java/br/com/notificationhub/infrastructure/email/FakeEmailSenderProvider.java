package br.com.notificationhub.infrastructure.email;

import br.com.notificationhub.application.port.out.EmailSenderProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Random;

@Slf4j
@Component
public class FakeEmailSenderProvider implements EmailSenderProvider {

    private final Random random = new Random();

    @Override
    public void enviar(String destinatario, String mensagem) {

        log.info("Tentando enviar e-mail para: {}", destinatario);

        try{
            Thread.sleep(500);
        } catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }

        if (random.nextInt(100) < 30){
            log.error("Falha ao conectar com a API de E-mail (Simulação de Timeout)");
            throw new RuntimeException("Timeout na API externa de e-mail");
        }

        log.info("E-mail enviado com sucesso para: {}", destinatario);
    }
}
