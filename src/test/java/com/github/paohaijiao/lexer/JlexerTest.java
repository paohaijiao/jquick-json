package com.github.paohaijiao.lexer;

import com.github.paohaijiao.parser.JQuickJSONLexer;
import com.github.paohaijiao.parser.JQuickJSONParser;
import com.github.paohaijiao.visitor.JSONCommonVisitor;
import com.github.paohaijiao.param.JContext;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Test;

import java.io.IOException;

public class JlexerTest {

    @Test
    public void json1() throws IOException {
        String input = "[\n" +
                "  {\n" +
                "    \"org_id\": \"1001\",\n" +
                "    \"org_name\": \"北京市\",\n" +
                "    \"org_type\": \"REGION\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"org_id\": \"1002\",\n" +
                "    \"org_name\": \"上海市\",\n" +
                "    \"org_type\": \"REGION\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"org_id\": \"2001\",\n" +
                "    \"org_name\": \"北京分行\",\n" +
                "    \"org_type\": \"ORG\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"org_id\": \"2002\",\n" +
                "    \"org_name\": \"上海分行\",\n" +
                "    \"org_type\": \"ORG\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"org_id\": \"2003\",\n" +
                "    \"org_name\": \"北京朝阳支行\",\n" +
                "    \"org_type\": \"ORG\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"org_id\": \"2004\",\n" +
                "    \"org_name\": \"上海浦东支行\",\n" +
                "    \"org_type\": \"ORG\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"org_id\": \"3001\",\n" +
                "    \"org_name\": \"华东大区\",\n" +
                "    \"org_type\": \"REGION\"\n" +
                "  }\n" +
                "]";
        System.out.println(input);
        JQuickJSONLexer lexer = new JQuickJSONLexer(CharStreams.fromString(input));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JQuickJSONParser parser = new JQuickJSONParser(tokens);
        ParseTree tree = parser.json();
        JContext params = new JContext();
        params.put("key", "value");
        JSONCommonVisitor visitor = new JSONCommonVisitor(params);
        Object key= visitor.visit(tree);
        System.out.println(key);
    }
    @Test
    public void string() throws IOException {
        String input = "'1'";
        JQuickJSONLexer lexer = new JQuickJSONLexer(CharStreams.fromString(input));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JQuickJSONParser parser = new JQuickJSONParser(tokens);
        ParseTree tree = parser.json();
        JContext params = new JContext();
        params.put("key", "value");
        JSONCommonVisitor visitor = new JSONCommonVisitor(params);
        Object key= visitor.visit(tree);
        System.out.println(key);
    }
    @Test
    public void number() throws IOException {
        String input = "1";
        System.out.println(input);
        JQuickJSONLexer lexer = new JQuickJSONLexer(CharStreams.fromString(input));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JQuickJSONParser parser = new JQuickJSONParser(tokens);
        ParseTree tree = parser.json();
        JContext params = new JContext();
        params.put("key", "value");
        JSONCommonVisitor visitor = new JSONCommonVisitor(params);
        Object key= visitor.visit(tree);
        System.out.println(key);
    }
    @Test
    public void float1() throws IOException {
        String input = "1.1f";
        System.out.println(input);
        JQuickJSONLexer lexer = new JQuickJSONLexer(CharStreams.fromString(input));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JQuickJSONParser parser = new JQuickJSONParser(tokens);
        ParseTree tree = parser.json();
        JContext params = new JContext();
        params.put("key", "value");
        JSONCommonVisitor visitor = new JSONCommonVisitor(params);
        Object key= visitor.visit(tree);
        System.out.println(key);
    }
    @Test
    public void double1() throws IOException {
        String input = "1.1d";
        System.out.println(input);
        JQuickJSONLexer lexer = new JQuickJSONLexer(CharStreams.fromString(input));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JQuickJSONParser parser = new JQuickJSONParser(tokens);
        ParseTree tree = parser.json();
        JContext params = new JContext();
        params.put("key", "value");
        JSONCommonVisitor visitor = new JSONCommonVisitor(params);
        Object key= visitor.visit(tree);
        System.out.println(key);
    }

