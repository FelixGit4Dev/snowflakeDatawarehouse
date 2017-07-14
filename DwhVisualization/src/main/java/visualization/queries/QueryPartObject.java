package visualization.queries;

public class QueryPartObject {

	
	private  String tableName;
	private String alias;
	private String joinAttribute;
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getJoinAttribute() {
		return joinAttribute;
	}
	public void setJoinAttribute(String joinAttribute) {
		this.joinAttribute = joinAttribute;
	}
	public QueryPartObject(String tableName, String alias, String joinAttribute) {
		super();
		this.tableName = tableName;
		this.alias = alias;
		this.joinAttribute = joinAttribute;
	}
	public QueryPartObject() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
