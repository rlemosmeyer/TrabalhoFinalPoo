package com.poo.controle;

import java.util.ArrayList;
import java.util.List;

import com.poo.modelo.AlaHospial;
import com.poo.modelo.Atendimento;
import com.poo.modelo.dao.AtendimentoDao;
import com.poo.modelo.dao.HospitalDao;
import com.poo.modelo.dao.PersistenciaException;
import com.poo.modelo.vo.InternacaoVo;

public class InternacaoControle extends HospitalControle {
	private AtendimentoDao atendimentoDao = new AtendimentoDao();
	private HospitalDao hospitalDao = new HospitalDao();

	public List<InternacaoVo> getFila() throws ControleExcption {
		try {
			List<InternacaoVo> lstVo = new ArrayList<InternacaoVo>();

			for (AlaHospial ala : getHospital().getAlas().values()) {
				for (int i = 0; i < ala.getFilaEspera().size(); i++) {
					Atendimento atendimento =atendimentoDao.buscarAtendimentoAberto(ala.getFilaEspera().get(i));
					if(atendimento==null)
						throw new ControleExcption("Nenhum atendimento encontrado");
					InternacaoVo vo = new InternacaoVo(atendimento, i + 1);
					vo.setLeitos(ala.getQtdleitos());
					vo.setLeitosVagos(ala.getQtdleitos() - ala.getLeitos().size());
					lstVo.add(vo);
				}
			}

			return lstVo;
		} catch (PersistenciaException e) {
			resturarHospital();
			e.printStackTrace();
			throw new ControleExcption("Erro ao buscar paciente: \n" + e.getMessage(), e);
		}
	}

	public void internar(Atendimento atendimento) throws ControleExcption {

		try {
			if (atendimento.getCpf() == null || atendimento.getCpf().isBlank())
				throw new ControleExcption("Informe todos os dados do paciente");

			getHospital().getfAtendimento().remove(atendimento.getCpf());
			AlaHospial ala = getHospital().getAlas().get(atendimento.getAla());
			if (!ala.temVaga()) {
				throw new ControleExcption("Não há vagas na ala " + ala.getAla());
			}
			ala.internar(atendimento.getCpf());
			getHospital().getFilaEmfermagem().remove(atendimento.getCpf());

			hospitalDao.salva(getHospital());
		} catch (ControleExcption e) {
			throw e;
		} catch (Exception e) {
			resturarHospital();
			throw new ControleExcption("Erro ao gerar internação: " + e.getCause());
		}
	}

}
