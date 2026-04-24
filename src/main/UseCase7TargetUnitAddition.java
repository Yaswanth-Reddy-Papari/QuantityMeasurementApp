package main;

public class UseCase7TargetUnitAddition {

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

        // 🔥 UC7 ADD METHOD (TARGET UNIT)
        public Quantity add(Quantity other, Unit targetUnit) {

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

        System.out.println("UC7 - Addition with Target Unit\n");

        Quantity q1 = new Quantity(1, Unit.FEET);
        Quantity q2 = new Quantity(12, Unit.INCHES);

        System.out.println("Result in FEET: " + q1.add(q2, Unit.FEET));
        System.out.println("Result in INCHES: " + q1.add(q2, Unit.INCHES));

        Quantity q3 = new Quantity(1, Unit.METER);
        Quantity q4 = new Quantity(100, Unit.CM);

        System.out.println("Result in METER: " + q3.add(q4, Unit.METER));
        System.out.println("Result in CM: " + q3.add(q4, Unit.CM));
    }
}