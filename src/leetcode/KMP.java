package leetcode;

import java.util.Comparator;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.TreeSet;

public class KMP {
    public void getNext(int[] next, String s){
        int j = -1;
        next[0] = j;
        for (int i = 1; i < s.length(); i++) {
            while (j >= 0 && s.charAt(i) != s.charAt(j + 1)){
                j = next[j];
            }
            if (s.charAt(i) == s.charAt(j + 1)){
                j++;
            }
            next[i] = j;
        }
    }

    int strStr(String haystack, String needle) {
        int[] next = new int[needle.length()];
        getNext(next, needle);
        int j = -1;
        for (int i = 0; i < haystack.length(); i++){
            while (j >= 0 && haystack.charAt(i) != needle.charAt(j + 1)){
                j = next[j];
            }
            if (haystack.charAt(i) == needle.charAt(j + 1)){
                j++;
            }
            if (j == needle.length() - 1){
                return (i - needle.length() + 1);
            }
        }
        return -1;
    }
    public static void main(String[] args) {
        String s = "aabaaf";
        KMP test = new KMP();
        int[] next = new int[s.length()];
        test.getNext(next, s);
    }
}

class LRUCache {

    int capacity;
    int size;
    CacheNode head;
    CacheNode tail;
    HashMap<Integer, CacheNode> hashMap;
    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.hashMap = new HashMap<>();
        this.head = new CacheNode();
        this.tail = new CacheNode();
        tail.prev = head;
        head.next = tail;
    }

    public int get(int key) {
        if (hashMap.containsKey(key)){
            CacheNode node = hashMap.get(key);
            removeNode(node);
            addFromHead(node);
            return node.val;
        }
        return -1;
    }

    public void put(int key, int value) {
        CacheNode node;
        if (!hashMap.containsKey(key)){
            node = new CacheNode(value, key);
            hashMap.put(key, node);
            size++;
        }else {
            node = hashMap.get(key);
            node.val = value;
            removeNode(node);
        }
        addFromHead(node);
        if (size > capacity){
            hashMap.remove(removeFromTail().key);
        }
    }
    public void removeNode(CacheNode node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
    public void addFromHead(CacheNode node){
        CacheNode temp = head.next;
        head.next = node;
        node.next = temp;
        node.prev = head;
        temp.prev = node;
    }
    public CacheNode removeFromTail(){
        CacheNode temp = tail.prev;
        removeNode(temp);
        size--;
        return temp;
    }
}
class CacheNode{
    int val;
    int key;
    CacheNode prev;
    CacheNode next;
    public CacheNode(int val, int key){
        this.val = val;
        this.key = key;
    }

    public CacheNode() {}
}