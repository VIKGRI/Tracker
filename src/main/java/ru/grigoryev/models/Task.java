package ru.grigoryev.models;

/**
*Class represent the task.
*@author vgrigoryev
*@since 04.09.2017
*@version 1
*/
public class Task extends Item {
	/**
	*Constructor with parameters.
	*@param name Name of task
	*@param desc Description of task
	*@param create Time of creation
	*/
	public Task(String name, String desc, long create) {
		super(name, desc, create);
	}
}