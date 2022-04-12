# rendertheme-color-viewer
A simple Java based application that analyzes MapsForge rendertheme XML's and collects the used colors into a viewable HTML file.

| CodeQL      | Code coverage    | Code quality    |
| ----------- | ---------------- | --------------- |
| [![CodeQL](https://github.com/szrnka-peter/rendertheme-color-viewer/actions/workflows/codeql-analysis.yml/badge.svg)](https://github.com/szrnka-peter/rendertheme-color-viewer/actions/workflows/codeql-analysis.yml) | [![codecov](https://codecov.io/gh/szrnka-peter/rendertheme-color-viewer/branch/main/graph/badge.svg)](https://codecov.io/gh/szrnka-peter/rendertheme-color-viewer) | [![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=szrnka-peter_rendertheme-color-viewer&metric=vulnerabilities)](https://sonarcloud.io/summary/new_code?id=szrnka-peter_rendertheme-color-viewer) [![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=szrnka-peter_rendertheme-color-viewer&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=szrnka-peter_rendertheme-color-viewer) [![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=szrnka-peter_rendertheme-color-viewer&metric=reliability_rating)](https://sonarcloud.io/summary/new_code?id=szrnka-peter_rendertheme-color-viewer) |

## Used technologies
- Java 8
- Maven 3

## Features
- configurable
- small
- portable (no installation required)

## Usage
- Start from command line with
```
java -jar rendertheme-color-out.jar src/test/resources/osmarender-test.xml
```
