#CDP Module JMP7.7: Spring Core  

Use task from https://kb.epam.com/display/EJAVACC/JaMP.Framework.Spring.Core.Tasks

It should be simple application  NOT WEB. Just in main method create Spring context and use service beans.
   
It should be maven app.

Please intoduce several Spring profiles. Use Java based config for Spring

## Notes:
- Used Java configurations with 2 profiles: "Prod", "ServiceTest". Second profile is used to create mocks for DAO's
- During bootstrapping "Prod" context some entities of Users and Tasks are added to storage through DAO
- Run it after build by invoking `java -jar com.epam.brest.jmp.app.TaskManagerApplication`
- Enjoy!