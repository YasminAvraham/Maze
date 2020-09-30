package Client;


import java.io.InputStream;
import java.io.OutputStream;

/**
 * Interface to create ClientStrategy
 * @author  Yasmin Avraham & Neta Barel
 */
public interface IClientStrategy {
    void clientStrategy(InputStream inFromServer, OutputStream outToServer);
}
