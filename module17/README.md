#CDP Module JMP17: Memory Management and Garbage Collection  

Write a simple program that repeatedly creates some objects structure (for example ArrayList collection)
and then release memory (set reference to null).
Please note that in order to avoid JVM optimizations use some kind of randomness.
Then try to run this program using different types of GC (listed below)
and monitor memory utilization using Java VisualVm.
 
Use the following GC configurations:
1. Serial Collector with the following parameters: (-XX:+UseSerialGC)
 * the 6m initial heap size for when the JVM starts (-Xms)
 * the18m maximum heap size (-Xmx)
 * the 2m size of the Young Generation (-XX:NewSize= -XX:NewSizeMax=)
 * the 20m starting size of the MetaSpace (-XX:MetaspaceSize=)
 * the 30 maximum size of the MetaSpace (-XX:MaxMetaspaceSize=)
### Resulting cli command:
```java \
-Xms6m -Xmx18m \
-XX:MetaspaceSize=20m -XX:MaxMetaspaceSize=30m \
-XX:NewSize=2m -XX:NewSizeMax=2m \
-XX:+UseSerialGC \
-jar target/module17-0.0.1-SNAPSHOT.jar
```
### Resulting JvisualVM monitoring:
![Case 1](/module17/report/1 Case.png)
2. Parallel Collector with the following parameters:
 * the 3m initial heap size for when the JVM starts
 * the12m maximum heap size
 * the 1m size of the Young Generation
 * the 20m starting size of the MetaSpace
 * the 20 maximum size of the MetaSpace
### Resulting cli command:
```java \
-Xms3m -Xmx12m \
-XX:MetaspaceSize=20m -XX:MaxMetaspaceSize=20m \
-XX:NewSize=1m -XX:MaxNewSize=1m \
-XX:+UseParallelGC -XX:-UseParallelOldGC \
-jar target/module17-0.0.1-SNAPSHOT.jar
```
![Case 2](/module17/report/2 Case.png)
3. Parallel Old Collector with the following parameters:
 * the 9m initial heap size for when the JVM starts
 * the 18m maximum heap size
 * the 3m size of the Young Generation
 * the 40m starting size of the MetaSpace
 * the 40 maximum size of the MetaSpace
 ```java \
 -Xms9m -Xmx18m \
 -XX:MetaspaceSize=40m -XX:MaxMetaspaceSize=40m \
 -XX:NewSize=3m -XX:MaxNewSize=3m \
 -XX:+UseParallelGC -XX:+UseParallelOldGC \
 -jar target/module17-0.0.1-SNAPSHOT.jar
 ```
![Case 3](/module17/report/3 Case.png)
4. Concurrent Mark Sweep (CMS) Collector with 2 Parallel CMS Threads with the following parameters:
 * the 6m initial heap size for when the JVM starts
 * the18m maximum heap size
 * the 2m size of the Young Generation
 * the 20m starting size of the MetaSpace
 * the 30 maximum size of the MetaSpace
```java \
-Xms6m -Xmx18m \
-XX:MetaspaceSize=20m -XX:MaxMetaspaceSize=30m \
-XX:NewSize=2m -XX:MaxNewSize=2m \
-XX:+UseConcMarkSweepGC \
-jar target/module17-0.0.1-SNAPSHOT.jar
```
![Case 4](/module17/report/4 Case.png)
5. G1 Garbage Collector with the following parameters:
 * the 4m initial heap size for when the JVM starts
 * the 16m maximum heap size
 * the 2m size of the Young Generation
 * the 12m starting size of the MetaSpace
 * the 18 maximum size of the MetaSpace
```java \
-Xms4m -Xmx16m \
-XX:MetaspaceSize=12m -XX:MaxMetaspaceSize=18m \
-XX:NewSize=2m -XX:MaxNewSize=2m \
-jar target/module17-0.0.1-SNAPSHOT.jar
```
![Case 5](/module17/report/5 Case.png)


![Case No GC opts](/module17/report/No GC opts.png)

![Case No Memory opts](/module17/report/No Memory opts Case.png)

What should we get in result in repository:
 * Source code of the program README file with all GC command line configuration parameters
 * Screenshots from VisualVM for all types of GC running for 5 mins.