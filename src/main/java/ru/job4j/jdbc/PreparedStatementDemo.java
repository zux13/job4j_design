package ru.job4j.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PreparedStatementDemo {

    private Connection connection;

    public PreparedStatementDemo() throws Exception {
        initConnection();
    }

    public void initConnection() throws Exception {
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://localhost:5432/idea_db";
        String login = "postgres";
        String password = "password";
        connection = DriverManager.getConnection(url, login, password);
    }

    public City insert(City city) {
        try (PreparedStatement statement =
                     connection.prepareStatement("INSERT INTO cities(name, population) VALUES (?, ?)",
                             Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, city.getName());
            statement.setInt(2, city.getPopulation());
            statement.execute();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    city.setId(generatedKeys.getInt(1));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return city;
    }

    public boolean update(City city) {
        boolean result = false;
        try (PreparedStatement statement =
                     connection.prepareStatement("UPDATE cities SET name = ?, population = ? WHERE id = ?")) {
            statement.setString(1, city.getName());
            statement.setInt(2, city.getPopulation());
            statement.setInt(3, city.getId());
            result = statement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public void delete(int id) {
        try (PreparedStatement statement =
                     connection.prepareStatement("DELETE FROM cities WHERE id = ?")) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<City> findAll() {
        List<City> cities = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM cities")) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    cities.add(new City(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getInt("population")
                    ));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cities;
    }

    public static void main(String[] args) {

        int id = 1;
        City ufa = new City(id++, "Ufa", 1157994);
        City msk = new City(id++, "Moscow", 13104177);
        City spb = new City(id, "Saint Petersburg", 5600044);

        try {
            var prepStatement = new PreparedStatementDemo();
            List<City> cities = prepStatement.findAll();

            if (cities.isEmpty()) {

                prepStatement.insert(ufa);
                prepStatement.insert(msk);
                prepStatement.insert(spb);
            }

            for (City city : cities) {
                System.out.printf(
                        "City name: %s; id: %d; population: %d\n",
                        city.getName(),
                        city.getId(),
                        city.getPopulation()
                );
            }

            spb.setName("Leningrad");
            ufa.setPopulation(1158000);

            prepStatement.update(spb);
            prepStatement.update(ufa);
            prepStatement.delete(msk.getId());

            for (City city : prepStatement.findAll()) {
                System.out.printf(
                        "City name: %s; id: %d; population: %d\n",
                        city.getName(),
                        city.getId(),
                        city.getPopulation()
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}