# rendertheme-color-viewer
A simple Java based application that analyzes MapsForge rendertheme XML's and collects the used colors into a viewable HTML file.

| CodeQL                                                        | Code coverage                                                |
| ------------------------------------------------------------- | ------------------------------------------------------------ |
| [![CodeQL](https://github.com/szrnka-peter/rendertheme-color-viewer/actions/workflows/codeql-analysis.yml/badge.svg)](https://github.com/szrnka-peter/rendertheme-color-viewer/actions/workflows/codeql-analysis.yml) | [![Code coverage](https://github.com/szrnka-peter/rendertheme-color-viewer/actions/workflows/codecov.yaml/badge.svg)](https://github.com/szrnka-peter/rendertheme-color-viewer/actions/workflows/codecov.yaml) [![codecov](https://codecov.io/gh/szrnka-peter/rendertheme-color-viewer/branch/main/graph/badge.svg)](https://codecov.io/gh/szrnka-peter/rendertheme-color-viewer) |

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
