# Smart Query Builder Project

A fluent way for building dynamic queries based on preconditions.

---

## Basic Usage

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
  sb.getParamsList();
 ```
 
 

