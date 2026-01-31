package servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;

import javax.servlet.http.Part;

public class FilePart implements Part {
    private final File file;

    public FilePart(File file) {
        this.file = file;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return new FileInputStream(file);
    }

    @Override
    public String getContentType() {
        return "text/csv";
    }

    @Override
    public String getName() {
        return file.getName();
    }

    @Override
    public String getSubmittedFileName() {
        return file.getName();
    }

    @Override
    public long getSize() {
        return file.length();
    }

    @Override
    public void write(String fileName) throws IOException {
        // ファイルのコピー処理
        try (InputStream in = new FileInputStream(file)) {
            java.nio.file.Files.copy(in, new File(fileName).toPath(), java.nio.file.StandardCopyOption.REPLACE_EXISTING);
        }
    }

    @Override
    public void delete() throws IOException {
        // ファイル削除は安全性のため無効化
        System.out.println("delete() は無効化されています。");
    }

    @Override
    public String getHeader(String name) {
        return null; // ヘッダー情報が不要なためnull
    }

    @Override
    public java.util.Collection<String> getHeaders(String name) {
        return Collections.emptyList();
    }

    @Override
    public java.util.Collection<String> getHeaderNames() {
        return Collections.emptyList();
    }
}
