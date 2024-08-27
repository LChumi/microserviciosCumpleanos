package com.cumpleanos.reccomprobantes.service;

import com.cumpleanos.reccomprobantes.models.csv.ComprobanteCsv;
import com.cumpleanos.reccomprobantes.models.entity.Comprobante;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class CSVReaderService {

    public List<Comprobante> parseCsvString(String csvContent) throws IOException {
        List<Comprobante> comprobantes = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new StringReader(csvContent))) {
            String line;
            // Leer la cabecera del CSV (opcional si no necesitas los nombres de columna)
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] values = line.split("\t"); // Tab es el delimitador en tu archivo

                ComprobanteCsv comprobante = new ComprobanteCsv();
                comprobante.setRucEmisor(values[0]);
                comprobante.setRazonSocialEmisor(values[1]);
                comprobante.setTipoComprobante(values[2]);
                comprobante.setSerieComprobante(values[3]);
                comprobante.setClaveAcceso(values[4]);
                comprobante.setFechaAutorizacion(values[5]);
                comprobante.setFechaEmision(values[6]);
                comprobante.setIdentificacionReceptor(values[7]);
                comprobante.setValorSinImpuestos(values[8]);
                comprobante.setIva(values[9]);
                comprobante.setImporteTotal(values[10]);
                // Solo establecer el valor para `numeroDocumentoModificado` si está presente
                if (values.length > 11) {
                    comprobante.setNumeroDocumentoModificado(values[11]);
                } else {
                    comprobante.setNumeroDocumentoModificado(""); // O cualquier valor predeterminado
                }

                comprobantes.add(comprobante);
            }
        }
        return comprobantes;
    }
}
