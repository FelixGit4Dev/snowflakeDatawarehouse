package visualization.queries;

public class QueryPartObject {

	
	private  String tableName;
	private String alias;
	private String joinAttribute;
	private String primarykeyAttribute;
	private String filterAttribute;
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
	public QueryPartObject(String tableName, String alias, String joinAttribute, String filterAttribute,String primary) {
		super();
		this.tableName = tableName;
		this.alias = alias;
		this.joinAttribute = joinAttribute;
		this.filterAttribute=filterAttribute;
		this.primarykeyAttribute=primary;
	}
	public QueryPartObject() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getFilterAttribute() {
		return filterAttribute;
	}
	public void setFilterAttribute(String filterAttribute) {
		this.filterAttribute = filterAttribute;
	}
	public String getPrimarykeyAttribute() {
		return primarykeyAttribute;
	}
	public void setPrimarykeyAttribute(String primarykeyAttribute) {
		this.primarykeyAttribute = primarykeyAttribute;
	}
	
}
