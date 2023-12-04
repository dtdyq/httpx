## 所有的http说明
#### 1. transfer-encoding是chunked 请求数据样例:
```
POST /test HTTP/1.1
Host: www.example.com
Content-Type: text/plain
Transfer-Encoding: chunked

7\r\n
Mozilla\r\n
9\r\n
Developer\r\n
7\r\n
Network\r\n
0\r\n
\r\n
```
#### 2. transfer-encoding是chunked请求 上传多个文件:
 注意:即使是使用的Content-Length，boundary还是存在的,只是chunked需要忽略数据长度字段
```
POST /upload HTTP/1.1
Host: www.example.com
Transfer-Encoding: chunked
Content-Type: multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW

7
------WebKitFormBoundary7MA4YWxkTrZu0gW
1A
Content-Disposition: form-data; name="file1"; filename="file1.jpg"
Content-Type: image/jpeg

(file data)
7
------WebKitFormBoundary7MA4YWxkTrZu0gW
1A
Content-Disposition: form-data; name="file2"; filename="file2.jpg"
Content-Type: image/jpeg

(file data)
7
------WebKitFormBoundary7MA4YWxkTrZu0gW--
0
```
