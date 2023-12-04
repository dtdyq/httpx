package org.dyq.httpx.core.request;

import org.dyq.httpx.config.Config;
import org.dyq.httpx.core.ByteSlice;
import org.dyq.httpx.exception.FormParseException;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * 系统自带resolver，将所有文件报错到用户提供的目录,没有会尝试创建
 */
public class ToDirFileResolver implements MultipartFileResolver {
    private final Path dir;

    private FileInfo currentInfo;
    private OutputStream currOs;

    public ToDirFileResolver(String dir) {
        Path p = Paths.get(dir);
        if (!Files.exists(p)) {
            try {
                Files.createDirectories(p);
            } catch (IOException e) {
                throw new FormParseException("dir %s create error".formatted(dir));
            }
        }
        if (Files.exists(p) && Files.isDirectory(p)) {
            this.dir = p;
        } else {
            throw new FormParseException("dir %s not exist".formatted(dir));
        }
    }

    @Override
    public void start(FileInfo fileInfo) throws Throwable {
        currentInfo = fileInfo;
        String fn = fileInfo.filename();
        Path fp = dir.resolve(fn);
        this.currOs = new BufferedOutputStream(Files.newOutputStream(fp, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.CREATE), Config.curr().buffer().getRespSize());
    }

    @Override
    public void incoming(ByteSlice b) throws IOException {
        currOs.write(b.data(), b.pos(), b.available());
    }

    @Override
    public void finish() throws IOException {
        currOs.flush();
        currOs.close();
        currentInfo = null;
        currOs = null;
    }
}
