package ru.grigoryev.start;

import ru.grigoryev.models.Task;
/**
*Class represent the interface of tracker.
*@author vgrigoryev
*@since 04.09.2017
*@version 1
*/
public class StartUI {
	/**
	*This method is main.
	*@param args args
	*/
	public static void main(String[] args) {
		Tracker tracker = new Tracker();
		tracker.add(new Task("first task", "first desc"));
		for (Item item : tracker.getAll()) {
			System.out.println(item.getName());
		}
	}
}