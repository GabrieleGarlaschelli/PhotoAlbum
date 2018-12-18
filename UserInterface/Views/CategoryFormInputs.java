package UserInterface.Views;

import ManageImages.*;
import UserInterface.ActionListener.*;
import java.awt.*;
import javax.swing.*;

public class CategoryFormInputs extends JFrame {

	private JButton save_button;
	private JTextField name_texfield;
	private JCheckBox di_check;
	private JLabel label_on_main_view; // only for update
	private Category current_cat; // only for update
	private Album album; 
	private JPanel album_panel; // only for create
	private AlbumFrame album_frame; // only for create

	public CategoryFormInputs(Category current_cat, Album album, JLabel label_on_main_view, String frame_name, JPanel album_panel, AlbumFrame album_frame) {
		super(frame_name);

		this.current_cat = current_cat; 
		this.album = album;
		this.label_on_main_view = label_on_main_view;
		this.album_panel = album_panel;
		this.album_frame = album_frame;

		// Category current_cat = main_view_listener.getAlbum().findCategoryById(main_view_listener.getCategoryId());
		this.setLocation(150, 150);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JPanel form_panel = new JPanel();
		form_panel.setLayout(new BoxLayout(form_panel, BoxLayout.PAGE_AXIS));

		JPanel align_panel = new JPanel(new BorderLayout());

		// name field
		JPanel name_panel = new JPanel(new FlowLayout());
		JLabel name_label = new JLabel("Nome: ");

		String precompile = "";
		if(current_cat != null) {
			precompile = current_cat.getName();
		}
		name_texfield = new JTextField(precompile, 30);
		name_panel.add(name_label);
		name_panel.add(name_texfield);

		// want double
		Boolean want_double = false;
		if(current_cat != null) {
			want_double = current_cat.acceptDoubles();
		}
		JPanel double_image_check_panel = new JPanel(new FlowLayout());
		di_check = new JCheckBox("Accetta immagini doppie", want_double);
		double_image_check_panel.add(di_check);

		// save button
		JPanel save_panel = new JPanel(new FlowLayout());
		save_button = new JButton("Salva");
		save_panel.add(save_button);

		form_panel.add(name_panel);
		form_panel.add(double_image_check_panel);
		form_panel.add(save_panel);
		align_panel.add(form_panel, BorderLayout.WEST);

		this.add(align_panel);
		this.pack();
		this.setVisible(true);
	}

	public void setButtonListenerToEdit() {
		save_button.addActionListener(new UpdateCategoryListener(this, current_cat, album, label_on_main_view, name_texfield, di_check));
	}

	public void setButtonListenerToNew() {
		save_button.addActionListener(new NewCategoryListener(this, album_panel, album, name_texfield, di_check, album_frame));
	}
}