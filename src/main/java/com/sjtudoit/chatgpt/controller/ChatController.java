package com.sjtudoit.chatgpt.controller;

import com.alibaba.fastjson.JSON;
import com.sjtudoit.chatgpt.request.api.ChatCompleteOpenAIRequest;
import com.sjtudoit.chatgpt.request.api.ImageGenerationOpenAIRequest;
import com.sjtudoit.chatgpt.request.rpc.ChatRpcRequest;
import com.sjtudoit.chatgpt.request.rpc.ConfigRpcRequest;
import com.sjtudoit.chatgpt.request.rpc.ImageGenerationRpcRequest;
import com.sjtudoit.chatgpt.result.api.ChatCompleteOpenAIResult;
import com.sjtudoit.chatgpt.result.api.ImageGenerationOpenAIResult;
import com.sjtudoit.chatgpt.result.rpc.BaseRpcResult;
import com.sjtudoit.chatgpt.result.rpc.ChatRpcResult;
import com.sjtudoit.chatgpt.result.rpc.ImageGenerationRpcResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@RestController
public class ChatController {

    private static String authorization = "Bearer ${secretKey}";

    private static String modelName = "text-davinci-003";

    private static int maxTokens = 1000;

    private static double temperature = 1;

    private static String size = "512x512";

    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/chat/completion")
    public ChatRpcResult chat(@RequestBody ChatRpcRequest request) {

        ChatRpcResult chatRpcResult = new ChatRpcResult();
        chatRpcResult.setSuccess(true);

        ChatCompleteOpenAIRequest openAIRequest = buildOpenAIRequest(request);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", authorization);
        HttpEntity<ChatCompleteOpenAIRequest> entity = new HttpEntity<>(openAIRequest, headers);

        System.out.println(LocalDateTime.now().format(dateTimeFormatter) + " request:" + JSON.toJSONString(openAIRequest));

        try {
            ResponseEntity<ChatCompleteOpenAIResult> responseEntity = restTemplate.postForEntity("https://api.openai.com/v1/completions", entity, ChatCompleteOpenAIResult.class);
            ChatCompleteOpenAIResult openAIResult = responseEntity.getBody();

            System.out.println(LocalDateTime.now().format(dateTimeFormatter) + " response:" + JSON.toJSONString(openAIResult));
            fillResult(chatRpcResult, openAIResult);
        } catch (Exception e) {
            chatRpcResult.setSuccess(false);
        }

        return chatRpcResult;
    }

    @PostMapping("/chat/image/generation")
    public ImageGenerationRpcResult imageGeneration(@RequestBody ImageGenerationRpcRequest request) {

        ImageGenerationRpcResult imageGenerationRpcResult = new ImageGenerationRpcResult();
        imageGenerationRpcResult.setSuccess(true);

        ImageGenerationOpenAIRequest openAIRequest = new ImageGenerationOpenAIRequest();
        openAIRequest.setPrompt(request.getPrompt());
        openAIRequest.setSize(size);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", authorization);
        HttpEntity<ImageGenerationOpenAIRequest> entity = new HttpEntity<>(openAIRequest, headers);

        System.out.println(LocalDateTime.now().format(dateTimeFormatter) + " request:" + JSON.toJSONString(openAIRequest));

        try {
            ResponseEntity<ImageGenerationOpenAIResult> responseEntity = restTemplate.postForEntity("https://api.openai.com/v1/images/generations", entity, ImageGenerationOpenAIResult.class);
            ImageGenerationOpenAIResult openAIResult = responseEntity.getBody();

            System.out.println(LocalDateTime.now().format(dateTimeFormatter) + " response:" + JSON.toJSONString(openAIResult));

            if (openAIResult == null) {
                imageGenerationRpcResult.setSuccess(false);
            } else {
                imageGenerationRpcResult.setUrl(openAIResult.getData().get(0).getUrl());
            }
        } catch (Exception e) {
            imageGenerationRpcResult.setSuccess(false);
        }

        return imageGenerationRpcResult;
    }

    @PostMapping("/chat/config/update")
    public BaseRpcResult setConfig(@RequestBody ConfigRpcRequest configRpcRequest) {
        System.out.println("更新配置: " + JSON.toJSONString(configRpcRequest));

        authorization = configRpcRequest.getAuthorization();
        modelName = configRpcRequest.getModelName();
        maxTokens = configRpcRequest.getMaxTokens();
        temperature = configRpcRequest.getTemperature();
        size = configRpcRequest.getSize();

        BaseRpcResult baseRpcResult = new BaseRpcResult();
        baseRpcResult.setSuccess(true);
        return baseRpcResult;
    }

    private ChatCompleteOpenAIRequest buildOpenAIRequest(ChatRpcRequest request) {
        ChatCompleteOpenAIRequest openAIRequest = new ChatCompleteOpenAIRequest();
        openAIRequest.setModel(modelName);
        openAIRequest.setMaxTokens(maxTokens);
        openAIRequest.setTemperature(temperature);
        openAIRequest.setPrompt(request.getContent());

        return openAIRequest;
    }

    private void fillResult(ChatRpcResult chatRpcResult, ChatCompleteOpenAIResult openAIResult) {
        if (openAIResult == null) {
            chatRpcResult.setSuccess(false);
            return;
        }
        chatRpcResult.setChatId(openAIResult.getId());
        chatRpcResult.setChatTime(LocalDateTime.ofInstant(Instant.ofEpochSecond(openAIResult.getCreated()),
                ZoneId.systemDefault()).format(dateTimeFormatter));

        if (CollectionUtils.isEmpty(openAIResult.getChoices())) {
            chatRpcResult.setResponseText("system error, please waiting");
        } else {
            chatRpcResult.setResponseText(StringUtils.trimLeadingWhitespace(openAIResult.getChoices().get(0).getText()));
        }
    }
}
