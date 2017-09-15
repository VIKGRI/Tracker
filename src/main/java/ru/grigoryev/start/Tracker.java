package ru.grigoryev.start;

import ru.grigoryev.models.Item;

import java.util.ArrayList;
import java.util.Random;

/**
*Class represent the tracker.
*@author vgrigoryev
*@since 04.09.2017
*@version 1
*/
public class Tracker {
	/**
	*items.
	*/
	private ArrayList<Item> items = new ArrayList<Item>();
	/**
	*ID.
	*/
	private static final Random RN = new Random();
	/**
	*This method is used for adding an item.
	*@param item New item
	*@return added iten with id assigned.
	*/
	public Item add(Item item) {
		item.setId(this.generateId());
		items.add(item);
		return item;
	}

	/**
	*This method is used for updating an item which is identified by id in the tracker.
	*@param item Updated item
	*/
	public void update(Item item) {
		for (int i = 0; i < items.size(); i++) {
			if (this.items.get(i) != null && this.items.get(i).getId().equals(item.getId())) {
				this.items.set(i, item);
				break;
			}
		}
	}
	/**
	*This method is used for deleting an item which is identified by id in the tracker.
	*@param item Item to delete
	*/
	public void delete(Item item) {
		for (int i = 0; i < this.items.size(); i++) {
			if (this.items.get(i) != null && this.items.get(i).getId().equals(item.getId())) {
				this.items.remove(i);
				break;
			}
		}
	}
	/**
	*This method is used for getting a list of all items.
	*@return Array of all items.
	*/
	public ArrayList<Item> findAll() {
		ArrayList<Item> result = new ArrayList<Item>();
		for (Item value : this.items) {
			result.add(value);
		}
		return result;
	}
	/**
	*This method is used for getting a list of all items with matching names.
	 *@param key key name
	*@return Array of matching items.
	*/
	public ArrayList<Item> findByName(String key) {
		ArrayList<Item> result = new ArrayList<Item>();
		for (Item value : this.items) {
			if (value.getName().equals(key)) {
				result.add(value);
			}
		}
		return result;
	}
	/**
	*This method is used for finding an item by its id.
	*@param id Id of item
	*@return Item. If it isn't found, then null is returned.
	*/
	public Item findById(String id) {
		Item result = null;
		for (Item value: items) {
			if (value.getId().equals(id)) {
				result = value;
				break;
			}
		}
		return result;
	}
	/**
	*This method is used for generating id.
	*@return id.
	*/
	String generateId() {
		return String.valueOf(System.currentTimeMillis() + RN.nextInt());
	}
}