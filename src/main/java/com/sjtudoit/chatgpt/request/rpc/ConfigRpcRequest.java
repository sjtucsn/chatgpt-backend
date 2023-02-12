package com.sjtudoit.chatgpt.request.rpc;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ConfigRpcRequest implements Serializable {
    private static final long serialVersionUID = -3064078111476103342L;

    private String authorization = "Bearer ${secretKey}";

    private String modelName = "text-davinci-003";

    private int maxTokens = 1000;

    private double temperature = 1;

    private String size = "512x512";
}
