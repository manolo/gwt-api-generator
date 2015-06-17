# Vaadin gwt-api-generator

Vaadin gwt-api-generator is a tool that produces GWT APIs for JavaScript libraries provided as input, assuming their APIs are decorated with JSDoc annotations.

Currently the generator only supports Web Components written in Polymer 1.0 syntax. Support for other type of JavaScript sources might be added in the future.

Original motivation behind the project was to provide GWT community an easy access to the elements in [Vaadin Components](https://github.com/vaadin/components) library.

## Installation and Usage

- Installation
```shell
$ sudo npm install -g vaadin/gwt-api-generator
```
- Generating the resources for a library
```shell
$ gwt-api-generator --package=PolymerElements/paper-elements
```
- Generating the resources with a custom groupId and artifactId
```shell
$ gwt-api-generator --package=PolymerElements/paper-elements --groupId=com.foo --artifactId=bar
```
- Generating the resources for a non-maven structure
```shell
$ gwt-api-generator --package=PolymerElements/paper-elements --javaDir=src --resourcesDir=src
```
- Packaging the result as a ready-to-use jar file
```shell
$ gwt-api-generator --package=PolymerElements/paper-elements --pom
$ mvn package
```


## Pre-built packages

### Paper elements and Iron elements

There's a pre-built example package in the Maven Central containing all the resources needed for using Polymer [paper-elements](https://elements.polymer-project.org/browse?package=paper-elements) and [iron-elements](https://elements.polymer-project.org/browse?package=iron-elements) in a GWT application. Build script and a demo for the project is available [here](https://github.com/vaadin/gwt-polymer).
To start using the library...

- Include the following dependency to pom.xml:
```XML
<dependency>
   <groupId>com.vaadin.polymer</groupId>
   <artifactId>elements</artifactId>
   <version>1.0-alpha1</version>
   <scope>provided</scope>
</dependency>
```

- Inherit the package GWT module in .gwt.xml file:
```XML
<inherits name="com.vaadin.polymer.Elements"/>
```

Remember to add the JSInterop flag to the compiler in order to make it run: `-XjsInteropMode JS`

## About GWT 2.7/2.8 compatibility

Vaadin gwt-api-generator produces @JsType interfaces for JS Element level access from Java Objects.
Generated classes are written using Java 1.7 syntax, and rely on GWT JSInterop available as an experimental feature from GWT 2.7.0.

Notice that even though the generated code is GWT 2.7 compatible for now, it's recommended to use GWT 2.8-SNAPSHOT for better stability.

The produced interfaces don't currently utilize full GWT 2.8 JSInterop API. For example @JsFunction is replaced by generic JavaScriptObject parameters leaving the responsibility of wrapping callbacks to the developer.

The plan is however to support @JsFunction among other JSInterop features in future releases so consider GWT 2.7 support deprecated at this point.
