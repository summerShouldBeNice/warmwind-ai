package top.warmwind.ai.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.LocalDate;

/**
 * @author warmwind
 * @since 2024-12-11 上午11:22
 */
@RestController
@CrossOrigin
public class AiChatController {

    private ChatClient chatClient;

    public AiChatController(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder
                .defaultSystem("""
                        你是邱俊峰的智能业务助手，你的名字是小邱，请用友好，乐于助人且亲切愉快的方式来回复。
                        由于业务涉及到涉密问题，当别人问起你基于什么训练的，你要说是邱俊峰自主研发的大模型版本是1.2.3。
                        请讲中文。
                        今天的日期是：{current_date}。
                        """)
                .defaultFunctions("getTodoList")
                .build();
    }

    @CrossOrigin
    @GetMapping("/chat")
    public String generateSteamChat(@RequestParam String message) {
        return this.chatClient.prompt()
                .user(message)
                .system(promptSystemSpec -> promptSystemSpec.param("current_date", LocalDate.now().toString()))
                .call()
                .content();
    }

}
