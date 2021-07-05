package com.poo.visao.componentes;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;

import javax.swing.JButton;

public class MenuBarItem extends JButton {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MenuBarItem(String txt) {
		this(txt, false);
	}
	
	public MenuBarItem(String txt, boolean topo) {
		super(txt);

		this.setFocusPainted(false);
		this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		this.setBorderPainted(false);
		this.setAlignmentY(Component.TOP_ALIGNMENT);
		this.setPreferredSize(new Dimension(154, 47));
		this.setBorder(null);
		this.setForeground(Color.WHITE);
		this.setBackground(new java.awt.Color(4, 158, 218));


	}
}
