package main;

public class UseCase3GenericQuantity {

    // -------- ENUM FOR UNITS --------
    enum Unit {
        FEET(1.0),
        INCHES(1.0 / 12);

        private final double conversionFactor;

        Unit(double factor) {
            this.conversionFactor = factor;
        }

        public double toFeet(double value) {
            return value * conversionFactor;
        }
    }

    // -------- GENERIC QUANTITY CLASS --------
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

        System.out.println("UC3 - Generic Quantity Equality\n");

        Quantity q1 = new Quantity(1.0, Unit.FEET);
        Quantity q2 = new Quantity(12.0, Unit.INCHES);

        System.out.println("1 foot == 12 inches ? " + q1.equals(q2));

        Quantity q3 = new Quantity(2.0, Unit.FEET);
        Quantity q4 = new Quantity(24.0, Unit.INCHES);

        System.out.println("2 feet == 24 inches ? " + q3.equals(q4));
    }
}