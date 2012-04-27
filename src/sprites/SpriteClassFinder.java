package sprites;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class SpriteClassFinder {

	ArrayList<Class<?>> myClasses;

	public SpriteClassFinder(){
		myClasses = getClassesForPackage(Package.getPackage("sprites"));
	}
	
	public ArrayList<Class<?>> getClasses(){
		return myClasses;
	}
	
	private static ArrayList<Class<?>> getClassesForPackage(Package pkg) {

		String pkgname = pkg.getName();
		ArrayList<Class<?>> classes = new ArrayList<Class<?>>();
		File directory = null;
		String fullPath;
		String relPath = pkgname.replace('.', '/');
		java.net.URL resource = ClassLoader.getSystemClassLoader().getResource(
				relPath);

		if (resource == null) {
			throw new RuntimeException("No resource for " + relPath);
		}

		fullPath = resource.getFile();

		try {
			directory = new File(resource.toURI());
		} catch (URISyntaxException e) {
			throw new RuntimeException(
					pkgname
					+ " ("
					+ resource
					+ ") does not appear to be a valid URL / URI.  Strange, since we got it from the system...",
					e);

		} catch (IllegalArgumentException e) {
			directory = null;
		}

		if (directory != null && directory.exists()) {

			String[] files = directory.list();

			for (int i = 0; i < files.length; i++) {
				if (files[i].endsWith(".class")) {
					String className = pkgname + '.'
							+ files[i].substring(0, files[i].length() - 6);
					try {
						classes.add(Class.forName(className));
					} catch (ClassNotFoundException e) {
						throw new RuntimeException(
								"ClassNotFoundException loading " + className);
					}
				}
			}
		} else {
			try {
				String jarPath = fullPath.replaceFirst("[.]jar[!].*", ".jar")
						.replaceFirst("file:", "");
				JarFile jarFile = new JarFile(jarPath);
				Enumeration<JarEntry> entries = jarFile.entries();
				while (entries.hasMoreElements()) {
					JarEntry entry = entries.nextElement();
					String entryName = entry.getName();
					if (entryName.startsWith(relPath)
							&& entryName.length() > (relPath.length() + "/"
									.length())) {
						System.out.println("ClassDiscovery: JarEntry: "
								+ entryName);
						String className = entryName.replace('/', '.')
								.replace('\\', '.').replace(".class", "");
						try {
							classes.add(Class.forName(className));
						} catch (ClassNotFoundException e) {
							throw new RuntimeException(
									"ClassNotFoundException loading "
											+ className);
						}
					}
				}

			} catch (IOException e) {

				throw new RuntimeException(pkgname + " (" + directory
						+ ") does not appear to be a valid package", e);
			}
		}
		return classes;
	}
	
}
