package com.cumpleanos.reccomprobantes.utils;

import com.cumpleanos.reccomprobantes.models.xml.InfoTributaria;
import core.cumpleanos.models.entities.Cliente;
import core.cumpleanos.models.entities.Sistema;
import core.cumpleanos.models.entities.SriDocEleEmi;
import core.cumpleanos.models.ids.ClienteId;
import core.cumpleanos.models.ids.SriDocEleEmiId;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringReader;
import java.io.StringWriter;
import java.time.LocalDate;
import java.time.ZonedDateTime;

public class ComprobantesUtils {

    public static SriDocEleEmi crearSriDoc(
            Sistema empresa,
            String claveAcceso,
            String tipoComprobante,
            String serieComprobante,
            String rucEmisor,
            String razonSocialEmisor,
            LocalDate fechaEmision,
            ZonedDateTime fechaAutorizacion,
            String identificacionReceptor)
    {
        SriDocEleEmi docSri = new SriDocEleEmi();
        SriDocEleEmiId idSri = new SriDocEleEmiId();
        idSri.setEmpresa(empresa.getId());
        idSri.setNumeroAutorizacion(claveAcceso);
        docSri.setId(idSri);
        docSri.setComprobante(tipoComprobante);
        docSri.setSerieComprobante(serieComprobante);
        docSri.setRucEmisor(rucEmisor);
        docSri.setRazonSocialEmisor(razonSocialEmisor);
        docSri.setFechaEmision(fechaEmision);
        docSri.setFechaAutorizacion(fechaAutorizacion);
        docSri.setIdentificacionReceptor(identificacionReceptor);
        docSri.setClaveAcceso(claveAcceso);
        return docSri;
    }

    public static Cliente crearProveedor(InfoTributaria info, Long empresa) {
        Cliente proveedor = new Cliente();
        ClienteId id= new ClienteId();
        id.setEmpresa(empresa);
        proveedor.setId(id);
        proveedor.setNombre(reverzarNombre(info.getRazonSocial()));
        proveedor.setRucCedula(info.getRuc());
        proveedor.setTipo((short)2);
        proveedor.setDireccion(info.getDirMatriz());
        return proveedor;
    }

    public static String reverzarNombre(String nombre) {
        String[] partes = nombre.split(" ");

        StringBuilder nuevoNombre = new StringBuilder();

        for (int i = partes.length - 2; i >= 0; i--) {
            nuevoNombre.append(partes[i]).append(" ");
        }

        nuevoNombre.append(partes[partes.length - 1]);

        return nuevoNombre.toString().trim();
    }

    public static String cleanXml(String xml) {
        // Eliminar los caracteres de escape \" para obtener XML limpio
        return xml.replace("\\\"", "\"");
    }

    public static String transformXml(String xml) throws Exception {
        // Parsear el XML
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new InputSource(new StringReader(xml)));

        // Convertir el documento de vuelta a XML
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(javax.xml.transform.OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

        StringWriter writer = new StringWriter();
        transformer.transform(new DOMSource(document), new StreamResult(writer));

        return writer.toString();
    }
}
