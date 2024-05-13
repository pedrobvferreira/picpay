package com.challenge.picpay.service;

import com.challenge.picpay.domain.User;
import com.challenge.picpay.dto.NotificationDTO;
import com.challenge.picpay.exception.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NotificationService {
    private static final Logger LOGGER = LoggerFactory.getLogger(NotificationService.class);
    private static final String NAME = NotificationService.class.getSimpleName();

    @Autowired
    private RestTemplate restTemplate;

    @Value("${app.notificationApi}")
    private String notificationApiUrl;

    public void sendNotification(User user, String message) {
        String email = user.getEmail();
        NotificationDTO notificationRequest = new NotificationDTO(email, message);

        ResponseEntity<String> notificationResponse =
                restTemplate.postForEntity(
                        notificationApiUrl,
                        notificationRequest,
                        String.class);

        if(!(notificationResponse.getStatusCode() == HttpStatus.OK)){
            LOGGER.error("[{}].Erro ao enviar notificacao .", NAME);
            throw new BadRequestException("Serviço de notificação está fora do ar");
        }
        LOGGER.info("[{}].Notificacao enviada para o utilizador .", NAME);
    }
}
