// java
package com.zjgsu.aichat.service;

import com.volcengine.ark.runtime.model.completion.chat.ChatCompletionRequest;
import com.volcengine.ark.runtime.model.completion.chat.ChatMessage;
import com.volcengine.ark.runtime.model.completion.chat.ChatMessageRole;
import com.volcengine.ark.runtime.service.ArkService;
import lombok.var;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AiManage {
    private final ArkService arkService;

    public AiManage(ArkService arkService) {
        this.arkService = arkService;
    }

    public String doChat(String systemPrompt, String userPrompt) {
        if (arkService == null) {
            throw new IllegalStateException("ArkService 未注入");
        }

        List<ChatMessage> messages = new ArrayList<>();
        messages.add(ChatMessage.builder().role(ChatMessageRole.SYSTEM).content(systemPrompt).build());
        messages.add(ChatMessage.builder().role(ChatMessageRole.USER).content(userPrompt).build());

        return doChat(messages);

//        ChatCompletionRequest chatCompletionRequest = ChatCompletionRequest.builder()
//                .model("ep-20251201203401-6pxj5")
//                .messages(messages)
//                .build();
//
//        var response = arkService.createChatCompletion(chatCompletionRequest);
//        if (response == null || response.getChoices() == null) {
//            return "no answer from AI";
//        }
//
//        arkService.shutdownExecutor();
//
//        for (var choice : response.getChoices()) {
//            if (choice != null && choice.getMessage() != null) {
//                String content = (String) choice.getMessage().getContent();
//                if (content != null && !content.trim().isEmpty()) {
//                    return content;
//                }
//            }
//        }
//
//        return "";

    }



    public String doChat(List<ChatMessage> messages){
        if (messages == null || messages.size() == 0) {
            return "消息列表为空，无法生成回复";
        }

        ChatCompletionRequest chatCompletionRequest = ChatCompletionRequest.builder()
                .model("ep-20251201203401-6pxj5")
                .messages(messages)
                .build();

        var response = arkService.createChatCompletion(chatCompletionRequest);
        if (response == null || response.getChoices() == null) {
            return "no answer from AI";
        }

        arkService.shutdownExecutor();

        for (var choice : response.getChoices()) {
            if (choice != null && choice.getMessage() != null) {
                String content = (String) choice.getMessage().getContent();
                if (content != null && !content.trim().isEmpty()) {
                    return content;
                }
            }
        }

        return "";

    }
}


