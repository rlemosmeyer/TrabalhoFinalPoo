package com.poo.visao;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

import com.poo.controle.ControleExcption;
import com.poo.controle.InternacaoControle;
import com.poo.modelo.Atendimento;
import com.poo.modelo.vo.InternacaoVo;
import com.poo.visao.componentes.MJButton;
import com.poo.visao.componentes.PanelMestreEscraco;

/**
 *
 */
public class InternacoesView extends PanelMestreEscraco {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private InternacaoControle controle = new InternacaoControle();

	private MJButton internarBtn;
	protected InternacaoVo atendimentoCorrente;
	private MyTableModel tbModel;
	@Override
	public String getInternalTitle() {
		return "Agrdando Internação";
	}


	protected void criaBotoes() {
		internarBtn = new MJButton("Encaminhar Internação");
		addRodaPe(internarBtn);
	}


	@Override
	protected void addEvents() {

		if (internarBtn != null)
			internarBtn.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					internar();
				}
			});

	}

	protected void internar() {
		if (atendimentoCorrente == null)
			return;

		try {
			controle.internar(atendimentoCorrente.getAtendimento());
			JOptionPane.showMessageDialog(this, "Internação realizada com sucesso");
			limpar();
		} catch (ControleExcption e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, e.getMessage());
		}

	}

	public void limpar() {
		tbModel.setDados(null);
		super.limpar();
	}

	@Override
	public void dadosDefault() {
		try {
			if (controle == null)
				controle = new InternacaoControle();
			List<InternacaoVo> lst = controle.getFila();
			tbModel.setDados(lst);

		} catch (ControleExcption e) {
			JOptionPane.showMessageDialog(InternacoesView.this, "Erro ao buscar proximo paciente: " + e.getMessage());
		}

	}

	class MyTableModel extends AbstractTableModel {
		private static final long serialVersionUID = 1L;
		private String[] columnNames = new String[] { "Nome", "Ala", "posição", "Leitos Vagos" };
		private List<InternacaoVo> dados = new ArrayList<InternacaoVo>();

		public List<InternacaoVo> getDados() {
			return dados;
		}

		public void setDados(List<InternacaoVo> dados) {
			this.dados = dados;
			fireTableDataChanged();
		}

		public int getColumnCount() {
			return columnNames.length;
		}

		public int getRowCount() {
			if (dados == null)
				return 0;
			return dados.size();
		}

		public String getColumnName(int col) {
			return columnNames[col];
		}

		public Object getValueAt(int row, int col) {
			if (dados == null)
				return null;
			InternacaoVo vo = dados.get(row);
			switch (col) {
			case 0:
				return vo.getAtendimento().getNome();
			case 1:
				return vo.getAla().toString();
			case 2:
				return vo.getPosicaoFilaAla();
			default:
				return vo.getLeitosVagos() + "/" + vo.getLeitos();
			}
		}

		public boolean isCellEditable(int row, int col) {
			return false;
		}

		public void setValueAt(Object value, int row, int col) {

		}
	}

	@Override
	protected Atendimento linhaSelecionada(int selectedRow) {
		if(tbModel.getDados().isEmpty())
			return null;
		
		atendimentoCorrente =  tbModel.getDados().get(selectedRow);
		if(atendimentoCorrente!=null)
		return atendimentoCorrente.getAtendimento();
		return null;
	}


	@Override
	protected AbstractTableModel getTableModel() {
		if(tbModel==null)
			tbModel =  new MyTableModel();
		return tbModel;
	}

}
