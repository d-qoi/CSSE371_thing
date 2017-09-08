import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class VideoFeed {

	public JFrame frmVideoFeed;


	/**
	 * Create the application.
	 */
	public VideoFeed() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmVideoFeed = new JFrame();
		frmVideoFeed.setTitle("Video Feed Placeholder");
		frmVideoFeed.setBounds(100, 100, 450, 300);
		frmVideoFeed.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmVideoFeed.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JCheckBox chckbxPlayAudio = new JCheckBox("Play Audio");
		frmVideoFeed.getContentPane().add(chckbxPlayAudio, BorderLayout.SOUTH);
		
		JButton btnVideoFe = new JButton("Video Feed");
		frmVideoFeed.getContentPane().add(btnVideoFe, BorderLayout.CENTER);
		
		JMenuBar menuBar = new JMenuBar();
		frmVideoFeed.setJMenuBar(menuBar);
		
		JMenu mnAudioMenu = new JMenu("Audio Menu");
		menuBar.add(mnAudioMenu);
		
		JMenuItem mntmAudioMixer = new JMenuItem("Audio Mixer");
		mnAudioMenu.add(mntmAudioMixer);
		
		JMenuItem mntmAudioVolume = new JMenuItem("Audio Volume");
		mnAudioMenu.add(mntmAudioVolume);
		
		JMenuItem mntmAudioSettings = new JMenuItem("Audio Settings");
		mnAudioMenu.add(mntmAudioSettings);
		
		JMenu mnVideoMenu = new JMenu("Video Menu");
		menuBar.add(mnVideoMenu);
		
		JMenu mnVideoSettings = new JMenu("Video Settings");
		mnVideoMenu.add(mnVideoSettings);
	}

}
