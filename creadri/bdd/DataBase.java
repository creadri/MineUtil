package creadri.bdd;

import com.mysql.jdbc.PreparedStatement;
import creadri.exceptions.BddError;
import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author creadri
 */
public class DataBase {

    private String login;
    private String password;
    private String connectionURL;
    private Connection sqlCon;
    private int lastId;

    public DataBase(BddType type, String host, String bdd, String login, String password) throws BddError {
        this.login = login;
        this.password = password;

        try {
            switch (type) {
                case MYSQL:
                    URLClassLoader classLoader = new URLClassLoader(new URL[]{new URL("jar:file:" + new File("lib/mysql-connector-java-bin.jar").getPath() + "!/")});

                    Driver driver = (Driver) classLoader.loadClass("com.mysql.jdbc.Driver").newInstance();
                    DriverManager.registerDriver(new DriverStub(driver));

                    connectionURL = "jdbc:mysql://" + host + "/" + bdd;

                    sqlCon = DriverManager.getConnection(connectionURL, login, password);
                    break;
            }
        } catch (Exception ex) {
            throw new BddError("Connexion BDD impossible: " + ex.getMessage());
        }
    }

    public ResultSet selectQuery(String sql) throws BddError {
        try {
            if (sqlCon.isClosed()) {
                sqlCon = DriverManager.getConnection(connectionURL, login, password);
            }
            return sqlCon.createStatement().executeQuery(sql);
        } catch (SQLException ex) {
            throw new BddError("Requête non aboutie: " + ex.getMessage());
        }
    }

    public void updateQuery(String sql) throws BddError {
        try {
            if (sqlCon.isClosed()) {
                sqlCon = DriverManager.getConnection(connectionURL, login, password);
            }
            sqlCon.createStatement().executeUpdate(sql);
        } catch (SQLException ex) {
            throw new BddError("Requête non aboutie: " + ex.getMessage());
        }
    }

    public void insertQuery(String sql) throws BddError {
        try {
            if (sqlCon.isClosed()) {
                sqlCon = DriverManager.getConnection(connectionURL, login, password);
            }
            PreparedStatement stmnt = (PreparedStatement) sqlCon.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmnt.executeUpdate();
            ResultSet rs = stmnt.getGeneratedKeys();
            if (rs != null && rs.next()) {
                lastId = rs.getInt(1);
            }
        } catch (SQLException ex) {
            throw new BddError("Requête non aboutie: " + ex.getMessage());
        }
    }

    public int getLastId() {
        return lastId;
    }

    public void close() throws BddError {
        try {
            if (!sqlCon.isClosed()) {
                sqlCon.close();
            }
        } catch (SQLException ex) {
            throw new BddError("Requête non aboutie: " + ex.getMessage());
        }
    }

    public String getConnectionURL() {
        return connectionURL;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public Connection getConnection() {
        return sqlCon;
    }
}
