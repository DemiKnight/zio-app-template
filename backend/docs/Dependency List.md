Dependency list
---


| Dependency Name         | Description                                                  | Needed for?                                                                      |
|-------------------------|--------------------------------------------------------------|----------------------------------------------------------------------------------|
| ZIO                     | Concurrency runtime, that the entire backend is built in     | Running the app                                                                  |
| ZIO-Streams             | Impelementation of streams using ZIO-effects                 | Streaming data from sources, with back-pressure and performance in mind          |
| ZIO-HTTP                | Implementation of a HTTP 1.1 server                          | Providing a REST HTTP API for the frontend                                       | 
| ZIO-Quill (hidden)      | Compile-time SQL query generation                            | Connecting/interfacing with SQL database.                                        |
| ZIO-ProtoQuill (hidden) | Scala-3 features for Quill engine                            | Allows Scala 3 features to be used in generating SQL.                            |
| Quill-jdbc-zio          | Use JDBC interface for DB acccess and ZIO effects for access | Access DB through JDBC drivers and nice ZIO-wrapper                              |
| postgresql              | Driver for accessing PostgresSQL database                    | We use PostgresSQL for our database                                              |
| HikariCP                | "high performance" connection pooling for DB access          | Allows us to use multiple DB connections to run queries                          |
| ZIO-Config              | ZIO implementation of loading/using configuration files      | Loading config options for application, including Envrionment values             |
| ZIO-Config-Typesafe     | Mainly allows usage of `.hocon` config format.               | Nice config format, including envrionment and optional variables.                |
| ZIO-Config-Magnolia     | Automatice config derivation from case classes/types         | We can set the structure of the app through case classes and consume them safely |
