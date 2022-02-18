package newID;

public class Solution {
    public String solution(String new_id) {
        //1. 소문자화
        String id = new_id.toLowerCase();
        System.out.println(id);

        //2. 소문자, -, _, .을 제외한 문자 제거
        id = id.replaceAll("[^a-z|0-9|-|_|.]", "");
        System.out.println(id);

        //3. 마침표가 2번 이상 연속된 부분 하나의 마침표로 치환
        id = id.replaceAll("[.]+", ".");
        System.out.println(id);

        //4. 마침표가 처음이나 끝에 위치한다면 제거
        if (id.length()>0 && id.charAt(0) == '.') {
            if (id.length() <= 1) {
                id = "";
            } else {
                id = id.substring(1);
            }
        }
        System.out.println(id);
        if (id.length()>0 && id.charAt(id.length() - 1) == '.') {
            if (id.length() <= 1) {
                id = "";
            } else {
                id = id.substring(0, id.length() - 1);
            }
        }
        System.out.println(id);

        //5. new_id가 빈 문자열이라면, new_id에 "a"를 대입합니다.
        if (id.equals("")) {
            id = "a";
        }
        System.out.println(id);

        //6. new_id의 길이가 16자 이상이라면 첫 15자만을 남깁니다.
        //만약 제거 후 마침표가 new_id의 끝에 위치한다면 끝에 위치한 마침표도 제거합니다.
        if (id.length() >= 16) {
            id = id.substring(0, 15);
            if (id.charAt(id.length() - 1) == '.') {
                id = id.substring(0, id.length() - 1);
            }
        }
        System.out.println(id);

        //7. new_id의 길이가 2자 이하라면 new_id의 마지막 문자를 3이 될때까지 반복합니다.
        while (id.length() <= 2) {
            id += id.substring(id.length() - 1);
        }
        System.out.println(id);

        String answer = id;
        return answer;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        String id = "abcdefghijklmn.p";
        id = sol.solution(id);

        System.out.println("result : " + id);

    }
}
