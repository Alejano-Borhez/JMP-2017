#CDP Module JPM14: JPA with Hibernate
1. add Hibernate dependency, perform a standart setup
2. create a few related entities, annotate persistent fields. Try to use maximum framework capabilities (Tables mapping strategies, ManyToOne/OneToMany relationships, Cascade, orpahRemoval)
3. add Repository (DAO) layer with commands/queries, which peform operations on created entities
4. invoke Repository from Service layer
5. bind Service layer with Controllers

#CDP Module JPM13: Spring MVC

1. Implement Spring Controller with template resolver
2. Implement Locale Resolver
3. Implement Spring Security

##HowTo:
Analogous to Previous module.
Use credentials: admin 123



#CDP Module JPM12: Infrastructure Apache/Tomcat
1. Configure the Tomcat and Apache integration with mod_jk.so module.
2. Build multi-module web application and deploy with tomcat manager application (text/script mode).
3. Static (html, css, js) publish to apache, dynamic to tomcat.
4. Test and write readme, how mentor can deploy it and check that it is working.

##HowTo:

1. Install apache + mod_jk.so module + set config and workers to map tomcat's urls to apache's
2. Install tomcat and enable ajp13 connector
3. Set tomcat_home in gradle.properties
4. Run `gradlew clean build -PdeployTomcat=true` (omit `deployTomcat` gradle property or use any other than `true` value to skip war deployment)
5. Check that app is available at `http://{tomcat.host}:{tomcat.port}/jmp/user/1/user/4`
6. Check that app is available at `http://{apache.host}:80/jmp/user/1/user/4`
7. Deployment is also made using Jenkins job that builds a gradle project and deploys it
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