JSON Service
============

**JSON-RPC** library for Java

Project's [Home Page](http://stefaniuk.github.com/json-service/ "Project's Home Page")

Quick Start
-----------

1. Clone the repository using `git clone --recursive` or run `git submodule init` and `git submodule update` after you clone repository non-recursively.
2. Prepare JavaScript files from command line (this is optional). To do that go to `resources/dojotoolkit/` directory and execute Ant command `ant minify`.
3. Build project using Maven command `mvn clean install source:jar javadoc:javadoc javadoc:jar` from project's main directory.
4. Deploy example projects to your servlet container using WAR files or deployment scripts `json-service-example?/deploy/json-service-example?.xml`.

Useful Resources
----------------

* [JSON-RPC 2.0 Specification](http://www.jsonrpc.org/specification)
* [Service Mapping Description Proposal](http://dojotoolkit.org/reference-guide/1.8/dojox/rpc/smd.html)
* [dojox.rpc.Service](http://dojotoolkit.org/reference-guide/1.8/dojox/rpc/Service.html)

About
-----

This is JSON-RPC protocol implementation in Java with Service Mapping Description support.

For more information, visit <http://stefaniuk.github.com/json-service/>.

License
-------

See `LICENSE` file.

> Copyright (c) 2010-2012 Daniel Stefaniuk
