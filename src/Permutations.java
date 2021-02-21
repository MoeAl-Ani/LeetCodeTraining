import java.util.*;
import java.util.stream.Collectors;

public class Permutations {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> answers = new ArrayList<>();
        List<Integer> numsArray = Arrays.stream(nums).boxed().collect(Collectors.toList());
        List<Integer> subAnswer = new ArrayList<>();

        findAllPossible(numsArray, subAnswer, answers);
        return answers;
    }

    void findAllPossible(List<Integer> nums, List<Integer> subAnswer, List<List<Integer>> answers) {

        if (nums.isEmpty()) {
            answers.add(subAnswer);
            return;
        }

        for(int i = 0; i < nums.size(); i++) {
            List<Integer> numsCopy = new ArrayList<>(nums);
            List<Integer> subAnswersCopy = new ArrayList<>(subAnswer);

            subAnswersCopy.add(numsCopy.remove(i));
            findAllPossible(numsCopy, subAnswersCopy, answers);
        }
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 0};
        int[] nums2 = {1, 2, 3};
        int[] nums3 = {5, 4, 6, 2};
        int[] nums4 = {6,3,2,7,4,-1};
        int[] nums5 = {1};
        Permutations permutations = new Permutations();
        System.out.println(permutations.permute(nums1));
        System.out.println(permutations.permute(nums2));
        System.out.println(permutations.permute(nums3));
        System.out.println(permutations.permute(nums4));
        System.out.println(permutations.permute(nums5));
    }
}

