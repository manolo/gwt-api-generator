package com.vaadin.polymer.elemental;

import jsinterop.annotations.JsFunction;

@JsFunction
@com.google.gwt.core.client.js.JsFunction
public interface Function<RET, ARG>  {
    public RET call(ARG arg);
}

