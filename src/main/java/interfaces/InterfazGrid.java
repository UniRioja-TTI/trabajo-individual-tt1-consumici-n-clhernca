package interfaces;

import java.util.List;
import java.util.Map;
import modelo.CeldaColor;

public interface InterfazGrid {
    int obtenerAncho(int token, String usuario);
    Map<Integer, List<CeldaColor>> obtenerCeldasPorTiempo(int token, String usuario);
}