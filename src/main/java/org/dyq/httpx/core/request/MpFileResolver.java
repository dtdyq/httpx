package org.dyq.httpx.core.request;

import org.dyq.httpx.core.ByteSlice;

import java.nio.charset.Charset;

/**
 * 客户端文件上传，服务器处理接口，对于客户端上传的每个文件
 * 都会调用start提供FileInfo
 * 反复调用incoming传输这个文件的数据
 * 最后调用finish表示当前文件结束
 */
public interface MpFileResolver {
    record FileInfo(String filename, String contentType, Charset charset) {
    }

    void start(FileInfo fileInfo) throws Throwable;

    void incoming(ByteSlice b) throws Throwable;

    void finish() throws Throwable;

    public static MpFileResolver toDir(String dir) {
        return new ToDirFileResolver(dir);
    }
}
