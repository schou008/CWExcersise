/*
Coursework Submission (Exercises)
IS52059B: Object-oriented Programming (2024-25)
Computing, Goldsmiths

Instructions:
Fill in the provided .java class files with your solutions so that all tests in main() run successfully and the program exits on its own. Read the comments at the start of the provided files for any special instructions. Note that if the original problem statement requested you to print information to the console as part of the solution, that is no longer necessary. The only exception to this is the exercise 'Enter the Data'. You are welcome to comment out sections of main() to verify individual solutions, but your final submission should leave main() in its original form unless noted otherwise.
*/
import java.util.Scanner;

public class Main {

	public static void test(boolean v, String msg) {
		try{
			if(!v) throw new RuntimeException(msg);
		} catch(Exception e){
			System.out.println("Test failed: " + e.getMessage());
		}
	}

	public static void main(String[] args) {

		// Exercise: Timecode (Week 2)
		test(
			Timecode.fromSeconds(3661.001).equals("01:01:01:001"),
			"Timecode from 3661.001"
		);
		test(
			Timecode.fromSeconds(123456.5).equals("34:17:36:500"),
			"Timecode from 123456.5"
		);


		// Exercise: Finding Pi (Week 3)
		test(
			FindingPi.run(1_000).equals(355,113, 6),
			"FindingPi 1k"
		);
		/* Uncomment only for testing the optimized version
		test(
			FindingPi.run(100_000).equals(99733,31746, 7),
			"FindingPi 100k"
		);
		var piApx500k = FindingPi.run(500_000);
		test(
			piApx500k.equals(312689,99532, 9) || piApx500k.equals(312689,99532, 10),
			"FindingPi 500k"
		);
		//*/


		// Exercise: String Weaver (Week 5)
		DynStringTest.run();


		// Exercise: Enter the Data (Week 6)
		//EnterTheData.run(new Scanner(System.in)); // to test interactively
		EnterTheData.run(new Scanner(
			"help\n" +
			"l\n" +
			"a\n" + "John Doe 47\n" +
			"a\n" + "Jane Doe 42\n" +
			"l\n" +
			"r\n" + "3\n" +
			"r\n" + "2\n" +	
			"l\n" +		
			"q\n"
		));
	}
}