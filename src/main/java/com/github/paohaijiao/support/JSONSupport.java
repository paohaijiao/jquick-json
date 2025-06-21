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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

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

    private static final Pattern NUMBER_PATTERN = Pattern.compile("-?\\d+(\\.\\d+)?");

    public static Object parse(String json) {
        if (json == null || json.trim().isEmpty()) {
            throw new IllegalArgumentException("JSON string cannot be null or empty");
        }
        String trimmed = json.trim();
        if (trimmed.startsWith("{")) {
            return parseObject(trimmed);
        } else if (trimmed.startsWith("[")) {
            return parseArray(trimmed);
        } else {
            throw new IllegalArgumentException("Invalid JSON string: must start with '{' or '['");
        }
    }

    public static JSONObject parseObject(String json) {
        if (json == null || !json.trim().startsWith("{")) {
            throw new IllegalArgumentException("Invalid JSON object string");
        }
        String content = json.trim().substring(1, json.length() - 1).trim();
        if (content.isEmpty()) {
            return new JSONObject();
        }
        JSONObject result = new JSONObject();
        List<String> entries = splitJsonEntries(content);
        for (String entry : entries) {
            String[] kv = splitKeyValue(entry);
            String key = unescapeJsonString(kv[0].trim().replaceAll("^\"|\"$", ""));
            String valueStr = kv[1].trim();
            Object value = parseValue(valueStr);
            result.put(key, value);
        }
        return result;
    }

    public static JSONArray parseArray(String json) {
        if (json == null || !json.trim().startsWith("[")) {
            throw new IllegalArgumentException("Invalid JSON array string");
        }
        String content = json.trim().substring(1, json.length() - 1).trim();
        if (content.isEmpty()) {
            return new JSONArray();
        }
        JSONArray result = new JSONArray();
        List<String> elements = splitJsonElements(content);
        for (String element : elements) {
            Object value = parseValue(element.trim());
            if (value instanceof JSONObject) {
                result.add((JSONObject) value);
            } else {
                result.add(value);
            }
        }
        return result;
    }

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

    private static Object parseValue(String valueStr) {
        if (valueStr.startsWith("\"") && valueStr.endsWith("\"")) {
            return unescapeJsonString(valueStr.substring(1, valueStr.length() - 1));
        } else if (valueStr.equals("true")) {
            return true;
        } else if (valueStr.equals("false")) {
            return false;
        } else if (valueStr.equals("null")) {
            return null;
        } else if (valueStr.startsWith("{") && valueStr.endsWith("}")) {
            return parseObject(valueStr);
        } else if (valueStr.startsWith("[") && valueStr.endsWith("]")) {
            return parseArray(valueStr);
        } else if (NUMBER_PATTERN.matcher(valueStr).matches()) {
            try {
                if (valueStr.contains(".")) {
                    return Double.parseDouble(valueStr);
                } else {
                    return Long.parseLong(valueStr);
                }
            } catch (NumberFormatException e) {
                return valueStr;
            }
        } else {
            return valueStr;
        }
    }

    private static List<String> splitJsonEntries(String content) {
        List<String> entries = new ArrayList<>();
        int braceLevel = 0;
        int bracketLevel = 0;
        boolean inString = false;
        int start = 0;

        for (int i = 0; i < content.length(); i++) {
            char c = content.charAt(i);
            if (c == '"' && (i == 0 || content.charAt(i - 1) != '\\')) {
                inString = !inString;
            } else if (!inString) {
                if (c == '{') braceLevel++;
                else if (c == '}') braceLevel--;
                else if (c == '[') bracketLevel++;
                else if (c == ']') bracketLevel--;
                else if (c == ',' && braceLevel == 0 && bracketLevel == 0) {
                    entries.add(content.substring(start, i));
                    start = i + 1;
                }
            }
        }
        entries.add(content.substring(start));
        return entries;
    }

    private static List<String> splitJsonElements(String content) {
        List<String> elements = new ArrayList<>();
        int braceLevel = 0;
        int bracketLevel = 0;
        boolean inString = false;
        int start = 0;

        for (int i = 0; i < content.length(); i++) {
            char c = content.charAt(i);
            if (c == '"' && (i == 0 || content.charAt(i - 1) != '\\')) {
                inString = !inString;
            } else if (!inString) {
                if (c == '{') braceLevel++;
                else if (c == '}') braceLevel--;
                else if (c == '[') bracketLevel++;
                else if (c == ']') bracketLevel--;
                else if (c == ',' && braceLevel == 0 && bracketLevel == 0) {
                    elements.add(content.substring(start, i));
                    start = i + 1;
                }
            }
        }
        elements.add(content.substring(start));
        return elements;
    }

    private static String[] splitKeyValue(String entry) {
        int colonPos = -1;
        boolean inString = false;

        for (int i = 0; i < entry.length(); i++) {
            char c = entry.charAt(i);
            if (c == '"' && (i == 0 || entry.charAt(i - 1) != '\\')) {
                inString = !inString;
            } else if (!inString && c == ':') {
                colonPos = i;
                break;
            }
        }

        if (colonPos == -1) {
            throw new IllegalArgumentException("Invalid JSON entry: " + entry);
        }

        return new String[]{
                entry.substring(0, colonPos),
                entry.substring(colonPos + 1)
        };
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
