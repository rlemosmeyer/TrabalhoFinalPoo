package com.poo.visao;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.util.Collection;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.border.EmptyBorder;

import com.poo.controle.HospitalControle;
import com.poo.modelo.AlaHospial;
import com.poo.visao.componentes.IInternalFrame;
import com.poo.visao.componentes.MPanelCenter;

public class LeitosVagosView extends IInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HospitalControle controle = new HospitalControle();
	private MPanelCenter panelForm;
	private DefaultListModel<AlaHospial> listaModel;
	private JList<AlaHospial> lista;

	@Override
	public String getInternalTitle() {
		return "Leitos Vagos";
	}

	@Override
	protected JPanel getPanelForm() throws Exception {

		panelForm = new MPanelCenter();
		panelForm.setLayout(new BorderLayout());

		listaModel = new DefaultListModel<AlaHospial>();
		lista = new JList<AlaHospial>(listaModel);
		panelForm.add(lista, BorderLayout.CENTER);
		lista.setBorder(new EmptyBorder(10, 10, 10, 10));
		
		lista.setCellRenderer(new ListCellRenderer<AlaHospial>() {

			@Override
			public Component getListCellRendererComponent(JList<? extends AlaHospial> arg0, AlaHospial alaHospial, int arg2,
					boolean arg3, boolean arg4) {
				 
				JLabel lb = new JLabel(alaHospial.getAla()+": "+alaHospial.leitosVagos());
				lb.setFont(new Font("Bold", Font.PLAIN, 14));
				lb.setBorder(new EmptyBorder(10, 10, 10, 10));
				return  lb;
			}
		});
		
		return panelForm;
	}

	@Override
	protected void addEvents() {

	}

	@Override
	public void dadosDefault() {
		buscar();
	}

	private void buscar() {
		try {
			Collection<AlaHospial> alas = controle.getLotacoesAlas();
			listaModel.clear();
			for (AlaHospial alaHospial : alas) {
				listaModel.add(0, alaHospial);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(LeitosVagosView.this, e.getMessage());
		}

	}

	@Override
	public void limpar() {

	}

}
