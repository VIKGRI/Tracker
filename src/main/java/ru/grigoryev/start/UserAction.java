package ru.grigoryev.start;


/**
*Interface for representing actions.
*@author vgrigoryev
*@since 08.09.2017
*@version 1
*/
public interface UserAction {
	/**
	*.
	*@return requested key
	*/
	int key();
	/**
	*.
	*@param input Object which implement Input interface
	*@param tracker tracker which is used for storing and manipulating items
	*/
	void execute(Input input, Tracker tracker);
	/**
	*Method provides getting information about concrete action.
	*@return information about the action
	*/
	String info();
}