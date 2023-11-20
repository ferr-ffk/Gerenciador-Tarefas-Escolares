package br.com.ifsp.nando.gerenciadortarefasescolares.util;

import javafx.scene.paint.Color;

public class ColorUtil {

    /**
     * Retorna a string em hexadecimal da cor
     *
     * @param cor A cor fornecida
     * @return A string correspondente da cor e.g. "#FFFFFF"
     */
    public static String paraHexString(Color cor) {
        int r = ((int) Math.round(cor.getRed() * 255)) << 24;
        int g = ((int) Math.round(cor.getGreen() * 255)) << 16;
        int b = ((int) Math.round(cor.getBlue() * 255)) << 8;
        int a = ((int) Math.round(cor.getOpacity() * 255));

        return String.format("#%08X", (r + g + b + a));
    }

    /**
     * Retorna a cor à partir da string fornecida
     *
     * @param hex e.g. "#FFFFFF"
     * @return A cor à partir da string fornecida
     */
    public static Color hexParaRGB(String hex) {
        return new Color(
                 (double) (Integer.valueOf(hex.substring(1, 3), 16)) / 255,
                (double) (Integer.valueOf(hex.substring(3, 5), 16)) / 255,
                (double) (Integer.valueOf(hex.substring(5, 7), 16)) / 255,
                1);
    }
}
