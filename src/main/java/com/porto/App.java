package com.porto;

import java.io.File;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.WinDef.UINT_PTR;
import com.sun.jna.win32.StdCallLibrary;
import com.sun.jna.win32.W32APIFunctionMapper;
import com.sun.jna.win32.W32APITypeMapper;

public class App {

    public interface SPI extends StdCallLibrary {

        long SPI_SETDESKWALLPAPER = 20;
        long SPIF_UPDATEINIFILE = 0x01;
        long SPIF_SENDWININICHANGE = 0x02;

        @SuppressWarnings("serial")
        SPI INSTANCE = (SPI) Native.loadLibrary("user32", SPI.class, new HashMap<Object, Object>() {
                    {
                        put(OPTION_TYPE_MAPPER, W32APITypeMapper.UNICODE);
                        put(OPTION_FUNCTION_MAPPER, W32APIFunctionMapper.UNICODE);
                    }
                });
        boolean SystemParametersInfo(UINT_PTR uiAction, UINT_PTR uiParam, String pvParam, UINT_PTR fWinIni);
    }

    public static void main(String[] args) {
        while(true){
            try{
                ExtratorDeJsonDeApi api_consumer = new ExtratorDeJsonDeApi();
                String json = api_consumer.extrairJson("https://api.nasa.gov/planetary/apod"); // https://api.nasa.gov/planetary/apod?api_key + YOUR API KEY

                Gson gson = new Gson();
                Type tipoMap = new TypeToken<Map<String, String>>(){}.getType();
                Map<String, String> jsonNasa = gson.fromJson(json, tipoMap); //json mapeado em uma variável Map<String, String>
                
                File file = new File("images/");
                if(!file.exists()){
                    file.mkdir();
                }
                DeletaDiretorio.deletaDiretorio(file);

                GeradorDeImagemFotoNasa gerador = new GeradorDeImagemFotoNasa();
                gerador.gerarImagem(ImageIO.read(new URL(jsonNasa.get("url"))), "jpg", new File("images/" + jsonNasa.get("title") + ".jpg"));

                String curDir = System.getProperty("user.dir");
                String filename = curDir + "\\images\\" + jsonNasa.get("title") + ".jpg";

                SPI.INSTANCE.SystemParametersInfo(new UINT_PTR(SPI.SPI_SETDESKWALLPAPER), new UINT_PTR(0), filename, new UINT_PTR(SPI.SPIF_UPDATEINIFILE | SPI.SPIF_SENDWININICHANGE));

                Thread.sleep(3600000);

            } catch(Exception ex){
                ex.printStackTrace();
            }
        }
    }
}   