package com.poo.visao;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import com.poo.controle.ConsultaControle;
import com.poo.controle.ControleExcption;
import com.poo.modelo.Atendimento;
import com.poo.modelo.Consulta;
import com.poo.modelo.EnumAlaHospital;
import com.poo.visao.componentes.IInternalFrame;
import com.poo.visao.componentes.MJButton;
import com.poo.visao.componentes.MPanelCenter;
import com.poo.visao.componentes.MTextField;

/**
 *
 */
public class GeraConsulta extends IInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ConsultaControle cControle = new ConsultaControle();
	private MPanelCenter panelForm;

	/*
	 * Para o item de menu “Gerenciar paciente”, a tela deve apresentar um campo
	 * CPF, o qual será utilizado para verificar se o paciente já possui cadastro
	 * (ler arquivo “pacientes.txt”).
	 */
	private MTextField cpfTextField;
//	nome 
	private JTextField nomeTextField;

	// i) a descrição do histórico de saúde (queixa)
	private JTextArea queixaTextArea;
	// i) avaliação do médico,
	private JTextArea avaliacaoTextArea;
	// iii) medicação prescrita
	private JTextArea prescricaoTextArea;
	// iv)ala para internação e
	private JComboBox<EnumAlaHospital> alaCb;
	// v)observações.
	private JTextArea obsTextField;

	// botões “Finalizar Consulta”,
	private MJButton finalizarConsultaBtn;
	// “Encaminhar para Internação” e,
	private MJButton internarBtn;;
	// “Limpar Tela”.
	private MJButton limparBtn;

	@Override
	public String getInternalTitle() {
		return "Gerar Consulta";
	}

	@Override
	protected JPanel getPanelForm() throws Exception {

		String colunas = "[grow][grow][][grow][grow]";
		String linhas = "[grow][23px][5px,fill][50px][5px][50px][5px][50px][5px][23px][5px][50px][5px][grow,fill]";
		panelForm = new MPanelCenter(colunas, linhas);

		cpfTextField = new MTextField("###.###.###-##");
		nomeTextField = new MTextField();
		queixaTextArea = new JTextArea();
		avaliacaoTextArea = new JTextArea();
		prescricaoTextArea = new JTextArea();
		alaCb = new JComboBox<EnumAlaHospital>();
		obsTextField = new JTextArea();

		nomeTextField.setColumns(10);
		queixaTextArea.setColumns(50);
		queixaTextArea.setRows(3);
		queixaTextArea.setBorder(new LineBorder(new Color(171, 173, 179)));
		avaliacaoTextArea.setBorder(new LineBorder(new Color(171, 173, 179)));
		prescricaoTextArea.setBorder(new LineBorder(new Color(171, 173, 179)));
		obsTextField.setBorder(new LineBorder(new Color(171, 173, 179)));
		alaCb.setModel(new DefaultComboBoxModel<EnumAlaHospital>(EnumAlaHospital.listar()));

		cpfTextField.setEditable(false);
		nomeTextField.setEditable(false);
		// linha 1
		panelForm.add(new JLabel("CPF"), "flowx,cell 1 1,growy");
		panelForm.add(new JLabel("Nome"), "flowx,cell 3 1,growy");
		panelForm.add(cpfTextField, "flowx,cell 1 1,grow");
		panelForm.add(nomeTextField, "cell 3 1,grow");

		// linha 2
		panelForm.add(new JLabel("Queixa"), "flowx,cell 1 2 3 1");
		panelForm.add(new JScrollPane(queixaTextArea), "cell 1 3 3 1,grow");
		// linha 3
		panelForm.add(new JLabel("Avaliação"), "flowx,cell 1 4 3 1");
		panelForm.add(new JScrollPane(avaliacaoTextArea), "cell 1 5 3 1,grow");
		// linha 4
		panelForm.add(new JLabel("Medicação prescrita"), "flowx,cell 1 6 3 1");
		panelForm.add(new JScrollPane(prescricaoTextArea), "cell 1 7 3 1,grow");

		// linha 3
		panelForm.add(new JLabel("Ala"), "flowx,cell 1 9 3 1,growy");
		panelForm.add(alaCb, "cell 1 9 3 ,grow");

		// linha 3
		panelForm.add(new JLabel("Observação"), "flowx,cell 1 10 3 1,growy");
		panelForm.add(new JScrollPane(obsTextField), "cell 1 11 3 1,grow");

		limparBtn = new MJButton("Limpar Tela");
		finalizarConsultaBtn = new MJButton("Finalizar Consulta");
		internarBtn = new MJButton("Encaminhar Internação");

		addRodaPe(finalizarConsultaBtn);
		addRodaPe(internarBtn);
		addRodaPe(limparBtn);

		return panelForm;
	}

	@Override
	protected void addEvents() {

		internarBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				internar();
			}
		});

		limparBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				limpar();
			}
		});

		finalizarConsultaBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (finalizarConsultaBtn.isEnabled())
					finalizarConsulta();
			}
		});

	}

	private Consulta getConsultaBean() {
		Consulta consulta = new Consulta();
		consulta.setNome(nomeTextField.getText());
		consulta.setCpf(cpfTextField.getApenasFigitos());

		consulta.setQueixa(queixaTextArea.getText());
		consulta.setAvaliacao(avaliacaoTextArea.getText());
		consulta.setPrescricao(prescricaoTextArea.getText());
		consulta.setAla((EnumAlaHospital) alaCb.getSelectedItem());
		consulta.setObservacao(obsTextField.getText());
		
		return consulta;
	}

	private void internar() {

		Consulta consulta = getConsultaBean();
		try {
			String msg = cControle.internar(consulta);
			JOptionPane.showMessageDialog(this, msg);
			limpar();
			dadosDefault();
		} catch (ControleExcption e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, e.getMessage());
		}  

	}

	private void finalizarConsulta() {

		Consulta consulta = getConsultaBean();
		try {
			cControle.finalizaConsulta(consulta);
			JOptionPane.showMessageDialog(this, "Consulta finalizada com sucesso");
			limpar();
			dadosDefault();
		} catch (ControleExcption e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}  

	}

	public void limpar() {
		nomeTextField.setText("");
		cpfTextField.setText("");
		queixaTextArea.setText("");
		avaliacaoTextArea.setText("");
		obsTextField.setText("");
		alaCb.setSelectedIndex(-1);
		dadosDefault();
	}

	@Override
	public void dadosDefault() {
		try {
			if (cControle == null)
				cControle = new ConsultaControle();
			Atendimento atendimento = cControle.proximoAtendimento();

			if (atendimento == null) {
				finalizarConsultaBtn.setEnabled(false);
				return;
			}
			nomeTextField.setText(atendimento.getNome());
			cpfTextField.setText(atendimento.getCpf());
			queixaTextArea.setText(atendimento.getQueixa());
			alaCb.setSelectedItem(atendimento.getAla());

		} catch (ControleExcption e) {
			JOptionPane.showMessageDialog(GeraConsulta.this, "Erro ao buscar proximo paciente: " + e.getMessage());
		}  

	}

}
