package com.nhsbsa.utils;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PathUtil {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy_mm_dd_HH_mm_ss_S");
    private final Path parentRunPath=  Paths.get(FileSystems.getDefault().getPath("").toAbsolutePath().toString(),"target", "screenshots");
    private final Path runPath= Paths.get(
        parentRunPath.toString(), LocalDateTime.now().format(formatter));
    
    public DateTimeFormatter getScreenShotFormat(){
        return formatter;
    }
    public Path getRunRootDirectory(){
        createDirectory(parentRunPath);
        createDirectory(runPath);
        return runPath;
    }
    public Path getScreenShotPath(Path resultRoot,String scenarioName){
        Path screenShotPath=Paths.get(resultRoot.toString(),scenarioName);
        createDirectory(screenShotPath);
        return screenShotPath;
    }
    public void createDirectory(Path path) {
        try {
            if (!Files.isDirectory(path)) {
                System.out.println("Creating Directory : " + path.toString());
                Files.createDirectory(path);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
