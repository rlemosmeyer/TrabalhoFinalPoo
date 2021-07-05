package com.poo.modelo.vo;

import com.poo.modelo.Atendimento;

public class AtendimentoFilaVo {
	private Atendimento atendimento;
	private String situacao;

	public AtendimentoFilaVo(Atendimento atendimento, String situacao) {
		super();
		this.atendimento = atendimento;
		this.situacao = situacao;
	}

	public Atendimento getAtendimento() {
		return atendimento;
	}

	public void setAtendimento(Atendimento atendimento) {
		this.atendimento = atendimento;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

}
