import java.math.BigDecimal;
import java.math.RoundingMode;

// Rational approximation
class RationalApx {
    int num;    // numerator
    int den;    // denominator
    int prec;   // how many decimal places the approximation is good out to
    
    RationalApx() { 
        this(0, 1, 0); 
    }
    
    RationalApx(int n, int d, int p) {
        num = n;
        den = d;
        prec = p;
    }

    boolean equals(int n, int d, int p) {
        return num == n && den == d && prec == p;
    }

    boolean equals(RationalApx other) {
        return equals(other.num, other.den, other.prec);
    }
}

public class FindingPi {
    private static final double PI = Math.PI;

    public static RationalApx run(int maxInt) {
        RationalApx result = new RationalApx();
        double bestDiff = Double.MAX_VALUE;

        for (int b = 1; b <= maxInt; b++) {
            int a = (int) Math.round(PI * b); // Closest numerator
            double approximation = (double) a / b;
            double diff = Math.abs(PI - approximation);

            if (diff < bestDiff) {
                bestDiff = diff;
                int decimalPlaces = countMatchingDecimalPlaces(PI, approximation);
                result = new RationalApx(a, b, decimalPlaces);
            }
        }

        return result;
    }

    private static int countMatchingDecimalPlaces(double target, double approximation) {
        BigDecimal targetBD = BigDecimal.valueOf(target).setScale(15, RoundingMode.HALF_UP);
        BigDecimal approximationBD = BigDecimal.valueOf(approximation).setScale(15, RoundingMode.HALF_UP);

        String targetStr = targetBD.toPlainString();
        String approxStr = approximationBD.toPlainString();

        int matchCount = 0;
        for (int i = 0; i < Math.min(targetStr.length(), approxStr.length()); i++) {
            if (targetStr.charAt(i) == approxStr.charAt(i)) {
                if (targetStr.charAt(i) == '.') continue;
                matchCount++;
            } else {
                break;
            }
        }
        return matchCount;
    }

    public static void main(String[] args) {
        int maxInt = 1000; // Change to 100_000 or 500_000 for larger ranges
        RationalApx result = run(maxInt);
        System.out.println("Closest Rational Approximation to PI:");
        System.out.println("Numerator: " + result.num);
        System.out.println("Denominator: " + result.den);
        System.out.println("Precision: " + result.prec + " decimal places");
    }
}
