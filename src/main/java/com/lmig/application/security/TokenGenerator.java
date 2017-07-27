package com.lmig.application.security;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;

/**
 * This class encapsulates token creation and validation... I have created my
 * own secret string below, this is just seed data and you could use anything
 * you want, just be consistent
 */
public class TokenGenerator {


	private static String SECRET = "f!36N0%13";

	/**
	 * I decided to put the creation date of the token into the token itself, thus
	 * every token will be different since they are created at diff times. This is
	 * the format of the date
	 */
	private static final String DATE_FORMAT = "yyyy-MM-dd_HH:mm";

	/**
	 * This will generate and encrypt a token that can be used with all JSON apis. I
	 * am using the Blowfish encryption algo and
	 *
	 * @return Returns a String object with the encrypted token as its value
	 * @throws Throwable
	 *             t
	 */
	public static String genToken() throws Throwable {

		SecretKeySpec secretKeySpec = new SecretKeySpec(SECRET.getBytes(), "Blowfish");
		Cipher cipher = Cipher.getInstance("Blowfish");
		cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);

		// build my token
		DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
		Calendar cal = Calendar.getInstance();
		String date = dateFormat.format(cal.getTime());
		// this line just concats the date then a tilda '~' then the letters 'TIY'
		String unencryptedToken = String.format("%s~TIY", date);

		// now encrypt the message encrypt message
		byte[] hasil = cipher.doFinal(unencryptedToken.getBytes());
		String encryptedToken = new BASE64Encoder().encode(hasil);
		return encryptedToken;
	}

	/**
	 * This takens in an encrypted token and decrypts it to a string
	 * 
	 * @param token
	 * @return
	 */
	private static String decrypt(String token) {
		String decrypted = null;

		try {
			SecretKeySpec secretKeySpec = new SecretKeySpec(SECRET.getBytes(), "Blowfish");
			Cipher cipher = Cipher.getInstance("Blowfish");
			cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
			byte[] hasil = cipher.doFinal(new BASE64Decoder().decodeBuffer(token));
			decrypted = new String(hasil);
		} catch (Throwable t) {
			// ignore
		}
		return decrypted;
	}

	/**
	 * We decrypt the token, and since the creation date is inside, lets make sure
	 * its only 5 mins old max... so this is just a time based token.. (we could
	 * have also put the username or id in it.. but we didnt, keeping it simple for
	 * now) The front end will need to login and then if they get denied they will
	 * need to login again for fresh token.
	 *
	 * @param key
	 * @return
	 */
	public static boolean isValidKey(String key) {
		DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
		Calendar fifteenMinsAgo = Calendar.getInstance();
		fifteenMinsAgo.add(Calendar.MINUTE, -15);
		boolean authorized = false;
		if (key != null) {
			String decrypted = null;
			try {
				decrypted = decrypt(key);
			} catch (Throwable throwable) {
				throwable.printStackTrace();
			}
			if (decrypted != null) {
				StringTokenizer st = new StringTokenizer(decrypted, "~");
				if (st.countTokens() == 2) {
					try {
						Date dayOfToken = dateFormat.parse(st.nextToken());
						// must be within a year
						authorized = dayOfToken.after(fifteenMinsAgo.getTime());
						// second part of token should match our key
						authorized = authorized && "TIY".equals(st.nextToken());
					} catch (Throwable t) {
						t.printStackTrace();
					}
				}
			}
		}
		return authorized;
	}
}
