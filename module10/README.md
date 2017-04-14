#CDP Module JMP7.7: Spring Core  

Gradle:
1) Создать multi-module project со структурой:
-domain
-service
-web


2) a) Каждый модуль должен собираться в jar,
b) Общий в war. (исользовать java-plugin, war-plugin)
*c) Деплой на томкат (использоать gretty ) или другой вебсервер- контенер сервлетов

3) Подключить checkstyle плагин, чекстаил можно брать из http://checkstyle.sourceforge.net/google_style.html или любой по умолчанию, цель научится работать с плагином.


4) Добавить пару простых тестов подключить cobertura-maven-plugin (или любой другой тул для проверки покрытия тестами)
построить простой отчет с выводом в файл

Все команды закомитать в readme file. например mvn clean install -DskipTests
## Notes:
`gradlew clean build` - for build
`gradlew coberturaReport` - for cobertura report
`gradlew checkstyleMain` - for checkstyle report
`gradlew jmp-web:jettyRun` - runs and makes available at http://localhost:8081/jmp/