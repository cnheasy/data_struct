package com.cnheasy.tree.binaryTree;

import com.cnheasy.baseCommon.Base;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Objects;

public class BinaryTree<E extends Comparable<E>> extends Base {

    /**
     * 创建树节点
     */
    class Node {
        E data;//数据
        Node leftChild;//左孩子
        Node rightChild;//右孩子

        public Node(E data) {
            this.data = data;
            this.leftChild = null;
            this.rightChild = null;
        }
    }

    /**
     * 元素个数记录器
     */
    private int size;

    /**
     * 获取树元素个数
     *
     * @return
     */
    public int getSize() {
        return size;
    }

    /**
     * 创建根节点
     */
    public Node root;

    /**
     * 添加元素
     *
     * @param data
     */
    public void add(E data) {
        root = createTree(root, data);
    }

    /**
     * 使用递归添加元素
     *
     * @param node
     * @param data
     * @return
     */
    private Node createTree(Node node, E data) {
        //数据不能为空
        if (Objects.isNull(data)) {
            return null;
        }
        //当当前节点为空时，添加元素，并且容量+1
        if (Objects.isNull(node)) {
            size++;
            return new Node(data);
        }
        //元素比较，如果输入的元素比存在的小，往当前元素的左边继续查找。如果还是小，继续往左边查找。否则往右边查找。直到值为空。添加新的元素
        if (data.compareTo(node.data) < 0) {
            node.leftChild = createTree(node.leftChild, data);
        } else if (data.compareTo(node.data) > 0) {
            node.rightChild = createTree(node.rightChild, data);
        } else {//暂时不比较重复的元素
            logger.info("不能包含重复数据");
//            return node;
        }
        return node;
    }

    /**
     * 前序遍历
     * 遍历顺序：根。左节点。右节点
     */
    public Collection<E> preOrder() {
        Collection<E> res = new ArrayList<E>();
        preOrder(root, res);
        return res;
    }

    /**
     * 使用递归遍历
     *
     * @param node
     */
    private void preOrder(Node node, Collection<E> res) {
        if (Objects.isNull(node)) {
            return;
        }
        res.add(node.data);
        preOrder(node.leftChild, res);
        preOrder(node.rightChild, res);
    }

    /**
     * 中序遍历
     * 遍历顺序：左节点。根。右节点
     */
    public Collection<E> inOrder() {
        Collection<E> res = new ArrayList<E>();
        inOrder(root, res);
        return res;
    }

    /**
     * 使用递归遍历
     *
     * @param node
     */
    private void inOrder(Node node, Collection<E> res) {
        if (Objects.isNull(node)) {
            return;
        }
        inOrder(node.leftChild, res);
        res.add(node.data);
        inOrder(node.rightChild, res);
    }

    /**
     * 中序遍历
     * 遍历顺序：左节点。右节点。根。
     */
    public Collection<E> postOrder() {
        Collection<E> res = new ArrayList<E>();
        postOrder(root, res);
        return res;
    }

    /**
     * 使用递归遍历
     *
     * @param node
     */
    private void postOrder(Node node, Collection<E> res) {
        if (Objects.isNull(node)) {
            return;
        }
        postOrder(node.leftChild, res);
        postOrder(node.rightChild, res);
        res.add(node.data);
    }

    public static void main(String[] args) {

        BinaryTree<Integer> bst = new BinaryTree<>();
        int[] nums = {5, 3, 6, 8, 4, 2};
        for (int num : nums)
            bst.add(num);

        System.out.println("size:" + bst.getSize());
        /////////////////
        //      5      //
        //    /   \    //
        //   3    6    //
        //  / \    \   //
        // 2  4     8  //
        /////////////////
        System.out.println("perOrder");
        System.out.println(StringUtils.join(bst.preOrder(), ","));
//        Collection<Integer> integers = bst.preOrder();
        System.out.println("inOrder");
        System.out.println(StringUtils.join(bst.inOrder(), ","));

        System.out.println("postOrder");
        System.out.println(StringUtils.join(bst.postOrder(), ","));

        System.out.println();

        System.out.println(bst);
    }
}
