package com.poo.visao.componentes;

import java.awt.Color;
import java.text.ParseException;

import javax.swing.JFormattedTextField;
import javax.swing.border.LineBorder;

public class MTextField extends JFormattedTextField {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MTextField() {
		super();
		setBorder(new LineBorder(new Color(171, 173, 179)));
	}

	public MTextField(String formater) throws ParseException {
		super(new javax.swing.text.MaskFormatter(formater));
		setBorder(new LineBorder(new Color(171, 173, 179)));
	}

	public String getApenasFigitos() {
		String txt = super.getText();
		if (txt != null)
			txt = txt.replaceAll("\\D+", "");
		return txt;
	}
}