package ru.job4j.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private Connection connection;

    private Properties properties;

    public TableEditor(Properties properties) {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() {
        try {
            connection = getConnection();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void createTable(String tableName) {
        String sql = String.format("CREATE TABLE %s(%s);", tableName, "id SERIAL PRIMARY KEY");
        executeStatement(sql);
    }

    public void dropTable(String tableName) {
        String sql = String.format("DROP TABLE %s;", tableName);
        executeStatement(sql);
    }

    public void addColumn(String tableName, String columnName, String type) {
        String sql = String.format("ALTER TABLE %s ADD COLUMN %s %s;", tableName, columnName, type);
        executeStatement(sql);
    }

    public void dropColumn(String tableName, String columnName) {
        String sql = String.format("ALTER TABLE %s DROP COLUMN %s;", tableName, columnName);
        executeStatement(sql);
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) {
        String sql = String.format(
                "ALTER TABLE %s RENAME COLUMN %s TO %s;",
                tableName,
                columnName,
                newColumnName);
        executeStatement(sql);
    }

    private void executeStatement(String sql) {
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {

        Properties properties = new Properties();
        try (InputStream input = TableEditor.class.getClassLoader().getResourceAsStream("app.properties")) {
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (var tableEditor = new TableEditor(properties)) {
            var tableName = "students";
            tableEditor.createTable(tableName);
            tableEditor.printTableScheme(tableName);

            tableEditor.addColumn(tableName, "name", "VARCHAR(100)");
            tableEditor.addColumn(tableName, "surname", "VARCHAR(100)");
            tableEditor.addColumn(tableName, "course", "VARCHAR(100)");
            tableEditor.printTableScheme(tableName);

            tableEditor.renameColumn(tableName, "course", "subject");
            tableEditor.printTableScheme(tableName);

            tableEditor.dropColumn(tableName, "subject");
            tableEditor.printTableScheme(tableName);

            tableEditor.dropTable(tableName);
            tableEditor.printTableScheme(tableName);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void printTableScheme(String tableName) {
        try {
            System.out.println(getTableScheme(tableName));
        } catch (Exception e) {
            System.out.printf("Table %s doesn't exist!\n", tableName);
        }
    }

    private Connection getConnection() throws Exception {
        Class.forName(properties.getProperty("driver"));
        String url = properties.getProperty("url");
        String login = properties.getProperty("login");
        String password = properties.getProperty("password");
        return DriverManager.getConnection(url, login, password);
    }

    private String getTableScheme(String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "SELECT * FROM %s LIMIT 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }
}