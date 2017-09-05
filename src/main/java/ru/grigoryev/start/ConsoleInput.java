package ru.grigoryev.start;

import java.util.Scanner;

/**
*Class represent the console input.
*@author vgrigoryev
*@since 05.09.2017
*@version 1
*/
public class ConsoleInput implements Input {
	/**
	*Scanner.
	*/
	private Scanner scanner = new Scanner(System.in);
	/**
	*This method provides asking user a question.
	*@param question question
	*@return question to the user
	*/
	public String ask(String question) {
		System.out.print(question);
		return scanner.nextLine();
	}
	/**
	*This method provides printing date.
	*@param data Data to print out
	*/
	public void print(String data) {
		System.out.print(data);
	}
}