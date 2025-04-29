package com.cumpleanos.mongo.utils;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageUtils {

    /**
     * Metodo para cambiar el tamaño de la imagen preservando la relación de aspecto
     * @param originalImage el archivo que contiene la imagen
     * @param maxWidth altura maximo de la imagen
     * @param maxHeight ancho maximo de la imagen
     * @return BufferedImage con la nueva version
     */
    public static BufferedImage resizeImagePreservingAspectRatio(BufferedImage originalImage, int maxWidth, int maxHeight) {
        int originalWidth = originalImage.getWidth();
        int originalHeight = originalImage.getHeight();

        double widthRatio = (double) maxWidth / originalWidth;
        double heightRatio = (double) maxHeight / originalHeight;
        double scaleFactor = Math.min(widthRatio, heightRatio); // Mantener proporción

        int newWidth = (int) (originalWidth * scaleFactor);
        int newHeight = (int) (originalHeight * scaleFactor);

        BufferedImage outputImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = outputImage.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.setComposite(AlphaComposite.Src); // Soporte para transparencia
        g2d.drawImage(originalImage, 0, 0, newWidth, newHeight, null);
        g2d.dispose();

        return outputImage;
    }
}


