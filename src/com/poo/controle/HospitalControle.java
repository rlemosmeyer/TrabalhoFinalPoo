package com.poo.controle;

import java.util.Collection;

import com.poo.modelo.AlaHospial;
import com.poo.modelo.Hospital;
import com.poo.modelo.dao.HospitalDao;
import com.poo.modelo.dao.PersistenciaException;

public class HospitalControle {

	private static Hospital hospital;

	private HospitalDao hospitalDao = new HospitalDao();

	public Hospital getHospital() {
		if (hospital == null) {
			try {
				hospital = hospitalDao.get();
			} catch (PersistenciaException e) {
				e.printStackTrace();
			}
			if (hospital == null)
				hospital = new Hospital();
		}

		return hospital;
	}
	
	protected void resturarHospital() {
		hospital = null;
	}
	
	public Collection<AlaHospial> getLotacoesAlas(){
		return getHospital().getAlas().values();
	}
	
}
