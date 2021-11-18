import java.util.ArrayList;

public class ServerService {
    ArrayList<String[]> productos;
    ArrayList<String[]> registros;
    FileHandler fileHandler;
    CallsFormatter callsFormatter;

    public ServerService(){
        fileHandler = new FileHandler();
        productos = fileHandler.readFile("productos.txt");
        registros = fileHandler.readFile("registros.txt");
        this.callsFormatter = new CallsFormatter();
    }

    public ArrayList<String[]> getProductos() {
        productos = fileHandler.readFile("productos.txt");
        return productos;
    }
    public int getProductosSize() {
        return productos.size();
    }
    public void setProductos(ArrayList<String[]> productos) {
        this.productos = productos;
        fileHandler.writeFile("productos.txt", this.productos);
    }
    public ArrayList<String[]> getRegistros() {
        registros = fileHandler.readFile("registros.txt");
        return registros;
    }
    public void setRegistros(ArrayList<String[]> registros) {
        fileHandler.writeFile("registros.txt", this.registros);
        this.registros = registros;
    }
    public String contar(){
        productos = fileHandler.readFile("productos.txt");
        ArrayList<String> variables = new ArrayList<>();

        for(int i=0; i<productos.size();i++){
            variables.add(productos.get(i)[0]);
            variables.add(productos.get(i)[1]);
        }
        return callsFormatter.generateResponse("contar", productos.size(), variables.iterator());
    }
    public String votar(String producto, int voto){
        productos = fileHandler.readFile("productos.txt");
        ArrayList<String> variables = new ArrayList<>();

        for(int i=0; i<productos.size();i++){
            if(productos.get(i)[0].equals(producto)){
                int valor = Integer.parseInt(productos.get(i)[1]) + voto;
                productos.get(i)[1] =  String.valueOf(valor);
                break;
            }
        }
        for(int i=0; i<productos.size();i++){
            variables.add(productos.get(i)[0]);
            variables.add(productos.get(i)[1]);
        }
        setProductos(productos);
        return callsFormatter.generateResponse("votar", productos.size(), variables.iterator());
    }
    public String registrar(String valor1, String valor2){
        registros = fileHandler.readFile("registros.txt");
        String[] registro = new String[2];
        registro[0] = valor1;
        registro[1] = valor2;
        registros.add(registro);
        setRegistros(registros);
        String response = "{\"servicio\":\"registrar\",\"respuestas\":1,\"respuesta1\":\"eventos\",\"valor1\":" + registros.size() + "}";
        return response;
    }
    public String listar(){
        registros = fileHandler.readFile("registros.txt");
        ArrayList<String> variables = new ArrayList<>();

        for(int i=0; i<registros.size();i++){
            variables.add(registros.get(i)[0]);
            variables.add(registros.get(i)[1]);
        }
        return callsFormatter.generateResponse("listar", registros.size(), variables.iterator());
    }

    public static void main(String[] args) {
        ServerService prueba = new ServerService();
        System.out.println(prueba.contar());
        System.out.println(prueba.listar());
        System.out.println(prueba.votar("Windows", 100));
        System.out.println(prueba.registrar("evento", "Probando"));
    }
}
