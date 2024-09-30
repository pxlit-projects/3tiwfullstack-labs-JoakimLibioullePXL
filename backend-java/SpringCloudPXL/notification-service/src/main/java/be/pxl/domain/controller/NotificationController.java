package be.pxl.domain.controller;

import be.pxl.domain.dto.NotificationRequest;
import be.pxl.domain.dto.NotificationResponse;
import be.pxl.services.INotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notification")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class NotificationController {
    private final INotificationService notificationService;

    // Fetch a notification by ID
    @GetMapping("/{id}")
    public ResponseEntity<NotificationResponse> getNotificationById(@PathVariable Long id) {
        try {
            NotificationResponse response = notificationService.GetById(id);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Add a new notification
    @PostMapping
    public ResponseEntity<Void> addNotification(@RequestBody NotificationRequest notificationRequest) {
        notificationService.add(notificationRequest);
        return ResponseEntity.ok().build();
    }

    // Update an existing notification
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateNotification(
            @PathVariable Long id,
            @RequestBody NotificationRequest notificationRequest) {
        try {
            notificationService.update(id, notificationRequest);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete a notification by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNotification(@PathVariable Long id) {
        try {
            notificationService.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
