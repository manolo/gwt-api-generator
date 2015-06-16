# gwt-api-generator

A generator for creating GWT JSInterop classes from Javascript libraries and Frameworks, such as Polymer.

### Installation and Usage

- Install
```shell
$ npm install -g vaadin/gwt-api-generator
```
- Generate classes from [paper-elements](https://elements.polymer-project.org/browse?package=paper-elements)
```shell
$ gwt-api-generator --package=PolymerElements/paper-elements
```
- Package a jar of the classes using an example `pom.xml`
```shell
$ gwt-api-generator --package=PolymerElements/paper-elements --pom
$ mvn package
```

