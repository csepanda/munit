package csepanda.munit.launcher.loaders.utils;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.jar.JarFile;

public class URLClassLoaderHelper {
    public static URL createURLForJar(String jarPath) throws MalformedURLException {
        return new URL("jar:file:" + jarPath + "!/");
    }

    public static URL createURL(JarFile file) throws MalformedURLException {
        return new URL("jar:file:" + file.getName() + "!/");
    }

    public static URL createURL(File file) throws MalformedURLException {
        return file.toURI().toURL();
    }
}
