#CDP Module JMP7.5: Architecture: Structural Patterns: Decorator  

Create a PersonOutputStream that implements the writePerson(Person) method and can decorate any given OutputStream.
Create a PersonOutputStream that implements the readPerson() method that returns a Person and can decorate any given InputStream.
The PersonOutputStream decorator must check if the name of the person starts with a capital letter and if it doesn't –
it should update it before writing it to the destination.
 
The PersonInputStream decorator must check if the name of the person starts with a low letter and if it doesn’t –
it should update it before writing it to the destination.
 
Write a program that uses the two decorators to write and read persons to and from a file


## To run an application
Refer to [Main Readme](../README.md)