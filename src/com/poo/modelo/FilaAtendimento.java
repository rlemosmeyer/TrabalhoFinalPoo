package com.poo.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FilaAtendimento implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<String> fila1 = new ArrayList<String>();
	private List<String> fila2 = new ArrayList<String>();
	private List<String> fila3 = new ArrayList<String>();
	private List<String> fila4 = new ArrayList<String>();
	private List<List<String>> mapFilas = new ArrayList<List<String>>();

	public FilaAtendimento() {
		mapFilas.add(fila1);
		mapFilas.add(fila2);
		mapFilas.add(fila3);
		mapFilas.add(fila4);
	}

	public List<String> getFila1() {
		return fila1;
	}

	public void setFila1(List<String> fila1) {
		this.fila1 = fila1;
	}

	public List<String> getFila2() {
		return fila2;
	}

	public void setFila2(List<String> fila2) {
		this.fila2 = fila2;
	}

	public List<String> getFila3() {
		return fila3;
	}

	public void setFila3(List<String> fila3) {
		this.fila3 = fila3;
	}

	public List<String> getFila4() {
		return fila4;
	}

	public void setFila4(List<String> fila4) {
		this.fila4 = fila4;
	}

	public List<List<String>> getMapFilas() {
		return mapFilas;
	}

	public void setMapFilas(List<List<String>> mapFilas) {
		this.mapFilas = mapFilas;
	}

	public int ocupacao() {
		return fila1.size() + fila2.size() + fila3.size() + fila4.size();
	}

	public void add(int fila, String cpf) throws Exception {
		mapFilas.get(fila - 1).add(cpf);
	}

	public List<String> getFila(int fila) throws Exception {
		return mapFilas.get(fila - 1);
	}

	public String proximo() {
		for (int i = mapFilas.size() - 1; i >= 0; i--) {
			if (!mapFilas.get(i).isEmpty())
				return mapFilas.get(i).get(0);
		}

		return null;
	}

	public String remove() {
		for (int i = mapFilas.size() - 1; i >= 0; i--) {
			if (!mapFilas.get(i).isEmpty())
				return mapFilas.get(i).remove(0);
		}

		return null;
	}

	public String remove(String cpf) {
		for (int i = mapFilas.size() - 1; i >= 0; i--) {
			List<String> lista = mapFilas.get(i);
			for (int j = 0; j < lista.size(); j++) {
				if (lista.get(j).replaceAll("\\D+", "").equals(cpf))
					return lista.remove(j);
			}
		}

		return null;
	}
}
