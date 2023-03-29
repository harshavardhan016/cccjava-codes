public static List<Integer> findIndices(int n, List<List<Integer>> query) {
        int[] arr = new int[n + 1]; // initialize array with length n+1
        Arrays.fill(arr, -1); // initialize array with all elements set to -1
        
        for (List<Integer> q : query) {
            int l = q.get(0);
            int r = q.get(1);
            int sum = 0;
            
            for (int i = l; i <= r; i++) {
                if (arr[i] == -1) {
                    arr[i] = 0;
                }
                sum += arr[i];
            }
            
            int unknownIndex = -1;
            for (int i = l; i <= r; i++) {
                if (arr[i] == -1) {
                    if (unknownIndex != -1) {
                        unknownIndex = -2;
                        break;
                    } else {
                        unknownIndex = i;
                    }
                }
            }
            
            if (unknownIndex >= 0) {
                arr[unknownIndex] = sum - Arrays.stream(arr, l, r+1).sum();
            }
        }
        
        List<Integer> result = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (arr[i] != -1) {
                result.add(i);
            }
        }
        
        if (result.isEmpty()) {
            result.add(-1);
        }
        
        return result;
    }