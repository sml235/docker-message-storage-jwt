package dev.sml.messagestorage.controllers;

import dev.sml.messagestorage.dto.MessageData;
import dev.sml.messagestorage.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MessageController {

    @Autowired
    MessageService messageService;

    @GetMapping("/messages")
    public ResponseEntity<List<MessageData>> getMessages() {
        return ResponseEntity.ok(messageService.getMessages());
    }

    @PostMapping("/messages")
    public ResponseEntity<List<MessageData>> postMessage(@RequestBody MessageData messageData) {
        return ResponseEntity.ok(messageService.postMessage(messageData));
    }

}
