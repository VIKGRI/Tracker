package ru.grigoryev.start;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import ru.grigoryev.models.Item;

/**
 * Class represents database which stores information about
 * items in the tracker.
 *
 * @author vgrigoryev
 * @version 1
 * @since 27.10.2017
 */
public class ItemDataBase {
    /**
     * Request for inserting row in the database.
     */
    private static final String INSERT_ITEM =
            "INSERT INTO items (id, name, description, create_time) VALUES ((?), (?), (?), (?))";
    /**
     * Request for updating row in the database.
     */
    private static final String UPDATE_ITEM =
            "UPDATE items SET name = (?), description = (?), create_time = (?) WHERE id = (?)";
    /**
     * Request for deleting row in the database.
     */
    private static final String DELETE_ITEM =
            "DELETE FROM items WHERE id = (?)";
    /**
     * Request for deleting all rows in the database.
     */
    private static final String DELETE_ALL =
            "DELETE FROM items";
    /**
     * Request for selecting row which matches specified name in the database.
     */
    private static final String SELECT_BY_NAME = "SELECT * FROM items WHERE name = (?)";
    /**
     * Request for selecting row which matches specified id in the database.
     */
    private static final String SELECT_BY_ID = "SELECT * FROM items WHERE id = (?)";
    /**
     * Request for selecting all rows in the database.
     */
    private static final String SELECT_ALL = "SELECT * FROM items";
    /**
     * Connection to database.
     */
    private Connection connection;

    /**
     * Provides connection to the database and configurating it's
     * properties. Also creates database if it is not exists.
     *
     * @throws IOException  thrown if problems with file reading occur
     * @throws SQLException thrown if problems with database connection occur
     */
    public void connectDataBase() throws SQLException, IOException {
        // Setting connection to database
        Properties properties = new Properties();
        try (InputStream in =
                     Files.newInputStream(Paths.get("database.properties"))) {
            properties.load(in);
        }
        String drivers = properties.getProperty("jdbc.drivers");
        if (drivers != null) {
            System.setProperty("jdbc.drivers", drivers);
        }
        String url = properties.getProperty("jdbc.url");
        String username = properties.getProperty("jdbc.username");
        String password = properties.getProperty("jdbc.password");
        connection = DriverManager.getConnection(url, username, password);

        //Reading and executing sql create table request
        StringBuilder initTable = new StringBuilder();
        try (BufferedReader reader = new BufferedReader((
                new FileReader("createTable.sql")))) {
            String line;
            while ((line = reader.readLine()) != null) {
                initTable.append(line);
                if (line.endsWith(";")) {
                    break;
                }
            }
        }
        PreparedStatement createTable = connection.prepareStatement(initTable.toString());
        createTable.execute();
    }

    /**
     * Closes database connection.
     */
    public void closeConnection() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }


    /**
     * Insert item in the database.
     *
     * @param item specified item
     */
    public void insertItem(Item item) {
        try (PreparedStatement insert =
                     connection.prepareStatement(INSERT_ITEM);) {
            insert.setString(1, item.getId());
            insert.setString(2, item.getName());
            insert.setString(3, item.getDescription());
            insert.setString(4, Long.toString(item.getCreate()));
            insert.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates item in the database.
     *
     * @param item specified item
     */
    public void updateItem(Item item) {
        try (PreparedStatement update =
                     connection.prepareStatement(UPDATE_ITEM);) {
            update.setString(1, item.getName());
            update.setString(2, item.getDescription());
            update.setString(3, Long.toString(item.getCreate()));
            update.setString(4, item.getId());
            update.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deletes item from database.
     *
     * @param item specified item
     */
    public void deleteItem(Item item) {
        try (PreparedStatement delete =
                     connection.prepareStatement(DELETE_ITEM);) {
            delete.setString(1, item.getId());
            delete.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * Deletes all items from database.
     */
    public void deleteAllItems() {
        try (PreparedStatement deleteAll =
                     connection.prepareStatement(DELETE_ALL);) {
            deleteAll.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * Selects all items with specified name.
     *
     * @param name specified name
     * @return list of items
     */
    public List<Item> selectByName(String name) {
        Item currentItem;
        List<Item> result = new ArrayList<>();
        try (PreparedStatement selectByName =
                     connection.prepareStatement(SELECT_BY_NAME);) {
            selectByName.setString(1, name);
            ResultSet items = selectByName.executeQuery();
            while (items.next()) {
                currentItem = new Item(
                        items.getString("name"),
                        items.getString("description"),
                        Long.parseLong(items.getString("create_time"))
                );
                currentItem.setId(items.getString("id"));
                result.add(currentItem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Selects item with specified id.
     *
     * @param id specified id.
     * @return Item with specified id or null if it doesn't exist
     */
    public Item selectById(String id) {
        Item currentItem = null;
        try (PreparedStatement selectByName =
                     connection.prepareStatement(SELECT_BY_ID);) {
            selectByName.setString(1, id);
            ResultSet items = selectByName.executeQuery();
            while (items.next()) {
                currentItem = new Item(
                        items.getString("name"),
                        items.getString("description"),
                        Long.parseLong(items.getString("create_time"))
                );
                currentItem.setId(items.getString("id"));
                break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return currentItem;
    }

    /**
     * Selects all items from database.
     *
     * @return list of all items
     */
    public List<Item> selectAll() {
        Item currentItem;
        List<Item> result = new ArrayList<>();
        try (PreparedStatement selectAll =
                     connection.prepareStatement(SELECT_ALL);) {
            ResultSet items = selectAll.executeQuery();
            while (items.next()) {
                currentItem = new Item(
                        items.getString("name"),
                        items.getString("description"),
                        Long.parseLong(items.getString("create_time"))
                );
                currentItem.setId(items.getString("id"));
                result.add(currentItem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}