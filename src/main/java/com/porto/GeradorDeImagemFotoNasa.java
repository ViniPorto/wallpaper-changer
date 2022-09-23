package com.porto;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class GeradorDeImagemFotoNasa {

    public void gerarImagem(BufferedImage image, String formato, File file){
        try{
            ImageIO.write(image, formato, file);
        }catch(IOException ex){
            throw new RuntimeException(ex);
        }
        
    }
}