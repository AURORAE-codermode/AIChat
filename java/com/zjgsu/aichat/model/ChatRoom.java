package com.zjgsu.aichat.model;

import com.volcengine.ark.runtime.model.completion.chat.ChatMessage;
import lombok.Data;

import java.util.List;

@Data
public class ChatRoom {
    private long roomId;

    private List<ChatMessage> messages;

    public void setChatMessages(List<ChatMessage> messages) {
        this.messages = messages;
    }

}
