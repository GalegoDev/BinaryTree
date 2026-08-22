public class Main {

        static class node {
            String StundNam;
            node left;
            node right;

            public node(String stundNam) {
                this.StundNam = stundNam;
                this.left = null;
                this.right = null;
            }
        static node insert (node currentName, String name){
                if (currentName == null){
                    return new node(name);
                }
                int compare = name.compareTo(currentName.StundNam);
                if (compare < 0){
                    currentName.left = insert(currentName.left, name);
                } else if (compare>0) {
                    currentName.right= insert(currentName.right,name);

                }
                return currentName;
        }
        static boolean search(node currentName, String name){
            if (currentName == null) {

                return false;
            }

            int compare = name.compareTo(currentName.StundNam);

            if (compare == 0) {

                return true;

            } else if (compare<0) {
                return search(currentName.left,name);

            }
            else{
                return search(currentName.right,name);
            }

        }
        static void list(node currentName){
                if (currentName == null){
                    return ;
                }
                 list(currentName.left);
                 System.out.println(currentName.StundNam);
                 list(currentName.right);
        }
        }



}
