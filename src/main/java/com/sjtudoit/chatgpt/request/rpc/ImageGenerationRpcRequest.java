package com.sjtudoit.chatgpt.request.rpc;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ImageGenerationRpcRequest implements Serializable {
    private static final long serialVersionUID = -42117906734876262L;

    /**
     * prompt
     */
    private String prompt;
}
