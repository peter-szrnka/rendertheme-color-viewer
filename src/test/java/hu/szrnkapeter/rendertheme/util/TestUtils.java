package hu.szrnkapeter.rendertheme.util;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TestUtils {

	public static <S> void testPrivateConstructor(Class<S> singletonClass) throws SecurityException,
			NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
		final Constructor<S> constructor = singletonClass.getDeclaredConstructor();
		constructor.setAccessible(true);
		constructor.newInstance();
		constructor.setAccessible(false);
	}

	public static String getFileContent(String filePath) throws IOException {
		return new String(Files.readAllBytes(Paths.get(filePath)));
	}
}