package com.porto;

import java.io.File;

public class DeletaDiretorio {
    
    public static void deletaDiretorio(File file){
        for (File subarq : file.listFiles()) {
            subarq.delete();
        }
    }
}
