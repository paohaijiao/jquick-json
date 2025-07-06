/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Copyright (c) [2025-2099] Martin (goudingcheng@gmail.com)
 */
package com.github.paohaijiao.merge;

import com.github.paohaijiao.model.JSONArray;
import com.github.paohaijiao.model.JSONObject;
import org.junit.Test;

import java.io.IOException;

/**
 * packageName com.github.paohaijiao.merge
 *
 * @author Martin
 * @version 1.0.0
 *  JSonMerge
 * @since 2025/6/26
 * 
 */
public class JSonMerge {

    @Test
    public void test() throws IOException {
        JSONObject obj1 = new JSONObject();
        obj1.put("name", "Alice");
        obj1.put("age", 25);
        JSONObject object1 = new JSONObject();
        object1.put("city", "New York");
        obj1.put("address", object1);

        JSONObject obj2 = new JSONObject();
        obj2.put("age", 26);
        JSONObject object2 = new JSONObject();
        object2.put("zip", "10001");
        obj2.put("address", object2);
        JSONArray array = new JSONArray();
        array.add("Reading");
        obj2.put("hobbies", array);
        JSONObject deepMerged = obj1.deepMergeWith(obj2);
        System.out.println(deepMerged);
    }

    @Test
    public void test1() throws IOException {
        JSONObject obj1 = new JSONObject();
        obj1.put("name", "Alice");
        obj1.put("age", 25);
        JSONObject object = new JSONObject();
        object.put("city", "New York");
        obj1.put("address", object);

        JSONObject obj2 = new JSONObject();
        obj2.put("age", 26);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("zip", "10001");
        obj2.put("address", jsonObject);
        JSONArray array = new JSONArray();
        array.add("Reading");
        ;
        obj2.put("hobbies", array);
        JSONObject shallowMerged = obj1.shallowMergeWith(obj2);
        System.out.println(shallowMerged);

    }
//    @Test
//    public void test3() throws IOException {
//        JSONObject obj1 = new JSONObject();
//        obj1.put("name", "Alice");
//        obj1.put("age", 25);
//        obj1.put("address", new JSONObject().put("city", "New York"));
//
//        JSONObject obj2 = new JSONObject();
//        obj2.put("age", 26);
//        obj2.put("address", new JSONObject().put("zip", "10001"));
//        obj2.put("hobbies", new JSONArray().add("Reading"));
//        JMergeStrategy customStrategy = new JCustomMergeStrategy(
//                (target, source) -> {
//                    JSONObject result = new JSONObject();
//                    for (String key : target.keySet()) {
//                        Object targetValue = target.get(key);
//                        Object sourceValue = source.get(key);
//                        if (sourceValue instanceof Number && targetValue instanceof Number) {
//                            double targetNum = ((Number) targetValue).doubleValue();
//                            double sourceNum = ((Number) sourceValue).doubleValue();
//                            result.put(key, Math.max(targetNum, sourceNum));
//                        } else {
//                            result.put(key, sourceValue != null ? sourceValue : targetValue);
//                        }
//                    }
//                    for (String key : source.keySet()) {
//                        if (!target.containsKey(key)) {
//                            result.put(key, source.get(key));
//                        }
//                    }
//                    return result;
//                },
//                JSONArray::addAll
//        );
//        JSONObject merged = new JDefaultJsonMerger()
//                .strategy(customStrategy)
//                .merge(obj1, obj2);
//
//    }
}
