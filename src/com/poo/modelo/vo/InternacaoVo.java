package com.poo.modelo.vo;

import com.poo.modelo.Atendimento;
import com.poo.modelo.EnumAlaHospital;

public class InternacaoVo {
	private Atendimento atendimento;
	private EnumAlaHospital ala = null;
	private int posicaoFilaAla = 0;
	private int leitos;
	private int leitosVagos;

	public InternacaoVo(Atendimento atendimento, int posicaoFilaAla) {
		super();
		this.atendimento = atendimento;
		this.posicaoFilaAla = posicaoFilaAla;
		this.ala = atendimento.getAla();
	}

	public Atendimento getAtendimento() {
		return atendimento;
	}

	public void setAtendimento(Atendimento atendimento) {
		this.atendimento = atendimento;
	}

	public EnumAlaHospital getAla() {
		return ala;
	}

	public void setAla(EnumAlaHospital ala) {
		this.ala = ala;
	}

	public int getPosicaoFilaAla() {
		return posicaoFilaAla;
	}

	public void setPosicaoFilaAla(int posicaoFilaAla) {
		this.posicaoFilaAla = posicaoFilaAla;
	}

	public int getLeitos() {
		return leitos;
	}

	public void setLeitos(int leitos) {
		this.leitos = leitos;
	}

	public int getLeitosVagos() {
		return leitosVagos;
	}

	public void setLeitosVagos(int leitosVagos) {
		this.leitosVagos = leitosVagos;
	}
	
	
}
