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
	*This method provides asking user a question.
	*@param question question
	*@param range range of numbers which corresponds to items in menu
	*@return question to the user
	*/
	int ask(String question, int[] range);
	/**
	*This method provides printing date.
	*@param data Data to print out
	*/
	void print(String data);
}