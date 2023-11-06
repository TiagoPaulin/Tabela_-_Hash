public class Registro {
    // definindo atributos
    private Integer key;
    private Registro next;
    // metodo construtor
    public Registro(){
        this.key = null;
        this.next = null;
    }
    // metodos get
    public Integer getKey(){
        return key;
    }
    public Registro getNext(){
        return next;
    }
    // metodos set
    public void setKey(Integer key){
        this.key = key;
    }
    public void setNext(Registro next){
        this.next = next;
    }
}
