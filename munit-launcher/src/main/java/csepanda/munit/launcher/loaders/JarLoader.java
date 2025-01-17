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

    public JarLoader(JarFile file) throws IOException {
        this.jarFile = file;
        this.classLoader = URLClassLoader.newInstance(new URL[]{
                URLClassLoaderHelper.createURL(file)
        });
    }

    @Override
    public Iterable<Class<?>> load() throws ClassNotFoundException {
        var entries = jarFile.entries();
        var list = new ArrayList<Class<?>>();

        // iterate over all files inside jar and load if class file was found
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
