package com.poo.modelo.dao;

import java.util.HashMap;

import com.poo.modelo.Hospital;

public class HospitalDao extends AbstractDao<Integer, Hospital> {
	@Override
	protected String getNomeArq() {
		return "hospital.txt";
	}

	public Hospital get() throws PersistenciaException {
		try {
			HashMap<Integer, Hospital> map = lerArquivoBinario();
			return map.get(0);
		} catch (Exception e) {
			throw new PersistenciaException("Erro ao buscar pascinete", e);
		}
	}
	

	public void salva(Hospital hospital) throws PersistenciaException {
		try {
			HashMap<Integer, Hospital> map = lerArquivoBinario();
			map.remove(0);
			map.put(0, hospital);
			gravarArquivoBinario(map);
		} catch (Exception e) {
			throw new PersistenciaException("Erro ao buscar pascinete", e);
		}

	}

	@Override
	protected Class<Hospital> getValueClass() {
		// TODO Auto-generated method stub
		return Hospital.class;
	}

	@Override
	protected Class<Integer> getKeyClass() {
		// TODO Auto-generated method stub
		return Integer.class;
	}
	
 
}
