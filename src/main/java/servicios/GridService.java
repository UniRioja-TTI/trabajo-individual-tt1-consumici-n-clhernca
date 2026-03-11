package servicios;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import interfaces.InterfazGrid;
import modelo.CeldaColor;

@Service
public class GridService implements InterfazGrid {

    @Override
    public int obtenerAncho(int token, String usuario) {
        String data = llamarServicio(token, usuario);
        if (data == null) return 0;
        String[] lineas = data.split("\n");
        try {
            return Integer.parseInt(lineas[0].trim());
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public Map<Integer, List<CeldaColor>> obtenerCeldasPorTiempo(int token, String usuario) {
        String data = llamarServicio(token, usuario);
        Map<Integer, List<CeldaColor>> resultado = new LinkedHashMap<>();
        if (data == null) return resultado;

        String[] lineas = data.split("\n");
        for (int i = 1; i < lineas.length; i++) {
            String linea = lineas[i].trim();
            if (linea.isEmpty()) continue;
            String[] partes = linea.split(",");
            if (partes.length == 4) {
                int tiempo = Integer.parseInt(partes[0].trim());
                int y = Integer.parseInt(partes[1].trim());
                int x = Integer.parseInt(partes[2].trim());
                String color = partes[3].trim();
                resultado.computeIfAbsent(tiempo, k -> new ArrayList<>())
                         .add(new CeldaColor(tiempo, y, x, color));
            }
        }
        return resultado;
    }

    private String llamarServicio(int token, String usuario) {
        try {
            org.openapitools.client.ApiClient apiClient = new org.openapitools.client.ApiClient();
            apiClient.setBasePath("http://localhost:8080");
            org.openapitools.client.api.ResultadosApi resultadosApi =
                new org.openapitools.client.api.ResultadosApi(apiClient);
            org.openapitools.client.model.ResultsResponse response =
                resultadosApi.resultadosPost(usuario, token);
            return response.getData();
        } catch (Exception e) {
            return null;
        }
    }
}