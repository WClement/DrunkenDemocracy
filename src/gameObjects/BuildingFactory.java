package gameObjects;

import java.lang.reflect.InvocationTargetException;

public class BuildingFactory {

	public static Building createBuilding(BuildingProperties b) {
		try {
			return (Building) b.getMyClass().getConstructor(BuildingProperties.class).newInstance(b);
		} catch (InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
