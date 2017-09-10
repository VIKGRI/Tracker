package ru.grigoryev.start;


/**
*Abstract class for all actions.
*@author vgrigoryev
*@since 10.09.2017
*@version 1
*/
public abstract class BaseAction implements UserAction {
	/**
	*represents the description of action.
	*/
	private String name;
	/**
	*represents the number of action in the menu.
	*/
	private int key;
	/**
	*Constructor with parameters.
	*@param name the description of action
	*@param key the number of action in the menu
	*/
	public BaseAction(String name, int key) {
		this.name = name;
		this.key = key;
	}
	/**
	*Method provides getting information about concrete action.
	*@return information about the action
	*/
	public String info() {
		return String.format("%d. %s", this.key, this.name);
	}
}