//package com.jquick.test;
//
//import com.github.paohaijiao.executor.JSONExecutor;
//import com.github.paohaijiao.factory.JSONSerializerFactory;
//import com.github.paohaijiao.model.JUser;
//import com.github.paohaijiao.model.JsonResponse;
//import com.github.paohaijiao.serializer.JSONSerializer;
//import com.paohaijiao.javelin.adaptor.JQuickAdaptor;
//import com.paohaijiao.javelin.exception.JAntlrExecutionException;
//import com.paohaijiao.javelin.param.JContext;
//import com.paohaijiao.javelin.resource.JQuickReader;
//import com.paohaijiao.javelin.resource.impl.JQuickReSourceFileReader;
//import org.junit.Ignore;
//import org.junit.Test;
//
//import java.io.IOException;
//import java.util.List;
//
//public class JQuickJSonTest {
//
//    @Test
//    @Ignore
//    public void test1() throws IOException {
//        JContext jcontext = new JContext();
//        jcontext.put("name", "paohaijiao");
//        JSONExecutor executor = new JSONExecutor(jcontext);
//        executor.addErrorListener(error -> {
//        });
//        try {
//            JQuickReader fileReader = new JQuickReSourceFileReader("rule.txt");
//            JQuickAdaptor context = new JQuickAdaptor(fileReader);
//            System.out.println(context.getRuleContent());
//            JsonResponse jsonObject = executor.execute(context.getRuleContent());
//            System.out.println(jsonObject);
//        } catch (JAntlrExecutionException e) {
//            System.err.println("解析失败: " + e.getMessage());
//            e.getErrors().forEach(err ->
//                    System.err.println(" - " + err.getMessage()));
//        }
//    }
//
//    @Test
//    @Ignore
//    public void test2() throws IOException {
//        JContext jcontext = new JContext();
//        jcontext.put("name", "paohaijiao");
//        JSONExecutor executor = new JSONExecutor(jcontext);
//        executor.addErrorListener(error -> {
//        });
//        try {
//            JQuickReader fileReader = new JQuickReSourceFileReader("rule1.txt");
//            JQuickAdaptor context = new JQuickAdaptor(fileReader);
//            System.out.println(context.getRuleContent());
//            JsonResponse jsonObject = executor.execute(context.getRuleContent());
//            System.out.println(jsonObject);
//        } catch (JAntlrExecutionException e) {
//            System.err.println("解析失败: " + e.getMessage());
//            e.getErrors().forEach(err ->
//                    System.err.println(" - " + err.getMessage()));
//        }
//    }
//
//    @Test
//    @Ignore
//    public void test3() throws IOException {
//        JUser user = new JUser("John", 30);
//        JSONSerializer serializer = JSONSerializerFactory.getDefaultSerializer();
//        String json = serializer.serialize(user);
//        System.out.println(json);
//
//        String jsonStr = "{\"name\":\"Alice\",\"age\":25}";
//        JUser deserializedUser = serializer.deserialize(jsonStr, JUser.class);
//        System.out.println(deserializedUser.getName());
//
//        String jsonArray = "[{\"name\":\"Alice\",\"age\":25}]";
//        List<JUser> deserializedUserArray = serializer.deserialize(jsonArray, List.class);
//        System.out.println(deserializedUserArray);
//
//
//    }
//
//
//}
