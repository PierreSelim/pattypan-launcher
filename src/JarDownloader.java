import java.io.FileOutputStream;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;


class JarDownloader {

    private static String DEFAULT_URL = "https://github.com/yarl/pattypan/releases/download/v19.06/pattypan.jar";

    public static void main(String ... argv) {
        String pattypanURL = null;
        if (argv.length == 1) {
            pattypanURL = argv[0];
        } else {
            pattypanURL = getDefaultURL();
        }
        System.out.println(pattypanURL);

        try {
            ReadableByteChannel rbc = Channels.newChannel(new URL(pattypanURL).openStream());
            FileOutputStream fos = new FileOutputStream("pattypan-dl.jar");
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getDefaultURL() {
        String envVariable = System.getenv("PATTYPAN_JAR_URL");
        return envVariable != null ? envVariable : DEFAULT_URL;
    }
}
