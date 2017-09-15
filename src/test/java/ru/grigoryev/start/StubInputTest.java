package ru.grigoryev.start;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import ru.grigoryev.models.Item;

/**
*Class for class Tracker testing.
*@author vgrigoryev
*@since 04.09.2017
*@version 1
*/
public class StubInputTest {
	/**
	*method for add testing.
	*/
	@Test
	public void whenUserAddItemThenTrackerHasNewItemWithSameName() {
		Tracker tracker = new Tracker(); // создаём Tracker
		Input input = new StubInput(new String[]{"0", "test name", "desc", "6"}); //создаём StubInput с последовательностью действий
		new StartUI(input, tracker).init();     //   создаём StartUI и вызываем метод init()
		assertThat(tracker.findAll().get(0).getName(), is("test name")); // проверяем, что нулевой элемент массива в трекере содержит имя, введённое при эмуляции.
	}
	/**
	*method for findAll testing.
	*/
	@Test
	public void whenUserfindAllItemsThenTrackerReturnsAllItems() {
		Tracker tracker = new Tracker(); // создаём Tracker
		Input input = new StubInput(new String[]{"0", "test name", "desc", "0", "test name2", "desc", "1", "6"}); //создаём StubInput с последовательностью действий
		new StartUI(input, tracker).init();     //   создаём StartUI и вызываем метод init()
		String[] expected = new String[4]; //Массив ожидаемых данных
		expected[0] = new String("Name: test name Description: desc ID: " + tracker.findAll().get(0).getId() + "\n");
		expected[1] = "\n";
		expected[2] = new String("Name: test name2 Description: desc ID: " + tracker.findAll().get(1).getId() + "\n");
		expected[3] = "\n";
		String[] result = Arrays.copyOf(((StubInput) input).getOutputBuffer(), 4); // Массив добавленных имен
		assertThat(result, is(expected)); // проверяем, в буфер были выведены все элементы из треккера.
	}
	/**
	*method for update item testing.
	*/
	@Test
	public void whenUpdateThenTrackerHasUpdatedValue() {
		// создаём Tracker
		Tracker tracker = new Tracker();
		//Напрямую добавляем заявку
		Item item = tracker.add(new Item());
		//создаём StubInput с последовательностью действий
		Input input = new StubInput(new String[]{"2", item.getId(), "test name", "desc", "6"});
		// создаём StartUI и вызываем метод init()
		new StartUI(input, tracker).init();
		// проверяем, что нулевой элемент массива в трекере содержит имя, введённое при эмуляции.
		assertThat(tracker.findById(item.getId()).getName(), is("test name"));
	}
	/**
	*method for delete item testing.
	*/
	@Test
	public void whenDeleteThenTrackerHasNoValue() {
		// создаём Tracker
		Tracker tracker = new Tracker();
		//Напрямую добавляем заявку
		Item item = tracker.add(new Item("test name", "desc", System.currentTimeMillis()));
		//создаём StubInput с последовательностью действий
		Input input = new StubInput(new String[]{"3", item.getId(), "6"});
		// создаём StartUI и вызываем метод init()
		new StartUI(input, tracker).init();
		Item nullReference = null;
		// проверяем, что в треккере нет элемента с id, который был добавлен при эмуляции.
		assertThat(tracker.findById(item.getId()), is(nullReference));
	}
	/**
	*method for find item by id testing.
	*/
	@Test
	public void whenUserfindByIdItemThenTrackerReturnsItemWithSameId() {
		Tracker tracker = new Tracker(); // создаём Tracker
		//Напрямую добавляем заявку
		Item item1 = tracker.add(new Item("test name1", "desc", System.currentTimeMillis()));
		//Напрямую добавляем еще одну заявку
		Item item2 = tracker.add(new Item("test name2", "desc", System.currentTimeMillis()));
		//создаём StubInput с последовательностью действий
		Input input = new StubInput(new String[]{"4", item1.getId(), "6"});
		new StartUI(input, tracker).init();     //   создаём StartUI и вызываем метод init()
		String[] expected = new String[1]; // Массив ожидаемых данных
		expected[0] = new String("Name: test name1 Description: desc" + "\n");
		String[] result = Arrays.copyOf(((StubInput) input).getOutputBuffer(), 1); // Массив добавленных имен
		assertThat(result, is(expected)); // проверяем, в буфер был выведен элемент с тем id, с которым мы искали.
	}
	/**
	*method for find all by name testing.
	*/
	@Test
	public void whenUserfindByNameThenTrackerReturnsAllItemsWithSameName() {
		Tracker tracker = new Tracker(); // создаём Tracker
		//Напрямую добавляем заявку
		Item item1 = tracker.add(new Item("test name1", "desc", System.currentTimeMillis()));
		//Напрямую добавляем еще одну заявку
		Item item2 = tracker.add(new Item("test name2", "desc", System.currentTimeMillis()));
		//Напрямую добавляем еще одну заявку с именем как у первой
		Item item3 = tracker.add(new Item("test name1", "desc", System.currentTimeMillis()));
		//создаём StubInput с последовательностью действий
		Input input = new StubInput(new String[]{"5", "test name1", "6"});
		new StartUI(input, tracker).init();     //   создаём StartUI и вызываем метод init()
		String[] expected = new String[2]; // Массив ожидаемых данных
		expected[0] = new String("Name: test name1 Description: desc" + "\n");
		expected[1] = new String("Name: test name1 Description: desc" + "\n");
		String[] result = Arrays.copyOf(((StubInput) input).getOutputBuffer(), 2); // Массив добавленных имен
		assertThat(result, is(expected)); // проверяем, в буфер был выведен элемент с тем name, с которым мы искали.
	}
}
