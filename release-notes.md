# Release Notes for mvc-tools
## Changes in version 2.2 (2015-09-01)
*   [2015-09-01] [@kyberorg](https://github.com/kyberorg):
    Moved to Gradle and distribution via bintray and JCenter
    [#19](https://github.com/kyberorg/mvc-tools/issues/19).
*   [2015-09-01] [@kyberorg](https://github.com/kyberorg):
    Updated to Spring 4.2.0
    [#19](https://github.com/kyberorg/mvc-tools/issues/19).
*   [2015-09-01] [@kyberorg](https://github.com/kyberorg):
    Added webapp for testing
*   [2015-09-01] [@kyberorg](https://github.com/kyberorg):
    Added encoding parameter to ```ServletTools.getRequestBody()``` method
    [#16](https://github.com/kyberorg/mvc-tools/issues/16).
*   [2015-09-01] [@kyberorg](https://github.com/kyberorg):
    ```ServletTools.requestBody(HttpServletRequest)``` renamed to ```ServletTools.requestBodyAfterLogger(HttpServletRequest)```
    [#17](https://github.com/kyberorg/mvc-tools/issues/17).
## Changes in version 2.1 (2015-03-23)
*   [2015-03-23] [@kyberorg](https://github.com/kyberorg):
    Added new Renderer with builder pattern ```Reply```
    [#13](https://github.com/kyberorg/mvc-tools/issues/13).
    
## Changes in version 2.0
*   [2015-03-16] [@kyberorg](https://github.com/kyberorg):
    Moved to Spring 4
    [#11](https://github.com/kyberorg/mvc-tools/issues/11).
*   [2015-03-16] [@kyberorg](https://github.com/kyberorg):
    Separated DB related code and dependencies to another repo.
    [#12](https://github.com/kyberorg/mvc-tools/issues/12).
## Changes in version 1.2
* [2015-03-16] [@kyberorg](https://github.com/kyberorg):
  Added methods ```render(int status)``` to AppOut and AppErr
  [#10](https://github.com/kyberorg/mvc-tools/issues/10).

## Changes in version 1.1.1

This is the production use version of mvc-tools.

* [2015-01-09] [@kyberorg](https://github.com/kyberorg):
  Added method getFullUrl() which reconstructs original requesting URL from servlet request
  [#7](https://github.com/kyberorg/mvc-tools/issues/7).
* [2015-01-09] [@kyberorg](https://github.com/kyberorg):
  Moved ServletTools from internal package as it was found useful at external software
  [#8](https://github.com/kyberorg/mvc-tools/issues/8).
