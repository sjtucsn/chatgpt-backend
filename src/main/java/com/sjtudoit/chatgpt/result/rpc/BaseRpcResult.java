package com.sjtudoit.chatgpt.result.rpc;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class BaseRpcResult implements Serializable {
    private static final long serialVersionUID = -5346792333313269172L;

    /**
     * 是否成功
     */
    private boolean success;
}
