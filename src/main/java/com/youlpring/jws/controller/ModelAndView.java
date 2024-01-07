package com.youlpring.jws.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModelAndView {

    private final Map<String, Object> model = new HashMap<>();
    private String viewName;

    public ModelAndView() {}

    public List<String> getModelKeys() {
        return new ArrayList<>(model.keySet());
    }

    public Object getModelValue(String key) {
        return model.get(key);
    }

    public String getViewName() {
        return viewName;
    }

    public void addModel(String key, Object value) {
        model.put(key, value);
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }
}
