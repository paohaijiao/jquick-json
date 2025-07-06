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
import com.github.paohaijiao.merge.JsonMerger;
import com.github.paohaijiao.model.JSONArray;
import com.github.paohaijiao.model.JSONObject;

/**
 * packageName com.github.paohaijiao.merge.impl
 *
 * @author Martin
 * @version 1.0.0
 *  JDefaultJsonMerger
 * @since 2025/6/26
 * 
 */
public class JDefaultJsonMerger implements JsonMerger {

    private JMergeStrategy strategy;

    public JDefaultJsonMerger() {
        this.strategy = new JDeepMergeStrategy();
    }

    @Override
    public JSONObject merge(JSONObject... objects) {
        if (objects == null || objects.length == 0) {
            return new JSONObject();
        }

        JSONObject result = objects[0];
        for (int i = 1; i < objects.length; i++) {
            result = strategy.mergeObjects(result, objects[i]);
        }
        return result;
    }

    @Override
    public JSONArray merge(JSONArray... arrays) {
        if (arrays == null || arrays.length == 0) {
            return new JSONArray();
        }
        JSONArray result = arrays[0];
        for (int i = 1; i < arrays.length; i++) {
            result = strategy.mergeArrays(result, arrays[i]);
        }
        return result;
    }

    @Override
    public JsonMerger strategy(JMergeStrategy strategy) {
        this.strategy = strategy;
        return this;
    }

}
