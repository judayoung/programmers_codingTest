package lotto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

    Solution solution = new Solution();

    @DisplayName("int[]를 List<Integer>로 정렬해서 반환한다.")
    @Test
    void convertToList() {
        //given
        int[] array = {1, 6, 3, 2, 8, 9};
        //when
        List<Integer> list = solution.convertToList(array);
        //then
        List<Integer> list2 = Arrays.asList(1, 2, 3, 6, 8, 9);
        assertThat(list).isEqualTo(list2);
    }

    @DisplayName("List<Integer>에서 0이 아닌 숫자를 반환한다.")
    @Test
    void getNumberList() {
        //given
        List<Integer> list = Arrays.asList(0, 0, 0, 6, 8, 9);
        //when
        list = solution.getNumberList(list);
        //then
        List<Integer> list2 = Arrays.asList(6, 8, 9);
        assertThat(list).isEqualTo(list2);
    }

    @DisplayName("list1에 있는 숫자 중에 list2에 있는 숫자의 총 개수를 리턴한다.")
    @Test
    void getMatchingCount() {
        //given
        List<Integer> list1 = Arrays.asList(1, 2, 3);
        List<Integer> list2 = Arrays.asList(1, 2, 5, 6, 7, 8);
        List<Integer> list3=new ArrayList<>();
        //when
        int match1 = solution.getMatchingCount(list1, list2);
        int match2=solution.getMatchingCount(list3,list2);
        //then
        assertThat(match1).isEqualTo(2);
        assertThat(match2).isEqualTo(0);
    }

    @DisplayName("지워지지 않은 숫자와 맞춘 숫자 개수를 통해 최고 등수와 최저 등수를 반환한다.")
    @Test
    void getHighLow() {
        //given
        Lotto lotto = new Lotto(3, 2);
        //when
        int number = 6;
        //then
        int max = number - (3 - 2);  //5
        int min = 2;
        int[] highLow= {number + 1 - max, number + 1 - min};  //2,5
        assertThat(solution.getHighLow(lotto)).isEqualTo(highLow);
    }

    @DisplayName("6개 맞추면 1등, 5개 맞추면 2등, ... 1개나 0개를 맞추면 6등을 반환한다.")
    @Test
    void getRank(){
        //given
        int match1=5;
        int match2=0;
        //when
        int rank1= solution.getRank(match1);
        int rank2 = solution.getRank(match2);
        //then
        assertThat(rank1).isEqualTo(2);
        assertThat(rank2).isEqualTo(6);
    }

}