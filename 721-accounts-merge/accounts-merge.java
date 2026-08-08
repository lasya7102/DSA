class DisjointSet {
    int n;
    int[] rank;
    int[] parent;
    int[] size;

    public DisjointSet(int n) {
        this.n = n;
        rank = new int[n];
        parent = new int[n];
        size = new int[n];

        for (int i = 0; i < n; i++) {
            rank[i] = 0;
            size[i] = 1;
            parent[i] = i;
        }
    }

    public int findParent(int x) {
        if (parent[x] == x) {
            return x;
        }

        parent[x] = findParent(parent[x]);
        return parent[x];
    }

    public boolean find(int u, int v) {
        return findParent(u) == findParent(v);
    }

    public void unionByRank(int u, int v) {
        int p1 = findParent(u);
        int p2 = findParent(v);

        if (p1 == p2) return;

        if (rank[p1] < rank[p2]) {
            parent[p1] = p2;
        }
        else if (rank[p1] > rank[p2]) {
            parent[p2] = p1;
        }
        else {
            parent[p1] = p2;
            rank[p2]++;
        }
    }

    public void unionBySize(int u, int v) {
        int p1 = findParent(u);
        int p2 = findParent(v);

        if (p1 == p2) return;

        if (size[p1] <= size[p2]) {
            parent[p1] = p2;
            size[p2] += size[p1];
        }
        else {
            parent[p2] = p1;
            size[p1] += size[p2];
        }
    }
}


class Solution {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {

        HashMap<String, Integer> map = new HashMap<>();

        DisjointSet dj = new DisjointSet(accounts.size());

        // Step 1: Map email -> account
        // If email already exists, union the two accounts
        for (int i = 0; i < accounts.size(); i++) {

            for (int j = 1; j < accounts.get(i).size(); j++) {

                String email = accounts.get(i).get(j);

                if (map.containsKey(email)) {

                    int previousAccount = map.get(email);

                    dj.unionBySize(i, previousAccount);

                }
                else {
                    map.put(email, i);
                }
            }
        }

        // Step 2: Group emails according to their parent account
        HashMap<Integer, List<String>> merged = new HashMap<>();

        for (String email : map.keySet()) {

            int account = map.get(email);

            int parent = dj.findParent(account);

            if (!merged.containsKey(parent)) {
                merged.put(parent, new ArrayList<>());
            }

            merged.get(parent).add(email);
        }

        // Step 3: Create final answer
        List<List<String>> ans = new ArrayList<>();

        for (Map.Entry<Integer, List<String>> entry : merged.entrySet()) {

            int accountIndex = entry.getKey();

            List<String> emails = entry.getValue();

            Collections.sort(emails);

            List<String> temp = new ArrayList<>();

            // First element is account name
            temp.add(accounts.get(accountIndex).get(0));

            // Add sorted emails
            temp.addAll(emails);

            ans.add(temp);
        }

        return ans;
    }
}