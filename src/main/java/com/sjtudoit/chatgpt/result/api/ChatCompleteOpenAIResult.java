package com.sjtudoit.chatgpt.result.api;

import com.sjtudoit.chatgpt.model.ChoiceModel;
import com.sjtudoit.chatgpt.model.UsageModel;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class ChatCompleteOpenAIResult implements Serializable {

    private static final long serialVersionUID = 7032815366687222385L;

    /**
     * 对话ID
     */
    private String id;

    /**
     * object
     */
    private String object;

    /**
     * 对话时间
     */
    private long created;

    /**
     * 使用的模型
     */
    private String model;

    /**
     * 对话结果
     */
    private List<ChoiceModel> choices;

    /**
     * token使用情况
     */
    private UsageModel usage;
}
