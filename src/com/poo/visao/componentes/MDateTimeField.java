package com.poo.visao.componentes;

import java.awt.Color;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.border.LineBorder;

public class MDateTimeField extends MTextField {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final SimpleDateFormat dtf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

	public MDateTimeField() throws ParseException {
		super("##/##/#### ##:##");
		setBorder(new LineBorder(new Color(171, 173, 179)));
	}

	public void setDate(Date dta) {
		if(dta == null) {
			setText("");
			return;
		}
		String txt = dtf.format(dta);
		setText(txt);
	}

	public Date getDate() {
		try {
			if (getText() != null && !getText().isEmpty())
				return dtf.parse(getText());
		} catch (ParseException e) {
			System.err.println(e.getMessage());
		}
		return null;
	}

}