package ru.grigoryev.start;

/**
*Class represent the exception which occures when user chooses invakid number in item in the menu.
*@author vgrigoryev
*@since 09.09.2017
*@version 1
*/
public class MenuOutException extends RuntimeException {
	/**
	*Constructor with parameters.
	*@param msg message to the user
	*/
	public MenuOutException(String msg) {
		super(msg);
	}
}