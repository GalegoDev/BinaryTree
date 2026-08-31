import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {
    static HashMap<String, Node> campus = new HashMap<>();

    public static void main(String[] args) {
        initializeCampuses();
        MainWindow.launch();
    }

    static void initializeCampuses() {
        campus.put("Anália Franco", null);
        campus.put("Guarulhos", null);
        campus.put("Liberdade", null);
        campus.put("Paulista", null);
        campus.put("São Miguel", null);
        campus.put("Santo Amaro", null);
        campus.put("Villa Lobos", null);
    }

    static List<String> getCampusList() {
        return new ArrayList<>(campus.keySet());
    }

    static boolean registerStudent(String name, String campusName) {
        for (String nomeCampus : campus.keySet()) {
            Node root = campus.get(nomeCampus);
            boolean found = NodeTree.search(root, name);

            if (found) {
                return false;
            }
        }

        Node currentRoot = campus.get(campusName);
        Node novaRaiz = NodeTree.insert(currentRoot, name);
        campus.put(campusName, novaRaiz);
        return true;
    }

    static String findStudentCampus(String name) {
        for (String nomeCampus : campus.keySet()) {
            Node root = campus.get(nomeCampus);
            boolean found = NodeTree.search(root, name);

            if (found) {
                return nomeCampus;
            }
        }

        return null;
    }

    static List<String> getStudentsByCampus(String campusName) {
        Node root = campus.get(campusName);

        if (root == null) {
            return new ArrayList<>();
        }

        return NodeTree.listAsCollection(root);
    }
}
