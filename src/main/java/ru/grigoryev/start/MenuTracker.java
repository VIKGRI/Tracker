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
	*represents the input system.
	*/
	private Input input;
	/**
	*represents the tracker which is used for storing and manipulating items.
	*/
	private Tracker tracker;
	/**
	*represents array of actions which are available to use.
	*/
	private UserAction[] actions;
	/**
	*represents the key ADD to add item.
	*/
	static final int ADD = 0;
	/**
	*represents the key SHOW_ALL to show all items.
	*/
	static final int SHOW_ALL = 1;
	/**
	*represents the key EDIT to update an item.
	*/
	static final int EDIT = 2;
	/**
	*represents the key DELETE to delete an item.
	*/
	static final int DELETE = 3;
	/**
	*represents the key FIND_BY_ID to find th item by id.
	*/
	static final int FIND_BY_ID = 4;
	/**
	*represents the key FIND_ALL_BY_NAME to find items by name.
	*/
	static final int FIND_ALL_BY_NAME = 5;
	/**
	*represents the key EXIT to exit the programm.
	*/
	static final int EXIT = 6;
	/**
	*represents the key EXIT to exit the programm.
	*/
	static final int INPUT_ERROR = -1;
	/**
	*constructor with parameters.
	*@param input Input system
	*@param tracker the tracker which is used for storing and manipulating items
	*/
	public MenuTracker(Input input, Tracker tracker) {
		this.input = input;
		this.tracker = tracker;
		this.actions = new UserAction[6];
		this.fillActions();
	}
	/**
	*This method adds all actions.
	*/
	private void fillActions() {
		this.actions[0] = new AddItem();
		this.actions[1] = new MenuTracker.ShowAll();
		this.actions[2] = new UpdateItem();
		this.actions[3] = new DeleteItem();
		this.actions[4] = new MenuTracker.FindByID();
		this.actions[5] = new FindAllByName();
	};
	/**
	*This method provides selecting the operation from menu.
	*@return the number which represents the user's choise
	*/
	public int select() {
		for (UserAction action: actions) {
			System.out.println(action.info());
		}
		System.out.println(String.format("%d. %s", EXIT, "Exit Program."));
		int key = 0;
		try {
			key = Integer.parseInt(input.ask("Select: "));
		} catch (NumberFormatException ex) {
			key = INPUT_ERROR;
		}
		switch (key) {
			case ADD: case SHOW_ALL: case EDIT:
			case DELETE: case FIND_BY_ID: case FIND_ALL_BY_NAME:
			    this.actions[key].execute(this.input, this.tracker);
			    break;
			case EXIT:
			    break;
			default:
			    break;
		}
		return key;
	}
	/**
	*This method provides creating an item and adding it to the tracker.
	*Parameters are supplied by user input.
	*@param input Input for providing user input
	*@param tracker Tracker which contains items and manadges
	*/
	private void createItem(Input input, Tracker tracker) {
		String name = input.ask("Please, enter the name of the item: ");
		String description = input.ask("Please, enter the description of the item: ");
		Item item = new Item(name, description, System.currentTimeMillis());
		tracker.add(item);
	}
	/**
	*This is inner class which represents the action of adding a new item.
	*/
	private class AddItem implements UserAction {
		/**
		*Return the key of add action which corresponds to index in the array actions.
		*@return requested key
		*/
		public int key() {
			return ADD;
		}
		/**
		*Implementation of this method provides adding new item.
		*@param input Input for providing user input
		*@param tracker Tracker which contains items and manadges
		*/
		public void execute(Input input, Tracker tracker) {
			createItem(input, tracker);
		}
		/**
		*This method yields information about action.
		*@return String representation of information about the add action
		*/
		public String info() {
			return String.format("%d. %s", this.key(), "Add new item.");
		}
	}
	/**
	*This is static inner class which represents the action of showing all items.
	*/
	private static class ShowAll implements UserAction {
		/**
		*Return the key of add action which corresponds to index in the array actions.
		*@return requested key
		*/
		public int key() {
			return SHOW_ALL;
		}
		/**
		*Implementation of this method provides showing all items.
		*@param input Input for providing user input
		*@param tracker Tracker which contains items and manadges
		*/
		public void execute(Input input, Tracker tracker) {
			Item[] items = tracker.findAll();
			for (Item current: items) {
				input.print("Name: " + current.getName() + " Description: " + current.getDescription() + " ID: " + current.getId() + "\n");
				input.print("\n");
			}
		}
		/**
		*This method yields information about action.
		*@return String representation of information about the show all action
		*/
		public String info() {
			return String.format("%d. %s", this.key(), "Show all items.");
		}
	}
	/**
	*This method provides deleting an item in the tracker.
	*Id is supplied by user input.
	*@param input Input for providing user input
	*@param tracker Tracker which contains items
	*/
	private void deleteItem(Input input, Tracker tracker) {
		String id = input.ask("Please, enter the id of the item: ");
		Item item = new Item();
		item.setId(id);
		tracker.delete(item);
	}
	/**
	*This is inner class which represents the action of deleting the item.
	*/
	private class DeleteItem implements UserAction {
		/**
		*Return the key of delete action which corresponds to index in the array actions.
		*@return requested key
		*/
		public int key() {
			return DELETE;
		}
		/**
		*Implementation of this method provides deleting the item.
		*@param input Input for providing user input
		*@param tracker Tracker which contains items and manadges
		*/
		public void execute(Input input, Tracker tracker) {
			deleteItem(input, tracker);
		}
		/**
		*This method yields information about action.
		*@return String representation of information about the delete action
		*/
		public String info() {
			return String.format("%d. %s", this.key(), "Delete item.");
		}
	}
	/**
	*This is static inner class which represents the action of finding the item by id.
	*/
	private static class FindByID implements UserAction {
		/**
		*Return the key of find by id action which corresponds to index in the array actions.
		*@return requested key
		*/
		public int key() {
			return FIND_BY_ID;
		}
		/**
		*Implementation of this method provides finding item by id.
		*@param input Input for providing user input
		*@param tracker Tracker which contains items and manadges
		*/
		public void execute(Input input, Tracker tracker) {
			String id = input.ask("Please, enter the id of the item: ");
			Item item = tracker.findById(id);
			input.print("Name: " + item.getName() + " Description: " + item.getDescription() + "\n");
		}
		/**
		*This method yields information about action.
		*@return String representation of information about the find by id action
		*/
		public String info() {
			return String.format("%d. %s", this.key(), "Find the item by id.");
		}
	}
}
/**
*This is the class which represents the action of updating the item.
*/
class UpdateItem implements UserAction {
	/**
	*Return the key of add action which corresponds to index in the array actions.
	*@return requested key
	*/
	public int key() {
		return MenuTracker.EDIT;
	}
	/**
	*Implementation of this method provides updating the item.
	*@param input Input for providing user input
	*@param tracker Tracker which contains items and manadges
	*/
	public void execute(Input input, Tracker tracker) {
		String id = input.ask("Please, enter the id of the item: ");
		String name = input.ask("Please, enter the new name of the item: ");
		String description = input.ask("Please, enter the new description of the item: ");
		Item item = new Item(name, description, System.currentTimeMillis());
		item.setId(id);
		tracker.update(item);
	}
	/**
	*This method yields information about action.
	*@return String representation of information about the add action
	*/
	public String info() {
		return String.format("%d. %s", this.key(), "Update item.");
	}
}
/**
*This is the class which represents the action of finding all items with the same name.
*/
class FindAllByName implements UserAction {
	/**
	*Return the key of find all by name action which corresponds to index in the array actions.
	*@return requested key
	*/
	public int key() {
		return MenuTracker.FIND_ALL_BY_NAME;
	}
	/**
	*Implementation of this method provides finding all items with the same name.
	*@param input Input for providing user input
	*@param tracker Tracker which contains items and manadges
	*/
	public void execute(Input input, Tracker tracker) {
		String key = input.ask("Please, enter the name of the item: ");
		Item[] items = tracker.findByName(key);
		for (Item current: items) {
			input.print("Name: " + current.getName() + " Description: " + current.getDescription() + "\n");
		}
	}
	/**
	*This method yields information about action.
	*@return String representation of information about the find all by name action
	*/
	public String info() {
		return String.format("%d. %s", this.key(), "Find all items by name.");
	}
}