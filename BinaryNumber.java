//Name :  CWID : 

public class BinaryNumber{
	private int data[];
	private boolean overflow;
/**
 * A constructor for creating a binary number of length and consisting only of zeros.
 * @param length
 */
	public BinaryNumber(int length){
		data = new int[length];
		for(int i = 0; i < length; i++){
			data[i] = 0;
		}
	}
/**
 * A constructor for creating a binary number given a string. 
 * @param str
 */
	public BinaryNumber(String str){
		int length = str.length();
		data = new int[length];
		for(int i = 0; i < length; i++){
			char ch = str.charAt(i);
			if (ch == '0' || ch == '1') {
				data[i] = Character.getNumericValue(ch);
			} else {
				System.out.println("Only put in 0 or 1");
				break;
			}
		}
	}
	public int getLength(){
		return data.length;
	}
/**
 * obtaining a digit of a binary number given an index.
 * @param index
 * @return digit
 */
	public int getDigit(int index){
		if (index < data.length && index >= 0){
			return data[index];
		}else{
			System.out.println ("Index out of bound!"); //the index is out of bounds
			return 0;
		}
	}
// toDecimal() transforming a binary number to its decimal notation.
//each element of decimal = array[index] * (2 ^ index) then sum all of the elements
	public int toDecimal() {
		int decimal = 0;
		for (int i = 0; i< data.length; i++) {
			decimal += data[i] * Math.pow(2, i);
		}
		return decimal;
	}
/**
 * shifting all digits in a binary number any number of places to the right,
 * as indicated by a parameter amountToShift.
 * @param amount
 */
	public void shiftR(int amount) {
		if (amount < 0) {
			System.out.println("Amount should be positive");
		}
		int array[] = new int[data.length + amount];
		for (int i = 0; i < amount; i++) {
			array[i] = 0;
		}
		for (int i = 0; i < data.length; i++) {
			array[i + amount] = data[i];
		}
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i]);
		}
		System.out.println();
	}
/**
 * adding two binary numbers, one is the binary number that receives the message 
 * and the other is given as a parameter.
 * @param aBinaryNumber
 */
	public void add(BinaryNumber aBinaryNumber){
		int carry = 0;
		//int sum[] = new int[aBinaryNumber.getLength()];
		if (data.length == aBinaryNumber.getLength()) {
			for (int i = 0; i < data.length; i++) {
				int digitNum = carry + data[i] + aBinaryNumber.getDigit(i);
				if (digitNum == 0) {
					data[i] = 0;
					carry = 0;
				}
				if (digitNum == 1) {
					data[i] = 1;
					carry = 0;
				}
				if (digitNum == 2) {
					data[i] = 0;
					carry = 1;
				}
				if (digitNum == 3) {
					data[i] = 1;
					carry = 1;
				}
				if (carry == 1) {
					overflow = true;
				}
			}
			if(carry == 1) {
				System.out.println("Overflow!");
			} else {
				for(int j = 0; j < data.length; j++) {
					System.out.print(data[j]);
				}
				System.out.println();
			}
		} else {
			System.out.println("Lengths Not Match!.");
		}
	}
/**
 * for transforming a binary number to a String. 
 * If the number is the result of an overflow, the string “Overflow” should be returned.
 */
	public String toString() {
		if (overflow == true) {
			return "Overflow";
		} else {
			String s = "";
			for (int i = 0; i < data.length; i++) {
				s += data[i];
			}
			return s;
		}
	}
// set overflow equals to false
	public void clearOverflow() {
		overflow = false;
		System.out.println("Overflow cleared!");
	}
// BinaryNumber Test
	public static void main(String[] args) {
		BinaryNumber data1 = new BinaryNumber("1011");
		BinaryNumber data2 = new BinaryNumber("0100");
		BinaryNumber data3 = new BinaryNumber("216");
		BinaryNumber data4 = new BinaryNumber("1110");
		BinaryNumber data5 = new BinaryNumber("111");
		BinaryNumber data6 = new BinaryNumber("abcd");
		System.out.println(data1);
		System.out.println(data2);
		System.out.println(data3);
		System.out.println(data4);
		System.out.println(data5);
		System.out.println(data6);
		System.out.println("---------------------");
		data1.add(data2);
		data1.clearOverflow();
		data4.add(data2);
		data1.add(data5);
		System.out.println(data1.toString());
		System.out.println(data1.toDecimal());
		data1.shiftR(3);
	}
}
