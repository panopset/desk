package com.panopset.marin;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import com.panopset.compat.Logop;

/**
 * HTTP POST client.
 */
public final class HttpPOSTclient extends HttpClientAbstract {

    @Override
    protected void setConnectionProperties(final HttpURLConnection con) {
        try {
            con.setRequestMethod("POST");
        } catch (ProtocolException e) {
            Logop.error(e);
            throw new RuntimeException(e);
        }
        con.setRequestProperty("User-Agent", "Mozilla/4.0");
        con.setRequestProperty("Content-Type",
                "application/x-www-form-urlencoded");
        con.setDoInput(true);
        con.setDoOutput(true);
        con.setUseCaches(false);
    }

    /**
     * @param urlString
     *            URL to connect to.
     */
    public HttpPOSTclient(final String urlString) {
        super(urlString);
    }


    /**
     * @param url
     *            URL.
     */
    public HttpPOSTclient(final URL url) {
        super(url);
    }

    /**
     * Get text from POST.
     *
     * @param dta
     *            Data to post.
     * @return Result from HTTP stream.
     */
    public String getTextFromPost(final String dta) {
        try (
            InputStream ins = getConnection().getInputStream();
            InputStreamReader isr = new InputStreamReader(ins);
            OutputStreamWriter writer = new OutputStreamWriter(getConnection().getOutputStream());
            BufferedReader in = new BufferedReader(isr)) {
            writer.write(dta);
            writer.flush();
            StringWriter sw = new StringWriter();
            String s = in.readLine();
            while (s != null) {
                sw.append(s);
                s = in.readLine();
            }
            in.close();
            getConnection().getResponseCode();
            return sw.toString();
        } catch (Exception ex) {
            Logop.error(ex);
            return ex.getMessage();
        }
    }
}
