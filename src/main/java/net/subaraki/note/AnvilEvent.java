package net.subaraki.note;

import net.minecraftforge.common.MinecraftForge;

public class AnvilEvent {

	public AnvilEvent() {
		MinecraftForge.EVENT_BUS.register(this);

	}

}
