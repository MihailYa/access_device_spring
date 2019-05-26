package com.mihailya.coursework.accessDevice.util.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encoder {
	public static String md5(String string) {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("Can't find algorithm for encoding", e);
		}

		md.update(string.getBytes());
		byte[] digest = md.digest();

		return new String(digest);
	}
}
