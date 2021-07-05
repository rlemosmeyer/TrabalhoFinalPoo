package com.poo.modelo.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import com.poo.modelo.Consulta;

public class ConsultaDao extends AbstractDao<Integer, Consulta> {
	@Override
	protected String getNomeArq() {
		return "consulta.txt";
	}

	public List<Consulta> buscarPorCpf(String cpf) throws PersistenciaException {
		try {
			HashMap<Integer, Consulta> map = lerArquivoBinario();
			
			List<Consulta> lst = new ArrayList<Consulta>();
			for (Consulta atd: map.values()) {
				if(atd.getCpf()!=null && atd.getCpf().equals(cpf))
					lst.add(atd);
			}
			Collections.sort(lst);
			return lst;
		} catch (Exception e) {
			throw new PersistenciaException("Erro ao buscar pascinete", e);
		}
	}

	public void salva(Consulta consulta) throws PersistenciaException {
		try {
			HashMap<Integer, Consulta> map = lerArquivoBinario();
			map.put(map.size(), consulta);
			gravarArquivoBinario(map);
		} catch (Exception e) {
			throw new PersistenciaException("Erro ao buscar pascinete", e);
		}
	}

	@Override
	protected Class<Consulta> getValueClass() {
		// TODO Auto-generated method stub
		return Consulta.class;
	}

	@Override
	protected Class<Integer> getKeyClass() {
		// TODO Auto-generated method stub
		return Integer.class;
	}
	
 
}
