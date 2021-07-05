package com.poo.modelo;

public enum TipoSang {
	A_POS("A+"), A_NEG("A-"), B_POS("B+"), B_NEG("B-"), AB_POS("AB+"), AB_NEG("AB-"), O_POS("O-"), O_NEG("O+");
	private String desc;
	
	TipoSang(String desc) {
		this.desc = desc;
	}
	
	@Override
	public String toString() {
		return desc;
	}
	
	public static TipoSang[] list() {
		return new TipoSang[] {A_POS,A_NEG,B_POS,B_NEG,AB_POS,AB_NEG,O_POS,O_NEG};
	}
}
