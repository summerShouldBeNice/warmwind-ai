package top.warmwind.ai.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * @author warmwind
 * @since 2024-12-11 下午4:13
 */
@Service
public class TestService {

    TestService testService = new TestService();


    public List<String> getTodoList() {
        List<String> list = new ArrayList<String>();
        list.add("下班了拿快递");
        list.add("下班了买牛肉和香料");
        return list;
    }

    public record Message(String message){}

    @Bean
    @Description("获取个人待办列表")
    public Function<Message, String> getList() {
        return message -> {
            testService.getTodoList();
            return "操作成功";
        };
    }
}
