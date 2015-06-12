package com.vaadin.components.gwt.polymer.client;

import java.util.HashSet;
import java.util.Set;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;

public abstract class Polymer {

    private static Set<String> urlImported = new HashSet<>();

    /**
     * Ensures that the tagName has been registered, otherwise injects
     * the appropriate &lt;import&gt; tag in the document header.
     *
     * It loads the component from following the convention:
     * bower_components/tagName/tagName.html
     */
    public static void ensureTag(String tagName) {
        ensureHTMLImport(tagName + "/" + tagName + ".html");
    }

    /**
     * Ensures that the tagName has been registered, otherwise injects
     * the appropriate &lt;import&gt; tag in the document header.
     *
     * @param tagName
     * @param url component path relative to bower_components folder
     */
    public static void ensureTag(String tagName, String src) {
        ensureHTMLImport(src);
    }

    /**
     * Inserts the appropriate &lt;import&gt; of a component given by url.
     *
     * Note that bower_components will be prefixed to the url always.
     *
     * @param url component path relative to bower_components folder
     */
    public static void ensureHTMLImport(String url) {
        String href = "bower_components/" + url;
        if (!urlImported.contains(href)) {
            Element link = Document.get().createLinkElement();
            link.setAttribute("rel", "import");
            link.setAttribute("href", GWT.getModuleBaseForStaticFiles() + href);
            Document.get().getHead().appendChild(link);
            urlImported.add(href);
        }
    }

    /**
     * Returns a new instance of the Element. It loads the webcomponent
     * from the bower_components/src url if it was not loaded yet.
     */
    public static <T> T createElement(String tagName, String src) {
        ensureTag(tagName);
        return (T)Document.get().createElement(tagName);
    }

    /**
     * Returns a new instance of the Element. It loads the webcomponent
     * from the bower_components/tagName/tagName.html url if not loaded yet.
     */
    public static <T> T createElement(String tagName) {
        ensureTag(tagName);
        return (T)Document.get().createElement(tagName);
    }

    /**
     * Returns the JsInterop instance of Document
     */
    public static com.vaadin.components.gwt.polymer.client.webapi.Document getDocument() {
        return (com.vaadin.components.gwt.polymer.client.webapi.Document)Document.get();
    }
}
