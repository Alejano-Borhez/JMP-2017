#CDP Module JMP7.4: Backend: Classloading 


Write a program that supports writing and reading from files and DB (Access DB using JDBC.ODBC)
1. Writing to a file includes these features:
*  Defining the file name to write or read from
*  Wrapping with a buffer
*  Writing/Reading Persons
2. Writing to the DB is also done in three steps:
*  Loading driver and creating connection
*  Person to DB serializer which breaks Objects into Record and vise versa
*  Writing/Reading Persons
3. Client chooses to work with files or DB but once the choice was made – client code is identical in both cases.
This means that and beside specifying the source (File/DB) working with the actual resource should
be transparent and include the following operations:
*  void writePerson (Person)
*  Person readPerson()
*  Person readPerson (String name)

## To run an application
Refer to [Main Readme](../README.md)