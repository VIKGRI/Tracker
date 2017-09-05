package ru.grigoryev.start;
/**
*Interface represent the console input.
*@author vgrigoryev
*@since 05.09.2017
*@version 1
*/
public interface Input {
	/**
	*This method provides asking user a question.
	*@param question question
	*@return question to the user
	*/
	String ask(String question);
	/**
	*This method provides printing date.
	*@param data Data to print out
	*/
	void print(String data);
}