package blackuser;

import java.util.*;

// 신고
public class Problem1 {

    public int[] solution(String[] id_list, String[] report, int k) {
        int[] answer=new int[id_list.length];

        // black user : {신고한 유저1, 신고한 유저2, ... }
        Map<String, Set<String>> black_user_map=get_black_user_map(report);

        for(int i=0;i<id_list.length;i++){
            String id=id_list[i];
            if(black_user_map.containsKey(id) && black_user_map.get(id).size()>=k ){
                Set<String> white_user_set=black_user_map.get(id);
                Iterator<String> iterator=white_user_set.iterator();
                while(iterator.hasNext()){
                    String white_user=iterator.next();
                    int index= Arrays.asList(id_list).indexOf(white_user);
                    answer[index]++;
                }
            }
        }

        return answer;
    }

    public Map<String, Set<String>> get_black_user_map(String[] report){
        Map<String, Set<String>> black_user_map=new HashMap<>();
        for(int i=0;i< report.length;i++){
            String[] users=report[i].split(" ");
            String white_user=users[0];
            String black_user=users[1];

            Set<String> white_user_set=(!black_user_map.containsKey(black_user))? new HashSet<>() : black_user_map.get(black_user);
            white_user_set.add(white_user);
            black_user_map.put(black_user,white_user_set);
        }
        return black_user_map;
    }

    // 66프로 정답률(시간초과)
    public int[] solution1(String[] id_list, String[] report, int k) {

        HashMap<String, Integer> id_map = new HashMap<>();
        int[] answer = new int[id_list.length];

        for(int i=0;i<id_list.length;i++){
            String user=id_list[i];
            HashSet people=new HashSet<String>();

            for(int j=0;j<report.length;j++){
                String report_user=report[j].split(" ")[0];
                String reported_user=report[j].split(" ")[1];
                if(reported_user.equals(user)){
                    people.add(report_user);
                }
            }

            if(people.size()>=k){
                Iterator<String> it=people.iterator();
                while(it.hasNext()){
                    String answer_id = it.next();
                    if(id_map.get(answer_id)==null){
                        id_map.put(answer_id,1);
                    }else{
                        id_map.put(answer_id,id_map.get(answer_id)+1);
                    }
                }
            }
        }

        for(int i=0;i<id_list.length;i++){
            if(id_map.get(id_list[i])!=null){
                answer[i]=id_map.get(id_list[i]);
            }
        }

        return answer;
    }
}