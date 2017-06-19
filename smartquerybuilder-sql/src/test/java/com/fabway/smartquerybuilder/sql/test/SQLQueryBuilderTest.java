package com.fabway.smartquerybuilder.sql.test;

import static com.fabway.smartquerybuilder.Predicates.Empty;
import static com.fabway.smartquerybuilder.Predicates.IsZero;
import static com.fabway.smartquerybuilder.Predicates.NotNull;
import static com.fabway.smartquerybuilder.Predicates.and;
import static com.fabway.smartquerybuilder.Predicates.not;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.fabway.smartquerybuilder.sql.builders.SQLQueryBuilder;

public class SQLQueryBuilderTest {
    @Test
    public void testBuild() {
        Long roleId = null;
        String level = "root";

        SQLQueryBuilder sb = new SQLQueryBuilder()
                .given("c1", roleId, and(not(IsZero), NotNull))
                .given("c2", level, not(Empty)).param(roleId, "c1")
                .param(level, "c2")
                .from(" from Users u")
                .from(" inner join Role role on role.id = u.role_id ", "c1")
                .where(" where u.id > 0")
                .where(" and role.id = ? ", "c1")
                .where(" and u.level = ?", "c2")
                .orderBy(" order by u.login, u.level", "c2");

        String sqlCount = sb.copy().select("select count(u.id)").get();
        String sqlSelect = sb.select("select u.*").build();

        assertEquals("select count(u.id) from Users u where u.id > 0 and u.level = ? order by u.login, u.level",
                sqlCount);
        assertEquals("select u.* from Users u where u.id > 0 and u.level = ? order by u.login, u.level", sqlSelect);
        assertArrayEquals(new Object[] { level }, sb.paramsAsArray());
    }
    
    @Test
    public void testGroupby() {
        String level = "root";

        SQLQueryBuilder sb = new SQLQueryBuilder()
                .gp("pLevel", level, NotNull)
                .from(" from Users u")
                .where(" where u.level is not null")
                .where(" and u.level = ?", "pLevel")
                .orderBy(" order by u.level", "pLevel")
                .groupBy(" group by u.level", "pLevel");

        String sqlSelect = sb.select("select count(u.id), u.level").get();

        assertEquals("select count(u.id), u.level from Users u where u.level is not null and u.level = ? group by u.level order by u.level",
                sqlSelect);
        assertArrayEquals(new Object[] { level }, sb.paramsAsArray());
    }

}
