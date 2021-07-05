package com.poo.controle;

import com.poo.modelo.Paciente;
import com.poo.modelo.dao.PacienteDao;
import com.poo.modelo.dao.PersistenciaException;

public class PacienteControle {
	private PacienteDao dao = new PacienteDao();

	public Paciente buscar(String cpf) throws ControleExcption {
		try {
			System.err.println("#"+cpf);
			return dao.buscarPorCpf(cpf);
		} catch (PersistenciaException e) {
			throw new ControleExcption("Erro ao buscar paciente: \n" + e.getMessage(), e);
		}
	}

	public void gravar(Paciente paciente) throws ControleExcption {
		try {
			if(paciente==null)
				throw new ControleExcption("Nenhum paciente informado");

			if(paciente.getCPF()==null || paciente.getCPF().isBlank())
				throw new ControleExcption("CPF do paciente não informado");

			if(paciente.getNome()==null || paciente.getNome().isBlank())
				throw new ControleExcption("Nome do paciente não informado");

			dao.salva(paciente);
		} catch (ControleExcption  e) {
			throw e;
		}catch (PersistenciaException e) {
			throw new ControleExcption("Erro ao salvar paciente: \n" + e.getMessage(), e);
		}
	}

}
