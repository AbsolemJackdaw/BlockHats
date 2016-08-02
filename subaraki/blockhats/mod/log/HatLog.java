package subaraki.blockhats.mod.log;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import subaraki.blockhats.mod.BlockHats;

public class HatLog{

	private static final Logger  log  = LogManager.getFormatterLogger(BlockHats.MODID);
	
	public static void printError(String s){
		log.error(s);
	}
	
	public static void printInfo(String s){
		log.info(s);
	}
}
