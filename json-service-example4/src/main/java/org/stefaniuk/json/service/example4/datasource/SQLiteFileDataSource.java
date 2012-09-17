package org.stefaniuk.json.service.example4.datasource;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

public class SQLiteFileDataSource extends SingleConnectionDataSource implements InitializingBean {

    @Autowired
    private HttpSession session;

    @Override
    public void afterPropertiesSet() throws Exception {

        // This is necessary due to the relative path to the database file and not possible to do from XML configuration file. 
        setDriverClassName("org.sqlite.JDBC");
        setUrl("jdbc:sqlite:" + session.getServletContext().getRealPath("/WEB-INF/classes") + "/chinook.sqlite");
    }

}
