package controllers.parsers;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ObjectParser {

		public static void writeObject(String filepath, Object object) {
			FileOutputStream fout = null;
			ObjectOutputStream oos = null;
			
			try {
				fout = new FileOutputStream(filepath);
				oos = new ObjectOutputStream(fout);
				oos.writeObject(object);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (fout != null) {
					try {
						fout.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if (oos != null) {
					try {
						oos.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		
		public static Object readObject(String filepath) {
			FileInputStream fin = null;
			ObjectInputStream ois = null;
			Object obj = null;
			try {
				fin = new FileInputStream(filepath);
				ois = new ObjectInputStream(fin);
				obj = ois.readObject();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (fin != null) {
					try {
						fin.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if (ois != null) {
					try {
						ois.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			return obj;
		}
}
