package com.vaadin.polymer.elemental;

import static jsinterop.annotations.JsPackage.GLOBAL;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

import com.google.gwt.core.client.JsArray;

@JsType(isNative=true, namespace=GLOBAL)
@com.google.gwt.core.client.js.JsType
public interface Template extends HTMLElement {
    @com.google.gwt.core.client.js.JsProperty
    @JsProperty String getIs();
    @com.google.gwt.core.client.js.JsProperty
    @JsProperty void setIs(String value);

    @com.google.gwt.core.client.js.JsProperty
    @JsProperty String getAs();
    @com.google.gwt.core.client.js.JsProperty
    @JsProperty void setAs(String value);

    @com.google.gwt.core.client.js.JsProperty
    @JsProperty String getIdexAs();
    @com.google.gwt.core.client.js.JsProperty
    @JsProperty void setIndexAs(String value);

    @com.google.gwt.core.client.js.JsProperty
    @JsProperty JsArray<?> getItems();
    @com.google.gwt.core.client.js.JsProperty
    @JsProperty void setItems(JsArray<?> items);
  
    @com.google.gwt.core.client.js.JsProperty
    @JsProperty void setFilter(Function<Boolean, ?> items);
    
    @com.google.gwt.core.client.js.JsProperty
    @JsProperty void setIf(String value);
}
