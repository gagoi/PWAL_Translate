package fr.gagoi.pwal_translate.lang;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class TranslationFile {

	private String lang_code, directory;
	private HashMap<String, String> texts;

	public TranslationFile(String lang_code, String directory) {
		texts = new HashMap<>();
		this.lang_code = lang_code;
		this.directory = directory;
		load();
	}

	private void load() {
		String line = "";
		int lineNb = 0;
		try {
			BufferedReader reader = new BufferedReader(new FileReader(directory + "/" + lang_code + ".lang"));
			while ((line = reader.readLine()) != null) {
				lineNb++;
				try {
					if (!line.startsWith("#") && !line.isEmpty()) {
						String[] splitted = line.split("=");
						texts.put(splitted[0], splitted[1]);
					}
				} catch (Exception e) {
					System.err.println("Error whith line " + lineNb + " : " + line);
				}
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getTranslationOf(String key, String defaultValue) {
		return texts.getOrDefault(key, defaultValue);
	}

}
