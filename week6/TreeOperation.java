package week6;

import java.util.ArrayList;
import java.util.Scanner;

class Ref<T>
{
    public T Value;

    public Ref(T value)
    {
        Value = value;
    }
}

class TreeNode{
    public String cityName;

    public int depth = 0;
    public int parentWidth = 0;

    private TreeNode parent = null;
    public ArrayList<TreeNode> children = new ArrayList<TreeNode>();

    public boolean isLeaf = true;

    public int iterator = 0;

    public TreeNode(String pCityName){
        cityName = pCityName;
    }

    public TreeNode addChild(TreeNode treeNode){
        treeNode.setParent(this);
        children.add(treeNode);
        isLeaf = false;
        return treeNode;
    }

    public TreeNode addChild(String pCityName){
        TreeNode newNode = new TreeNode(pCityName);
        newNode.setParent(this);
        children.add(newNode);
        isLeaf = false;
        return newNode;
    }

    public void setParent(TreeNode treeNode){
        parent = treeNode;
        depth = treeNode.depth + 1;
        parentWidth = treeNode.parentWidth + treeNode.cityName.length() + 1;
    }
}

class Tree{

    public TreeNode rNode = null;
    private boolean isChangeLine = false;


    public Tree(){

    }

    private int getHeight(TreeNode treeNode){
        
        Ref<Integer> maxDepth = new Ref<Integer>(0);

        __getHeight(treeNode, maxDepth);

        return maxDepth.Value - treeNode.depth;

    }

    private void __getHeight(TreeNode treeNode, Ref<Integer> maxDepth){
        maxDepth.Value = Math.max(maxDepth.Value, treeNode.depth);
        // System.out.println("maxDepth:"+maxDepth);
        if(treeNode.isLeaf){
            return;
        }
        for (TreeNode childNode : treeNode.children) {
            __getHeight(childNode, maxDepth);
        }
    }

    public void searchCity(String cityName){
        TreeNode target = __searchCity(rNode, cityName);

        if(target != null){
            System.out.println("found:" + target.cityName + " depth:" + target.depth + " height:"+getHeight(target));
        } else {
            System.out.println(cityName + " didn't found!");
        }

    }

    private TreeNode __searchCity(TreeNode startNode, String cityName){
        if(startNode.cityName.equalsIgnoreCase(cityName)){
            return startNode;
        } else {
            if(startNode.isLeaf){
                return null;
            } else {
                for (TreeNode childNode : startNode.children) {
                    TreeNode ret = __searchCity(childNode, cityName);
                    if(ret != null){
                        return ret;
                    }
                }
            }
        }
        return null;
    }

    public void show(){
        System.out.println();
        __show(rNode);
        System.out.println();
    }

    private void __show(TreeNode treeNode){
        if(isChangeLine){
            for (int i = 0; i < treeNode.parentWidth; i++) {
                System.out.print(" ");
            }
            System.out.println("|");
            for (int i = 0; i < treeNode.parentWidth; i++) {
                System.out.print(" ");
            }
        }
        System.out.print(treeNode.cityName);

        if(treeNode.isLeaf){
            System.out.println();
            isChangeLine = true;
            return;
        } else {
            System.out.print("-");
            isChangeLine = false;
        }
        for (TreeNode childNode : treeNode.children) {
            __show(childNode);
        }
    }

    public void initTree(){
        rNode = new TreeNode("Victoria");
        
        TreeNode vancouver = rNode.addChild("Vancouver");
        TreeNode waterFront = vancouver.addChild("WaterFront");
        TreeNode gasTown = waterFront.addChild("GasTown");

        TreeNode renfrew = vancouver.addChild("Renfrew");
        TreeNode btc =renfrew.addChild("BroadwayTecnCenter");
        TreeNode nyit = btc.addChild("NYIT");
        TreeNode dataStructure = nyit.addChild("DataStructure");

        TreeNode burnaby = rNode.addChild("Burnaby");
        TreeNode metroTown = burnaby.addChild("MetroTown");
        TreeNode edmonds = burnaby.addChild("Edmonds");
        TreeNode crystallMall = metroTown.addChild("CrystalMall");

        TreeNode coquitlam = rNode.addChild("Coquitlam");
        
    }
    



}

public class TreeOperation{
    public static void main(String[] args) {
        
        Tree CitiesTree = new Tree();

        CitiesTree.initTree();

        CitiesTree.show();

        while(true){

            Scanner myObj = new Scanner(System.in);
            System.out.println("Please select a city(\'q\' for quit ):");

            String city = myObj.nextLine();
            if("q".equalsIgnoreCase(city)){
                System.out.println("Quit! Bye!");
                break;
            }
            CitiesTree.searchCity(city);

        }

    }
}
