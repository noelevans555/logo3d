# Logo3d

A compiler for the [Logo programming language](https://en.wikipedia.org/wiki/Logo_%28programming_language%29) featuring language extensions for navigations in three-dimensional space. The compiler takes a logo3d program string and outputs a collection of colored lines defined in terms of their X, Y, & Z coordinates.


The compiler is supplied with a demo visualizer and three [predefined programs](https://github.com/noelevans555/logo3d/blob/master/src/main/java/com/noelevans555/logo3d/DemoPrograms.java):

**GRID_SPHERE**

<img src="images/grid_sphere.png" width="600"/>

**SPIRAL_SHELL**

<img src="images/spiral_shell.png" width="600"/>

**RECURSIVE_TREE**

<img src="images/recursive_tree.png" width="600"/>

## Prerequisites

The following dependencies are needed to build the project and run the demo application.

* Java, version 11+
* [Maven](https://maven.apache.org/)

## Running the demo application

The demo application is run from the downloaded/cloned project directory, i.e. the directory containing `pom.xml`.

To run the application:

```
mvn exec:java
```

The demo application runs GRID_SPHERE from the [predefined programs](https://github.com/noelevans555/logo3d/blob/master/src/main/java/com/noelevans555/logo3d/DemoPrograms.java). An alternative program, or custom program string, can be specified [here](https://github.com/noelevans555/logo3d/blob/master/src/main/java/com/noelevans555/logo3d/DemoApplication.java#L36).

## Running the tests

The project contains unit tests, static analysis tests ([SpotBugs](https://spotbugs.github.io/)), code coverage tests ([JaCoCo](https://www.jacoco.org/jacoco/)) and code style tests ([Checkstyle](https://checkstyle.sourceforge.io/)). To run all, specify:

```
mvn verify
```

Verification will fail if any test(s) fail. Code coverage thresholds are set at 90% for both line and branch coverage. The code coverage report is written to `target/site/jacoco/index.html`

Checkstyle is used to enforce the Sun coding conventions, see: [checkstyle.xml](./checkstyle.xml)

## Authors

Concept, design & implementation by [Noel Evans](https://www.linkedin.com/in/noelevans/)

## License

This project is licensed under the MIT License - see the [LICENSE.txt](LICENSE.txt) file for details.

## Acknowledgments

* Thanks to [Benoit](https://github.com/bsouffle) for code reviews.
* [Dan Mellinger](https://www.linkedin.com/in/daniel-mellinger-35909648/) for collaboration on the earlier iOS implementation.
* [PurpleBooth](https://github.com/PurpleBooth), for this README template.

