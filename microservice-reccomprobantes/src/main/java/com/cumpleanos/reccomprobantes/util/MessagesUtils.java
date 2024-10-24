package com.cumpleanos.reccomprobantes.util;

import com.cumpleanos.core.models.entities.Cliente;
import com.cumpleanos.core.models.entities.Sistema;

import java.util.ArrayList;
import java.util.List;

public class MessagesUtils {

    public static String mensajeCamposNulosCliente(Cliente cliente, Sistema sis) {
        List<String> camposNulos = new ArrayList<>();
        camposNulos.add("Telefono");
        camposNulos.add("Correo");
        String camposNulosString = String.join(", ", camposNulos);

        return String.format(
                "Estimado usuario, \n\nEl proveedor %s (RUC/Cedula: %s) \nregistrado en la empresa %s tiene los siguientes campos nulos: %s.\nPor favor, complete la informacion necesaria.\nOpcional se agrego ciudad por defecto Cuenca",
                cliente.getNombre(),cliente.getRucCedula(),sis.getNombre(),camposNulosString);
    }
}
