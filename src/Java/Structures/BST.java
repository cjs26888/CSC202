package Java.Structures;

import Resources.Exceptions.InvalidInputException;
import Resources.Exceptions.UnderflowException;
import Resources.Interfaces.I_BST;

/**
 * Created by Biggus on 6/23/2017.
 */
public class BST<T extends Comparable<T>> implements I_BST
{
    protected class BST_Node
    {
        //Holds information that is wished to be stored in the tree and the references to its children
        T info;               //The info in a BST node in this case is a restaurant
        BST_Node left;        //A link to the left child node
        BST_Node right;       //A link to the right child node
        
        BST_Node()
        {
            info = null;
            left = null;
            right = null;
        }
    }
    
    //Member Variables (Attributes)
    private BST_Node root;
    private myQueue InOrder;
    private myQueue PreOrder;
    private myQueue PostOrder;
    
    //Constructor
    public BST()
    {
        root = null;
    }
    public BST(Object item)
    {
        this.add(item);
    }
    public BST(Object item1, Object item2){ this.add(item1); this.add(item2); }
    
    //Determines if the Tree has a component element
    @Override
    public boolean isEmpty()
    {
        return (root == null);
    }
    
    @Override
    public boolean isFull()
    {
        //Always false as this is a theoretically limitless tree
        return false;
    }
    
    @Override
    public int size()
    {
        return recSize(root);
    }
    
    @Override
    public boolean contains(Object item)
    {
        if(!isEmpty())
        {
            return recContains(item, root);
        }
        else
        {
            throw new UnderflowException("contains() attempted on an empty Tree");
        }
    }
    
    @Override
    public Object get(Object item)
    {
        return recGet(item, root);
    }
    
    @Override
    public void add(Object item)
    {
        root = recAdd(item,root);
    }
    
    @Override
    public void remove(Object item)
    {
        root = recRemove(item, root);
    }
    
    @Override
    public int reset(int orderType)
    {
        //System.out.println("OrderType: " + orderType);
        if(!isEmpty())
        {
            //System.out.println("Is empty in reset()");
            if(orderType <= 3 && orderType >= 1)
            {
                if (orderType == INORDER)
                {
                    //System.out.println("Inside inorder");
                    InOrder = new myQueue();
                    inOrder(root);
                    //System.out.println("In reset(): " + InOrder.toString());
                }
                else if (orderType == PREORDER)
                {
                    PreOrder = new myQueue();
                    preOrder(root);
                }
                else if (orderType == POSTORDER)
                {
                    PostOrder = new myQueue();
                    postOrder(root);
                }
            }
            else
            {
                throw new InvalidInputException("Order Type needs to be 1, 2, or 3");
            }
        }
        else
        {
            throw new UnderflowException("reset() attempted on an empty Tree");
        }
        
        return size();
    }
    
    @Override
    public Object getNext(int orderType)
    {
        Comparable tmp = null;
        
        if(orderType <= 3 && orderType >= 1)
        {
            if (orderType == INORDER)
            {
                tmp = (Comparable) InOrder.dequeue();
            }
            else if (orderType == PREORDER)
            {
                tmp = (Comparable) PreOrder.dequeue();
            }
            else if (orderType == POSTORDER)
            {
                tmp = (Comparable) PostOrder.dequeue();
            }
        }
        else
        {
            throw new InvalidInputException("Your order type needs to be 1, 2, or 3");
        }
        if(tmp == null)
        {
            throw new UnderflowException("getNext() attempted on empty Tree");
        }
        
        return tmp;
    }

    //todo needs to show by inorder, postorder, preorder
    @Override
    public String toString()
    {
        //System.out.println("Tree Size: " + tree.size());
        String str = "";
        int treeSize;
        treeSize = this.size();
        
        this.reset(1);
        
        for(int i = 0; i < treeSize; i++)
        {
            str = this.getNext(BST.INORDER).toString();
            //"StringProperty [value: Cam's Pizza]"
            str = str.replace("StringProperty [value: ", "");
            str = str.replace("]", "");
        }
        return str;
    }
    
