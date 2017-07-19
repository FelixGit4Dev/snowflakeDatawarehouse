package visualization.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import visualization.entity.DynamicDataTableObject;

@Named
@SessionScoped
public class NativeQueryDatatableView implements Serializable {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final static List<String> VALID_COLUMN_KEYS = Arrays.asList("id", "brand", "year", "color", "price");
     
    private String columnTemplate = "id brand year";
     
    private List<ColumnModel> columns;
     
    private List<DynamicDataTableObject> cars;
     
 public void createColumns(List<String> namen){
	 columns = new ArrayList<ColumnModel>();  
	for(String name:namen ){
	columns.add(new ColumnModel(name, name));	
	}
 }
 
   
     
    public void setCars(List<DynamicDataTableObject> cars) {
		this.cars = cars;
	}


	public List<DynamicDataTableObject> getCars() {
        return cars;
    }
 
    
    public String getColumnTemplate() {
        return columnTemplate;
    }
 
    public void setColumnTemplate(String columnTemplate) {
        this.columnTemplate = columnTemplate;
    }
 
    public List<ColumnModel> getColumns() {
        return columns;
    }
 
    private void createDynamicColumns() {
        String[] columnKeys = columnTemplate.split(" ");
        columns = new ArrayList<ColumnModel>();   
         
        for(String columnKey : columnKeys) {
            String key = columnKey.trim();
             
            if(VALID_COLUMN_KEYS.contains(key)) {
                columns.add(new ColumnModel(columnKey.toUpperCase(), columnKey));
            }
        }
    }
     
    public void updateColumns() {
        //reset table state
        UIComponent table = FacesContext.getCurrentInstance().getViewRoot().findComponent(":form:cars");
        table.setValueExpression("sortBy", null);
         
        //update columns
        createDynamicColumns();
    }
     
    static public class ColumnModel implements Serializable {
 
        private String header;
        private String property;
 
        public ColumnModel(String header, String property) {
            this.header = header;
            this.property = property;
        }
 
        public String getHeader() {
            return header;
        }
 
        public String getProperty() {
            return property;
        }
    }
    
    
  public ArrayList<String> extractColumnNames(String query){
	  String testString=query;
	  ArrayList<String> list = new ArrayList<>();
	  testString = testString.trim();
	  if(testString.toUpperCase().startsWith("SELECT")){
	  testString=testString.substring(6);
	  int index = testString.toUpperCase().lastIndexOf("FROM");
	  testString = testString.substring(0, index);
	  String[] arr = testString.split(",");
	
	  for(String s : arr){
	list.add(s.trim());
	  }
	  
	  }
	  return list;
  }  
    
    
  public static void main (String[] args){
String testString="select customer.CustomerID, store.Name from customer inner join store  on customer.CustomerID=store.CustomerID";

testString = testString.trim();
if(testString.toUpperCase().startsWith("SELECT")){
testString=testString.substring(6);
int index = testString.toUpperCase().lastIndexOf("FROM");
testString = testString.substring(0, index);
String[] arr = testString.split(",");
for(String s : arr){
System.out.println(s.trim());	
}
}
  }  
    
    
    
    
}