package com.sjtudoit.chatgpt.result.rpc;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ImageGenerationRpcResult extends BaseRpcResult {
    private static final long serialVersionUID = 5796442735149618342L;

    /**
     * 图片地址
     */
    private String url;
}
