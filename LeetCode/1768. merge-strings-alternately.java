class Solution {
    public String mergeAlternately(String word1, String word2) {
        int len1 = word1.length();
        int len2 = word2.length();

        StringBuilder sb = new StringBuilder();
        if (len1 >= len2) {
            for (int i=0; i<len2; i++) {
                sb.append(word1.charAt(i));
                sb.append(word2.charAt(i));
            }

            for (int i=len2; i<len1; i++) {
                sb.append(word1.charAt(i));
            }
        } else {
            for (int i=0; i<len1; i++) {
                sb.append(word1.charAt(i));
                sb.append(word2.charAt(i));
            }
            for (int i=len1; i<len2; i++) {
                sb.append(word2.charAt(i));
            }
        }
        return sb.toString();
    }
}