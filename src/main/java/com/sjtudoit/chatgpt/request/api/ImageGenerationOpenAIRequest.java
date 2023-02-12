package com.sjtudoit.chatgpt.request.api;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ImageGenerationOpenAIRequest implements Serializable {
    private static final long serialVersionUID = -611544184249111456L;

    /**
     * 提词
     */
    private String prompt;

    /**
     * 图片尺寸
     */
    private String size;
}
