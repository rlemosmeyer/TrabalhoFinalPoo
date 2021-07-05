package com.poo.modelo;

import java.io.Serializable;
import java.util.Date;

public class Paciente  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L
			;
	private String CPF;
	private String nome;
	private String nomeMae;
	private String nomePai;
	private String endereco;
	private Date nascimento;
	private TipoSang tipoSanguinio;

	public String getCPF() {
		return CPF;
	}

	public void setCPF(String cPF) {
		CPF = cPF;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNomeMae() {
		return nomeMae;
	}

	public void setNomeMae(String nomeMae) {
		this.nomeMae = nomeMae;
	}

	public String getNomePai() {
		return nomePai;
	}

	public void setNomePai(String nomePai) {
		this.nomePai = nomePai;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public Date getNascimento() {
		return nascimento;
	}

	public void setNascimento(Date nascimento) {
		this.nascimento = nascimento;
	}

	public TipoSang getTipoSanguinio() {
		return tipoSanguinio;
	}

	public void setTipoSanguinio(TipoSang tipoSanguinio) {
		this.tipoSanguinio = tipoSanguinio;
	}

}
