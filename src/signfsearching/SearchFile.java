package signfsearching;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class SearchFile {

	private static ArrayList<File> filelist = new ArrayList<File>();
	private static int n = 0;

	public static ArrayList<File> retArr(File dir) {
		if (!dir.exists()) {
			System.err.println("Input folder not found!");
			System.exit(1);
		}

		if (dir.isDirectory()) {
			File[] files = dir.listFiles();

			for (File file : files) {

				if (file.isDirectory()) {

					retArr(file);
				} else {
					filelist.add(file);

				}

			}

		}

		return filelist;
	}

	// method returns quantity of all files in all nested folders
	public static int searchFilesQuant(File dir) throws IOException {
		if (!dir.exists()) {
			System.err.println("File not found!");
			return -1;
		}
		if (dir.isDirectory()) {
			File[] files = dir.listFiles();

			for (File file : files) {

				if (file.isDirectory())

					searchFilesQuant(file);

				n++;

			}

		}
		return n;
	}
}
