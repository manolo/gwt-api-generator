package com.vaadin.polymer.elemental;

import jsinterop.annotations.JsFunction;

@JsFunction
@com.google.gwt.core.client.js.JsFunction
public interface EventListener<T extends Event> {
    void handleEvent(T event);
}
