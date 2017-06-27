package realmbase.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;

public class UtilFile {
	public static FileTime creationTime(File file){
		try {
			BasicFileAttributes attr = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
			return attr.creationTime();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