    private void inOrder(BST_Node node)
    {
        if(node != null)
        {
            inOrder(node.left);
            InOrder.enqueue(node.info);
            inOrder(node.right);
            //System.out.println("In inOrder: " + InOrder.toString());
        }
        
    }
    
    private void preOrder(BST_Node node)
    {
        if(node != null)
        {
            PreOrder.enqueue(node.info);
            preOrder(node.left);
            preOrder(node.right);
        }
    }
    
    private void postOrder(BST_Node node)
    {
        if(node != null)
        {
            postOrder(node.left);
            postOrder(node.right);
            PostOrder.enqueue(node.info);
        }
    }
    
    private BST_Node removeNode(BST_Node node)
    {
        if (node.left == null)
        {
            return node.right;
        }
        else if (node.right == null)
        {
            return node.left;
        }
        else
        {
            while (node.right != null)
            {
                node = node.right;
            }
            node.left = recRemove(node.info, node.left);
        }
        
        return node;
    }
    
    private BST_Node recRemove(Object item, BST_Node node)
    {
        if(!isEmpty() && contains(item))
        {
            if(node.info.compareTo((T) item) > 0)
            {
                node.left = recRemove(item, node.left);
            }
            else if(node.info.compareTo((T) item) < 0)
            {
                node.right = recRemove(item, node.right);
            }
            else
            {
                node = removeNode(node);
            }
        }
        else
        {
            node = null;
            throw new UnderflowException("remove() attempted on an empty Tree");
        }
        
        return node;
    }
    
    private BST_Node recAdd(Object item, BST_Node node)
    {
        // Check the current node if > or <
        // > check child. If child == null set node's right child to the value
        // < "                                      " left child  "          "
        // if the left or right child is filled check that one and go back to the top line
        
        /*
        if(!isEmpty())
        {
            System.out.println("\nContains(\n\n" + item + "\n): " + contains(item));
        }
        */
        
        if(!isEmpty())
        {
            if(node == null)
            {
                node = new BST_Node();
                node.info = (T)item;
            }
            else /*if(!contains(item))*/
            {
                if (node.info.compareTo((T) item) > 0)
                {
                    node.left = recAdd(item, node.left);
                }
                else
                {
                    node.right = recAdd(item, node.right);
                }
            }
            /*else
            {
                throw new DuplicateException("Element already exists in this Tree");
            }*/
        }
        else
        {
            node = new BST_Node();
            node.info = (T)item;
        }
        
        return node;
    }
    
    private Object recGet(Object item, BST_Node node)
    {
        if(!isEmpty() && contains(item))
        {
            if (node.info.compareTo((T) item) > 0)
            {
                return recGet(item, node.left);
            }
            else if (node.info.compareTo((T) item) < 0)
            {
                return recGet(item, node.right);
            }
        }
        else
        {
            throw new UnderflowException("get() attempted on an empty Tree");
        }
        
        return node.info;
    }
    
    //Counts and sends back the number of constituent nodes
    //Node is empty. If true: return 0 as the size. If false: Run, recursively the method on node's constituents
    private int recSize(BST_Node node)
    {
        return ((node == null) ? 0 : recSize(node.left) + recSize(node.right) + 1);
    }
    
    private boolean recContains(Object item, BST_Node node)
    {
        //Initializing boolContains means if none of the conditions are true the item is found
        boolean boolContains;
        
        if(node == null)
        {
            //Item is not found
            boolContains = false;
        }
        else if (node.info.compareTo((T) item) > 0)
        {
            boolContains = recContains(item, node.left);
        }
        else
        {
            boolContains = node.info.compareTo((T) item) <= 0 || recContains(item, node.right);
        }
    
        return boolContains;
    }
    
    public myQueue getInOrder()
    {
        return InOrder;
    }
    
    public myQueue getPreOrder()
    {
        return PreOrder;
    }
    
    public myQueue getPostOrder()
    {
        return PostOrder;
    }
}
