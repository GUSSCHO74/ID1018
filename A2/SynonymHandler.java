import java.io.*;    // FileReader, BufferedReader, PrintWriter, IOException

class SynonymHandler
{
	// readSynonymData reads the synonym data from a given file
	// and returns the data as an array
    public static String[] readSynonymData (String synonymFile) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(synonymFile));
        int numberOfLines = 0;
        String synonymLine = reader.readLine();
        while (synonymLine != null){
			numberOfLines++;
			synonymLine = reader.readLine();
		}
		reader.close();

		String[] synonymData = new String[numberOfLines];
        reader = new BufferedReader(new FileReader(synonymFile));
		for (int i = 0; i < numberOfLines; i++)
		    synonymData[i] = reader.readLine();
		reader.close();

		return synonymData;
    }

    // writeSynonymData writes a given synonym data to a given
    // file
    public static void writeSynonymData (String[] synonymData, String synonymFile) throws IOException{
        PrintWriter writer = new PrintWriter(synonymFile);
        for (String synonymLine: synonymData)
            writer.println(synonymLine);
        writer.close();
    }

    // synonymLineIndex accepts synonym data, and returns the
    // index of the synonym line corresponding to a given word.
    // If the given word is not present, an exception of
    // the type IllegalArgumentException is thrown.
	private static int synonymLineIndex (String[] synonymData, String word) throws IllegalArgumentException{
		int delimiterIndex = 0;
		String w = "";
		int i = 0;
		boolean wordFound = false;
		while (!wordFound  &&  i < synonymData.length)
		{
		    delimiterIndex = synonymData[i].indexOf('|');
		    w = synonymData[i].substring(0, delimiterIndex).trim();
		    if (w.equalsIgnoreCase(word))
				wordFound = true;
			else
				i++;
	    }

	    if (!wordFound)
	        throw new IllegalArgumentException(word + " not present");
	    return i;
	}

    // getSynonymLine accepts synonym data, and returns
    // the synonym line corresponding to a given word.
    // If the given word is not present, an exception of
    // the type IllegalArgumentException is thrown.
    public static String getSynonymLine (String[] synonymData, String word) throws IllegalArgumentException{
		int index = synonymLineIndex(synonymData, word);

	    return synonymData[index];
	}

    // addSynonymLine accepts synonym data, and adds a given
    // synonym line to the data.
	public static String[] addSynonymLine (String[] synonymData, String synonymLine)
	{
		String[] synData = new String[synonymData.length + 1];
		for (int i = 0; i < synonymData.length; i++)
		    synData[i] = synonymData[i];
		synData[synData.length - 1] = synonymLine;

	    return synData;
	}

    // removeSynonymLine accepts synonym data, and removes
    // the synonym line corresponding to a given word.
    // If the given word is not present, an exception of
    // the type IllegalArgumentException is thrown.
	public static String[] removeSynonymLine (String[] synonymData, String word) throws IllegalArgumentException{
		// add code here
        String[] newSynonymData = new String[synonymData.length - 1];
        
        int added = 0;
        
        for(int i = 0; i < synonymData.length; i++){
            String synonymLine = synonymData[i];
            
            String firstSynonym = synonymLine.split(" ")[0];

            if(!firstSynonym.equals(word)){
                if(added < newSynonymData.length){
                    newSynonymData[added] = synonymData[i];
                    added++; 
                }
                else{
                    throw new IllegalArgumentException("The word \"" + word + "\" is not present.");
                }
            }
        }
        return newSynonymData;
	}

    // getSynonyms returns synonyms in a given synonym line.
	private static String[] getSynonyms(String synonymLine)
	{
        // add code here
        String[] synonymsList = synonymLine.split("[|]")[1].split("[,]");
        for(int i = 0; i < synonymsList.length; i++)
            synonymsList[i] = synonymsList[i].trim();

        return synonymsList;
	}

    // addSynonym accepts synonym data, and adds a given
    // synonym for a given word.
    // If the given word is not present, an exception of
    // the type IllegalArgumentException is thrown.
	public static void addSynonym (String[] synonymData, String word, String synonym) throws IllegalArgumentException{
        // add code here
        int wordIndex = synonymLineIndex(synonymData, word);
        String synonymLine = synonymData[wordIndex];

        synonymLine += ", " + synonym;
        synonymData[wordIndex] = synonymLine; 
	}

    // removeSynonym accepts synonym data, and removes a given
    // synonym for a given word.
    // If the given word or the given synonym is not present, an
    // exception of the type IllegalArgumentException is thrown.
    // If there is only one synonym for the given word, an
    // exception of the type IllegalStateException is thrown.
	public static void removeSynonym (String[] synonymData, String word, String synonym) throws IllegalArgumentException, IllegalStateException{
        // add code here
        int lineIndex = synonymLineIndex(synonymData, word);
        String synonymLine = getSynonymLine(synonymData, word);
        String[] synonyms = getSynonyms(synonymLine);

        if(synonyms.length == 1) 
            throw new IllegalStateException("There is only one synonym.");
        
        Boolean synonymFound = false;
        String returnSynLine = word + " | ";
        String[] newSynonyms = new String[synonyms.length - 1];
        int added = 0;
        
        for(int synonymIndex = 0; synonymIndex < synonyms.length; synonymIndex++){
            if(!synonyms[synonymIndex].equals(synonym)){
                newSynonyms[added] = synonyms[synonymIndex];
                added++;
            } 
            else{
                // System.out.println("hjehjeh");
                synonymFound = true;
            }
        }

        if(synonymFound == false)
            throw new IllegalArgumentException(synonym + " is not present.");

        for(int synonymIndex = 0; synonymIndex < newSynonyms.length; synonymIndex++){
            if(synonymIndex > 0){
                returnSynLine += ", " + newSynonyms[synonymIndex];
            }
            else{
                returnSynLine += newSynonyms[synonymIndex];
            }
        }
        synonymData[lineIndex] = returnSynLine;
	}

    // sortIgnoreCase sorts an array of strings, using
    // the selection sort algorithm
    private static void sortIgnoreCase (String[] strings){
        // add code here
        int last = strings.length - 1;
        int min = 0;
        for(int i = 0; i < strings.length; i++){
            min = i;
            for(int j = i + 1; j <= last; j++){
                if (strings[j].compareToIgnoreCase(strings[min]) < 0)
                    min = j;
            }
            if ( min != i){
                String str = strings[i];
                strings[i] = strings[min];
                strings[min] = str;
            }
        }
    }

    // sortSynonymLine accepts a synonym line, and sorts
    // the synonyms in this line
    private static String sortSynonymLine (String synonymLine){
	    // add code here
        String word = synonymLine.split("[|]")[0].trim();
        String[] synonyms = getSynonyms(synonymLine);

        // System.out.println(java.util.Arrays.toString(synonyms));
        sortIgnoreCase(synonyms);
        return word + " | " + String.join(", ", synonyms);
	}

    // sortSynonymData accepts synonym data, and sorts its
    // synonym lines and the synonyms in these lines
	public static void sortSynonymData (String[] synonymData){
        // add code here
        sortIgnoreCase(synonymData); 

        for(int synonymDataIndex = 0; synonymDataIndex < synonymData.length; synonymDataIndex++) {
            synonymData[synonymDataIndex] = sortSynonymLine(synonymData[synonymDataIndex]);
        }
	}
}
