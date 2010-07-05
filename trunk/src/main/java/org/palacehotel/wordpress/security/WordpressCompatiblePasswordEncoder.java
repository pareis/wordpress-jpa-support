package org.palacehotel.wordpress.security;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.dao.DataAccessException;
import org.springframework.security.providers.encoding.PasswordEncoder;

public class WordpressCompatiblePasswordEncoder implements PasswordEncoder {

	public String encodePassword(String rawPass, Object salt)
			throws DataAccessException {
		if (rawPass.length()>30) return rawPass;
		MessageDigest digest;
		try {
			digest = MessageDigest.getInstance("SHA-1");
			digest.update((""+salt).getBytes());
			byte[] messageDigest = digest.digest(rawPass.getBytes());

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			PrintStream ps = new PrintStream(baos);
			for (byte b : messageDigest) {
			    ps.format("%02x", b);
			}
			return new String(baos.toByteArray());
		} catch (NoSuchAlgorithmException e) {}
		return null;
	}

	public boolean isPasswordValid(String encPass, String rawPass, Object salt)
			throws DataAccessException {
        String pass1 = "" + encPass;
        String pass2 = encodePassword(rawPass, salt);

        return pass1.equals(pass2);
	}

}
