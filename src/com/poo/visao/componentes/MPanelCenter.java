package com.poo.visao.componentes;

import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

public class MPanelCenter extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MPanelCenter() {
		super();
		setBackground(new java.awt.Color(237, 242, 249));
	}

	public MPanelCenter(String colunas, String linhas) {
		this();
		setLayout(new MigLayout("", colunas, linhas));
	}

}
