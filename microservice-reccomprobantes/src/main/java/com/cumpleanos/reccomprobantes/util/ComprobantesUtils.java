package com.cumpleanos.reccomprobantes.util;

import com.cumpleanos.core.models.entities.Autcliente;
import com.cumpleanos.core.models.entities.Cliente;
import com.cumpleanos.core.models.entities.Sistema;
import com.cumpleanos.core.models.entities.SriDocEleEmi;
import com.cumpleanos.core.models.ids.AutclienteId;
import com.cumpleanos.core.models.ids.ClienteId;
import com.cumpleanos.core.models.ids.SriDocEleEmiId;
import com.cumpleanos.reccomprobantes.persistence.models.csv.ComprobanteCsv;
import com.cumpleanos.reccomprobantes.persistence.models.xml.InfoTributaria;
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
import java.math.BigDecimal;
import java.text.Normalizer;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.regex.Pattern;

public class ComprobantesUtils {

    public static ComprobanteCsv getComprobanteCsv(String[] values) {
        ComprobanteCsv comprobante = new ComprobanteCsv();
        comprobante.setRucEmisor(normalizeString(values[0]));
        comprobante.setRazonSocialEmisor(normalizeString(values[1]));
        comprobante.setTipoComprobante(normalizeString(values[2]));
        comprobante.setSerieComprobante(normalizeString(values[3]));
        comprobante.setClaveAcceso(normalizeString(values[4]));
        comprobante.setFechaAutorizacion(normalizeString(values[5]));
        comprobante.setFechaEmision(normalizeString(values[6]));
        comprobante.setIdentificacionReceptor(normalizeString(values[7]));
        comprobante.setValorSinImpuestos(normalizeString(values[8]));
        comprobante.setIva(normalizeString(values[9]));
        comprobante.setImporteTotal(normalizeString(values[10]));

        if (values.length > 11) {
            comprobante.setNumeroDocumentoModificado(normalizeString(values[11]));
        } else {
            comprobante.setNumeroDocumentoModificado(""); // O cualquier valor predeterminado
        }
        return comprobante;
    }

    public static SriDocEleEmi crearSriDoc(
            Sistema empresa,
            String claveAcceso,
            String tipoComprobante,
            String serieComprobante,
            String rucEmisor,
            String identificacionReceptor,
            LocalDate fechaEmision,
            ZonedDateTime fechaAutorizacion,
            String razonSocialEmisor)
    {
        SriDocEleEmi docSri = new SriDocEleEmi();
        SriDocEleEmiId idSri = new SriDocEleEmiId();
        idSri.setEmpresa(empresa.getId());
        idSri.setNumeroAutorizacion(claveAcceso);
        docSri.setId(idSri);
        docSri.setFecha(LocalDate.now());
        docSri.setTipoEmision("NORMAL");
        docSri.setComprobante(tipoComprobante);
        docSri.setSerieComprobante(serieComprobante);
        docSri.setRucEmisor(rucEmisor);
        docSri.setRazonSocialEmisor(razonSocialEmisor);
        docSri.setFechaEmision(fechaEmision);
        docSri.setRegistrado(false);
        docSri.setFechaAutorizacion(fechaAutorizacion);
        docSri.setIdentificacionReceptor(identificacionReceptor);
        docSri.setClaveAcceso(claveAcceso);
        return docSri;
    }

    public static SriDocEleEmi creaDoc(ComprobanteCsv comp, Sistema empresa) {
        SriDocEleEmi doc = new SriDocEleEmi();
        SriDocEleEmiId id = new SriDocEleEmiId();
        id.setEmpresa(empresa.getId());
        id.setNumeroAutorizacion(comp.getClaveAcceso());
        doc.setId(id);
        doc.setRucEmisor(comp.getRucEmisor());
        doc.setRazonSocialEmisor(comp.getRazonSocialEmisor());
        doc.setFecha(LocalDate.now());
        doc.setTipoEmision("NORMAL");
        doc.setRegistrado(false);
        doc.setComprobante(comp.getTipoComprobante());
        doc.setSerieComprobante(comp.getSerieComprobante());
        doc.setClaveAcceso(comp.getClaveAcceso());
        doc.setFechaAutorizacion(DateTimeUtils.parseDateTime(comp.getFechaAutorizacion()));
        doc.setFechaEmision(DateTimeUtils.parseDate(comp.getFechaEmision()));
        doc.setIdentificacionReceptor(comp.getIdentificacionReceptor());
        return doc;
    }

