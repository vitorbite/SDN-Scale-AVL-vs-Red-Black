import java.util.Random;

public class StressTest {

    public static void main(String[] args) {
        int TOTAL_NODES = 1000000; 
        int DELETE_NODES = (int) (TOTAL_NODES * 0.20); // 20% dos nós
        
        System.out.println("Preparando " + TOTAL_NODES + " PacketRules...");
        
        // 1. Geração de Data
        Random random = new Random(42); 
        PacketRule[] rules = new PacketRule[TOTAL_NODES];
        PacketRule[] rulesToDelete = new PacketRule[DELETE_NODES];
        
        for (int i = 0; i < TOTAL_NODES; i++) {
            // IDs ordenados forçam o pior cenário possíveçl! 
            // Para testar aleatóriamente podemos usar random.nextInt()
            int id = i + 1; 
            String ipOrigem = "192.168.1." + (i % 255);
            String ipDestino = "10.0.0." + (i % 255);
            int prioridade = random.nextInt(5) + 1;
            
            rules[i] = new PacketRule(id, ipOrigem, ipDestino, prioridade);
            
            // Separa os 20% primeiros e apaga depois
            if (i < DELETE_NODES) {
                rulesToDelete[i] = rules[i];
            }
        }
        

        System.out.println("Dados gerados! Iniciando Benchmark...\n");


        // Instanciando as árvores
        AVL_Router_Tree avlTree = new AVL_Router_Tree();
        
        AVLNode rootAVL = null;
        // RedBlackNode rootRB = null; (quando a implementação estiver pronta eu tiro o comentário)

        // Primeiro teste: Inserção
        long startAVLInsert = System.nanoTime();
        for (int i = 0; i < TOTAL_NODES; i++) {
            rootAVL = avlTree.insert(rootAVL, rules[i]);
        }
        long endAVLInsert = System.nanoTime();
        long avlInsertTime = endAVLInsert - startAVLInsert;
        // quando a implementação estiver pronta eu tiro o comentário) 
        /*
        long startRBInsert = System.nanoTime();
        for (int i = 0; i < TOTAL_NODES; i++) {
            rootRB = rbTree.insert(rootRB, rules[i]);
        }
        long endRBInsert = System.nanoTime();
        long rbInsertTime = endRBInsert - startRBInsert;
        */

        // Segundo teste: Busca
        long avlSearchTime = System.nanoTime();
for (int i = 0; i < TOTAL_NODES; i++) {
    rootAVL = avlTree.insert(rootAVL, rules[i]);
    
    // Imprime o tempo a cada 100.000 inserções para você colocar no gráfico
    if ((i + 1) % 100000 == 0) {
        long partialTime = System.nanoTime() - avlSearchTime;
        System.out.println("Tempo até " + (i + 1) + " inserções: " + partialTime + " ns");
    }
}

        // Terceiro teste: Exclusão
        long startAVLDelete = System.nanoTime();
        for (int i = 0; i < DELETE_NODES; i++) {
            rootAVL = avlTree.delete(rootAVL, rulesToDelete[i]);
        }
        long endAVLDelete = System.nanoTime();
        long avlDeleteTime = endAVLDelete - startAVLDelete;


        // Resultados
        System.out.println("= RESULTADOS AVL (em nanossegundos) =");
        System.out.println("Inserção: " + avlInsertTime + " ns");
        System.out.println("Busca:    " + avlSearchTime + " ns");
        System.out.println("Deleção:   " + avlDeleteTime + " ns\n");

        // Vou implementar quando a Red-Black tree estiver pronta, mas por enquanto deixo o código comentado para não causar confusão.
        
        /*
        System.out.println("= RESULTADOS RED-BLACK (em nanossegundos) =");
        System.out.println("Inserção: " + rbInsertTime + " ns");
        System.out.println("Busca:    " + rbSearchTime + " ns");
        System.out.println("Deleção:   " + rbDeleteTime + " ns\n");
        */

        
        System.out.println("Benchmark concluído!");
    }
}