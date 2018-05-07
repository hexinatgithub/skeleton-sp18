import edu.princeton.cs.algs4.Stack;

import java.util.*;

class Examprep05 {
    public boolean use_them_a(int k) {
        HashSet<Integer> A = new HashSet<>();
        for (Integer v : A) {
            if (A.contains(k - v)) {
                return true;
            }
        }
        return false;
    }

    public List<String> use_them_b(String[] words, int k) {
        HashMap<String, Integer> wordsCount = new HashMap<>();

        for (String word : words) {
            if (wordsCount.containsKey(word)) {
                wordsCount.put(word, wordsCount.get(word) + 1);
            } else {
                wordsCount.put(word, 1);
            }
        }

        PriorityQueue<String> pq = new PriorityQueue<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return wordsCount.get(01) - wordsCount.get(o2);
            }
        });

        for (String word : wordsCount.keySet()) {
            pq.add(word);
        }

        List<String> topKWords = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            topKWords.add(pq.poll());
        }

        return topKWords;
    }

    public class Mutant_ADTs_a<T> {
        private Stack<T> stack;

        public Mutant_ADTs_a() {
            stack = new Stack<>();
        }

        public void push(T v) {
            stack.push(v);
        }

        public T poll() {
            Stack<T> temp = new Stack<>();

            while (!stack.isEmpty()) {
                temp.push(stack.pop());
            }

            T result = temp.pop();

            while (!temp.isEmpty()) {
                stack.push(temp.pop());
            }

            return result;
        }
    }

    /* use PriorityQueue */
    public class Mutant_ADTs_b_s1 {
        public PriorityQueue<Integer> queue;

        public Mutant_ADTs_b_s1() {
            queue = new PriorityQueue<>(new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return -(o1 - o1);
                }
            });
        }

        public void push(int i) {
            queue.add(i);
        }

        public int pop() {
            return queue.poll();
        }
    }

    /* use stack */
    public class Mutant_ADTs_b_s2<Item extends Comparable> {
        public Stack<Item> a;
        private Stack<Item> b;

        public Mutant_ADTs_b_s2() {
            a = new Stack<>();
            b = new Stack<>();
        }

        public void push(Item i) {
            while (!a.isEmpty() && a.peek().compareTo(i) < 0) {
                b.push(i);
            }
            a.push(i);
            while (!b.isEmpty()) {
                a.push(b.pop());
            }
        }

        public Item pop() {
            return a.pop();
        }
    }
}