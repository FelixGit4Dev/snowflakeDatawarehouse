package visualization.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import visualization.entity.DataTableObject;

 
@SessionScoped
@Named
public class ColumnManagerView implements Serializable {
     
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final List<String> VALID_COLUMN_KEYS = Arrays.asList("total", "quantity", "unitPrice", "taxAmt","discount","totalCost","standartCost","margin");
     
    private List<ColumnModel> columns = new ArrayList<ColumnModel>();
     
    private List<DataTableObject> tableObjects;
     
    private TreeNode availableColumns;

	private HashMap<String, Integer> map=new HashMap<>();
     
     
    @PostConstruct
    public void init() {
        createAvailableColumns();
        createDynamicColumns();
    }
     
    private void createAvailableColumns() {
        availableColumns = new DefaultTreeNode("Root", null);
        TreeNode rootKennzahlen = new DefaultTreeNode("Kennzahlen", availableColumns);
       TreeNode rootDimensionen = new DefaultTreeNode("Dimensionen", availableColumns);
        rootKennzahlen.setExpanded(true);
        rootDimensionen.setExpanded(true);
        // Kennzahlen
        TreeNode model = new DefaultTreeNode("column", new ColumnModel("Total", "total"), rootKennzahlen);
        map.put("total", 0);
        TreeNode year = new DefaultTreeNode("column", new ColumnModel("Quantity", "quantity"), rootKennzahlen);
        map.put("quantity", 0);
        TreeNode manufacturer = new DefaultTreeNode("column", new ColumnModel("Unit Price", "unitPrice"), rootKennzahlen);
        map.put("unitPrice", 0);       
        TreeNode taxamt = new DefaultTreeNode("column", new ColumnModel("Tax Amt", "taxAmt"), rootKennzahlen);
        map.put("taxAmt", 0);
        TreeNode discount = new DefaultTreeNode("column", new ColumnModel("Discount", "discount"), rootKennzahlen);
        map.put("discount", 0);
        TreeNode totalCost = new DefaultTreeNode("column", new ColumnModel("Total Cost", "totalCost"), rootKennzahlen);
        map.put("totalCost", 0);
        TreeNode standartCost = new DefaultTreeNode("column", new ColumnModel("Standart Cost", "standartCost"), rootKennzahlen);
        map.put("standartCost", 0);
        TreeNode margin = new DefaultTreeNode("column", new ColumnModel("Margin", "margin"), rootKennzahlen);
        map.put("margin", 0);
        
        
        //Dimensionen
        TreeNode orderDate = new DefaultTreeNode("column", new ColumnModel("Order Date", "orderDate"), rootDimensionen);
        map.put("orderDate", 1);
        TreeNode shipDate = new DefaultTreeNode("column", new ColumnModel("Ship Date", "shipDate"), rootDimensionen);
        map.put("shipDate", 1);
        TreeNode billTo = new DefaultTreeNode("column", new ColumnModel("Bill To", "billTo"), rootDimensionen);
        map.put("billTo", 1);
        TreeNode shipTo = new DefaultTreeNode("column", new ColumnModel("Ship To", "shipTo"), rootDimensionen);
        map.put("shipTo", 1);
        TreeNode productDimension = new DefaultTreeNode("column", new ColumnModel("ProductDimension", "productDimension"), rootDimensionen);
        map.put("productDimension", 1);
        TreeNode salesReason = new DefaultTreeNode("column", new ColumnModel("SalesReason", "salesReason"), rootDimensionen);
        map.put("salesReason", 1);
        TreeNode salesPerson = new DefaultTreeNode("column", new ColumnModel("SalesPerson", "salesPerson"), rootDimensionen);
        map.put("salesPerson", 1);
        TreeNode shippingMethod = new DefaultTreeNode("column", new ColumnModel("ShippingMethod", "shippingMethod"), rootDimensionen);
        map.put("shippingMethod", 1);
        TreeNode salesChannel = new DefaultTreeNode("column", new ColumnModel("SalesChannel", "salesChannel"), rootDimensionen);
        map.put("salesChannel", 1);
        TreeNode customerRegion = new DefaultTreeNode("column", new ColumnModel("CustomerRegion", "customerRegion"), rootDimensionen);
        map.put("customerRegion", 1);
    }
       
    public void createDynamicColumns() {
        String[] columnKeys = new String[]{"margin"};
        columns.clear();  
          
        for(String columnKey : columnKeys) {
            String key = columnKey.trim();
              
            if(VALID_COLUMN_KEYS.contains(key)) {
                columns.add(new ColumnModel(columnKey.toUpperCase(), columnKey));
            }
        }
    }
         
    public void treeToTable() {
        Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String property = params.get("property");
        String droppedColumnId = params.get("droppedColumnId");
        String dropPos = params.get("dropPos");
          
        String[] droppedColumnTokens = droppedColumnId.split(":");
        int draggedColumnIndex = Integer.parseInt(droppedColumnTokens[droppedColumnTokens.length - 1]);
        int dropColumnIndex = draggedColumnIndex + Integer.parseInt(dropPos);
          
        //add to columns
        this.columns.add(dropColumnIndex, new ColumnModel(property.toUpperCase(), property));
          
        //remove from nodes
        TreeNode root = availableColumns.getChildren().get(0);
        for(TreeNode node : root.getChildren()) {
            ColumnModel model = (ColumnModel) node.getData();
            if(model.getProperty().equals(property)) {
                root.getChildren().remove(node);
                break;
            }
        }
      root = availableColumns.getChildren().get(1);
        for(TreeNode node : root.getChildren()) {
            ColumnModel model = (ColumnModel) node.getData();
            if(model.getProperty().equals(property)) {
                root.getChildren().remove(node);
                break;
            }
        }
    }
      
    public void tableToTree() {
        Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        int colIndex = Integer.parseInt(params.get("colIndex"));
          
        //remove from table
        ColumnModel model = this.columns.remove(colIndex);
          
        //add to nodes
        if(this.map.get(model.getProperty())==0){
        TreeNode property = new DefaultTreeNode("column", model, availableColumns.getChildren().get(0));}
        else{
        	TreeNode property = new DefaultTreeNode("column", model, availableColumns.getChildren().get(1));	
        }
    }
 

 
    public List<ColumnModel> getColumns() {
        return columns;
    }
 
    public TreeNode getAvailableColumns() {
        return availableColumns;
    }
 

    public List<DataTableObject> getTableObjects() {
		return tableObjects;
	}

	public void setTableObjects(List<DataTableObject> tableObjects) {
		this.tableObjects = tableObjects;
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
}