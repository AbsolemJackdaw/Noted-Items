package net.subaraki.note.config;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class Config {

	public int inkPerStack;
	public boolean shouldShow;

	public static Config instance= new Config();


	public void loadConfig(File file){
		Configuration config = new Configuration(file);
		config.load();
		loadSettings(config);
		config.save();
	}

	private void loadSettings(Configuration config)
	{
		config.addCustomCategoryComment("1-Ink Consumed Per Stack", "range from 1 to 7");

		this.inkPerStack = config.get("1-Ink Consumed Per Stack", "ink", 1).getInt(1);

		config.addCustomCategoryComment("2-Draw Amount in gui", "true or false");
		this.shouldShow = config.get("2-Draw Amount in gui", "draw ink requiered", true).getBoolean(true);

	}

}
