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

                }       else if (compare<0) {
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
    }




