package br.com;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class PeriodicCycleCalculator {

    public static final int FEBRUARY = 2;
    public static final int DAY_29_OF_MONTH = 29;
    public static final int DAY_28_OF_MONTH = 28;

    public enum Periodicity {
        WEEKLY(ChronoUnit.WEEKS, 1),
        MONTHLY(ChronoUnit.MONTHS, 1),
        QUARTERLY(ChronoUnit.MONTHS, 3),
        SEMIANNUAL(ChronoUnit.MONTHS, 6),
        ANNUAL(ChronoUnit.YEARS, 1);

        private final ChronoUnit unit;
        private final int amount;

        Periodicity(ChronoUnit unit, int amount) {
            this.unit = unit;
            this.amount = amount;
        }

        public LocalDate addToDate(LocalDate date) {
            return date.plus(amount, unit);
        }
    }

    public static int getCycle(final LocalDate startDate,
                               final LocalDate targetDate,
                               final Periodicity periodicity) {
        LocalDate currentStart = startDate;
        int cycle = 1;

        while (!currentStart.isAfter(targetDate)) {
            LocalDate nextStart = periodicity.addToDate(currentStart);

            final var isFebruary = currentStart.getMonthValue() == 2;
            if (isFebruary) {
                LocalDate lastDayOfFebruary = currentStart.isLeapYear()
                        ? LocalDate.of(currentStart.getYear(), FEBRUARY, DAY_29_OF_MONTH)
                        : LocalDate.of(currentStart.getYear(), FEBRUARY, DAY_28_OF_MONTH);

                if ((targetDate.isEqual(currentStart) || targetDate.isAfter(currentStart)) &&
                        targetDate.isBefore(nextStart)) {
                    return cycle;  // Dentro do ciclo de fevereiro
                }

                if (targetDate.isEqual(lastDayOfFebruary) || targetDate.isEqual(nextStart)) {
                    return cycle;  // Caso especial se targetDate é o último dia de fevereiro
                }
            }

            if (!currentStart.isAfter(targetDate) && targetDate.isBefore(nextStart)) {
                return cycle;
            }

            currentStart = nextStart;
            cycle++;
        }

        throw new IllegalStateException("It is not possible to find a cycle for the date: " + targetDate);
    }
}
