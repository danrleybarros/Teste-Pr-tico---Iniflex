import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.math.BigDecimal;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        // 3.1 Inserir todos os funcionários
        List<Funcionario> funcionarios = new ArrayList<>();
        funcionarios.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"));
        funcionarios.add(new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"));
        funcionarios.add(new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador"));
        funcionarios.add(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"));
        funcionarios.add(new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista"));
        funcionarios.add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"));
        funcionarios.add(new Funcionario("Arthur", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"));
        funcionarios.add(new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"));
        funcionarios.add(new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente"));
        

        // 3.2 Remover o funcionário "João"
        funcionarios.removeIf(funcionario -> funcionario.getNome().equals("João"));

        // 3.3 Imprimir todos os funcionários com suas informações
        Divisoria();
        System.out.println("Funcionários:");
        funcionarios.forEach(System.out::println);
        Divisoria();
        // 3.4 Aumentar salário em 10%
        funcionarios.forEach(funcionario -> {
            BigDecimal novoSalario = funcionario.getSalario().multiply(new BigDecimal("1.10"));
            funcionario.setSalario(novoSalario);
        });

        // 3.5 Agrupar funcionários por função em um MAP
        Map<String, List<Funcionario>> funcionariosPorFuncao = funcionarios.stream()
                .collect(Collectors.groupingBy(Funcionario::getFuncao));

        // 3.6 Imprimir funcionários agrupados por função
        System.out.println("\nFuncionários por Função:");
        funcionariosPorFuncao.forEach((funcao, lista) -> {
            System.out.println("Função: " + funcao);
            lista.forEach(System.out::println);
        });
        Divisoria();

        // 3.8 Imprimir funcionários que fazem aniversário no mês 10 e 12
        System.out.println("\nFuncionários com aniversário no mês 10 e 12:");
        funcionarios.stream()
                .filter(funcionario -> funcionario.getDataNascimento().getMonthValue() == 10 ||
                        funcionario.getDataNascimento().getMonthValue() == 12)
                .forEach(System.out::println);
        Divisoria();

        // 3.9 Imprimir funcionário com maior idade
        System.out.println("\nFuncionário com maior idade:");
        Funcionario maisVelho = funcionarios.stream()
                .min((f1, f2) -> f2.getDataNascimento().compareTo(f1.getDataNascimento()))
                .orElse(null);
        if (maisVelho != null) {
            int idade = LocalDate.now().getYear() - maisVelho.getDataNascimento().getYear();
            System.out.println("Nome: " + maisVelho.getNome() + ", Idade: " + idade);
        }
        Divisoria();

        // 3.10 Imprimir lista de funcionários por ordem alfabética
        System.out.println("\nFuncionários em ordem alfabética:");
        funcionarios.stream()
                .sorted((f1, f2) -> f1.getNome().compareToIgnoreCase(f2.getNome()))
                .forEach(System.out::println);
        Divisoria();
        // 3.11 Imprimir total dos salários dos funcionários
        BigDecimal totalSalarios = funcionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("\nTotal dos Salários: " + formatarMoeda(totalSalarios));
        Divisoria();

        // 3.12 Imprimir quantos salários mínimos ganha cada funcionário
        System.out.println("\nSalários Mínimos:");
        BigDecimal salarioMinimo = new BigDecimal("1212.00");
        funcionarios.forEach(funcionario -> {
            BigDecimal salariosMinimos = funcionario.getSalario().divide(salarioMinimo, 2, BigDecimal.ROUND_HALF_UP);
            System.out.println(funcionario.getNome() + ": " + salariosMinimos + " salários mínimos");
        });
        Divisoria();
    }

    private static String formatarMoeda(BigDecimal valor) {
        return String.format("%,.2f", valor);
    }
    
    private static void Divisoria(){
        System.out.println("\n─── ❖ ── ✦ ── ❖ ────── ❖ ── ✦ ── ❖ ────── ❖ ── ✦ ── ❖ ───\n");
    }
}
