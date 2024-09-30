package be.pxl.services;

import be.pxl.domain.dto.NotificationRequest;
import be.pxl.domain.dto.NotificationResponse;

public interface INotificationService {
    NotificationResponse GetById(Long id) throws Exception;
    void add(NotificationRequest notificationRequest);
    void deleteById(Long id) throws Exception;
    void update(Long id, NotificationRequest notificationRequest) throws Exception;
}
