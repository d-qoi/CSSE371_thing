import java.util.ArrayList;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

public class Datastore extends AbstractTableModel implements TableModelListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3503642854424338908L;
	public ArrayList<Datastoreobject> objects;
	public String[] columnNames = {"ID", "Name", "Description", "Start", "End", "Completed"};
	private ArrayList<ArrayList<Object>> data;
	

	public Datastore() {
		objects = new ArrayList<>();
		data = new ArrayList<ArrayList<Object>>();
	}

	public Datastore(int initialCapacity) {
		objects = new ArrayList<>(initialCapacity);
	}

	public void addTask(Datastoreobject task) {
		objects.add(task);
//		this.fireTableRowsInserted(0, this.objects.size());
		this.updateData();
	}
	
	public void removeTask(Datastoreobject task) {
		objects.remove(task);
//		this.fireTableRowsDeleted(0, this.objects.size());
		this.updateData();
	}
	
	public int getNextID() {
		return this.objects.size()+1;
	}

	@Override
	public int getColumnCount() {
		return this.columnNames.length;
	}
	
	public String getColumnName(int col) {
		return columnNames[col];
	}

	@Override
	public int getRowCount() {
		return this.data.size();
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		return data.get(arg0).get(arg1);
	}
	
	public boolean isCellEditable(int row, int col) {
		return (col != 3 && col != 4);
	}
	
	public void setValueAt(Object value, int row, int col) {
		if (col == 0) {
			value = (Object)(new Integer((String)value));
		}
		data.get(row).set(col, value);
		fireTableCellUpdated(row, col);
	}
	
	public void tableChanged(TableModelEvent e) {
		System.out.println("Data Change Detected!");
		int row = e.getFirstRow();
		int col = e.getColumn();
		TableModel model = (TableModel)e.getSource();
		if (row < 0 || col < 0)
			return;
		Object value = model.getValueAt(row, col);
		this.setForID(row, col, value);
	}
	private void updateData() {
		this.data.clear();
		ArrayList<Object> newRow;
		for (Datastoreobject row: this.objects) {
			newRow = new ArrayList<>(6);
			newRow.add(row.id);
			newRow.add(new String(row.getName()));
			newRow.add(new String(row.getTaskDiscription()));
			newRow.add(row.getStartDate());
			if (row.getEndDate() != null)
				newRow.add(row.getEndDate());
			else
				newRow.add("Null");
			newRow.add(new Boolean(row.isCompleted()));
			this.data.add(newRow);
		}
		this.fireTableDataChanged();
	}
	
	private void setForID(int id, int col, Object value) {
		for (int i = 0; i < this.objects.size(); i++) {
			if (this.objects.get(i).id == id) {
				switch (col) {
				case 0:
					int newID = (int) value;
					if (newID < 1)
						newID = 1;
					if (newID > this.objects.size())
						newID = this.objects.size();
					Datastoreobject temp = this.objects.remove(id);
					this.objects.add(newID-1, temp);
					this.fixNumbering();
				case 1:
					this.objects.get(i).setName(value.toString());
					break;
				case 2:
					this.objects.get(i).setTaskDiscription(value.toString());
					break;
				case 5:
					if ((Boolean) value)
						this.objects.get(i).autoEndDate();
					else
						this.objects.get(i).setEndDate(null);
				}
			}
		}
	}

	public void deleteRow(int selectedRow) {
		this.objects.remove(selectedRow);
		this.fixNumbering();
		
	}
	
	private void fixNumbering() {
		System.out.println("Fixing numbering");
		for (int i = 0; i < this.objects.size(); i++) {
			this.objects.get(i).id = i+1;
		}
		this.updateData();
		this.fireTableDataChanged();
	}
	
	public Datastoreobject getNext() {
		for (int i = 0; i<this.objects.size(); i++) {
			if(!this.objects.get(i).isCompleted())
				return this.objects.get(i);
		}
		return null;
	}
}
	
//	public ArrayList<Datastoreobject> sortByNameInOrder() {
//	Collections.sort(objects, new Comparator<Datastoreobject>() {
//
//		@Override
//		public int compare(Datastoreobject o1, Datastoreobject o2) {
//			return o1.name.compareTo(o2.name);
//		}
//
//	});
//	return objects;
//}
//
//public ArrayList<Datastoreobject> sortByNameReverseOrder() {
//	Collections.sort(objects, new Comparator<Datastoreobject>() {
//
//		@Override
//		public int compare(Datastoreobject o1, Datastoreobject o2) {
//			return o2.name.compareTo(o1.name);
//		}
//
//	});
//	return objects;
//}
//
//public ArrayList<Datastoreobject> sortByStartDateInOrder() {
//	Collections.sort(objects, new Comparator<Datastoreobject>() {
//
//		@Override
//		public int compare(Datastoreobject o1, Datastoreobject o2) {
//			return o1.startDate.compareTo(o2.startDate);
//		}
//
//	});
//	return objects;
//}
//
//public ArrayList<Datastoreobject> sortByStartDateReverseOrder() {
//	Collections.sort(objects, new Comparator<Datastoreobject>() {
//
//		@Override
//		public int compare(Datastoreobject o1, Datastoreobject o2) {
//			return o2.startDate.compareTo(o1.startDate);
//		}
//
//	});
//	return objects;
//}
//
//public ArrayList<Datastoreobject> sortByEndDateInOrder() {
//	Collections.sort(objects, new Comparator<Datastoreobject>() {
//
//		@Override
//		public int compare(Datastoreobject o1, Datastoreobject o2) {
//			return o1.endDate.compareTo(o2.endDate);
//		}
//
//	});
//	return objects;
//}
//
//public ArrayList<Datastoreobject> sortByEndDateReverseOrder() {
//	Collections.sort(objects, new Comparator<Datastoreobject>() {
//
//		@Override
//		public int compare(Datastoreobject o1, Datastoreobject o2) {
//			return o2.endDate.compareTo(o1.endDate);
//		}
//
//	});
//	return objects;
//}
//
//public ArrayList<Datastoreobject> sortByCompletedInOrder() {
//	Collections.sort(objects, new Comparator<Datastoreobject>() {
//
//		@Override
//		public int compare(Datastoreobject o1, Datastoreobject o2) {
//			if (o1.isCompleted() && o2.isCompleted())
//				return o1.endDate.compareTo(o2.endDate);
//			if (!o1.isCompleted() && o2.isCompleted())
//				return -1;
//			if (o1.isCompleted() && !o2.isCompleted())
//				return 1;
//			return o1.startDate.compareTo(o2.startDate);
//		}
//
//	});
//	return objects;
//}
//
//
//}
//
//public ArrayList<Datastoreobject> sortByCompletedReverseOrder() {
//	sortByCompletedInOrder();
//	Collections.reverse(objects);
//	return objects;
//}

