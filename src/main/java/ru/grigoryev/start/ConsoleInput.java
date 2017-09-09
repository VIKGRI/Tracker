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
	*This method provides asking user a question.
	*@param question question in the menu
	*@param range range of numbers which corresponds to items in menu
	*@return number of request in the menu
	*/
	public int ask(String question, int[] range) {
		int key = Integer.valueOf(this.ask(question));
		boolean exist = false;
		for (int value: range) {
			if (value == key) {
				exist = true;
				break;
			}
		}
		if (!exist) {
			throw new MenuOutException("Out of menu range");
		}
		return key;
	}
	/**
	*This method provides printing date.
	*@param data Data to print out
	*/
	public void print(String data) {
		System.out.print(data);
	}
}