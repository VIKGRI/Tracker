package ru.grigoryev.start;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import ru.grigoryev.models.Item;
import ru.grigoryev.models.Task;

import java.util.ArrayList;
import java.util.List;

/**
*Class for class Tracker testing.
*@author vgrigoryev
*@since 04.09.2017
*@version 1
*/
public class TrackerTest {
	/**
	*method for add method testing.
	*/
	@Test
	public void whenAddItemThenReturnNewName() {
		Tracker tracker = new Tracker();
		tracker.clearItemsStorage();
		Item current = new Item("test1", "testDescription", 123L);
		// Добавляем заявку в трекер. Теперь объект проинициализирован id.
		String newItemId = tracker.add(current).getId();
		// Проверяем, что заявка с таким id добавилась.
		assertThat(tracker.findById(newItemId).getName(), is("test1"));
		tracker.closeTrackerResources();
	}
	/**
	*method for update method testing.
	*/
	@Test
	public void whenUpdateNameThenReturnNewName() {
		Tracker tracker = new Tracker();
		tracker.clearItemsStorage();
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
		tracker.closeTrackerResources();
	}
	/**
	*method for delete method testing.
	*/
	@Test
	public void whenDeleteItemThenNotFoundById() {
		Tracker tracker = new Tracker();
		tracker.clearItemsStorage();
		Item current = new Item("test1", "testDescription", 123L);
		// Добавляем заявку в трекер. Теперь в объекте проинициализирован id.
		String newItemId = tracker.add(current).getId();
		// Удаляем заявку в трекере.
		tracker.delete(current);
		// Проверяем, что заявка была удалена.
		Item nullReference  = null;
		assertThat(tracker.findById(newItemId), is(nullReference));
		tracker.closeTrackerResources();
	}
	/**
	*method for findAll method testing.
	*/
	@Test
	public void whenFindAllThenAllFound() {
		Tracker tracker = new Tracker();
		tracker.clearItemsStorage();
		Item item1 = new Item("test23", "testDescription", 123L);
		Item item2 = new Item("test24", "testDescription", 123L);
		// Добавляем заявку в трекер. Теперь в объекте проинициализирован id.
		Item item1WithID = tracker.add(item1);
		Item item2WithID = tracker.add(item2);
		// Создаем массив из этих заявок.
		List<Item> isExpect = new ArrayList<>();
		isExpect.add(item1WithID);
		isExpect.add(item2WithID);
		// Проверяем, что возвращает все заявки.
		assertThat(tracker.findAll(), is(isExpect));
		tracker.closeTrackerResources();
	}
	/**
	*method for findByName method testing.
	*/
	@Test
	public void whenFindByNameThenFound() {
		Tracker tracker = new Tracker();
		tracker.clearItemsStorage();
		Item item1 = new Item("test1", "testDescription", 123L);
		Item item2 = new Item("test2", "testDescription", 123L);
		// Добавляем заявку в трекер. Теперь в объекте проинициализирован id.
		Item item1withId = tracker.add(item1);
		tracker.add(item2);
		// Создаем массив из этих заявок.
		String isExpect = item1withId.getId();
		// Проверяем, что возвращает массив с одной заявкой.
		assertThat(tracker.findByName("test1").get(0).getId(), is(isExpect));
		tracker.closeTrackerResources();
	}
	/**
	*method for findById method testing.
	*/
	@Test
	public void whenFindByIdThenFound() {
		Tracker tracker = new Tracker();
		tracker.clearItemsStorage();
		Item item1 = new Item("test1", "testDescription", 123L);
		Item item2 = new Item("test2", "testDescription", 123L);
		// Добавляем заявку в трекер. Теперь в объекте проинициализирован id.
		String id1 = tracker.add(item1).getId();
		String id2 = tracker.add(item2).getId();
		// Проверяем, что возвращает нужную заявку.
		assertThat(tracker.findById(id1).getName(), is("test1"));
		tracker.closeTrackerResources();
	}
}