    public static Cliente crearProveedor(InfoTributaria info, Long empresa, Long tipoJuridico) {
        Cliente proveedor = new Cliente();
        ClienteId id= new ClienteId();
        id.setEmpresa(empresa);
        proveedor.setId(id);
        proveedor.setNombre(info.getRazonSocial());
        proveedor.setRucCedula(info.getRuc());
        proveedor.setTipoced(tipoCedula(info.getRuc()));
        proveedor.setTipo((short)2);
        proveedor.setDireccion(info.getDirMatriz());
        proveedor.setTipoper(tipoJuridico.shortValue());
        proveedor.setGenero((short)1);
        proveedor.setInactivo(false);
        proveedor.setCupo(BigDecimal.ZERO);
        proveedor.setImpuestos((short)1);
        proveedor.setFechaing(LocalDate.now());
        return proveedor;
    }

    public static Autcliente crearAutCliente(SriDocEleEmi doc, Long clienteId , Sistema empresa, InfoTributaria info) {
        Autcliente autcliente = new Autcliente();
        AutclienteId id = new AutclienteId();
        id.setCliente(clienteId);
        id.setEmpresa(doc.getId().getEmpresa());
        id.setNroAutoriza(doc.getId().getNumeroAutorizacion());
        id.setFac1(info.getEstab());
        id.setFac2(info.getPtoEmi());
        autcliente.setId(id);
        autcliente.setSistema(empresa);
        autcliente.setFac3(info.getSecuencial());
        autcliente.setFact1(info.getEstab());
        autcliente.setFact2(info.getPtoEmi());
        autcliente.setFact3(info.getSecuencial());
        autcliente.setInactivo(false);
        autcliente.setTclipro((short)2);
        return autcliente;
    }

    public static Autcliente crearAutClienteCsv(ComprobanteCsv csv, Long clienteId,Sistema empresa){
        String[] serie1 = csv.getSerieComprobante().split("-");
        Autcliente autcliente = new Autcliente();
        AutclienteId id = new AutclienteId();
        id.setCliente(clienteId);
        id.setEmpresa(empresa.getId());
        id.setNroAutoriza(csv.getClaveAcceso());
        id.setFac1(serie1[0]);
        id.setFac2(serie1[1]);
        autcliente.setId(id);
        autcliente.setFac3(serie1[2]);
        autcliente.setInactivo(false);
        autcliente.setTclipro((short)2);
        autcliente.setFact1(serie1[0]);
        autcliente.setFact2(serie1[1]);
        autcliente.setFact3(serie1[2]);
        return autcliente;
    }

    public static String identificarTipoDoc(String tipo){
        return switch (tipo){
            case "Factura" -> "01";
            case "Comprobante de Retención" -> "07";
            case "Nota de Crédito" -> "04";
            default -> throw new IllegalArgumentException("Tipo de comprobante no reconocido: " + tipo);
        };
    }

    private static String reverzarNombre(String nombre) {
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

    private static String normalizeString(String str) {
        if (str == null) {
            return null;
        }
        // Normalizar a NFD
        String normalized = Normalizer.normalize(str, Normalizer.Form.NFD);
        // Expresión regular para eliminar diacríticos
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        // Reemplazar los caracteres acentuados
        return pattern.matcher(normalized).replaceAll("");
    }

    private static Short tipoCedula(String cedula) {
        //Verificar si el string solo tiene numeros
        if (cedula.matches("\\d+")) {
            if (cedula.length() == 10) {
                return 1;
            }else if (cedula.length() == 13) {
                return 2;
            }
        }else {
            return 3;
        }
        return 3;
    }
}
