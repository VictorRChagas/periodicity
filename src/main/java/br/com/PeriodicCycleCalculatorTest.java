package br.com;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PeriodicCycleCalculatorTest {

    @Test
    public void testWeeklyCycleCorrect() {
        LocalDate startDate = LocalDate.of(2024, 11, 3);

        assertEquals(1, PeriodicCycleCalculator.getCycle(startDate, LocalDate.of(2024, 11, 9), PeriodicCycleCalculator.Periodicity.WEEKLY));

        assertEquals(2, PeriodicCycleCalculator.getCycle(startDate, LocalDate.of(2024, 11, 10), PeriodicCycleCalculator.Periodicity.WEEKLY));
        assertEquals(2, PeriodicCycleCalculator.getCycle(startDate, LocalDate.of(2024, 11, 16), PeriodicCycleCalculator.Periodicity.WEEKLY));
    }

    @Test
    public void testMonthlyCycleCorrect() {
        LocalDate startDate = LocalDate.of(2024, 1, 1);  // Ajustando para iniciar no dia 1 para consistência

        // Teste do primeiro ciclo mensal
        assertEquals(1, PeriodicCycleCalculator.getCycle(startDate, LocalDate.of(2024, 1, 31), PeriodicCycleCalculator.Periodicity.MONTHLY));

        // Teste do segundo ciclo mensal
        assertEquals(2, PeriodicCycleCalculator.getCycle(startDate, LocalDate.of(2024, 2, 28), PeriodicCycleCalculator.Periodicity.MONTHLY));  // Alterado para o último dia de fevereiro
        assertEquals(2, PeriodicCycleCalculator.getCycle(startDate, LocalDate.of(2024, 3, 1), PeriodicCycleCalculator.Periodicity.MONTHLY));

        // Teste do terceiro ciclo mensal
        assertEquals(3, PeriodicCycleCalculator.getCycle(startDate, LocalDate.of(2024, 3, 31), PeriodicCycleCalculator.Periodicity.MONTHLY));
    }

    @Test
    public void testQuarterlyCycleCorrect() {
        LocalDate startDate = LocalDate.of(2024, 1, 1);

        assertEquals(1, PeriodicCycleCalculator.getCycle(startDate, LocalDate.of(2024, 3, 31), PeriodicCycleCalculator.Periodicity.QUARTERLY));

        assertEquals(2, PeriodicCycleCalculator.getCycle(startDate, LocalDate.of(2024, 4, 1), PeriodicCycleCalculator.Periodicity.QUARTERLY));
        assertEquals(2, PeriodicCycleCalculator.getCycle(startDate, LocalDate.of(2024, 6, 30), PeriodicCycleCalculator.Periodicity.QUARTERLY));

        assertEquals(3, PeriodicCycleCalculator.getCycle(startDate, LocalDate.of(2024, 7, 1), PeriodicCycleCalculator.Periodicity.QUARTERLY));
        assertEquals(3, PeriodicCycleCalculator.getCycle(startDate, LocalDate.of(2024, 9, 30), PeriodicCycleCalculator.Periodicity.QUARTERLY));
    }

    @Test
    public void testSemiannualCycleCorrect() {
        LocalDate startDate = LocalDate.of(2024, 1, 1);

        assertEquals(1, PeriodicCycleCalculator.getCycle(startDate, LocalDate.of(2024, 6, 30), PeriodicCycleCalculator.Periodicity.SEMIANNUAL));

        assertEquals(2, PeriodicCycleCalculator.getCycle(startDate, LocalDate.of(2024, 7, 1), PeriodicCycleCalculator.Periodicity.SEMIANNUAL));
        assertEquals(2, PeriodicCycleCalculator.getCycle(startDate, LocalDate.of(2024, 12, 31), PeriodicCycleCalculator.Periodicity.SEMIANNUAL));
    }

    @Test
    public void testAnnualCycleCorrect() {
        LocalDate startDate = LocalDate.of(2024, 1, 1);

        assertEquals(1, PeriodicCycleCalculator.getCycle(startDate, LocalDate.of(2024, 12, 31), PeriodicCycleCalculator.Periodicity.ANNUAL));

        assertEquals(2, PeriodicCycleCalculator.getCycle(startDate, LocalDate.of(2025, 1, 1), PeriodicCycleCalculator.Periodicity.ANNUAL));
        assertEquals(2, PeriodicCycleCalculator.getCycle(startDate, LocalDate.of(2025, 12, 31), PeriodicCycleCalculator.Periodicity.ANNUAL));
    }

    @Test
    public void testLeapYearHandlingCorrect() {
        LocalDate startDate = LocalDate.of(2024, 2, 29);  // Ano bissexto

        assertEquals(1, PeriodicCycleCalculator.getCycle(startDate, LocalDate.of(2025, 2, 28), PeriodicCycleCalculator.Periodicity.ANNUAL));

        assertEquals(2, PeriodicCycleCalculator.getCycle(startDate, LocalDate.of(2026, 2, 28), PeriodicCycleCalculator.Periodicity.ANNUAL));
    }
}
