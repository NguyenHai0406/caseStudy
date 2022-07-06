package com.java.study.service;

import java.io.FileNotFoundException;

public interface MainService {
    void outputEmail(String csvPath, String jsonPath, String outputFolderPath) throws FileNotFoundException;
}
