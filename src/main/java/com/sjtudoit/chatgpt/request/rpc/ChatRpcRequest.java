package com.sjtudoit.chatgpt.request.rpc;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ChatRpcRequest implements Serializable {
    private static final long serialVersionUID = 7129684260772959795L;

    /**
     * 聊天内容
     */
    private String content;
}
