public class Timecode {
    public static String fromSeconds(double seconds) {
        // Extract hours, minutes, and seconds using integer division
        int hours = (int) seconds / 3600;
        int minutes = ((int) seconds % 3600) / 60;
        int remainingSeconds = (int) seconds % 60;

        // Extract milliseconds by multiplying the fractional part of seconds by 1000
        int milliseconds = (int) ((seconds - (int) seconds) * 1000);

        // Return the formatted timecode string using System.out.printf equivalent
        return String.format("%02d:%02d:%02d:%03d", hours, minutes, remainingSeconds, milliseconds);
    }

    public static void main(String[] args) {
        // Example usage
        double timeInSeconds = 3661.001;  // Example input: 3661.001 seconds
        String timecode = fromSeconds(timeInSeconds);
        System.out.println(timecode);  // Output: 01:01:01:001
    }
}