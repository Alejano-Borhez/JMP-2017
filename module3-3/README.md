#CDP Module JMP7.4: Backend: Classloading 

1. Implement Custom Classloader:
<p> Custom classloader should be able to load compiled Semaphore.class file.
Class path is passed via console on program start.
Status progress/success/error should be output to console.
Semaphore object should be instantiated.
Method lever should be invoked. Use reflection.
Google exmples on reflection API.
Expected to see "It works!" message on the console.

2. Extend Custom Classloader:
<p>Use custom class files from previous task.
Extend classloader by adding ability to reload class at runtime.
Program should ask continuously for a class path.
When path is entered to console - class should be reloaded.
 a. Prepare several versions of Semaphor class - change message string for output.
 b. Expected: after entering class path status messages and correct message are displayed in console.

3. Implement Secure Classloader (This Task Is Optional):
<p>Extend URLClassLoader by adding security checks with SecurityManager to it.
Checkout JAAC project - available in git.
Get familiar with it. Modify it to add required.
 a. Implement classloader 
 b. Add various security checks before actually loading class 
 c. Validate policy file and classes - check env paths 
 d. Build multiple jars using build.cmd script 
 e. Start program using run.cmd 
 f. Login with test:test

Expected: 
 A. When user is authorized - class is loaded and method lever is invoked
 B. When user is not authorized - access denied exception is output to console

## To run an application
1. Package project from root `mvn clean package`
2. To run first part type:
 `java -cp module3-3/target/module3-3-0.0.1-SNAPSHOT.jar -Dcp=old com.epam.brest.jmp.app.PartOneApp` for one code source and
 `java -cp module3-3/target/module3-3-0.0.1-SNAPSHOT.jar -Dcp=new com.epam.brest.jmp.app.PartOneApp` for another
3. To run second part type:
 `java -cp module3-3/target/module3-3-0.0.1-SNAPSHOT.jar com.epam.brest.jmp.app.PartTwoApp`
 Type `exit` during execution to quit