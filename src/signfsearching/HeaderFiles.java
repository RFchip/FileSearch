package signfsearching;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class HeaderFiles {

	public static void main(String[] args) throws IOException {

		final String FILESTYPE = "exe";
		// final String FILESTYPE = "apk";
		// requested file path
		String reqFilePath = null;
		if (args.length < 2) {
			System.err.println("Missing path of folders!!!");
			System.exit(1);
		}
		if ((args[0].equalsIgnoreCase("null"))
				|| (args[1].equalsIgnoreCase("null"))) {
			System.err.println("Agument(s) cannot be a NULL!");
			System.exit(1);

		} else {

			File inpFldr = new File(args[0].toString());

			ArrayList<File> flArr = SearchFile.retArr(inpFldr);

			for (File f : flArr) {

				reqFilePath = SearchFileSignature.searchSigFile(f, FILESTYPE);

				if (reqFilePath == "-1") {

					System.err.println("File doesn't exist!");

				} else if (reqFilePath == "-2") {

					// "-2" - the returns code processing file, doesn't compare
					// to need suffix
					;
				} else {

					CopyFile.copyFile(reqFilePath, args[1]);

				}

			}

		}
	}

}
