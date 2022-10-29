package com.example.Socket_Simple_Prac_06.controller;

import com.example.Socket_Simple_Prac_06.model.ChatMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class MessageController {

    private final SimpMessageSendingOperations sendingOperations;

    @MessageMapping("/chat/message")
    public void enter(ChatMessage message) {
        if (ChatMessage.MessageType.ENTER.equals(message.getType())) {
            message.setMessage(message.getSender()+"님이 입장하였습니다.");
        }
        sendingOperations.convertAndSend("/topic/chat/room/"+message.getRoomId(),message);
    }
}


//@RestController
//@RequiredArgsConstructor
//public class MessageController {
//
//    private final SimpMessageSendingOperations sendingOperations;
//
//    //STOMP client의 send에 대한 target url입니다.
//    @MessageMapping("/hello")
//    // @MessageMapping의 경로가 "/chat/message"이지만
//    // ChatConfig의 setApplicationDestinationPrefixes()를 통해 prefix를 "/app"으로 해줬기 때문에
//    // 실제 경로는 "/app/chat/message"
//    // 클라이언트에서 /app/chat/message 로 보내면 컨트롤러가
//    // /queue/chat/room/{roomid}를 구독하는 클라이언트에게 보냄
//    public void message(Message message) {
//        sendingOperations.convertAndSend("/sub/channel/" + message.getChannelId(), message);
//    }
//}