package com.poo.visao;

import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import com.poo.controle.AtendimentoControle;
import com.poo.controle.ControleExcption;
import com.poo.modelo.Atendimento;
import com.poo.modelo.EnumAlaHospital;
import com.poo.modelo.Paciente;
import com.poo.visao.componentes.IInternalFrame;
import com.poo.visao.componentes.MDateTimeField;
import com.poo.visao.componentes.MJButton;
import com.poo.visao.componentes.MPanelCenter;
import com.poo.visao.componentes.MTextField;

public class GeraAtendimento extends IInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AtendimentoControle atendimentoControle = new AtendimentoControle();
	private MPanelCenter panelForm;

	/*
	 * Para o item de menu “Gerenciar paciente”, a tela deve apresentar um campo
	 * CPF, o qual será utilizado para verificar se o paciente já possui cadastro
	 * (ler arquivo “pacientes.txt”).
	 */
	private MTextField cpfTextField;
	// nome
	private JTextField nomeTextField;

	// i) data e horário da entrada na emergência,
	private MDateTimeField dtaEntrada;

	// ii) descrição do problema de saúde (queixa),
	private JTextArea queixaTextArea;
	// iii) prioridade de atendimento numa escala de 1 a 5,
	private JComboBox<Integer> prioridadeCb;
	// iv) ala de internação
	private JComboBox<Object> alaCb;
	// v) observações. Devem
	private JTextField obsTextField;
	private MJButton limparBtn;
	private MJButton cadastrarBtn;

	@Override
	public String getInternalTitle() {
		return "Gerar Atendimento";
	}

	@Override
	protected JPanel getPanelForm() throws Exception {

		String colunas = "[grow][grow][][grow][grow]";
		String linhas = "[grow][23px][5px,fill][23px][5px][60px][5px][23px][5px][5px][35px][grow,fill]";
		panelForm = new MPanelCenter(colunas, linhas);

		cpfTextField = new MTextField("###.###.###-##");
		nomeTextField = new MTextField();
		dtaEntrada = new MDateTimeField();
		queixaTextArea = new JTextArea();
		prioridadeCb = new JComboBox<Integer>();
		alaCb = new JComboBox<Object>();
		obsTextField = new MTextField();

		nomeTextField.setColumns(10);
		queixaTextArea.setColumns(50);
		queixaTextArea.setRows(3);
		queixaTextArea.setBorder(new LineBorder(new Color(171, 173, 179)));
		prioridadeCb.setModel(new DefaultComboBoxModel<Integer>(new Integer[] { 1, 2, 3, 4, 5 }));
		nomeTextField.setEditable(false);

		ArrayList<Object> alas = new ArrayList<Object>(Arrays.asList(EnumAlaHospital.listar()));
		alas.add(0, "Selecione");
		alaCb.setModel(new DefaultComboBoxModel<Object>(alas.toArray()));

		// linha 1
		panelForm.add(new JLabel("CPF"), "flowx,cell 1 1,growy");
		panelForm.add(new JLabel("Nome"), "flowx,cell 3 1,growy");
		panelForm.add(cpfTextField, "flowx,cell 1 1,grow");
		panelForm.add(nomeTextField, "cell 3 1,grow");

		// linha 2
		panelForm.add(new JLabel("Entrada"), "flowx,cell 1 3,growy");
		panelForm.add(new JLabel("Observação"), "flowx,cell 3 3,growy");
		panelForm.add(dtaEntrada, "cell 1 3,grow");
		panelForm.add(obsTextField, "cell 3 3,grow");

		// linha 3
		panelForm.add(new JLabel("Queixa"), "flowx,cell 1 5 3 1");
		panelForm.add(new JScrollPane(queixaTextArea), "cell 1 5 3 1,grow");

		// linha 3
		panelForm.add(new JLabel("Prioridade"), "flowx,cell 1 7,growy");
		panelForm.add(new JLabel("Ala"), "flowx,cell 3 7,growy");
		panelForm.add(prioridadeCb, "flowx,cell 1 7,grow");
		panelForm.add(alaCb, "cell 3 7,grow");

		limparBtn = new MJButton("Limpar Tela");
		cadastrarBtn = new MJButton("Gerar atendimento");

		addRodaPe(cadastrarBtn);
		addRodaPe(limparBtn);

		return panelForm;
	}

	@Override
	protected void addEvents() {
		cpfTextField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {

				buscar();
			}
		});

		cadastrarBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				gravar();
			}
		});

		limparBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				limpar();
			}
		});

	}

	private void buscar() {
		nomeTextField.setEditable(false);
		String cpf = cpfTextField.getApenasFigitos();
		try {
			if(cpf==null || cpf.isBlank())
				return;
			
			cadastrarBtn.setEnabled(false);
			Paciente paciente = atendimentoControle.buscarPaciente(cpf);

			if (paciente == null) {
				JOptionPane.showMessageDialog(this, "Nenhum paciente encontrado para o CPF: " + cpf);
				limpar();
				return;
			}
			cadastrarBtn.setEnabled(true);
			nomeTextField.setText(paciente.getNome());
			panelForm.repaint();
			dtaEntrada.setDate(new Date());
			dtaEntrada.setEditable(false);
			JOptionPane.showMessageDialog(GeraAtendimento.this, "Paciente: " + paciente.getNome());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(GeraAtendimento.this, e.getMessage());
		} finally {
			nomeTextField.setEditable(true);
		}

	}

	private void gravar() {

		Atendimento atendimento = new Atendimento();
		atendimento.setCpf(cpfTextField.getApenasFigitos());
		atendimento.setNome(nomeTextField.getText());
		atendimento.setDataEntrada(dtaEntrada.getDate());
		atendimento.setQueixa(queixaTextArea.getText());
		atendimento.setPrioridade((int) prioridadeCb.getSelectedItem());
		if (alaCb.getSelectedIndex() > 0)
			atendimento.setAla((EnumAlaHospital) alaCb.getSelectedItem());
		atendimento.setObservacao(obsTextField.getText());
		cadastrarBtn.setEnabled(false);

		try {
			String msg = atendimentoControle.gravar(atendimento);
			JOptionPane.showMessageDialog(GeraAtendimento.this, msg);
			limpar();
		} catch (ControleExcption e) {
			JOptionPane.showMessageDialog(GeraAtendimento.this, e.getMessage());
		} finally {
			cadastrarBtn.setEnabled(true);
		}
	}

	public void limpar() {
		nomeTextField.setText("");
		cpfTextField.setText("");
		dtaEntrada.setDate(null);
		queixaTextArea.setText("");
	}

}
