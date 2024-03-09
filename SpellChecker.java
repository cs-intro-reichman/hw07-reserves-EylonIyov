import java.util.Dictionary;

public class SpellChecker {


	public static void main(String[] args) {
		String word = args[0];
		int threshold = Integer.parseInt(args[1]);
		String[] dictionary = readDictionary("dictionary.txt");
		String correction = spellChecker(word, threshold, dictionary);
		System.out.println(correction);
		
	}

	public static String tail(String str) {
		return str.substring(1,str.length());
	}

	public static String head(String str){
		return str.substring(0,1);
	}

	public static int levenshtein(String word1, String word2) {
		word1 = word1.toLowerCase();
		word2 = word2.toLowerCase();
		if(word1.length() == 0){
			return word2.length();
		}
		else if(word2.length() == 0){
			return word1.length();
		}
		else if(head(word1).equals(head(word2))){
			return levenshtein(tail(word1), tail(word2));
		}else{
			return 1+Math.min(levenshtein(tail(word1), word2),Math.min(levenshtein(word1, tail(word2)), levenshtein(tail(word1), tail(word2))));
		}
	}

	public static String[] readDictionary(String fileName) {
		String[] dictionary = new String[3000];
		In in = new In(fileName);
		for (int i = 0; i < 3000; i++){
			dictionary[i] =  in.readLine();
		}
		return dictionary;
	}

	public static String spellChecker(String word, int threshold, String[] dictionary) {
		String lower=word.toLowerCase();
		String match=lower;
		int minLev=1000;
		for (String dictWord : dictionary) {
			int lev = levenshtein(lower, dictWord);
			if (lev < minLev && lev <= threshold) {
				minLev = lev;
				match = dictWord;
			}
		}
		return match;
		}
	}

