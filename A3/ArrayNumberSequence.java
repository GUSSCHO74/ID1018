// ArrayNumberSequence represents a sequence of real numbers.
// Such a sequence is defined by the interface NumberSequence.
// The class uses an array to store the numbers in the sequence.

public class ArrayNumberSequence implements NumberSequence {
	// numbers in the sequence
    private double[] numbers;

    // create the sequence
    public ArrayNumberSequence (double[] numbers){
		if (numbers.length < 2)
		    throw new IllegalArgumentException("not a sequence");
			
		this.numbers = new double[numbers.length];

		for (int i = 0; i < numbers.length; i++)
		    this.numbers[i] = numbers[i];
	}
    // toString returns the character string representing this sequence
	public String toString (){
		String s = "";
		for (int i = 0; i < numbers.length - 1; i++)
		    s = s + numbers[i] + ", ";
		s = s + numbers[numbers.length - 1];

		return s;
	}
	
    // add code here
    public int length(){
		return numbers.length;
	}

    public double upperBound(){
		double max = numbers[0];
		for(int i = 1; i < length(); i++){
			max = (max > numbers[i]) ? max : numbers[i];
		}		
	
		return max;
	}

    public double lowerBound(){
		double min = numbers[0];
		for(int i = 1; i < length(); i++)
			min = (min < numbers[i]) ? min : numbers[i];		
		
		return min;
	}

    public double numberAt(int position){
		if(position >= length()) throw new IndexOutOfBoundsException(); 
		
		return numbers[position];
	}

    public int positionOf(double number) {
		for(int i = 0; i < length(); i++){
			if(number == numbers[i]) 
				return i; 
		}
		return -1;
	}

    public boolean isIncreasing(){
		for(int i = 1; i < length(); i++){
			if(numbers[i] < numbers[i - 1]) 
				return false; 
		}
		return true;
	}

    public boolean isDecreasing(){
		for(int i = 1; i < length(); i++){
			if(numbers[i] > numbers[i - 1]) 
				return false; 
		}
		return true;
	}

    public boolean contains(double number){
		for(int i = 0; i < length(); i++){
			if (numbers[i] == number) 
				return true;
		}
		return false;
	}

    public void add(double number){
		double[] newArray = new double[length() + 1];
		for(int i = 0; i < length(); i++){
			newArray[i] = numbers[i];
		}
		
		newArray[length()] = number;
		numbers = newArray;
	}

    public void insert(int position, double number){
		if(position > length())
			throw new IndexOutOfBoundsException("Invalid position");

		double[] newArray = new double[length() + 1];
		int added = 0;

		for(int i = 0; i < newArray.length; i++){
			if (i == position) {
				newArray[i] = number;
			} else {
				newArray[i] = numbers[added];
				added++;
			}
		}
		numbers = newArray;
	}

    public void removeAt(int position){
		if(position >= length()) throw new IndexOutOfBoundsException();
		if(position <= 2) throw new IllegalStateException();

		double[] newArray = new double[length() - 1];
		int added = 0;

		for(int i = 0; i < length(); i++){
			if(i != position){
				newArray[added] = numbers[i];
				added++;
			}
		}
		numbers = newArray;
	}

    public double[] asArray(){
		double[] newArray = new double[length()];

    	for (int i = 0; i < length(); i++) {
        	numbers[i] = numbers[i];
    	}

    	return newArray;
	}
}
