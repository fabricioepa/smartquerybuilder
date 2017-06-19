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
  .given("hasAge", age, and(not(IsZero), not(Null))
  .given("isAdmin", admin, True)
  .param(age, "hasAge")
  .put("DELETE FROM USER WHERE ID > 0")
  .put(" AND AGE >= ? ", "hasAge")
  .put(" AND IS_ADMIN = 1", "isAdmin");
  
  //Now you can use the statement
  sb.get() //returns the mounted statement "DELETE FROM USER WHERE ... "
  
  //returns the parameters in the position that should be used for the built statement
  sb.params();
 ```
 
 ## SQL Queries and Counting Using CLONE
```java
import static com.fabway.smartquerybuilder.Predicates.*;
//..

Long roleId = null;
String level = "root";

SQLQueryBuilder sb = new SQLQueryBuilder()
                            .given("c1", roleId, and(not(IsZero), NotNull))
                            .given("c2", level, not(Empty))
                            .param(roleId, "c1")
                            .param(level, "c2")
                            .from(" from Users u")
                            .from(" inner join Role role on role.id = u.role_id ", "c1")
                            .where(" where u.id > 0")
                            .where(" and role.id = ? ", "c1")
                            .where(" and u.level = ?", "c2")
                            .orderBy(" order by u.login, u.level", "c2");
 
String sqlCount = sb.clone().select("select count(u.id) ").get();
String sqlSelect = sb.select("select u.* ").build();
List<Object>  params = sb.paramsAsArray();
 ```
 
## Keep It Easier Using: ALIAS


TODO...
