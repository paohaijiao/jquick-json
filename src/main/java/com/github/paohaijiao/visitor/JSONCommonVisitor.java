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
package com.github.paohaijiao.visitor;

import com.github.paohaijiao.model.JSONArray;
import com.github.paohaijiao.model.JSONObject;
import com.github.paohaijiao.model.JSonKeyValue;
import com.github.paohaijiao.model.JsonResponse;
import com.github.paohaijiao.parser.JQuickJSONBaseVisitor;
import com.github.paohaijiao.parser.JQuickJSONParser;
import com.paohaijiao.javelin.exception.JAssert;
import com.paohaijiao.javelin.param.JContext;
import com.paohaijiao.javelin.util.JBeanCopyUtils;
import com.paohaijiao.javelin.util.JStringUtils;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class JSONCommonVisitor extends JQuickJSONBaseVisitor {

    private JContext context;

    public JSONCommonVisitor() {
        this.context = new JContext();
    }

    public JSONCommonVisitor(JContext context) {
        this.context = context;
    }

    @Override
    public JsonResponse visitJson(JQuickJSONParser.JsonContext ctx) {
        JsonResponse response = new JsonResponse();
        Object result = null;
        if (null != ctx.value()) {
            result = visitValue(ctx.value());
            if (result instanceof JSONObject) {
                response.setData((JSONObject) result);
                response.setType("object");
            } else {
                List<JSONObject> list = JBeanCopyUtils.copyList((List) result, JSONObject.class);
                response.setData(new JSONArray(list));
                response.setType("array");
            }
        }
        return response;
    }

    @Override
    public Object visitValue(JQuickJSONParser.ValueContext ctx) {
        if (null != ctx.object()) {
            Object obj = visitObject(ctx.object());
            return obj;
        }
        if (null != ctx.array()) {
            return visitArray(ctx.array());
        }
        if (null != ctx.string()) {
            return visitString(ctx.string());
        }
        if (null != ctx.number()) {
            return visitNumber(ctx.number());
        }
        if (null != ctx.bool()) {
            return visitBool(ctx.bool());
        }
        if (null != ctx.null_()) {
            return visitNull(ctx.null_());
        }
        if (null != ctx.variable()) {
            Object obj = visitVariable(ctx.variable());
            return obj;
        }
        return null;
    }

    @Override
    public Object visitVariable(JQuickJSONParser.VariableContext ctx) {
        if (ctx.identifier() != null) {
            String str = visitIdentifier(ctx.identifier());
            return context.get(str);
        }
        JAssert.throwNewException("invalid variable");
        return null;
    }


    @Override
    public String visitIdentifier(JQuickJSONParser.IdentifierContext ctx) {
        String string = ctx.getText();
        return StringUtils.trim(string);
    }


    @Override
    public List<Object> visitArray(JQuickJSONParser.ArrayContext ctx) {
        List<Object> array = new ArrayList<>();
        for (JQuickJSONParser.ValueContext value : ctx.value()) {
            Object object = visitValue(value);
            array.add(object);

        }
        return array;
    }

    @Override
    public JSONObject visitObject(JQuickJSONParser.ObjectContext ctx) {
        JSONObject jsonObject = new JSONObject();
        List<JQuickJSONParser.PairContext> pair = ctx.pair();
        for (int i = 0; i < pair.size(); i++) {
            JSonKeyValue keyValue = visitPair(ctx.pair(i));
            jsonObject.put(keyValue.getKey(), keyValue.getValue());
        }
        return jsonObject;
    }

    @Override
    public JSonKeyValue visitPair(JQuickJSONParser.PairContext ctx) {
        JSonKeyValue jSonObject = new JSonKeyValue();
        String key = null;
        Object value = null;
        if (null != ctx.string()) {
            key = visitString(ctx.string());
        }
        if (null != ctx.value()) {
            value = visitValue(ctx.value());
        }
        jSonObject.setKey(key);
        jSonObject.setValue(value);
        return jSonObject;
    }

    @Override
    public String visitString(JQuickJSONParser.StringContext ctx) {
        String str = ctx.getText();
        return JStringUtils.trim(str);
    }

    @Override
    public BigDecimal visitNumber(JQuickJSONParser.NumberContext ctx) {
        BigDecimal bigDecimal = new BigDecimal(ctx.getText());
        return bigDecimal;
    }

    @Override
    public Boolean visitBool(JQuickJSONParser.BoolContext ctx) {
        String str = ctx.getText();
        if (str.equals("true")) {
            return true;
        } else if (str.equals("false")) {
            return true;
        } else {
            return null;
        }
    }

    @Override
    public JSONObject visitNull(JQuickJSONParser.NullContext ctx) {
        return null;
    }

}

