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

import com.github.paohaijiao.model.JSONArray;
import com.github.paohaijiao.model.JSONObject;

import java.util.Collection;
import java.util.Map;

/**
 * packageName com.paohaijiao.javelin.support
 *
 * @author Martin
 * @version 1.0.0
 * @className JSONSupport
 * @date 2025/6/21
 * @description
 */
public class JSONSupport {


    public static String toJsonString(Object obj) {
        if (obj == null) {
            return "null";
        }
        if (obj instanceof JSONObject) {
            return obj.toString();
        }
        if (obj instanceof JSONArray) {
            return obj.toString();
        }
        if (obj instanceof String) {
            return "\"" + JSONObject.escape((String) obj) + "\"";
        }
        if (obj instanceof Number || obj instanceof Boolean) {
            return obj.toString();
        }
        if (obj instanceof Map) {
            return new JSONObject((Map<String, Object>) obj).toString();
        }
        if (obj instanceof Collection) {
            JSONArray array = new JSONArray();
            for (Object item : (Collection<?>) obj) {
                if (item instanceof JSONObject) {
                    array.add((JSONObject) item);
                } else {
                    JSONObject wrapper = new JSONObject();
                    wrapper.put("value", item);
                    array.add(wrapper);
                }
            }
            return array.toString();
        }
        try {
            JSONObject jsonObj = new JSONObject().fromBean(obj);
            return jsonObj.toString();
        } catch (Exception e) {
            return "\"" + JSONObject.escape(obj.toString()) + "\"";
        }
    }

    public static JSONObject merge(JSONObject first, JSONObject second) {
        JSONObject result = new JSONObject();
        if (first != null) {
            result.putAll(first);
        }
        if (second != null) {
            for (Map.Entry<String, Object> entry : second.entrySet()) {
                result.put(entry.getKey(), entry.getValue());
            }
        }
        return result;
    }

    public static boolean isValidJson(String json) {
        if (json == null || json.trim().isEmpty()) {
            return false;
        }
        String trimmed = json.trim();
        return (trimmed.startsWith("{") && trimmed.endsWith("}")) ||
                (trimmed.startsWith("[") && trimmed.endsWith("]"));
    }

    public static String prettyPrint(String json, int indentSpaces) {
        if (!isValidJson(json)) {
            return json;
        }
        StringBuilder sb = new StringBuilder();
        int indent = 0;
        boolean inString = false;
        char prevChar = 0;
        for (char c : json.toCharArray()) {
            if (c == '"' && prevChar != '\\') {
                inString = !inString;
            }
            if (!inString) {
                if (c == '{' || c == '[') {
                    sb.append(c).append("\n");
                    indent++;
                    appendIndent(sb, indent, indentSpaces);
                } else if (c == '}' || c == ']') {
                    sb.append("\n");
                    indent--;
                    appendIndent(sb, indent, indentSpaces);
                    sb.append(c);
                } else if (c == ',') {
                    sb.append(c).append("\n");
                    appendIndent(sb, indent, indentSpaces);
                } else if (c == ':') {
                    sb.append(" : ");
                } else {
                    sb.append(c);
                }
            } else {
                sb.append(c);
            }
            prevChar = c;
        }

        return sb.toString();
    }


    private static String unescapeJsonString(String str) {
        return str.replace("\\\"", "\"")
                .replace("\\\\", "\\")
                .replace("\\b", "\b")
                .replace("\\f", "\f")
                .replace("\\n", "\n")
                .replace("\\r", "\r")
                .replace("\\t", "\t");
    }

    private static void appendIndent(StringBuilder sb, int indent, int indentSpaces) {
        for (int i = 0; i < indent * indentSpaces; i++) {
            sb.append(" ");
        }
    }


}
