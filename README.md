To Run
======

Requires a JDK: tested on JDK8
Requires sbt or Activator to build. I suggest the Activator mini-package from https://www.typesafe.com/activator/download

To start the Activator console, from within the same directory as this file:
`./my-install-path/activator`

To run tests
`test`

To run the program with N=8
`run 8`

Either command will, if necessary, download dependencies and compile.

About
=====

Built with Scala 2.11, which is what my recent experience is in. I've attempted to briefly explain some of Scala's more
unusual features in comments.

Tested with ScalaTest. This framework supports multiple styles: I use WordSpec partly as I find it human readable,
partly because it is similar to Specs2 which I have used in production but dislike for various reasons. A couple of
Scala features that this uses that may look odd are:

* Infix operator notation for methods. I can write either a.do(b) or (a do b). All operators in Scala are in fact
 methods.
* Implicit type conversions, enabling additional methods on types such as 'should' on String. Other languages may have
other mechanisms for extending types.

Methodology
===========

I am committing when writing a failing test to demonstrate my application of TDD. Normally I wouldn't commit if a test
is failing.