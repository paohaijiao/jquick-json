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

    public static Object[] parseString(String content, int index) {
        StringBuilder sb = new StringBuilder();
        boolean escape = false;
        index++;
        while (index < content.length()) {
            char c = content.charAt(index);
            if (escape) {
                switch (c) {
                    case '"':
                        sb.append('"');
                        break;
                    case '\\':
                        sb.append('\\');
                        break;
                    case '/':
                        sb.append('/');
                        break;
                    case 'b':
                        sb.append('\b');
                        break;
                    case 'f':
                        sb.append('\f');
                        break;
                    case 'n':
                        sb.append('\n');
                        break;
                    case 'r':
                        sb.append('\r');
                        break;
                    case 't':
                        sb.append('\t');
                        break;
                    case 'u':
                        if (index + 4 < content.length()) {
                            String hex = content.substring(index + 1, index + 5);
                            sb.append((char) Integer.parseInt(hex, 16));
                            index += 4;
                        }
                        break;
                    default:
                        sb.append(c);
                }
                escape = false;
            } else if (c == '"') {
                index++;
                return new Object[]{sb.toString(), index};
            } else if (c == '\\') {
                escape = true;
            } else {
                sb.append(c);
            }
            index++;
        }
        throw new IllegalArgumentException("Unterminated string");
    }

    public static Object[] parseObject(String content, int index) {
        int start = index;
        int braceCount = 1;
        index++; // 跳过开始的{
        while (index < content.length()) {
            char c = content.charAt(index);
            if (c == '"') {
                // 跳过字符串内容
                Object[] stringResult = parseString(content, index);
                index = (int) stringResult[1];
            } else if (c == '{') {
                braceCount++;
            } else if (c == '}') {
                braceCount--;
                if (braceCount == 0) {
                    index++; // 跳过结束的}
                    String objectStr = content.substring(start, index);
                    return new Object[]{JSONObject.parseObject(objectStr), index};
                }
            }
            index++;
        }
        throw new IllegalArgumentException("Unterminated object");
    }

    public static Object[] parseNumber(String content, int index) {
        int start = index;
        while (index < content.length()) {
            char c = content.charAt(index);
            if (!(Character.isDigit(c) || c == '-' || c == '+' || c == 'e' || c == 'E' || c == '.')) {
                break;
            }
            index++;
        }
        String numStr = content.substring(start, index);
        try {
            if (numStr.contains(".")) {
                return new Object[]{Double.parseDouble(numStr), index};
            } else {
                long longValue = Long.parseLong(numStr);
                if (longValue >= Integer.MIN_VALUE && longValue <= Integer.MAX_VALUE) {
                    return new Object[]{(int) longValue, index};
                }
                return new Object[]{longValue, index};
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid number: " + numStr);
        }
    }
}
