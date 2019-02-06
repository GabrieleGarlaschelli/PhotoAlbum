package UserInterface.Component;

import ManageImages.*;
import UserInterface.ActionListener.*;
import Utils.*;
import Exceptions.*;
import UserInterface.Component.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class JMenuItemRemove extends JMenuItem {
	public JMenuItemRemove(String message) {
		super(message);
	}

	public void removeIstantanely() {
		this.fireActionPerformed(new ActionEvent(this, 0, "removed_istantanely"));
	}
}