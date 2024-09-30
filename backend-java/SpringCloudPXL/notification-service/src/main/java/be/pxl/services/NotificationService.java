package be.pxl.services;

import be.pxl.domain.Notification;
import be.pxl.domain.dto.NotificationRequest;
import be.pxl.domain.dto.NotificationResponse;
import be.pxl.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService implements INotificationService{
    private final NotificationRepository notificationRepository;
    @Override
    public NotificationResponse GetById(Long id) throws Exception {
        Notification notification = notificationRepository.findById(id).orElseThrow(() -> new Exception("Notification with ID:" + id + " doesn't exist."));
        return mapToNotificationResponse(notification);
    }

    @Override
    public void add(NotificationRequest notificationRequest) {
        notificationRepository.save(mapToNotification(notificationRequest));
    }

    @Override
    public void deleteById(Long id) throws Exception {
        if (notificationRepository.existsById(id)) {
            notificationRepository.deleteById(id);
        } else {
            throw new Exception("Notification not found with id " + id);
        }
    }

    @Override
    public void update(Long id, NotificationRequest notificationRequest) throws Exception {
        Notification notification = notificationRepository.findById(id).orElseThrow(() -> new Exception("Notification not found with id " + id));

        // Update de velden van het bestaande object
        notification.setFrom(notificationRequest.getFrom());
        notification.setTo(notificationRequest.getTo());
        notification.setMessage(notificationRequest.getMessage());
        notification.setSubject(notificationRequest.getSubject());

        // Sla het bijgewerkte object op
        notificationRepository.save(notification);
    }

    private NotificationResponse mapToNotificationResponse(Notification notification){
        return NotificationResponse.builder()
                .from(notification.getFrom())
                .to(notification.getTo())
                .message(notification.getMessage())
                .subject(notification.getSubject())
                .build();
    }

    private Notification mapToNotification(NotificationRequest notificationRequest){
        return Notification.builder()
                .from(notificationRequest.getFrom())
                .to(notificationRequest.getTo())
                .message(notificationRequest.getMessage())
                .subject(notificationRequest.getSubject())
                .build();
    }
}
