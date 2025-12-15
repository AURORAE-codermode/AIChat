package com.zjgsu.aichat.service;

import com.zjgsu.aichat.model.ChatRoom;

import java.util.List;

public interface ChatService {
    //通过房间号与特定用户对话
    String dochat(Long roomId, String userPrompt);
    //获取当前所有的聊天房间对话
    List<ChatRoom> getChatRooms();



}
