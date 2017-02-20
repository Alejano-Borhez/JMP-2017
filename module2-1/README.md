# Deadlock with 4 shared resources

In this project we have 2 entities:
FileWorker - an object that performs some work with 2 consequential resources
FileResource - an object that represents some file resource to work on

In main() method we create 4 instances of FileWorker and 4 instances of FileResource.
All workers start their work in 4 Threads.
As FileResource object assumes concurrent-safe access,
every FileWorker performs actions with FileResources
in synchronized() block consequentially with 2 FileResources.
 
As a result we have a deadlock with 4 resources in 4 threads.
This results are represented in [thread dumps](./docs).
  
## To run an application
1. Refer to [Main Readme](../README.md)