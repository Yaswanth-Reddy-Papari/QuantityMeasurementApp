package main;

public class UseCase6Addition {

    // -------- ENUM --------
    enum Unit {
        FEET(1.0),
        INCHES(1.0 / 12),
        YARD(3.0),
        CM(1.0 / 30.48),
        METER(3.28084);

        private final double toFeetFactor;

        Unit(double factor) {
            this.toFeetFactor = factor;
        }

        public double toFeet(double value) {
            return value * toFeetFactor;
        }

        public double fromFeet(double feetValue) {
            return feetValue / toFeetFactor;
        }
    }

    // -------- QUANTITY --------
    static class Quantity {
        private final double value;
        private final Unit unit;

        public Quantity(double value, Unit unit) {
            this.value = value;
            this.unit = unit;
        }

        // 🔥 ADDITION METHOD
        public Quantity add(Quantity other, Unit resultUnit) {

            double thisInFeet = this.unit.toFeet(this.value);
            double otherInFeet = other.unit.toFeet(other.value);

            double sumInFeet = thisInFeet + otherInFeet;

            double finalValue = resultUnit.fromFeet(sumInFeet);

            return new Quantity(finalValue, resultUnit);
        }

        @Override
        public String toString() {
            return value + " " + unit;
        }
    }

    // -------- MAIN --------
    public static void main(String[] args) {

        System.out.println("UC6 - Addition of Length Units\n");

        Quantity q1 = new Quantity(1, Unit.FEET);
        Quantity q2 = new Quantity(12, Unit.INCHES);

        System.out.println("1 FEET + 12 INCHES = " + q1.add(q2, Unit.FEET));

        Quantity q3 = new Quantity(1, Unit.METER);
        Quantity q4 = new Quantity(100, Unit.CM);

        System.out.println("1 METER + 100 CM = " + q3.add(q4, Unit.METER));
    }
}