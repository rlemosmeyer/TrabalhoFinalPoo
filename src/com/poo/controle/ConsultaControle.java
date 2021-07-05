package com.poo.controle;

import com.poo.modelo.AlaHospial;
import com.poo.modelo.Atendimento;
import com.poo.modelo.Consulta;
import com.poo.modelo.dao.AtendimentoDao;
import com.poo.modelo.dao.ConsultaDao;
import com.poo.modelo.dao.HospitalDao;
import com.poo.modelo.dao.PersistenciaException;

public class ConsultaControle extends HospitalControle {
	private AtendimentoDao atendimentoDao = new AtendimentoDao();
	private HospitalDao hospitalDao = new HospitalDao();
	private ConsultaDao consultaDao = new ConsultaDao();

	public Atendimento proximoAtendimento() throws ControleExcption {
		try {
			String cpf = getHospital().getfAtendimento().proximo();
			return atendimentoDao.buscarAtendimentoAberto(cpf);
		} catch (PersistenciaException e) {
			resturarHospital();
			throw new ControleExcption("Erro ao buscar paciente: \n" + e.getMessage(), e);
		}
	}

	public String internar(Consulta consulta) throws ControleExcption {

		try {
			if(consulta.getAla()==null)
				throw new ControleExcption("Nenhuma ala informada");
			
			removeFilaAtendimento(consulta);
			
			AlaHospial ala = getHospital().getAlas().get(consulta.getAla());
			String msg = "";
			if (ala.temVaga()) {
				ala.internar(consulta.getCpf());
				msg = "Paciente internado na ala " + ala.getAla();
			} else {
				ala.adFila(consulta.getCpf());
				msg = "Paciente adicionado na fila da ala " + ala.getAla() + " \nPosição na fila da ala: "
						+ ala.getFilaEspera().size() + " \nPosição na fila da enfermagem: "
						+ getHospital().getFilaEmfermagem().size();

			}
			consultaDao.salva(consulta);
			hospitalDao.salva(getHospital());
			return msg;
		} catch (ControleExcption e) {
			throw e;
		}catch (Exception e) {e.printStackTrace();
			resturarHospital();
			throw new ControleExcption("Erro ao gerar internação: " + e.getCause());
		}
	}

	private void removeFilaAtendimento(Consulta consulta) throws ControleExcption {
		if (consulta.getCpf() == null || consulta.getCpf().isBlank() || consulta.getNome() == null
				|| consulta.getNome().isBlank())
			throw new ControleExcption("Informe todos os dados do paciente");

		getHospital().getfAtendimento().remove(consulta.getCpf());
	}
	
	public void finalizaConsulta(Consulta consulta) throws ControleExcption {
		try {
			removeFilaAtendimento(consulta);
			
			consultaDao.salva(consulta);
			hospitalDao.salva(getHospital());
		} catch (ControleExcption e) {
			throw e;
		} catch (Exception e) {
			resturarHospital();
			e.printStackTrace();
			throw new ControleExcption("Erro ao finalizar consulta: " + e.getMessage(), e);
		}
	}

}
