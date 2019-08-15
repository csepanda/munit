package csepanda.munit.launcher.loaders.utils;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.jar.JarFile;

public class URLClassLoaderHelper {
    /**
     * Transform file to url to jar file that is used in URLClassLoader.
     *
     * @param file JarFile url of which will be used in URLClassLoader.
     * @return jar:file URL to this file
     * @throws MalformedURLException
     */
    public static URL createURL(JarFile file) throws MalformedURLException {
        return new URL("jar:file:" + file.getName() + "!/");
    }

    public static URL createURL(File file) throws MalformedURLException {
        return file.toURI().toURL();
    }
}
