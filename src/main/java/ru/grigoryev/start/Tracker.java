package ru.grigoryev.start;

import ru.grigoryev.models.Item;

import javax.naming.NamingException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
*Class represent the tracker.
*@author vgrigoryev
*@since 04.09.2017
*@version 1
*/
public class Tracker {
	/**
	 * Stores information about items in the database.
	 */
	ItemDataBase storage;
	/**
	*ID.
	*/
	private static final Random RN = new Random();;

	/**
	 * Constructor.
	 */
	public Tracker() {
		this.storage = new ItemDataBase();
		try {
			this.storage.connectDataBase();
		} catch (SQLException  | IOException e) {
			e.printStackTrace();
		}
	}

	/**
	*This method is used for adding an item.
	*@param item New item
	*@return added iten with id assigned.
	*/
	public Item add(Item item) {
		String id = this.generateId();
		item.setId(id);
		this.storage.insertItem(item);
		return item;
	}

	/**
	 * Closes connection associated with this tracker.
	 */
	public void closeTrackerResources() {
		try {
			this.storage.closeConnection();
		} catch (SQLException e) {
			for (Throwable t : e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 *This method is used for deleting all items in the tracker.
	 */
	public void clearItemsStorage() {
		this.storage.deleteAllItems();
	}

	/**
	*This method is used for updating an item which is identified by id in the tracker.
	*@param item Updated item
	*/
	public void update(Item item) {
		storage.updateItem(item);
	}
	/**
	*This method is used for deleting an item which is identified by id in the tracker.
	*@param item Item to delete
	*/
	public void delete(Item item) {
		storage.deleteItem(item);
	}
	/**
	*This method is used for getting a list of all items.
	*@return Array of all items.
	*/
	public List<Item> findAll() {
		return storage.selectAll();
	}
	/**
	*This method is used for getting a list of all items with matching names.
	 *@param key key name
	*@return Array of matching items.
	*/
	public List<Item> findByName(String key) {
		return storage.selectByName(key);
	}
	/**
	*This method is used for finding an item by its id.
	*@param id Id of item
	*@return Item. If it isn't found, then null is returned.
	*/
	public Item findById(String id) {
		return storage.selectById(id);
	}
	/**
	*This method is used for generating id.
	*@return id.
	*/
	String generateId() {
		return String.valueOf(System.currentTimeMillis() + RN.nextInt());
	}
}