package org.dyq.httpx.core.request;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.Path;

@Data
@Accessors(fluent = true)
public class MultipartFile {
    private String filename;
    private Charset charset;
    private boolean memory;
    private byte[] data;
    private Path tempFile;
}
