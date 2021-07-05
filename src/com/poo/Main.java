package com.poo;

import java.awt.EventQueue;
import java.util.Date;

import com.poo.controle.AtendimentoControle;
import com.poo.controle.ControleExcption;
import com.poo.modelo.Atendimento;
import com.poo.modelo.EnumAlaHospital;
import com.poo.modelo.Paciente;
import com.poo.modelo.dao.PacienteDao;
import com.poo.modelo.dao.PersistenciaException;
import com.poo.visao.TelaPrincipal;

public class Main {
	/**
	 * Launch the application.
	 */ 
	public static void main(String[] args) {
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Windows".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		}

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//seed();
					TelaPrincipal frame = new TelaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	protected static void seed() throws PersistenciaException, InterruptedException, ControleExcption {
		PacienteDao pDao = new PacienteDao();
		AtendimentoControle ctr= new AtendimentoControle();
		int nivel = 3;
		for (int i = 0; i < 30; i++) {
			Paciente p = new Paciente();
			p.setCPF("0000000000"+i);
			p.setNome("Paciente "+i);
			
			pDao.salva(p);
			
			Atendimento a = new Atendimento();
			a.setDataEntrada(new Date());
			a.setCpf(p.getCPF());
			a.setNome(p.getNome());
			a.setQueixa("quixa do paciente "+i);
			a.setPrioridade(nivel++);
			a.setAla(i%2==0 ? EnumAlaHospital.CARDIOLOGIA :EnumAlaHospital.NEUROLOGIA );
			if(nivel>5)
				nivel = 3;
			ctr.gravar(a);
			
		}
		
	}
}
