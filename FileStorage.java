/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package files;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class FileStorage {
    private Path dataDir;

    public FileStorage(String dataDirectory) {
        this.dataDir = Paths.get(dataDirectory);
        try { if (!Files.exists(dataDir)) Files.createDirectories(dataDir); } 
        catch (IOException e) { e.printStackTrace(); }
    }

    public List<String> readLines(String filename) {
        List<String> lines = new ArrayList<>();
        Path p = dataDir.resolve(filename);
        if (!Files.exists(p)) return lines;
        try (BufferedReader br = Files.newBufferedReader(p)) {
            String line; while ((line = br.readLine()) != null) if (!line.trim().isEmpty()) lines.add(line);
        } catch (IOException e) { e.printStackTrace(); }
        return lines;
    }

    public boolean writeLines(String filename, List<String> lines) {
        Path p = dataDir.resolve(filename);
        try (BufferedWriter bw = Files.newBufferedWriter(p)) {
            for (String line : lines) { bw.write(line); bw.newLine(); }
            return true;
        } catch (IOException e) { e.printStackTrace(); return false; }
    }

    public boolean appendLine(String filename, String line) {
        Path p = dataDir.resolve(filename);
        try (BufferedWriter bw = Files.newBufferedWriter(p, StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
            bw.write(line); bw.newLine(); return true;
        } catch (IOException e) { e.printStackTrace(); return false; }
    }
}

