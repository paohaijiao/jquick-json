package com.github.paohaijiao;

import com.github.paohaijiao.executor.JSONExecutor;
import com.github.paohaijiao.model.JsonResponse;
import com.paohaijiao.javelin.adaptor.JQuickAdaptor;
import com.paohaijiao.javelin.exception.JAntlrExecutionException;
import com.paohaijiao.javelin.param.JContext;
import com.paohaijiao.javelin.resource.JQuickReader;
import com.paohaijiao.javelin.resource.impl.JQuickReSourceFileReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        JContext jcontext = new JContext();
        jcontext.put("name", "paohaijiao");
        JSONExecutor executor = new JSONExecutor(jcontext);
        executor.addErrorListener(error -> {
        });
        try {
            JQuickReader fileReader = new JQuickReSourceFileReader("rule.txt");
            JQuickAdaptor context = new JQuickAdaptor(fileReader);
            System.out.println(context.getRuleContent());
            JsonResponse jsonObject = executor.execute(context.getRuleContent());
            System.out.println(jsonObject);
        } catch (JAntlrExecutionException e) {
            System.err.println("解析失败: " + e.getMessage());
            e.getErrors().forEach(err ->
                    System.err.println(" - " + err.getMessage()));
        }
    }

    public String readFileFromClasspath(String fileName) {
        StringBuilder result = new StringBuilder();
        try (InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(fileName);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result.toString();
    }
}
