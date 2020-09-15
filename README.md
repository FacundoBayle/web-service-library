# Web Service Library

The idea of ​​this project is to try to reproduce some of the functionalities of Spring.

Internally, the library has to be able to:

* Receive an HTTP / 1.1 call and interpret it.
* Create controllers to which to derive the call based on the method and the path of the request, obtaining the different parameters necessary for its execution.
* Serialize and deserialize the different media types.
* Allow dependency injection.

Based on [pierre](https://github.com/gamestoy/pierre) project.