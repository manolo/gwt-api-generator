#!/usr/bin/env node
'use strict';
var gulp = require('gulp')
require('../gulpfile');

require('coa').Cmd()
  .name(process.argv[1])
  .title('Vaadin Metathesis')
  .helpful()
  .act(function() {
    console.log(this.usage());
  })
  .opt()
    .name('version')
    .title('Version')
    .short('v')
    .long('version')
    .flag()
    .act(function(opts) {
      return JSON.parse(require('fs').readFileSync(__dirname + '/../package.json'))
        .version;
    })
    .end()
  .cmd()
    .name('polymer')
    .title('Generate GWT API classes from Polymer API')
    .helpful()
    .opt()
      .name('package')
      .req()
      .title('Bower package(s) to use. Multiple packages can be defined with: package="foo bar" or package=foo,bar')
      .long('package')
      .end()
    .opt()
      .name('pom')
      .flag()
      .title('Adds a pom.xml file to the root directory to enable easy packaging')
      .long('pom')
      .end()
    .opt()
      .name('Java directory')
      .title('Directory where the Java files are generated. (Default: src/main/java)')
      .long('javaDir')
      .end()
    .opt()
      .name('Resources directory')
      .title('Directory where the Resource files are generated. (Default: src/main/resources)')
      .long('resourcesDir')
      .end()
  .act(function() {
    gulp.start('default');
  })
  .end()
  .run(process.argv.slice(2));