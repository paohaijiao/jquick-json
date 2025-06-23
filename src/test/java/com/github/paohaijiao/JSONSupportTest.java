package com.github.paohaijiao; ///*
// * Licensed under the Apache License, Version 2.0 (the "License");
// * you may not use this file except in compliance with the License.
// * You may obtain a copy of the License at
// *
// *     http://www.apache.org/licenses/LICENSE-2.0
// *
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
// *
// * Copyright (c) [2025-2099] Martin (goudingcheng@gmail.com)
// */
//package com.github.paohaijiao.test;
//
//import com.github.paohaijiao.model.JSONArray;
//import com.github.paohaijiao.model.JSONObject;
//import com.github.paohaijiao.support.JSONSupport;
//import org.junit.Test;
//
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.Map;
//
//import static org.junit.Assert.*;
//
///**
// * packageName com.jquick.test
// *
// * @author Martin
// * @version 1.0.0
// * @className JSONSupport
// * @date 2025/6/21
// * @description
// */
//public class JSONSupportTest {
//    @Test
//    public void testParseObjectSimple() {
//        String json = "{\"name\":\"John\",\"age\":30,\"isStudent\":false}";
//        JSONObject obj = JSONSupport.parseObject(json);
//        assertEquals("John", obj.getString("name"));
//        assertEquals(30, (int) obj.getInteger("age"));
//        assertFalse(obj.getBoolean("isStudent"));
//    }
//
//    @Test
//    public void testParseObjectNested() {
//        String json = "{\"person\":{\"name\":\"Alice\",\"age\":25},\"scores\":[90,85,95]}";
//        JSONObject obj = JSONSupport.parseObject(json);
//        JSONObject person = obj.getJSONObject("person");
//        assertEquals("Alice", person.getString("name"));
//        assertEquals(25, (int) person.getInteger("age"));
//        JSONArray scores = obj.getJSONArray("scores");
//        assertEquals(3, scores.size());
//        assertEquals(90, (int) scores.getInteger(0));
//        assertEquals(85, (int) scores.getInteger(1));
//        assertEquals(95, (int) scores.getInteger(2));
//    }
//
//    @Test
//    public void testParseArraySimple() {
//        String json = "[\"apple\",\"banana\",\"cherry\"]";
//        JSONArray array = JSONSupport.parseArray(json);
//        assertEquals(3, array.size());
//        assertEquals("apple", array.getString(0));
//        assertEquals("banana", array.getString(1));
//        assertEquals("cherry", array.getString(2));
//    }
//
//    @Test
//    public void testParseArrayMixed() {
//        String json = "[{\"name\":\"John\"},42,true,null]";
//        JSONArray array = JSONSupport.parseArray(json);
//        assertEquals(4, array.size());
//        assertEquals("John", array.getJSONObject(0).getString("name"));
//        assertEquals(42, (int) array.getInteger(1));
//        assertTrue(array.getBoolean(2));
//        assertNull(array.get(3));
//    }
//
//    @Test
//    public void testParseGeneric() {
//        String jsonObject = "{\"key\":\"value\"}";
//        Object obj = JSONSupport.parse(jsonObject);
//        assertTrue(obj instanceof JSONObject);
//        String jsonArray = "[1,2,3]";
//        Object arr = JSONSupport.parse(jsonArray);
//        assertTrue(arr instanceof JSONArray);
//        System.out.println("end");
//    }
//
//    @Test
//    public void testToJsonStringPrimitives() {
//        assertEquals("\"hello\"", JSONSupport.toJsonString("hello"));
//        assertEquals("123", JSONSupport.toJsonString(123));
//        assertEquals("true", JSONSupport.toJsonString(true));
//        assertEquals("null", JSONSupport.toJsonString(null));
//    }
//
//    @Test
//    public void testToJsonStringMap() {
//        Map<String, Object> map = new HashMap<>();
//        map.put("name", "John");
//        map.put("age", 30);
//
//        String json = JSONSupport.toJsonString(map);
//        assertTrue(json.contains("\"name\":\"John\""));
//        assertTrue(json.contains("\"age\":30"));
//    }
//
//    @Test
//    public void testToJsonStringCollection() {
//        String json = JSONSupport.toJsonString(Arrays.asList("a", "b", "c"));
//        assertEquals("[{\"value\":\"a\"},{\"value\":\"b\"},{\"value\":\"c\"}]", json);
//    }
//
//    @Test
//    public void testMergeJSONObjects() {
//        JSONObject first = new JSONObject();
//        first.put("name", "John");
//        first.put("age", 30);
//        JSONObject second = new JSONObject();
//        second.put("age", 31);
//        second.put("city", "New York");
//        JSONObject merged = JSONSupport.merge(first, second);
//        assertEquals("John", merged.getString("name"));
//        assertEquals(31, (int) merged.getInteger("age"));
//        assertEquals("New York", merged.getString("city"));
//    }
//
//    @Test
//    public void testIsValidJson() {
//        assertTrue(JSONSupport.isValidJson("{}"));
//        assertTrue(JSONSupport.isValidJson("[]"));
//        assertTrue(JSONSupport.isValidJson("{\"key\":\"value\"}"));
//        assertTrue(JSONSupport.isValidJson("[1,2,3]"));
//
//        assertFalse(JSONSupport.isValidJson(null));
//        assertFalse(JSONSupport.isValidJson(""));
//        assertFalse(JSONSupport.isValidJson("not json"));
//        assertFalse(JSONSupport.isValidJson("{key:value}"));
//    }
//
//    @Test
//    public void testPrettyPrint() {
//        String json = "{\"name\":\"John\",\"age\":30,\"address\":{\"street\":\"Main St\",\"city\":\"Boston\"}}";
//        String pretty = JSONSupport.prettyPrint(json, 2);
//        String expected =
//                "{\n" +
//                        "  \"name\" : \"John\",\n" +
//                        "  \"age\" : 30,\n" +
//                        "  \"address\" : {\n" +
//                        "    \"street\" : \"Main St\",\n" +
//                        "    \"city\" : \"Boston\"\n" +
//                        "  }\n" +
//                        "}";
//
//        assertEquals(expected, pretty);
//    }
//
//    @Test
//    public void testEscapeUnescape() {
//        String original = "Line1\nLine2\tTab\"Quote\\Backslash";
//        String escaped = JSONObject.escape(original);
//        String unescaped = JSONSupport.parse("\"" + escaped + "\"").toString();
//        unescaped = unescaped.substring(1, unescaped.length() - 1); // Remove quotes
//
//        assertEquals(original, unescaped);
//    }
//
//    @Test(expected = IllegalArgumentException.class)
//    public void testParseInvalidJson() {
//        JSONSupport.parse("not valid json");
//    }
//
//    @Test
//    public void testEmptyJson() {
//        JSONObject emptyObj = JSONSupport.parseObject("{}");
//        assertTrue(emptyObj.isEmpty());
//
//        JSONArray emptyArr = JSONSupport.parseArray("[]");
//        assertTrue(emptyArr.isEmpty());
//    }
//
//    @Test
//    public void testJsonWithSpecialCharacters() {
//        String json = "{\"message\":\"Hello\\nWorld\\t!\",\"path\":\"C:\\\\temp\\\\file.txt\"}";
//        JSONObject obj = JSONSupport.parseObject(json);
//
//        assertEquals("Hello\nWorld\t!", obj.getString("message"));
//        assertEquals("C:\\temp\\file.txt", obj.getString("path"));
//    }
//
//    @Test
//    public void testToJsonStringWithCustomObject() {
//        class Person {
//            String name = "Alice";
//            int age = 25;
//        }
//
//        String json = JSONSupport.toJsonString(new Person());
//        assertTrue(json.contains("\"name\":\"Alice\""));
//        assertTrue(json.contains("\"age\":25"));
//    }
//}
