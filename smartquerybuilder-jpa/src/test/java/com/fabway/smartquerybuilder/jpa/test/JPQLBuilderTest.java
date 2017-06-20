package com.fabway.smartquerybuilder.jpa.test;

import static com.fabway.smartquerybuilder.Predicates.Empty;
import static com.fabway.smartquerybuilder.Predicates.IsZero;
import static com.fabway.smartquerybuilder.Predicates.Null;
import static com.fabway.smartquerybuilder.Predicates.and;
import static com.fabway.smartquerybuilder.Predicates.not;
import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

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
    
}
