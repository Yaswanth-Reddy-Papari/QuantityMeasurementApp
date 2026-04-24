package main;

public class QuantityMeasurementApp {

    // Inner class for Feet
    static class Feet {
        private final double value;

        public Feet(double value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object obj) {

            // Same reference
            if (this == obj) return true;

            // Null or different class
            if (obj == null || this.getClass() != obj.getClass()) return false;

            Feet other = (Feet) obj;

            // Compare double safely
            return Double.compare(this.value, other.value) == 0;
        }
    }

    public static void main(String[] args) {

        Feet f1 = new Feet(1.0);
        Feet f2 = new Feet(1.0);

        System.out.println("Equal: " + f1.equals(f2));
    }
}
