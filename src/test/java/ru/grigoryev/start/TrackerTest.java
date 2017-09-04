package ru.job4j.professions;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
*Class for class Tracker testing.
*@author vgrigoryev
*@since 04.09.2017
*@version 1
*/
public class TrackerTest {
	/**
	*method for remove method testing.
	*/
	@Test
	public void whenUpdateNameThenReturnNewName() {
		Tracker tracker = new Tracker();
		Item previous = new Item("test1", "testDescription", 123L);
		// Добавляем заявку в трекер. Теперь в объект проинициализирован id.
		tracker.add(previous);
		// Создаем новую заявку.
		Item next = new Task("test2", "testDescription2", 1234L);
		// Проставляем старый id из previous, который был сгенерирован выше.
		next.setId(previous.getId());
		// Обновляем заявку в трекере.
		tracker.update(next);
		// Проверяем, что заявка с таким id имеет новые имя test2.
		assertThat(tracker.findById(previous.getId()).getName(), is("test2"));
	}
}