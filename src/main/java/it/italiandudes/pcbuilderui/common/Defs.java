package it.italiandudes.pcbuilderui.common;

import it.italiandudes.pcbuilderui.PCBuilderUI;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Objects;

public final class Defs {

    // App File Name
    public static final String APP_FILENAME = "PCBuilderUI";

    // Charset
    public static final String DEFAULT_CHARSET = "UTF-8";

    // Jar App Position
    public static final String JAR_POSITION;
    static {
        try {
            JAR_POSITION = new File(PCBuilderUI.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getAbsolutePath();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    // Resources Location
    public static final class Resources {

        //Resource Getters
        public static URL get(@NotNull final String resourceConst) {
            return Objects.requireNonNull(PCBuilderUI.class.getResource(resourceConst));
        }
        public static InputStream getAsStream(@NotNull final String resourceConst) {
            return Objects.requireNonNull(PCBuilderUI.class.getResourceAsStream(resourceConst));
        }

        // SQL
        public static final class SQL {
            private static final String SQL_DIR = "/sql/";
            public static final String SQL_BUILD_SHEET = SQL_DIR + "build_sheet.sql";
        }
    }
}
