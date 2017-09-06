package ru.grigoryev.start;

/**
*Class represent the console input.
*@author vgrigoryev
*@since 05.09.2017
*@version 1
*/
public class StubInput implements Input {
	/**
	*Scanner.
	*/
	private String[] answers;
	/**
	*Position.
	*/
	private int position = 0;
	/**
	*Output results.
	*/
	private String[] outputBuffer = new String[100];
	/**
	*Output position.
	*/
	private int outputPosition = 0;
	/**
	*Constructor with parameters.
	*@param answers answers
	*/
	public StubInput(String[] answers) {
		this.answers = answers;
	}
	/**
	*This method provides asking user a question.
	*@param question question
	*@return question to the user
	*/
	public String ask(String question) {
		return answers[position++];
	}
	/**
	*This method provides locating data in the buffer.
	*@param data Data to print out
	*/
	public void print(String data) {
		outputBuffer[outputPosition++] = data;
	}
		/**
	*This method provides getting output.
	*It's used for testing methods which prints the data in the console.
	*@return array of data
	*/
	public String[] getOutputBuffer() {
		return outputBuffer;
	}
}