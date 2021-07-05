package com.poo.visao.componentes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.text.ParseException;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;

import com.poo.modelo.Atendimento;

/**
 *
 */
public abstract class PanelMestreEscraco extends IInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panelForm;

	private JTable internacoesTable;
	private MTextField cpfTextField;
	private MTextField nomeTextField;
	private JTextArea queixaTextArea;
	private JTextArea obsTextField;

	protected abstract void criaBotoes();

	protected abstract Atendimento linhaSelecionada(int selectedRow);

	protected abstract AbstractTableModel getTableModel();

	@Override
	protected JPanel getPanelForm() throws Exception {

		panelForm = new MPanelCenter();
		panelForm.setLayout(new BorderLayout());

		AbstractTableModel tbModel = getTableModel();
		internacoesTable = new JTable(tbModel);
		JScrollPane scrollPane = new JScrollPane(internacoesTable);
		internacoesTable.setFillsViewportHeight(true);

		panelForm.add(scrollPane, BorderLayout.CENTER);

		panelForm.add(getPanelAtendimento(), BorderLayout.SOUTH);

		criaBotoes();

		return panelForm;
	}

	private MPanelCenter getPanelAtendimento() throws ParseException {
		String colunas = "[grow][grow][][grow][grow]";
		String linhas = "[grow][23px][3px][40px][3px][23px]";
		MPanelCenter panelPacient = new MPanelCenter(colunas, linhas);

		cpfTextField = new MTextField("###.###.###-##");
		nomeTextField = new MTextField();
		queixaTextArea = new JTextArea();
		obsTextField = new JTextArea();

		nomeTextField.setColumns(10);
		queixaTextArea.setRows(3);
		queixaTextArea.setBorder(new LineBorder(new Color(171, 173, 179)));
		obsTextField.setBorder(new LineBorder(new Color(171, 173, 179)));

		cpfTextField.setEditable(false);
		nomeTextField.setEditable(false);
		queixaTextArea.setEditable(false);
		obsTextField.setEditable(false);
		obsTextField.setEditable(false);
		queixaTextArea.setEditable(false);
		queixaTextArea.setColumns(50);

		panelPacient.add(new JLabel("CPF"), "flowx,cell 1 1,growy");
		panelPacient.add(new JLabel("Nome"), "flowx,cell 3 1,growy");
		panelPacient.add(cpfTextField, "flowx,cell 1 1,grow");
		panelPacient.add(nomeTextField, "cell 3 1,grow");

		// linha 2
		panelPacient.add(new JLabel("Queixa"), "flowx,cell 1 2 3 1");
		panelPacient.add(new JScrollPane(queixaTextArea), "cell 1 3 3 1,grow");
		// linha 3
		panelPacient.add(new JLabel("Observação"), "flowx,cell 1 4 3 1");
		panelPacient.add(obsTextField, "cell 1 5 3 1,grow");
		_addEvents();
		return panelPacient;
	}

	@Override
	protected void addEvents() {
		_addEvents();
	}

	private void _addEvents() {

		internacoesTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				if (internacoesTable.getSelectedRow() < 0)
					return;

				Atendimento row = linhaSelecionada(internacoesTable.getSelectedRow());
				if (row == null)
					return;
				cpfTextField.setText(row.getCpf());
				nomeTextField.setText(row.getNome());
				queixaTextArea.setText(row.getQueixa());
				obsTextField.setText(row.getObservacao());

			}
		});
	}
	
	public void limpar() {
		
		internacoesTable.clearSelection();
		cpfTextField.setText("");
		nomeTextField.setText("");
		queixaTextArea.setText("");
		
		dadosDefault();
	}

}
