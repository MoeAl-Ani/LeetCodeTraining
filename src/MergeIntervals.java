import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MergeIntervals {

    public int[][] merge(int[][] intervals) {
        if (intervals.length == 1)
            return intervals;
        List<List<Integer>> ans = new ArrayList<>();
        List<List<Integer>> intervalsArray = new ArrayList<>();
        for (int i = 0; i < intervals.length; i++) {
            List<Integer> interval = new ArrayList<>();
            interval.add(intervals[i][0]);
            interval.add(intervals[i][1]);
            intervalsArray.add(interval);
        }

        PriorityQueue<Integer> minPq = new PriorityQueue<>(Integer::compareTo);
        PriorityQueue<Integer> maxPq = new PriorityQueue<>((integer, t1) -> integer.compareTo(t1) * -1);

        Set<List<Integer>> anss = new HashSet<>();
        mergeOverlapIntervals(intervalsArray,anss, minPq, maxPq);
        int[][] result = new int[anss.size()][2];
        int index = 0;
        for (List<Integer> subs : anss) {
            result[index][0] = subs.get(0);
            result[index][1] = subs.get(1);
            index++;
        }
        return result;
    }

    private void mergeOverlapIntervals(List<List<Integer>> intervals,Set<List<Integer>> ans,  PriorityQueue<Integer> minPq, PriorityQueue<Integer> maxPq) {

        int index = 0;
        for (List<Integer> interval : intervals) {
            minPq.add(interval.get(0));
            minPq.add(interval.get(1));
            maxPq.add(interval.get(0));
            maxPq.add(interval.get(1));
            index++;
            if (maxPq.size() == 4) {
                Integer min = minPq.peek();
                Integer max = maxPq.peek();

                maxPq.poll();
                int sum = 0;
                Set<Integer> intervalsSet = new HashSet<>();
                while (!maxPq.isEmpty()) {
                    intervalsSet.add(maxPq.poll());
                }
                for (Integer v : intervalsSet) {
                    sum = sum + v;
                }
                if (max - sum == 0) {
                    List<Integer> mergedInterval = new ArrayList<>();
                    mergedInterval.add(min);
                    mergedInterval.add(max);
                    List<List<Integer>> copy = new ArrayList<>(intervals);
                    copy.remove(0);
                    copy.remove(0);
                    copy.add(mergedInterval);
                    minPq.clear();
                    maxPq.clear();
                    for (List<Integer> integers : copy) {
                        ans.add(integers);
                    }
                    mergeOverlapIntervals(copy, ans, minPq, maxPq);
                }
            }
        }

    }

    public static void main(String[] args) {
        int[][] intervals = {{1,3},{2,6},{8,10},{15,18}};
        int[][] intervals2 = {{1,4},{4,5}};
        int[][] intervals3 = {{1,4},{1,4}};
        //System.out.println(Arrays.deepToString(new MergeIntervals().merge(intervals)));
        //System.out.println(Arrays.deepToString(new MergeIntervals().merge(intervals2)));
        System.out.println(Arrays.deepToString(new MergeIntervals().merge(intervals3)));
    }
}
