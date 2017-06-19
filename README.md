[![Build Status](https://travis-ci.org/fabricioepa/smartquerybuilder.svg?branch=master)](https://travis-ci.org/fabricioepa/smartquerybuilder)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)


# Smart Query Builder Project

A fluent way for building dynamic queries based on preconditions.

---

## Basic Usage: Statement

```java
import static com.fabway.smartquerybuilder.Predicates.*;
//..

boolean admin = false;
Integer age = null;
  
StatementBuilder sb = new StatementBuilder()
  .given("pAge", age, and(not(IsZero), not(Null))
  .given("pAdmin", admin, True)
  .param(age, "pAge")
  .put("DELETE FROM USER WHERE ID > 0")
  .put(" AND AGE >= ? ", "pAge")
  .put(" AND IS_ADMIN = 1", "pAdmin");
  
  //Now you can use the statement
  sb.get() //returns the mounted statement "DELETE FROM USER WHERE ... "
  
  //returns the parameters in the position that should be used for the built statement
  sb.params();
 ```
 
 ## SQL Queries and Counting Using COPY
```java
import static com.fabway.smartquerybuilder.Predicates.*;
//..

Long roleId = null;
String level = "root";

SQLQueryBuilder sb = new SQLQueryBuilder()
                            .given("p1", roleId, and(not(IsZero), NotNull))
                            .given("p2", level, not(Empty))
                            .param(roleId, "p1")
                            .param(level, "p2")
                            .from(" from Users u")
                            .from(" inner join Role role on role.id = u.role_id ", "p1")
                            .where(" where u.id > 0")
                            .where(" and role.id = ? ", "p1")
                            .where(" and u.level = ?", "p2")
                            .orderBy(" order by u.login, u.level", "p2");
 
String sqlCount = sb.clone().select("select count(u.id) ").get();
String sqlSelect = sb.select("select u.* ").build();
List<Object>  params = sb.paramsAsArray();
 ```
 
## Keep It Easier Using: ALIAS


TODO...
