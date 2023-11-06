import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        long seed = 123456789;
        Random rd = new Random(seed);
        int nElementos = 0;
        int tamanhoTabela = 0;
        int[] vetor;
        // definindo número de elementos
        System.out.println("=== TABELA HASH ===");
        System.out.println("Selecione o número de elementos para inserir na tabela hash");
        System.out.println("1. 20 mil elementos");
        System.out.println("2. 100 mil elementos");
        System.out.println("3. 500 mil elementos");
        System.out.println("4. 1 milhão de elementos");
        System.out.println("5. 5 milhões de elementos");
        System.out.println(" ");
        System.out.print("Escolha uma das opções: ");
        int opcaoElementos = scan.nextInt();
        switch (opcaoElementos){
            case 1:
                nElementos = 20000;
                break;
            case 2:
                nElementos = 100000;
                break;
            case 3:
                nElementos = 500000;
                break;
            case 4:
                nElementos = 1000000;
                break;
            case 5:
                nElementos = 5000000;
                break;
            default:
                System.out.println("Opção inválida");
                break;
        }
        // definindo tamanho da tabela
        System.out.println("Selecione o tamanho da tabela hash");
        System.out.println("1. 10000");
        System.out.println("2. 20000");
        System.out.println("3. 35000");
        System.out.println("4. 50000");
        System.out.println("5. 100000");
        System.out.println(" ");
        System.out.print("Escolha uma das opções: ");
        int opcaoTamanho = scan.nextInt();
        switch (opcaoTamanho){
            case 1:
                tamanhoTabela = 10000;
                break;
            case 2:
                tamanhoTabela = 20000;
                break;
            case 3:
                tamanhoTabela = 35000;
                break;
            case 4:
                tamanhoTabela = 50000;
                break;
            case 5:
                tamanhoTabela = 100000;
                break;
            default:
                System.out.println("Opção inválida");
                break;
        }
        scan.close();
        // preenchendo vetor com os dados que serão utilizados
        vetor = new int[nElementos];
        for(int i = 0; i < nElementos; i ++){
            int chave = Integer.parseInt(String.format("%09d", rd.nextInt(1000000000)));
            vetor[i] = chave;
        }
        // instanciando as tabelas
        HashTable hashTableDivisao = new HashTable(tamanhoTabela);
        HashTable hashTableMultiplicacao = new HashTable(tamanhoTabela);
        HashTable hashTableDobramento = new HashTable(tamanhoTabela);
        // criando os atributos para fazer o cálulo do tempo de execução
        long inicioResto;
        long fimResto;
        long inicioMultiplicacao;
        long fimMultiplicacao;
        long inicioDobramento;
        long fimDobramento;
        System.out.println(" ");
        System.out.println("Iniciando execução. Aguarde...");
        System.out.println(" ");
//         inserindo valores na tabela hash
        inicioResto = System.nanoTime();
        for (int key : vetor){
            hashTableDivisao.inserir(key, 1);
        }
        fimResto = System.nanoTime();
        long insercaoResto = fimResto - inicioResto;
        // feedback da inserção no terminal
        System.out.println("\n");
        System.out.println("=====    HASH DE DIVISÃO    =====");
        System.out.println("Tempo em nanossegundos para a inserção: " + insercaoResto + "ns" + " Aproximadamente " + insercaoResto / 1000000000 + "s");
        System.out.println("Total de colisões da inserção: " + hashTableDivisao.getTotalColisoes());
        System.out.println(" ");
        // buscando valores na tabela hash
        inicioResto = System.nanoTime();
        for(int i = 0; i < 5; i ++){
            for (int key : vetor){
                hashTableDivisao.buscaHashResto(key);
            }
        }
        fimResto = System.nanoTime();
        long buscaResto = (fimResto - inicioResto) / 5;
        // feedback da busca no terminal
        System.out.println("Média de tempo em nanossegundos para a busca de todos os elementos: " + buscaResto + "ns" + " Aproximadamente " + buscaResto / 1000000000 + "s");
        System.out.println("Total de comparações realizadas na busca de todos os elementos: " + hashTableDivisao.getTotalComparacoes() / 5);
        // inserindo valores na tabela hash
        inicioMultiplicacao = System.nanoTime();
        for (int key : vetor){
            hashTableMultiplicacao.inserir(key, 2);
        }
        fimMultiplicacao = System.nanoTime();
        long insercaoMultiplicacao = fimMultiplicacao - inicioMultiplicacao;
        // feedback da inserção no terminal
        System.out.println("\n");
        System.out.println("=====    HASH DE MULTIPLICAÇÃO    =====");
        System.out.println("Tempo em nanossegundos para a inserção: " + insercaoMultiplicacao + "ns" + " Aproximadamente " + insercaoMultiplicacao / 1000000000 + "s");
        System.out.println("Total de colisões da inserção: " + hashTableMultiplicacao.getTotalColisoes());
        System.out.println(" ");
        // buscando valores na tabela
        inicioMultiplicacao = System.nanoTime();
        for(int i = 0; i < 5; i ++){
            for (int key : vetor){
                hashTableMultiplicacao.buscaHashMultiplicacao(key);
            }
        }
        fimMultiplicacao = System.nanoTime();
        long buscaMultiplicacao = (fimMultiplicacao - inicioMultiplicacao) / 5;
        // feedback da busca no terminal
        System.out.println("Média de tempo em nanossegundos para a busca de todos os elementos: " + buscaMultiplicacao+ "ns" + " Aproximadamente " + buscaMultiplicacao / 1000000000 + "s");
        System.out.println("Total de comparações realizadas na busca de todos os elementos: " + hashTableMultiplicacao.getTotalComparacoes() / 5);
