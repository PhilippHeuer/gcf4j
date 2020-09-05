package com.github.philippheuer.gcf4j.api.domain;

/**
 * A message attachment either contains the bytes or a url to download the attachment
 */
public interface IGCFMessageAttachment {

    /**
     * Name of the attachment
     */
    String getName();

    /**
     * Attached file (bytes)
     */
    byte[] getFile();

    /**
     * Gets the url used to download the attachment
     */
    String getUrl();

}
