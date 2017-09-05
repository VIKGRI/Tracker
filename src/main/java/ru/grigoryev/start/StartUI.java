package ru.grigoryev.start;

/**
*Class represent the interface of tracker.
*@author vgrigoryev
*@since 04.09.2017
*@version 1
*/
public class StartUI {
	/**
	*This init method.
	*/
	private Input input;
	/**
	*Constructor with parameters.
	*@param input Input
	*/
	public StartUI(Input input) {
		this.input = input;
	}
	/**
	*This init method.
	*/
	public void init() {
		String name = null;
		int menuItem = 0;
		Tracker tracker = new Tracker();
		MenuTracker menu = new MenuTracker();
		while (true) {
			menuItem = menu.select(this.input, tracker);
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
		new StartUI(input).init();
	}
}