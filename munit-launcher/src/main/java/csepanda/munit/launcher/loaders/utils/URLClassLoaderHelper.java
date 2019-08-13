package csepanda.munit.launcher.loaders.utils;

import java.net.MalformedURLException;
import java.net.URL;

public class URLClassLoaderHelper {
    public static URL createURLForJar(String jarPath) throws MalformedURLException {
        return new URL("jar:file:" + jarPath + "!/");
    }
}
