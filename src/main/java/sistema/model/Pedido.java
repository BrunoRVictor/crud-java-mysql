package sistema.model;

public class Pedido {
    int idCliente;
    int idProduto;
    int id;

    public Pedido(int idCliente, int idProduto) {
        this.idCliente = idCliente;
        this.idProduto = idProduto;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

     @Override
     public String toString() {
        return "[- ID: " + id + " | ID do Produto: " + idProduto + " | ID do Cliente: " + idCliente + "]";
     }
}
