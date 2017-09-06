package ru.grigoryev.start;

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
		MenuTracker menu = new MenuTracker();
		while (true) {
			menuItem = menu.select(this.input, this.tracker);
			if (menuItem == MenuTracker.INPUT_ERROR) {
				this.input.print("Input error\n");
				continue;
			}
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
		Input input = new ConsoleInput();
		Tracker tracker = new Tracker();
		new StartUI(input, tracker).init();
	}
}