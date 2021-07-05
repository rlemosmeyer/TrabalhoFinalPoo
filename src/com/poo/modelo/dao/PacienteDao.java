package com.poo.modelo.dao;

import java.util.HashMap;

import com.poo.modelo.Paciente;

public class PacienteDao extends AbstractDao<String, Paciente> {
	@Override
	protected String getNomeArq() {
		return "pacientes.txt";
	}

	public Paciente buscarPorCpf(String cpf) throws PersistenciaException {
		try {
			HashMap<String, Paciente> map = lerArquivoBinario();

			System.err.println("*"+cpf+"*"+map.containsKey(cpf));
			return map.get(cpf);
		} catch (Exception e) {
			throw new PersistenciaException("Erro ao buscar pascinete", e);
		}
	}
	

	public void salva(Paciente paciente) throws PersistenciaException {
		try {
			HashMap<String, Paciente> map = lerArquivoBinario();
			map.remove(paciente.getCPF());
			map.put(paciente.getCPF(), paciente);
			gravarArquivoBinario(map);
		} catch (Exception e) {
			throw new PersistenciaException("Erro ao buscar pascinete", e);
		}

	}

	@Override
	protected Class<Paciente> getValueClass() {
		// TODO Auto-generated method stub
		return Paciente.class;
	}

	@Override
	protected Class<String> getKeyClass() {
		// TODO Auto-generated method stub
		return String.class;
	}
	
 

}
