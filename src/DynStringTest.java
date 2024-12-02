// You are free to edit this file while working, however, proceed with caution. When your submission is marked it will be replaced with the original file. It is recommended that you backup the original file and replace your working copy before submission.

public class DynStringTest {

	public static void test(boolean v, String msg) {
		try{
			if(!v) throw new RuntimeException(msg);
		} catch(Exception e){
			System.out.println("Test failed (DynString): " + e.getMessage());
		}
	}

	public static void run() {
		{ // Check fields: only one char[]
			var fields = DynString.class.getDeclaredFields();
			test(fields.length == 1, "more than one field");
			test(fields[0].getType().getCanonicalName().equals("char[]"), "field is not char[]");
		}

		//* Move this toggle comment down as you implement new features

		{ // Default ctor
			var s = new DynString();
			test(s.empty(), "default ctor: not empty");
		}

		{ // String ctor and get/set
			var s = new DynString("xyz");
			test(s.size() == 3, "copy ctor: wrong size");
			test(s.get(0) == 'x', "get(0) wrong value");
			test(s.get(1) == 'y', "get(1) wrong value");
			test(s.get(2) == 'z', "get(2) wrong value");
			s.set(0, 'a');
			test(s.get(0) == 'a', "set(0) incorrect mutation");
		}

		{ // Fill ctor
			var s = new DynString(3, 'o');
			test(s.size() == 3, "fill ctor: wrong size");
			for(int i=0; i<s.size(); ++i)
				test(s.get(i) == 'o', "fill ctor: wrong value");
		}

		{ // Resize and clear
			var s = new DynString("abcd");

			s.resize(2);
			test(s.size() == 2, "resize down: wrong size");
			test(s.get(0) == 'a', "resize down: wrong value at index 0");
			test(s.get(1) == 'b', "resize down: wrong value at index 1");

			s.resize(4);
			test(s.size() == 4, "resize up: wrong size");
			test(s.get(2) == '\0', "resize up: wrong value at index 2");
			test(s.get(3) == '\0', "resize up: wrong value at index 3");

			s.clear();
			test(s.empty(), "clear did not empty");
		}

		{ // equals
			var s = new DynString(4, 'o');
			test( s.equals(new char[]{'o','o','o','o'}), "equals: char[]");
			test( s.equals(new DynString("oooo")), "equals: DynString");
			test( s.equals( "oooo"), "equals: String");
			test(!s.equals(  "ooo"), "equals: String");
			test(!s.equals("ooooo"), "equals: String");
		}

		{ // substr
			var s = new DynString("steal");

			var t = s.substr(1,4);
			test(t.equals("tea"), "substr(int,int): wrong result");

			t = s.substr(1);
			test(t.equals("teal"), "substr(int): wrong result");
		}

		{ // concat
			var a = new DynString("Hello");
			var b = new DynString("World");
			var c = a.concat(" ").concat(b).concat("!");
			test(c.equals("Hello World!"), "concat: wrong result");
		}
		//*/
	}

}
