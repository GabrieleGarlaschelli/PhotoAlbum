package UserInterface.Views;

import ManageImages.*;
import UserInterface.ActionListener.*;
import java.awt.*;
import javax.swing.*;

public class AlbumFrame extends JFrame {

	public static final int WIDTH = 1000;
	public static final int HEIGH = 550;

	private JPanel album_panel;

	public AlbumFrame(String name, Album myAlbum) {
		super(name);                     
		this.setSize(this.WIDTH + 10, this.HEIGH);
		this.setLocation(100, 100);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// menu
		JMenuBar menuBar = new JMenuBar();;
		JMenu menu_cat = new JMenu("Categorie");
		JMenu menu_general = new JMenu("Generale");

		JMenuItem save = new JMenuItem("Salva");
		save.addActionListener(new SaveOnFileListener(myAlbum, this));
		JMenuItem reset = new JMenuItem("Resetta album");
		reset.addActionListener(new ResetAlbumListener(myAlbum, this));
		menu_general.add(save);
		menu_general.add(reset);

		JMenuItem menuItemCreate = new JMenuItem("Crea");
		menu_cat.add(menuItemCreate);

		menuBar.add(menu_general);
		menuBar.add(menu_cat);
		
		this.setJMenuBar(menuBar);

		// category list
		album_panel = new JPanel();
		album_panel.setLayout(new BoxLayout(album_panel, BoxLayout.PAGE_AXIS));

		int ids[] = myAlbum.getCategoriesId();

		for(int i=0; i<ids.length; i++) {
			JSeparator sep = new JSeparator(JSeparator.HORIZONTAL);
			album_panel.add(sep);
			CategoryPanel current_panel = new CategoryPanel(ids[i], myAlbum, sep, this);
			// current_panel.setBorder(BorderFactory.createLineBorder(Color.black));
			album_panel.add(current_panel);
		}

		menuItemCreate.addActionListener(new CreateCategoryListener(myAlbum, album_panel, this)); // i need to do this here cause i need the cat list container

		// scroll
		JScrollPane scrollFrame = new JScrollPane(album_panel);
		scrollFrame.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    scrollFrame.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollFrame.setBounds(0, 0, this.WIDTH + 30, this.HEIGH - 30);
		album_panel.setAutoscrolls(true);
		JPanel content_pane = new JPanel(new BorderLayout());
		content_pane.add(scrollFrame);

		this.setContentPane(content_pane);
		this.setVisible(true);
	}

	public void removeCategoryPanel(CategoryPanel cat_panel, JSeparator sep) {
		// System.out.println((Object)cat_panel);
		album_panel.remove(sep);
		album_panel.remove(cat_panel);
		album_panel.revalidate();
		album_panel.repaint();
	}

}