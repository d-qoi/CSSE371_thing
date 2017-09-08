import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;

import java.awt.GridLayout;

public class NewTaskPrompt extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6315437726042614010L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtName;
	private JTextField txtDescription;
	private boolean createTask = false;


	/**
	 * Create the dialog.
	 */
	public NewTaskPrompt(Datastore taskList) {
		setTitle("New Task");
		setAlwaysOnTop(true);
		setModal(true);
		setResizable(false);
		setBounds(100, 100, 500, 150);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(0, 1, 0, 0));
		{
			txtName = new JTextField();
			txtName.setText("Name");
			contentPanel.add(txtName);
			txtName.setColumns(10);
		}
		{
			txtDescription = new JTextField();
			txtDescription.setText("Description");
			contentPanel.add(txtDescription);
			txtDescription.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			FlowLayout fl_buttonPane = new FlowLayout(FlowLayout.RIGHT);
			buttonPane.setLayout(fl_buttonPane);
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setToolTipText("Create New Task");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
				okButton.addActionListener(actionEvent -> {
					this.dispose();
					this.createTask = true;
					taskList.addTask(new Datastoreobject(getTaskName(), getTaskDesc(), taskList.getNextID()));
				});
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setToolTipText("Don't creat new task.");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
				cancelButton.addActionListener(actionEvent -> {
					this.dispose();
				});
			}
		}
	}
	
	public boolean getCreateTask() {
		return this.createTask;
	}
	
	public String getTaskName() {
		return this.txtName.getText();
	}
	
	public String getTaskDesc() {
		return this.txtDescription.getText();
	}

}
