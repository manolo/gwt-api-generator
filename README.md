# Vaadin gwt-api-generator

Vaadin gwt-api-generator is a tool that produces GWT Java APIs for JavaScript libraries provided as input, assuming their APIs are decorated with JSDoc annotations.

Currently the generator only supports Web Components written in Polymer 1.0 syntax. Support for other type of JavaScript sources might be added in the future.

Original motivation behind the project was to provide GWT community an easy access to the elements in [Vaadin Elements](https://github.com/vaadin/vaadin-elements) library.

## Installation and Usage

- Installation
```shell
$ npm install -g vaadin/gwt-api-generator
```
> If you've installed node and npm using `sudo`, installing packages globally will also require you to use `sudo`. See [http://givan.se/do-not-sudo-npm/](http://givan.se/do-not-sudo-npm/) how to remedy the situation.

- Generating the resourcer for bower packages installed in your bower_components folder
```shell
$ bower install my-web-component
$ gwt-api-generator
```
- Generating the resources for a library
```shell
$ gwt-api-generator --package=PolymerElements/paper-elements
```
- Generating the resources with a custom groupId and artifactId
```shell
$ gwt-api-generator --package=PolymerElements/paper-elements \
                    --groupId=com.foo --artifactId=bar
```
- Generating the resources for a non-maven structure
```shell
$ gwt-api-generator --package=PolymerElements/paper-elements
                    --javaDir=src --resourcesDir=src
```
- Packaging the result as a ready-to-use jar file
```shell
$ gwt-api-generator --package=PolymerElements/paper-elements --pom
$ mvn package
```

> Bower can be configured by placing a `.bowerrc` into the folder where `gwt-api-generator` command is run.

## Pre-built packages

### Paper and Iron elements

Vaadin officially maintains and supports a pre-built package deployed at Maven Central repository containing all the resources needed for using Polymer [paper-elements](https://elements.polymer-project.org/browse?package=paper-elements) and [iron-elements](https://elements.polymer-project.org/browse?package=iron-elements) in a GWT application.

Build script, demo and usage instructions for the project are available [here](https://github.com/vaadin/gwt-polymer-elements).

### Vaadin elements

Comming soon.

## About GWT 2.7/2.8 compatibility

Vaadin gwt-api-generator produces `@JsType` interfaces for JS Element level access from Java Objects.
Generated classes are written using Java 1.7 syntax, and rely on GWT JSInterop available as an experimental feature from GWT 2.7.0.

Notice that even though the generated code is GWT 2.7 compatible for now, it's recommended to use GWT 2.8-SNAPSHOT for better stability.

Produced interfaces don't currently utilize the full GWT 2.8 JSInterop API. For example `@JsFunction` is replaced by generic JavaScriptObject parameters leaving the responsibility of wrapping callbacks to the developer.

The plan is however to support `@JsFunction` among other JSInterop features in future releases so consider GWT 2.7 support deprecated at this point.
