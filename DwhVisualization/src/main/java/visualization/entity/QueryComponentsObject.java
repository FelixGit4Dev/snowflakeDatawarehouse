package visualization.entity;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class QueryComponentsObject {

	private ArrayList<String> joins;
	
	private LinkedHashMap<String, String> select;
	
	private ArrayList<String> where;
	
	private ArrayList<String> groupBy;

	public ArrayList<String> getJoins() {
		return joins;
	}

	public void setJoins(ArrayList<String> joins) {
		this.joins = joins;
	}

	public LinkedHashMap<String, String> getSelect() {
		return select;
	}

	public QueryComponentsObject(ArrayList<String> joins, LinkedHashMap<String, String> select,
			ArrayList<String> where, ArrayList<String> groupBy) {
		super();
		this.groupBy=groupBy;
		this.joins = joins;
		this.select = select;
		this.where = where;
	}

	public void setSelect(LinkedHashMap<String, String> select) {
		this.select = select;
	}

	public ArrayList<String> getWhere() {
		return where;
	}

	public void setWhere(ArrayList<String> where) {
		this.where = where;
	}

	public ArrayList<String> getGroupBy() {
		return groupBy;
	}

	public void setGroupBy(ArrayList<String> groupBy) {
		this.groupBy = groupBy;
	}
}
