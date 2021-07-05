package com.poo.modelo.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.poo.modelo.Atendimento;

public class AtendimentoDao extends AbstractDao<String, Atendimento> {
	SimpleDateFormat dtf = new SimpleDateFormat("ddMMyyyyHH:mm");

	@Override
	protected String getNomeArq() {
		return "atendimentos.txt";
	}

	public Atendimento buscarAtendimentoAberto(String cpf)  throws PersistenciaException{
		List<Atendimento> atendimentos = buscarPorCpf(cpf);
		for (Atendimento atendimento : atendimentos) {
			if(atendimento.getDataSaida()==null)
				return atendimento;
		}
		
		return null;
	}
	
	public List<Atendimento> buscarPorCpf(String cpf) throws PersistenciaException {
		try {
			HashMap<String, Atendimento> map = lerArquivoBinario();

			List<Atendimento> lst = new ArrayList<Atendimento>();
			for (Atendimento atd : map.values()) {
				if (atd.getCpf() != null && atd.getCpf().equals(cpf))
					lst.add(atd);
			}
			Collections.sort(lst);
			return lst;
		} catch (Exception e) {
			e.printStackTrace();
			throw new PersistenciaException("Erro ao buscar pascinete", e);
		}
	}

	public Atendimento buscarPorEntrada(String cpf, Date dtaEntrada) throws PersistenciaException {
		try {
			HashMap<String, Atendimento> map = lerArquivoBinario();

			List<Atendimento> lst = new ArrayList<Atendimento>();
			for (Atendimento atd : map.values()) {
				if (atd.getCpf() != null && atd.getCpf().equals(cpf)) {
					lst.add(atd);
					if (atd.getDataEntrada() == null && dtaEntrada == null || atd.getDataEntrada().equals(dtaEntrada))
						return atd;

				}
			}
			if (lst.isEmpty())
				return null;

			Collections.sort(lst);
			return lst.get(lst.size() - 1);
		} catch (Exception e) {
			e.printStackTrace();
			throw new PersistenciaException("Erro ao buscar pascinete", e);
		}
	}

	public void salva(Atendimento atendimento) throws PersistenciaException {
		try {
			HashMap<String, Atendimento> map = lerArquivoBinario();
			if (atendimento.getChave() == null)
				atendimento.setChave(atendimento.getCpf() + dtf.format(atendimento.getDataEntrada()));

			map.put(atendimento.getChave(), atendimento);
			gravarArquivoBinario(map);
		} catch (Exception e) {
			throw new PersistenciaException("Erro ao buscar pascinete", e);
		}

	}

	@Override
	protected Class<Atendimento> getValueClass() {
		// TODO Auto-generated method stub
		return Atendimento.class;
	}

	@Override
	protected Class<String> getKeyClass() {
		// TODO Auto-generated method stub
		return String.class;
	}


}
