package ru.grigoryev.start;

import ru.grigoryev.models.Item;

/**
*Class represent the menu.
*@author vgrigoryev
*@since 05.09.2017
*@version 1
*/
public class MenuTracker {
	/**
	*represents the key ADD to add item.
	*/
	private static final int ADD = 0;
	/**
	*represents the key SHOW_ALL to show all items.
	*/
	private static final int SHOW_ALL = 1;
	/**
	*represents the key EDIT to update an item.
	*/
	private static final int EDIT = 2;
	/**
	*represents the key DELETE to delete an item.
	*/
	private static final int DELETE = 3;
	/**
	*represents the key FIND_BY_ID to find th item by id.
	*/
	private static final int FIND_BY_ID = 4;
	/**
	*represents the key FIND_ALL_BY_NAME to find items by name.
	*/
	private static final int FIND_ALL_BY_NAME = 5;
	/**
	*represents the key EXIT to exit the programm.
	*/
	static final int EXIT = 6;
	/**
	*represents the key EXIT to exit the programm.
	*/
	static final int INPUT_ERROR = -1;
	/**
	*This method provides selecting the operation from menu.
	*@param input Input object
	*@param tracker Current tracker of items
	*@return question to the user
	*/
	public int select(Input input, Tracker tracker) {
		System.out.println("Please, enter the number of operation");
		System.out.println("0. Add new item");
		System.out.println("1. Show all items");
		System.out.println("2. Edit item");
		System.out.println("3. Delete item");
		System.out.println("4. Find item by Id");
		System.out.println("5. Find items by name");
		System.out.println("6. Exit Program");
		int menuItem = 0;
		try {
			menuItem = Integer.parseInt(input.ask("Select: "));
		} catch (NumberFormatException ex) {
			menuItem = INPUT_ERROR;
		}
		switch (menuItem) {
			case ADD:
				this.createItem(input, tracker); //++
				break;
			case SHOW_ALL:
				this.getAllItems(input, tracker); //++
				break;
			case EDIT:
				this.updateItem(input, tracker); //++
				break;
			case DELETE:
				this.deleteItem(input, tracker); //++
				break;
			case FIND_BY_ID:
				this.findItemById(input, tracker); //++
				break;
			case FIND_ALL_BY_NAME:
				this.findItemsByName(input, tracker); //+
				break;
			case EXIT:
				break;
			default:
				break;
		}
		return menuItem;
	}
	/**
	*This method provides creating an item and adding it to the tracker.
	*Paraameters are supplied by user input.
	*@param input Input for providing user input
	*@param tracker Tracker which contains items and manadges
	*/
	public void createItem(Input input, Tracker tracker) {
		String name = input.ask("Please, enter the name of the item: ");
		String description = input.ask("Please, enter the description of the item: ");
		Item item = new Item(name, description, System.currentTimeMillis());
		tracker.add(item);
	}
	/**
	*This method provides printing out all items in the tracker.
	*@param input User input
	*@param tracker Tracker which contains items
	*/
	public void getAllItems(Input input, Tracker tracker) {
		Item[] items = tracker.findAll();
		for (Item current: items) {
			input.print("Name: " + current.getName() + " Description: " + current.getDescription() + " ID: " + current.getId() + "\n");
			input.print("\n");
		}
	}
	/**
	*This method provides updating an item in the tracker.
	*Paraameters are supplied by user input.
	*@param input Input for providing user input
	*@param tracker Tracker which contains items
	*/
	public void updateItem(Input input, Tracker tracker) {
		String id = input.ask("Please, enter the id of the item: ");
		String name = input.ask("Please, enter the new name of the item: ");
		String description = input.ask("Please, enter the new description of the item: ");
		Item item = new Item(name, description, System.currentTimeMillis());
		item.setId(id);
		tracker.update(item);
	}
	/**
	*This method provides deleting an item in the tracker.
	*Id is supplied by user input.
	*@param input Input for providing user input
	*@param tracker Tracker which contains items
	*/
	public void deleteItem(Input input, Tracker tracker) {
		String id = input.ask("Please, enter the id of the item: ");
		Item item = new Item();
		item.setId(id);
		tracker.delete(item);
	}
	/**
	*This method is used for finding an item by its id in the tracker.
	*Id is supplied by user input.
	*@param input Input for providing user input
	*@param tracker Tracker which contains items
	*/
	public void findItemById(Input input, Tracker tracker) {
		String id = input.ask("Please, enter the id of the item: ");
		Item item = tracker.findById(id);
		input.print("Name: " + item.getName() + " Description: " + item.getDescription() + "\n");
	}
	/**
	*This method is used for finding all items by name in the tracker.
	*Name is supplied by user input.
	*@param input Input for providing user input
	*@param tracker Tracker which contains items
	*/
	public void findItemsByName(Input input, Tracker tracker) {
		String key = input.ask("Please, enter the name of the item: ");
		Item[] items = tracker.findByName(key);
		for (Item current: items) {
			input.print("Name: " + current.getName() + " Description: " + current.getDescription() + "\n");
		}
	}
}