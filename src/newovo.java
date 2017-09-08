import java.awt.EventQueue;


public class newovo {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VideoFeed window2 = new VideoFeed();
					window2.frmVideoFeed.setVisible(true);
					newMainFrame window = new newMainFrame();
					window.frmTodoList.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
