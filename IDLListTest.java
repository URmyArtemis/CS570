//Name:  CWID:
public class IDLListTest {
	public static void main(String[] args){
		IDLList<Integer> idllist = new IDLList<Integer>();
		IDLList<String> idllist1 = new IDLList<String>();
		IDLList<Double> idllist2 = new IDLList<Double>();
		
//		The test of Integer
//		 
//		 
		System.out.println("The test of Integer");
		//adds elem = 100 at the head (i.e. it becomes the first element of the list).
		System.out.println(idllist.add(100));
		System.out.println(idllist.add(2, 7));//false
		System.out.println(idllist.toString());
		//adds elem = 10 at the head (i.e. it becomes the first element of the list).
		System.out.println(idllist.add(10));
		//System.out.println(idllist.toString());
		System.out.println("-------------");
		/*adds elem = 200 as the new last element of the list (i.e. at the tail).*/
		//System.out.println(idllist.append(200));
		//adds elem = 20 as the new last element of the list (i.e. at the tail).
		System.out.println(idllist.append(20));
		//adds elem = 150 as the new last element of the list (i.e. at the tail).
		System.out.println(idllist.append(150));
		System.out.println("-------------");
		//adds elem =100 at position index = 2 (counting from wherever head is).
		System.out.println(idllist.add(2, 100));
		//adds elem =500 at position index = 5 (counting from wherever head is).
		System.out.println(idllist.add(5, 500));
		System.out.println("-------------");
		//System.out.println(idllist + "^^^^^^");
		//get(1) returns the 2nd element of the list.
		System.out.println(idllist.get(1));
		//getHead() returns head data.
		System.out.println(idllist.getHead());
		//getLast() returns tail data.
		System.out.println(idllist.getLast());
		//the size of the list.
		System.out.println(idllist.size());
		//Return false because elem = 1 not in the list.
		System.out.println(idllist.remove(1));
		//Return true because elem = 10 in the list.
		System.out.println(idllist.remove(10));
		//Return null because the size is 5.
		System.out.println(idllist.removeAt(100));
		//removes and returns the element at the head.
		System.out.println(idllist.remove());
		//removes and returns the element at the tail.
		System.out.println(idllist.removeLast());
		System.out.println(idllist.remove());
		System.out.println(idllist.remove());
		//System.out.println(idllist.toString());
		System.out.println("+++++++++++++");
		
		//The test of String.
		System.out.println("The test of String");
		System.out.println(idllist1.add("BRZ"));
		System.out.println(idllist1.add("Subaru"));
		System.out.println(idllist1.toString());
		System.out.println(idllist1.append("Mazda"));
		System.out.println(idllist1.append("MX-5 Miata"));
		System.out.println(idllist1.get(1));
		System.out.println(idllist1.get(100));
		System.out.println(idllist1.get(0));
		System.out.println(idllist1.getHead());
		System.out.println(idllist1.getLast());
		System.out.println(idllist1.size());
		System.out.println(idllist1.remove("ABC"));
		System.out.println(idllist1.removeAt(10));
		System.out.println(idllist1.remove());
		System.out.println(idllist1.remove());
		System.out.println(idllist1.removeLast());
		System.out.println(idllist1.removeAt(0));
        System.out.println("*************");

		//The test of Double.
        System.out.println("The test of Double.");
		System.out.println(idllist2.add(2.0));
		//System.out.println(idllist2.add(3.0));
		System.out.println(idllist2.add(1, 12.0));
		System.out.println(idllist2.append(3.0));
		System.out.println(idllist2.get(1));
		System.out.println(idllist2.getHead());
		System.out.println(idllist2.getLast());
		System.out.println(idllist2.size());
		System.out.println(idllist2.remove(3.0) + "  @");
		System.out.println(idllist2.toString());
		System.out.println(idllist2.remove());
		System.out.println(idllist2.removeAt(1));
		System.out.println(idllist2.remove());
		System.out.println(idllist2.removeLast());
	}
}
