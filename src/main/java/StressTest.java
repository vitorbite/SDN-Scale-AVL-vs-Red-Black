import java.util.Random;

public class StressTest {

    // CUIDADO, ATENTECE AS OPÇÕES DE DEBUG ANTES DE RODAR O TESTE POIS A OPÇÃO DE DEBUG 
    // PODE CAUSAR UM USO EXCESSIVO DE MEMÓRIA E FAZER O TESTE DEMORAR MUITO, ENTÃO RECOMENDO USAR
    //  A OPÇÃO "Launch StressTest Pouco Uso de Memória" PARA TESTES INICIAIS (DE 1-10 Milhões) E APENAS QUANDO
    //  VOCÊ ESTIVER PRONTO PARA O TESTE COMPLETO, USE A OPÇÃO "Launch StressTest Grande Uso de Memória" (11-40 Milhões).
    // Caso você tenha menos de 8GB de RAM Livre, não use a opção de "Grande Uso de Memória" ao inves disso
    // altere "-Xms512m -Xmx7680m -XX:+UseG1GC" em launch.json para um valor menor, como "-Xms512m -Xmx4096m -XX:+UseG1GC"
    public static void main(String[] args) {
        int TOTAL_NODES = 40000000; //Atente-se as configurações de debug
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
        long startAVLSearch = System.nanoTime();
        for (int i = 0; i < TOTAL_NODES; i++) {
            avlTree.search(rootAVL, rules[i].getId());

            // Imprime o tempo a cada 100.000 buscas para você colocar no gráfico
            if ((i + 1) % 100000 == 0) {
                long partialTime = System.nanoTime() - startAVLSearch;
                System.out.println("Tempo até " + (i + 1) + " buscas: " + partialTime + " ns");
            }
        }
        long endAVLSearch = System.nanoTime();
        long avlSearchTime = endAVLSearch - startAVLSearch;

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

        // Vou implementar quando a Red-Black tree estiver pronta, mas por enquanto deixo o código
        //  comentado para não causar confusão e ficar mais fácil na hora de implementar a comparação final.
        
        /*
        System.out.println("= RESULTADOS RED-BLACK (em nanossegundos) =");
        System.out.println("Inserção: " + rbInsertTime + " ns");
        System.out.println("Busca:    " + rbSearchTime + " ns");
        System.out.println("Deleção:   " + rbDeleteTime + " ns\n");
        */


    // Comparação entre Red-Black e AVL
  
/*
        System.out.println("=== ANÁLISE COMPARATIVA FINAL ===");
        
        // Calculando as diferenças absolutas
        long diffInsercao = Math.abs(avlInsertTime - rbInsertTime);
        long diffBusca = Math.abs(avlSearchTime - rbSearchTime);
        long diffDelecao = Math.abs(avlDeleteTime - rbDeleteTime);


        // Inserção: Ganhadora?
        if (avlInsertTime < rbInsertTime) {
            System.out.println("Vencedora na Inserção: AVL (Foi " + diffInsercao + " ns mais rápida)");
        } else {
            System.out.println("Vencedora na Inserção: Red-Black (Foi " + diffInsercao + " ns mais rápida)");
        }


        // Busca: Ganhadora?
        if (avlSearchTime < rbSearchTime) {
            System.out.println("Vencedora na Busca: AVL (Foi " + diffBusca + " ns mais rápida)");
        } else {
            System.out.println("Vencedora na Busca: Red-Black (Foi " + diffBusca + " ns mais rápida)");
        }
        
        // Exclusão: Ganhadora?
        if (avlDeleteTime < rbDeleteTime) {
            System.out.println("Vencedora na Exclusão: AVL (Foi " + diffDelecao + " ns mais rápida)");
        } else {
            System.out.println("Vencedora na Exclusão: Red-Black (Foi " + diffDelecao + " ns mais rápida)");
        }


*/


        System.out.println("Benchmark concluído!");
    }
}