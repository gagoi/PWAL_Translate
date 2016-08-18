package fr.gagoi.pwal_translate.io;

import java.io.File;
import java.util.ArrayList;

import fr.gagoi.pwal_translate.lang.TranslationFile;

public class TranslationLoader implements Runnable {

	private String name, directory;
	private ArrayList<String> avaibleLang = new ArrayList<>();

	private Thread t;

	public TranslationLoader(String name, String directory) {
		this.name = name;
		this.directory = directory;
		t = new Thread(this, name);
	}

	public void load() {
		t.start();
	}

	@Override
	public void run() {
		System.out.println("Loader : " + this.name);
		System.out.println("Start loading translations...");
		System.out.println("Searching for avaible translations...");
		long time = System.currentTimeMillis();
		File dir = new File(directory);
		File[] files = dir.listFiles();
		for (int i = 0; i < files.length; i++) {
			File file = files[i];
			System.out.print("File " + i + " of " + files.length + " checked...");
			if (file.getName().endsWith(".lang")) {
				avaibleLang.add(file.getName().replaceAll(".lang", ""));
				System.out.println(" and seems to be avaible. (" + avaibleLang.get(avaibleLang.size() - 1) + ")");
			} else
				System.out.println(" and seems to be unavaible.");
		}
		System.out.println("Translations's loading finish (" + (System.currentTimeMillis()-time) + "ms)");
	}

	public TranslationFile getTranslationFile(String lang){
		return new TranslationFile(lang, directory);
	}
}
