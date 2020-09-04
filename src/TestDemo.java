/*
*
*
*                                    基于二叉树遍历 的一些具体问题
*
*
*
* */


public class TestDemo {

    static class Node{
        public char val;
        public Node left;
        public Node right;

        public Node(char val) {
            this.val = val;
            //left和right可以不用卸载构造方法里，应为医用类型成员变量默认值为null
            this.left = null;
            this.right = null;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "val=" + val +
                    '}';
        }
    }

    //用bulid方法构造一棵树，返回这棵树的根节点
    public static Node bulid(){
        Node A = new Node('A');
        Node B = new Node('B');
        Node C = new Node('C');
        Node D = new Node('D');
        Node E = new Node('E');
        Node F = new Node('F');
        Node G = new Node('G');

        A.left = B;
        A.right = C;
        B.left = D;
        B.right = E;
        E.left = G;
        C.right = F;
        return  A;
    }


    public static void preOrder(Node root){
        //前序遍历（先序遍历）：先访问根节点，递归访问左子树，再递归访问右子树
        //如果是空树，不需要任何操作
        if (root == null){
            return;
        }
        System.out.print(root.val + " ");
        preOrder(root.left);
        preOrder(root.right);
    }




    public static void inOrder(Node root){
        //中序遍历：先访问左子树，递归访问根节点，再递归访问右子树
        //如果是空树，不需要任何操作
        if (root == null){
            return;
        }
        inOrder(root.left);
        System.out.print(root.val + " ");
        inOrder(root.right);
    }



    public static void postOrder(Node root){
        //后序遍历：先访问左子树，递归访问右子树，再递归访问根节点
        //如果是空树，不需要任何操作
        if (root == null){
            return;
        }
        postOrder(root.left);
        postOrder(root.right);
        System.out.print(root.val + " ");
    }

    //计算节点的个数
    public static int treesize = 0;
    public static void size1(Node root){
        if (root == null){
            return;
        }
        treesize++;
        size1(root.left);
        size1(root.right);
    }

    //直接通过返回size确定树的节点个数
    public static int size2(Node root){
        if (root == null){
            return 0;
        }
        //整个树的节点个数=根节点个数+左子树节点个数+右子树节点个数
        return 1 + size2(root.left) + size2(root.right);
    }





    //求树的叶子节点个数 （叶子节点就是没有左右子树的）
    public static int leafSize = 0;
    public static void leafSize1(Node root){
        if(root == null){
            return;
        }
        if (root.left == null && root.right == null){
            leafSize++;
            return;
        }
        leafSize1(root.left);
        leafSize1(root.right);
    }


    //直接通过size返回叶子节点个数
    public static int ledfSize2(Node root){
        if (root == null){
            return 0;
        }
        if (root.right == null && root.left == null){
            return 1;
        }
        return ledfSize2(root.left) + ledfSize2(root.right);
    }




    //求第K层的节点个数 例：第三层
    public static int klevelSize(Node root , int k){
        if (root == null || k < 1){
            return 0;
        }
        //求第一层的节点个数
        if (k == 1){
            return 1;
        }
        //求树第k层节点个数 = 左子树的k-1层节点个数 + 右子树的k-1层节点个数
        //一开始root为A  k为3 不满足两个if 返回root左子树为B k为2
        //直接把B看做一个新的树 然后第一层是B自身 第二层的节点D和E，不满足两个if，
        // 继续返回root.left是D 此时K = 1 所以满足if k=1 得到第一个节点D
        //再递归得到E和F

        //求树第k层的节点个数 =  左子树k-1层节点个数 + 右子树k-1层节点个数
        //A 的第三层节点个数 = 左子树B 的第二层节点个数 + 右子树C的第二层节点个数
        //B的第二层节点个数 = 左子树D的第一层 + 右子树E的第一层（刚好满足if（k==1））返回1
        return klevelSize(root.left,k-1) + klevelSize(root.right,k-1);
    }





    //查找是否存在给定值
    public static Node result = null;
    public static void find1(Node root , char value){
        if (root == null){
            return;
        }
        if (root.val == value){
            result = root;
            return;
        }
        find1(root.left,value);
        find1(root.right,value);
    }

    //查找是否存在给定值
    public static Node find2(Node root , char value){
        if (root == null){
            return null;
        }
        //先看根节点是不是要找的value
        if (root.val == value){
            return root;
        }
        //再递归找左子树
        Node ret = find2(root.left,value);
        if (ret != null){
            //左子树找到了返回ret
            return ret;
        }
        //左子树没找到就递归右子树
        return find2(root.right,value);
    }



    public static void main(String[] args) {
        Node root = bulid();
        postOrder(root);
    }

}
