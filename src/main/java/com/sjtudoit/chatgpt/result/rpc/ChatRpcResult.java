package com.sjtudoit.chatgpt.result.rpc;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ChatRpcResult extends BaseRpcResult {
    private static final long serialVersionUID = 2847842520482069352L;

    /**
     * 对话ID
     */
    private String chatId;

    /**
     * 对话时间
     */
    private String chatTime;

    /**
     * 响应内容
     */
    private String responseText;
}
