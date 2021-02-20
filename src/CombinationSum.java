import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class CombinationSum {

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Stack<Integer> intermediateResult = new Stack<>();
        findAnswer(target, 0, candidates, result, intermediateResult);
        return result;
    }

    private void findAnswer(int sum, int index, int[] candidates, List<List<Integer>> answers, Stack<Integer> intermediateResult) {
        if (sum == 0) {
            List<Integer> subResult = new ArrayList<>();
            intermediateResult.iterator().forEachRemaining(subResult::add);
            answers.add(subResult);
            return;
        }
        for (int i = index; i < candidates.length; i++) {
            int newSum = sum - candidates[i];
            if (newSum >= 0) {
                intermediateResult.push(candidates[i]);
                findAnswer(newSum, i, candidates, answers, intermediateResult);
                if (!intermediateResult.isEmpty()) intermediateResult.pop();
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {2, 3, 6, 7};

        int target = 7;
        CombinationSum combinationSum = new CombinationSum();
        List<List<Integer>> answers = combinationSum.combinationSum(arr, target);
        System.out.println(answers);
    }
}