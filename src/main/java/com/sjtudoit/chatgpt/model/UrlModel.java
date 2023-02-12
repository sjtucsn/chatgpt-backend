package com.sjtudoit.chatgpt.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class UrlModel implements Serializable {
    private static final long serialVersionUID = -640876872469747239L;

    /**
     * url
     */
    private String url;
}
