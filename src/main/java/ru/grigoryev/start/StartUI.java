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
	*This method is main.
	*@param args args
	*/
	public static void main(String[] args) {
		Tracker tracker = new Tracker();
		tracker.add(new Item("first task", "first desc", 0L));
		for (Item item : tracker.findAll()) {
			System.out.println(item.getName());
		}
	}
}