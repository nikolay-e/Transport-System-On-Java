package com.njerrry.transportsystem.view;

import javax.swing.JFrame;

class MyFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private static final int DEFAULT_FRAME_WIDTH = 500;
	private static final int DEFAULT_FRAME_HEIGHT = 500;

	public MyFrame() {
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(DEFAULT_FRAME_WIDTH, DEFAULT_FRAME_HEIGHT);
		// setExtendedState(JFrame.MAXIMIZED_BOTH);
		setVisible(true);
	}
}
