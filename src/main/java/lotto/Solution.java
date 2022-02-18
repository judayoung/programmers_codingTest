package lotto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Solution {

    public int[] solution(int[] lottos, int[] win_nums) {
        List<Integer> lottoList = convertLottos(lottos);
        List<Integer> win_numsList = convertToList(win_nums);

        int countNumbers = lottoList.size();
        int matchedNumbers = getMatchingCount(lottoList, win_numsList);
        Lotto lotto = new Lotto(countNumbers, matchedNumbers);

        int[] answer = getHighLow(lotto);
        return answer;
    }

    public static void main(String[] args) {
        int[] lottos={20,40,30,2,35,3};
        int[] win_nums={2,20,30,40,35,3};

        Solution sol=new Solution();
        int[] answer=sol.solution(lottos,win_nums);
        for(int i=0;i<answer.length;i++){
            System.out.println(answer[i]);
        }

    }


    //lottos를 0과 숫자로 구분하여 숫자 리스트를 리턴하는 메서드
    public List<Integer> convertLottos(int[] lottos) {
        List<Integer> list = convertToList(lottos);
        list = getNumberList(list);
        return list;
    }

    //1. int[] 를 List<Integer>로 변환하는 메서드 (정렬하는 이유 : 빠르게 매칭하기 위해)
    public List<Integer> convertToList(int[] intArray) {
        List<Integer> list = Arrays.stream(intArray)
                .boxed()
                .collect(Collectors.toList());
        Collections.sort(list);
        return list;
    }

    //2. 0을 제외한 숫자 리스트를 리턴하는 메서드
    // 주의. [0,0,0,0,0,0]
    public List<Integer> getNumberList(List<Integer> lottos) {
        List<Integer> numberList = new ArrayList<>();
        for (int i = 0; i < lottos.size(); i++) {
            if (lottos.get(i) != 0) {
                numberList = lottos.subList(i, lottos.size());
                break;
            }
        }
        return numberList;
    }

    //lottos의 숫자가 win_nums에 몇 개 있는지 판별하는 메서드
    public int getMatchingCount(List<Integer> lottoList, List<Integer> win_numsList) {
        int match = 0;
        if(lottoList==null){
            return match;
        }

        for (int i = 0; i < lottoList.size(); i++) {
            if (win_numsList.contains(lottoList.get(i))) {
                match++;
            }
        }
        return match;
    }

    //Lotto 클래스로, 맞출수 있는 최대 개수와 최소 개수를 리턴하는 메서드
    public int[] getHighLow(Lotto lotto) {
        int[] highLow = new int[2];
        highLow[0] = getRank(6 - lotto.getNotMatched());
        highLow[1] = getRank(lotto.matchedNumbers);
        return highLow;
    }

    //맞춘 개수를 넣으면 순위를 반환하는 함수
    public int getRank(int matched) {
        if(matched>=2){
            return 7-matched;
        }else{
            return 6;
        }
    }
}

@AllArgsConstructor
@Getter
class Lotto {
    int countNumbers;  //지워지지 않은 숫자의 개수
    int matchedNumbers;  //맞춘 숫자의 개수

    public int getNotMatched() {
        return countNumbers - matchedNumbers;
    }
}
