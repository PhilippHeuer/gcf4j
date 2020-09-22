package com.github.philippheuer.gcf4j.core.domain;

import com.github.philippheuer.gcf4j.api.domain.IGCFAttachmentType;
import com.github.philippheuer.gcf4j.api.domain.IGCFMessageAttachment;
import lombok.*;
import okhttp3.OkHttpClient;
import okhttp3.Request;

import java.io.InputStream;

@AllArgsConstructor
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@Builder
public class GCFMessageAttachment implements IGCFMessageAttachment {

    private static OkHttpClient httpClient = new OkHttpClient();

    /**
     * {@inheritDoc}
     */
    @Getter
    private IGCFAttachmentType type;

    /**
     * {@inheritDoc}
     */
    @Getter
    private String name;

    /**
     * {@inheritDoc}
     */
    private byte[] file;

    /**
     * {@inheritDoc}
     */
    @Getter
    private String url;

    /**
     * {@inheritDoc}
     */
    public byte[] getFile() {
        // download if we got a url and the bytes are not present yet
        if (url != null && file == null) {
            // download attachment
            try {
                var request = new Request.Builder().url(url).build();
                var response = httpClient.newCall(request).execute();

                InputStream is = response.body().byteStream();
                file = is.readAllBytes();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        return file;
    }

}
