package com.poo.controle;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.poo.modelo.AlaHospial;
import com.poo.modelo.Atendimento;
import com.poo.modelo.Paciente;
import com.poo.modelo.dao.AtendimentoDao;
import com.poo.modelo.dao.HospitalDao;
import com.poo.modelo.dao.PacienteDao;
import com.poo.modelo.dao.PersistenciaException;
import com.poo.modelo.vo.AtendimentoFilaVo;

public class AtendimentoControle extends HospitalControle {
	private PacienteDao pacienteDao = new PacienteDao();
	private AtendimentoDao atendimentoDao = new AtendimentoDao();
	private HospitalDao hospitalDao = new HospitalDao();

	public Paciente buscarPaciente(String cpf) throws ControleExcption {
		try {
			Paciente paciente = pacienteDao.buscarPorCpf(cpf);
			if (paciente != null) {
				Atendimento atendimento = atendimentoDao.buscarAtendimentoAberto(cpf);
				if (atendimento!=null && atendimento.getDataSaida() == null) {
					SimpleDateFormat dtf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
					throw new ControleExcption("Já á um atendimento para esse paciente. Entrada:"
							+ dtf.format(atendimento.getDataEntrada()));
				}
			}
			return paciente;
		} catch (PersistenciaException e) {
			e.printStackTrace();
			throw new ControleExcption("Erro ao buscar paciente: \n" + e.getMessage(), e);
		}
	}

	public String gravar(Atendimento atendiemnto) throws ControleExcption {
		try {
			if (atendiemnto == null)
				throw new ControleExcption("Nenhum paciente informado");

			if (atendiemnto.getCpf() == null || atendiemnto.getCpf().isBlank())
				throw new ControleExcption("CPF do paciente não informado");

			if (atendiemnto.getNome() == null || atendiemnto.getNome().isBlank())
				throw new ControleExcption("Nome do paciente não informado");

			if (atendiemnto.getPrioridade() == 5) {
				return internar(atendiemnto);
			}

			getHospital().getfAtendimento().add(atendiemnto.getPrioridade(), atendiemnto.getCpf());
			atendimentoDao.salva(atendiemnto);
			hospitalDao.salva(getHospital());
			System.out.println( "Paciente adicionado na fina de atendimento (tamanho da fila " + atendiemnto.getPrioridade() + ": "
					+ getHospital().getfAtendimento().getFila(atendiemnto.getPrioridade()).size());
			return "Paciente adicionado na fina de atendimento (tamanho da fila " + atendiemnto.getPrioridade() + ": "
					+ getHospital().getfAtendimento().getFila(atendiemnto.getPrioridade()).size();
		} catch (ControleExcption e) {
			throw e;
		} catch (Exception e) {
			resturarHospital();
			e.printStackTrace();
			throw new ControleExcption("Erro ao salvar paciente: \n" + e.getMessage(), e);
		} finally {
		}
	}

	private String internar(Atendimento atendiemnto) throws Exception {
		if (atendiemnto.getAla() == null)
			throw new ControleExcption("Informe uma ala para a internação");

		AlaHospial ala = getHospital().getAlas().get(atendiemnto.getAla());
		boolean semVagas = false;
		String msg = "";
		if (ala.temVaga()) {
			ala.internar(atendiemnto.getCpf());
			msg = "Paciente internado na ala " + ala.getAla();
		} else if (getHospital().temVagasEnfermagem()) {
			getHospital().getFilaEmfermagem().add(atendiemnto.getCpf());
			ala.adFila(atendiemnto.getCpf());
			msg = "Paciente adicionado na fila da ala " + ala.getAla() + " \nPosição na fila da ala: "
					+ ala.getFilaEspera().size() + " \nPosição na fila da enfermagem: "
					+ getHospital().getFilaEmfermagem().size();
			;
		} else {
			semVagas = true;
			atendiemnto.setDataSaida(new Date());
			atendiemnto.setObservacao("paciente encaminhado para outro hospital por indisponibilidade de leitos");
		}
		atendimentoDao.salva(atendiemnto);
		hospitalDao.salva(getHospital());
		if (semVagas)
			throw new Exception("Não temos vagas para enternação ou na emfermaria. \nPor favor procure outro hospital");
		System.err.println(msg);
		return msg;
	}

	public List<AtendimentoFilaVo> getAtendimentosPorFinalizar() throws ControleExcption {
		try {
			List<AtendimentoFilaVo> lstVo = new ArrayList<AtendimentoFilaVo>();

			for (AlaHospial ala : getHospital().getAlas().values()) {
				for (int i = 0; i < ala.getLeitos().size(); i++) {
					Atendimento atendimento = atendimentoDao.buscarAtendimentoAberto(ala.getLeitos().get(i));
					if(atendimento==null)
						throw new ControleExcption("Nenhum atendimento encontrado");
					AtendimentoFilaVo vo = new AtendimentoFilaVo(atendimento, "Internado na ala " + ala.getAla());
					lstVo.add(vo);
				}
			}
			for (String cpf : getHospital().getFilaEmfermagem()) {
				Atendimento atendimento = atendimentoDao.buscarAtendimentoAberto(cpf);
				AtendimentoFilaVo vo = new AtendimentoFilaVo(atendimento, "Fila Enfermagem");
				lstVo.add(vo);
			}

			return lstVo;
		} catch (PersistenciaException e) {
			resturarHospital();
			throw new ControleExcption("Erro ao buscar paciente: \n" + e.getMessage(), e);
		}
	}

	public List<AtendimentoFilaVo> getPacientesInterados() throws ControleExcption {
		try {
			List<AtendimentoFilaVo> lstVo = new ArrayList<AtendimentoFilaVo>();

			for (AlaHospial ala : getHospital().getAlas().values()) {
				for (int i = 0; i < ala.getLeitos().size(); i++) {
					Atendimento atendimento = atendimentoDao.buscarAtendimentoAberto(ala.getLeitos().get(i));
					if(atendimento==null)
						throw new ControleExcption("Nenhum atendimento encontrado");
					AtendimentoFilaVo vo = new AtendimentoFilaVo(atendimento, "Internado na ala " + ala.getAla());
					lstVo.add(vo);
				}
			}
			for (String cpf : getHospital().getFilaEmfermagem()) {
				Atendimento atendimento = atendimentoDao.buscarAtendimentoAberto(cpf);
				AtendimentoFilaVo vo = new AtendimentoFilaVo(atendimento, "Fila Enfermagem");
				lstVo.add(vo);
			}

			return lstVo;
		} catch (PersistenciaException e) {
			resturarHospital();
			throw new ControleExcption("Erro ao buscar paciente: \n" + e.getMessage(), e);
		}
	}

	public void finalizarAtendimento(Atendimento atendimento) throws ControleExcption {
		if (atendimento.getCpf() == null || atendimento.getCpf().isBlank())
			throw new ControleExcption("Informe todos os dados do paciente");
		try {
			getHospital().getFilaEmfermagem().remove(atendimento.getCpf());
			for (AlaHospial halohospital : getHospital().getAlas().values()) {
				halohospital.getLeitos().remove(atendimento.getCpf());
				halohospital.getFilaEspera().remove(atendimento.getCpf());
			}
			atendimento.setDataSaida(new Date());
			atendimentoDao.salva(atendimento);
			hospitalDao.salva(getHospital());
		} catch (PersistenciaException e) {
			resturarHospital();
			e.printStackTrace();
			throw new ControleExcption("Erro ao finalixaratendimento: " + e.getMessage());
		}
	}

}
