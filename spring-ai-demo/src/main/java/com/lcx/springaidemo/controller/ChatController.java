package com.lcx.springaidemo.controller;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.deepseek.DeepSeekChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Map;

@RestController
public class ChatController {
    private final ChatModel chatModel;

    @Autowired
    public ChatController(DeepSeekChatModel chatModel) {
        this.chatModel = chatModel;
    }

    @RequestMapping("/api/chat/deepseek/generate")
    public String generate(@RequestParam(value = "message") String message) {
        return chatModel.call(message);
    }

    @RequestMapping(value = "/api/chat/deepseek/generateStream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> generateStream(@RequestParam(value = "message") String message) {
        Flux<String> stringFlux = chatModel.stream(message);
        // 在控制台打印流失输出结果
        stringFlux.subscribe(System.out::print);
        return stringFlux;
    }
}
