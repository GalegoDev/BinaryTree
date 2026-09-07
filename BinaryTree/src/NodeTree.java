import java.util.ArrayList;
import java.util.List;

public class NodeTree {

    public static Node insert(Node currentName, String name) {
        if (currentName == null) {
            return new Node(name);
        }

            int compare = name.compareTo(currentName.StundNam);

        if (compare < 0) {
            currentName.left = insert(currentName.left, name);
        } else if (compare > 0) {
            currentName.right = insert(currentName.right, name);
        }

        return currentName;
    }

    static boolean search(Node currentName, String name) {
        if (currentName == null) {
            return false;
        }

                int compare = name.compareTo(currentName.StundNam);

                if (compare == 0) {

                    return true;

                }      else if (compare<0) {
                    return search(currentName.left,name);

                }
                else{
                    return search(currentName.right,name);


            }}
        static void list(Node currentName){
            if (currentName == null){
                return ;
            }
            list(currentName.left);
            System.out.println(currentName.StundNam);
            list(currentName.right);
        }

    // GALEGO MEXEU AQUI:
    public static Node delete(Node root, String name) {
        if (root == null) {
            return root;
        }

        int compare = name.compareTo(root.StundNam);

        if (compare < 0) {
            root.left = delete(root.left, name);
        } else if (compare > 0) {
            root.right = delete(root.right, name);
        } else {
            // Caso 1 e 2: Sem filhos ou com apenas 1 filho
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            // Caso 3: O nó tem dois filhos
            root.StundNam = minValue(root.right);
            root.right = delete(root.right, root.StundNam);
        }

        return root;
    }
    
    static String minValue(Node root) {
        String minv = root.StundNam;
        while (root.left != null) {
            minv = root.left.StundNam;
            root = root.left;
        }
        return minv;
    }
}