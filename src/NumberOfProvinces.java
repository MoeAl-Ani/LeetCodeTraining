
public class NumberOfProvinces {
    /**
     * using dfs
     * @param isConnected
     * @return
     */
    public int findCircleNum2(int[][] isConnected) {
        int[] visited = new int[isConnected.length];
        int count = 0;
        for (int city = 0; city < isConnected.length; city++) {
            if (visited[city] == 0) {
                solve(isConnected, visited, city);
                count++;
            }
        }
        return count;
    }
    private void solve(int[][] isConnected, int[] visited, int city) {
        for (int j = 0; j < isConnected.length; j++) {
            if (isConnected[city][j] == 1 && visited[j]  == 0) {
                visited[j] = 1;
                solve(isConnected, visited, j);
            }
        }
    }

    /**
     * using Union find
     * @param isConnected
     * @return
     */
    public int findCircleNum(int[][] isConnected) {
        UF uf = new UF(isConnected.length);
        for (int i = 0; i < isConnected.length; i++) {
            for (int j = 0; j < isConnected.length; j++) {
                if (isConnected[i][j] == 1) {
                    uf.union(i,j);
                }
            }
        }
        return uf.count;
    }

    static class UF {
        private final int[] parent;
        private final byte[] rank;
        private int count;

        public UF(int n) {
            if (n < 0) throw new IllegalArgumentException();
            count = n;
            parent = new int[n];
            rank = new byte[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                rank[i] = 0;
            }
        }
        public int find(int p) {
            validate(p);
            while (p != parent[p]) {
                parent[p] = parent[parent[p]];
                p = parent[p];
            }
            return p;
        }
        public int count() {
            return count;
        }
        public void union(int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);
            if (rootP == rootQ) return;

            if (rank[rootP] < rank[rootQ]) parent[rootP] = rootQ;
            else if (rank[rootP] > rank[rootQ]) parent[rootQ] = rootP;
            else {
                parent[rootQ] = rootP;
                rank[rootP]++;
            }
            count--;
        }
        private void validate(int p) {
            int n = parent.length;
            if (p < 0 || p >= n) {
                throw new IllegalArgumentException("index " + p + " is not between 0 and " + (n-1));
            }
        }
    }


    public static void main(String[] args) {

        System.out.println(
                "expected 2 : " +
                new NumberOfProvinces().findCircleNum2(
                new int[][]{{1,1,0}, {1,1,0},{0,0,1}}
        ));
        System.out.println(
                "expected 3 : " +
                        new NumberOfProvinces().findCircleNum2(
                                new int[][]{{1,0,0}, {0,1,0},{0,0,1}}
                        ));
    }
}
