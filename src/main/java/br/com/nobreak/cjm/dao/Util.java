package br.com.nobreak.cjm.dao;

import java.util.Base64;

import javax.imageio.ImageIO;
//import javax.servlet.http.Part;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;

public class Util {

    public static String[] encode(byte[] img) {
        Base64.Encoder base64Encoder = Base64.getEncoder();
        String[] arrayImagem = new String[1];
        arrayImagem[0] = base64Encoder.encodeToString(img);
        return arrayImagem;
    }

    public static byte[] decode (String img) {
        Base64.Decoder base64Decoder = Base64.getDecoder();
        return base64Decoder.decode(img);
    }

    public static String prepareImagem(String url) {
        String imageString = null;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        BufferedImage arqRedacaoOrigin = null;
        try{
            arqRedacaoOrigin = ImageIO.read(new File(url));

        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            ImageIO.write(arqRedacaoOrigin, "png", byteArrayOutputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*aqui*/
        byte[] imageAsByte = byteArrayOutputStream.toByteArray();
        return Base64.getEncoder().encode(imageAsByte).toString();
    }

}
