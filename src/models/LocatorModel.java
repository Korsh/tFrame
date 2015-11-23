package models;

public class LocatorModel<TYPE, VALUE> {
	protected final TYPE type;
	protected final VALUE value;

	public LocatorModel(TYPE in_type, VALUE in_value) {
		type = in_type;
		value = in_value;
	}

	public TYPE getType() {
		return type;
	}

	public VALUE getValue() {
		return value;
	}

}
