package com.example.MyBookShopApp.dto;

import org.springframework.http.MediaType;


import java.nio.file.Path;

public class DownloadingBookDto {

    private Path path;

    private MediaType mediaType;

    private byte[] data;

    public DownloadingBookDto(Path path, MediaType mediaType, byte[] data) {
        this.path = path;
        this.mediaType = mediaType;
        this.data = data;
    }

    public DownloadingBookDto() {
    }

    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }

    public org.springframework.http.MediaType getMediaType() {
        return mediaType;
    }

    public void setMediaType(MediaType mediaType) {
        this.mediaType = mediaType;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
