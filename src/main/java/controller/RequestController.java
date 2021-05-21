package controller;

import annotations.Controller;
import annotations.Mapping;
import database.ConnectionCreator;
import dto.Dto;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

@Controller
public class RequestController {

    private final ConnectionCreator connectionCreator;

    public RequestController() {
        this.connectionCreator=new ConnectionCreator();
    }

    @Mapping(mapping = "/test")
    public String test(Dto dto) throws SQLException {
        PreparedStatement statement = connectionCreator.getConnection().prepareStatement("INSERT USERS (id,name) as (1,?)");
        statement.setString(1,dto.getName());
        statement.execute();
        return String.format("<p>Hello %s</p>",dto.getName());
    }
}
