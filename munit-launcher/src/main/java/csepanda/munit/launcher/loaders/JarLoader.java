package csepanda.munit.launcher.loaders;

import csepanda.munit.launcher.loaders.utils.FileNameUtils;
import csepanda.munit.launcher.loaders.utils.URLClassLoaderHelper;

import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.jar.JarFile;

public class JarLoader implements ILoader {
    private final JarFile jarFile;
    private final URLClassLoader classLoader;

    public JarLoader(String path) throws IOException {
        this.jarFile = new JarFile(path);
        this.classLoader = URLClassLoader.newInstance(new URL[]{
                URLClassLoaderHelper.createURLForJar(path)
        });
    }

    @Override
    public Iterable<Class<?>> load() throws ClassNotFoundException {
        var entries = jarFile.entries();
        var list = new ArrayList<Class<?>>();

        while (entries.hasMoreElements()) {
            var entry = entries.nextElement();
            var entryName = entry.getName();

            if (entry.isDirectory() || !FileNameUtils.isClass(entryName)) {
                continue;
            }

            var className = FileNameUtils.convertToClassName(entryName);

            list.add(this.classLoader.loadClass(className));
        }

        return list;
    }
}
