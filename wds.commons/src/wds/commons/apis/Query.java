package wds.commons.apis;

public class Query {
	
	private Condition condition;
	
	private String field;
	
	private String value;
	
	private String value2;
	
	public Condition getCondition() {
		return condition;
	}

	public void setCondition(Condition condition) {
		this.condition = condition;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getValue2() {
		return value2;
	}

	public void setValue2(String value2) {
		this.value2 = value2;
	}

	public enum Condition {
		
		LIKE("like"),
		
		EQUAL("equal"),
		
		BETWEEN("between");
		
		Condition(String name) {
			
		}
	}
	
}
