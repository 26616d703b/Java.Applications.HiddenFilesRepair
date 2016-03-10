package com.ampersand.hfr;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;

import com.ampersand.lcu.gui.color.ColorPalette;
import com.ampersand.lcu.gui.component.button.FlatButton;
import com.ampersand.lcu.runtime.RuntimeUtils;

public class HiddenFilesRepair extends JFrame {

	/**
	 *
	 */
	private static final long serialVersionUID = 8890204574742167498L;

	/*
	 * Methods:
	 */

	// CONSTRUCTOR

	public HiddenFilesRepair() {

		try {

			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (final ClassNotFoundException e) {

			e.printStackTrace();
		} catch (final InstantiationException e) {

			e.printStackTrace();
		} catch (final IllegalAccessException e) {

			e.printStackTrace();
		} catch (final UnsupportedLookAndFeelException e) {

			e.printStackTrace();
		}

		// Window Settings
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setIconImage(new ImageIcon(getClass().getResource("res/icons/application.png")).getImage());
		setTitle("HiddenFilesRepair");
		setSize(320, 160);
		setResizable(false);
		setLocationRelativeTo(null);

		init();
	}

	// INISIALIZATIONS

	public void init() {

		final FlatButton fix_button = new FlatButton(new ImageIcon(getClass().getResource("res/icons/show.png")),
				ColorPalette.WHITE);
		fix_button.addActionListener(event -> {

			final JFileChooser file_chooser = new JFileChooser();
			file_chooser.setApproveButtonText("Sélectionner");
			file_chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

			final int state = file_chooser.showOpenDialog(null);

			if (state == JOptionPane.OK_OPTION) {

				final String absolute_path = file_chooser.getSelectedFile().getAbsolutePath();
				absolute_path.replaceAll(" ", "?");

				if (file_chooser.getSelectedFile().isFile()) {

					RuntimeUtils.launch("Attrib \"" + absolute_path + "\" -S -A -R -H /S /D");
				} else {

					RuntimeUtils.launch("Attrib \"" + absolute_path + "\"\\*.* -S -A -R -H /S /D");
				}

				JOptionPane.showMessageDialog(null, "Les fichiers séléctionnés ont été réstaurés.",
						"Restauration terminée", JOptionPane.INFORMATION_MESSAGE);
			}
		});

		add(fix_button);
	}
}
