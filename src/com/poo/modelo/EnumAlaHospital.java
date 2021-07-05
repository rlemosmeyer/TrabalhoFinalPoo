package com.poo.modelo;

public enum EnumAlaHospital {
	CARDIOLOGIA, PEDIATRICA, PNEUMOLOGIA, NEUROLOGIA;

	@Override
	public String toString() {
		String str = super.toString();
		return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
	}

	public static EnumAlaHospital[] listar() {
		return new EnumAlaHospital[] { CARDIOLOGIA, PEDIATRICA, PNEUMOLOGIA, NEUROLOGIA };
	}
}