//         inserindo valores na tabela
        inicioDobramento = System.nanoTime();
        for (int key : vetor){
            hashTableDobramento.inserir(key, 3);
        }
        fimDobramento = System.nanoTime();
        long insercaoDobramento = fimDobramento - inicioDobramento;
        // feedback da inserção no terminal
        System.out.println("\n");
        System.out.println("=====    HASH DE DOBRAMENTO    =====");
        System.out.println("Tempo em nanossegundos para a inserção: " + insercaoDobramento + "ns" + " Aproximadamente " + insercaoDobramento / 1000000000 + "s");
        System.out.println("Total de colisões da inserção: " + hashTableDobramento.getTotalColisoes());
        System.out.println(" ");
        // buscando valores na tabela
        inicioDobramento = System.nanoTime();
        for(int i = 0; i < 5; i ++){
            for (int key : vetor){
                hashTableDobramento.buscaHashDobramento(key);
            }
        }
        fimDobramento = System.nanoTime();
        long buscaDobramento = (fimDobramento - inicioDobramento) / 5;
        // feedback da busca no terminal
        System.out.println("Média de tempo em nanossegundos para a busca de todos os elementos: " + buscaDobramento + "ns" + " Aproximadamente " + buscaDobramento / 1000000000 + "s");
        System.out.println("Total de comparações realizadas na busca de todos os elementos: " + hashTableDobramento.getTotalComparacoes() / 5);


        // TESTANDO A TABELA HASH

        // instancia a tabela
//        HashTable  hash = new HashTable(1000);
//        // gerando a chave
//        for(int i = 0; i <= 10000; i++){
//            int key = Integer.parseInt(String.format("%09d", rd.nextInt(1000000000)));
//            hash.inserir(key, 1);
//        }


        // TESTANDO MÉTODOS HASH
//        Random random = new Random();
//        HashTable hash1 = new HashTable(10000);
////        int key = Integer.parseInt(String.format("%09d", random.nextInt(1000000000)));
//        int key = 123456789;
//        System.out.println("Key: " + key);
//        System.out.println(" ");
//        // hash de divisao
//        int hashKeyResto = hash1.hashResto(key);
//        System.out.println("Hash com resto da divisão: " + hashKeyResto);
//        System.out.println(" ");
//        // Hash com fator de multiplicação
//        int hashKeyMultiplicacao = hash1.hashMultiplicacao(key);
//        System.out.println("Hash com fator de multiplicação: " + hashKeyMultiplicacao);
//        System.out.println(" ");
//        // Hash com dobramento
//        int hashKeyDobramento = hash1.hashDobramento(key);
//        System.out.println("Hash com dobramento: " + hashKeyDobramento);


        // TESTANDO A TABELA

//        Scanner scanner = new Scanner(System.in);
//        Registro[] hastTable;
//        HashTable hash = new HashTable(1000);
//        int key;
//        int hashValue;
//        while(true){
//            System.out.println("=== TESTANDO TABELA ===");
//            System.out.println("1. Hash Divisão");
//            System.out.println("2. Hash Multiplicação");
//            System.out.println("3. Hash Dobramento");
//            System.out.print("Escolha uma das opções: ");
//            int opcao = scanner.nextInt();
//            scanner.close();
//            switch (opcao){
//                case 1:
//                    key = Integer.parseInt(String.format("%09d", rd.nextInt(1000000000)));
//                    System.out.println("Chave: " + key);
//                    hashValue = hash.hashResto(key);
//                    System.out.println("Hash: " + hashValue);
//                    hash.inserir(key, 1);
//                    hastTable = hash.getHashTable();
//                    System.out.println("hashTable[" + hashValue + "] = " + hash.buscaHashResto(key));
//                    break;
//                case 2:
//                    key = Integer.parseInt(String.format("%09d", rd.nextInt(1000000000)));
//                    System.out.println("Chave: " + key);
//                    hashValue = hash.hashMultiplicacao(key);
//                    System.out.println("Hash: " + hashValue);
//                    hash.inserir(key, 2);
//                    hastTable = hash.getHashTable();
//                    System.out.println("hashTable[" + hashValue + "] = " + hash.buscaHashMultiplicacao(key));
//                    break;
//                case 3:
//                    key = Integer.parseInt(String.format("%09d", rd.nextInt(1000000000)));
//                    System.out.println("Chave: " + key);
//                    hashValue = hash.hashDobramento(key);
//                    System.out.println("Hash: " + hashValue);
//                    hash.inserir(key, 3);
//                    hastTable = hash.getHashTable();
//                    System.out.println("hashTable[" + hashValue + "] = " + hash.buscaHashDobramento(key));
//                    break;
//                default:
//                    System.out.println("Opção inválida!");
//                    break;
//            }
//        }


        // TESTANDO ENCADEMENTO DAS COLISÕES

//        HashTable hash = new HashTable(1000);
//        hash.inserir(123456789, 1);
//        hash.inserir(223456789, 1);
//        hash.inserir(323456789, 1);
//        hash.inserir(423456789, 1);
//        Integer dado;
//        dado = hash.buscaHashResto(123456789);
//        System.out.println(dado);
//        dado = hash.buscaHashResto(223456789);
//        System.out.println(dado);
//        dado = hash.buscaHashResto(323456789);
//        System.out.println(dado);
//        dado = hash.buscaHashResto(423456789);
//        System.out.println(dado);
//        Registro[] linkedList = hash.getHashTable();
//        Registro lista = linkedList[789];
//        while(true){
//            System.out.print(lista.getKey() + " -> ");
//            lista = lista.getNext();
//            if(lista == null){
//                break;
//            }
//        }
    }
}