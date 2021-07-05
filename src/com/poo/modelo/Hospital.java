package com.poo.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Hospital implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Cardiologia com 3 leitos
	private AlaHospial alaCardiologia = new AlaHospial(EnumAlaHospital.CARDIOLOGIA, 3);
	// Pediátrica com 6 leitos
	private AlaHospial alaPediatrica = new AlaHospial(EnumAlaHospital.PEDIATRICA, 6);
	// Pneumologia com 4 leitos
	private AlaHospial alaPneumologia = new AlaHospial(EnumAlaHospital.PNEUMOLOGIA, 4);
	// Neurologia com 6 leitos
	private AlaHospial alaNeurologia = new AlaHospial(EnumAlaHospital.NEUROLOGIA, 6);
	private HashMap<EnumAlaHospital, AlaHospial> mAlas;
	private FilaAtendimento fAtendimento = new FilaAtendimento();

	private int capacidadeEnfermagem = 10;

	private List<String> filaEmfermagem = new ArrayList<String>();

	public Hospital() {
		super();
		mAlas = new HashMap<EnumAlaHospital, AlaHospial>();
		mAlas.put(alaCardiologia.getAla(), alaCardiologia);
		mAlas.put(alaPediatrica.getAla(), alaPediatrica);
		mAlas.put(alaPneumologia.getAla(), alaPneumologia);
		mAlas.put(alaNeurologia.getAla(), alaNeurologia);
	}

	public boolean temVagasEnfermagem() {
		return filaEmfermagem.size() < capacidadeEnfermagem - 1;
	}

	public AlaHospial getAlaCardiologia() {
		return alaCardiologia;
	}

	public void setAlaCardiologia(AlaHospial alaCardiologia) {
		this.alaCardiologia = alaCardiologia;
	}

	public AlaHospial getAlaPediatrica() {
		return alaPediatrica;
	}

	public void setAlaPediatrica(AlaHospial alaPediatrica) {
		this.alaPediatrica = alaPediatrica;
	}

	public AlaHospial getAlaPneumologia() {
		return alaPneumologia;
	}

	public void setAlaPneumologia(AlaHospial alaPneumologia) {
		this.alaPneumologia = alaPneumologia;
	}

	public AlaHospial getAlaNeurologia() {
		return alaNeurologia;
	}

	public void setAlaNeurologia(AlaHospial alaNeurologia) {
		this.alaNeurologia = alaNeurologia;
	}

	public FilaAtendimento getfAtendimento() {
		return fAtendimento;
	}

	public void setfAtendimento(FilaAtendimento fAtendimento) {
		this.fAtendimento = fAtendimento;
	}

	public int getCapacidadeEnfermagem() {
		return capacidadeEnfermagem;
	}

	public void setCapacidadeEnfermagem(int capacidadeEnfermagem) {
		this.capacidadeEnfermagem = capacidadeEnfermagem;
	}

	public List<String> getFilaEmfermagem() {
		return filaEmfermagem;
	}

	public void setFilaEmfermagem(List<String> filaEmfermagem) {
		this.filaEmfermagem = filaEmfermagem;
	}

	public HashMap<EnumAlaHospital, AlaHospial> getAlas() {
		return mAlas;
	}

	public void setmAlas(HashMap<EnumAlaHospital, AlaHospial> mAlas) {
		this.mAlas = mAlas;
	}

	public HashMap<EnumAlaHospital, AlaHospial> getmAlas() {
		return mAlas;
	}
	
	

}
