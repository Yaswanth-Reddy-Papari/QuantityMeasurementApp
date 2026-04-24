package main;

public class UseCase4ExtendedUnits {

    // -------- ENUM WITH CONVERSIONS --------
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
    }

    // -------- GENERIC QUANTITY --------
    static class Quantity {
        private final double value;
        private final Unit unit;

        public Quantity(double value, Unit unit) {
            this.value = value;
            this.unit = unit;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;

            Quantity other = (Quantity) obj;

            double thisInFeet = this.unit.toFeet(this.value);
            double otherInFeet = other.unit.toFeet(other.value);

            return Double.compare(thisInFeet, otherInFeet) == 0;
        }
    }

    // -------- MAIN --------
    public static void main(String[] args) {

        System.out.println("UC4 - Extended Unit Comparison\n");

        Quantity q1 = new Quantity(1, Unit.FEET);
        Quantity q2 = new Quantity(12, Unit.INCHES);
        System.out.println("1 foot == 12 inches ? " + q1.equals(q2));

        Quantity q3 = new Quantity(1, Unit.YARD);
        Quantity q4 = new Quantity(3, Unit.FEET);
        System.out.println("1 yard == 3 feet ? " + q3.equals(q4));

        Quantity q5 = new Quantity(1, Unit.METER);
        Quantity q6 = new Quantity(100, Unit.CM);
        System.out.println("1 meter == 100 cm ? " + q5.equals(q6));
    }
}