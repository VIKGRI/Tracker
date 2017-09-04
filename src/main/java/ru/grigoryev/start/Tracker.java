package ru.grigoryev.start;

import ru.grigoryev.models.Item;
import java.util.Arrays;

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
	private Item[] items = new Item[100];
	/**
	*free position in the array - amount of items.
	*/
	private int position = 0;
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
		this.items[position++] = item;
		return item;
	}

	/**
	*This method is used for updating an item which is identified by id in the tracker.
	*@param item Updated item
	*/
	public void update(Item item) {
		for (int i = 0; i < position; i++) {
			if (this.items[i] != null && this.items[i].getId().equals(item.getId())) {
				this.items[i] = item;
				break;
			}
		}
	}
	/**
	*This method is used for deleting an item which is identified by id in the tracker.
	*@param item Item to delete
	*/
	public void delete(Item item) {
		for (int i = 0; i < position; i++) {
			if (this.items[i] != null && this.items[i].getId().equals(item.getId())) {
				this.items[i] = null;
				System.arraycopy(this.items, i + 1, this.items, i, position - i - 1);
				break;
			}
		}
	}
	/**
	*This method is used for getting a list of all items.
	*@return Array of all items.
	*/
	public Item[] findAll() {
		Item[] result = new Item[position];
		for (int i = 0; i < position; i++) {
			result[i] = this.items[i];
		}
		return result;
	}
	/**
	*This method is used for getting a list of all items with matching names.
	@param key key name
	*@return Array of matching items.
	*/
	public Item[] findByName(String key) {
		Item[] result = new Item[position];
		int matches = 0;
		for (int i = 0; i < position; i++) {
			if (this.items[i].getName().equals(key)) {
				this.result[matches++] = item[i];
			}
		}
		return Arrays.copyOf(result, matches);
	}
	/**
	*This method is used for finding an item by its id.
	*@param id Id of item
	*@return Item. If it isn't found, then null is returned.
	*/
	public Item findById(String id) {
		Item result = null;
		for (Item item: items) {
			if (item != null && item.getId().equals(id)) {
				result = item;
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