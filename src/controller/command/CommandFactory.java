package controller.command;

import java.lang.reflect.InvocationTargetException;

public class CommandFactory {
	
	public ChatCommand createCommand(String suffix, CommandEnum myEnum, Class<?> theClass) {
		try {
			return (ChatCommand) theClass.getConstructor(String.class, CommandEnum.class).newInstance(suffix, myEnum);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
