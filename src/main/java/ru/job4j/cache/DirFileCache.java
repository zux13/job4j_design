package ru.job4j.cache;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;

public class DirFileCache extends AbstractCache<String, String> {

    private final String cachingDir;

    public DirFileCache(String cachingDir) {
        validatePath(Path.of(cachingDir));
        this.cachingDir = cachingDir;
    }

    private void validatePath(Path path) {
        if (!Files.exists(path)) {
            throw new CacheException("Файл или директория не существует: " + path);
        }
        if (!Files.isReadable(path)) {
            throw new CacheException("Нет прав на чтение файла или директории: " + path);
        }
    }

    @Override
    protected String load(String key) {
        var filePath = Paths.get(cachingDir, key);
        validatePath(filePath);
        try {
            return Files.readString(filePath);
        } catch (IOException e) {
            throw new CacheException("Ошибка чтения файла: " + filePath, e);
        }
    }
}