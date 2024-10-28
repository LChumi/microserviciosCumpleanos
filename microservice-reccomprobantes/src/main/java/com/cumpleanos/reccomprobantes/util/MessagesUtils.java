package com.cumpleanos.reccomprobantes.util;

import java.util.List;
import java.util.Map;

public class MessagesUtils {

    public static String mensajeHtmlCamposNulosClientes(List<Map<String, String>> clientes, String empresa) {
        StringBuilder htmlBuilder = new StringBuilder();

        // Comenzar la tabla HTML
        htmlBuilder.append(String.format("<span>Registro de proveedores nuevos registrados en: %s </span>",empresa));
        htmlBuilder.append("<table border='1' style='border-collapse:collapse; font-family: Arial'><tr><th>Proveedor</th><th>RUC/Cédula</th></tr>");

        for (Map<String, String> cliente : clientes) {
            htmlBuilder.append(String.format(
                    "<tr><td>%s</td><td>%s</td></tr>",
                    cliente.get("Nombre"),
                    cliente.get("RUC/Cedula")));
        }

        // Cerrar la tabla HTML
        htmlBuilder.append("</table>");

        return htmlBuilder.toString();
    }

}
