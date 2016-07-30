package subaraki.blockhats.mod;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

@Mod(modid = BlockHats.MODID, version = BlockHats.VERSION, name = BlockHats.NAME)
public class BlockHats
{

	public static final String MODID = "blockhats";
	public static final String VERSION = "1.0";
	public static final String NAME = "Block Hats";

	@EventHandler
	public void init(FMLInitializationEvent event)
	{

	}

	@EventHandler
	public void serverLoad(FMLServerStartingEvent event)
	{
		event.registerServerCommand(new HatCommands());
	}
}
