package signfsearching;

import java.io.File;
import java.io.IOException;

public class SearchFileSignature {

	private static final Byte EMPTY = -1;
	private static final String ERR0 = "0";  // the flag of error -
											 // "File signature is Right, but this file isn't APK-file"
	private static final String ERR1 = "-1"; // the flag of error
											 // -"File doesn't exist!"
	private static final String ERR2 = "-2"; // the flag of error
											 // -"File doesn't compare with suffix!"
	private static Byte signature[] = { EMPTY, EMPTY, EMPTY, EMPTY };

	public static void searchSignature(File file, String suffix)
			throws IOException {
		boolean compareFlag = false;

		switch (suffix) {
		case "exe": // "exe" file signature header 4D 5A in hex
			signature[0] = 77; // File signature header in hex 4D = 77 in
								// decimal
			signature[1] = 90; // in hex 5A = 90 in decimal

			break;

		case "apk": // "apk" file signature header 50 4B 03 04 in hex
			signature[0] = 80; // in hex 50 = 80 in decimal
			signature[1] = 75; // in hex 4B = 75 in decimal
			signature[2] = 03; // in hex 03 = 03 in decimal
			signature[3] = 04; // in hex 04 = 04 in decimal

			break;

		/*
		 * Add Your signatures
		 */

		default:
			System.err.println("!Wrong file sufffix!");
			break;
		}

		if (file.exists()) {
			for (int i = 0; i < signature.length; i++) {
				// System.out.println(FileReadByte.fReadByte(file, i));
				if (signature[i] != EMPTY) {
					System.out.println(FileReadByte.fReadByte(file, i));
					if (signature[i] == FileReadByte.fReadByte(file, i)) {
						compareFlag = true;
					} else {
						compareFlag = false;
					}
				}

			}
			if (compareFlag) {
				// only for test!
				System.out.println("This is File " + suffix);
			} else {
				System.err.println("This is NOT a  " + suffix + " File!");
			}
			// FileReadByte.fReadByte(file);
		} else {
			System.err.println("File doesn't exist!");
		}

	}

	public static String searchSigFile(File file, String suffix)
			throws IOException {
		boolean compareFlag = false;

		switch (suffix) {
		case "exe": // "exe" file signature header 4D 5A in hex
			signature[0] = 77; // File signature header in hex 4D = 77 in
								// decimal
			signature[1] = 90; // in hex 5A = 90 in decimal

			break;

		case "apk": // "apk" file signature header 50 4B 03 04 in hex
			signature[0] = 80; // in hex 50 = 80 in decimal
			signature[1] = 75; // in hex 4B = 75 in decimal
			signature[2] = 03; // in hex 03 = 03 in decimal
			signature[3] = 04; // in hex 04 = 04 in decimal

			break;

		/*
		 * Add Your signatures
		 */

		default:
			System.err.println("!Wrong file sufffix!");
			break;
		}

		if (file.exists()) {
			for (int i = 0; i < signature.length; i++) {
				// System.out.println(FileReadByte.fReadByte(file, i));
				if (signature[i] != EMPTY) {
					// System.out.println(FileReadByte.fReadByte(file, i));
					if (signature[i] == FileReadByte.fReadByte(file, i)) {
						compareFlag = true;
					} else {
						compareFlag = false;
					}
				}

			}
			if (compareFlag) {
				// only for test!
				// System.out.println("This is File " + suffix );

				if (ApkFilesChk.chk_include(file))
					return file.getAbsolutePath();
				return ERR0;
			} else {
				// System.err.println("This is NOT a  " + suffix + " File!");
				return ERR2;
			}

			// FileReadByte.fReadByte(file);
		} else {
			// System.err.println("File doesn't exist!");
			return ERR1;
		}

	}
}
