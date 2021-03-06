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

Property based testing using ScalaCheck is used for testing prime generation. ScalaCheck started as a clone of
 QuickCheck for Haskell. One of QuickCheck's writers, John Hughes, has also written an Erlang implementation. Property
 based testing is useful here as it is not viable to write tests for all possible values of an Integer N.

The Parfait pattern is used for dependency injection. This uses implicit parameters: if an implicit variable is in
scope then the Scala compiler will pass it as a parameter. Implicit parameters can be abused to make code very hard to
 understand.
 
DI allows me to mock the Printer so I can write more complete end-to-end tests in Scala. I'm using Mockito, probably the
most common JVM mocking framework.

Methodology
===========

I am committing when writing a failing test to demonstrate my application of TDD. Normally I wouldn't commit if a test
is failing.

Algorithm
===========

Uses a Sieve of Eratosthenes to generate prime numbers. Rather than 'mark off' multiples of primes, checks if each
candidate number is a multiple of a prime, i.e. the ratio is a whole number.
