package com.github.paohaijiao.merge;

import com.github.paohaijiao.model.JSONArray;
import com.github.paohaijiao.model.JSONObject;

public interface JsonMerger {

    JSONObject merge(JSONObject... objects);


    JSONArray merge(JSONArray... arrays);


    JsonMerger strategy(JMergeStrategy strategy);
}
