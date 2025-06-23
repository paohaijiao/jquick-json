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

import com.github.paohaijiao.parser.JQuickJSONBaseVisitor;
import com.github.paohaijiao.param.JContext;
import org.apache.commons.lang3.StringUtils;

public class JSONCoreVisitor extends JQuickJSONBaseVisitor {

    protected JContext context;



    protected String trimDateSplit(String date) {
        if (StringUtils.isBlank(date)) {
            return date;
        }
        String newString=date.replace("-", "");
        String newString1=newString.replace(" ", "");
        String newString2=newString1.replace("/", "");
        return newString2.trim();
    }



}

