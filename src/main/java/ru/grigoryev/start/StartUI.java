package ru.grigoryev.start;

import ru.grigoryev.models.Item;

/**
*Class represent the interface of tracker.
*@author vgrigoryev
*@since 04.09.2017
*@version 1
*/
public class StartUI {
	/**
	*This field represents input.
	*/
	private Input input;
	/**
	*Tracker.
	*/
	private Tracker tracker;
	/**
	*Constructor with parameters.
	*@param input Input
	*@param tracker Tracker
	*/
	public StartUI(Input input, Tracker tracker) {
		this.input = input;
		this.tracker = tracker;
	}
	/**
	*This init method.
	*/
	public void init() {
		String name = null;
		int menuItem = 0;
		MenuTracker menu = new MenuTracker(this.input, this.tracker);
		UserAction findAllByNameAction = new BaseAction("Find all items by name.", MenuTracker.FIND_ALL_BY_NAME) {
			public int key() {
				return MenuTracker.FIND_ALL_BY_NAME;
			}
			public void execute(Input input, Tracker tracker) {
				String key = input.ask("Please, enter the name of the item: ");
				Item[] items = tracker.findByName(key);
				for (Item current: items) {
					input.print("Name: " + current.getName() + " Description: " + current.getDescription() + "\n");
				}
			}
		};
		menu.addAction(findAllByNameAction);
		while (true) {
			menuItem = menu.select();
			if (menuItem == MenuTracker.EXIT) {
				this.input.print("EXIT\n");
				break; //EXIT
			}
		}
	}
	/**
	*This method is main.
	*@param args args
	*/
	public static void main(String[] args) {
		Input input = new ValidateInput();
		Tracker tracker = new Tracker();
		new StartUI(input, tracker).init();
	}
}