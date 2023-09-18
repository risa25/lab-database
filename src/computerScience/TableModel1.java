package computerScience;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class TableModel1 extends AbstractTableModel {

	private ArrayList row = null;
	private Object[] column = null;
	
	public TableModel1(ArrayList row, Object[] column) {
		this.row = row;
		this.column = column;
	}
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return row.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return column.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		Object[] row = (Object[]) getRow().get(rowIndex);
		return row[columnIndex];
	}

	private ArrayList getRows() {
		// TODO Auto-generated method stub
		return row;
	}
	
	public Object[] getColumn() {
		return column;
	}
	
	public void setColumn(Object[] column) {
		this.column = column;
		
	}
	
	public ArrayList getRow() {
		return row;
	}
	
	public void setRow(ArrayList row) {
		this.row = row;
	}
	
	
	

}
