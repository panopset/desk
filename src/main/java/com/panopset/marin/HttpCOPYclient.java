package com.panopset.marin;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.URL;
import com.panopset.compat.Logop;

/**
 * HTTP Copy client.
 *
 * @author Karl Dinwiddie.
 *
 */
public final class HttpCOPYclient {

    /**
     * HTTP client.
     */
    private final HttpGETclient client;

    /**
     *
     * @param newURL
     *            URL to copy from.
     */
    public HttpCOPYclient(final URL newURL) {
        client = new HttpGETclient(newURL);
    }

    /**
     * Copy HTTP stream to file.
     *
     * @param f
     *            File to copy stream to.
     */
    public void copy(final File f) {
        f.getParentFile().mkdirs();
        try (InputStreamReader isr = new InputStreamReader(client
                .getConnection().getInputStream());
                BufferedReader br = new BufferedReader(isr);
                FileWriter fw = new FileWriter(f);
                BufferedWriter bw = new BufferedWriter(fw)) {
            int c = br.read();
            while (c != -1) {
                bw.write(c);
                c = br.read();
            }
        } catch (Exception ex) {
            Logop.error(ex);
        }
    }
}
