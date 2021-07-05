package com.poo.visao;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

import com.poo.controle.AtendimentoControle;
import com.poo.controle.ControleExcption;
import com.poo.modelo.Atendimento;
import com.poo.modelo.vo.AtendimentoFilaVo;
import com.poo.visao.componentes.PanelMestreEscraco;

/**
 *
 */
public class AgurdandoLeitosView extends PanelMestreEscraco {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AtendimentoControle controle = new AtendimentoControle();
	private MyTableModel tbModel;
	protected AtendimentoFilaVo atendimentoCorrente;

	@Override
	public String getInternalTitle() {
		return "Finalizar Atendimentos";
	}


	@Override
	protected void addEvents() {
		super.addEvents();
	}

	protected void finalizarAtendimento() {
		if (atendimentoCorrente == null)
			return;

		try {
			controle.finalizarAtendimento(atendimentoCorrente.getAtendimento());
			JOptionPane.showMessageDialog(this, "Atendiemtno finalizado com sucesso");
			limpar();
		} catch (ControleExcption e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}

	}

	@Override
	public void dadosDefault() {
		try {
			if (controle == null)
				controle = new AtendimentoControle();
			List<AtendimentoFilaVo> lst = controle.getAtendimentosPorFinalizar();
			tbModel.setDados(lst);

		} catch (ControleExcption e) {
			JOptionPane.showMessageDialog(AgurdandoLeitosView.this,
					"Erro ao buscar proximo paciente: " + e.getMessage());
		}

	}

	class MyTableModel extends AbstractTableModel {
		private static final long serialVersionUID = 1L;
		private String[] columnNames = new String[] { "Nome", "Situação" };
		private List<AtendimentoFilaVo> dados = new ArrayList<AtendimentoFilaVo>();

		public List<AtendimentoFilaVo> getDados() {
			return dados;
		}

		public void setDados(List<AtendimentoFilaVo> dados) {
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
			AtendimentoFilaVo vo = dados.get(row);
			if (col == 0)
				return vo.getAtendimento().getNome();
			return vo.getSituacao();
		}

		public boolean isCellEditable(int row, int col) {
			return false;
		}

		public void setValueAt(Object value, int row, int col) {

		}
	}

	@Override
	protected Atendimento linhaSelecionada(int selectedRow) {
		if (tbModel.getDados().isEmpty())
			return null;

		atendimentoCorrente = tbModel.getDados().get(selectedRow);
		if (atendimentoCorrente != null)
			return atendimentoCorrente.getAtendimento();
		return null;
	}

	@Override
	protected AbstractTableModel getTableModel() {
		if(tbModel==null)
			tbModel =  new MyTableModel();
		return tbModel;
	}


	@Override
	protected void criaBotoes() {
	}

}
