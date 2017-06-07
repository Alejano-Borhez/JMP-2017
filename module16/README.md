#CDP Module JMP16: Multi-Threading  

1. Please create CLI application which scans a specified folder and provides detailed statistics:
* File count
* Folder count
* Size (sum of all files size) (Similar like windows context menu "properties")
 
2. Since the folder may contain huge amount of files the scanning process should be executed in a separate thread
displaying an informational message with some simple animation like progress bar in CLI
(up to you, but I'd like to see that task is in progress).
 
3. Once task is done the statistics should be displayed in the output immediately.
4. Additionally there should be ability to interrupt the process pressing some reserved key (for instance "c").
 
5. Advanced part: Play with Files.walk or Fork-Join Framework for implementation parallel scanning