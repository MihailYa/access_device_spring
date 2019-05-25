package com.mihailya.coursework.accessDevice.util;

public class CodeMasker {

	public static String maskCode(int codeLen) {
		StringBuilder encodedPassword = new StringBuilder(codeLen);
		for (int i = 0; i < codeLen; ++i) {
			encodedPassword.append("*");
		}

		return encodedPassword.toString();
	}
}
