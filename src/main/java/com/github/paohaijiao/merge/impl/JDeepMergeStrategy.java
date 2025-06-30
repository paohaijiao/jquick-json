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
package com.github.paohaijiao.merge.impl;

import com.github.paohaijiao.merge.JMergeStrategy;
import com.github.paohaijiao.model.JSONArray;
import com.github.paohaijiao.model.JSONObject;

import java.util.Map;

/**
 * packageName com.github.paohaijiao.merge
 *
 * @author Martin
 * @version 1.0.0
 * @className JDeepMergeStrategy
 * @date 2025/6/26
 * @description
 */
public class JDeepMergeStrategy implements JMergeStrategy {
    @Override
    public JSONObject mergeObjects(JSONObject target, JSONObject source) {
        if (target == null) return source;
        if (source == null) return target;
        JSONObject result = new JSONObject();
        for (Map.Entry<String, Object> entry : target.entrySet()) {
            result.put(entry.getKey(), entry.getValue());
        }
        for (Map.Entry<String, Object> entry : source.entrySet()) {
            String key = entry.getKey();
            Object sourceValue = entry.getValue();
            Object targetValue = target.get(key);
            if (targetValue == null) {
                result.put(key, sourceValue);
            } else if (sourceValue instanceof JSONObject && targetValue instanceof JSONObject) {
                result.put(key, mergeObjects((JSONObject) targetValue, (JSONObject) sourceValue));
            } else if (sourceValue instanceof JSONArray && targetValue instanceof JSONArray) {
                result.put(key, mergeArrays((JSONArray) targetValue, (JSONArray) sourceValue));
            } else {
                result.put(key, sourceValue);
            }
        }

        return result;
    }

    @Override
    public JSONArray mergeArrays(JSONArray target, JSONArray source) {
        if (target == null) return source;
        if (source == null) return target;
        JSONArray result = new JSONArray();
        result.addAll(target);
        result.addAll(source);
        return result;
    }
}
