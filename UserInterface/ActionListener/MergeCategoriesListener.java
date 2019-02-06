package UserInterface.ActionListener;

import UserInterface.Views.*;
import ManageImages.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MergeCategoriesListener 
							implements ActionListener {
	private Album album;
	private AlbumFrame album_frame;

	public MergeCategoriesListener(Album album, AlbumFrame album_frame) {
		super();
		this.album = album;
		this.album_frame = album_frame;
	}

	public void actionPerformed(ActionEvent event) {
		JFrame frame = new JFrame("Unisci Categorie");
		frame.setSize(400,150);
		frame.setLocation(300, 300);

		JPanel main_panel = new JPanel();
		main_panel.setLayout(new BoxLayout(main_panel, BoxLayout.PAGE_AXIS));

		String[] categories = album.getCategoriesName();

		JPanel source_panel = new JPanel(new FlowLayout());
		JLabel lable_source = new JLabel("Categoria Sorgente");
		JComboBox<String> combo_source = new JComboBox<String>(categories);
		source_panel.add(lable_source);
		source_panel.add(combo_source);

		JPanel destionation_panel = new JPanel(new FlowLayout());
		JLabel lable_dest = new JLabel("Categoria Destinazione");
		JComboBox<String> combo_destination = new JComboBox<String>(categories);
		destionation_panel.add(lable_dest);
		destionation_panel.add(combo_destination);

		JButton make = new JButton("Unisci");

		make.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				String source = (String)combo_source.getSelectedItem();
				String destination = (String)combo_destination.getSelectedItem();
				Boolean success = album.mergeCategories(source, destination);
				if(!success) {
					JOptionPane.showMessageDialog(frame, "Operazione Fallita", "error", JOptionPane.ERROR_MESSAGE);
					return;
				}	

				Category source_cat = album.findCategoryByName(source);
				source_cat.removeOnView(album_frame);
				album.removeCategory(source_cat.getId());
				Category dest_cat = album.findCategoryByName(destination);
				dest_cat.refreshPanel();
			}
		});

		main_panel.add(source_panel);
		main_panel.add(destionation_panel);
		main_panel.add(make);
		frame.add(main_panel);
		frame.setVisible(true);
	} 
}