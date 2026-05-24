public class PacketRule {
    private int id;
    private String ipOrigem;
    private String ipDestino;
    private int prioridade;

    public PacketRule(int id, String ipOrigem, String ipDestino, int prioridade) {
        this.id = id;
        this.ipOrigem = ipOrigem;
        this.ipDestino = ipDestino;
        this.prioridade = prioridade;
    }
    public int getId() {
        return id;
    }
    public String getIpDestino() {
        return ipDestino;
    }
    public String getIpOrigem() {
        return ipOrigem;
    }
    public int getPrioridade() {
        return prioridade;
    }
}
