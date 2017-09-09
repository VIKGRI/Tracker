package ru.grigoryev.start;

/**
*Class represent the user  console input which is going to be validated.
*@author vgrigoryev
*@since 09.09.2017
*@version 1
*/
public class ValidateInput extends ConsoleInput {
	/**
	*This method checks whether the input data is valid or not.
	*@param question question in the menu
	*@param range range of numbers which corresponds to items in menu
	*@return number of item in the menu
	*/
	public int ask(String question, int[] range) {
		boolean invalid = true;
		int value = -1;
		do {
			try {
				value = super.ask(question, range);
				invalid = false;
			} catch (NumberFormatException nfe) {
				System.out.println("Please, enter valid data again");
				} catch (MenuOutException moe) {
					System.out.println(moe.getMessage() + " Please, select item from menu");
				}
			} while (invalid);
			return value;
	}
}