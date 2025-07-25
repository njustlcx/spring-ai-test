package com.lcx.springaidemo.controller;

import org.springframework.ai.embedding.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmbeddingController {

    private final EmbeddingModel embeddingModel;

    @Autowired
    public EmbeddingController(EmbeddingModel embeddingModel) {
        this.embeddingModel = embeddingModel;
    }

    @RequestMapping("/api/embedding/getembeddings")
    public float[] getEmbeddings(String message) {
        return embeddingModel.embed(message);
    }

}
