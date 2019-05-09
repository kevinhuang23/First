package Jdbc;

import java.sql.SQLException;

public interface customerDAO {
     void insert(customer cs) throws SQLException ;
     void update();
}
