package com.poo.visao;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.poo.visao.componentes.IInternalFrame;

public class FrameEmpity extends IInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String getInternalTitle() {
		return "Empity";
	}

	@Override
	protected JPanel getPanelForm() throws Exception {
		JPanel pn = new JPanel();
		pn.add(new JLabel("Empity"), BorderLayout.NORTH);
		return pn;
	}

	@Override
	protected void addEvents() {
	}

	@Override
	public void limpar() {
	}
}
