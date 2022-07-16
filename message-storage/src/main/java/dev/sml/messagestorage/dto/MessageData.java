package dev.sml.messagestorage.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class MessageData {
    private String name;
    private String messageText;
}
