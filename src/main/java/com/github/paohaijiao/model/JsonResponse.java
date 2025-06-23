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
package com.github.paohaijiao.model;

import com.github.paohaijiao.util.JBeanCopyUtils;
import lombok.Data;

import java.util.List;


@Data
public class JsonResponse {
    private Object data;

    private String type;

    public JsonResponse getData() {
        JsonResponse response = new JsonResponse();
        if (data instanceof JSONObject) {
            response.setData((JSONObject) data);
            response.setType("object");
        } else {
            List<JSONObject> list = JBeanCopyUtils.copyList((List) data, JSONObject.class);
            response.setData(new JSONArray(list));
            response.setType("array");
        }
        return response;
    }

    @Override
    public String toString() {
        return "{" +
                "type=" + type +
                ", data=" + data +
                '}';
    }
}
