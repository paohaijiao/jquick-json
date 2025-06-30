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
package com.github.paohaijiao.mapper;

import java.math.BigDecimal;
import java.util.Date;

public interface JNativeMapper {

    public String getString(String key);

    public Integer getInteger(String key);

    public Long getLong(String key);

    public Double getDouble(String key);

    public Boolean getBoolean(String key);

    public Date getDate(String key);

    public BigDecimal getBigDecimal(String key);

    public Float getFloat(String key);

}
