import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import net.minecraft.server.MinecraftServer;

public class OThreadCommandReader extends Thread {

    // hMod: Store server

    private MinecraftServer server;

    public OThreadCommandReader(MinecraftServer paramMinecraftServer) {
        // hMod: Actually store it
        server = paramMinecraftServer;
    }

    @Override
    public void run() {
        BufferedReader localBufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String str = null;
        try {
            // hMod: Parse all console commands
            while ((!server.g) && ((str = localBufferedReader.readLine()) != null))
                if (!etc.getInstance().parseConsoleCommand(str, server))
                    server.a(str, server);
        } catch (IOException localIOException) {
            localIOException.printStackTrace();
        }
    }
}
