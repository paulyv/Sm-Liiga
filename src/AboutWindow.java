import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AboutWindow extends JFrame {

	private JPanel contentPane;
	ImageIcon bg = new ImageIcon(getClass().getResource("/img/aboutBG.png"));

	public AboutWindow() {
		setTitle("About");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 640, 298);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(0, 33, 86));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblDesigned = new JLabel("Designed By");
		lblDesigned.setFont(new Font("Lucida Grande", Font.BOLD, 41));
		lblDesigned.setForeground(Color.WHITE);
		lblDesigned.setBounds(252, 47, 307, 49);
		contentPane.add(lblDesigned);

		JLabel lblPauli = new JLabel("Pauli Varelius");
		lblPauli.setFont(new Font("Abadi MT Condensed Extra Bold", Font.BOLD,
				37));
		lblPauli.setForeground(Color.WHITE);
		lblPauli.setBounds(358, 108, 255, 49);
		contentPane.add(lblPauli);

		JLabel label = new JLabel("2013-2014");
		label.setFont(new Font("Geneva", Font.PLAIN, 26));
		label.setForeground(Color.WHITE);
		label.setBounds(30, 28, 210, 25);
		contentPane.add(label);

		JButton btnClose = new JButton("Close");
		btnClose.setForeground(Color.DARK_GRAY);
		btnClose.setFocusable(false);
		btnClose.setFocusTraversalKeysEnabled(false);
		btnClose.setFocusPainted(false);
		btnClose.setBackground(Color.GRAY);
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnClose.setBounds(298, 241, 72, 29);
		contentPane.add(btnClose);

		JLabel BGimgLabel = new JLabel(new ImageIcon(
				AboutWindow.class.getResource("/img/aboutBG.png")));
		BGimgLabel.setBounds(0, 0, 639, 276);
		contentPane.add(BGimgLabel);
	}
}
