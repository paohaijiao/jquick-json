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
package com.github.paohaijiao.executor;

import com.github.paohaijiao.parser.JQuickJSONLexer;
import com.github.paohaijiao.parser.JQuickJSONParser;
import com.github.paohaijiao.visitor.JSONCommonVisitor;
import com.github.paohaijiao.antlr.impl.JAbstractAntlrExecutor;
import com.github.paohaijiao.exception.JAntlrExecutionException;
import com.github.paohaijiao.param.JContext;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.TokenStream;

public class JSONExecutor extends JAbstractAntlrExecutor<String, Object> {
    private JContext context;

    public JSONExecutor() {
        this.context = new JContext();
    }

    public JSONExecutor(JContext context) {
        this.context = context;
    }

    @Override
    protected Lexer createLexer(CharStream input) {
        return new JQuickJSONLexer(input);
    }

    @Override
    protected Parser createParser(TokenStream tokens) {
        return new JQuickJSONParser(tokens);
    }

    @Override
    protected Object parse(Parser parser) throws JAntlrExecutionException {
        JQuickJSONParser calcParser = (JQuickJSONParser) parser;
        JQuickJSONParser.JsonContext tree = calcParser.json();
        JSONCommonVisitor visitor = new JSONCommonVisitor(context);
        Object response = visitor.visitJson(tree);
        return response;
    }
}
