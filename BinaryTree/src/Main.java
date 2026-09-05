import java.util.HashMap;

public class Main {
    static HashMap<String, Node> campus = new HashMap<>();

    public static void main(String[] args) {
        campus.put("Anália Franco", null);
        campus.put("Guarulhos", null);
        campus.put("Liberdade", null);
        campus.put("Paulista", null);
        campus.put("São Miguel", null);
        campus.put("Santo Amaro", null);
        campus.put("Villa Lobos", null);
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
    static boolean findStudent(String name){
    for (String nomeCampus : campus.keySet()) {
        Node root = campus.get(nomeCampus);
        boolean found = NodeTree.search(root, name);

        if (found) {
            return true;
        }
    }return false;}
    static void listStudentsByCampus(String campusName) {
        Node root = campus.get(campusName);

        if (root == null) {
            System.out.println("Não há aluno cadastrado neste campus.");
            return;
        }NodeTree.list(root);}

    // GALEGO MEXEU AQUI:
    static boolean deleteStudent(String name) {
        for (String nomeCampus : campus.keySet()) {
            Node root = campus.get(nomeCampus);
            boolean found = NodeTree.search(root, name);

            if (found) {
                Node novaRaiz = NodeTree.delete(root, name);
                campus.put(nomeCampus, novaRaiz);
                return true;
            }
        }
        return false;
    }
}