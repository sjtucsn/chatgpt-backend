package com.sjtudoit.chatgpt.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class UsageModel implements Serializable {
    private static final long serialVersionUID = 5033046091080682143L;

    /**
     * 输入单词数
     */
    @JSONField(name = "prompt_tokens")

    private int promptTokens;

    /**
     * 输出单词数
     */
    @JSONField(name = "completion_tokens")
    private int completionTokens;

    /**
     * 单词总数
     */
    @JSONField(name = "total_tokens")
    private int totalTokens;
}
