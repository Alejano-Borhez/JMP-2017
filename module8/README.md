#CDP Module JMP7.7: Spring Core  

Write your own annotation-based or XML-based ORM which parses specific class and generates SQL-queries for CRUD (or SCRUD) operations. Your MiniORM should have one entry point, which supports CRUD operations for parsed class passed as a parameter in .save .load .update .delete methods. Inheritance support will be a plus.

## Notes:
- Created another implementation of GenericDao from module 7;
- Used Custom annotations and Reflection API to perform all CRUD operations;
- Tested with EasyMock and on a real MySQL database in transactional fashion;
- Tables dumps are in test/resources folder;