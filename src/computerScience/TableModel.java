package computerScience;

import javax.swing.Icon;
import javax.swing.table.AbstractTableModel;

public class TableModel extends AbstractTableModel{

	    private String[] columns;
	    private Object[][] rows;
	    
	    
	    public TableModel(Object[][] rows, String[] header){
	    
	        this.rows = rows;
	        this.columns = header;
	    }

	    public Class getColumnClass(int column){
	// 2 is the index of the column image
	        if(column == 2){
	            return Icon.class;
	        } else {
	            return getValueAt(0,column).getClass();
	        }
	    }
	    
	    
	    public int getRowCount() {
	     return this.rows.length;
	    }

	    public int getColumnCount() {
	     return this.columns.length;
	    }

	    
	    public Object getValueAt(int rowI, int colI) {
	    
	    return this.rows[rowI][colI];
	    
	    }
	    public String getColumnName(int columns){
	        return this.columns[columns];
	    }


}
