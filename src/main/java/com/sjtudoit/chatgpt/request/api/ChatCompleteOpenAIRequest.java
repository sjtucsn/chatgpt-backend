package com.sjtudoit.chatgpt.request.api;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ChatCompleteOpenAIRequest implements Serializable {

    private static final long serialVersionUID = 4837623801608870198L;

    /**
     * 语言模型
     */
    private String model;

    /**
     * 输入对话内容
     */
    private String prompt;

    /**
     * 最多返回单词数
     */
    @JSONField(name = "max_tokens")
    private int maxTokens;

    /**
     * What sampling temperature to use, between 0 and 2. Higher values like 0.8 will make the output more random,
     * while lower values like 0.2 will make it more focused and deterministic.
     */
    private double temperature;
}
