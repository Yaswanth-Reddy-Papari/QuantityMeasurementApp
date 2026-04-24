package main;

public class UseCase5UnitConversion {

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

        // 🔥 CONVERSION METHOD
        public Quantity convertTo(Unit targetUnit) {
            double valueInFeet = unit.toFeet(value);
            double convertedValue = targetUnit.fromFeet(valueInFeet);
            return new Quantity(convertedValue, targetUnit);
        }

        @Override
        public String toString() {
            return value + " " + unit;
        }
    }

    // -------- MAIN --------
    public static void main(String[] args) {

        System.out.println("UC5 - Unit Conversion\n");

        Quantity q1 = new Quantity(1, Unit.FEET);

        System.out.println("1 FEET to INCHES: " + q1.convertTo(Unit.INCHES));
        System.out.println("1 FEET to YARD: " + q1.convertTo(Unit.YARD));
        System.out.println("1 FEET to CM: " + q1.convertTo(Unit.CM));
        System.out.println("1 FEET to METER: " + q1.convertTo(Unit.METER));
    }
}