package signfsearching;

import java.nio.channels.*;
import java.io.*;

public class CopyFile {

	private static boolean allfiles = false;

	public static void copyFile(String inputPath, String outputPath) {

		String pathOutputFile = null;

		boolean copyPermission = true;

		// flag for overwriting existing files

		// Check the method arguments
		if ((inputPath == null) && (outputPath == null)) {
			System.err.println("Missing file path!");
			System.exit(1);
		}

		File inFile = new File(inputPath);
		File outDir = new File(outputPath);

		if (!inFile.exists() || !inFile.canRead()) {
			//System.out.println(inFile.getAbsolutePath());
			System.err
					.println("Input file doesn't exist or protected for reading!");
			System.exit(1);
		}

		if (!outDir.isDirectory() || !inFile.canWrite()) {
			System.err
					.println("Output dir doesn't exist or protected for writing!");
			System.exit(1);
		}

		pathOutputFile = outDir.getAbsolutePath() + File.separator
				+ inFile.getName();

		// System.out.println(pathOutputFile); //Check the output file path

		File outputFile = new File(pathOutputFile);

		// if file doesn't exist - create It!
		if (!outputFile.exists()) {
			try {
				outputFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			// if file already exist - check Write permissions
			if (!outputFile.canWrite()) {
				System.err
						.println("Output file is exist and protected for writing!");
				System.exit(1);
			} else if (!allfiles) {
				// if file already exist - ask user about overwriting

				for (int i = 0; i < 1;) {
					System.out
							.println("Owerwriting the current file: "
									+ outputFile.getName()
									+ "? (Y/N/ALL-to overwrite all/STOP - to abort programm)");
					System.out.flush();
					BufferedReader in = new BufferedReader(
							new InputStreamReader(System.in));
					String response = null;
					try {
						response = in.readLine();
					} catch (IOException e) {
						e.printStackTrace();
					}
					switch (response.toUpperCase()) {
					case "Y":
						i = 1;
						// only for test!
						System.out.println("File: " + outputFile.getName()
								+ " was overwritten!");

						break;

					case "N":
						i = 1;
						copyPermission = false;
						// only for test!
						System.out.println("File: " + outputFile.getName()
								+ " wasn't overwritten!");

						break;

					case "ALL":
						i = 1;
						allfiles = true;
						System.out.println("OK! All files will be overwritten!");

						break;

					case "STOP":
						System.out.println("Applications is stopped!");
						System.exit(1);

						break;

					default:
						// i=0;
						System.out.println("Wrong command!");
						break;
					}
				}
			}
		}

		if (copyPermission) {

			FileInputStream fis = null;
			FileOutputStream fos = null;
			FileChannel fcin = null;
			FileChannel fcout = null;
			try {
				fis = new FileInputStream(inFile);
				fos = new FileOutputStream(outputFile);
				fcin = fis.getChannel();
				fcout = fos.getChannel();

				fcin.transferTo(0, fcin.size(), fcout);

			} catch (IOException e) {
				e.printStackTrace();
			} finally {

				try {
					fcin.close();
					fcout.close();
					fis.close();
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}

		}

	}
}