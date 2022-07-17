package dev.sml.messagestorage.service;

import dev.sml.messagestorage.dto.MessageData;
import dev.sml.messagestorage.entities.MessageModel;
import dev.sml.messagestorage.entities.UserModel;
import dev.sml.messagestorage.repository.MessageRepository;
import dev.sml.messagestorage.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MessageServiceTest {

    public static final String HISTORY = "history 1";
    public static final String MESSAGE = "message";


    @InjectMocks
    private MessageService messageService;

    @Mock
    private MessageRepository messageRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private MessageConverter converter;
    private final MessageData messageData = mock(MessageData.class);
    private final MessageModel messageModel = mock(MessageModel.class);
    private final UserModel user = mock(UserModel.class);

    @Test
    public void testGetMessages() {

        List<MessageData> messages = List.of(messageData);
        List<MessageModel> models = List.of(messageModel);

        when(messageRepository.findAll()).thenReturn(models);
        when(converter.convert(messageModel)).thenReturn(messageData);

        List<MessageData> actualResult = messageService.getMessages();
        assertEquals(messages, actualResult);
    }

    @Test
    public void testPostMessages() {
        when(messageData.getMessageText()).thenReturn(MESSAGE);
        when(userRepository.findUserByName(any())).thenReturn(user);
        when(converter.convert(any(MessageModel.class))).thenReturn(messageData);

        messageService.postMessage(messageData);
        verify(messageRepository).save(any(MessageModel.class));
    }

    @Test
    public void testPostMessagesHistory() {
        List<MessageData> messages = List.of(messageData);
        when(messageData.getMessageText()).thenReturn(HISTORY);
        when(messageRepository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(List.of(messageModel)));
        when(converter.convert(any(MessageModel.class))).thenReturn(messageData);

        List<MessageData> actualResult = messageService.postMessage(messageData);
        assertEquals(messages, actualResult);
    }


    @Test(expected = NullPointerException.class)
    public void testPostMessagesWithoutText() {
        messageService.postMessage(messageData);
    }
}