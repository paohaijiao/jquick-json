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
package com.github.paohaijiao.support;

import com.github.paohaijiao.merge.JsonMerger;
import com.github.paohaijiao.merge.impl.JDeepMergeStrategy;
import com.github.paohaijiao.merge.impl.JDefaultJsonMerger;
import com.github.paohaijiao.merge.impl.JShallowMergeStrategy;
import com.github.paohaijiao.model.JSONArray;
import com.github.paohaijiao.model.JSONObject;

/**
 * packageName com.github.paohaijiao.support
 *
 * @author Martin
 * @version 1.0.0
 *  JSonMerge
 * @since 2025/6/26
 * 
 */
public class JSonMerge {
    private static final JsonMerger DEEP_MERGER = new JDefaultJsonMerger().strategy(new JDeepMergeStrategy());

    private static final JsonMerger SHALLOW_MERGER = new JDefaultJsonMerger().strategy(new JShallowMergeStrategy());


    public static JSONObject deepMerge(JSONObject... objects) {
        return DEEP_MERGER.merge(objects);
    }


    public static JSONArray deepMerge(JSONArray... arrays) {
        return DEEP_MERGER.merge(arrays);
    }

    public static JSONObject shallowMerge(JSONObject... objects) {
        return SHALLOW_MERGER.merge(objects);
    }


    public static JSONArray shallowMerge(JSONArray... arrays) {
        return SHALLOW_MERGER.merge(arrays);
    }
}
