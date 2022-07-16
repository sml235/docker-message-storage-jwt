package dev.sml.messagestorage.service;

import dev.sml.messagestorage.dto.MessageData;
import dev.sml.messagestorage.entities.MessageModel;
import dev.sml.messagestorage.entities.UserModel;
import dev.sml.messagestorage.repository.MessageRepository;
import dev.sml.messagestorage.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class MessageService {
    public static final String HISTORY = "history ";
    @Autowired
    MessageRepository messageRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    MessageConverter converter;

    public List<MessageData> getMessages() {
        return StreamSupport.stream(messageRepository.findAll().spliterator(), false)
                .map(converter::convert)
                .collect(Collectors.toList());
    }

    public List<MessageData> postMessage(MessageData messageData) {
        String messageText = messageData.getMessageText();
        String name = messageData.getName();
        if (messageText.startsWith(HISTORY)) {
            return getHistoryData(messageText);
        }
        return saveMessage(messageText, name);
    }

    private List<MessageData> saveMessage(String messageText, String name) {
        UserModel user = userRepository.findUserByName(name);
        MessageModel message = new MessageModel();
        message.setUser(user);
        message.setMessageText(messageText);
        messageRepository.save(message);
        return List.of(Objects.requireNonNull(converter.convert(message)));
    }

    private List<MessageData> getHistoryData(String messageText) {
        List<MessageData> messages = new ArrayList<>();
        String[] params = messageText.split(" ");
        int count = Integer.parseInt(params[1]);
//        messageRepository.findAllByUserName(name,     уточнил ТЗ, нужны любые 10 сообщений
        messageRepository.findAll(
                        PageRequest.of(0, count, Sort.by(Sort.Direction.DESC, "id")))
                .map(converter::convert)
                .forEach(messages::add);
        return messages;
    }


}
