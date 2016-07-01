# kubernetes-gradle-plugin

[![Build Status](https://travis-ci.org/nbyl/kubernetes-gradle-plugin.svg?branch=master)](https://travis-ci.org/nbyl/kubernetes-gradle-plugin)[ ![Download](https://api.bintray.com/packages/nbyl/maven/kubernetes-gradle-plugin/images/download.svg) ](https://bintray.com/nbyl/maven/kubernetes-gradle-plugin/_latestVersion)

## Description

TODO: provide some description

## Development

### Releasing

* Create a branch and commit the final stuff before the release.
* When ready create a release tag: `./gradlew release -Prelease.scope=minor` (see full description at https://github.com/ajoberstar/gradle-git/wiki/Release%20Plugins)
* Open a pull request for this release and merge it afterwards
* Travis will build the release and deploy the artifacts to bintray