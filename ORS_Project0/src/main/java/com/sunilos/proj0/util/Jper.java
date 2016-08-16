package com.sunilos.proj0.util;

import java.util.HashMap;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;

public class Jper {

	public static void main(String[] args) throws JRException {
		HashMap<String, String> hm = new HashMap<String, String>();

		JasperCompileManager
				.compileReportToFile("C:/Users/Admin/Documents/report1.jrxml");

	}
}
