package com.sjtudoit.chatgpt.result.api;

import com.sjtudoit.chatgpt.model.UrlModel;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class ImageGenerationOpenAIResult implements Serializable {
    private static final long serialVersionUID = -9186864098249591586L;

    private long created;

    private List<UrlModel> data;
}