    @Test
    public void data() throws IOException {
        String input = "2023-12-25 14:30:01";
        System.out.println(input);
        JQuickJSONLexer lexer = new JQuickJSONLexer(CharStreams.fromString(input));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JQuickJSONParser parser = new JQuickJSONParser(tokens);
        ParseTree tree = parser.json();
        JContext params = new JContext();
        params.put("key", "value");
        JSONCommonVisitor visitor = new JSONCommonVisitor(params);
        Object key= visitor.visit(tree);
        System.out.println(key);
    }
    @Test
    public void date1() throws IOException {
        String input = "2023-12-25T14:30:01";
        System.out.println(input);
        JQuickJSONLexer lexer = new JQuickJSONLexer(CharStreams.fromString(input));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JQuickJSONParser parser = new JQuickJSONParser(tokens);
        ParseTree tree = parser.json();
        JContext params = new JContext();
        params.put("key", "value");
        JSONCommonVisitor visitor = new JSONCommonVisitor(params);
        Object key= visitor.visit(tree);
        System.out.println(key);
    }
    @Test
    public void date2() throws IOException {
        String input = "2023-12-25";
        System.out.println(input);
        JQuickJSONLexer lexer = new JQuickJSONLexer(CharStreams.fromString(input));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JQuickJSONParser parser = new JQuickJSONParser(tokens);
        ParseTree tree = parser.json();
        JContext params = new JContext();
        params.put("key", "value");
        JSONCommonVisitor visitor = new JSONCommonVisitor(params);
        Object key= visitor.visit(tree);
        System.out.println(key);
    }
    @Test
    public void date3() throws IOException {
        String input = "2023/12/25";
        System.out.println(input);
        JQuickJSONLexer lexer = new JQuickJSONLexer(CharStreams.fromString(input));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JQuickJSONParser parser = new JQuickJSONParser(tokens);
        ParseTree tree = parser.json();
        JContext params = new JContext();
        params.put("key", "value");
        JSONCommonVisitor visitor = new JSONCommonVisitor(params);
        Object key= visitor.visit(tree);
        System.out.println(key);
    }
    @Test
    public void bool() throws IOException {
        String input = "true";
        System.out.println(input);
        JQuickJSONLexer lexer = new JQuickJSONLexer(CharStreams.fromString(input));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JQuickJSONParser parser = new JQuickJSONParser(tokens);
        ParseTree tree = parser.json();
        JContext params = new JContext();
        params.put("key", "value");
        JSONCommonVisitor visitor = new JSONCommonVisitor(params);
        Object key= visitor.visit(tree);
        System.out.println(key);
    }
    @Test
    public void nullVlue() throws IOException {
        String input = "null";
        System.out.println(input);
        JQuickJSONLexer lexer = new JQuickJSONLexer(CharStreams.fromString(input));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JQuickJSONParser parser = new JQuickJSONParser(tokens);
        ParseTree tree = parser.json();
        JContext params = new JContext();
        params.put("key", "value");
        JSONCommonVisitor visitor = new JSONCommonVisitor(params);
        Object key= visitor.visit(tree);
        System.out.println(key);
    }
    @Test
    public void variable() throws IOException {
        String input = "${key}";
        System.out.println(input);
        JQuickJSONLexer lexer = new JQuickJSONLexer(CharStreams.fromString(input));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JQuickJSONParser parser = new JQuickJSONParser(tokens);
        ParseTree tree = parser.json();
        JContext params = new JContext();
        params.put("key", "value");
        JSONCommonVisitor visitor = new JSONCommonVisitor(params);
        Object key= visitor.visit(tree);
        System.out.println(key);
    }
    @Test
    public void netestJson() throws IOException {
        String input = "{\n" +
                "  \"company\": {\n" +
                "    \"name\": \"TechCorp\",\n" +
                "    \"founded\": 2005,\n" +
                "    \"headquarters\": {\n" +
                "      \"city\": \"San Francisco\",\n" +
                "      \"country\": \"USA\",\n" +
                "      \"coordinates\": {\n" +
                "        \"latitude\": 37.7749,\n" +
                "        \"longitude\": -122.4194\n" +
                "      }\n" +
                "    },\n" +
                "    \"departments\": [\n" +
                "      {\n" +
                "        \"name\": \"Engineering\",\n" +
                "        \"manager\": \"Alice Johnson\",\n" +
                "        \"teams\": [\n" +
                "          {\n" +
                "            \"name\": \"Frontend\",\n" +
                "            \"size\": 8,\n" +
                "            \"projects\": [\n" +
                "              {\n" +
                "                \"name\": \"Website Redesign\",\n" +
                "                \"status\": \"In Progress\",\n" +
                "                \"deadline\": \"2023-12-15\"\n" +
                "              }\n" +
                "            ]\n" +
                "          },\n" +
                "          {\n" +
                "            \"name\": \"Backend\",\n" +
                "            \"size\": 6,\n" +
                "            \"projects\": []\n" +
                "          }\n" +
                "        ]\n" +
                "      },\n" +
                "      {\n" +
                "        \"name\": \"Marketing\",\n" +
                "        \"manager\": \"Bob Smith\",\n" +
                "        \"teams\": [\n" +
                "          {\n" +
                "            \"name\": \"Digital\",\n" +
                "            \"size\": 5,\n" +
                "            \"campaigns\": [\n" +
                "              {\n" +
                "                \"name\": \"Summer Sale\",\n" +
                "                \"budget\": 50000\n" +
                "              }\n" +
                "            ]\n" +
                "          }\n" +
                "        ]\n" +
                "      }\n" +
                "    ],\n" +
                "    \"employees\": [\n" +
                "      {\n" +
                "        \"id\": 101,\n" +
                "        \"name\": \"John Doe\",\n" +
                "        \"position\": \"Senior Developer\",\n" +
                "        \"skills\": [\"JavaScript\", \"React\", \"Node.js\"],\n" +
                "        \"contact\": {\n" +
                "          \"email\": \"john@techcorp.com\",\n" +
                "          \"phone\": \"+1-555-0101\"\n" +
                "        }\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": 102,\n" +
                "        \"name\": \"Jane Smith\",\n" +
                "        \"position\": \"Marketing Specialist\",\n" +
                "        \"skills\": [\"SEO\", \"Content Writing\", \"Social Media\"],\n" +
                "        \"contact\": {\n" +
                "          \"email\": \"jane@techcorp.com\",\n" +
                "          \"phone\": \"+1-555-0102\"\n" +
                "        }\n" +
                "      }\n" +
                "    ]\n" +
                "  },\n" +
                "  \"metadata\": {\n" +
                "    \"generated\": \"2023-11-20T14:30:00Z\",\n" +
                "    \"version\": 1.2\n" +
                "  }\n" +
                "}";
        System.out.println(input);
        JQuickJSONLexer lexer = new JQuickJSONLexer(CharStreams.fromString(input));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JQuickJSONParser parser = new JQuickJSONParser(tokens);
        ParseTree tree = parser.json();
        JContext params = new JContext();
        params.put("key", "value");
        JSONCommonVisitor visitor = new JSONCommonVisitor(params);
        Object key= visitor.visit(tree);
        System.out.println(key);
    }
}
