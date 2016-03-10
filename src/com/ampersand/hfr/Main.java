package com.ampersand.hfr;

import javax.swing.SwingUtilities;

public class Main {

	public static void main(String[] args) {

		SwingUtilities.invokeLater(() -> {

			final HiddenFilesRepair hfr = new HiddenFilesRepair();
			hfr.setVisible(true);
		});
	}
}
