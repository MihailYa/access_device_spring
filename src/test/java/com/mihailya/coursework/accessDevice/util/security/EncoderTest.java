package com.mihailya.coursework.accessDevice.util.security;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class EncoderTest {

	@Test
	void md5() {
		try (FileOutputStream fileOutputStream = new FileOutputStream("md5.txt")) {
			fileOutputStream.write(Encoder.md5("qwerty").getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}