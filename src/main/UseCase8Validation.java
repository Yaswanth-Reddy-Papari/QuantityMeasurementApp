package main;

public class UseCase8Validation {

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

    // -------- CUSTOM EXCEPTION --------
    static class InvalidQuantityException extends RuntimeException {
        public InvalidQuantityException(String message) {
            super(message);
        }
    }

    // -------- QUANTITY --------
    static class Quantity {
        private final double value;
        private final Unit unit;

        public Quantity(double value, Unit unit) {

            if (unit == null) {
                throw new InvalidQuantityException("Unit cannot be null");
            }

            if (Double.isNaN(value)) {
                throw new InvalidQuantityException("Value must be numeric");
            }

            this.value = value;
            this.unit = unit;
        }

        public Quantity add(Quantity other, Unit targetUnit) {

            if (other == null) {
                throw new InvalidQuantityException("Cannot add null quantity");
            }

            if (targetUnit == null) {
                throw new InvalidQuantityException("Target unit cannot be null");
            }

            double thisFeet = this.unit.toFeet(this.value);
            double otherFeet = other.unit.toFeet(other.value);

            double sumFeet = thisFeet + otherFeet;

            double finalValue = targetUnit.fromFeet(sumFeet);

            return new Quantity(finalValue, targetUnit);
        }

        @Override
        public String toString() {
            return value + " " + unit;
        }
    }

    // -------- MAIN --------
    public static void main(String[] args) {

        System.out.println("UC8 - Validation & Error Handling\n");

        try {
            Quantity q1 = new Quantity(1, Unit.FEET);
            Quantity q2 = new Quantity(12, Unit.INCHES);

            System.out.println("Valid Addition: " + q1.add(q2, Unit.FEET));

            // ❌ invalid case
            Quantity invalid = new Quantity(Double.NaN, Unit.FEET);

        } catch (InvalidQuantityException e) {
            System.out.println("Error: " + e.getMessage());
        }

        try {
            Quantity q3 = new Quantity(1, Unit.FEET);
            System.out.println(q3.add(null, Unit.FEET)); // ❌ null case

        } catch (InvalidQuantityException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}