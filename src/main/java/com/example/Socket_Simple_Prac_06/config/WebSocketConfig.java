package com.example.Socket_Simple_Prac_06.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;


@RequiredArgsConstructor
@Configuration
// 설정파일을 선언하며 여러개의 빈 등록을 가능하도록 만들어준다
@EnableWebSocketMessageBroker
// 웹 소켓 서버를 사용하가위한 어노테이션
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
                    // 상속받은 인터페이스에서 제공하는 몇몇 메소드를 구현하여 웹소켓 연결속성을 지정한다.
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws/chat")
                // sockJs 클라이언트가 웹 소켓 핸드쉐이크를 하기 위해 연결할 엔드포인트 지정
                .setAllowedOriginPatterns("*")
                // CORS 모두 열어주기 위해 사용함
                .withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {

        registry.enableSimpleBroker("/queue");
        // 1대1 채팅만 사용하기 때문에 /topic 경로는 없음

        registry.setApplicationDestinationPrefixes("/app");
        // 클라이언트가 메시지를 보낼 때 경로 맨 앞에 /app이 붙으면 broker로 보내짐
    }

//      //클론코딩때 사용한 코드로 우선 주석처리
//    private final StompHandler stompHandler;
//    @Override
//    public void configureClientInboundChannel(ChannelRegistration registration) {
//        registration.interceptors(stompHandler);
//        // http 세션과 websocket 세션이 다르기 때문에 서로의 데이터에 접근할수 없어 인터센터 추가
//    }
}