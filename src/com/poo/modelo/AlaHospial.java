package com.poo.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AlaHospial implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private EnumAlaHospital ala;;
	private List<String> leitos;
	private List<String> filaEspera;
	private int qtdleitos;

	public AlaHospial() {
		// TODO Auto-generated constructor stub
	}
	
	public AlaHospial(EnumAlaHospital ala, int tamanho) {
		this.ala = ala;
		this.qtdleitos = tamanho;
		leitos = new ArrayList<String>();
		filaEspera = new ArrayList<String>();
	}

	public boolean temVaga() {
		return leitos.size() < qtdleitos;
	}

	public int leitosVagos() {
		return qtdleitos - leitos.size();
	}

	public void internar(String cpf) throws Exception {
		if (!temVaga())
			throw new Exception("Não tem vaga na ala " + ala);

		leitos.add(cpf);
		filaEspera.remove(cpf);
	}

	public void adFila(String cpf) throws Exception {
		filaEspera.add(cpf);
	}

	public boolean remove(String cpf) {
		return leitos.remove(cpf);
	}

	public EnumAlaHospital getAla() {
		return ala;
	}

	public void setAla(EnumAlaHospital ala) {
		this.ala = ala;
	}

	public List<String> getLeitos() {
		return leitos;
	}

	public void setLeitos(List<String> leitos) {
		this.leitos = leitos;
	}

	public List<String> getFilaEspera() {
		return filaEspera;
	}

	public void setFilaEspera(List<String> filaEspera) {
		this.filaEspera = filaEspera;
	}

	public int getQtdleitos() {
		return qtdleitos;
	}

	public void setQtdleitos(int qtdleitos) {
		this.qtdleitos = qtdleitos;
	}
	
	

}
