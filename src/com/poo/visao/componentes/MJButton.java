package com.poo.visao.componentes;

import java.awt.Color;

import javax.swing.JButton;

public class MJButton extends JButton{
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		public MJButton(String txt) {
			super(txt);
			this.setForeground(Color.WHITE);
			this.setBorderPainted(false);
			this.setBackground(new Color(2, 100, 167));
		}
	}