package com.cnheasy.tree.binaryTree;

import com.cnheasy.baseCommon.Base;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import javax.xml.crypto.Data;
import javax.xml.transform.Source;
import java.util.*;

public class BinaryTree<E extends Comparable<E>> extends Base {

    /**
     * 创建树节点
     */
    class Node {
        E data;//数据
        Node leftChild;//左孩子（左节点）
        Node rightChild;//右孩子（右节点）

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
        //当当前节点为空时,添加元素,并且容量+1
        if (Objects.isNull(node)) {
            size++;
            return new Node(data);
        }
        //元素比较,如果输入的元素比存在的小,往当前元素的左边继续查找。如果还是小,继续往左边查找。否则往右边查找。直到值为空。添加新的元素
        if (data.compareTo(node.data) < 0) {
            node.leftChild = createTree(node.leftChild, data);
        } else if (data.compareTo(node.data) > 0) {
            node.rightChild = createTree(node.rightChild, data);
        } else {//暂时不比较重复的元素
            logger.info("不能包含重复数据");
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
     * 使用递归前序遍历
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
     * 非递归前序遍历,使用栈结构进行遍历
     * <p>
     * //////////////////////
     * //         10       //
     * //      /     \     //
     * //     5      12    //
     * //    / \    /  \   //
     * //   3  8   11   14 //
     * //      \           //
     * //      9           //
     * //////////////////////
     *
     * @param currentNode 需要遍历的节点
     * @return
     */
    public Collection<E> preOrder(Node currentNode) {
        Collection<E> res = new ArrayList<E>();
        Stack<Node> stack = new Stack();
        //当前节点不等空,或者栈顶不为空,都需要继续遍历
        while (Objects.nonNull(currentNode) || !stack.isEmpty()) {
            //循环访问当前节点的左节点,并入栈
            while (Objects.nonNull(currentNode)) {
                res.add(currentNode.data);
                stack.push(currentNode);
                currentNode = currentNode.leftChild;
            }
            //如果当前节点没有左孩子,出栈,弹出栈顶元素,访问出栈后节点的右节点
            if (!stack.isEmpty()) {
                currentNode = stack.pop();
                currentNode = currentNode.rightChild;
            }
        }
        return res;
    }


    /**
     * 1.先放中节点
     * 2.有右节点放右节点
     * 3.有左节点放左节点
     *
     * @param currentNode
     * @return
     */
    public Collection<E> preOrder2(Node currentNode) {
        Collection<E> res = new ArrayList<E>();
        Stack<Node> stack = new Stack();
        stack.add(currentNode);
        //当前节点不等空,或者栈顶不为空,都需要继续遍历
        while (!stack.isEmpty()) {
            currentNode = stack.pop();
            res.add(currentNode.data);
            if (Objects.nonNull(currentNode.rightChild)) {
                stack.push(currentNode.rightChild);
            }
            if (Objects.nonNull(currentNode.leftChild)) {
                stack.push(currentNode.leftChild);
            }
        }
        return res;
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
     * 非递归中序遍历,使用栈结构进行遍历
     * <p>
     * //////////////////////
     * //         10       //
     * //      /     \     //
     * //     5      12    //
     * //    / \    /  \   //
     * //   3  8   11   14 //
     * //      \           //
     * //      9           //
     * //////////////////////
     *
     * @param currentNode 需要遍历的节点
     * @return
     */
    public Collection<E> inOrder(Node currentNode) {
        Collection<E> res = new ArrayList<E>();
        Stack<Node> stack = new Stack();
        //当前节点不等空,或者栈顶不为空,都需要继续遍历
        while (Objects.nonNull(currentNode) || !stack.isEmpty()) {
            //循环访问当前节点的左节点,并入栈
            while (Objects.nonNull(currentNode)) {
                stack.push(currentNode);
                currentNode = currentNode.leftChild;
            }
            //如果当前节点没有左孩子,出栈,弹出栈顶元素,访问出栈后节点的右节点
            if (!stack.isEmpty()) {
                currentNode = stack.pop();
                res.add(currentNode.data);
                currentNode = currentNode.rightChild;
            }
        }
        return res;
    }

    /**
     * 中序遍历方式2
     * // 1.左节点不为null则压入左节点
     * // 2.左节点为null时，pop并打印，左节点
     * // 3.在往右，右节点为null时，pop并打印
     * // 4.右节点不为null时，压入右节点
     *
     * @param currentNode
     * @return
     */
    public Collection<E> inOrder2(Node currentNode) {
        Collection<E> res = new ArrayList<E>();
        Stack<Node> stack = new Stack();
        //当前节点不等空,或者栈顶不为空,都需要继续遍历
        while (Objects.nonNull(currentNode) || !stack.isEmpty()) {
            if (Objects.nonNull(currentNode)) {
                stack.add(currentNode);
                currentNode = currentNode.leftChild;
            } else {
                currentNode = stack.pop();
                res.add(currentNode.data);
                currentNode = currentNode.rightChild;
            }
        }
        return res;
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

    /**
     * 非递归后序遍历,使用栈结构进行遍历
     * //和前序遍历一样的只不过是使用了两个栈
     * //在前序遍历的时候将 中 右 左 节点压栈
     * //在原来是打印的地方不打印，将本该打印的节点压到第二个栈中
     * //这样第二个栈的出栈顺序就是 右 左 中了
     * <p>
     * //////////////////////
     * //         10       //
     * //      /     \     //
     * //     5      12    //
     * //    / \    /  \   //
     * //   3  8   11   14 //
     * //      \           //
     * //      9           //
     * //////////////////////
     *
     * @param currentNode 需要遍历的节点
     * @return
     */
    public Collection<E> postOrder(Node currentNode) {
        Collection<E> res = new ArrayList<E>();
        Stack<Node> stack = new Stack();
        Stack<Node> resStack = new Stack();
        //把当前元素添加到栈低
        stack.push(currentNode);
        //当前节点不等空,或者栈顶不为空,都需要继续遍历
        while (!stack.isEmpty()) {
            currentNode = stack.pop();
            resStack.push(currentNode);
            if (Objects.nonNull(currentNode.leftChild)) {
                stack.push(currentNode.leftChild);
            }
            if (Objects.nonNull(currentNode.rightChild)) {
                stack.push(currentNode.rightChild);
            }
        }

        while (!resStack.isEmpty()) {
            res.add(resStack.pop().data);
        }

        return res;
    }

    public static void main(String[] args) {

        BinaryTree<Integer> bst = new BinaryTree<>();
        int[] nums = {10, 5, 3, 8, 9, 12, 11, 14};
        for (int num : nums)
            bst.add(num);

        System.out.println("size:" + bst.getSize());
        //////////////////////
        //         10       //
        //      /     \     //
        //     5      12    //
        //    / \    /  \   //
        //   3  8   11   14 //
        //      \           //
        //      9           //
        //////////////////////

        bst.preOrder(bst.root);
        System.out.println("perOrder");
        System.out.println("递归前序遍历:" + StringUtils.join(bst.preOrder(), ","));
        System.out.println("非递归前序遍历:" + StringUtils.join(bst.preOrder(bst.root), ","));
        System.out.println("非递归前序遍历2:" + StringUtils.join(bst.preOrder2(bst.root), ","));
        System.out.println("inOrder");
        System.out.println("递归中序遍历:" + StringUtils.join(bst.inOrder(), ","));
        System.out.println("非递归中序遍历:" + StringUtils.join(bst.inOrder(bst.root), ","));
        System.out.println("非递归中序遍历2:" + StringUtils.join(bst.inOrder2(bst.root), ","));
        System.out.println("postOrder");
        System.out.println("递归后序遍历:" + StringUtils.join(bst.postOrder(), ","));
        System.out.println("非递归后序遍历:" + StringUtils.join(bst.postOrder(bst.root), ","));
    }
}
