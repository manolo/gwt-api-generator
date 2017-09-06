package com.vaadin.polymer;

import jsinterop.annotations.JsFunction;

@JsFunction
public interface PolymerFunction<RET, ARG>  {
    public RET call(ARG arg);
}