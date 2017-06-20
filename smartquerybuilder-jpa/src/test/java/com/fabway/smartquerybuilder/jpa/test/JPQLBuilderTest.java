package com.fabway.smartquerybuilder.jpa.test;

import static com.fabway.smartquerybuilder.Predicates.Empty;
import static com.fabway.smartquerybuilder.Predicates.IsZero;
import static com.fabway.smartquerybuilder.Predicates.NotNull;
import static com.fabway.smartquerybuilder.Predicates.Null;
import static com.fabway.smartquerybuilder.Predicates.True;
import static com.fabway.smartquerybuilder.Predicates.and;
import static com.fabway.smartquerybuilder.Predicates.not;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.junit.Test;
import org.mockito.Mockito;

import com.fabway.smartquerybuilder.jpa.builders.JPQLBuilder;

public class JPQLBuilderTest {

    @Test
    public void testBuild() {
        Long roleId = null;
        String level = "root";

        JPQLBuilder jb = new JPQLBuilder()
                .gp("level", level, not(Empty))
                .gp("role", roleId, and(not(Null),not(IsZero)))
                .from("Users u")
                .from(" inner join Role role on role.id = u.role_id ", "role")
                .where("u.id > 0")
                .and("role.id = :role ", "role")
                .and("u.level = :level", "level")
                .orderBy("u.login, u.level", "level");

        String sqlCount = jb.copy().select("count(u.id)").get();
        String sqlSelect = jb.select("u.*").build();

        assertEquals("select count(u.id) from Users u where u.id > 0 and u.level = :level order by u.login, u.level",
                sqlCount);
        assertEquals("select u.* from Users u where u.id > 0 and u.level = :level order by u.login, u.level", sqlSelect);
        @SuppressWarnings("serial")
        Map<String, Object> params = new HashMap<String, Object>(){{
            put("level", level);
        }};
        assertEquals(params.toString(), jb.namedParams().toString());
    }
    
    @Test
    public void testGroupBy(){
        
        String userName = "a", userCountry = "b";

        JPQLBuilder jb = new JPQLBuilder()
                .g("group", true, True)
                .gp("name", userName, NotNull)
                .gp("country", userCountry, NotNull)
                .select("count(u.id), u.country")
                .from("Users u")
                .where("u.id > 0")
                .and("u.name = ?", "name")
                .and("u.country = ?", "country")
                .groupBy("u.country", "group")
                .orderBy("u.country");
        
        System.out.println(jb.build());

        assertEquals("select count(u.id), u.country from Users u where u.id > 0 and u.name = ? and u.country = ? group by u.country order by u.country",
                jb.get());
        
    }
 
    @SuppressWarnings("unchecked")
    @Test
    public void testCreateQuery(){
        
        String userName = "a", userCountry = "b";

        JPQLBuilder jb = new JPQLBuilder()
                .gp("name", userName, NotNull)
                .gp("country", userCountry, NotNull)
                .select("count(u.id) as count, u.country as country")
                .from("Users u")
                .where("u.id > 0")
                .and("u.name = :name", "name")
                .and("u.country = :country", "country")
                .orderBy("u.country");
        
        
        String jpql = "select count(u.id) as count, u.country as country from Users u where u.id > 0 and u.name = :name "
                + "and u.country = :country order by u.country";
        
        System.out.println(jb.get());
        assertEquals(jpql,  jb.get());

        EntityManager em = Mockito.mock(EntityManager.class);
        TypedQuery<QueryResult> mockQuery = Mockito.mock(TypedQuery.class);
        when(em.createQuery(jpql, QueryResult.class)).thenReturn(mockQuery);
        

        TypedQuery<QueryResult> createdQuery = jb.createQuery(em, QueryResult.class);
        
        Mockito.verify(em).createQuery(jpql, QueryResult.class);
        assertEquals(mockQuery, createdQuery);
        
        Mockito.verify(mockQuery).setParameter("name",  userName);
        Mockito.verify(mockQuery).setParameter("country",  userCountry);
    }
    

    class QueryResult {
        int count;
        String country;
        
        public int getCount() {
            return count;
        }
        public void setCount(int count) {
            this.count = count;
        }
        public String getCountry() {
            return country;
        }
        public void setCountry(String country) {
            this.country = country;
        }
        
    }
    
}
