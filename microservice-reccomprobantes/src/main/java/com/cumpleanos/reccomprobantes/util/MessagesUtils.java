package com.cumpleanos.reccomprobantes.util;

import java.util.List;
import java.util.Map;

public class MessagesUtils {

    public static String mensajeHtmlCamposNulosClientes(List<Map<String, String>> clientes, String empresa, String logoSrc) {
        StringBuilder htmlBuilder = new StringBuilder();

        if (logoSrc != null) {
            htmlBuilder.append("<div style='text-align: center;'>")
                    .append("<img src='").append(logoSrc)
                    .append("' alt='Logo de la empresa' style='max-width: 120px; max-height: 120px; display: block; margin: auto;'>")
                    .append("</div>");
        }

        // Comenzar la tabla HTML
        htmlBuilder.append(String.format(
                "<div style='font-family: Arial, sans-serif; padding: 20px; background: #f9f9f9;'>"
                        + "<div style='background: #ffffff; border-radius: 10px; box-shadow: 0 2px 8px rgba(0,0,0,0.1); padding: 20px;'>"
                        + "<h2 style='color: #4a4a4a;'>Registro de proveedores nuevos registrados en: <span style='color: #007BFF;'>%s</span></h2>", empresa));

        htmlBuilder.append(
                "<table style='width: 100%; border-collapse: collapse; margin-top: 15px;'>"
                        + "<thead>"
                        + "<tr style='background-color: #007BFF; color: white;'>"
                        + "<th style='padding: 10px; border: 1px solid #ddd;'>Proveedor</th>"
                        + "<th style='padding: 10px; border: 1px solid #ddd;'>RUC/Cédula</th>"
                        + "</tr>"
                        + "</thead>"
                        + "<tbody>");

        for (Map<String, String> cliente : clientes) {
            htmlBuilder.append(String.format(
                    "<tr style='background-color: #f2f2f2;'>"
                            + "<td style='padding: 10px; border: 1px solid #ddd;'>%s</td>"
                            + "<td style='padding: 10px; border: 1px solid #ddd;'>%s</td>"
                            + "</tr>",
                    cliente.get("Nombre"),
                    cliente.get("RUC/Cedula")));
        }

        htmlBuilder.append("</tbody></table></div></div>");

        return htmlBuilder.toString();
    }

}
