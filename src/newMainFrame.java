import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.CardLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextPane;

public class newMainFrame {

	public JFrame frmTodoList;
	private JTextField txtName;
	private JTextField txtDescription;
	private JTextField txtStart;
	private JTextField txtEnd;
	private JTable table;
	private Datastore tasks;
	final JFileChooser fc = new JFileChooser();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					newMainFrame window = new newMainFrame();
					window.frmTodoList.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public newMainFrame() {
		this.tasks = new Datastore();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmTodoList = new JFrame();
		frmTodoList.setTitle("TODO List");
		frmTodoList.setBounds(100, 100, 700, 400);
		frmTodoList.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTodoList.getContentPane().setLayout(new CardLayout(0, 0));

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frmTodoList.getContentPane().add(tabbedPane, "name_45570727554346");

		JPanel tablePane = new JPanel();
		tabbedPane.addTab("All", null, tablePane, null);
		tablePane.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		tablePane.add(scrollPane, BorderLayout.CENTER);

		table = new JTable(this.tasks);
		table.getModel().addTableModelListener(this.tasks);
		scrollPane.setViewportView(table);

		JInternalFrame internalFrame = new JInternalFrame("Commands");
		tablePane.add(internalFrame, BorderLayout.NORTH);
		internalFrame.getContentPane().setLayout(new GridLayout(0, 3, 0, 0));

		JButton btnNewButton = new JButton("New button");
		internalFrame.getContentPane().add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				NewTaskPrompt prompt = new NewTaskPrompt(tasks);
				prompt.setVisible(true);
			}
		});

		JButton btnDeleteTask = new JButton("Delete Task");
		internalFrame.getContentPane().add(btnDeleteTask);
		internalFrame.setVisible(true);
		btnDeleteTask.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				tasks.deleteRow(table.getSelectedRow());

			}
		});
		JButton btnExportTask = new JButton("Export");
		internalFrame.getContentPane().add(btnExportTask);
		btnExportTask.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				FileWriter fw;
				try {
					fw = new FileWriter("tasks.csv");
					BufferedWriter bw = new BufferedWriter(fw);
					for (int i = 0; i < tasks.objects.size(); i++) {
						bw.write(String.format("Task: %d, Name: \"%s\", Description: \"%s\", Started: %s, Ended: %s\n",
								tasks.objects.get(i).id, tasks.objects.get(i).getName(),
								tasks.objects.get(i).getTaskDiscription(),
								tasks.objects.get(i).getStartDate().toString(),
								tasks.objects.get(i).getEndDate().toString()));
					}
					bw.close();
					fw.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}

		});

		JPanel singlePane = new JPanel();
		tabbedPane.addTab("Single", null, singlePane, null);
		singlePane.setLayout(new GridLayout(6, 2, 0, 0));

		JLabel lblName = new JLabel("Name:");
		singlePane.add(lblName);

		txtName = new JTextField();
		txtName.setText("Name");
		singlePane.add(txtName);
		txtName.setColumns(10);

		JLabel lblDescription = new JLabel("Description:");
		singlePane.add(lblDescription);

		txtDescription = new JTextField();
		txtDescription.setText("Description");
		singlePane.add(txtDescription);
		txtDescription.setColumns(10);

		JButton btnComplete = new JButton("Complete");
		singlePane.add(btnComplete);

		JButton btnLater = new JButton("Move Down");
		singlePane.add(btnLater);
		btnLater.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Datastoreobject current = tasks.getNext();
				Integer nextID = new Integer(current.id + 1);
				tasks.setValueAt((Object) nextID.toString(), current.id - 1, 0);

			}
		});

		btnComplete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				tasks.getNext().autoEndDate();
				tasks.fireTableDataChanged();

				Datastoreobject nextTask = tasks.getNext();
				if (nextTask == null) {
					txtDescription.setText("No more tasks");
					txtName.setText("No more tasks");
					txtStart.setText("No more tasks");
					txtEnd.setText("No more tasks");
					btnComplete.setEnabled(false);
					btnLater.setEnabled(false);

				}
				btnComplete.setEnabled(true);
				btnLater.setEnabled(true);
				txtDescription.setText(nextTask.getTaskDiscription());
				txtName.setText(nextTask.getName());
				txtStart.setText(nextTask.getStartDate().toString());
				txtEnd.setText("Not yet completed");

			}
		});

		JLabel lblStart = new JLabel("Start");
		singlePane.add(lblStart);

		JLabel lblEnd = new JLabel("End");
		singlePane.add(lblEnd);

		txtStart = new JTextField();
		txtStart.setText("Start");
		singlePane.add(txtStart);
		txtStart.setColumns(10);

		txtEnd = new JTextField();
		txtEnd.setText("End");
		singlePane.add(txtEnd);
		txtEnd.setColumns(10);

		JButton btnNewTask_2 = new JButton("New Task");
		btnNewTask_2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				NewTaskPrompt prompt = new NewTaskPrompt(tasks);
				prompt.setVisible(true);
			}
		});
		singlePane.add(btnNewTask_2);
		
		JPanel helpPane = new JPanel();
		tabbedPane.addTab("Help", null, helpPane, null);
		helpPane.setLayout(new BorderLayout(0, 0));
		
		JTextPane txtpnHowToUse = new JTextPane();
		txtpnHowToUse.setText("How to use this TODO list:"
				+ "\n\n"
				+ "The 'All' panel will show all of the tasks, to change the order, click the ID column and enter the new ID for the task."
				+ "\n\n"
				+ "The Single panel will show one task, the next task that has yet to be completed"
				+ "\n"
				+ "The 'Move Down' button will move the task back one position in the list.\n"
				+ "The export button on the 'All' page will save the current tasks to disk, in the folder that this app was run from.");
		helpPane.add(txtpnHowToUse);
		tabbedPane.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				System.out.println(e.getSource());
				Datastoreobject nextTask = tasks.getNext();
				txtDescription.setText(nextTask.getTaskDiscription());
				txtName.setText(nextTask.getName());
				txtStart.setText(nextTask.getStartDate().toString());
				txtEnd.setText("Not yet completed");

			}
		});
	}
}
