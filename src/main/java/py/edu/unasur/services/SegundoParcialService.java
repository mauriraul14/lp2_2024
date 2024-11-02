package py.edu.unasur.services;

import java.util.List;
import java.util.stream.Collectors;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SegundoParcialService {

    public int contarPalindromos(List<String> lista) {
        return (int) lista.stream()
                         .filter(this::esPalindromo)
                         .count();
    }

    public List<String> obtenerPalindromos(List<String> lista) {
        return lista.stream()
                    .filter(this::esPalindromo)
                    .collect(Collectors.toList());
    }

    private boolean esPalindromo(String str) {
        String limpio = str.replaceAll("\\s+", "").toLowerCase();
        return new StringBuilder(limpio).reverse().toString().equals(limpio);
    }
}
