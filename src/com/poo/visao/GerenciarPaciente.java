package com.poo.visao;

import java.awt.Dimension;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingWorker;

import com.poo.controle.ControleExcption;
import com.poo.controle.PacienteControle;
import com.poo.modelo.Paciente;
import com.poo.modelo.TipoSang;
import com.poo.visao.componentes.IInternalFrame;
import com.poo.visao.componentes.MDateField;
import com.poo.visao.componentes.MJButton;
import com.poo.visao.componentes.MTextField;

import net.miginfocom.swing.MigLayout;

public class GerenciarPaciente extends IInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MTextField nomeTextField;
	private MTextField nomePaiTextField;
	private MTextField EnderecoTextField;
	private MTextField nomeMaeTextField;
	private PacienteControle controle = new PacienteControle();
	private MTextField cpfTextField;
	private MDateField nascimentoTextField;
	private JButton limparBtn;
	private JButton cadastrarBtn;
	private JComboBox<TipoSang> tipoSanguineoComboBox;
	private JPanel panelForm;

	@Override
	public String getInternalTitle() {
		return "Gerenciar Paciente";
	}

	@Override
	protected JPanel getPanelForm() throws Exception {

		panelForm = new JPanel();
		panelForm.setPreferredSize(new Dimension(0, 0));
		panelForm.setLayout(new MigLayout("", "[grow][grow][][grow][grow]",
				"[grow][23px][5px,fill][23px][5px][23px][5px][23px][5px][5px][35px][grow,fill]"));

		panelForm.setBackground(new java.awt.Color(237, 242, 249));

		JLabel cpfLabel = new JLabel("CPF");
		panelForm.add(cpfLabel, "flowx,cell 1 1,growy");

		JLabel nomeLabel = new JLabel("Nome");
		panelForm.add(nomeLabel, "flowx,cell 3 1,growy");

		JLabel nomeMaeLabel = new JLabel("Nome da M\u00E3e");
		panelForm.add(nomeMaeLabel, "flowx,cell 1 3,growy");

		JLabel nomePaiLabel = new JLabel("Nome do Pai");
		panelForm.add(nomePaiLabel, "flowx,cell 3 3");

		JLabel enderecoLabel = new JLabel("Endere\u00E7o");
		panelForm.add(enderecoLabel, "flowx,cell 1 5 3 1,growy");

		EnderecoTextField = new MTextField();
		panelForm.add(EnderecoTextField, "cell 1 5 3 1,grow");
		EnderecoTextField.setColumns(20);

		nomeMaeTextField = new MTextField();
		nomeMaeTextField.setColumns(20);

		nomePaiTextField = new MTextField();
		nomePaiTextField.setColumns(20);

		cpfTextField = new MTextField("###.###.###-##");

		nomeTextField = new MTextField();
		nomeTextField.setColumns(20);

		panelForm.add(cpfTextField, "flowx,cell 1 1,grow");
		panelForm.add(nomeTextField, "cell 3 1,grow");
		panelForm.add(nomeMaeTextField, "cell 1 3,grow");
		panelForm.add(nomePaiTextField, "cell 3 3,grow");

		JLabel nascimentoLabel = new JLabel("Nascimento");
		panelForm.add(nascimentoLabel, "flowx,cell 1 7,growy");

		JLabel tipoSanguineoLabel = new JLabel("Tipo Sangu\u00EDneo");
		panelForm.add(tipoSanguineoLabel, "flowx,cell 3 7,alignx left,growy");

		nascimentoTextField = new MDateField();
		panelForm.add(nascimentoTextField, "cell 1 7,grow");

		tipoSanguineoComboBox = new JComboBox<TipoSang>();
		tipoSanguineoComboBox.setModel(new DefaultComboBoxModel<TipoSang>(TipoSang.list()));
		panelForm.add(tipoSanguineoComboBox, "cell 3 7,grow");

		limparBtn = new MJButton("Limpar Tela");
		cadastrarBtn = new MJButton("Cadastrar Paciente");

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
		SwingWorker<Paciente, Void> mySwingWorker = new SwingWorker<Paciente, Void>() {

			@Override
			protected Paciente doInBackground() throws Exception {
				try {
					return controle.buscar(cpf);
				} catch (ControleExcption e) {
					JOptionPane.showMessageDialog(GerenciarPaciente.this, e.getMessage());
				} finally {
					nomeTextField.setEditable(true);
				}
				return null;
			}

			@Override
			protected void done() {
				super.done();
				try {
					Paciente paciente = get();
					if (paciente == null)
						return;
					nomeTextField.setText(paciente.getNome());
					nomeMaeTextField.setText(paciente.getNomeMae());
					nomePaiTextField.setText(paciente.getNomePai());
					EnderecoTextField.setText(paciente.getEndereco());
					nascimentoTextField.setDate(paciente.getNascimento());
					tipoSanguineoComboBox.setSelectedItem(paciente.getTipoSanguinio());

					panelForm.repaint();
					JOptionPane.showMessageDialog(GerenciarPaciente.this, "Paciente: " + paciente.getNome());
				} catch (Exception e) {
					JOptionPane.showMessageDialog(GerenciarPaciente.this, e.getMessage());
				} finally {
					nomeTextField.setEditable(true);
				}
			}
		};

		mySwingWorker.execute();
	}

	private void gravar() {

		nomeTextField.setEditable(false);
		Paciente paciente = new Paciente();
		paciente.setNome(nomeTextField.getText());
		paciente.setCPF(cpfTextField.getApenasFigitos());
		paciente.setNomeMae(nomeMaeTextField.getText());
		paciente.setNomePai(nomePaiTextField.getText());
		paciente.setEndereco(EnderecoTextField.getText());
		paciente.setNascimento(nascimentoTextField.getDate());
		paciente.setTipoSanguinio((TipoSang) tipoSanguineoComboBox.getSelectedItem());
		try {
			controle.gravar(paciente);
			JOptionPane.showMessageDialog(this, "Paciente cadastrado com sucesso");
		} catch (ControleExcption e) {
			JOptionPane.showMessageDialog(GerenciarPaciente.this, e.getMessage());
		}
	 

	}

	public void limpar() {
		nomeTextField.setText("");
		nomeMaeTextField.setText("");
		nomePaiTextField.setText("");
		EnderecoTextField.setText("");
		nascimentoTextField.setDate(null);
		tipoSanguineoComboBox.setSelectedIndex(0);
		cpfTextField.setText("");

	}

}
