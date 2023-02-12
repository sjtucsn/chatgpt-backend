package com.sjtudoit.chatgpt.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ChoiceModel implements Serializable {
    private static final long serialVersionUID = 7181535564710833917L;

    /**
     * 输入文本 \n分隔
     */
    private String text;

    /**
     * 索引
     */
    private int index;

    /**
     * 结束原因 stop/length
     */
    @JSONField(name = "finish_reason")

    private String finishReason;
}
