package main;

public class UseCase2FeetInchesEquality {

    // -------- FEET --------
    static class Feet {
        private final double value;

        public Feet(double value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;

            Feet other = (Feet) obj;
            return Double.compare(this.value, other.value) == 0;
        }
    }

    // -------- INCHES --------
    static class Inches {
        private final double value;

        public Inches(double value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;

            Inches other = (Inches) obj;
            return Double.compare(this.value, other.value) == 0;
        }
    }

    // -------- MAIN --------
    public static void main(String[] args) {

        System.out.println("UC2 - Feet and Inches Equality\n");

        // Feet check
        Feet f1 = new Feet(1.0);
        Feet f2 = new Feet(1.0);
        System.out.println("Feet Equal: " + f1.equals(f2));

        // Inches check
        Inches i1 = new Inches(12.0);
        Inches i2 = new Inches(12.0);
        System.out.println("Inches Equal: " + i1.equals(i2));
    }
}