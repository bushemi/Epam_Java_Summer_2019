package com.epam.impl;

import com.epam.interfaces.Map;

import java.util.*;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public class HashMap<K, V> implements Map<K, V> {
    private short mapSize = 100;

    private Node<K, V>[] dataArray;
    private int size = 0;

    public HashMap() {
        dataArray = new Node[mapSize];
    }

    @Override
    public V get(K key) {
        long hash = getHash(key);
        int index = getIndexForElement(hash);
        Node<K, V> node = dataArray[index];
        if (key == node.key) {
            return node.value;
        } else {
            return findValueByKey(node, key);
        }
    }

    private V findValueByKey(Node<K, V> node, K key) {
        Node<K, V> next = node.next;
        if (isNull(next)) {
            return null;
        } else {
            if (next.key == key) {
                return next.value;
            } else {
                return findValueByKey(next, key);
            }
        }
    }

    private void setNewNode(K key, V value, int index) {
        Node<K, V> newNode = new Node<>(value, key, null);
        dataArray[index] = newNode;
    }

    private long getHash(K key) {
        return Integer.toUnsignedLong(Objects.hashCode(key));
    }

    private Node<K, V> getNeededNode(Node<K, V> node, K key) {
        Node<K, V> next = node.next;
        if (isNull(next)) {
            return node;
        } else {
            if (next.key == key) {
                return next;
            } else {
                return getNeededNode(next, key);
            }
        }
    }

    @Override
    public void put(K key, V value) {
        long hash = getHash(key);
        int index = getIndexForElement(hash);
        Node<K, V> node = dataArray[index];
        if (isNull(node)) {
            setNewNode(key, value, index);
            size++;
        } else {
            if (node.key == key) {
                node.value = value;
            } else {
                Node<K, V> neededNode = getNeededNode(node, key);
                neededNode.next = new Node<>(value, key, null);
                size++;
            }
        }
    }

    private int getIndexForElement(long hash) {
        return (int) (hash % mapSize);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<Entry<K, V>> iterator() {
        return Arrays.stream(dataArray)
                .filter(Objects::nonNull)
                .map(Node::getLinkedNodes)
                .flatMap(Collection::stream)
                .map(node -> new Entry<>(node.getKey(), node.getValue()))
                .iterator();
    }

    private class Node<K, V> {

        private V value;
        private K key;
        private Node<K, V> next;

        Node(V value, K key, Node<K, V> next) {
            this.value = value;
            this.key = key;
            this.next = next;
        }

        V getValue() {
            return value;
        }

        K getKey() {
            return key;
        }

        List<Node<K, V>> getLinkedNodes() {
            List<Node<K, V>> list = new ArrayList<>();
            list.add(this);
            if (nonNull(next)) {
                list.addAll(next.getLinkedNodes());
            }
            return list;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HashMap)) return false;
        HashMap<?, ?> hashMap = (HashMap<?, ?>) o;
        return Arrays.equals(dataArray, hashMap.dataArray);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(dataArray);
    }
}
