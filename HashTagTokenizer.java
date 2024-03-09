

public class HashTagTokenizer {

	public static void main(String[] args) {

		String hashTag = args[0];
		String []dictionary = readDictionary("dictionary.txt");
		//System.out.println(existInDictionary(hashTag, dictionary));
		breakHashTag(hashTag, dictionary);
	}

	public static String[] readDictionary(String fileName) {
		String[] dictionary = new String[3000];
		In in = new In(fileName);
		for (int i = 0; i < 3000; i++){
			dictionary[i] =  in.readLine();
		}
		return dictionary;
	}

	public static boolean existInDictionary(String word, String[] dictionary) {
		int low = 0;
		int high = dictionary.length-1;
		while (low < high) {
			int med = ((low+high)/2)+1;
			int diff = word.compareTo(dictionary[med]);
			if (diff == 0){
				return true;
			}
			if (diff > 0){
				low = med + 1; 
			}
			else{
				high = med -1;
			}
		}
		return false;
	}

	public static void breakHashTag(String hashtag, String[] dictionary) {

		// Base case: do nothing (return) if hashtag is an empty string.
        if (hashtag.isEmpty()) {
            return;
        }
        int N = hashtag.length();
		
        for (int i = 1; i <= N; i++) {
			if(existInDictionary(hashtag.substring(0, i), dictionary)){
				System.out.println(hashtag.substring(0, i));
				breakHashTag(hashtag.substring(i, N), dictionary);
				return;
			}
		
        }
    }

}
