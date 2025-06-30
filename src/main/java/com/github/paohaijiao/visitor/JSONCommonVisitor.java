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

import com.github.paohaijiao.exception.JAssert;
import com.github.paohaijiao.model.JSONArray;
import com.github.paohaijiao.model.JSONObject;
import com.github.paohaijiao.model.JSonKeyValue;
import com.github.paohaijiao.model.JsonResponse;
import com.github.paohaijiao.param.JContext;
import com.github.paohaijiao.parser.JQuickJSONParser;
import com.github.paohaijiao.util.JObjectConverter;
import com.github.paohaijiao.util.JStringUtils;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JSONCommonVisitor extends JSONCoreVisitor {

    private JContext context;

    public JSONCommonVisitor() {
        this.context = new JContext();
    }

    public JSONCommonVisitor(JContext context) {
        this.context = context;
    }

    @Override
    public Object visitJson(JQuickJSONParser.JsonContext ctx) {
        JsonResponse response = new JsonResponse();
        Object result = null;
        if (null != ctx.value()) {
            result = visitValue(ctx.value());
            if (result instanceof JSONObject) {// object
                return (JSONObject) result;
            } else if (result instanceof List) {//list
                List<?> list = JObjectConverter.assign(result, List.class);
                return new JSONArray(list);
            } else {//native
                return result;
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
        if (null != ctx.float_()) {
            Object obj = visitFloat(ctx.float_());
            return obj;
        }
        if (null != ctx.double_()) {
            Object obj = visitDouble(ctx.double_());
            return obj;
        }
        if (null != ctx.date()) {
            Object obj = visit(ctx.date());
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
    public Float visitFloat(JQuickJSONParser.FloatContext ctx) {
        if (ctx.NUMBER() != null) {
            String str = ctx.NUMBER().getText();
            return Float.parseFloat(str);
        }
        JAssert.throwNewException("invalid variable");
        return null;
    }

    @Override
    public Double visitDouble(JQuickJSONParser.DoubleContext ctx) {
        if (ctx.NUMBER() != null) {
            String str = ctx.NUMBER().getText();
            return Double.parseDouble(str);
        }
        JAssert.throwNewException("invalid variable");
        return null;
    }

    @Override
    public Date visitDate(JQuickJSONParser.DateContext ctx) {
        if (ctx.DATE() != null) {
            String date = trimDateSplit(ctx.DATE().getText());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            try {
                return sdf.parse(date);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        } else if (ctx.DATETIMETYPE() != null) {
            String datetime = trimDateSplit(ctx.DATETIMETYPE().getText());
            String newDate = datetime.replace("T", "");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHH:mm:ss");
            try {
                return sdf.parse(newDate);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
        JAssert.throwNewException("invalid date");
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

