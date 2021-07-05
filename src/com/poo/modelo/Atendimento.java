package com.poo.modelo;

import java.io.Serializable;
import java.util.Date;

public class Atendimento implements Serializable, Comparable<Atendimento> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String cpf;
	private String nome;
	private Date dataEntrada;
	private String queixa;
	private int prioridade;
	private EnumAlaHospital ala;
	private String observacao;
	private Date dataSaida;
	private String chave;

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(Date dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public String getQueixa() {
		return queixa;
	}

	public void setQueixa(String queixa) {
		this.queixa = queixa;
	}

	public int getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(int prioridade) {
		this.prioridade = prioridade;
	}

	public EnumAlaHospital getAla() {
		return ala;
	}

	public void setAla(EnumAlaHospital ala) {
		this.ala = ala;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Date getDataSaida() {
		return dataSaida;
	}

	public void setDataSaida(Date dataSaida) {
		this.dataSaida = dataSaida;
	}

	@Override
	public int compareTo(Atendimento arg0) {
		if (arg0.getDataEntrada() != null)
			return arg0.getDataEntrada().compareTo(getDataEntrada());

		if (arg0.getDataSaida() == null && getDataSaida() != null)
			return -1;

		if (arg0.getDataSaida() != null && getDataSaida() == null)
			return 1;

		return 0;
	}

	public String getChave() {
		return chave;
	}

	public void setChave(String chave) {
		this.chave = chave;
	}


}
