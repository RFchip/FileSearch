package signfsearching;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class FileReadByte {
	private static int QUANTITY = 4;
	public static byte fReadByte(File f, int bytePosition) throws IOException {
		
		byte arrByte[] = new byte[QUANTITY];
		// int i = 0;
		// byte b = 0;
		//File f = file;
		//byte headerByte;
		// hArrays = null;
		// File f = new File("C:\\test1.txt");
		if (f.exists() && (f.canRead())) {
			
			RandomAccessFile raf = new RandomAccessFile(f, "r");

			if (QUANTITY < f.length()) {

				for (int i = 0; i < QUANTITY; i++) {
					arrByte[i] = raf.readByte();
					//System.out.println(arrByte[i]);
					// b=raf.readByte();
				}
			} else {
				// only for test!
//				System.out
//						.println("The Quantity of reading bytes more than File length!");
			}
			
			raf.close();

			return arrByte[bytePosition];

		} else {
			System.out.println("File doesn't EXIST!");
			return -1;
		}
	}

}
