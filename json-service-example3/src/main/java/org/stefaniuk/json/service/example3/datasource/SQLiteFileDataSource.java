package org.stefaniuk.json.service.example3.datasource;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

public class SQLiteFileDataSource extends SingleConnectionDataSource {

    @Autowired
    private HttpSession session;

    //<property name="driverClassName" value="org.sqlite.JDBC" />
    //<property name="url" value="jdbc:sqlite:/WEB-INF/classes/chinook.sqlite" />

    public SQLiteFileDataSource() {

        super();

        //System.out.println("-----------------------------------");
        //System.out.println(session.toString());
        //System.out.println(session.getServletContext().getRealPath("/WEB-INF/classes") + "/chinook.sqlite");
    }

}
