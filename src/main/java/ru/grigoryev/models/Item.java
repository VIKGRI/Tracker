package ru.grigoryev.models;

/**
*Class represents the item.
*@author vgrigoryev
*@since 04.09.2017
*@version 1
*/
public class Item {
	/**
	*Id of item.
	*/
	private String id;
	/**
	*Name of item.
	*/
	private String name;
	/**
	*Description of item.
	*/
	private String description;
	/**
	*Time of creation.
	*/
	private long create;
	/**
	*Default constructor.
	*/
	public Item() {
	}
	/**
	*Constructor with parameters.
	*@param name Item's name
	*@param description Item's description
	*@param create Item's creation time
	*/
	public Item(String name, String description, long create) {
		this.name = name;
		this.description = description;
		this.create = create;
	}
	/**
	*This method is used for getting item's name.
	*@return Item's name.
	*/
	public String getName() {
		return this.name;
	}
	/**
	*This method is used for getting item's description.
	*@return Item's description.
	*/
	public String getDescription() {
		return this.description;
	}
	/**
	*This method is used for getting item's time of creation.
	*@return Item's time of creation.
	*/
	public long getCreate() {
		return this.create;
	}
	/**
	*This method is used for getting item's id.
	*@return Item's id.
	*/
	public String getId() {
		return this.id;
	}
	/**
	*This method is used for setting item's id.
	*@param id id
	*/
	public void setId(String id) {
		this.id = id;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		Item item = (Item) o;

		if (create != item.create) {
			return false;
		}
		if (!id.equals(item.id)) {
			return false;
		}
		if (name != null ? !name.equals(item.name) : item.name != null) {
			return false;
		}
		return description != null ? description.equals(item.description) : item.description == null;
	}

	@Override
	public int hashCode() {
		int result = id.hashCode();
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + (description != null ? description.hashCode() : 0);
		result = 31 * result + (int) (create ^ (create >>> 32));
		return result;
	}
}