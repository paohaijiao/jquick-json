package com.github.paohaijiao.merge;

import com.github.paohaijiao.model.JSONArray;
import com.github.paohaijiao.model.JSONObject;

public interface JMergeStrategy {

    JSONObject mergeObjects(JSONObject target, JSONObject source);


    JSONArray mergeArrays(JSONArray target, JSONArray source);
}
