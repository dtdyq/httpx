package org.dyq.httpx.util;

public enum Config {
    PORT(8080),//8080
    BACKLOG(1228),
    RECEIVE_BUFFER_SIZE(40960),//1024 接收缓冲区大小
    MULTIPART_FILE_MEMORY_MAX_SIZE(1024 * 1024 * 2),//multipart文件上传处理最大内存大小，超过写入临时文件
    RESP_CHUNKED_MIN_SIZE(1024 * 1024 * 4),//1024 * 1024 * 4 写出文件超过多大用chunked
    RESP_CHUNKED_PART_SIZE(2048);

    private Object value;

    Config(Object value) {
        this.value = value;
    }

    public <T> void set(T value) {
        this.value = value;
    }

    public int getInt() {
        if (value instanceof Integer i) {
            return i;
        }
        return Integer.parseInt(value.toString());
    }

    public long getLong() {
        if (value instanceof Long l) {
            return l;
        }
        return Long.parseLong(value.toString());
    }

    public String getStr() {
        if (value instanceof String s) {
            return s;
        }
        return value.toString();
    }
}
