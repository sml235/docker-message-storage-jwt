package dev.sml.messagestorage.service;

import dev.sml.messagestorage.dto.MessageData;
import dev.sml.messagestorage.entities.MessageModel;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class MessageConverter implements Converter<MessageModel, MessageData> {
    @Override
    public MessageData convert(MessageModel source) {
        MessageData target = new MessageData();
        target.setName(source.getUser().getName());
        target.setMessageText(source.getMessageText());
        return target;
    }
}
