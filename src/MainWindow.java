import java.io.IOException;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainWindow extends JFrame {

	private JPanel contentPane;
	ImageIcon logo = new ImageIcon(getClass().getResource("/img/blues3.png"));
	boolean isGames = false;

	public MainWindow() throws IOException {
		setTitle("JŠŠkiekon SM-liiga");
		setUndecorated(false);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 782, 492);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.setIcon(null);
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		mnFile.add(mntmExit);

		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);

		JMenuItem mntmAbout = new JMenuItem("About");
		mntmAbout.setIcon(null);
		mntmAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					AboutWindow frame = new AboutWindow();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		mnHelp.add(mntmAbout);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 33, 86));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		final JTextArea textArea = new JTextArea();
		textArea.setOpaque(false);
		textArea.setForeground(Color.WHITE);
		textArea.setFont(new Font("Arial Black", Font.BOLD, 15));
		textArea.setEditable(false);
		textArea.setBounds(55, 158, 173, 310);
		contentPane.add(textArea);

		final JTextArea textArea_2 = new JTextArea();
		textArea_2.setOpaque(false);
		textArea_2.setForeground(Color.WHITE);
		textArea_2.setFont(new Font("Arial Black", Font.BOLD, 15));
		textArea_2.setEditable(false);
		textArea_2.setBounds(313, 158, 51, 310);
		contentPane.add(textArea_2);

		final JTextArea textArea_1 = new JTextArea();
		textArea_1.setOpaque(false);
		textArea_1.setForeground(Color.YELLOW);
		textArea_1.setFont(new Font("Arial Black", Font.BOLD, 15));
		textArea_1.setEditable(false);
		textArea_1.setBounds(244, 158, 57, 310);
		contentPane.add(textArea_1);

		JLabel lblTulospalvelu = new JLabel("SM-LIIGAN TULOSPALVELU.");
		lblTulospalvelu.setFont(new Font("Arial Black",
				Font.BOLD | Font.ITALIC, 29));
		lblTulospalvelu.setForeground(Color.YELLOW);
		lblTulospalvelu.setBounds(38, 11, 530, 56);
		contentPane.add(lblTulospalvelu);

		final JLabel lblDate = new JLabel("");
		lblDate.setForeground(new Color(255, 255, 255));
		lblDate.setBounds(48, 90, 269, 14);
		contentPane.add(lblDate);

		final JTextArea twitterTextArea = new JTextArea();
		twitterTextArea.setLineWrap(true);
		twitterTextArea.setEditable(false);
		twitterTextArea.setWrapStyleWord(true);
		twitterTextArea.setForeground(new Color(0, 0, 102));
		twitterTextArea.setFont(new Font("Tahoma", Font.BOLD, 12));
		twitterTextArea.setBounds(440, 175, 315, 237);
		contentPane.add(twitterTextArea);

		JScrollPane scrollPane = new JScrollPane(twitterTextArea);
		scrollPane.setBounds(440, 175, 315, 237);
		contentPane.add(scrollPane);

		JLabel lblTwitterespooblues = new JLabel("TWITTER FEED #EspooBlues");
		lblTwitterespooblues.setForeground(Color.YELLOW);
		lblTwitterespooblues.setFont(new Font("Arial Black", Font.BOLD, 12));
		lblTwitterespooblues.setBounds(440, 149, 228, 14);
		contentPane.add(lblTwitterespooblues);

		JLabel lblNewLabel = new JLabel(logo);
		lblNewLabel.setToolTipText("blues.fi");
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					String URL = "http://www.blues.fi";
					java.awt.Desktop.getDesktop().browse(
							java.net.URI.create(URL));
				} catch (Exception e) {

				}
			}
		});
		lblNewLabel.setBounds(629, 11, 115, 115);
		contentPane.add(lblNewLabel);

		final JLabel lblSeur = new JLabel("");
		lblSeur.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		lblSeur.setForeground(Color.WHITE);
		lblSeur.setBounds(41, 205, 295, 56);
		contentPane.add(lblSeur);

		/*** PELIT ***/

		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {

				try {
					while (true) {
						Document doc = Jsoup.connect("http://sm-liiga.fi")
								.get();

						Elements getTeam = doc.select("td.teams");
						Elements getScore = doc.select("td.score");
						Elements getTime = doc.select("td.time");
						if (isGames) {
							Element getDate = doc.select("span.date").first();
							lblDate.setText("Seuraavat ottelut "
									+ getDate.text());
						} else {
							lblDate.setText("TŠnŠŠn ei pelata yhtŠkŠŠn ottelua.");

						}
						textArea.setText("");
						textArea_1.setText("");
						textArea_2.setText("");

						for (Element teams : getTeam) {
							textArea.append(teams.text() + "\n\n");
						}
						for (Element scores : getScore) {
							textArea_1.append(scores.text() + "\n\n");
						}

						for (Element times : getTime) {
							textArea_2.append(times.text() + "\n\n");
						}
						if (!getTeam.isEmpty()) {
							boolean isGames = true;
							lblSeur.setVisible(false);
							lblDate.setText("TŠnŠŠn pelataan seuraavat ottelut:");
						} else {
							lblSeur.setVisible(true);
							Elements getDate = (doc.select("div.matches")
									.select("span"));
							for (Element setDate : getDate) {
								lblSeur.setText("Seuraavat ottelut "
										+ setDate.text());
							}
							boolean isGames = false;

						}
						try {
							Thread.sleep(10000);
							System.out.print("view did update.");

						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}

				} catch (IOException e) {
					System.out.println("IOExcepzione");
				}
			}
		});

		t1.start();

		/** TWITTER **/

		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setOAuthConsumerKey("WiPpAfpZraW4xDZgqsoDw");
		cb.setOAuthConsumerSecret("ZuhYPmSJqFrL3hFLxyJ6f6ckYBvgn2sOncGUKy8");
		cb.setOAuthAccessToken("84278070-ikKHqhlRHKkayKQfocTolKbgGjp7Vgi2yErqzSFhv");
		cb.setOAuthAccessTokenSecret("VkMEpoU87e91I6n17LiY1LtTUNGDxgApXcO9WFY");
		final Twitter twitter = new TwitterFactory(cb.build()).getInstance();

		Thread t2 = new Thread(new Runnable() {
			public void run() {

				while (true) {

					try {
						Query query = new Query("#EspooBlues");
						QueryResult result;

						result = twitter.search(query);
						List<Status> tweets = result.getTweets();
						twitterTextArea.setText("");
						for (Status tweet : tweets) {
							twitterTextArea.append(" " + tweet.getCreatedAt()
									+ "\n@" + tweet.getUser().getScreenName()
									+ " - " + " " + tweet.getText() + "\n\n");
						}

					} catch (TwitterException te) {
						te.printStackTrace();
						System.out.println("Failed to search tweets: "
								+ te.getMessage());
					}
					try {
						Thread.sleep(10000);
					} catch (InterruptedException e) {

						e.printStackTrace();
					}
				}
			}

		});

		t2.start();

	}
}
