import java.util.*;
import java.util.stream.Collectors;

public class MergeIntervals {

    public int[][] merge(int[][] intervals) {
        if (intervals.length == 1)
            return intervals;

        List<int[]> sortedArrayEntries = Arrays.stream(intervals)
                .sorted(Comparator.comparingInt(arrayEntryOne -> arrayEntryOne[0]))
                .collect(Collectors.toList());

        MyTree tree = new MyTree();

        for (int[] col : sortedArrayEntries) {
            int st = col[0];
            int et = col[1];
            IntervalPoint intervalPoint = new IntervalPoint(st, et);
            tree.addItem(intervalPoint);
        }

        return tree.convertToArray();
    }

    static class MyTree {
        Node root;

        public MyTree() {
        }

        public void addItem(IntervalPoint point) {
            root = addNode(point, root);
        }
        private Node addNode(IntervalPoint point, Node node) {
            if (node == null) return new Node(point, null, null);
            if (node.point.compareTo(point) < 0) {
                // left then merge
                node.point = node.point.maxCombine(point);
                //node.left = addNode(point, node.left);
            } else node.right = addNode(point, node.right);
            return node;
        }

        public int[][] convertToArray() {
            List<int[]> ans = new ArrayList<>();
            traverse(ans, root);
            int[][] finalAns = new int[ans.size()][2];
            int index = 0;
            for (int[] an : ans) {
                finalAns[index++] = an;
            }
            return finalAns;
        }

        private void traverse(List<int[]> ans, Node node) {
            Node temp = node;
            while (temp != null) {
                ans.add(new int[] {temp.point.startTime, temp.point.endTime});
                temp = temp.right;
            }
        }
    }

    static class Node {
        IntervalPoint point;
        Node left;
        Node right;

        public Node(IntervalPoint point, Node left, Node right) {
            this.left = left;
            this.right = right;
            this.point = point;
        }
    }

    static class IntervalPoint implements Comparable<IntervalPoint> {
        int startTime;
        int endTime;


        public IntervalPoint(int startTime, int endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }

        public IntervalPoint maxCombine(IntervalPoint point) {
            int max = Math.max(this.endTime, point.endTime);
            int min = Math.min(this.startTime, point.startTime);
            return new IntervalPoint(min, max);
        }

        @Override
        public int compareTo(IntervalPoint first) {
            if ((first.endTime >= this.startTime) && (first.startTime <= this.endTime)) return -1;
            return 1;
        }

        @Override
        public String toString() {
            return "IntervalPoint{" +
                    "startTime=" + startTime +
                    ", endTime=" + endTime +
                    '}';
        }
    }

    public static void main(String[] args) {
        int[][] intervals = {{1,3},{2,6},{8,10},{15,18}};
        int[][] intervals2 = {{1,4},{4,5}};
        int[][] intervals3 = {{1,4},{1,4}};
        System.out.println(Arrays.deepToString(new MergeIntervals().merge(intervals)));
        System.out.println(Arrays.deepToString(new MergeIntervals().merge(intervals2)));
        System.out.println(Arrays.deepToString(new MergeIntervals().merge(intervals3)));
    }
}
