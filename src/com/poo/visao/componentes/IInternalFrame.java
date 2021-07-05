package com.poo.visao.componentes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public abstract class IInternalFrame extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panelRodape;

	public abstract String getInternalTitle();

	protected abstract JPanel getPanelForm() throws Exception;

	protected abstract void addEvents();
	
	public abstract void limpar();
	
	
	
	/**
	 * Create the frame.
	 */
	public IInternalFrame() {
		setRootPaneCheckingEnabled(false);
		setMaximizable(true);
		setResizable(true);

		setBackground(new java.awt.Color(237, 242, 249));
		setBorder(null);
		setTitle(getInternalTitle());
		setName(getInternalTitle());

		pack();
		try {
			criaPainel();
			addEvents();

		} catch (Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(this, "Erro ao abrir a tela: " + ex.getMessage());

		}
	}
	
	public void dadosDefault() {
		// TODO Implementar nas classes necessárias
	}

	private void criaPainel() throws Exception {
		JPanel panelNome = new JPanel();
		panelNome.setBackground(new Color(2, 100, 167));

		JLabel lblNewLabel = new JLabel(getInternalTitle());
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setAlignmentX(0.5f);
		panelNome.add(lblNewLabel);
		getContentPane().setLayout(new BorderLayout(0, 0));

		panelRodape = new JPanel(new FlowLayout());
		panelRodape.setBorder(new EmptyBorder(10, 10, 10, 10));
		
		getContentPane().add(getPanelForm());
		getContentPane().add(panelNome, BorderLayout.NORTH);

		
		panelRodape.setBackground(new Color(227, 232, 239));
		getContentPane().add(panelRodape, BorderLayout.PAGE_END);
		
	}

	protected void addRodaPe(JButton btn) {
		panelRodape.add(btn);
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL); 
		panelRodape.add(separator);
	}

}
