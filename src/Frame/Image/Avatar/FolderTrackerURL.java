package Frame.Image.Avatar;

import java.io.File;

public class FolderTrackerURL{
        public static String folderTrackerURL() {
                Class<?> clazz = FolderTrackerURL.class;
                String classFilePath = clazz.getResource(clazz.getSimpleName() + ".class").getFile();
                File classFile = new File(classFilePath);
                File parentDir = classFile.getParentFile(); // récupère le dossier parent
                String parentDirPath = parentDir.getAbsolutePath(); // récupère le chemin absolu du dossier parent
                return parentDirPath;
        }
}
