package signfsearching;

import java.io.File;
import java.io.IOException;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

/*
 * ApkFilesChk Class - Android package Files Checker.
 * This Class implementing check - is a file is a ".apk".
 * 
 * Every APK file includes an AndroidManifest.xml file.
 * APK format is an extension of the Java JAR format, 
 * which in turn is an extension of the popular ZIP file format.
 * 
 * Algorithm of check file's produced by comparing name of nested files in archive-file with value of constant - FILENAMETEMPLATE. 
 * 
 * The method chk_include(...) will return true, if archive-file contain AndroidManifest.xml file.
 */

public class ApkFilesChk {
	private static final String FILENAMETEMPLATE = "AndroidManifest.xml";
	private static ZipFile zfile = null;

	public static boolean chk_include(File zipfile) throws IOException {
		if (!zipfile.exists()) {
			System.err.println("Zipfile doesn't exist!");
			System.exit(1);
		}
/***********************************************************/
// The ZipExeption might occur, if ZIP-file has a password for opening.
		
		try {
			zfile = new ZipFile(zipfile);
		} catch (ZipException e) {
//			System.out.println(zipfile.getAbsolutePath());
        	System.err.println("Can't read! Possibly, ZIP-file: " + zipfile.getAbsolutePath() + " - encrypted!!!");
			zfile.close();
			//e.printStackTrace();
			return false;
		}
/***********************************************************/		

		if (zfile.getEntry(FILENAMETEMPLATE) != null) {
			zfile.close();
			return true;
		} else {
			zfile.close();
			return false;
		}

	}

}
