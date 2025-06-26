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

import java.util.function.BiFunction;

/**
 * packageName com.github.paohaijiao.merge
 *
 * @author Martin
 * @version 1.0.0
 * @className JCustomMergeStrategy
 * @date 2025/6/26
 * @description
 */
public class JCustomMergeStrategy implements JMergeStrategy
{
    private final BiFunction<JSONObject, JSONObject, JSONObject> objectMerger;
    private final BiFunction<JSONArray, JSONArray, JSONArray> arrayMerger;

    public JCustomMergeStrategy(
            BiFunction<JSONObject, JSONObject, JSONObject> objectMerger,
            BiFunction<JSONArray, JSONArray, JSONArray> arrayMerger) {
        this.objectMerger = objectMerger;
        this.arrayMerger = arrayMerger;
    }

    @Override
    public JSONObject mergeObjects(JSONObject target, JSONObject source) {
        return objectMerger.apply(target, source);
    }

    @Override
    public JSONArray mergeArrays(JSONArray target, JSONArray source) {
        return arrayMerger.apply(target, source);
    }
}
