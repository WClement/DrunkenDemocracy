package gameObjects;

import java.lang.reflect.InvocationTargetException;

public class UnitFactory {

	public static Unit createUnit(UnitProperties u) {
		try {
			return (Unit) u.myUnitClass.getConstructor(UnitProperties.class).newInstance(u);
		} catch (InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
