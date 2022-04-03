package crypto;

import java.io.File;

/**
 * A tester for the CryptoUtils class.
 * @author www.codejava.net
 *
 */
public class CryptoUtilsTest {
	public static void main(String[] args) {
		String key = "0000001301770212";
		File in = new File("Prova.dec");
		File encryptedFile = new File("document2022.encrypted");
		File decryptedFile = new File("document.dsdsted");
		
		try {
			//CryptoUtils.encrypt(key, in, new File("provazza.crypp"));
			CryptoUtils.decrypt(key, encryptedFile, new File("decc.txt"));
		} catch (CryptoException ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
	}
}
