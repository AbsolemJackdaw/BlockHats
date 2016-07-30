package subaraki.blockhats.mod;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemBlockSpecial;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import subaraki.blockhats.mod.log.HatLog;

public class HatCommands implements ICommand {

	private List aliases;

	public HatCommands()
	{
		this.aliases = new ArrayList();
		this.aliases.add("hat");
		this.aliases.add("ha");
		this.aliases.add("h");
		this.aliases.add("at");
	}

	@Override
	public int compareTo(ICommand arg0) {
		return 0;
	}

	@Override
	public String getCommandName() {
		return "hat";
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		return "Requieres a block in main hand";
	}

	@Override
	public List<String> getCommandAliases() {
		return this.aliases;
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {

		Entity e = sender.getCommandSenderEntity();

		if(e instanceof EntityLivingBase){
			EntityLivingBase elb = (EntityLivingBase)e;

			ItemStack s = 
					(elb.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND));

			//if stack is null, will throw an exception
			if(s == null){
				throw new CommandException("/"+ "!" + "\\ " +  "Empty Hand !", new Object[]{"emptyhand"});
			}

			//get a copy from the held stack
			ItemStack stack = s.copy();

			//			if(stack.getItem() instanceof ItemBlock || stack.getItem() instanceof ItemBlockSpecial){

			//get head
			ItemStack head = elb.getItemStackFromSlot(EntityEquipmentSlot.HEAD);

			//get new head
			ItemStack newHead = stack.copy();
			newHead.stackSize = 1;

			if(head == null){
				//set copy from head with stacksize 1 to head
				elb.setItemStackToSlot(EntityEquipmentSlot.HEAD, newHead);
				//refresh held item by removing stacksize, and setting the copied stack to the entity's hand
				stack.stackSize--;
				elb.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, stack.stackSize > 0 ? stack : null);
			}
			else
				if(elb instanceof EntityPlayer){
					EntityPlayer player = (EntityPlayer)elb;
					//if there's no place left in the player's inventory
					if(!player.inventory.addItemStackToInventory(head)){
						throw new CommandException("/"+ "!" + "\\ " +"Inventory Full !", new Object[]{"fullInventory"});
					}else{
						//set copy from head with stacksize 1 to head
						player.setItemStackToSlot(EntityEquipmentSlot.HEAD, newHead);
						//refresh held item by removing stacksize, and setting the copied stack to the entity's hand
						stack.stackSize--;
						elb.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, stack.stackSize > 0 ? stack : null);
					}
				}
		}
		//		}
	}

	@Override
	public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
		return true;
	}

	@Override
	public List<String> getTabCompletionOptions(MinecraftServer server, ICommandSender sender, String[] args,
			BlockPos pos) {
		return null;
	}

	@Override
	public boolean isUsernameIndex(String[] args, int index) {
		return false;
	}
}
