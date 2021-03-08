package algorithm;

import share.TreeNode;

public class Serializeanddeoftree {

    private  String deserializeStr;

    public String Serializer(TreeNode t){
        if(t==null){return "#";}
        return t.val + " " + Serializer(t.left) + " " + Serializer(t.right);
    }

    public TreeNode Deserialize(String str) {
        deserializeStr = str;
        return Deserialize();
    }

    private TreeNode Deserialize() {
        if (deserializeStr.length() == 0)
            return null;
        int index = deserializeStr.indexOf(" ");
        String node = index == -1 ? deserializeStr : deserializeStr.substring(0, index);
        deserializeStr = index == -1 ? "" : deserializeStr.substring(index + 1);
        if (node.equals("#"))
            return null;
        int val = Integer.valueOf(node);
        TreeNode t = new TreeNode(val);
        t.left = Deserialize();
        t.right = Deserialize();
        return t;
    }

    public String str="";//变化使用

    public TreeNode Deserializer(){
        if (str.length() == 0)
            return null;
        int index = str.indexOf(" ");
        if(index==-1) {str ="";return  null;}
        String node = str.substring(0, index);
        str = str.substring(index + 1);
        if (node.equals("#"))
            return null;
        int val = Integer.valueOf(node);

        TreeNode r = new TreeNode(val);
        r.left=Deserializer();
        r.right =Deserializer();

        return r;
    }
}
