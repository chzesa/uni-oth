package oht.chess.io;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
/**
 * Tiedostojen lukemiseen ja kirjoittamiseen liittyvä apuluokka.
 */
public class FileHandler {
	/**
	 * Yrittää lukea polussa sijaitsevan tiedoston.
	 * @param	path	luettavan tiedoston polku
	 * @return	Merkkijono joka sisältää luetun tiedoston, tai tyhjä merkkijono jos lukeminen epäonnistui.
	 */
	public static String readToString(String path) {
		byte[] bytes;
		try {
			Path p = Paths.get(path);
			bytes = Files.readAllBytes(p);
		} catch (IOException e) {
			return "";
		} catch (SecurityException e) {
			return "";
		}

		return new String(bytes);
	}

	/**
	 * Yrittää kirjoittaa merkkijonon polussa sijaitsevaan
	 * tiedostoon. Onnistuessaan luo uuden tiedoston
	 * tai päällekirjoitaa edellisen tiedoston.
	 * @param	content	tiedostoon kirjoitettava merkkijono
	 * @param	path	tiedostopolku

	 * @return	true, jos operaatio onnistue, false muuten.
	 */
	public static boolean write(String content, String path) {
		if (content == null || path == null) {
			return false;
		}

		try {
			Path p = Paths.get(path);
			byte[] bytes = content.getBytes();
			Files.write(p, bytes);
		} catch (IOException e) {
			return false;
		} catch (SecurityException e) {
			return false;
		}

		return true;
	}
}