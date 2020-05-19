package cn.szag.oms.manager.common.utils.sequence;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Loader {
	private static final Log log = LogFactory.getLog(Loader.class.getName());
	private static final String TSTR = "Caught Exception while in Loader.getResource. This may be innocuous.";

	private static ClassLoader getTCL() throws IllegalAccessException,
			InvocationTargetException {
		Method method = null;
		try {
			method = Thread.class.getMethod("getContextClassLoader", null);
		} catch (NoSuchMethodException e) {
			return null;
		}
		return (ClassLoader) method.invoke(Thread.currentThread(), null);
	}

	public static Class loadClass(String clazz) throws ClassNotFoundException {
		try {
			return getTCL().loadClass(clazz);
		} catch (Throwable e) {
		}
		return Class.forName(clazz);
	}

	public static URL getResource(String resource) {
		ClassLoader classLoader = null;
		URL url = null;
		try {
			classLoader = getTCL();
			if (classLoader != null) {
				log.debug("Trying to find [" + resource
						+ "] using context classloader " + classLoader + ".");
				url = classLoader.getResource(resource);
				if (url != null) {
					return url;
				}
			}
			classLoader = Loader.class.getClassLoader();
			if (classLoader != null) {
				log.debug("Trying to find [" + resource + "] using "
						+ classLoader + " class loader.");
				url = classLoader.getResource(resource);
				if (url != null) {
					return url;
				}
			}
		} catch (Throwable t) {
			log.warn(
					"Caught Exception while in Loader.getResource. This may be innocuous.",
					t);
		}
		log.debug("Trying to find [" + resource
				+ "] using ClassLoader.getSystemResource().");
		return ClassLoader.getSystemResource(resource);
	}
}