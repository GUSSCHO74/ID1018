// LinkedNumberSequence represents a sequence of real numbers.
// Such a sequence is defined by the interface NumberSequence.
// The class uses linked nodes to store the numbers in the sequence.

public class LinkedNumberSequence implements NumberSequence {
	private class Node {
		public double number;
		public Node next;

		public Node (double number){
			this.number = number;
			next = null;
		}
	}
	// the first node in the node-sequence
    private Node first;

    // create the sequence
    public LinkedNumberSequence (double[] numbers){
		if (numbers.length < 2)
		    throw new IllegalArgumentException("not a sequence");

		first = new Node(numbers[0]);
        Node n = first;

		for (int i = 1; i < numbers.length; i++){
			n.next = new Node(numbers[i]);
			n = n.next;
		}
	}

    // toString returns the character string representing this sequence
	public String toString (){
		String s = "";
		Node n = first;
		
		while (n.next != null){
		    s = s + n.number + ", ";
		    n = n.next;
		}
		s = s + n.number;

		return s;
	}

    // add code here
	public int length(){
		Node node = first;
		int count = 0;

		while(node != null){
			count++;
			node = node.next;
		}

		return count;
	}

	public double upperBound(){
		Node node = first;
		double max = node.number;

		while(node.next != null){
			node = node.next;

			if(max < node.number)
				max = node.number;
		}

		return max;
	}

	public double lowerBound(){
		Node node = first;
		double min = node.number;

		while(node.next != null){
			node = node.next;

			if(min > node.number)
				min = node.number;
		}

		return min;
	}

	public double numberAt(int position) throws IndexOutOfBoundsException {
		if(position < 0 || position > length() - 1)
			throw new IndexOutOfBoundsException();

		Node node = first;

		for(int i = 1; i <= position; i++)
			node = node.next;

		return node.number;
	}

	public int positionOf(double number){
		Node node = first;
		int i = 0;

		while(node != null){
			if(node.number == number)
				return i;

			node = node.next;
			i++;
		}

		return -1;
	}

	public boolean isIncreasing(){
		Node node = first;

		while(node.next != null){
			if(node.number > node.next.number) 
				return false;

			node = node.next;
		}

		return true;
	}

	public boolean isDecreasing(){
		Node node = first;

		while(node.next != null){
			if(node.number < node.next.number)
				return false;

			node = node.next;
		}

		return true;
	}

	public boolean contains(double number){
		return positionOf(number) != -1;
	}

	public void add(double number){
		Node node = first;

		while(node.next != null)
			node = node.next;

		node.next = new Node(number);
	}

	public void insert(int position, double number) throws IndexOutOfBoundsException {
		if(position < 0 || position > length())
			throw new IndexOutOfBoundsException();

		Node newNode = new Node(number);

		if(position == 0){
			newNode.next = first;
			first = newNode;
		}
		else {
			Node node = first;

			for(int i = 1; i < position; i++)
				node = node.next;

			newNode.next = node.next;
			node.next = newNode;
		}
	}

	public void removeAt(int position) throws IndexOutOfBoundsException, IllegalStateException {
		if (position < 0 || position > length() - 1)
			throw new IndexOutOfBoundsException();

		if (length() <= 2)
			throw new IllegalStateException();

		if (position == 0){
			first = first.next;
		}
		else {
			Node node = first;

			for(int i = 1; i < position; i++)
				node = node.next;

			node.next = node.next.next;
		}
	}

	public double[] asArray(){
		double[] numbers = new double[length()];
		Node node = first;
		int i = 0;

		while(node != null){
			numbers[i] = node.number;
			node = node.next;
			i++;
		}

		return numbers;
	}
}
