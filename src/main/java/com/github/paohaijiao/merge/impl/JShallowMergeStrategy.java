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

/**
 * packageName com.github.paohaijiao.merge
 *
 * @author Martin
 * @version 1.0.0
 *  JShallowMergeStrategy
 * @since 2025/6/26
 * 
 */
public class JShallowMergeStrategy implements JMergeStrategy {
    @Override
    public JSONObject mergeObjects(JSONObject target, JSONObject source) {
        if (target == null) return source;
        if (source == null) return target;
        JSONObject result = new JSONObject();
        result.putAll(target);
        result.putAll(source);
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
