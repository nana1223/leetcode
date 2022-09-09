package labuladong.图;

/**
 * 并查集算法Union-Find
 * 并查集算法总结： https://blog.csdn.net/weixin_50666824/article/details/123630135
 * <p>
 * 本代码的优化地方：
 * 1. 防止出现「头重脚轻」的不平衡状况，希望小一些的树接到大一些的树下面，这样就能避免头重脚轻，更平衡一些。解决方法是额外使用一个size数组，记录每棵树包含的节点数
 * 2. 其实我们并不在乎每棵树的结构长什么样，只在乎根节点。因为无论树长啥样，树上的每个节点的根节点都是相同的，所以进一步压缩每棵树的高度，使树高始终保持为常数，
 *    具体做法是在findRoot时增加压缩树的代码，使树的结构始终为两层
 */
public class UnionFind {

    //连通分量个数
    private int count;

    //存储一棵树
    private int[] parent;

    //记录树的重量
    private int[] size;


    public UnionFind(int n) {
        this.count = n;
        parent = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    //将节点p和节点q连通
    public void union(int p, int q) {
        int rootP = findRoot(p);
        int rootQ = findRoot(q);
        //p和q已经在同一个连通分量里
        if (rootP == rootQ) {
            return;
        }
        //小树接到大树下面比较平衡（不过findRoot时候会压缩树的结构）
        if (size[rootP] > size[rootQ]) {
            parent[rootQ] = rootP;
            size[rootP] += size[rootQ];
        } else {
            parent[rootP] = rootQ;
            size[rootQ] += size[rootP];
        }
        //两个连通分量合并成一个连通分量
        count--;
    }

    //判断节点p和节点q是否连通
    public boolean connected(int p, int q) {
        int rootP = findRoot(p);
        int rootQ = findRoot(q);
        return rootP == rootQ;
    }

    // 返回图中的连通分量个数
    public int count() {
        return count;
    }


    //返回节点x的连通分量根节点
    private int findRoot(int x) {
        if (parent[x] != x) {
            //进行路径压缩，把连通分量的整个树压缩成两层，第一层就一个根节点，第二层所有其余的节点。这样在寻找两个连通分量的根节点时间复杂度就是O(1)
            parent[x] = findRoot(parent[x]);
        }
        return parent[x];
    }

    //findRoot的迭代版理解
    public int findRootIterationV(int x) {
        // 先找到根节点
        int root = x;
        while (parent[root] != root) {
            root = parent[root];
        }
        // 然后把 x 到根节点之间的所有节点直接接到根节点下面
        int old_parent = parent[x];
        while (x != root) {
            parent[x] = root;
            x = old_parent;
            old_parent = parent[old_parent];
        }
        return root;
    }
}
